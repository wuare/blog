package top.wuareb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import top.wuareb.blog.domain.vo.user.Admin;
import top.wuareb.blog.domain.vo.user.Img;
import top.wuareb.blog.domain.vo.user.User;
import top.wuareb.blog.service.UserService;
import top.wuareb.blog.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.List;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	
	
	//ajax校验用户名是否被注册
	@RequestMapping(value="/ajaxUsername")
	public String ajaxFindByUsername(User user, HttpServletResponse response){
		User aUser =  userService.ajaxFindByUsername(user.getUsername());
		boolean b = true;
		
		if(aUser == null){
			try {
				response.getWriter().print(b);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}else{
			b = false;
			try {
				response.getWriter().print(b);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		return null;
	}
	
	
	
	
	@RequestMapping(value="/login")
	public ModelAndView login(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("login");
		return mv;
	}
	//登录
	@RequestMapping(value="/loginCheck")
	public ModelAndView loginCheck(User user, String vCode, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		if(user.getUsername()==null || user.getUsername().trim().equals("")){
			mv.addObject("msg", "用户名不能为空！");
			mv.setViewName("login");
			return mv;
		}
		if(user.getPassword()==null || user.getPassword().trim().equals("")){
			mv.addObject("msg", "密码不能为空！");
			mv.setViewName("login");
			return mv;
		}
		if(vCode==null || vCode.trim().equals("")){
			mv.addObject("msg", "验证码不能为空！");
			mv.setViewName("login");
			return mv;
		}
		if(!vCode.equalsIgnoreCase((String) request.getSession().getAttribute("vCode"))){
			mv.addObject("msg", "验证码不正确！");
			mv.setViewName("login");
			return mv;
		}
		String password = MD5Utils.md5(user.getPassword());
		user.setPassword(password);
		User u = userService.findByUserAndPass(user);
		if(u==null){
			mv.addObject("msg", "用户名或密码不正确！");
			mv.setViewName("login");
			return mv;
		}
		if(u.getState()==0){
			mv.addObject("msg", "用户未激活，请激活后登录！");
			mv.setViewName("login");
			return mv;
		}
		request.getSession().setAttribute("user", u);
		return new ModelAndView("redirect:/index");
	}
	
	@Token(save=true)
	@RequestMapping(value="/regist")
	public ModelAndView regist(){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("regist");
		return mv;
	}

	
	//注册功能
	@Token(remove=true)
	@RequestMapping(value="/registCheck")
	public ModelAndView registCheck(Model model, User user,String vCode, HttpServletRequest request){
		ModelAndView mv = new ModelAndView();
		boolean b = true;
		if(user.getUsername()==null || user.getUsername().trim().equals("")){
			mv.addObject("msg", "用户名不能为空！");
			b = false;
		}
		if(user.getPassword()==null || user.getPassword().trim().equals("")){
			mv.addObject("msg", "密码不能为空！");
			b = false;
		}
		if(user.getRepassword()==null || user.getRepassword().trim().equals("")){
			mv.addObject("msg", "确认密码不能为空！");
			b = false;
		}
		if(user.getEmail()==null || user.getEmail().trim().equals("")){
			mv.addObject("msg", "邮箱不能为空！");
			b = false;
		}
		if(vCode==null || vCode.trim().equals("")){
			mv.addObject("msg", "验证码不能为空！");
			b = false;
		}		
		if(!user.getPassword().equals(user.getRepassword())){
			mv.addObject("msg", "两次输入密码不一致！");
			b = false;
		}
		if(!vCode.equalsIgnoreCase((String) request.getSession().getAttribute("vCode"))){
			System.out.println(request.getSession().getAttribute("vCode"));
			mv.addObject("msg", "验证码不正确！");
			b = false;
		}
		model.addAttribute("user", user);
		if(b == false){
			mv.setViewName("regist");
			return mv;		
		}	
		String password = MD5Utils.md5(user.getPassword());
		user.setPassword(password);
		userService.regist(user);
		mv.addObject("msg", "注册成功，请到邮箱激活！");
		mv.addObject("code", "success");
		mv.setViewName("msg");
		
		return mv;
	}
	
	//注销
	@RequestMapping(value="/logout")
	public String logout(HttpServletRequest request){
		if(request.getSession().getAttribute("user")!=null){
			request.getSession().invalidate();
		}
		return "redirect:/index";
	}
	
	//验证码
	@RequestMapping(value="/userVerifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse response){
		VerifyCode vc = new VerifyCode();
		BufferedImage image = vc.getImage();//获取一次性验证码图片
		// 该方法必须在getImage()方法之后来调用
//		System.out.println(vc.getText());//获取图片上的文本
		try {
			VerifyCode.output(image, response.getOutputStream());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}//把图片写到指定流中
		
		// 把文本保存到session中，为LoginServlet验证做准备
		request.getSession().setAttribute("vCode", vc.getText());
	}
	
	//激活功能
	@RequestMapping(value="/activation")
	public ModelAndView activation(String code){
		ModelAndView mv = new ModelAndView();
		try {
			userService.activation(code);
			mv.addObject("code", "success");
			mv.addObject("msg", "恭喜，激活成功！");
			mv.setViewName("msg");
		} catch (UserException e) {
			e.printStackTrace();
			mv.addObject("msg", e.getMessage());
			mv.addObject("code", "error");//通知msg.jsp显示x
			mv.setViewName("msg");
		}
		return mv;
	}
	/**
	 * 个人资料
	 * @return
	 */
	@RequestMapping(value="/user/person")
	public ModelAndView person(HttpServletRequest request){
		//用户登录后从session中获取用户信息
		User u = (User) request.getSession().getAttribute("user");
		if(u==null){
			return new ModelAndView("redirect:/login");
		}
		User user = userService.findUserById(u.getId());
		ModelAndView mv = new ModelAndView();
		mv.addObject("user", user);
		mv.setViewName("user/person");
		return mv;
	}
	@RequestMapping(value="/user/savePerson")
	public String savePerson(@RequestParam MultipartFile img, User user, HttpServletRequest request){
		try{
			if(img!=null && img.getSize()>0){
				//存储图片的物理路径
//				String img_path = request.getSession().getServletContext().getRealPath("/userimg");
				String img_path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/static/userimg";
				//图片的原始名称
				String oldName = img.getOriginalFilename();
				System.out.println(oldName);
				int index = oldName.lastIndexOf("\\");
				if(index != -1) {
					oldName = oldName.substring(index + 1);
				}
				
				//图片新名称
				String newName = CommonUtils.uuid()+oldName.substring(oldName.lastIndexOf("."));
				//新的图片
				File newImg = new File(URLDecoder.decode(img_path, "UTF-8"),newName);
				//将内存中的数据写入磁盘
				img.transferTo(newImg);
				String imgUrl = "/static/userimg/"+newName;
				user.setUserUrl(imgUrl);
			}
			System.out.println(user);
			userService.updatePerson(user);
			return "redirect:/user/person";
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value="/a/login")
	public ModelAndView adminLogin(){
		return new ModelAndView("admin/login");
	}
	@RequestMapping(value="/a/loginvalidate")
	public ModelAndView adminLoginValidate(Admin admin, HttpServletRequest request){
		if(admin.getUsername()==null || admin.getUsername().trim().equals("")){
			return new ModelAndView("admin/login","msg","用户名不能为空！");
		}
		if(admin.getPassword()==null || admin.getPassword().trim().equals("")){
			return new ModelAndView("admin/login","msg","密码不能为空！");
		}
		Admin a = userService.findAdminByUandP(admin);
		if(a==null){
			return new ModelAndView("admin/login","msg","用户名或密码错误！");
		}
		request.getSession().setAttribute("admin", a);
		return new ModelAndView("redirect:/admin/index");
	}
	
	@RequestMapping(value="/admin/userimg")
	public ModelAndView adminUserImg(){
		List<Img> imgList = userService.findAllImg();
		int num = imgList.size();
		ModelAndView mv = new ModelAndView();
		mv.addObject("imgList", imgList);
		mv.addObject("num", num);
		mv.setViewName("admin/userimg");
		return mv;
	}
	/**
	 * 上传用户随机头像图片
	 * @param img
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/admin/adduserimg")
	public String addUserImg(@RequestParam MultipartFile img, HttpServletRequest request){
		try{
			if(img!=null && img.getSize()>0){
				//存储图片的物理路径
				String img_path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/static/userimg";
				//图片的原始名称
				String oldName = img.getOriginalFilename();
				System.out.println(oldName);
				int index = oldName.lastIndexOf("\\");
				if(index != -1) {
					oldName = oldName.substring(index + 1);
				}
				
				//图片新名称
				String newName = CommonUtils.uuid()+oldName.substring(oldName.lastIndexOf("."));
				//新的图片
				File newImg = new File(URLDecoder.decode(img_path, "UTF-8"),newName);
				//将内存中的数据写入磁盘
				img.transferTo(newImg);
				String imgUrl = "/static/userimg/"+newName;
				userService.addUserImg(imgUrl);
				return "redirect:/admin/userimg";
			}
			return "redirect:/admin/userimg";
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@RequestMapping(value="/admin/deleteuserimg")
	public String deleteUserImg(Integer id){
		userService.deleteUserImg(id);
		return "redirect:/admin/userimg";
	}
}

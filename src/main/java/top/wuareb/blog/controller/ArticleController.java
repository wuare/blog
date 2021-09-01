package top.wuareb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import top.wuareb.blog.domain.vo.article.*;
import top.wuareb.blog.domain.vo.comment.Comment;
import top.wuareb.blog.domain.vo.comment.CommentCustom;
import top.wuareb.blog.domain.vo.comment.Reply;
import top.wuareb.blog.domain.vo.comment.ReplyCustom;
import top.wuareb.blog.domain.vo.user.User;
import top.wuareb.blog.service.ArticleService;
import top.wuareb.blog.service.CommentService;
import top.wuareb.blog.util.CommonUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class ArticleController {
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/user/write")
	public ModelAndView write(){
		List<TagCustom> tagList = articleService.findTag();
		ModelAndView mv = new ModelAndView();
		mv.addObject("tagList", tagList);
		mv.setViewName("user/write");
		return mv;
	}
	//点击喜欢功能
	@RequestMapping(value="/ajaxLikeNum")
	public String ajaxLikeNum(Integer id, HttpServletRequest request, HttpServletResponse response){
		try {
			Article article = articleService.findArticleById(id);
			if(article.getLikeNum()==null){
				article.setLikeNum(0);
			}
			
			//获取客户ip
			String addr = null;
			if (request.getHeader("x-forwarded-for") == null) { 
				addr = request.getRemoteAddr(); 
			}else{ 
				addr = request.getHeader("x-forwarded-for"); 
			}
			Address a = commentService.findAddrByAddr(addr);
			if(a==null){
				Address add = new Address();
				add.setAddr(addr);
				add.setArticleid(id);
				int addrid = articleService.saveAddr(add);
				add.setId(addrid);
				articleService.saveAddrAndAid(add);
				articleService.updateLikeNum(article);
				response.getWriter().print(article.getLikeNum());
				return null;
			}else{
				//根据文章id查找ip是否已存在，如果存在，则点过赞，否则保存ip到数据库中
				Address add1 = articleService.findAddrByAid(id);
				if(add1==null){
					Address add = new Address();
					add.setAddr(addr);
					add.setArticleid(id);
					add.setId(a.getId());
					articleService.saveAddrAndAid(add);
					articleService.updateLikeNum(article);
					response.getWriter().print(article.getLikeNum());
					return null;
				}else{
					return null;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//回复顶功能
	@RequestMapping(value="/ajaxReplyLike")
	public String ajaxReplyLike(Integer id, HttpServletRequest request, HttpServletResponse response){
		try {
			Reply reply = commentService.findReplyByRid(id);
			//获取客户端ip
			String addr = null;
			if (request.getHeader("x-forwarded-for") == null) { 
				addr = request.getRemoteAddr(); 
			}else{ 
				addr = request.getHeader("x-forwarded-for"); 
			}
			Address a = commentService.findAddrByAddr(addr);
			if(a==null){
				Address add = new Address();
				add.setAddr(addr);
				add.setReplyid(id);
				int addrid = commentService.saveAddr(add);
				add.setId(addrid);
				commentService.saveAddrAndAid(add);
				commentService.updateLikeNum(reply);
				response.getWriter().print(reply.getLikeNum());
				return null;
			}else{
				//根据回复id查找ip是否已存在，如果存在，则顶过，否则保存ip到数据库中
				Address add1 = commentService.findAddrByAid(id);
				if(add1==null){
					Address add = new Address();
					add.setAddr(addr);
					add.setReplyid(id);
					add.setId(a.getId());
					commentService.saveAddrAndAid(add);
					commentService.updateLikeNum(reply);
					response.getWriter().print(reply.getLikeNum());
					return null;
				}else{
					return null;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	//评论顶功能
	@RequestMapping(value="/ajaxCommentLike")
	public String ajaxCommentLike(Integer id, HttpServletRequest request, HttpServletResponse response){
		try {
			Comment comment = commentService.findCommentByCid(id);
			//获取客户端ip
			String addr = null;
			if (request.getHeader("x-forwarded-for") == null) { 
				addr = request.getRemoteAddr(); 
			}else{ 
				addr = request.getHeader("x-forwarded-for"); 
			}
			Address a = commentService.findAddrByAddr(addr);
			if(a==null){
				Address add = new Address();
				add.setAddr(addr);
				add.setCommentid(id);
				int addrid = commentService.saveAddr(add);
				add.setId(addrid);
				commentService.saveAddrAndCommentid(add);
				commentService.updateCommentLikeNum(comment);
				response.getWriter().print(comment.getLikeNum());
				return null;
			}else{
				//根据回复id查找ip是否已存在，如果存在，则顶过，否则保存ip到数据库中
				Address add1 = commentService.findAddrByCommentid(id);
				if(add1==null){
					Address add = new Address();
					add.setAddr(addr);
					add.setCommentid(id);
					add.setId(a.getId());
					commentService.saveAddrAndCommentid(add);
					commentService.updateCommentLikeNum(comment);
					response.getWriter().print(comment.getLikeNum());
					return null;
				}else{
					return null;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	//保存文章
	@RequestMapping(value="/user/writeSave")
	public ModelAndView writeSave(@RequestParam MultipartFile img, Article article, HttpServletRequest request){
		try{
			//上传图片
			if(img==null || img.getSize()==0){
				return new ModelAndView("user/write","msg","文章主页图片不能为空！");
			}
				//存储图片的物理路径
				String img_path = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/static/articleimg";
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
				String imgUrl = "/static/articleimg/"+newName;
				article.setImgUrl(imgUrl);
			
			
			if(article.getTitle()==null || article.getTitle().trim().equals("")){
				return new ModelAndView("user/write","msg","文章标题不能为空！");
			}
			if(article.getCid()==null){
				return new ModelAndView("user/write","msg","文章分类不能为空！");
			}
			if(article.getContent()==null || article.getContent().trim().equals("")){
				return new ModelAndView("user/write","msg","文章内容不能为空！");
			}
			User u = (User) request.getSession().getAttribute("user");
			if(u==null){
				article.setAuthor(null);
				article.setUid(null);
			}else{
				article.setAuthor(u.getUsername());
				article.setUid(u.getId());
			}
			article.setHotState(0);
			int aid = articleService.saveArticle(article);
			String tag = request.getParameter("tag").trim();
			if(tag!=null && !tag.equals("")){
				if(tag.contains(",")){
					String[] list = tag.split(",");
					Set<String> set = new HashSet<String>();
					for(int i = 0; i < list.length; i++){
						set.add(list[i]);
					}
					System.out.println(set +""+set.size());
					for(String s : set){
						Tag ta = new Tag();
						ta.setName(s.trim());
						Tag t = articleService.findTagByName(ta);
						if(t!=null){
							t.setAid(aid);
							articleService.addTagAndAid(t);
						}
					}
				}else{
					Tag ta = new Tag();
					ta.setName(tag);
					Tag t = articleService.findTagByName(ta);
					if(t!=null){
						t.setAid(aid);
						articleService.addTagAndAid(t);
					}
				}
			}

			return new ModelAndView("redirect:/user/index");
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	//根据id查找文章
	@RequestMapping(value="/content")
	public ModelAndView content(Integer id){
		Article article = articleService.findArticleById(id);
		//文章的标签
		List<Tag> list = articleService.findTagByAid(id);
		article.setTagList(list);
		//更新点击数量
		articleService.updateLookNum(article);
		//上一篇和下一篇
		Article lastArticle = articleService.findLastById(id);
		Article nextArticle = articleService.findNextById(id);
		List<Date> dateList = articleService.findDate();
		Set<String> setList = new LinkedHashSet<String>();
		for(Date each: dateList){
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
			String e = format.format(each);
			setList.add(e);
		}
		//猜你喜欢
		List<Article> hotList = articleService.findArticleByHot();
		
		PageBeanCustom pb = new PageBeanCustom();
		pb.setCateDate(setList);
		pb.setArticle(article);
		pb.setHotList(hotList);
		//友链
		List<Link> linkList = articleService.findLink();
		//标签
		List<TagCustom> tagList = articleService.findTag();
		for(TagCustom tag: tagList){
			int number = (int) (Math.random()*10);
			tag.setNum(number);
			int anum = articleService.findArticleNumByTid(tag.getId());
			tag.setAnum(anum);
		}
		
		/**
		 * 评论部分
		 */
		//根据文章id查找评论
		int commentNum = 0;
		List<CommentCustom> commentList = commentService.findByAid(id);
		for(CommentCustom comment: commentList){
				List<ReplyCustom> replyList = commentService.findReplyByCommentId(comment.getId());
				comment.setReplyList(replyList);
				commentNum += replyList.size();
		}
		commentNum = commentNum + commentList.size();
		ModelAndView mv = new ModelAndView();
		mv.addObject("commentNum", commentNum);
		mv.addObject("pb", pb);
		mv.addObject("cList", commentList);
		mv.addObject("linkList", linkList);
		mv.addObject("tagList", tagList);
		mv.addObject("last", lastArticle);
		mv.addObject("next", nextArticle);
		mv.setViewName("content");
		return mv;
	}
	@RequestMapping(value="/admin/modifyPre")
	public ModelAndView modifyPre(Integer id){
		Article article = articleService.findArticleById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("article", article);
		mv.setViewName("admin/modify");
		return mv;
	}
	@RequestMapping(value="/admin/modify")
	public String modifyPre(@RequestParam MultipartFile img, ArticleCustom article, HttpServletRequest request){
		try{
			if(img!=null && img.getSize()>0){
				//存储图片的物理路径
				String img_path = request.getSession().getServletContext().getRealPath("/articleimg");
				//图片的原始名称
				String oldName = img.getOriginalFilename();
				int index = oldName.lastIndexOf("\\");
				if(index != -1) {
					oldName = oldName.substring(index + 1);
				}
				
				//图片新名称
				String newName = CommonUtils.uuid()+oldName.substring(oldName.lastIndexOf("."));
				//新的图片
				File newImg = new File(img_path,newName);
				//将内存中的数据写入磁盘
				img.transferTo(newImg);
				String imgUrl = "/articleimg/"+newName;
				article.setImgUrl(imgUrl);
			}
			articleService.modify(article);
			return "redirect:/admin/index";
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 删除文章
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/admin/delete")
	public ModelAndView delete(Integer id){
		articleService.deleteAddr(id);
		articleService.deleteTag(id);
		articleService.deleteArticleById(id);
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "删除成功！");
		mv.addObject("code", "success");
		mv.setViewName("admin/msg");
		return mv;
	}
	
	@RequestMapping(value="/user/addTag")
	public String addTag(HttpServletRequest request){
		String name = request.getParameter("tagName");
		if(name==null || name.trim().equals("")){
			return "redirect:/user/write";
		}
		if(name.contains(",")){
			String[] list = name.split(",");
			for(int i = 0; i < list.length; i++){
				Tag tag = new Tag();
				tag.setName(list[i]);
				Tag t = articleService.findTagByName(tag);
				if(t!=null)
					continue;
				articleService.addTag(tag);
			}
			return "redirect:/user/write";
		}else{
			Tag tag = new Tag();
			tag.setName(name);
			Tag t = articleService.findTagByName(tag);
			if(t==null){
				articleService.addTag(tag);
				return "redirect:/user/write";
			}else{
				return "redirect:/user/write";
			}
		}
	}
	//嵌套
	/*
	private List<ReplyCustom> getReply(List<ReplyCustom> list){
		if(list!=null){
			for(ReplyCustom reply : list){
				List<ReplyCustom> replyList = commentService.findReplyByReplyId(reply.getId());
				reply.setReplyList(replyList);
				getReply(replyList);
			}
		}
		return list;
	}
	
	private void getReplyItem(List<ReplyCustom> list){
		if(list!=null){
			for(ReplyCustom reply : list){
				
				System.out.println(reply.getReplyList()+"///////////////");
				getReply(reply.getReplyList());
			}
		}
	}
	 */
	
}

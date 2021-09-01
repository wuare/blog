package top.wuareb.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.wuareb.blog.domain.vo.article.*;
import top.wuareb.blog.service.ArticleService;
import top.wuareb.blog.service.CommentService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
public class IndexController {
	
	@Resource
	private ArticleService articleService;
	@Resource
	private CommentService commentService;
	
	
	//主页，包含分页
	@RequestMapping(value="/index")
	public ModelAndView findAll(HttpServletRequest request, Integer cid, Integer tid){
		int pc = 1;
		String param = request.getParameter("pc");
		if(param != null && !param.trim().isEmpty()) {
			try {
				pc = Integer.parseInt(param);
			} catch(RuntimeException e) {}
		}
		String queryStr = request.getQueryString();
		String url = request.getRequestURL() + "?";
		if(queryStr!=null){
			url = request.getRequestURL() + "?" + queryStr + "&";
		}
		/*
		 * 如果url中存在pc参数，截取掉，如果不存在那就不用截取。
		 */
		int index = url.lastIndexOf("pc=");
		if(index != -1) {
			url = url.substring(0, index);
		}
		PageBeanCustom page = new PageBeanCustom();
		page.setPc(pc);
		page.setUrl(url);
		//按标签id分页查询文章
		if(tid!=null){
			page.setTid(tid);
			List<ArticleCustom> list = articleService.findByTagId(page);
			for(ArticleCustom article : list){
				int cNum = commentService.findCommentNum(article.getId());
				article.setcNum(cNum);
			}
			page.setBeanList(list);
		}
		if(tid==null){
			if(cid==null){
				//分页查询所有文章
				List<ArticleCustom> list = articleService.findAll(page);
				for(ArticleCustom article : list){
					int cNum = commentService.findCommentNum(article.getId());
					article.setcNum(cNum);
				}
				page.setBeanList(list);
			}else{
				//按分类id分页查询文章
				page.setCategoryId(cid);
				List<ArticleCustom> list = articleService.findByCategoryId(page);
				for(ArticleCustom article : list){
					int cNum = commentService.findCommentNum(article.getId());
					article.setcNum(cNum);
				}
				page.setBeanList(list);
			}
		}
		//热门文章，猜你喜欢
		List<Article> hotList = articleService.findArticleByHot();
		page.setHotList(hotList);
		/*
		 * 文章归档，查询日期
		 * 把所有日期查出来，按格式解析
		 * 放在set中去重复，转发到页面中
		 */
		List<Date> dateList = articleService.findDate();
		Set<String> setList = new LinkedHashSet<String>();
		for(Date each: dateList){
			SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月");
			String e = format.format(each);
			setList.add(e);
		}
		page.setCateDate(setList);
		
		
		//查找标签
		List<TagCustom> tagList = articleService.findTag();
		for(int i=0;i<tagList.size();i++){
			int random = (int) (Math.random()*10);
			tagList.get(i).setNum(random);
			int tagid = tagList.get(i).getId();
			int anum = articleService.findArticleNumByTid(tagid);
			tagList.get(i).setAnum(anum);
		}
		page.setTagList(tagList);
		
		//查找友情链接
		List<Link> linkList = articleService.findLink();
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("pb", page);
		mv.addObject("linkList", linkList);
		mv.setViewName("main");
		return mv;
	}
	//用户主页
	@RequestMapping(value="/user/index")
	public ModelAndView userIndex(Integer pc, String title){
		if(title==null || title.trim().equals("")){
			if(pc==null){
				pc = 1;
			}
			PageBeanCustom pb = new PageBeanCustom();
			pb.setPc(pc);
			int hotNum = 0;
			List<ArticleCustom> beanList = articleService.findAll(pb);
			for(ArticleCustom article : beanList){
				List<Tag> tagList = articleService.findTagByAid(article.getId());
				article.setTagList(tagList);
				if(article.getHotState()==1){
					hotNum++;
				}
			}
			pb.setBeanList(beanList);
			ModelAndView mv = new ModelAndView();
			mv.addObject("pb", pb);
			mv.addObject("hotNum", hotNum);
			mv.setViewName("user/all");
			return mv;
		}else{
			if(pc==null){
				pc = 1;
			}
			PageBeanCustom pb = new PageBeanCustom();
			pb.setPc(pc);
			pb.setTitle(title);
			int hotNum = 0;
			List<ArticleCustom> beanList = articleService.findArticleByTitle(pb);
			for(ArticleCustom article : beanList){
				List<Tag> tagList = articleService.findTagByAid(article.getId());
				article.setTagList(tagList);
				if(article.getHotState()==1){
					hotNum++;
				}
			}
			pb.setBeanList(beanList);
			ModelAndView mv = new ModelAndView();
			mv.addObject("pb", pb);
			mv.addObject("hotNum", hotNum);
			mv.setViewName("user/all");
			return mv;
		}
	}
	//admin主页
	@RequestMapping(value="/admin/index")
	public ModelAndView adminIndex(Integer pc, String title){
		if(title==null || title.trim().equals("")){
			if(pc==null){
				pc = 1;
			}
			PageBeanCustom pb = new PageBeanCustom();
			pb.setPc(pc);
			int hotNum = 0;
			List<ArticleCustom> beanList = articleService.findAll(pb);
			for(ArticleCustom article : beanList){
				List<Tag> tagList = articleService.findTagByAid(article.getId());
				article.setTagList(tagList);
				if(article.getHotState()==1){
					hotNum++;
				}
			}
			pb.setBeanList(beanList);
			ModelAndView mv = new ModelAndView();
			mv.addObject("pb", pb);
			mv.addObject("hotNum", hotNum);
			mv.setViewName("admin/all");
			return mv;
		}else{
			if(pc==null){
				pc = 1;
			}
			PageBeanCustom pb = new PageBeanCustom();
			pb.setPc(pc);
			pb.setTitle(title);
			int hotNum = 0;
			List<ArticleCustom> beanList = articleService.findArticleByTitle(pb);
			for(ArticleCustom article : beanList){
				List<Tag> tagList = articleService.findTagByAid(article.getId());
				article.setTagList(tagList);
				if(article.getHotState()==1){
					hotNum++;
				}
			}
			pb.setBeanList(beanList);
			ModelAndView mv = new ModelAndView();
			mv.addObject("pb", pb);
			mv.addObject("hotNum", hotNum);
			mv.setViewName("admin/all");
			return mv;
		}
	}
	/**
	 * 友链列表
	 * @return
	 */
	@RequestMapping(value="/admin/linklist")
	public ModelAndView findLink(){
		List<Link> linkList = articleService.findLink();
		int linkNum = linkList.size();
		ModelAndView mv = new ModelAndView();
		mv.addObject("linkList", linkList);
		mv.addObject("linkNum", linkNum);
		mv.setViewName("admin/link");
		return mv;
	}
	@RequestMapping(value="/admin/deletelink")
	public String deleteLink(Integer id){
		articleService.deleteLinkById(id);
		return "redirect:/admin/linklist";
	}
	/**
	 * 添加友链
	 * @param link
	 * @return
	 */
	@RequestMapping(value="/admin/addlink")
	public String addLink(Link link){
		articleService.addLink(link);
		return "redirect:/admin/linklist";
	}
	/**
	 * 猜你喜欢改变状态
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/admin/ajaxhot")
	public String ajaxHot(Integer id, HttpServletResponse response){
		try{
			articleService.ajaxHot(id);
			boolean b = true;
			response.getWriter().print(b);
			return null;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 猜你喜欢改变状态:取消推荐
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/admin/ajaxcancelhot")
	public String ajaxCancelHot(Integer id, HttpServletResponse response){
		try{
			articleService.ajaxCancelHot(id);
			boolean b = true;
			response.getWriter().print(b);
			return null;
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}

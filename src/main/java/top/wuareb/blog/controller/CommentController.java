package top.wuareb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.wuareb.blog.domain.vo.article.PageBeanCustom;
import top.wuareb.blog.domain.vo.comment.Comment;
import top.wuareb.blog.domain.vo.comment.CommentCustom;
import top.wuareb.blog.domain.vo.comment.Reply;
import top.wuareb.blog.domain.vo.comment.ReplyCustom;
import top.wuareb.blog.domain.vo.user.User;
import top.wuareb.blog.service.CommentService;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
public class CommentController {
	@Autowired
	private CommentService commentService;
	
	@RequestMapping(value="/user/comment")
	public ModelAndView saveComment(Comment comment, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		
		if(user!=null){
			comment.setUid(user.getId());
		}
		if(comment.getContent()==null || comment.getContent().trim().equals("")){
			return new ModelAndView("redirect:/content?id=" + comment.getAid());
		}
		String content = comment.getContent();
		content = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		comment.setContent(content);
		comment.setDate(new Date());
		comment.setLikeNum(0);
		comment.setWarnNum(0);
		commentService.saveComment(comment);
		return new ModelAndView("redirect:/content?id=" + comment.getAid());
	}
	
	@RequestMapping(value="/user/retocm")
	public ModelAndView replyToComment(Integer aid, Reply reply, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		
		if(user!=null){
			reply.setUid(user.getId());
		}
		if(reply.getContent()==null || reply.getContent().trim().equals("")){
			return new ModelAndView("redirect:/content?id=" + aid);
		}
		String content = reply.getContent();
		content = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		reply.setContent(content);
		reply.setDate(new Date());
		reply.setLikeNum(0);
		reply.setWarnNum(0);
		commentService.replyToComment(reply);
		return new ModelAndView("redirect:/content?id=" + aid);
	}
	//回复回复
	@RequestMapping(value="/user/retore")
	public ModelAndView replyToReply(Integer aid, Reply reply, HttpServletRequest request){
		User user = (User)request.getSession().getAttribute("user");
		
		if(user!=null){
			reply.setUid(user.getId());
		}
		if(reply.getContent()==null || reply.getContent().trim().equals("")){
			return new ModelAndView("redirect:/content?id=" + aid);
		}
		String content = reply.getContent();
		content = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		reply.setContent(content);
		reply.setDate(new Date());
		reply.setLikeNum(0);
		reply.setWarnNum(0);
		commentService.replyToReply(reply);
		return new ModelAndView("redirect:/content?id=" + aid);
	}
	/**
	 * 查找所有评论
	 * @return
	 */
	@RequestMapping(value="/user/commentlist")
	public ModelAndView commentList(Integer pc){
		if(pc==null){
			pc = 1;
		}
		PageBeanCustom pb = new PageBeanCustom();
		pb.setPc(pc);
		List<CommentCustom> commentList = commentService.findAllComment(pb);
		for(CommentCustom comment : commentList){
			List<ReplyCustom> replyList = commentService.findReplyByCommId(comment.getId());
			comment.setReplyList(replyList);
		}
		pb.setCommentList(commentList);
		ModelAndView mv = new ModelAndView();
		mv.addObject("pb", pb);
		mv.setViewName("user/comment");
		return mv;
	}
	/**
	 * 查找所有评论
	 * @return
	 */
	@RequestMapping(value="/admin/commentlist")
	public ModelAndView adminCommentList(Integer pc){
		if(pc==null){
			pc = 1;
		}
		PageBeanCustom pb = new PageBeanCustom();
		pb.setPc(pc);
		List<CommentCustom> commentList = commentService.findAllComment(pb);
		for(CommentCustom comment : commentList){
			List<ReplyCustom> replyList = commentService.findReplyByCommId(comment.getId());
			comment.setReplyList(replyList);
		}
		pb.setCommentList(commentList);
		ModelAndView mv = new ModelAndView();
		mv.addObject("pb", pb);
		mv.setViewName("admin/comment");
		return mv;
	}
	/**
	 * 删除评论功能
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/admin/deletecomment")
	public String adminDeleteComment(Integer id){
		commentService.deleteCommentById(id);
		return "redirect:/admin/commentlist";
	}
	@RequestMapping(value="/admin/deletereply")
	public String adminDeleteReply(Integer id){
		commentService.deleteReplyById(id);
		return "redirect:/admin/commentlist";
	}
	@RequestMapping(value="/admin/deletemreply")
	public String adminDeleteMsgReply(Integer id){
		commentService.deleteReplyById(id);
		return "redirect:/admin/messagelist";
	}
}

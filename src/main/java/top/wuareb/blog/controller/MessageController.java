package top.wuareb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.wuareb.blog.domain.vo.article.Address;
import top.wuareb.blog.domain.vo.article.PageBeanCustom;
import top.wuareb.blog.domain.vo.comment.Reply;
import top.wuareb.blog.domain.vo.comment.ReplyCustom;
import top.wuareb.blog.domain.vo.message.Message;
import top.wuareb.blog.domain.vo.message.MessageCustom;
import top.wuareb.blog.domain.vo.user.User;
import top.wuareb.blog.service.CommentService;
import top.wuareb.blog.service.MessageService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
public class MessageController {
	
	@Autowired
	private MessageService messageService;
	@Autowired
	private CommentService commentService;
	
	/**
	 * 查询所有留言
	 * @return
	 */
	@RequestMapping(value="/message")
	public ModelAndView message(){
		
		List<MessageCustom> listMessage = messageService.findAllMessage();

		ModelAndView mv = new ModelAndView();
		mv.addObject("listMessage", listMessage);
		mv.setViewName("message");
		return mv;
	}
	
	@RequestMapping(value="/user/writeMessage")
	public ModelAndView writeMessage(Message message, HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("user");
		if(u!=null){
			message.setUid(u.getId());
		}
		String content = message.getContent();
		content = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("操", "*");
		message.setContent(content);
		message.setDate(new Date());
		message.setWarnNum(0);
		message.setLikeNum(0);
		messageService.saveMessage(message);
		return new ModelAndView("redirect:/message");
	}
	//回复留言
	@RequestMapping(value="/user/msgretome")
	public ModelAndView MsgReToMe(Reply reply, HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("user");
		if(u!=null){
			reply.setUid(u.getId());
		}
		String content = reply.getContent();
		content = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		reply.setContent(content);
		reply.setDate(new Date());
		reply.setWarnNum(0);
		reply.setLikeNum(0);
		messageService.msgReToMe(reply);
		return new ModelAndView("redirect:/message");
	}
	
	//回复留言的回复
	@RequestMapping(value="/user/msgretore")
	public ModelAndView MsgReToRe(Reply reply, HttpServletRequest request){
		User u = (User) request.getSession().getAttribute("user");
		if(u!=null){
			reply.setUid(u.getId());
		}
		String content = reply.getContent();
		content = content.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		reply.setContent(content);
		reply.setDate(new Date());
		reply.setWarnNum(0);
		reply.setLikeNum(0);
		messageService.msgReToRe(reply);
		return new ModelAndView("redirect:/message");
	}
	
	@RequestMapping(value="/about")
	public ModelAndView about(){
		return new ModelAndView("about");
	}
	//留言顶功能
	@RequestMapping(value="/ajaxMessageLike")
	public String ajaxMessageLike(Integer id, HttpServletRequest request, HttpServletResponse response){
		try {
			Message message = messageService.findMessageByMid(id);
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
				add.setMessageid(id);
				int addrid = commentService.saveAddr(add);
				add.setId(addrid);
				messageService.saveAddrAndMessageid(add);
				messageService.updateMessageLikeNum(message);
				response.getWriter().print(message.getLikeNum());
				return null;
			}else{
				//根据回复id查找ip是否已存在，如果存在，则顶过，否则保存ip到数据库中
				Address add1 = messageService.findAddrByMessageid(id);
				if(add1==null){
					Address add = new Address();
					add.setAddr(addr);
					add.setMessageid(id);
					add.setId(a.getId());
					messageService.saveAddrAndMessageid(add);
					messageService.updateMessageLikeNum(message);
					response.getWriter().print(message.getLikeNum());
					return null;
				}else{
					return null;
				}
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 留言列表
	 * @param pc
	 * @return
	 */
	@RequestMapping(value="/admin/messagelist")
	public ModelAndView adminMessageList(Integer pc){
		if(pc==null){
			pc = 1;
		}
		PageBeanCustom pb = new PageBeanCustom();
		pb.setPc(pc);
		List<MessageCustom> messageList = messageService.findAllMessagePage(pb);
		for(MessageCustom message : messageList){
			List<ReplyCustom> replyList = messageService.findReplyByMId(message.getId());
			message.setReplyList(replyList);
		}
		pb.setMessageList(messageList);
		ModelAndView mv = new ModelAndView();
		mv.addObject("pb", pb);
		mv.setViewName("admin/message");
		return mv;
	}
	//删除留言
	@RequestMapping(value="/admin/deletemessage")
	public String adminDeleteMessage(Integer id){
		messageService.deleteMessageById(id);
		return "redirect:/admin/messageList";
	}
	
}

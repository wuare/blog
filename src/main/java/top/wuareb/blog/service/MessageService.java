package top.wuareb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wuareb.blog.domain.vo.article.Address;
import top.wuareb.blog.domain.vo.article.PageBeanCustom;
import top.wuareb.blog.domain.vo.article.PageConstants;
import top.wuareb.blog.domain.vo.comment.Reply;
import top.wuareb.blog.domain.vo.comment.ReplyCustom;
import top.wuareb.blog.domain.vo.message.Message;
import top.wuareb.blog.domain.vo.message.MessageCustom;
import top.wuareb.blog.mapper.MessageMapper;

import java.sql.SQLException;
import java.util.List;

@Service
public class MessageService {
	
	@Autowired
	private MessageMapper messageMapper;
	
	/**
	 * 查询所有留言
	 * @return
	 */
	public List<MessageCustom> findAllMessage() {
		try{
			List<MessageCustom> list = messageMapper.findAllMessage();
			for(MessageCustom m : list){
				List<ReplyCustom> listReply = messageMapper.findReplyByMid(m.getId());
				m.setReplyList(listReply);
				for(ReplyCustom reply : listReply){
					String pname = messageMapper.findPname(reply.getPid());
					reply.setPname(pname);
				}
			}
			return list;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 保存留言
	 * @param message
	 */
	public void saveMessage(Message message) {
		try{
			messageMapper.saveMessage(message);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 保存回复留言的回复
	 * @param reply
	 */
	public void msgReToMe(Reply reply) {
		try{
			messageMapper.msgReToMe(reply);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void msgReToRe(Reply reply) {
		try{
			messageMapper.msgReToRe(reply);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找留言
	 * @param id
	 * @return
	 */
	public Message findMessageByMid(Integer id) {
		try{
			return messageMapper.findMessageByMid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void saveAddrAndMessageid(Address add) {
		try{
			messageMapper.saveAddrAndMessageid(add);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void updateMessageLikeNum(Message message) {
		try{
			message.setLikeNum(message.getLikeNum()+1);
			messageMapper.updateMessageLikeNum(message);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Address findAddrByMessageid(Integer id) {
		try{
			return messageMapper.findAddrByMessageid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 分页查询所有留言
	 * @param pb
	 * @return
	 */
	public List<MessageCustom> findAllMessagePage(PageBeanCustom pb) {
		try{
			int tr = messageMapper.findMessageCount();
			pb.setTr(tr);
			pb.setPs(PageConstants.BLOG_PAGE_SIZE);
			pb.setStartPage((pb.getPc()-1)*pb.getPs());
			return messageMapper.findAllMessagePage(pb);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据messageid查找所有回复
	 * @param id
	 * @return
	 */
	public List<ReplyCustom> findReplyByMId(Integer id) {
		try{
			List<ReplyCustom> listReply = messageMapper.findReplyByMsgId(id);
			for(ReplyCustom reply : listReply){
				String pname = messageMapper.findPname(reply.getPid());
				reply.setPname(pname);
			}
			return listReply;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//删除留言
	public void deleteMessageById(Integer id) {
		try{
			messageMapper.deleteMessageById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

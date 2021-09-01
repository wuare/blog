package top.wuareb.blog.mapper;

import org.springframework.stereotype.Repository;
import top.wuareb.blog.domain.vo.article.Address;
import top.wuareb.blog.domain.vo.article.PageBeanCustom;
import top.wuareb.blog.domain.vo.comment.Reply;
import top.wuareb.blog.domain.vo.comment.ReplyCustom;
import top.wuareb.blog.domain.vo.message.Message;
import top.wuareb.blog.domain.vo.message.MessageCustom;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface MessageMapper {
	//查询所有留言
	public List<MessageCustom> findAllMessage() throws SQLException;
	//查询留言的所有回复
	public List<ReplyCustom> findReplyByMid(Integer id) throws SQLException;
	public String findPname(Integer pid) throws SQLException;
	//保存留言
	public void saveMessage(Message message) throws SQLException;
	//回复留言
	public void msgReToMe(Reply reply) throws SQLException;
	public void msgReToRe(Reply reply) throws SQLException;
	//留言顶功能
	public Message findMessageByMid(Integer id) throws SQLException;
	public void saveAddrAndMessageid(Address add) throws SQLException;
	public void updateMessageLikeNum(Message message) throws SQLException;
	public Address findAddrByMessageid(Integer id) throws SQLException;
	//留言数量
	public int findMessageCount() throws SQLException;
	public List<MessageCustom> findAllMessagePage(PageBeanCustom pb) throws SQLException;
	public List<ReplyCustom> findReplyByMsgId(Integer id) throws SQLException;
	//删除留言
	public void deleteMessageById(Integer id) throws SQLException;
	
}

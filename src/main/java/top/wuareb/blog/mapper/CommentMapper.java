package top.wuareb.blog.mapper;

import org.springframework.stereotype.Repository;
import top.wuareb.blog.domain.vo.article.Address;
import top.wuareb.blog.domain.vo.article.PageBeanCustom;
import top.wuareb.blog.domain.vo.comment.*;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface CommentMapper {
	//根据文章id查找评论
	public List<CommentCustom> findByAid(Integer id) throws SQLException;
	//根据评论id查找所有属于此评论的回复
	public List<ReplyCustom> findReplyByCommentId(Integer id) throws SQLException;
	//根据回复id查找所有属于此回复的回复
	public List<ReplyCustom> findReplyByReplyId(Integer id) throws SQLException;
	public String findPname(Integer pid) throws SQLException;
	//发表评论
	public void saveComment(Comment comment) throws SQLException;
	//回复评论
	public void replyToComment(Reply reply) throws SQLException;
	//回复回复
	public void replyToReply(Reply reply) throws SQLException;
	//评论数量
	public List<Integer> findCommentNum(Integer id) throws SQLException;
	public List<Integer> findReplyNum(Integer id) throws SQLException;
	//所有评论
	public List<CommentCustom> findAllComment(PageBeanCustom pb) throws SQLException;
	public int findCommentCount() throws SQLException;
	public List<ReplyCustom> findReplyByCommId(Integer id) throws SQLException;
	//根据id查找回复
	public Reply findReplyByRid(Integer id) throws SQLException;
	//根据回复id查找ip是否已存在
	public Address findAddrByAid(Integer id) throws SQLException;
	//保存ip
	public void saveAddr(Address add) throws SQLException;
	public int lastId() throws SQLException;
	public void saveAddrAndAid(Address add) throws SQLException;
	public void updateLikeNum(Reply reply) throws SQLException;
	public Address findAddrByAddr(String addr) throws SQLException;
	//根据id查找评论
	public Comment findCommentByCid(Integer id) throws SQLException;
	//保存评论和ip的关联
	public void saveAddrAndCommentid(Address add) throws SQLException;
	public Address findAddrByCommentid(Integer id) throws SQLException;
	public void updateCommentLikeNum(Comment comment) throws SQLException;
	//根据id删除评论
	public void deleteCommentById(Integer id) throws SQLException;
	//根据id删除评论
	public void deleteReplyById(Integer id) throws SQLException;
	
}

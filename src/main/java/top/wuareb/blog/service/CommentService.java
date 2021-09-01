package top.wuareb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wuareb.blog.domain.vo.article.Address;
import top.wuareb.blog.domain.vo.article.PageBeanCustom;
import top.wuareb.blog.domain.vo.article.PageConstants;
import top.wuareb.blog.domain.vo.comment.Comment;
import top.wuareb.blog.domain.vo.comment.CommentCustom;
import top.wuareb.blog.domain.vo.comment.Reply;
import top.wuareb.blog.domain.vo.comment.ReplyCustom;
import top.wuareb.blog.mapper.CommentMapper;

import java.sql.SQLException;
import java.util.List;

@Service
public class CommentService {
	
	@Autowired
	private CommentMapper commentMapper;
	/**
	 * 根据文章id查找对应的评论
	 * @return
	 */
	public List<CommentCustom> findByAid(Integer id) {
		try{
			return commentMapper.findByAid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据评论id查找所有属于此评论的回复
	 * @param id
	 * @return
	 */
	public List<ReplyCustom> findReplyByCommentId(Integer id) {
		try{
			List<ReplyCustom> replyList =  commentMapper.findReplyByCommentId(id);
			for(ReplyCustom reply : replyList){
				String pname = commentMapper.findPname(reply.getPid());
				reply.setPname(pname);
			}
			return replyList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据回复id查找所有属于此回复的回复
	 * @param id
	 * @return
	 */
	public List<ReplyCustom> findReplyByReplyId(Integer id) {
		try{
			return commentMapper.findReplyByReplyId(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 发表评论
	 * @param comment
	 */
	public void saveComment(Comment comment) {
		try{
			commentMapper.saveComment(comment);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 回复评论内容保存
	 * @param reply
	 */
	public void replyToComment(Reply reply) {
		try{
			commentMapper.replyToComment(reply);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//回复回复
	public void replyToReply(Reply reply) {
		try{
			commentMapper.replyToReply(reply);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//评论数量
	public int findCommentNum(Integer id) {
		try{
			int cNum = 0;
			//评论的数量
			List<Integer> listC = commentMapper.findCommentNum(id);
			for(Integer i : listC){
				//评论回复的数量
				List<Integer> listR = commentMapper.findReplyNum(i);
				cNum += listR.size();
			}
			cNum = cNum + listC.size();
			return cNum;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<CommentCustom> findAllComment(PageBeanCustom pb) {
		try{
			int tr = commentMapper.findCommentCount();
			pb.setTr(tr);
			pb.setPs(PageConstants.BLOG_PAGE_SIZE);
			pb.setStartPage((pb.getPc()-1)*pb.getPs());
			return commentMapper.findAllComment(pb);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//根据评论id查找所有回复
	public List<ReplyCustom> findReplyByCommId(Integer id) {
		try{
			List<ReplyCustom> replyList =  commentMapper.findReplyByCommId(id);
			for(ReplyCustom reply : replyList){
				String pname = commentMapper.findPname(reply.getPid());
				reply.setPname(pname);
			}
			return replyList;
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//根据id查找回复
	public Reply findReplyByRid(Integer id) {
		try{
			return commentMapper.findReplyByRid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//根据回复id查找ip是否已存在
	public Address findAddrByAid(Integer id) {
		try{
			return commentMapper.findAddrByAid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//保存ip
	public int saveAddr(Address add) {
		try{
			commentMapper.saveAddr(add);
			return commentMapper.lastId();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//保存关联信息
	public void saveAddrAndAid(Address add) {
		try{
			commentMapper.saveAddrAndAid(add);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//更新顶数量
	public void updateLikeNum(Reply reply) {
		try{
			reply.setLikeNum(reply.getLikeNum()+1);
			commentMapper.updateLikeNum(reply);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//查找ip
	public Address findAddrByAddr(String addr) {
		try{
			return commentMapper.findAddrByAddr(addr);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据id查找评论
	 * @param id
	 * @return
	 */
	public Comment findCommentByCid(Integer id) {
		try{
			return commentMapper.findCommentByCid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//保存评论和ip的关联
	public void saveAddrAndCommentid(Address add) {
		try{
			commentMapper.saveAddrAndCommentid(add);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public Address findAddrByCommentid(Integer id) {
		try{
			return commentMapper.findAddrByCommentid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void updateCommentLikeNum(Comment comment) {
		try{
			comment.setLikeNum(comment.getLikeNum()+1);
			commentMapper.updateCommentLikeNum(comment);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据id删除评论
	 * @param id
	 */
	public void deleteCommentById(Integer id) {
		try{
			commentMapper.deleteCommentById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据id删除回复
	 * @param id
	 */
	public void deleteReplyById(Integer id) {
		try{
			commentMapper.deleteReplyById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
}

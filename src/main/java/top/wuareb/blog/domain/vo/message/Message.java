package top.wuareb.blog.domain.vo.message;

import top.wuareb.blog.domain.vo.comment.ReplyCustom;

import java.util.Date;
import java.util.List;

public class Message {
	private Integer id;
	private String content;
	private Integer uid;
	private Integer warnNum;
	private Integer likeNum;
	private Date date;
	private List<ReplyCustom> replyList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	public Integer getWarnNum() {
		return warnNum;
	}
	public void setWarnNum(Integer warnNum) {
		this.warnNum = warnNum;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	public List<ReplyCustom> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<ReplyCustom> replyList) {
		this.replyList = replyList;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Message [content=" + content + ", date=" + date + ", id=" + id
				+ ", likeNum=" + likeNum + ", replyList=" + replyList
				+ ", uid=" + uid + ", warnNum=" + warnNum + "]";
	}
	
}

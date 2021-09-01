package top.wuareb.blog.domain.vo.comment;

import java.util.Date;

public class Comment {
	private Integer id;
	private String content;
	private Date date;
	private Integer aid;
	private Integer warnNum;
	private Integer likeNum;
	private Integer uid;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
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
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	@Override
	public String toString() {
		return "Comment [aid=" + aid + ", content=" + content + ", date="
				+ date + ", id=" + id + ", likeNum=" + likeNum + ", uid=" + uid
				+ ", warnNum=" + warnNum + "]";
	}
	
}
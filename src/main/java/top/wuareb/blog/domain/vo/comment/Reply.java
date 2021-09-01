package top.wuareb.blog.domain.vo.comment;

import java.util.Date;

public class Reply {
	private Integer id;
	private String content;
	private Date date;
	private Integer warnNum;
	private Integer likeNum;
	private Integer uid;//user的id
	private Integer cid;//comment的id
	private Integer pid;//reply的父id
	private Integer mid;//message的id
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
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getMid() {
		return mid;
	}
	public void setMid(Integer mid) {
		this.mid = mid;
	}
	@Override
	public String toString() {
		return "Reply [cid=" + cid + ", content=" + content + ", date=" + date
				+ ", id=" + id + ", likeNum=" + likeNum + ", mid=" + mid
				+ ", pid=" + pid + ", uid=" + uid + ", warnNum=" + warnNum
				+ "]";
	}
	
}

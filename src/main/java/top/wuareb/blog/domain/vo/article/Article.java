package top.wuareb.blog.domain.vo.article;

import java.util.Date;
import java.util.List;

public class Article {
	private Integer id;
	private String title;
	private String author;
	private String content;
	private Date date;
	private Integer lookNum;
	private Integer likeNum;
	private String imgUrl;
	private Integer hotState;
	private Integer cid;
	private Integer uid;
	private String summary;
	private List<Tag> tagList;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	public Integer getLookNum() {
		return lookNum;
	}
	public void setLookNum(Integer lookNum) {
		this.lookNum = lookNum;
	}
	public Integer getLikeNum() {
		return likeNum;
	}
	public void setLikeNum(Integer likeNum) {
		this.likeNum = likeNum;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Integer getHotState() {
		return hotState;
	}
	public void setHotState(Integer hotState) {
		this.hotState = hotState;
	}
	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public Integer getUid() {
		return uid;
	}
	public void setUid(Integer uid) {
		this.uid = uid;
	}
	
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	
	public List<Tag> getTagList() {
		return tagList;
	}
	public void setTagList(List<Tag> tagList) {
		this.tagList = tagList;
	}
	
}

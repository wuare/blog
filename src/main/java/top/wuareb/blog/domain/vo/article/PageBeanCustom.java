package top.wuareb.blog.domain.vo.article;


import top.wuareb.blog.domain.vo.comment.CommentCustom;
import top.wuareb.blog.domain.vo.message.MessageCustom;

import java.util.List;
import java.util.Set;

public class PageBeanCustom extends PageBean {
	private Integer categoryId;
	private Article article;
	private Set<String> cateDate;
	private List<TagCustom> tagList;
	private String title;
	private List<CommentCustom> commentList;
	private Integer tid;//标签id
	private List<MessageCustom> messageList;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public Set<String> getCateDate() {
		return cateDate;
	}
	public void setCateDate(Set<String> cateDate) {
		this.cateDate = cateDate;
	}
	public List<TagCustom> getTagList() {
		return tagList;
	}
	public void setTagList(List<TagCustom> tagList) {
		this.tagList = tagList;
	}
	public List<CommentCustom> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<CommentCustom> commentList) {
		this.commentList = commentList;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public List<MessageCustom> getMessageList() {
		return messageList;
	}
	public void setMessageList(List<MessageCustom> messageList) {
		this.messageList = messageList;
	}
	
	
}

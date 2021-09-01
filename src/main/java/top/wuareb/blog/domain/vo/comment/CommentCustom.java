package top.wuareb.blog.domain.vo.comment;

import java.util.List;

public class CommentCustom extends Comment {
	@Override
	public String toString() {
		return "CommentCustom [replyList=" + replyList + ", userUrl=" + userUrl
				+ ", username=" + username + "]";
	}
	private String username;
	private String userUrl;
	private String title;
	private List<ReplyCustom> replyList;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserUrl() {
		return userUrl;
	}
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
	public List<ReplyCustom> getReplyList() {
		return replyList;
	}
	public void setReplyList(List<ReplyCustom> replyList) {
		this.replyList = replyList;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
}

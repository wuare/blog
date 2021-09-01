package top.wuareb.blog.domain.vo.comment;

import java.util.List;

public class ReplyCustom extends Reply {
	@Override
	public String toString() {
		return "ReplyCustom [replyList=" + replyList + ", userUrl=" + userUrl
				+ ", username=" + username + "]";
	}
	private String username;
	private String userUrl;
	private String pname;
	
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
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
	
}

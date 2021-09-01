package top.wuareb.blog.domain.vo.message;

public class MessageCustom extends Message {
	private String username;
	private String userUrl;
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
	
}

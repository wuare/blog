package top.wuareb.blog.domain.vo.user;

public class User {
	private Integer id;
	private String username;
	private String password;
	private String repassword;
	private String email;
	private int state;
	private String code;
	private String userUrl;
	private String firstname;//名字
	private String lastname;//姓氏
	private String nickname;//昵称
	private String pubname;//公开显示名字
	private String site;//站点
	private String descrip;//个人说明
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getUserUrl() {
		return userUrl;
	}
	public void setUserUrl(String userUrl) {
		this.userUrl = userUrl;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getPubname() {
		return pubname;
	}
	public void setPubname(String pubname) {
		this.pubname = pubname;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getDescrip() {
		return descrip;
	}
	public void setDescrip(String descrip) {
		this.descrip = descrip;
	}
	@Override
	public String toString() {
		return "User [code=" + code + ", descrip=" + descrip + ", email="
				+ email + ", firstname=" + firstname + ", id=" + id
				+ ", lastname=" + lastname + ", nickname=" + nickname
				+ ", password=" + password + ", pubname=" + pubname
				+ ", repassword=" + repassword + ", site=" + site + ", state="
				+ state + ", userUrl=" + userUrl + ", username=" + username
				+ "]";
	}
	
}

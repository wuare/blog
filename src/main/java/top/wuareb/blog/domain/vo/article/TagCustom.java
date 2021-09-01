package top.wuareb.blog.domain.vo.article;


public class TagCustom extends Tag {
	private Integer num;
	private Integer anum;//文章数量
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Integer getAnum() {
		return anum;
	}

	public void setAnum(Integer anum) {
		this.anum = anum;
	}
	
}

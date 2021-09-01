package top.wuareb.blog.domain.vo.article;


public class ArticleCustom extends Article {
	private String cateName;//分类名称
	private Integer cNum;//评论数量
	private int tid;//标签id
	private String tname;//标签名称
	
	public Integer getcNum() {
		return cNum;
	}

	public void setcNum(Integer cNum) {
		this.cNum = cNum;
	}

	public String getCateName() {
		return cateName;
	}

	public void setCateName(String cateName) {
		this.cateName = cateName;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	
}

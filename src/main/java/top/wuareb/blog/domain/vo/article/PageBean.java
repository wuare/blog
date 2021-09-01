package top.wuareb.blog.domain.vo.article;

import java.util.List;

/**
 * 分页bean，他会在各层之间传递
 * @author Administrator
 *
 * @param <T>
 */
public class PageBean {
	private int pc;//当前页码
	private int tr;//总记录数
	private int ps;//每页记录数
	private int tp;//总页数
	private int startPage;
	private String url;
	private List<ArticleCustom> beanList;//
	private List<Article> hotList;
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(int tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}

	public List<ArticleCustom> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<ArticleCustom> beanList) {
		this.beanList = beanList;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}
	
	public List<Article> getHotList() {
		return hotList;
	}
	public void setHotList(List<Article> hotList) {
		this.hotList = hotList;
	}
	
	public void setTp(int tp) {
		this.tp = tp;
	}
	//计算总页数
	public int getTp(){
		int tp = tr/ps;
		return tr % ps == 0 ? tp : tp + 1;
	}
}

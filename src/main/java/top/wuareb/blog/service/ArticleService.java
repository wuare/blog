package top.wuareb.blog.service;

import org.springframework.stereotype.Service;
import top.wuareb.blog.domain.vo.article.*;
import top.wuareb.blog.mapper.ArticleMapper;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class ArticleService {
	
	@Resource
	private ArticleMapper articleMapper;
	
	/**
	 * 保存文章
	 */
	public int saveArticle(Article article) {
		try{
			article.setDate(new Date());
			article.setLikeNum(0);
			article.setLookNum(0);
			articleMapper.saveArticle(article);
			return articleMapper.articleLastId();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据id查找文章
	 */
	public Article findArticleById(Integer id) {
		try{
			return articleMapper.findArticleById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找所有文章
	 */
	public List<ArticleCustom> findAll(PageBeanCustom page) {
		try{
			int tr = articleMapper.findArticleCount();
			page.setTr(tr);
			page.setPs(PageConstants.BLOG_PAGE_SIZE);
			page.setStartPage((page.getPc()-1)*page.getPs());
			return articleMapper.findAll(page);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 猜你喜欢，热门文章查找
	 * @return
	 */
	public List<Article> findArticleByHot() {
		try{
			return articleMapper.findArticleByHot();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找文章日期，文章归档
	 * @return
	 */
	public List<Date> findDate() {
		try{
			return articleMapper.findDate();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//查找所有标签
	public List<TagCustom> findTag() {
		try{
			return articleMapper.findTag();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 按分类id查
	 * @param page
	 * @return
	 */
	public List<ArticleCustom> findByCategoryId(PageBeanCustom page) {
		try{
			int tr = articleMapper.findCountByCid(page);
			page.setTr(tr);
			page.setPs(PageConstants.BLOG_PAGE_SIZE);
			page.setStartPage((page.getPc()-1)*page.getPs());
			return articleMapper.findByCategoryId(page);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 查找状态为1友情链接
	 * @return
	 */
	public List<Link> findLink() {
		try{
			return articleMapper.findLink();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据文章标题查找相关文章
	 * @param pb
	 * @return
	 */
	public List<ArticleCustom> findArticleByTitle(PageBeanCustom pb) {
		try{
			int tr = articleMapper.findCountByTitle(pb.getTitle());
			pb.setTr(tr);
			pb.setPs(PageConstants.BLOG_PAGE_SIZE);
			pb.setStartPage((pb.getPc()-1)*pb.getPs());
			return articleMapper.findArticleByTitle(pb);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据id修改文章
	 * @param article
	 */
	public void modify(ArticleCustom article) {
		try{
			articleMapper.modify(article);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 删除文章
	 * @param id
	 */
	public void deleteArticleById(Integer id) {
		try{
			articleMapper.deleteArticleById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//上一篇
	public Article findLastById(Integer id) {
		try{
			return articleMapper.findLastById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//下一篇
	public Article findNextById(Integer id) {
		try{
			return articleMapper.findNextById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//更新点击数量
	public void updateLookNum(Article article) {
		try{
			if(article.getLookNum()==null){
				article.setLookNum(0);
			}
			article.setLookNum(article.getLookNum()+1);
			articleMapper.updateLookNum(article);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//更新喜欢数量
	public void updateLikeNum(Article article) {
		try{
			article.setLikeNum(article.getLikeNum()+1);
			articleMapper.updateLikeNum(article);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Address findAddrByAid(Integer id) {
		try{
			return articleMapper.findAddrByAid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//保存ip
	public int saveAddr(Address add) {
		try{
			articleMapper.saveAddr(add);
			return articleMapper.lastId();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void saveAddrAndAid(Address add) {
		try{
			articleMapper.saveAddrAndAid(add);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据标签id查找文章
	 * @param page
	 * @return
	 */
	public List<ArticleCustom> findByTagId(PageBeanCustom page) {
		try{
			int tr = articleMapper.findCountByTid(page.getTid());
			page.setTr(tr);
			page.setPs(PageConstants.BLOG_PAGE_SIZE);
			page.setStartPage((page.getPc()-1)*page.getPs());
			return articleMapper.findArticleByTid(page);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	
	}
	/**
	 * 查找文章的标签
	 * @param id
	 * @return
	 */
	public List<Tag> findTagByAid(Integer id) {
		try{
			return articleMapper.findTagByAid(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//添加标签
	public void addTag(Tag tag) {
		try{
			articleMapper.addTag(tag);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//根据tagName查询tag
	public Tag findTagByName(Tag tag) {
		try{
			return articleMapper.findTagByName(tag);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//为文章添加标签
	public void addTagAndAid(Tag t) {
		try{
			articleMapper.addTagAndAid(t);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//根据标签id查找文章数量
	public int findArticleNumByTid(int tagid) {
		try{
			return articleMapper.findArticleNumByTid(tagid);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//删除文章点赞ip
	public void deleteAddr(Integer id) {
		try{
			articleMapper.deleteAddr(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//删除文章关联的标签
	public void deleteTag(Integer id) {
		try{
			articleMapper.deleteTag(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//删除友链
	public void deleteLinkById(Integer id) {
		try{
			articleMapper.deleteLinkById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//添加友链
	public void addLink(Link link) {
		try{
			link.setState(1);
			articleMapper.addLink(link);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//猜你喜欢推荐
	public void ajaxHot(Integer id) {
		try{
			articleMapper.ajaxHot(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void ajaxCancelHot(Integer id) {
		try{
			articleMapper.ajaxCancelHot(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}

package top.wuareb.blog.mapper;


import org.springframework.stereotype.Repository;
import top.wuareb.blog.domain.vo.article.*;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public interface ArticleMapper {
	//保存文章
	public void saveArticle(Article article) throws SQLException;
	//根据id查找文章
	public Article findArticleById(Integer id) throws SQLException;
	//查找文章总数
	public int findArticleCount() throws SQLException;
	//查找所有文章
	public List<ArticleCustom> findAll(PageBeanCustom page) throws SQLException;
	//热门文章
	public List<Article> findArticleByHot() throws SQLException;
	//按日期查找
	public List<Date> findDate() throws SQLException;
	//查询所有标签
	public List<TagCustom> findTag() throws SQLException;
	//按分类id查询
	public List<ArticleCustom> findByCategoryId(PageBeanCustom page) throws SQLException;
	//按分类查找文章总数
	public int findCountByCid(PageBeanCustom page) throws SQLException;
	//按状态查找友情链接
	public List<Link> findLink() throws SQLException;
	//根据文章标题查找文章
	public List<ArticleCustom> findArticleByTitle(PageBeanCustom pb) throws SQLException;
	public int findCountByTitle(String title) throws SQLException;
	//修改文章
	public void modify(ArticleCustom article) throws SQLException;
	//删除文章
	public void deleteArticleById(Integer id) throws SQLException;
	//上一篇和下一篇
	public Article findLastById(Integer id) throws SQLException;
	public Article findNextById(Integer id) throws SQLException;
	//更新点击数量
	public void updateLookNum(Article article) throws SQLException;
	//更新喜欢数量
	public void updateLikeNum(Article article) throws SQLException;
	//根据文章id查找ip
	public Address findAddrByAid(Integer id) throws SQLException;
	public void saveAddr(Address add) throws SQLException;
	public void saveAddrAndAid(Address add) throws SQLException;
	public int lastId() throws SQLException;
	//根据标签id查找文章
	public int findCountByTid(Integer tid) throws SQLException;
	public List<ArticleCustom> findArticleByTid(PageBeanCustom page) throws SQLException;
	//查找文章的标签
	public List<Tag> findTagByAid(Integer id) throws SQLException;
	public void addTag(Tag tag) throws SQLException;
	public Tag findTagByName(Tag tag) throws SQLException;
	//为文章添加标签
	public void addTagAndAid(Tag t) throws SQLException;
	public int articleLastId() throws SQLException;
	//根据标签id查找文章数量
	public int findArticleNumByTid(int tagid) throws SQLException;
	public void deleteAddr(Integer id) throws SQLException;
	public void deleteTag(Integer id) throws SQLException;
	//删除友链
	public void deleteLinkById(Integer id) throws SQLException;
	//添加友链
	public void addLink(Link link) throws SQLException;
	//猜你喜欢推荐
	public void ajaxHot(Integer id) throws SQLException;
	public void ajaxCancelHot(Integer id) throws SQLException;
	

}

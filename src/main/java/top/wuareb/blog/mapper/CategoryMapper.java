package top.wuareb.blog.mapper;

import org.springframework.stereotype.Repository;
import top.wuareb.blog.domain.vo.category.Category;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface CategoryMapper {
	//查询所有分类
	public List<Category> findAll() throws SQLException;
	//删除标签
	public void deleteTagById(Integer id) throws SQLException;
	
}

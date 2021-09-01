package top.wuareb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.wuareb.blog.domain.vo.category.Category;
import top.wuareb.blog.mapper.CategoryMapper;

import java.sql.SQLException;
import java.util.List;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryMapper categoryMapper;
	/**
	 * 查询所有分类
	 * @return
	 */
	public List<Category> findAll() {
		try{
			return categoryMapper.findAll();
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//删除标签
	public void deleteTagById(Integer id) {
		try{
			categoryMapper.deleteTagById(id);
		}catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
}

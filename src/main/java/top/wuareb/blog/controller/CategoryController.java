package top.wuareb.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import top.wuareb.blog.domain.vo.article.TagCustom;
import top.wuareb.blog.domain.vo.category.Category;
import top.wuareb.blog.service.ArticleService;
import top.wuareb.blog.service.CategoryService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ArticleService articleService;
	
	//写文章页面异步加载分类
	@RequestMapping(value="/ajaxCategory")
	public String ajaxCategory(HttpServletResponse response){
		response.setContentType("text/html;charset=utf-8");
		List<Category> cList = categoryService.findAll();
		String json = toJson(cList);
		try {
			response.getWriter().print(json);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return null;
	}
	
	// {"cid":"fdsafdsa", "cname":"fdsafdas"}
	private String toJson(Category category) {
		StringBuilder sb = new StringBuilder("{");
		sb.append("\"id\"").append(":").append("\"").append(category.getId()).append("\"");
		sb.append(",");
		sb.append("\"name\"").append(":").append("\"").append(category.getName()).append("\"");
		sb.append("}");
		return sb.toString();
	}
	
	// [{"cid":"fdsafdsa", "cname":"fdsafdas"}, {"cid":"fdsafdsa", "cname":"fdsafdas"}]
	private String toJson(List<Category> categoryList) {
		StringBuilder sb = new StringBuilder("[");
		for(int i = 0; i < categoryList.size(); i++) {
			sb.append(toJson(categoryList.get(i)));
			if(i < categoryList.size() - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}
	/**
	 * 所有标签
	 * @return
	 */
	@RequestMapping(value="/admin/tag")
	public ModelAndView findAllTag(){
		List<TagCustom> tagList = articleService.findTag();
		for(int i=0;i<tagList.size();i++){
			int tagid = tagList.get(i).getId();
			int anum = articleService.findArticleNumByTid(tagid);
			tagList.get(i).setAnum(anum);
		}
		int num = tagList.size();
		ModelAndView mv = new ModelAndView();
		mv.addObject("tagList", tagList);
		mv.addObject("num", num);
		mv.setViewName("admin/tag");
		return mv;
	}
	/**
	 * 根据id删除标签
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/admin/deletetag")
	public String deleteTagById(Integer id){
		categoryService.deleteTagById(id);
		return "redirect:/admin/tag";
	}
}

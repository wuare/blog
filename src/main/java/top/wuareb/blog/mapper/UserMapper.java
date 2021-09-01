package top.wuareb.blog.mapper;

import org.springframework.stereotype.Repository;
import top.wuareb.blog.domain.vo.user.*;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserMapper {
	//根据用户名密码查找用户
	public User findByUserAndPass(User user) throws Exception;
	//ajax请求用户名是否存在
	public User ajaxFindByUsername(String username) throws Exception;
	//注册用户
	public void add(User user) throws Exception;
	//根据激活码查找用户
	public User findByCode(String code) throws SQLException;
	//激活用户
	public void updateCode(Integer id) throws SQLException;
	//查找所有图片头像
	public List<Img> findImg() throws SQLException;
	//更新个人信息
	public void updatePerson(User user) throws SQLException;
	//根据用户id查询用户信息
	public User findUserById(Integer id) throws SQLException;
	//管理员用户名密码校验
	public Admin findAdminByUandP(Admin admin) throws SQLException;
	public List<Img> findAllImg() throws SQLException;
	//添加用户随机头像
	public void addUserImg(String imgUrl) throws SQLException;
	//添加用户随机头像
	public void deleteUserImg(Integer id) throws SQLException;
}

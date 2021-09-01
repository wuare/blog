package top.wuareb.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.wuareb.blog.domain.vo.user.Admin;
import top.wuareb.blog.domain.vo.user.Img;
import top.wuareb.blog.domain.vo.user.User;
import top.wuareb.blog.mapper.UserMapper;
import top.wuareb.blog.util.CommonUtils;
import top.wuareb.blog.util.Mail;
import top.wuareb.blog.util.MailUtils;
import top.wuareb.blog.util.UserException;

import javax.mail.Session;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.util.List;
import java.util.Properties;

@Service
public class UserService {
	@Autowired
	private UserMapper userMapper;
	/*
	 * 根据用户名密码查找用户
	 */
	public User findByUserAndPass(User user){
		try{
			return userMapper.findByUserAndPass(user);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	/*
	 * ajax请求用户名是否存在
	 */
	public User ajaxFindByUsername(String username) {
		try{
			return userMapper.ajaxFindByUsername(username);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	//注册功能
	@Transactional(rollbackFor=Exception.class)
	public void regist(User user) {
		try{
			List<Img> imgList = userMapper.findImg();
			if(imgList!=null){
				int number = (int) (Math.random()*imgList.size());
				user.setUserUrl(imgList.get(number).getUrl());
			}
			//设置状态和激活码
			user.setState(0);
			user.setCode(CommonUtils.uuid());
		
			//向数据库中添加
			userMapper.add(user);
			//发邮件
			//加载配置文件
			Properties prop = new Properties();
	
			prop.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
			//登录服务器，得到session
			String host = prop.getProperty("host");//服务器主机名
			String name = prop.getProperty("username");//登录用户名
			String pass = prop.getProperty("password");//登录密码
			Session session = MailUtils.createSession(host, name, pass);
			//创建mail对象
			String from = prop.getProperty("from");
			String to = user.getEmail();
			String subject = prop.getProperty("subject");
			String content = MessageFormat.format(prop.getProperty("content"),user.getCode());
			Mail mail = new Mail(from,to,subject,content);
			//发送邮件
			MailUtils.send(session, mail);
		}catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
	
	//激活功能
	public void activation(String code) throws UserException {
		try{
			User user = userMapper.findByCode(code);
			if(user == null ) throw new UserException("无效的激活码！");
			if(user.getState()==1) throw new UserException("您的账户已经激活，请不要重复激活！");
			userMapper.updateCode(user.getId());
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 更改个人信息
	 */
	public void updatePerson(User user) {
		try{
			userMapper.updatePerson(user);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 根据用户id查询用户信息
	 * @param id
	 * @return
	 */
	public User findUserById(Integer id) {
		try{
			return userMapper.findUserById(id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 管理员用户名密码校验
	 * @return
	 */
	public Admin findAdminByUandP(Admin admin) {
		try{
			return userMapper.findAdminByUandP(admin);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	public List<Img> findAllImg() {
		try{
			return userMapper.findAllImg();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 添加用户随机头像
	 * @param imgUrl
	 */
	public void addUserImg(String imgUrl) {
		try{
			userMapper.addUserImg(imgUrl);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	/**
	 * 删除用户随机头像
	 * @param id
	 */
	public void deleteUserImg(Integer id) {
		try{
			userMapper.deleteUserImg(id);
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
}

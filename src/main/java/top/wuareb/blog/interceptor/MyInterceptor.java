package top.wuareb.blog.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.wuareb.blog.domain.vo.user.Admin;
import top.wuareb.blog.domain.vo.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Component
public class MyInterceptor implements HandlerInterceptor {

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		
			String contextPath = request.getContextPath();
	        String  url = request.getServletPath().toString();
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("user");
	        Admin admin = (Admin) session.getAttribute("admin");
	        //这里可以根据session的用户来判断角色的权限，根据权限来重定向不同的页面，简单起见，这里只是做了一个重定向
	        if (user != null || admin != null) {
	        	return true;
	        }else{
	        	//被拦截，重定向到login界面
	            response.sendRedirect(contextPath+"/login");
	            return false;
	        }
	}
	
}

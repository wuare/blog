package top.wuareb.blog.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import top.wuareb.blog.interceptor.AdminInterceptor;
import top.wuareb.blog.interceptor.MyInterceptor;
import top.wuareb.blog.interceptor.TokenInterceptor;

import javax.annotation.Resource;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private AdminInterceptor adminInterceptor;
    @Resource
    private MyInterceptor myInterceptor;
    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(myInterceptor).addPathPatterns("/user/**");
        registry.addInterceptor(adminInterceptor).addPathPatterns("/admin/**");
        registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/jsp/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
}

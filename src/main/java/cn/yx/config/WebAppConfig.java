package cn.yx.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cn.yx.interceptors.LoginInterceptor;

/**
 * @author yuxuanjiao
 * @date 2017年7月12日 上午9:37:43
 * @version 1.0
 * @description 继承和重写一些配置，添加静态资源filter类似。
 */

@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter {

    private List<String> excludePathes = Arrays.asList("/api/**", "/backend/login", "/backend/loginAction", "/backend/logout", "/backend/addUser", "/backend/modifyPwd");
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**");
        for (String path : excludePathes) {
            registration.excludePathPatterns(path);
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
        registry.addResourceHandler("/fonts/**").addResourceLocations("classpath:/static/fonts/");
        registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/img/");
        super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // TODO Auto-generated method stub
        super.addViewControllers(registry);
    }

}

package com.school.thymeleaf.config;


import com.school.thymeleaf.interceptor.AllRequest;
import com.school.thymeleaf.interceptor.Whitelist;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final Whitelist whitelist;

    public WebConfig(Whitelist whitelistConfig) {
        this.whitelist = whitelistConfig;
    }
    /**
     * 静态资源映射
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/");
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/");
        registry.addResourceHandler("/imgs/**").addResourceLocations("classpath:/static/imgs/");
        registry.addResourceHandler("/404/**").addResourceLocations("classpath:/static/404/");
    }

    /**
     * 配置自定义的拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AllRequest(whitelist)).addPathPatterns("/**");
    }
}

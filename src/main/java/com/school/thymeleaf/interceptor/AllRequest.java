package com.school.thymeleaf.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 拦截器，拦截登录请求
 */
@Slf4j
@Component
public class AllRequest implements HandlerInterceptor {

    @Autowired
    Whitelist whitelist;

    public AllRequest(Whitelist whitelist) {
        this.whitelist = whitelist;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Integer username = (Integer) session.getAttribute("username");
        System.out.println("执行拦截器方法！");
        System.out.println(username);
        String requestURI = request.getRequestURI();
        log.info("请求的路径---{}",requestURI);
        log.info("白名单--{}",whitelist);
        //白名单放行
        //判断里面是和否有静态资源
        if (eqs(eql(requestURI),whitelist.getStaticUrl())){
            return true;
        }

        if (HasList(whitelist.getUrls(),requestURI)){
            return true;
        }
        if (username == null){
            // 如果未登录，将请求重定向到登录页
            response.sendRedirect("/login");
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    /**
     * 匹配白名单
     */
    public boolean HasList(List<String> list,String url){
        for (String s : list) {
            if (eq(s,url)){
                System.out.println(s + "     " + url);
                return true;
            }
        }
        return false;
    }

    /**
     * 路径匹配
     */
    public boolean eq(String a,String b){
        String[] split = a.split("/");
        String[] split1 = b.split("/");
        for (String value : split1) {
            for (String s : split) {
                if (value.equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean eqs(String a,List<String> url){
        for (String s : url) {
            if (a.contains(s) || s.contains(a)){
                return true;
            }
        }
        return false;
    }

    public String eql(String a){
        if (a.contains("imags")){
            return "imags";
        }
        if (a.contains("js")){
            return "js";
        }
        if (a.contains("css")){
            return "css";
        }
        if (a.contains("404")){
            return "404";
        }
        return a;
    }
}

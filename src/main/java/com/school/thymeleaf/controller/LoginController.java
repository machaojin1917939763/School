package com.school.thymeleaf.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.school.thymeleaf.domain.Article;
import com.school.thymeleaf.domain.Menu;
import com.school.thymeleaf.domain.Type;
import com.school.thymeleaf.domain.User;
import com.school.thymeleaf.service.ArticleService;
import com.school.thymeleaf.service.MenuService;
import com.school.thymeleaf.service.TypeService;
import com.school.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping({"/toLogin"})
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              ModelAndView modelAndView,
                              HttpServletRequest request) {

       return userService.toLogin(username, password, modelAndView, request);
    }

    @RequestMapping({"/","/login","/login.html"})
    public ModelAndView toIndex(ModelAndView modelAndView,HttpServletRequest request) {
        HttpSession session = request.getSession();
        Integer usernameId = (Integer) session.getAttribute("username");
        //说明已经登录了
        if (usernameId != null) {
            User user = userService.getById(usernameId);
            return userService.toLogin(user.getName(),user.getPassword(),modelAndView,request);
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("currentYear", "2023");
            return modelAndView;
        }
    }

    @RequestMapping("/logout")
    public ModelAndView logout(ModelAndView modelAndView,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        modelAndView.setViewName("login");
        return modelAndView;
    }

}

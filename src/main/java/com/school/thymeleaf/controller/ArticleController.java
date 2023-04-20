package com.school.thymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ArticleController {
    @RequestMapping({"/toLogin"})
    public ModelAndView login(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              ModelAndView modelAndView){

        if (username.equals("username") && password.equals("password")){
            modelAndView.setViewName("index");
        }else {
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

}

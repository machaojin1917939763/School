package com.school.mybatis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ShowController {
    @RequestMapping({"/","/login","/login.html"})
    public ModelAndView toIndex(ModelAndView modelAndView){
        modelAndView.setViewName("login");
        return modelAndView;
    }
}

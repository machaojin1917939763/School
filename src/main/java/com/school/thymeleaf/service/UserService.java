package com.school.thymeleaf.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.school.thymeleaf.domain.User;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
* @author machaojin
* @description 针对表【stu_user】的数据库操作Service
* @createDate 2023-04-23 14:36:53
*/
public interface UserService extends IService<User> {

    ModelAndView toLogin(String username, String password, ModelAndView modelAndView, HttpServletRequest request);

}

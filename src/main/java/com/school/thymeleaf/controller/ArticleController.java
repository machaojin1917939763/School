package com.school.thymeleaf.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.school.thymeleaf.domain.Article;
import com.school.thymeleaf.domain.Comment;
import com.school.thymeleaf.domain.User;
import com.school.thymeleaf.service.ArticleService;
import com.school.thymeleaf.service.CommentService;
import com.school.thymeleaf.service.UserService;
import com.school.thymeleaf.vo.ArticleVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/getArticle/{articleId}")
    public ModelAndView getPage(@PathVariable String articleId, ModelAndView modelAndView, HttpServletRequest request){

        Article service = articleService.getById(articleId);
       ArticleVo articleVo = new ArticleVo();

        HttpSession session = request.getSession();
        Integer usernameId = (Integer) session.getAttribute("username");
        //说明已经登录了
        if (usernameId != null) {
            User user = userService.getById(usernameId);
            modelAndView = userService.toLogin(user.getName(),user.getPassword(),modelAndView,request);
        } else {
            modelAndView.setViewName("login");
            modelAndView.addObject("currentYear", "2023");
            return modelAndView;
        }
        if (service != null){
            List<Comment> list = commentService.list(new LambdaQueryWrapper<Comment>().eq(Comment::getArticleId,service.getId()));
            BeanUtils.copyProperties(service,articleVo,"time");
            User user = userService.getById(service.getUserId());
            articleVo.setUserId(user.getName());
            articleVo.setComments(list);
        }

        modelAndView.setViewName("article");
        modelAndView.addObject("article",articleVo);
        return modelAndView;
    }
}

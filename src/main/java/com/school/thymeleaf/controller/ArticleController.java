package com.school.thymeleaf.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.school.thymeleaf.domain.*;
import com.school.thymeleaf.service.*;
import com.school.thymeleaf.vo.ArticleVo;
import com.school.thymeleaf.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Controller
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    /**
     * 保存文章
     * @param article
     * @param modelAndView
     * @param request
     * @return
     */
    @PostMapping("/article/write")
    @ResponseBody
    @Transactional(rollbackFor = Exception.class)
    public Response addArticle(@RequestBody Article article, ModelAndView modelAndView, HttpServletRequest request){
        Integer username = (Integer) request.getSession().getAttribute("username");
        if(username == null){
            return new Response("请先登录",500);
        }
        article.setUserId(username);

        //查询，如果有就删除
        Article one = articleService.getOne(new LambdaQueryWrapper<Article>().eq(!StringUtils.isEmpty(article.getTitle()), Article::getTitle, article.getTitle()));
        if (one != null){
            articleService.removeById(one.getId());
        }

        article.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(System.currentTimeMillis()));
        Type serviceOne = typeService.getOne(new LambdaQueryWrapper<Type>().eq(!StringUtils.isEmpty(article.getTypeId()), Type::getName, article.getTypeId()));
        if (serviceOne != null){
            article.setTypeId(serviceOne.getId().toString());
        }else {
            Type type = new Type();
            type.setName(article.getTypeId());
            typeService.save(type);
            article.setTypeId(type.getId().toString());
        }
        articleService.save(article);
        modelAndView.addObject("code",200);
        log.info("提交文章{}",article);
        //清空缓存
        extracted();
        return new Response("提交成功",200);
    }

    private void extracted() {
        redisTemplate.delete("school:article:all");
        redisTemplate.delete("school:article:map");
    }

    @GetMapping("/toWrite")
    public ModelAndView toWrite(ModelAndView modelAndView,HttpServletRequest request){
        boolean isLog = getModelAndView(modelAndView, request);
        if (!isLog) return modelAndView;
        modelAndView.setViewName("write");
        return modelAndView;
    }

    @GetMapping("/article/delete/{id}")
    public ModelAndView delete(@PathVariable String id,ModelAndView modelAndView,HttpServletRequest request){
        //清空缓存
        extracted();
        articleService.removeById(id);
        //TODO跳转
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /**
     * 修改文章
     * @param modelAndView
     * @param request
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ModelAndView update(ModelAndView modelAndView, HttpServletRequest request, @PathVariable String id){
        //清空缓存
        extracted();
        boolean isLog = getModelAndView(modelAndView, request);
        if (!isLog) return modelAndView;
        Article service = articleService.getById(id);
        Type type = typeService.getById(service.getTypeId());
        if (type != null) {
            service.setTypeId(type.getName());
        }
        modelAndView.addObject("article",service);
        modelAndView.setViewName("write");
        return modelAndView;
    }

    private boolean getModelAndView(ModelAndView modelAndView, HttpServletRequest request) {
        Integer username = (Integer) request.getSession().getAttribute("username");
        if(username == null){
            modelAndView.setViewName("login");
            return false;
        }
        List<Menu> menuList = menuService.list(new LambdaQueryWrapper<Menu>().eq(Menu::getUserId, username));
        modelAndView.addObject("menuList",menuList);
        User user = userService.getById(username);
        modelAndView.addObject("username",user.getNickName());
        return true;
    }

    @GetMapping("/getArticle/{articleId}")
    public ModelAndView getArticle(@PathVariable String articleId, ModelAndView modelAndView, HttpServletRequest request){

        Article service;

        String arti = redisTemplate.opsForValue().get("school:article:" + articleId);
        if (StringUtils.isEmpty(arti)){
            service = articleService.getById(articleId);
            redisTemplate.opsForValue().set("school:article:" + articleId, JSON.toJSONString(service));
        }else {
            service = JSON.parseObject(arti, new TypeReference<Article>() {});
        }

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
            if (user != null) {
                articleVo.setUserId(user.getNickName());
            }
            articleVo.setComments(list);
            articleVo.setTime(service.getTime());
            Type type = typeService.getById(service.getTypeId());
            if (type != null) {
                articleVo.setTypeId(type.getName());
            }

        }
        modelAndView.setViewName("article");
        modelAndView.addObject("article",articleVo);
        return modelAndView;
    }
}

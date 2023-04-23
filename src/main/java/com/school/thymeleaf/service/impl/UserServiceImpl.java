package com.school.thymeleaf.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.thymeleaf.domain.Article;
import com.school.thymeleaf.domain.Menu;
import com.school.thymeleaf.domain.Type;
import com.school.thymeleaf.domain.User;
import com.school.thymeleaf.service.*;
import com.school.thymeleaf.mapper.UserMapper;
import com.school.thymeleaf.mapper.UserMapper;
import com.school.thymeleaf.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
* @author machaojin
* @description 针对表【stu_user】的数据库操作Service实现
* @createDate 2023-04-23 14:36:53
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private MenuService menuService;

    @Override
    public ModelAndView toLogin(String username, String password, ModelAndView modelAndView, HttpServletRequest request) {
            HttpSession session = request.getSession();

        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
            userLambdaQueryWrapper.eq(!StringUtils.isEmpty(username), User::getName, username)
                    .and(userLambdaQueryWrapper1 -> {
                        userLambdaQueryWrapper1.eq(!StringUtils.isEmpty(password), User::getPassword, password);
                    });
            User one = this.getOne(userLambdaQueryWrapper);
            if (one != null) {
                //查询菜单
                List<Menu> menuList = menuService.list(new LambdaQueryWrapper<Menu>().eq(Menu::getUserId, one.getId()));
                //设置用户名
                modelAndView.addObject("username", one.getNickName());
                //查询所有文章
                List<Article> list = articleService.list(new LambdaQueryWrapper<Article>().orderByAsc(Article::getTime));
                //按照文章分类传递给前端
                HashMap<String, List<Article>> map = new HashMap<>();
                list.forEach((article -> {
                    Type type = typeService.getById(article.getTypeId());
                    if (type != null) {
                        if (!map.containsKey(type.getName())) {
                            List<Article> articles = new ArrayList<>();
                            articles.add(article);
                            map.put(type.getName(), articles);
                        } else {
                            List<Article> articles = map.get(type.getName());
                            articles.add(article);
                        }
                    }else {
                        List<Article> articles = new ArrayList<>();
                        articles.add(article);
                        map.put(article.getTitle(), articles);
                    }
                }));
                modelAndView.addObject("article",map);
                modelAndView.addObject("menuList",menuList);
                modelAndView.setViewName("index");
                session.setAttribute("username",one.getId());
            } else {
                modelAndView.addObject("tip","用户名或者密码错误！请检查");
                modelAndView.setViewName("login");
            }
            return modelAndView;
    }
}





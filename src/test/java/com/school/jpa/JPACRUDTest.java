package com.school.jpa;

import com.school.jpa.domain.TArticle;
import com.school.jpa.mapper.TArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPACRUDTest {

    @Autowired
    private TArticleMapper articleMapper;

    @Test
    void select(){
        List<TArticle> articles = articleMapper.findAll();
        System.out.println(articles);
    }

    @Test
    void insert(){
        TArticle article = new TArticle();
        article.setId(1);
        article.setContent("11111");
        article.setTitle("title");
        TArticle save = articleMapper.save(article);
        System.out.println(save);
    }
}

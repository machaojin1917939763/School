package com.school;

import com.school.mapper.TArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MyBatisArticleTest {
    @Autowired
    private TArticleMapper mapper;

    //增删改查,有兴趣自己写

}

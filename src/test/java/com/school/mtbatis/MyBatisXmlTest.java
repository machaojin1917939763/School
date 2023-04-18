package com.school.mtbatis;

import com.school.mybatis.domain.TComment;
import com.school.mybatis.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class MyBatisXmlTest {

    @Autowired
    private CommentMapper mapper;

    /**
     * 查询测试
     */
    @Test
    void list(){
        mapper.getList().forEach((System.out::println));
    }

    /**
     * 添加测试
     */
    @Test
    void add(){
        TComment comment = new TComment();
        comment.setaId(new Random().nextInt());
        comment.setContent("这是文章的内容,基于XMl配置文件");
        comment.setAuthor("马超金");
        comment.setaId(new Random().nextInt());
        System.out.println(mapper.add(comment) == 1 ? "插入成功" : "插入失败");
    }

    /**
     * 修改测试
     */
    @Test
    void update(){
        System.out.println(mapper.update("修改后的作者",6) == 1 ? "修改成功" : "修改失败");
    }

    /**
     * 修改测试
     */
    @Test
    void delete(){
        System.out.println(mapper.delete(6) == 1 ? "删除成功" : "删除失败");
    }

}

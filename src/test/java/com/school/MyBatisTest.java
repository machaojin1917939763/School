package com.school;

import com.school.domain.TComment;
import com.school.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class MyBatisTest {
    //数据库的增删改查
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 查询全部测试
     */
    @Test
    void list(){
        List<TComment> list = commentMapper.getList();
        System.out.println(list);
    }

    /**
     * 添加测试
     */
    @Test
    void add(){
        TComment comment = new TComment();
        comment.setaId(new Random().nextInt());
        comment.setContent("这是文章的内容");
        comment.setAuthor("马超金");
        comment.setaId(new Random().nextInt());
        System.out.println(commentMapper.add(comment) == 1 ? "插入成功" : "插入失败");
    }

    /**
     * 修改测试
     */
    @Test
    void update(){
        System.out.println(commentMapper.update("修改后的作者",6) == 1 ? "修改成功" : "修改失败");
    }

    /**
     * 修改测试
     */
    @Test
    void delete(){
        System.out.println(commentMapper.delete(6) == 1 ? "删除成功" : "删除失败");
    }

}

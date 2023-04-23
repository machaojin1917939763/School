package com.school.thymeleaf.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.thymeleaf.domain.Comment;
import com.school.thymeleaf.service.CommentService;
import com.school.thymeleaf.mapper.CommentMapper;
import com.school.thymeleaf.mapper.CommentMapper;
import com.school.thymeleaf.service.CommentService;
import org.springframework.stereotype.Service;

/**
* @author machaojin
* @description 针对表【stu_comment】的数据库操作Service实现
* @createDate 2023-04-23 14:36:53
*/
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService {

}





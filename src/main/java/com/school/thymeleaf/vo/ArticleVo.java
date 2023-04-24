package com.school.thymeleaf.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.school.thymeleaf.domain.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleVo {
    private Integer id;

    private String title;

    private String content;

    private String userId;

    private String typeId;

    private String time;

    private List<Comment> comments;
}

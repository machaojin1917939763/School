package com.school.thymeleaf.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stu_comment")
public class Comment {

    @TableId
    private Integer id;

    private String person;

    private String comment;

    @TableField(value = "a_id")
    private Integer articleId;

    private String time;

}

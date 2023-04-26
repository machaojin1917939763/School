package com.school.thymeleaf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stu_article")
public class Article implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String title;

    private String content;

    @TableField(value = "u_id")
    private Integer userId;

    @TableField(value = "type_id")
    private String typeId;

    private String time;

}

package com.school.thymeleaf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stu_menu")
public class Menu implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String menuName;

    private Integer typeId;

    private String url;

    private String userId;
}

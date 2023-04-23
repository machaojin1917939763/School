package com.school.thymeleaf.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stu_user")
public class User {

    @TableId
    private Integer id;

    private String name;

    private String nickName;

    private String email;

    private String password;

}

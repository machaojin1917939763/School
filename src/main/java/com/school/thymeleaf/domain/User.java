package com.school.thymeleaf.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stu_user")
public class User implements Serializable {

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private String name;

    private String nickName;

    private String email;

    private String password;

}

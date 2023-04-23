package com.school.thymeleaf.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "stu_type")
public class Type {

    @Id
    private Integer id;

    private String name;

}


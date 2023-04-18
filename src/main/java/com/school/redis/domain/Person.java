package com.school.redis.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash("person")//指定操作对象在redis中的存储看空间
public class Person {
    @Id//标识实体类主键
    private String id;
    @Indexed//二级索引
    private String firstName;
    @Indexed
    private String lastName;
    private Address address;
    private String family;
}

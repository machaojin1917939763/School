package com.school.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.school.mapper")
public class MyBatisConfigP {
}

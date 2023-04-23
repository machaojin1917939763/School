package com.school.thymeleaf.interceptor;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;

@Data
@Configuration
//指定前缀
@ConfigurationProperties(prefix = "permit")
//指定配置文件的路径，可以是任意位置
@PropertySource(value = "classpath:interceptor/permit.perproties",encoding = "utf-8")
//开启配置文件的功能
//@EnableConfigurationProperties(Whitelist.class)
public class Whitelist {
    private List<String> urls;
    private List<String> staticUrl;
}




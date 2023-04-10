package com.school.domain;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

//指定这个类是SpringBoot的配置文件，SpringBoot在启动的时候会去加载这个嘞
@Configuration
//指定前缀
@ConfigurationProperties(prefix = "test")
//指定配置文件的路径，可以是任意位置
@PropertySource(value = "classpath:myproperties.properties",encoding = "utf-8")
//开启配置文件的功能
@EnableConfigurationProperties(MyProperties.class)
public class MyProperties {
    private String key;
    private String value;

    @Override
    public String toString() {
        return "MyProperties{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

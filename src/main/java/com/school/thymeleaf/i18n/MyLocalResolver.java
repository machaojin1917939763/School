package com.school.thymeleaf.i18n;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;


@Configuration
public class MyLocalResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        String name = request.getParameter("l");
        Locale locale = Locale.ENGLISH; // 使用英语作为默认本地化信息
        if (StringUtils.hasText(name)){
            String[] arr = name.split("_");
            locale = new Locale(arr[0], arr[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale) {
        // 这里不需要实现任何操作
    }

    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocalResolver();
    }
}


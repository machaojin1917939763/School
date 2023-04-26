package com.school.thymeleaf.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 异常拦截类，拦截所有的异常，并且重定向到404页面
 */
@ControllerAdvice
public class MyExceptionInterceptor{

    @ExceptionHandler(Exception.class)
    public String to(Exception e){
        e.printStackTrace();
        return "404";
    }
}

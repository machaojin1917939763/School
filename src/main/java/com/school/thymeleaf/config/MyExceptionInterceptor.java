package com.school.thymeleaf.config;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MyExceptionInterceptor{

    @ExceptionHandler(Exception.class)
    public String to(Exception e){
        e.printStackTrace();
        return "404";
    }
}

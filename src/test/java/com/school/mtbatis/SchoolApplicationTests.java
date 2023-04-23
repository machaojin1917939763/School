package com.school.mtbatis;

import com.school.common.ProfileConfig;
import com.school.mybatis.config.MyService;
import com.school.mybatis.domain.MyProperties;
import com.school.mybatis.domain.Person;
import com.school.mybatis.domain.Pet;
import com.school.thymeleaf.interceptor.Whitelist;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SchoolApplicationTests {

    @Autowired
    private Person person;
    @Autowired
    private Pet pet;

    @Autowired
    private MyService service;

    @Autowired
    private MyProperties myProperties;

    @Autowired
    private ProfileConfig config;



    @Test
    void contextLoads() {
        System.out.println(person);
        System.out.println(pet);
    }

    @Test
    void testProperties(){
        System.out.println(myProperties);
    }

    @Test
    void testXML(){
        System.out.println(service);
    }

    @Test
    void proFile(){
        System.out.println(whitelist);
    }

    @Autowired
    Whitelist whitelist;


}

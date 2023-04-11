package com.school;

import com.school.config.MyService;
import com.school.domain.MyProperties;
import com.school.domain.Person;
import com.school.domain.Pet;
import com.school.domain.TComment;
import com.school.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

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
        System.out.println(config);
    }


}

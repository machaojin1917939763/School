package com.school.redis;

import com.school.redis.domain.Address;
import com.school.redis.domain.Family;
import com.school.redis.domain.Person;
import com.school.redis.mapper.AddressMapper;
import com.school.redis.mapper.FamilyMapper;
import com.school.redis.mapper.PersonMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class RedisCRUDTest {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private FamilyMapper familyMapper;

    @Autowired
    private AddressMapper addressMapper;
    @Test
    void insert(){
        Person person = new Person();
        person.setId("1");
        person.setFirstName("ma");
        person.setLastName("chaojin");
        person.setFamily("f");
        Address address = new Address();
        address.setCity("bijie");
        address.setCountry("china");
        address.setId("1");
        person.setAddress(address);
        Person save = personMapper.save(person);
        System.out.println(save);
    }
    @Test
    void select(){
        List<Person> all = personMapper.findAll();
        System.out.println(all);
    }

}

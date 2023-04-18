package com.school.redis.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.util.List;

@Data
@RedisHash("family")
public class Family {
    @Id
    private String id;
    @Indexed
    private String type;
    private List<Person> people;
}

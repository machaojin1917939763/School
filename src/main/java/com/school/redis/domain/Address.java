package com.school.redis.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;


@Data
@RedisHash("address")
public class Address {
    @Id
    private String id;
    @Indexed
    private String country;
    private String city;
}

package com.school.redis.mapper;

import com.school.redis.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;


public interface PersonMapper extends JpaRepository<Person, Long> {
}

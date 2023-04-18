package com.school.redis.mapper;

import com.school.redis.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyMapper extends JpaRepository<Family,Integer> {
}

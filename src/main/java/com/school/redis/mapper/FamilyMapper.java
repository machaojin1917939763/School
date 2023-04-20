package com.school.redis.mapper;

import com.school.redis.domain.Family;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<Family,Integer>
 *     Family：对应的实体类
 *     Integer：实体类的主键类型
 */
public interface FamilyMapper extends JpaRepository<Family,String> {
}

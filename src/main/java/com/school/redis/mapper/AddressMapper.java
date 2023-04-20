package com.school.redis.mapper;

import com.school.redis.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<Address,Integer>
 *     Address: 对应的实体类
 *     Integer：实体对应的主键类型
 */
public interface AddressMapper extends JpaRepository<Address,String> {
}

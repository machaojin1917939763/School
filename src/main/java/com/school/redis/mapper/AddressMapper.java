package com.school.redis.mapper;

import com.school.redis.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressMapper extends JpaRepository<Address,Integer> {
}

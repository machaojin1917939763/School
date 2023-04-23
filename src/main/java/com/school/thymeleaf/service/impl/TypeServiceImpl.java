package com.school.thymeleaf.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.thymeleaf.domain.Type;
import com.school.thymeleaf.service.TypeService;
import com.school.thymeleaf.mapper.TypeMapper;
import com.school.thymeleaf.mapper.TypeMapper;
import com.school.thymeleaf.service.TypeService;
import org.springframework.stereotype.Service;

/**
* @author machaojin
* @description 针对表【stu_type】的数据库操作Service实现
* @createDate 2023-04-23 14:36:53
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService {

}





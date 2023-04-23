package com.school.thymeleaf.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.school.thymeleaf.domain.Menu;
import com.school.thymeleaf.mapper.MenuMapper;
import com.school.thymeleaf.service.MenuService;
import org.springframework.stereotype.Service;

/**
* @author machaojin
* @description 针对表【stu_menu】的数据库操作Service实现
* @createDate 2023-04-23 16:19:27
*/
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu>
    implements MenuService {

}





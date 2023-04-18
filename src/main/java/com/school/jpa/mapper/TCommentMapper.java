package com.school.jpa.mapper;

import com.school.jpa.domain.TComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
* @author machaojin
* @description 针对表【t_comment】的数据库操作Mapper
* @createDate 2023-04-18 08:51:42
* @Entity com.school.domain.jpa.TComment
*/
public interface TCommentMapper extends JpaRepository<TComment,Long> {

}

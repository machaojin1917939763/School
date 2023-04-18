package com.school.jpa.mapper;

import com.school.jpa.domain.TArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

/**
* @author machaojin
* @description 针对表【t_article】的数据库操作Mapper
* @createDate 2023-04-18 08:51:42
* @Entity com.school.domain.jpa.TArticle
*/


public interface TArticleMapper extends JpaRepository<TArticle,Long> {

}

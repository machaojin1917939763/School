package com.school.mapper;

import com.school.domain.TArticle;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TArticleMapper{
    //以下是基于注解
    @Select("select * from t_article")
    public List<TArticle> getList();

    @Insert("insert into t_article value (#{article.id},#{article.title},#{article.content})")
    public int add(@Param("article") TArticle article);

    @Update("update t_article set title = #{title} where id = #{id}")
    public int update(@Param("title") String title,@Param("id")Integer id);

    @Delete("delete from t_article where id = #{id}")
    public int delete(@Param("id") Integer id);
    //以下是基于xml配置文件的
    public List<TArticle> getListXml();

    public int addXml(@Param("article")TArticle article);

    public int updateXml(@Param("author")String author,@Param("id")Integer id);

    public int deleteXml(@Param("id")Integer id);

}





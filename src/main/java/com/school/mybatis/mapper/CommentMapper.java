package com.school.mybatis.mapper;


import com.school.mybatis.domain.TComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    //以下是基于注解
    @Select("select * from t_comment")
    public List<TComment> getList();

    @Insert("insert into t_comment value (#{comment.id},#{comment.content},#{comment.author},#{comment.aId})")
    public int add(@Param("comment") TComment comment);

    @Update("update t_comment set author = #{author} where id = #{id}")
    public int update(@Param("author") String author,@Param("id")Integer id);

    @Delete("delete from t_comment where id = #{id}")
    public int delete(@Param("id") Integer id);
    //以下是基于xml配置文件的
    public List<TComment> getListXml();

    public int addXml(@Param("comment")TComment comment);

    public int updateXml(@Param("author")String author,@Param("id")Integer id);

    public int deleteXml(@Param("id")Integer id);
}

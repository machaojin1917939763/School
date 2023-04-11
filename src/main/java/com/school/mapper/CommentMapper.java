package com.school.mapper;


import com.school.domain.TComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {

    @Select("select * from t_comment")
    public List<TComment> getList();

    @Insert("insert into t_comment value (#{comment.id},#{comment.content},#{comment.author},#{comment.aId})")
    public int add(@Param("comment") TComment comment);

    @Update("update t_comment set author = #{author} where id = #{id}")
    public int update(@Param("author") String author,@Param("id")Integer id);

    @Delete("delete from t_comment where id = #{id}")
    public int delete(@Param("id") Integer id);
}

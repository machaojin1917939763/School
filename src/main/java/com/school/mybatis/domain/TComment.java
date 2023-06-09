package com.school.mybatis.domain;


import java.io.Serializable;


public class TComment implements Serializable {

    /**
    * 评论id
    */
    private Integer id;
    /**
    * 评论内容
    */
    private String content;
    /**
    * 评论作者
    */
    private String author;
    /**
    * 关联的文章id
    */
    private Integer aId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    @Override
    public String toString() {
        return "TComment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", author='" + author + '\'' +
                ", aId=" + aId +
                '}';
    }
}

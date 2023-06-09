# School
SpringBoot练习

这里有老师当天讲的代码，需要交的作业，需要使用的时候，直接在当前项目下的目录下执行`git pull`或者点击右上角 ![img_1.png](doc/img_1.png) 号即可拉取项目
![img.png](doc/img-4.png)

## 2023-4-11
## 整合MyBatis
### 添加依赖
打开`pom.xml`文件
在`<dependencies>`下添加以下依赖

步骤一：
添加mysql依赖
```xml
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
步骤二：添加MyBatis依赖
```xml
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.2</version>
</dependency>
```
步骤三：添加jdbc依赖
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
</dependency>
```
步骤三：添加druid依赖
```xml
<dependency>
    <groupId>com.alibaba</groupId>
    <artifactId>druid-spring-boot-starter</artifactId>
    <version>1.2.16</version>
</dependency>
```
### 新建配置文件
在`resouces`目录下新建`applicatin.yml`文件
在文件中添加以下内容
```yaml
spring:
  datasource:
    druid:
      username: root
      password: machaojin
      url: jdbc:mysql://localhost:3306/spring-boot?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&useSSL=true
      driver-class-name: com.mysql.cj.jdbc.Driver
mybatis:
  configuration:
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl
```
### 新建实体类
其中`password`需要改成你自己的数据库密码，在`url`那一行中，`3306`后面的`spring-boot`数据库需要改成你自己的数据库名字

在`domain`包下面创建一个类`TComment`类,需要注意的是，类中的属性需要和数据库里面的字段对应，并且需要有属性的`get`和`set`方法
```java
package com.school.domain;


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

```
### Mapper类创建
在`school`包下新建`mapper`包，在`mapper`包下新建`com.school.thymeleaf.mapper.CommentMapper`类
在类中添加以下内容

```java
package com.school.mapper;


import com.school.mybatis.domain.TComment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface com.school.thymeleaf.mapper.CommentMapper {

    @Select("select * from t_comment")
    public List<TComment> getList();

    @Insert("insert into t_comment value (#{comment.id},#{comment.content},#{comment.author},#{comment.aId})")
    public int add(@Param("comment") TComment comment);

    @Update("update t_comment set author = #{author} where id = #{id}")
    public int update(@Param("author") String author, @Param("id") Integer id);

    @Delete("delete from t_comment where id = #{id}")
    public int delete(@Param("id") Integer id);
}

```
### 注解说明
`@Mapper`注解的作用是把这个类添加到`SpringBoot`的容器中，这样在创建的这个类的对象时，就不需要创建对象了

`@Select @Updata @Delete @Insert`对应的是对数据库的操作

`@Param`是为了给传入的属性起一个别名

`#{author}`表示将方法中传递过来的author属性的值放到这个位置，表示预处理

### 测试MyBatis

```java
package com.school;

import com.school.mybatis.domain.TComment;
import com.school.mybatis.mapper.com.school.thymeleaf.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class MyBatisTest {
    //数据库的增删改查
    @Autowired
    private com.school.thymeleaf.mapper.CommentMapper commentMapper;

    /**
     * 查询全部测试
     */
    @Test
    void list() {
        List<TComment> list = commentMapper.getList();
        System.out.println(list);
    }

    /**
     * 添加测试
     */
    @Test
    void add() {
        TComment comment = new TComment();
        comment.setaId(new Random().nextInt());
        comment.setContent("这是文章的内容");
        comment.setAuthor("马超金");
        comment.setaId(new Random().nextInt());
        System.out.println(commentMapper.add(comment) == 1 ? "插入成功" : "插入失败");
    }

    /**
     * 修改测试
     */
    @Test
    void update() {
        System.out.println(commentMapper.update("修改后的作者", 6) == 1 ? "修改成功" : "修改失败");
    }

    /**
     * 修改测试
     */
    @Test
    void delete() {
        System.out.println(commentMapper.delete(6) == 1 ? "删除成功" : "删除失败");
    }

}

```

## 2023-4-13
演示基于XMl配置文件的形式使用MyBatis

什么东西都不要加

步骤一：在resources目录下添加两个配置文件

![img.png](doc/img-1.png)

mybatis.xml

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="com.school.mybatis.mapper.com.school.thymeleaf.mapper.CommentMapper">

    <resultMap id="comment" type="com.school.mybatis.domain.TComment">
        <id property="id" column="id"/>
        <result property="aId" column="a_id"/>
        <result property="author" column="author"/>
        <result property="content" column="content"/>
    </resultMap>

    <insert id="addXml">
        insert into t_comment value (#{comment.id},#{comment.content},#{comment.author},#{comment.aId})
    </insert>

    <update id="updateXml">
        update t_comment set author = #{author} where id = #{id}
    </update>

    <delete id="deleteXml" parameterType="integer">
        delete from t_comment where id = #{id}
    </delete>

    <select id="getListXml" resultMap="comment">
        select * from t_comment
    </select>
</mapper>
```
TArticleMapper.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.school.mybatis.mapper.TArticleMapper">

    <resultMap id="BaseResultMap" type="com.school.mybatis.domain.TArticle">
        <id property="id" column="id" jdbcType="INTEGER"/>
        <result property="title" column="title" jdbcType="VARCHAR"/>
        <result property="content" column="content" jdbcType="VARCHAR"/>
    </resultMap>

    <insert id="addXml" parameterType="com.school.mybatis.domain.TArticle">
        insert into t_article value (#{article.id},#{article.title},#{article.content})
    </insert>
    <update id="updateXml">
        update t_article set title = #{title} where id = #{id}
    </update>
    <delete id="deleteXml">
        delete from t_article where id = #{id}
    </delete>
    <select id="getListXml" resultMap="BaseResultMap">
        select * from t_article
    </select>

</mapper>

```
在domain包下添加TArticle类，在mapper包下添加TArticleMapper接口

![img.png](doc/img-2.png)

TArticle
```java
package com.school.domain;

import java.io.Serializable;

public class TArticle implements Serializable {
    /**
     * 文章id
     */
    private Integer id;

    /**
     * 文章标题
     */
    private String title;

    /**
     * 文章内容
     */
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 文章id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TArticle other = (TArticle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
```

TArticleMapper

```java
package com.school.mapper;

import com.school.mybatis.domain.TArticle;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface TArticleMapper {
    //以下是基于注解
    @Select("select * from t_article")
    public List<TArticle> getList();

    @Insert("insert into t_article value (#{article.id},#{article.title},#{article.content})")
    public int add(@Param("article") TArticle article);

    @Update("update t_article set title = #{title} where id = #{id}")
    public int update(@Param("title") String title, @Param("id") Integer id);

    @Delete("delete from t_article where id = #{id}")
    public int delete(@Param("id") Integer id);

    //以下是基于xml配置文件的
    public List<TArticle> getListXml();

    public int addXml(@Param("article") TArticle article);

    public int updateXml(@Param("author") String author, @Param("id") Integer id);

    public int deleteXml(@Param("id") Integer id);

}





```

添加测试方法分别对两个表的xml配置方式进行测试

![img.png](doc/img-3.png)

MyBatisArticleTest

```java
package com.school;

import com.school.mybatis.mapper.TArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MyBatisArticleTest {
    @Autowired
    private TArticleMapper mapper;

    //增删改查,有兴趣自己写

}

```

MyBatisXmlTest

```java
package com.school;

import com.school.mybatis.domain.TComment;
import com.school.mybatis.mapper.com.school.thymeleaf.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

@SpringBootTest
public class MyBatisXmlTest {

    @Autowired
    private com.school.thymeleaf.mapper.CommentMapper mapper;

    /**
     * 查询测试
     */
    @Test
    void list() {
        mapper.getList().forEach((System.out::println));
    }

    /**
     * 添加测试
     */
    @Test
    void add() {
        TComment comment = new TComment();
        comment.setaId(new Random().nextInt());
        comment.setContent("这是文章的内容,基于XMl配置文件");
        comment.setAuthor("马超金");
        comment.setaId(new Random().nextInt());
        System.out.println(mapper.add(comment) == 1 ? "插入成功" : "插入失败");
    }

    /**
     * 修改测试
     */
    @Test
    void update() {
        System.out.println(mapper.update("修改后的作者", 6) == 1 ? "修改成功" : "修改失败");
    }

    /**
     * 修改测试
     */
    @Test
    void delete() {
        System.out.println(mapper.delete(6) == 1 ? "删除成功" : "删除失败");
    }

}

```
## 2023-4-18
### 整合 JPA 和 Redis
> jpa是一个全自动的ORM框架，可以根据方法自动生成sql语句查询数据

### 整合JPA，连接MySQL
添加Jpa依赖
```xml
<!--        jpa-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-jpa</artifactId>
        </dependency>
```

添加实体类,`@Entity(name = "t_article")`表示数据库的表名和当前的实体类匹配，`@Id`
`@GeneratedValue(strategy = GenerationType.AUTO)`，分别表示这个表的主键和主键的自增策略
```java
package com.school.jpa.domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "t_article")
public class TArticle implements Serializable {
    /**
     * 文章id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    /**
     * 文章标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 文章内容
     */
    @Column(name = "content")
    private String content;

    private static final long serialVersionUID = 1L;

    /**
     * 文章id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 文章id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 文章标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 文章标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 文章内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 文章内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        TArticle other = (TArticle) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getContent() == null ? other.getContent() == null : this.getContent().equals(other.getContent()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getContent() == null) ? 0 : getContent().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", title=").append(title);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }


}
```
和mybatis一样，添加mapper操作类，需要实现`JpaRepository<TArticle,Long>`接口，其中第一个参数是实体类名，第二个参数我现在不知道。。。。。
```java
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

```


需要在启动类上面添加注解开启Jpa模式`@EnableJpaRepositories(value = "com.school.jpa.mapper")`，指定mapper操作类在哪一个包下面
编写测试方法
```java

package com.school.jpa;

import com.school.jpa.domain.TArticle;
import com.school.jpa.mapper.TArticleMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class JPACRUDTest {

    @Autowired
    private TArticleMapper articleMapper;

    @Test
    void select(){
        List<TArticle> articles = articleMapper.findAll();
        System.out.println(articles);
    }

    @Test
    void insert(){
        TArticle article = new TArticle();
        article.setId(1);
        article.setContent("11111");
        article.setTitle("title");
        TArticle save = articleMapper.save(article);
        System.out.println(save);
    }
}

```

## 整合redis
操作和之前一样，只是实体类需要换一个注解，首先添加redis
```xml
<!--        redis-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>
```
实体类
```java
package com.school.redis.domain;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.index.Indexed;

@Data
@RedisHash("person")//指定操作对象在redis中的存储看空间
public class Person {
    @Id//标识实体类主键
    private String id;
    @Indexed//二级索引
    private String firstName;
    @Indexed
    private String lastName;
    private Address address;
    private String family;
}

```
mapper注解换一下
```java
package com.school.redis.mapper;

import com.school.redis.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressMapper extends JpaRepository<Address,Integer> {
}

```

大功告成！！！！！




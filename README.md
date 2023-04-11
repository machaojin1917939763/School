# School
SpringBoot练习

这里有老师当天讲的代码，需要交的作业，需要使用的时候，直接在当前项目下的目录下执行`git pull`或者点击右上角 ![img_1.png](img_1.png) 号即可拉取项目

## 2023-4-11
## 整合MyBatis
### 添加依赖
打开`pom.xml`文件
在`<dependencies>`下添加以下依赖

步骤一：
添加mysql依赖
```java
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
</dependency>
```
步骤二：添加MyBatis依赖
```java
<dependency>
    <groupId>org.mybatis.spring.boot</groupId>
    <artifactId>mybatis-spring-boot-starter</artifactId>
    <version>2.2.2</version>
</dependency>
```
步骤三：添加jdbc依赖
```java
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
</dependency>
```
步骤三：添加druid依赖
```java
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
在`school`包下新建`mapper`包，在`mapper`包下新建`CommentMapper`类
在类中添加以下内容
```java
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

```
### 注解说明
`@Mapper`注解的作用是把这个类添加到`SpringBoot`的容器中，这样在创建的这个类的对象时，就不需要创建对象了

`@Select @Updata @Delete @Insert`对应的是对数据库的操作

`@Param`是为了给传入的属性起一个别名

`#{author}`表示将方法中传递过来的author属性的值放到这个位置，表示预处理

### 测试MyBatis
```java
package com.school;

import com.school.domain.TComment;
import com.school.mapper.CommentMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
public class MyBatisTest {
    //数据库的增删改查
    @Autowired
    private CommentMapper commentMapper;
    /**
     * 查询全部测试
     */
    @Test
    void list(){
        List<TComment> list = commentMapper.getList();
        System.out.println(list);
    }

    /**
     * 添加测试
     */
    @Test
    void add(){
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
    void update(){
        System.out.println(commentMapper.update("修改后的作者",6) == 1 ? "修改成功" : "修改失败");
    }

    /**
     * 修改测试
     */
    @Test
    void delete(){
        System.out.println(commentMapper.delete(6) == 1 ? "删除成功" : "删除失败");
    }

}

```
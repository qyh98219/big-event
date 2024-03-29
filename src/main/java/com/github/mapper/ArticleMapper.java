package com.github.mapper;


import com.github.pojo.Article;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArticleMapper {

    @Insert("insert into article(title, content, cover_img, state, category_id,create_user,create_time, update_time) " +
            "values(#{article.title}, #{article.content}, #{article.coverImg}, #{article.state}, #{article.categoryId}, #{article.createUser}, now(), now())")
    void add(@Param("article")Article article);


    List<Article> listByPage(@Param("pageNum")Integer pageNum,
                             @Param("pageSize") Integer pageSize,
                             @Param("categoryId") Integer categoryId,
                             @Param("state")String state,
                             @Param("userId")Integer userId);

    Integer count(@Param("categoryId") Integer categoryId,
                  @Param("state")String state,
                  @Param("userId")Integer userId);

    @Select("select * from article where id = #{id}")
    Article findById(@Param("id") Integer id);

    @Update("update article set title = #{article.title}, content = #{article.content}, cover_img = #{article.coverImg}, state = #{article.state}, update_time = now(), category_id = #{article.categoryId} where id = #{article.id}")
    void update(@Param("article") Article article);

    @Delete("delete from article where id = #{id}")
    void delete(@Param("id")Integer id);

    @Delete("delete from article where category_id = #{categoryId}")
    void deleteByCategoryId(@Param("categoryId")Integer categoryId);
}

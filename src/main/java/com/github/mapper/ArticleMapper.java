package com.github.mapper;


import com.github.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
}

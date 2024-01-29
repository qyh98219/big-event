package com.github.mapper;


import com.github.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;

public interface ArticleMapper {

    @Insert("insert into article(title, content, cover_img, state, category_id,create_user,create_time, update_time) " +
            "values(#{article.title}, #{article.content}, #{article.coverImg}, #{article.state}, #{article.categoryId}, #{article.createUser}, now(), now())")
    void add(@Param("article")Article article);
}

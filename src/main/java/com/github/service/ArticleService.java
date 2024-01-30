package com.github.service;

import com.github.pojo.Article;

import java.util.List;

public interface ArticleService {
    void add(Article article);

    List<Article> listByPage(Integer pageNum,
                             Integer pageSize,
                             Integer categoryId,
                             String state,
                             Integer userId);

    Integer count( Integer categoryId,
                   String state,
                   Integer userId);

    Article findById(Integer id);
}

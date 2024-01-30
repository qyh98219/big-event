package com.github.service.impl;

import com.github.mapper.ArticleMapper;
import com.github.pojo.Article;
import com.github.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName ArticleServiceImpl
 * @Description 文章业务类
 * @Author qyh
 * @Date 2024/1/29 19:13
 * @Version 1.0
 **/
@Service
public class ArticleServiceImpl implements ArticleService {

    private ArticleMapper articleMapper;

    @Autowired
    public ArticleServiceImpl(ArticleMapper articleMapper){
        this.articleMapper = articleMapper;
    }


    @Override
    public void add(Article article) {
        articleMapper.add(article);
    }

    @Override
    public List<Article> listByPage(Integer pageNum, Integer pageSize, Integer categoryId, String state, Integer userId) {
        return articleMapper.listByPage(pageNum, pageSize,categoryId, state,userId);
    }

    @Override
    public Integer count(Integer categoryId, String state, Integer userId) {
        return articleMapper.count(categoryId, state, userId);
    }

    @Override
    public Article findById(Integer id) {
        return articleMapper.findById(id);
    }
}

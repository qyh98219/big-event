package com.github.service.impl;

import com.github.mapper.ArticleMapper;
import com.github.pojo.Article;
import com.github.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

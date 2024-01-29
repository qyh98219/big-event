package com.github.controller;

import com.github.pojo.Article;
import com.github.pojo.Result;
import com.github.service.ArticleService;
import com.github.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author qyh
 * @Description 文章内容接口
 * @Date 2024/1/26 9:46
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService){
        this.articleService = articleService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article){
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        article.setCreateUser(userId);

        articleService.add(article);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<String> list(){
        return Result.success("所有文章数据");
    }
}

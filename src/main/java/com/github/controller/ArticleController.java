package com.github.controller;

import com.github.pojo.Article;
import com.github.pojo.Result;
import com.github.service.ArticleService;
import com.github.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
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
    public Result<Map<String, Object>> list(@RequestParam("pageNum")Integer pageNum,
                               @RequestParam("pageSize")Integer pageSize,
                               @RequestParam(name = "categoryId", required = false) Integer categoryId,
                               @RequestParam(name = "state", required = false) String state){

        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");
        Integer offset = (pageNum - 1) * pageSize;
        Integer count = articleService.count(categoryId, state, userId);
        List<Article> articles = articleService.listByPage(offset, pageSize, categoryId,state, userId);
        Map<String, Object> resultMap = new HashMap<>(2);
        resultMap.put("total", count);
        resultMap.put("items", articles);
        return Result.success(resultMap);
    }

    @GetMapping("/detail")
    public Result<Article> detail(@RequestParam("id") Integer id){
        Article article = articleService.findById(id);
        return Result.success(article);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Article.UpdateGroup.class) Article article){
        articleService.update(article);
        return Result.success();
    }
}

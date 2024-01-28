package com.github.controller;

import com.github.pojo.Category;
import com.github.pojo.Result;
import com.github.service.CategoryService;
import com.github.service.impl.CategoryServiceImpl;
import com.github.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @ClassName CategoryController
 * @Description 文章分类接口
 * @Author qyh
 * @Date 2024/1/28 21:41
 * @Version 1.0
 **/
@RestController
@RequestMapping("/category")
public class CategoryController {

    private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryServiceImpl categoryService){
        this.categoryService = categoryService;
    }

    @PostMapping("/add")
    public Result addCategory(@RequestBody Category category){
        if(!StringUtils.hasLength(category.getCategoryName()) && !StringUtils.hasLength(category.getCategoryAlias())){
            return Result.error("缺乏必要参数");
        }

        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        category.setCreateUser(id);
        categoryService.add(category);
        return Result.success();
    }
}

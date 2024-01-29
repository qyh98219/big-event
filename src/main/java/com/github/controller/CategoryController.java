package com.github.controller;

import com.github.pojo.Category;
import com.github.pojo.Result;
import com.github.service.CategoryService;
import com.github.service.impl.CategoryServiceImpl;
import com.github.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        category.setCreateUser(id);
        categoryService.add(category);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<List<Category>> list(){
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer userId = (Integer) claims.get("id");

        List<Category> categories = categoryService.list(userId);
        if (categories.isEmpty()){
            return Result.error("出现未知错误");
        }

        return Result.success(categories);
    }

    @GetMapping("/detail")
    public Result<Category> detail(@RequestParam("id") Integer id){
        Category detail = categoryService.detail(id);
        return Result.success(detail);
    }

    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){

        categoryService.update(category);
        return Result.success();
    }
}

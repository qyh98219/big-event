package com.github.service.impl;

import com.github.mapper.CategoryMapper;
import com.github.pojo.Category;
import com.github.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName CategoryServiceImpl
 * @Description 文章分类业务处理
 * @Author qyh
 * @Date 2024/1/28 21:45
 * @Version 1.0
 **/
@Service
public class CategoryServiceImpl implements CategoryService {

    private CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper){
        this.categoryMapper = categoryMapper;
    }


    @Override
    public void add(Category category) {
        categoryMapper.add(category);
    }
}

package com.github.service;

import com.github.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list(Integer createUser);
}

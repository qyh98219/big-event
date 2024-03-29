package com.github.service;

import com.github.pojo.Category;

import java.util.List;

public interface CategoryService {
    void add(Category category);

    List<Category> list(Integer createUser);

    Category detail(Integer id);

    void update(Category category);

    void delete(Integer id);
}

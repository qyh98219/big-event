package com.github.mapper;

import com.github.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CategoryMapper {

    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values(#{category.categoryName}, #{category.categoryAlias}, #{category.createUser}, now(), now())")
    void add(@Param("category")Category category);

    @Select("select * from category where create_user = #{createUser}")
    List<Category> list(@Param("createUser") Integer createUser);
}

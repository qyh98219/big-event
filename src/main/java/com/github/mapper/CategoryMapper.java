package com.github.mapper;

import com.github.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface CategoryMapper {

    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values(#{category.categoryName}, #{category.categoryAlias}, #{category.createUser}, now(), now())")
    void add(@Param("category")Category category);

    @Select("select * from category where create_user = #{createUser}")
    List<Category> list(@Param("createUser") Integer createUser);

    @Select("select * from category where id = #{id}")
    Category findCategoryById(@Param("id") Integer id);

    @Update("update category set category_name = #{category.categoryName}, category_alias=#{category.categoryAlias}, update_time=now() where id = #{category.id}")
    void update(@Param("category") Category category);
}

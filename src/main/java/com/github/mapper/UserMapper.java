package com.github.mapper;

import com.github.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUserName(@Param("username") String username);

    @Insert("insert into user(username, password, create_time, update_time) " +
            "values(#{username}, #{password}, now(), now())")
    void add(@Param("username") String username, @Param("password") String password);

    @Update("update user set nickname=#{user.nickname}, email=#{user.email}, update_time=now() where id = #{user.id}")
    void update(@Param("user")User user);

    @Update("update user set user_pic = #{avatarUrl}, update_time = now() where id = #{id}")
    void updateAvatar(@Param("id")Integer id, @Param("avatarUrl")String avatarUrl);

    @Update("update user set password = #{password}, update_time= now() where id = #{id}")
    void updatePwd(@Param("id")Integer id, @Param("password") String password);
}

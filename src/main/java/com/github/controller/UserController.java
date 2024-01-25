package com.github.controller;

import com.github.pojo.Result;
import com.github.pojo.User;
import com.github.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author qyh
 * @Description 用户相关接口
 * @Date 2024/1/24 10:46
 */
@Validated
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Result register(@Pattern (regexp = "^\\S{5,16}$") @RequestParam("username") String username,
                           @Pattern (regexp = "^\\S{5,16}$") @RequestParam("password") String password){
        //查询用户
        User user = userService.findByUserName(username);
        if(user == null){
            //注册
            userService.register(username, password);
            return Result.success();
        }else {
            //TODO 被占用
            return Result.error("用户名被占用");
        }
    }
}

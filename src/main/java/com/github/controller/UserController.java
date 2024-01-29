package com.github.controller;

import com.github.mapstruct.UserMapper;
import com.github.pojo.Result;
import com.github.pojo.User;
import com.github.service.UserService;
import com.github.utils.JwtUtil;
import com.github.utils.Md5Util;
import com.github.utils.ThreadLocalUtil;
import com.github.vo.UpdateDataVO;
import com.github.vo.UserVO;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    //注册接口
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

    //登录接口
    @PostMapping("/login")
    public Result<String> login(@Pattern (regexp = "^\\S{5,16}$") @RequestParam("username")String username,
                        @Pattern (regexp = "^\\S{5,16}$") @RequestParam("password") String password){
        User user = userService.findByUserName(username);
        if (user == null) {
            //未查询到
            return Result.error("用户名有误");
        }

        //判断密码是否正确
        if(Md5Util.getMD5String(password).equals(user.getPassword())){
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码有误");
    }

    @GetMapping("/info")
    public Result<UserVO> userInfo(){
        Map<String,Object> claims = ThreadLocalUtil.get();
        String username = claims.get("username").toString();
        User user = userService.findByUserName(username);
        UserVO userVO = UserMapper.INSTANCE.userToUserVO(user);
        return Result.success(userVO);
    }

    @PutMapping("/update")
    public Result updateInfo(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success();
    }

    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("avatarUrl")@URL() String avatarUrl){
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        userService.updateAvatar(id, avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody UpdateDataVO updateDataVO){
        if(!StringUtils.hasLength(updateDataVO.getOldPwd()) || !StringUtils.hasLength(updateDataVO.getNewPwd()) && !StringUtils.hasLength(updateDataVO.getRePwd())){
            return Result.error("缺少必要参数");
        }

        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        String username = claims.get("username").toString();
        User user = userService.findByUserName(username);
        String encodeOldPwd = Md5Util.getMD5String(updateDataVO.getOldPwd());
        if (!user.getPassword().equalsIgnoreCase(encodeOldPwd)){
            return Result.error("旧密码不一致");
        }
        if(!updateDataVO.getNewPwd().equals(updateDataVO.getRePwd())){
            return Result.error("两次密码不一致");
        }

        userService.updatePwd(id, updateDataVO.getNewPwd());
        return Result.success();
    }

}

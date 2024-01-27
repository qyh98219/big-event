package com.github.vo;

import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

/**
 * @ClassName UserVO
 * @Description 传递给前端的用户数据
 * @Author qyh
 * @Date 2024/1/27 15:37
 * @Version 1.0
 **/
@Data
public class UserVO {
    private Integer id;//主键ID
    private String username;//用户名
    private String nickname;//昵称
    private String email;//邮箱
    private String userPic;//用户头像地址
    private LocalDateTime createTime;//创建时间
    private LocalDateTime updateTime;//更新时间
}

package com.github.service.impl;

import com.github.mapper.UserMapper;
import com.github.pojo.User;
import com.github.service.UserService;
import com.github.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author qyh
 * @Description 用户业务处理
 * @Date 2024/1/24 10:46
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Override
    public User findByUserName(String username) {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
        //加密处理
        String md5Password = Md5Util.getMD5String(password);
        //注册
        userMapper.add(username, md5Password);
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(Integer id, String avatarUrl) {
        userMapper.updateAvatar(id, avatarUrl);
    }

    @Override
    public void updatePwd(Integer id, String password) {
        userMapper.updatePwd(id, Md5Util.getMD5String(password));
    }
}

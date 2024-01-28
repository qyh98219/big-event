package com.github.service;

import com.github.pojo.User;

public interface UserService {
    User findByUserName(String username);
    void register(String username, String password);

    void update(User user);

    void updateAvatar(Integer id, String avatarUrl);

    void updatePwd(Integer id, String password);
}

package com.ray.service;

import com.ray.model.User;

/**
 * @author Ray
 * @date 2018/5/19 0019
 */
public interface UserService {

    void save(User user);

    void update(User user);

    User checkLogin(String userName, String userPwd);

    boolean checkRegister(String userName, String userPwd, String email);

    boolean updatePass(String newPass, String username);
}

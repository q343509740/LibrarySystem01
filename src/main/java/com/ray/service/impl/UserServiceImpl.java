package com.ray.service.impl;

import com.ray.dao.UserDao;
import com.ray.model.User;
import com.ray.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author Ray
 * @date 2018/5/19 0019
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public User checkLogin(String userName, String userPwd) {
        // 根据用户名实例化用户对象
        User user = userDao.selectUser(userName);
        if(user != null && user.getUserPwd().equals(userPwd)){
            return user;
        }
        return null;
    }

    @Override
    public boolean checkRegister(String userName, String userPwd, String email) {
        // 根据用户名实例化用户对象
        User user = userDao.selectUser(userName);
        if(user != null){
            return false;
        }else{
            User updateUser = new User();
            updateUser.setUserName(userName);
            updateUser.setUserPwd(userPwd);
            updateUser.setUserEmail(email);
            save(updateUser);
            return true;
        }
    }

    @Override
    public boolean updatePass(String newPass, String username) {
       userDao.updatePass(newPass,username);
       return true;
    }
}

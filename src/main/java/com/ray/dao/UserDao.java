package com.ray.dao;

import com.ray.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Ray
 * @date 2018/5/19 0019
 */
@Repository
public interface UserDao {

    void save(User user);

    void update(User user);

    User selectUser(@Param("userName") String userName);

    void updatePass(@Param("userPwd") String newPass,
                    @Param("userName") String userName);
}

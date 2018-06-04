package com.ray.model;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Ray
 * @date 2018/5/19 0019
 */
public class User {

    private Long id;
    private String userName;
    private String userEmail;
    private String userPwd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }
}

package com.ray.controller;

import com.ray.model.User;
import com.ray.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * @author Ray
 * @date 2018/5/19 0019
 */
@Controller //注册为控制器bean
public class UserController {

//    private Logger logger = Logger.getLogger(UserController.class);
    private static Logger logger = LogManager.getLogger(UserController.class);

    @Resource
    private UserService userService;

    @RequestMapping("login") //请求路径
    @ResponseBody //返回json数据
    public boolean login(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("username: " + username);
        System.out.println("password: " + password);

        //MD5加密(忽略)

        HttpSession session = request.getSession();
        User user = userService.checkLogin(username,password);
        if (user != null){
            session.setAttribute("username", user.getUserName());
            return true;
        }
        return false;
    }

    @RequestMapping("register")
    @ResponseBody
    public boolean register(HttpServletRequest request, HttpServletResponse response){
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String repassword = request.getParameter("repassword");
        String email = request.getParameter("email");

        HttpSession session = request.getSession();
        boolean rg = userService.checkRegister(username,password,email);
        if(rg){
            return true;
        }
        return false;
    }

    @RequestMapping("online")
    @ResponseBody
    public String online(HttpServletRequest request, HttpServletResponse response){
        Object user = request.getSession().getAttribute("username");

        if(user != null){
            System.out.println("username: " + user);
            return user.toString();
        }else{
            return null;
        }
    }

    @RequestMapping("updatePwd")
    @ResponseBody
    public boolean updatePwd(HttpServletRequest request, HttpServletResponse response){
        Object user = request.getSession().getAttribute("username");
        String password = request.getParameter("password");
        System.out.println("当前用户 " + user.toString() + " 的密码为: " + password);
        userService.updatePass(password, user.toString());
        request.getSession().removeAttribute("username");
        return true;
    }

    @RequestMapping("logout")
    @ResponseBody
    public boolean logout(HttpServletRequest request, HttpServletResponse response){
        Object user = request.getSession().getAttribute("username");

        if(user != null){
            request.getSession().removeAttribute("username");
            return true;
        }

        return false;
    }
}

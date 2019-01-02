package com.zy.controller;

import com.zy.entity.FmsUser;
import com.zy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器（接收请求并返回）
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    /**
     * 注册
     *
     * @param user
     * @return
     */
    @RequestMapping("/insertUser.do")
    @ResponseBody
    public Map<String, Object> insertUser(FmsUser user) {
        //封装返回map集合
        Map<String, Object> map = new HashMap<>();
        try {
            userService.insertUser(user);
            map.put("resCode", 10000);
            map.put("resMsg", "成功");
        } catch (Exception e) {
            map.put("resCode", 10001);
            map.put("resMsg", "失败");
        }
        return map;
    }

    /**
     * 登录
     *
     * @param userName
     * @param passWord
     * @return
     */
    @RequestMapping("/login.do")
    @ResponseBody
    public Map<String, Object> login(String userName, String passWord, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        try {
            //登录查询
            FmsUser user = userService.login(userName, passWord, request);
            //判断是否登录成功
            if (user == null) {
                map.put("resCode", 10001);
                map.put("resMsg", "用户名或密码错误");
            }
            map.put("resCode", 10000);
            map.put("resMsg", "登录成功");
        } catch (Exception e) {
            map.put("resCode", 10001);
            map.put("resMsg", "用户名或密码错误");
        }
        return map;
    }
}

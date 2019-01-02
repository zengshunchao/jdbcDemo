package com.zy.service;

import com.zy.entity.FmsUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 用户业务逻辑处理
 */
public interface IUserService {

    /**
     * 注册
     *
     * @param user
     */
    public void insertUser(FmsUser user);

    /**
     * 登录
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    public FmsUser login(String userName, String passWord, HttpServletRequest request);
}

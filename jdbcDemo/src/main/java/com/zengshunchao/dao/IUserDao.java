package com.zengshunchao.dao;

import com.zengshunchao.entity.User;

import java.util.List;

/**
 * @Author: zengshunchao
 * @Date: 2018/11/20 8:32
 */
public interface IUserDao {

    /**
     * 添加用户信息
     *
     * @param user
     */
    public void addUser(User user);

    /**
     * 修改密码
     *
     * @param id 用户id
     * @param pw 用户密码
     * @return 受影响的行数
     */
    public int updateUser(int id, String pw);

    /**
     * 获取用户列表
     *
     * @return list
     */
    public List<User> getUserList();

    /**
     * 删除用户信息
     *
     * @param id 用户id
     * @return
     */
    public int deleteUser(int id);
}

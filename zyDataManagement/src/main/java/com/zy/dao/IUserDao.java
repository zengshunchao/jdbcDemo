package com.zy.dao;

import com.zy.entity.FmsUser;
import org.springframework.stereotype.Repository;

/**
 * 用户持久接口
 */
@Repository
public interface IUserDao {

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
     * @return
     */
    public FmsUser login(String userName);
}

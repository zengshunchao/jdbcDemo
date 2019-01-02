package com.zy.service.impl;

import com.zy.dao.IUserDao;
import com.zy.entity.FmsUser;
import com.zy.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public void insertUser(FmsUser user) {
        FmsUser user1 = new FmsUser();
        user1.setUserName(user.getUserName());
        if (!user.getClassName().equals("")) {
            //密码--MD5加密
            user1.setPassWord(DigestUtils.md5DigestAsHex(user.getPassWord().getBytes()));
        }
        user1.setClassName(user.getClassName());
        user1.setGroupName(user.getGroupName());
        user1.setPhone(user.getPhone());
        user1.setRole(1);
        userDao.insertUser(user1);
    }

    @Override
    public FmsUser login(String userName, String passWord, HttpServletRequest request) {

        HttpSession session = request.getSession();
        //用户名、密码为空则直接返回null
        if (userName.equals("") || passWord.equals("")) {
            return null;
        }
        //根据用户名查询是否存在该用户
        FmsUser user = userDao.login(userName);
        //没有查询到用户直接返回
        if (user == null) {
            return null;
        }
        //判断密码是否正确
        if (user.getPassWord().equals(DigestUtils.md5DigestAsHex(passWord.getBytes()))) {
            //将登陆成功的状态保存在session中
            session.setAttribute("user", user);
            return user;
        }

        return null;
    }
}

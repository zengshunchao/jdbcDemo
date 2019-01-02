package com.zengshunchao.entity;

/**
 * @Author: zengshunchao
 * @Date: 2018/11/19 17:57
 */
public class User {

    /**
     * 用户id
     */
    private int id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 用户密码
     */
    private String passWord;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User(String userName, String passWord) {
        this.userName = userName;
        this.passWord = passWord;
    }

    public User() {
    }
}

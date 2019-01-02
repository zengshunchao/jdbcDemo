package com.zy.entity;

/**
 * 用户
 */
public class FmsUser {

    /**
     * id
     */
    private int id;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 班级名称
     */
    private String className;
    /**
     * 联系方式
     */
    private String phone;
    /**
     * 小组名称
     */
    private String groupName;
    /**
     * 角色（1表示学生，0表示管理员）
     */
    private Integer role;

    public FmsUser(String userName, String passWord, String className, String phone, String groupName, Integer role) {
        this.userName = userName;
        this.passWord = passWord;
        this.className = className;
        this.phone = phone;
        this.groupName = groupName;
        this.role = role;
    }

    public FmsUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}

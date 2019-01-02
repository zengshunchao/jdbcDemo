package com.zy.entity;

import java.util.Date;

/**
 * 查询使用实体
 */
public class FmsUserAndFile {

    /**
     * 资料id
     */
    private Integer id;
    /**
     * 资料名称
     */
    private String dataName;
    /**
     * ip
     */
    private String ipPath;
    /**
     * 上传者
     */
    private String userName;
    /**
     * 小组名称
     */
    private String groupName;
    /**
     * 上传时间
     */
    private String createDate;
    /**
     * 更新时间
     */
    private String updateDate;
    /**
     * 浏览次数
     */
    private int lookNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getIpPath() {
        return ipPath;
    }

    public void setIpPath(String ipPath) {
        this.ipPath = ipPath;
    }

    public String getUserName() {
        return userName;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getLookNumber() {
        return lookNumber;
    }

    public void setLookNumber(int lookNumber) {
        this.lookNumber = lookNumber;
    }

    public FmsUserAndFile(Integer id, String dataName, String ipPath, String userName, String groupName, String createDate, String updateDate, int lookNumber) {
        this.id = id;
        this.dataName = dataName;
        this.ipPath = ipPath;
        this.userName = userName;
        this.groupName = groupName;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.lookNumber = lookNumber;
    }

    public FmsUserAndFile() {
    }
}

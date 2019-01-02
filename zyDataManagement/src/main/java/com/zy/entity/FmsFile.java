package com.zy.entity;

import java.util.Date;

/**
 * 资料信息实体
 */
public class FmsFile {
    /**
     * id
     */
    private Integer id;
    /**
     * 上传人id
     */
    private Integer userId;
    /**
     * 上传的文件路径
     */
    private String filePath;
    /**
     * 上传文件名称
     */
    private String fileName;
    /**
     * 资料名称
     */
    private String dataName;
    /**
     * 资料简介
     */
    private String dataDesc;
    /**
     * ip地址
     */
    private String ipPath;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 更新时间
     */
    private Date updateDate;
    /**
     * 浏览次数
     */
    private int lookNumber;
    /**
     * 是否删除
     */
    private int deleted;

    public FmsFile(Integer userId, String filePath, String fileName, String dataName, String dataDesc, String ipPath, Date createDate, Date updateDate, int lookNumber, int deleted) {
        this.userId = userId;
        this.filePath = filePath;
        this.fileName = fileName;
        this.dataName = dataName;
        this.dataDesc = dataDesc;
        this.ipPath = ipPath;
        this.createDate = createDate;
        this.updateDate = updateDate;
        this.lookNumber = lookNumber;
        this.deleted = deleted;
    }

    public FmsFile() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    public String getIpPath() {
        return ipPath;
    }

    public void setIpPath(String ipPath) {
        this.ipPath = ipPath;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getLookNumber() {
        return lookNumber;
    }

    public void setLookNumber(int lookNumber) {
        this.lookNumber = lookNumber;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}

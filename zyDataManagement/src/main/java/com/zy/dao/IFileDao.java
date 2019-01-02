package com.zy.dao;

import com.zy.entity.FmsFile;
import com.zy.entity.FmsUserAndFile;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 资料持久层
 */
public interface IFileDao {
    /**
     * 添加资料
     *
     * @param info
     */
    int insertData(FmsFile info);

    /**
     * 修改文件简介
     *
     * @param id
     * @param dataDesc
     */
    int updateDataDesc(int id, String dataDesc);

    /**
     * 删除资料
     *
     * @param id
     */
    int deleteData(int id);

    FmsFile getFileById(Integer id);

    /**
     * 查询数据列表
     *
     * @return
     */
    List<FmsUserAndFile> getFileList(@Param("dataName") String dataName, @Param("groupName") String groupName);

    /**
     * 统计浏览次数
     */
    void countLookNum(Integer id);


}

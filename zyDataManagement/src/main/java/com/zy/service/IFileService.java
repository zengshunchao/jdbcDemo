package com.zy.service;

import com.zy.entity.FmsFile;
import com.zy.entity.FmsUserAndFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @Author: Witslan
 * @Desc: ${desc}
 * @CreateTime: 2018-11-29 10:44
 */
public interface IFileService {

    FmsFile uploadFile(FmsFile file, MultipartFile uploadFile, HttpServletRequest request);

    Map<String, Object> downloadFile(Integer fileId, HttpServletResponse response);

    List<FmsUserAndFile> getFileList(String dataName, String groupName);

    FmsFile getFileById(Integer id);

    int deleteData(int id);

    void countLookNum(Integer id);

    void updateDesc(Integer id, String dataDesc);
}

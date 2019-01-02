package com.zy.service.impl;

import com.zy.dao.IFileDao;
import com.zy.entity.FmsFile;
import com.zy.entity.FmsUser;
import com.zy.entity.FmsUserAndFile;
import com.zy.service.IFileService;
import com.zy.service.IUploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.InetAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Witslan
 * @Desc: ${desc}
 * @CreateTime: 2018-11-29 10:45
 */
@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private IUploadService uploadService;
    @Autowired
    private IFileDao fileDao;

    @Override
    public List<FmsUserAndFile> getFileList(String dataName, String groupName) {
        return fileDao.getFileList(dataName, groupName);
    }

    @Override
    public FmsFile uploadFile(FmsFile file, MultipartFile uploadFile, HttpServletRequest request) {
        try {
            FmsUser user = (FmsUser) request.getSession().getAttribute("user");
            Map<String, String> map = uploadService.uploadFile(uploadFile);
            if (map == null) {
                return null;
            }
            Date date = new Date();
            //获取本机ip地址
            String ipPath = InetAddress.getLocalHost().getHostAddress();
            file.setUserId(user.getId());
            file.setCreateDate(date);
            file.setUpdateDate(date);
            file.setIpPath(ipPath);
            file.setFileName(map.get("fileName"));
            file.setFilePath(map.get("filePath"));
            file.setDeleted(1);
            file.setLookNumber(0);
            fileDao.insertData(file);
            return file;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Map<String, Object> downloadFile(Integer fileId, HttpServletResponse response) {
        InputStream is = null;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        Map<String, Object> res = new HashMap<>();
        try {
            FmsFile fmsFile = fileDao.getFileById(fileId);
            if (fmsFile == null) {
                res.put("error", "文件没有找到");
                return res;
            }
            response.reset();
            response.setContentType("application/octet-stream");
            response.setHeader("Content-disposition", "attachment; filename=" +
                    new String(fmsFile.getFileName().getBytes("utf-8"), "ISO8859-1"));
            File file = new File(fmsFile.getFilePath());
            is = new FileInputStream(file);
            bis = new BufferedInputStream(is);
            bos = new BufferedOutputStream(response.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
            bos.flush();
            res.put("error", "文件下载成功");
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            res.put("error", "文件下载异常");
            return res;
        } finally {
            try {
                bis.close();
                bos.close();
                is.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public FmsFile getFileById(Integer id) {
        try {
            return fileDao.getFileById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteData(int id) {
        return fileDao.deleteData(id);
    }

    @Override
    public void countLookNum(Integer id) {
        fileDao.countLookNum(id);
    }

    @Override
    public void updateDesc(Integer id, String dataDesc) {
        fileDao.updateDataDesc(id, dataDesc);
    }
}

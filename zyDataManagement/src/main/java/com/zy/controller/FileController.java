package com.zy.controller;

import com.zy.entity.FmsFile;
import com.zy.entity.FmsUserAndFile;
import com.zy.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Witslan
 * @Desc: ${desc}
 * @CreateTime: 2018-11-29 10:11
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private IFileService fileService;

    @PostMapping("/upload")
    public FmsFile fileUpload(FmsFile file, @RequestParam("file") MultipartFile uploadfile, HttpServletRequest request) {
        try {
            FmsFile fmsFile = fileService.uploadFile(file, uploadfile, request);
            return fmsFile;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/download")
    public Map<String, Object> fileDownload(Integer fileId, HttpServletResponse response) {
        try {
            Map<String, Object> res = fileService.downloadFile(fileId, response);
            return res;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 查询列表
     *
     * @return
     */
    @RequestMapping("/getFileList")
    public Map<String, Object> getFileList(String dataName, String groupName) {

        Map<String, Object> map = new HashMap<>();
        try {
            List<FmsUserAndFile> list = fileService.getFileList(dataName, groupName);
            if (list.size() == 0) {
                map.put("resCode", 10002);
                map.put("resMsg", "未查询到数据");
            }
            map.put("resCode", 10000);
            map.put("resMsg", "成功");
            map.put("resList", list);
        } catch (Exception e) {
            map.put("resCode", 10001);
            map.put("resMsg", "失败");
            return null;
        }
        return map;
    }

    @RequestMapping("/getById")
    public ModelAndView getFileById(Integer id) {

        ModelAndView mv = new ModelAndView("forward:/dataOperate.jsp");
        FmsFile fmsFile = null;
        try {
            fmsFile = fileService.getFileById(id);
            fileService.countLookNum(id);
            mv.addObject("file", fmsFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping("/deleteById")
    public Map<String, Object> deleteById(int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            fileService.deleteData(id);
            map.put("resCode", 10000);
            map.put("resMsg", "删除成功");
        } catch (Exception e) {
            map.put("resCode", 10001);
            map.put("resMsg", "删除失败");
        }
        return map;
    }

    @RequestMapping("/updateDesc")
    public Map<String, Object> updateDesc(String dataDesc, int id) {
        Map<String, Object> map = new HashMap<>();
        try {
            fileService.updateDesc(id, dataDesc);
            map.put("resCode", 10000);
            map.put("resMsg", "修改成功");
        } catch (Exception e) {
            map.put("resCode", 10001);
            map.put("resMsg", "修改失败");
        }
        return map;
    }
}

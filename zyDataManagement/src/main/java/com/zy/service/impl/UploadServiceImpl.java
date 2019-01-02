package com.zy.service.impl;

import com.zy.service.IUploadService;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class UploadServiceImpl implements IUploadService {

    private static final String BASE_PATH = "/upload";

    @Override
    public Map<String, String> uploadFile(MultipartFile uploadFile) {
        try {
            //取原始文件名
            String oldName = uploadFile.getOriginalFilename();
            //生成新文件名
            String newName = String.valueOf(System.currentTimeMillis() / 100);
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //文件路径
            String filePath = new DateTime().toString("/yyyy/MM/dd");
            filePath = BASE_PATH + filePath;
            File floder = new File(filePath);
            //判断目录是否存在
            System.out.println(floder.getParentFile());
            if (!floder.exists()) {
                floder.mkdirs();
            }
            File file = new File(filePath + "/" + newName);
            uploadFile.transferTo(file);
            Map<String, String> map = new HashMap<>(2);
            map.put("fileName", oldName);
            map.put("filePath", filePath + "/" + newName);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

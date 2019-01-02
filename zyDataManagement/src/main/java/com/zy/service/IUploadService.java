package com.zy.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IUploadService {

    Map<String, String> uploadFile(MultipartFile uploadFile);
}

package com.zy.test;

import com.zy.dao.IFileDao;
import com.zy.entity.FmsUserAndFile;
import com.zy.service.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


public class Test extends BaseTest {

    @Autowired
    private IFileService fileService;

    @org.junit.Test
    public void test() {
        List<FmsUserAndFile> list = fileService.getFileList("", "");

        for (FmsUserAndFile fms : list) {
            System.out.println(fms.getGroupName() + "\t" + fms.getDataName());
        }

        System.out.println(list.size());
    }
}

package com.zy.test;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;

@RunWith(SpringJUnit4ClassRunner.class)   //表示继承了SpringJUnit4ClassRunner类
//告诉junit spring配置文件的位置
@ContextConfiguration(locations = {"classpath*:spring-dao.xml", "classpath*:spring-service.xml"})
public class BaseTest {
}

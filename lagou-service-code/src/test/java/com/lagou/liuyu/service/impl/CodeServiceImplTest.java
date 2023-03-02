package com.lagou.liuyu.service.impl;

import com.lagou.liuyu.CodeApplication;
import com.lagou.liuyu.service.ICodeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author LiuYu
 * @date 2022/5/15 16:27
 */
@SpringBootTest(classes = {CodeApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CodeServiceImplTest {

    @Autowired
    private ICodeService codeService;

    @Test
    public void createCode() {
        codeService.createCode("1225313145@qq.com");
    }

    @Test
    public void validateCode() {
        final int result = codeService.validateCode("1225313145@qq.com", "804959");
        System.out.println("result = " + result);
    }
}
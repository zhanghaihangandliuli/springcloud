package com.lagou.liuyu.service.impl;

import com.lagou.liuyu.EmailApplication;
import com.lagou.liuyu.service.IEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author LiuYu
 * @date 2022/5/15 11:27
 */
@SpringBootTest(classes = {EmailApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class EmailServiceImplTest {

    @Autowired
    private IEmailService emailService;

    @Test
    public void sendEmailByCode() {
        emailService.sendEmailByCode("1225313145@qq.com", "123456");
    }
}
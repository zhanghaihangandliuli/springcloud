package com.lagou.liuyu.service.impl;

import com.lagou.liuyu.UserApplication;
import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Locale;

import static org.junit.Assert.*;

/**
 * @author LiuYu
 * @date 2022/5/16 21:25
 */
@SpringBootTest(classes = {UserApplication.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Autowired
    private IUserService userService;
    @Autowired
    private HttpServletResponse response;

    private static final String EMAIL = "1225313145@qq.com";
    private static final String PASS_WORD = "123456";

    @Test
    public void register() {
        final ResultDTO register = userService.register(EMAIL, PASS_WORD, "804959", response);
        System.out.println("register = " + register);
    }

    @Test
    public void isRegistered() {
        final boolean registered = userService.isRegistered(EMAIL);
        System.out.println("registered = " + registered);
    }

    @Test
    public void login() {
        final String login = userService.login(EMAIL, PASS_WORD, response);
        System.out.println("login = " + login);
    }

    @Test
    public void userInfo() {
        final String userInfo = userService.userInfo("e5f2a8136ece06ad8fddc2760443d666");
        System.out.println("userInfo = " + userInfo);
    }
}
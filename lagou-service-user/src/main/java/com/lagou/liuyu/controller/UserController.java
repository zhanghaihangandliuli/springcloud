package com.lagou.liuyu.controller;

import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.service.IUserService;
import com.lagou.liuyu.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author LiuYu
 * @date 2022/5/15 09:50
 */
@RefreshScope
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping("/register/{email}/{passWord}/{code}")
    public ResultDTO register(@PathVariable("email") String email,
                              @PathVariable("passWord") String passWord,
                              @PathVariable("code") String code,
                              HttpServletResponse response){
        return userService.register(email, passWord, code, response);
    }

    @GetMapping("/isRegistered/{email}")
    public ResultDTO isRegistered(@PathVariable("email") String email){
        return ResultUtils.SUCCESS(userService.isRegistered(email));
    }

    @GetMapping("/login/{email}/{passWord}")
    public ResultDTO login(@PathVariable("email") String email, @PathVariable("passWord") String passWord,
                           HttpServletResponse response){
        return ResultUtils.SUCCESS(userService.login(email, passWord, response));
    }

    @GetMapping("/info/{token}")
    public ResultDTO userInfo(@PathVariable("token") String token){
        return ResultUtils.SUCCESS(userService.userInfo(token));
    }

}



package com.lagou.liuyu.service;

import com.lagou.liuyu.dto.ResultDTO;

import javax.servlet.http.HttpServletResponse;

/**
 * @author LiuYu
 * @date 2022/5/15 22:01
 */
public interface IUserService {

    /**
     * 注册接⼝，true成功，false失败
     * @param email
     * @param passWord
     * @param code
     */
    ResultDTO register(String email, String passWord, String code, HttpServletResponse response);

    /**
     * 是否已注册，根据邮箱判断,true代表已经注册过，
     * false代表尚未注册
     * @param email
     * @return
     */
    boolean isRegistered(String email);

    /**
     * 登录接⼝，验证⽤户名密码合法性，根据⽤户名和
     * 密码⽣成token，token存⼊数据库，并写⼊cookie
     * 中，登录成功返回邮箱地址，重定向到欢迎⻚
     * @param email
     * @param passWord
     * @return
     */
    String login(String email, String passWord, HttpServletResponse response);

    /**
     * 根据token查询⽤户登录邮箱接⼝
     * @param token
     * @return
     */
    String userInfo(String token);

}

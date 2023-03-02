package com.lagou.liuyu.service;

import com.lagou.liuyu.dto.ResultDTO;

/**
 * @author LiuYu
 * @date 2022/5/15 11:38
 */
public interface ICodeService {

    /**
     * ⽣成验证码并发送到对应邮箱，成功true，失败 false
     * @param toEmail
     */
    ResultDTO createCode(String toEmail);

    /**
     * 校验验证码是否正确，0正确1错误2超时
     * @param toEmail
     * @param code
     * @return
     */
    int validateCode(String toEmail, String code);

}

package com.lagou.liuyu.service;

/**
 * @author LiuYu
 * @date 2022/5/15 11:11
 */
public interface IEmailService {

    /**
     * 将验证码发送到指定的邮件接收人
     * @param toEmail
     * @param code
     */
    void sendEmailByCode(String toEmail, String code);

}

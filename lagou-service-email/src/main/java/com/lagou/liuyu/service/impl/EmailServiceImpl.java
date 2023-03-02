package com.lagou.liuyu.service.impl;

import com.lagou.liuyu.service.IEmailService;
import com.lagou.liuyu.utils.SendMailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author LiuYu
 * @date 2022/5/15 11:12
 */
@Service
public class EmailServiceImpl implements IEmailService {

    @Autowired
    private SendMailUtil sendMailUtil;

    @Override
    public void sendEmailByCode(String toEmail, String code) {
        String title = "注册用户验证码";
        String msg = "当前注册的验证码为:【" + code + "】,10分钟内有效,请不要泄露给其他人!";
        sendMailUtil.sendSimpleMail(toEmail, title, msg);
    }
}

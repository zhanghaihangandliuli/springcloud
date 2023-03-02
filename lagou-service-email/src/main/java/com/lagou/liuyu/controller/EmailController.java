package com.lagou.liuyu.controller;

import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.enums.BaseEnum;
import com.lagou.liuyu.utils.ResultUtils;
import com.lagou.liuyu.service.IEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送邮件服务
 *
 * @author LiuYu
 * @date 2022/5/15 10:38
 */
@RestController
@RequestMapping("/api/email")
public class EmailController {

    @Autowired
    private IEmailService emailService;
    @PostMapping("/{toEmail}/{code}")
    public ResultDTO sendEmailByCode(@PathVariable("toEmail") String toEmail, @PathVariable("code") String code){
        try {
            emailService.sendEmailByCode(toEmail, code);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.ERROR(BaseEnum.SEND_CODE_ERROR);
        }
        return ResultUtils.SUCCESS();
    }

}

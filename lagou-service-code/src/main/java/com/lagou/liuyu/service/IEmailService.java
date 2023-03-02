package com.lagou.liuyu.service;

import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.service.impl.EmailServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author LiuYu
 * @date 2022/5/15 15:52
 */
@FeignClient(value = "lagou-service-email", fallback = EmailServiceFallBack.class, path = "/api/email")
public interface IEmailService {

    @PostMapping("/{toEmail}/{code}")
    ResultDTO sendEmailByCode(@PathVariable("toEmail") String toEmail, @PathVariable("code") String code);

}

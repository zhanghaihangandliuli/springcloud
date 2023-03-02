package com.lagou.liuyu.service;

import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.service.impl.CodeServiceFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author LiuYu
 * @date 2022/5/15 22:15
 */
@FeignClient(value = "lagou-service-code", fallback = CodeServiceFallBack.class, path = "/api/code")
public interface ICodeService {

    @PostMapping("/validate/{toEmail}/{code}")
    ResultDTO validateCode(@PathVariable("toEmail")String toEmail, @PathVariable("code")String code);

}

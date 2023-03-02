package com.lagou.liuyu.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author LiuYu
 * @date 2022/5/22 09:55
 */
@FeignClient(value = "lagou-service-user", fallback = UserServiceImpl.class, path = "/api/user")
public interface IUserService {

    @GetMapping("/user/info/{token}")
    Object userInfo(@PathVariable("token") String token);

}

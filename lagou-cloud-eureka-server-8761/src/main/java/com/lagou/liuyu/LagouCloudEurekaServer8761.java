package com.lagou.liuyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author LiuYu
 * @date 2022/5/14 18:15
 */
@SpringBootApplication
@EnableEurekaServer
public class LagouCloudEurekaServer8761 {
    public static void main(String[] args) {
        SpringApplication.run(LagouCloudEurekaServer8761.class, args);
    }
}

package com.lagou.liuyu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author LiuYu
 * @date 2022/5/14 22:02
 */
@SpringBootApplication
@EnableDiscoveryClient
@EntityScan("com.lagou.liuyu")
public class EmailApplication {
    public static void main(String[] args) {
        SpringApplication.run(EmailApplication.class, args);
    }
}

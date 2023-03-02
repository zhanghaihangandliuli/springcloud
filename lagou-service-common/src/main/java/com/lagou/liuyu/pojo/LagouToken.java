package com.lagou.liuyu.pojo;

import lombok.Data;

import javax.persistence.*;

/**
 * lagou_token
 * @author LiuYu
 * @date 2022-05-14
 */
@Entity
@Data
@Table(name="lagou_token")
public class LagouToken {

    /**
    * ⾃增主键
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    /**
    * 邮箱地址
    */
    @Column(name="email")
    private String email;

    /**
    * 令牌
    */
    @Column(name="token")
    private String token;

    public LagouToken() {
    }

}
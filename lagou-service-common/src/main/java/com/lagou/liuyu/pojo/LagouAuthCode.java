package com.lagou.liuyu.pojo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * lagou_auth_code
 * @author LiuYu
 * @date 2022-05-14
 */
@Entity
@Data
@Table(name="lagou_auth_code")
public class LagouAuthCode {

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
     * 验证码
     */
    @Column(name="code")
    private String code;

    /**
     * 创建时间
     */
    @Column(name="createtime")
    private Date createTime;

    /**
     * 过期时间
     */
    @Column(name="expiretime")
    private Date expireTime;

    public LagouAuthCode() {
    }

}
package com.lagou.liuyu.dao;

import com.lagou.liuyu.pojo.LagouAuthCode;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LiuYu
 * @date 2022/5/15 11:51
 */
public interface LagouAuthCodeDao extends JpaRepository<LagouAuthCode, Integer> {
}

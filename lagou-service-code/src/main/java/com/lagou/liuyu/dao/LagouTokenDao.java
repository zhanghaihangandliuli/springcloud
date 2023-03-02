package com.lagou.liuyu.dao;

import com.lagou.liuyu.pojo.LagouToken;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author LiuYu
 * @date 2022/5/16 09:27
 */
public interface LagouTokenDao extends JpaRepository<LagouToken, Integer> {
}

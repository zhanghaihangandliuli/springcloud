package com.lagou.liuyu.service.impl;

import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.service.ICodeService;
import com.lagou.liuyu.utils.ResultUtils;
import org.springframework.stereotype.Component;

/**
 * @author LiuYu
 * @date 2022/5/15 22:17
 */
@Component
public class CodeServiceFallBack implements ICodeService {

    @Override
    public ResultDTO validateCode(String toEmail, String code) {
        return ResultUtils.SUCCESS(0);
    }
}

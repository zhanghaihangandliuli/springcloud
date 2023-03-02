package com.lagou.liuyu.service.impl;

import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.utils.ResultUtils;
import com.lagou.liuyu.service.IEmailService;
import org.springframework.stereotype.Component;

/**
 * @author LiuYu
 * @date 2022/5/15 15:54
 */
@Component
public class EmailServiceFallBack implements IEmailService {

    @Override
    public ResultDTO sendEmailByCode(String toEmail, String code) {
        return ResultUtils.SUCCESS();
    }

}

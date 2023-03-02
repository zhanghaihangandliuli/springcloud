package com.lagou.liuyu.service.impl;

import com.lagou.liuyu.dao.LagouTokenDao;
import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.enums.BaseEnum;
import com.lagou.liuyu.pojo.LagouAuthCode;
import com.lagou.liuyu.dao.LagouAuthCodeDao;
import com.lagou.liuyu.pojo.LagouToken;
import com.lagou.liuyu.service.ICodeService;
import com.lagou.liuyu.service.IEmailService;
import com.lagou.liuyu.utils.ResultUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Random;

/**
 * @author LiuYu
 * @date 2022/5/15 11:49
 */
@Service
public class CodeServiceImpl implements ICodeService {

    @Autowired
    private LagouAuthCodeDao lagouAuthCodeDao;
    @Autowired
    private IEmailService emailService;
    @Autowired
    private LagouTokenDao lagouTokenDao;

    @Override
    public ResultDTO createCode(String toEmail) {

        final LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(toEmail);
        final List<LagouToken> all = lagouTokenDao.findAll(Example.of(lagouToken));
        if(all.size() > 0){
            return ResultUtils.ERROR(BaseEnum.REGISTER_USER_NOT_NULL);
        }

        // 随机生成一个6位数的验证码，10分钟内有效
        Random random = new Random();
        final int randomCode = random.nextInt(1000000);

        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setEmail(toEmail);
        lagouAuthCode.setCode(String.valueOf(randomCode));
        lagouAuthCode.setCreateTime(new Date());
        lagouAuthCode.setExpireTime(DateUtils.addMinutes(new Date(), 10));
        lagouAuthCodeDao.save(lagouAuthCode);

        // 发送验证码至邮件
        emailService.sendEmailByCode(toEmail, String.valueOf(randomCode));
        return ResultUtils.SUCCESS();
    }

    @Override
    public int validateCode(String toEmail, String code) {
        LagouAuthCode lagouAuthCode = new LagouAuthCode();
        lagouAuthCode.setEmail(toEmail);
        lagouAuthCode.setCode(code);
        Example<LagouAuthCode> example = Example.of(lagouAuthCode);
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");

        final List<LagouAuthCode> all = lagouAuthCodeDao.findAll(example, sort);
        if(CollectionUtils.isEmpty(all)){
            // 当前email没有查询到对应的code，则为验证码错误
            return 1;
        }

        final LagouAuthCode lac = all.get(0);
        final Date expireTime = lac.getExpireTime();
        if(new Date().after(expireTime)){
            // 验证码超时，超过了10分钟
            return 2;
        }

        return 0;
    }
}

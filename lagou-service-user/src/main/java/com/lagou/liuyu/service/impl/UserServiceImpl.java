package com.lagou.liuyu.service.impl;

import com.lagou.liuyu.dao.LagouTokenDao;
import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.enums.BaseEnum;
import com.lagou.liuyu.pojo.LagouToken;
import com.lagou.liuyu.service.ICodeService;
import com.lagou.liuyu.service.IUserService;
import com.lagou.liuyu.utils.ResultUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

/**
 * @author LiuYu
 * @date 2022/5/15 22:04
 */
@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private ICodeService codeService;
    @Autowired
    private LagouTokenDao lagouTokenDao;

    /**
     * 注册接⼝，true成功，false失败
     *
     * @param email
     * @param passWord
     * @param code
     */
    @Override
    public ResultDTO register(String email, String passWord, String code, HttpServletResponse response) {
        // 验证验证码是否正确
        final ResultDTO resultDTO = codeService.validateCode(email, code);
        // 验证码服务异常
        if(!Objects.equals(BaseEnum.SUCCESS.code(), resultDTO.getCode())){
            return ResultUtils.ERROR(BaseEnum.REGISTER_ERROR);
        }
        // 验证码错误
        if(Objects.equals(resultDTO.getData(), 1)){
            return ResultUtils.ERROR(BaseEnum.VALIDATE_CODE_ERROR);
        }
        // 验证码超时，超过10分钟
        if(Objects.equals(resultDTO.getData(), 2)){
            return ResultUtils.ERROR(BaseEnum.VALIDATE_CODE_TIMEOUT);
        }

        final LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);
        final List<LagouToken> all = lagouTokenDao.findAll(Example.of(lagouToken));
        if(all.size() > 0){
            return ResultUtils.ERROR(BaseEnum.REGISTER_USER_NOT_NULL);
        }

        // 验证码正确，开始注册账户，生成token令牌
        final String token = DigestUtils.md5Hex(email+passWord);
        LagouToken lagouToken1 = new LagouToken();
        lagouToken1.setToken(token);
        lagouToken1.setEmail(email);
        lagouTokenDao.save(lagouToken1);

        Cookie cookie = new Cookie("token", token);
        response.addCookie(cookie);

        return ResultUtils.SUCCESS();
    }

    /**
     * 是否已注册，根据邮箱判断,true代表已经注册过，
     * false代表尚未注册
     *
     * @param email
     * @return
     */
    @Override
    public boolean isRegistered(String email) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setEmail(email);

        final Optional<LagouToken> one = lagouTokenDao.findOne(Example.of(lagouToken));
        return one.isPresent();
    }

    /**
     * 登录接⼝，验证⽤户名密码合法性，根据⽤户名和
     * 密码⽣成token，token存⼊数据库，并写⼊cookie
     * 中，登录成功返回邮箱地址，重定向到欢迎⻚
     *
     * @param email
     * @param passWord
     * @return
     */
    @Override
    public String login(String email, String passWord, HttpServletResponse response) {

        final String token = DigestUtils.md5Hex(email+passWord);
        LagouToken lagouToken = new LagouToken();
        lagouToken.setToken(token);
        lagouToken.setEmail(email);

        final Optional<LagouToken> one = lagouTokenDao.findOne(Example.of(lagouToken));
        if(one.isPresent()){
            Cookie cookie = new Cookie("token", token);
            cookie.setPath("/");
            cookie.setMaxAge(36000);
            response.addCookie(cookie);
            return email;
        }

        return null;
    }

    /**
     * 根据token查询⽤户登录邮箱接⼝
     *
     * @param token
     * @return
     */
    @Override
    public String userInfo(String token) {
        LagouToken lagouToken = new LagouToken();
        lagouToken.setToken(token);

        final Optional<LagouToken> one = lagouTokenDao.findOne(Example.of(lagouToken));
        return one.orElse(new LagouToken()).getEmail();
    }
}

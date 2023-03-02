package com.lagou.liuyu.enums;

/**
 * 异常的通用错误信息枚举类
 *
 * @author LiuYu
 * @date 2022/05/15 23:04
 * @version 1.0
 */
public enum BaseEnum implements CodeItem{
    //成功
    SUCCESS("000000","成功"),
    FAIL("000001", "未知的系统异常"),

    //拦截器报错枚举
    LOGIN_ERROR("0001","用户未登陆"),

    //登陆枚举
    LOGIN_USER_PWD_NULL("0002","用户名或密码为空"),
    LOGIN_USER_NULL("0003","用户名不存在"),
    LOGIN_USER_ERROR("0004","用户名或密码不正确,剩余登陆次数为【%s】"),
    LOGIN_USER_NUM("0005","该用户失败次数已达5次，请次日再试"),
    USER_FROZEN_NUM("0006","该用户已被冻结，请次日再试"),
    ACHIEVE_MAX_NUM("0007","当前账号已达最大登陆上限【%s】人"),
    UPDATE_ERROR("0008","更新数据库失败"),

    //注册枚举
    REGISTER_ERROR("0010","注册失败"),
    REGISTER_USER_NOT_NULL("0011","用户名已存在"),
    SEND_CODE_ERROR("0012","验证码发送异常"),
    CREATE_CODE_ERROR("0013","验证码获取异常"),
    VALIDATE_CODE_EXCEPTION("0014","验证码验证异常"),
    VALIDATE_CODE_ERROR("0015","验证码错误"),
    VALIDATE_CODE_TIMEOUT("0016","验证码超时"),


    ;

    /**
     * 编码
     */
    private String code;

    /**
     * 错误信息
     */
    private String msg;

    BaseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String code(){
        return this.code;
    }

    public String msg(){return this.msg;}

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {

    }

    @Override
    public String getMsg() {
        return msg;
    }

    @Override
    public void setMsg(String msg) {

    }
}

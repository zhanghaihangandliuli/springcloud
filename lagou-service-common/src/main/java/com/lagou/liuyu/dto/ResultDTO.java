package com.lagou.liuyu.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 *  返回结果实体(这里使用Object，不限制data的类型)
 * @author LiuYu
 * @date 2022/05/15 23:04
 * @version 1.0
 */
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResultDTO implements Serializable {

    private static final long serialVersionUID = -8765194837207655975L;

    /**错误码*/
    private String code;

    /**提示信息*/
    private String msg;

    /**具体内容*/
    private Object data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultDTO{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}

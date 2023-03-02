package com.lagou.liuyu.utils;

import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.enums.BaseEnum;
import com.lagou.liuyu.enums.CodeItem;

/**
 *  返回值类型的工具类
 * @author LiuYu
 * @version 1.0
 */
public class ResultUtils {

    /**
     *  成功返回数据的格式
     */
    public static ResultDTO SUCCESS(Object object){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(BaseEnum.SUCCESS.code());
        resultDTO.setMsg(BaseEnum.SUCCESS.msg());
        resultDTO.setData(object);
        return resultDTO;
    }

    /**
     * 空参的成功返回数据的格式
     */
    public static ResultDTO SUCCESS(){
        return SUCCESS(null);
    }

    /**
     *  返回数据失败的格式
     */
    public static ResultDTO ERROR(String code,String msg){
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setCode(code);
        resultDTO.setMsg(msg);
        return resultDTO;
    }

    /**
     * 返回数据失败的格式
     */
    public static ResultDTO ERROR(CodeItem codeItem){
        return ERROR(codeItem.getCode(), codeItem.getMsg());
    }

    /**
     *  返回数据失败的格式
     */
    public static ResultDTO ERROR(){
        return ERROR(BaseEnum.FAIL.code(), BaseEnum.FAIL.msg());
    }
}

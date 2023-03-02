package com.lagou.liuyu.controller;

import com.lagou.liuyu.dto.ResultDTO;
import com.lagou.liuyu.enums.BaseEnum;
import com.lagou.liuyu.utils.ResultUtils;
import com.lagou.liuyu.service.ICodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiuYu
 * @date 2022/5/15 11:37
 */
@RestController
@RequestMapping("/api/code")
public class CodeController {

    @Autowired
    private ICodeService codeService;

    @PostMapping("/create/{toEmail}")
    public ResultDTO createCode(@PathVariable("toEmail") String toEmail){
        try {
            return codeService.createCode(toEmail);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.ERROR(BaseEnum.CREATE_CODE_ERROR);
        }
    }

    @PostMapping("/validate/{toEmail}/{code}")
    public ResultDTO validateCode(@PathVariable("toEmail")String toEmail, @PathVariable("code")String code){
        final int result;
        try {
            result = codeService.validateCode(toEmail, code);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtils.ERROR(BaseEnum.VALIDATE_CODE_EXCEPTION);
        }
        return ResultUtils.SUCCESS(result);
    }

}

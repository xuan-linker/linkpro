package com.xlccc.service;

import com.xlccc.common.api.CommonResult;

/**
 * @author Linker
 * @date 2021/1/28 11:06
 * @description：会员管理Service
 */
public interface UmsMemberService {

    /**
     * 生成验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}

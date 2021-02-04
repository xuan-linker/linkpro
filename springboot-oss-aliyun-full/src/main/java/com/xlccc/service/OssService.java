package com.xlccc.service;

import com.xlccc.dto.OssCallbackResult;
import com.xlccc.dto.OssPolicyResult;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Linker
 * @date 2021/2/4 19:22
 * @description：oss上传管理Service
 */
public interface OssService {
    /**
     * oss上传策略生成
     */
    OssPolicyResult policy();

    /**
     * oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}

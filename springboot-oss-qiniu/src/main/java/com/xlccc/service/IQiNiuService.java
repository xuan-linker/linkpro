package com.xlccc.service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;

import java.io.File;

public interface IQiNiuService {
    /**
     * 七牛云上传文件
     *
     * @param file 文件
     * @return 七牛上传Response
     * @throws QiniuException 七牛异常
     */
    Response uploadFile(File file) throws QiniuException;
}

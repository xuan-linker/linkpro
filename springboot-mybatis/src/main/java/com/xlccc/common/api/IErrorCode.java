package com.xlccc.common.api;

/**
 * @author Linker
 * @date 2021/1/27 22:42
 * @description：封装API的错误码
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}

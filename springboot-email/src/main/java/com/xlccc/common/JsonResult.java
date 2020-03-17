package com.xlccc.common;

import lombok.Data;

/**
 * @Author Linker
 * @Date 2020/3/17 9:01 下午
 * @Version 1.0
 */
@Data
public class JsonResult<T> {


    private int code;
    private String msg;
    private T data;


    public JsonResult() {
        this.code = 0;
        this.msg = "success";
    }


    public JsonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public JsonResult(T data) {
        this.data = data;
        this.code = 0;
        this.msg = "success";
    }


    public JsonResult(T data, String msg) {
        this.data = data;
        this.code = 0;
        this.msg = msg;
    }

}

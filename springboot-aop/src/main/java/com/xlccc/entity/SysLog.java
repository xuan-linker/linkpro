package com.xlccc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Linker
 * @date 2020/11/3 16:51
 * @description：
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog {

    private String id;
    private String className;
    private String methodName;
    private String params;
    private Long execTime;
    private String remark;
    private String createDate;
    private String url;
    private String ip;
    private String httpMethod;
}

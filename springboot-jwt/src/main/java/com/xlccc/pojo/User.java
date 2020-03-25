package com.xlccc.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author Linker
 * @Date 2020/3/25 9:26 上午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class User {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String userName;
    private String name;
    private String password;
}

package com.xlccc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Linker
 * @Date 2020/3/27 7:42 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
    private Integer pid ;
    private String name ;
    private String url ;
}

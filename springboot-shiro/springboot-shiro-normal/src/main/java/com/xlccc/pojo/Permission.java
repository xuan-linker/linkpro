package com.xlccc.pojo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Linker
 * @Date 2020/2/9 0:28
 * @Version 1.0
 */
@Data
public class Permission {
    private Integer pid ;
    private String permission;
    private Set<Role> roles = new HashSet<>();
}

package com.xlccc.pojo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Linker
 * @Date 2020/3/21 5:16 下午
 * @Version 1.0
 */
@Data
public class Role {
    private Integer rid  ;
    private String rname ;
    private Set<User> users = new HashSet<>();
    private Set<Permission> permissions = new HashSet<>();
}

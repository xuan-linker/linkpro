package com.xlccc.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Linker
 * @Date 2020/3/27 7:42 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer uid ;
    private String username ;
    private String password ;
    private Set<Role> roles = new HashSet<>();
}

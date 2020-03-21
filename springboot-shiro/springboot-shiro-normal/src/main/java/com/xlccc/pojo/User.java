package com.xlccc.pojo;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class User {
    private Integer uid ;
    private String uname ;
    private String upwd ;
    private String salt ;
    private Set<Role> roles = new HashSet<>();
}

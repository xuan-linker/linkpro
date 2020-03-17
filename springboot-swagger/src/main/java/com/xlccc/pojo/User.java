package com.xlccc.pojo;

import io.swagger.annotations.ApiModel;

/**
 * @Author Linker
 * @Date 2020/3/17 10:49 下午
 * @Version 1.0
 */
@ApiModel(description = "用户实体")
public class User {
    private Long Id ;
    private String name ;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

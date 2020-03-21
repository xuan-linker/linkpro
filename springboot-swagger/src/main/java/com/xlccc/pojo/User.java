package com.xlccc.pojo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Linker
 * @Date 2020/3/17 10:49 下午
 * @Version 1.0
 */
@Data
@Getter
@Setter
@ApiModel(description = "用户实体")
public class User {
    private Long Id ;
    private String name ;
}

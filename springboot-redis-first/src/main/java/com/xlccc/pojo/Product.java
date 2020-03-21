package com.xlccc.pojo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author Linker
 * @Date 2020/3/21 10:49 下午
 * @Version 1.0
 */
@Data
@Setter
@Getter
public class Product {
    private Integer id ;
    private String name ;
    private Integer price ;
}

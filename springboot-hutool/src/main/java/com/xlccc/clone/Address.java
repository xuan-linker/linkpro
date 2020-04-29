package com.xlccc.clone;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Linker
 * @Date 2020/4/29 3:14 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Address implements Serializable {

    private String address;

}

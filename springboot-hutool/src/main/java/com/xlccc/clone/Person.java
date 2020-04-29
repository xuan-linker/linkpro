package com.xlccc.clone;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Linker
 * @Date 2020/4/29 3:16 下午
 * @Version 1.0
 */
@Data
@AllArgsConstructor
public class Person implements Cloneable, Serializable {
    private String name;
    private Integer age;
    private Address address;

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

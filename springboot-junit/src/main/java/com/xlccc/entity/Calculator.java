package com.xlccc.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Linker
 * @Date 2020/4/10 11:27 上午
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Calculator {
    /**
     * 参数1
     */
    private int operand1;
    /**
     * 参数2
     */
    private int operand2;
}

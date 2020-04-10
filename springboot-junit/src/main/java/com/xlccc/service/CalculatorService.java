package com.xlccc.service;

import com.xlccc.entity.Calculator;

/**
 * @Author Linker
 * @Date 2020/4/10 11:21 上午
 * @Version 1.0
 */
public interface CalculatorService {
    /**
     * 加法
     *
     * @param calculator
     * @return
     */
    int add(Calculator calculator);

    /**
     * 减法
     *
     * @param calculator
     * @return
     */
    int subtract(Calculator calculator);
}

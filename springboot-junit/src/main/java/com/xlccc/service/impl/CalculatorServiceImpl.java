package com.xlccc.service.impl;

import com.xlccc.entity.Calculator;
import com.xlccc.service.CalculatorService;
import org.springframework.stereotype.Service;

/**
 * @Author Linker
 * @Date 2020/4/10 11:23 上午
 * @Version 1.0
 */
@Service
public class CalculatorServiceImpl implements CalculatorService {

    @Override
    public int add(Calculator calculator) {
        return calculator.getOperand1() + calculator.getOperand2();
    }

    @Override
    public int subtract(Calculator calculator) {
        return calculator.getOperand1() - calculator.getOperand2();
    }
}

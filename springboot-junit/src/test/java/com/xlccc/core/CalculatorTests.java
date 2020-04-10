package com.xlccc.core;

import com.xlccc.entity.Calculator;
import com.xlccc.service.CalculatorService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Linker
 * @Date 2020/4/10 11:11 上午
 * @Version 1.0
 */
@SpringBootTest
@DisplayName("Calculator")
public class CalculatorTests {

    private Calculator calculator;
    @Autowired
    private CalculatorService calculatorService;

    /**
     * @Test 表明一个测试方法
     * @DisplayName 测试类或方法的显示名称
     * @BeforeEach 表明在单个测试方法运行之前执行的方法
     * @AfterEach 表明在单个测试方法运行之后执行的方法
     * @BeforeAll 表明在所有测试方法运行之前执行的方法
     * @AfterAll 表明在所有测试方法运行之后执行的方法
     * @Disabled 禁用测试类或方法
     * @Tag 为测试类或方法添加标签
     */

    @BeforeAll
    public static void init() {
        System.out.println("Start testing");
    }

    @BeforeEach
    public void create() {
        calculator = new Calculator();
    }

    @AfterEach
    public void destroy() {
        this.calculator = null;
    }

    @AfterAll
    public static void cleanUp() {
        System.out.println("Finish testing");
    }

    @Test
    @DisplayName("Test 1 + 2 = 3")
    public void testAdd() {
        calculator.setOperand1(1);
        calculator.setOperand2(2);
        assertEquals(3, this.calculatorService.add(calculator));
    }

    @Test
    @DisplayName("Test 3 - 2 = 1")
    public void testSubtract() {
        calculator.setOperand1(3);
        calculator.setOperand2(2);
        assertEquals(1, this.calculatorService.subtract(calculator));
    }

    @Disabled
    @Test
    @DisplayName("disabled test")
    public void ignoredTest() {
        System.out.println("This test is disabled");
    }

    /**
     * 元注解标签
     * <p>
     * 类型安全的元注解（meta annotation）
     * 编译器会对元注解标签的正确性进行验证，从而减少无意的错误。
     */

    @Target({ElementType.TYPE, ElementType.METHOD})
    @Retention(RetentionPolicy.RUNTIME)
    @Tag("remote")
    public @interface Remote {
    }

    /**
     * 使用元注解标签
     * <p>
     * 展示了@Remote 的用法。
     * 使用@Remote 的作用等同于@Tag("Remote")，为 testGetUser 方法添加了标签 remote。
     * 使用@Remote 不仅提高了代码的可读性，也可以避免无意的拼写错误带来的问题。
     */
    @DisplayName("Remote test")
    public class RemoteTest {
        @Test
        @Remote
        public void testGetUser() {
            System.out.println("Get User");
        }
    }




}

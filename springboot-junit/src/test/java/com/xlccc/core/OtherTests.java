package com.xlccc.core;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.stream;

/**
 * @Author Linker
 * @Date 2020/4/10 1:43 下午
 * @Version 1.0
 * @Todo: 依赖注入
 * @Description:
 */
@SpringBootTest
public class OtherTests {
    /**
     * 通过 TestInfo 接口，可以获取到当前测试的相关信息，包括显示名称、标签、测试类和测试方法，
     *
     * @param testInfo
     */
    @Test
    @DisplayName("test info")
    public void testInfo(final TestInfo testInfo) {
        System.out.println(testInfo.getDisplayName());
    }

    /**
     * TestReporter 接口对象，来输出额外的键值对信息。
     * 这些信息可以被测试执行的监听器 TestExecutionListener 处理，也可以被输出到测试结果报告中
     *
     * @param testReporter
     */
    @Test
    @DisplayName("test reporter")
    public void testReporter(final TestReporter testReporter) {
        testReporter.publishEntry("name", "Linker");
    }

    /**
     * 动态测试
     * <p>
     * 通过动态测试，可以满足一些静态测试无法解的需求，也可以完成一些重复性很高的测试。
     * 比如，有些测试用例可能依赖运行时的变量，有时候会需要生成上百个不同的测试用例。
     *
     * @return 工厂方法需要返回 org.junit.jupiter.api.DynamicTest 类的集合，
     * 可以是 Stream、Collection、Iterable 或 Iterator 对象
     */
    @TestFactory
    public Collection<DynamicTest> simpleDynamicTest() {
        return Collections.singleton(DynamicTest.dynamicTest("simple dynamic test", () -> assertTrue(2 > 1)));
    }

    @TestFactory
    public Stream<DynamicTest> streamDynamicTest() {
        return stream(
                Stream.of("Hello", "World").iterator(),
                (word) -> String.format("Test - %s", word),
                (word) -> assertTrue(word.length() > 4)
        );
    }
}

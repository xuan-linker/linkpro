package com.xlccc.core;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Linker
 * @Date 2020/4/10 11:56 上午
 * @Version 1.0
 * @Todo: 断言
 */
@SpringBootTest
public class AssertionsTests {
    /**
     * assertEquals	    判断两个对象或两个原始类型是否相等
     * assertNotEquals	判断两个对象或两个原始类型是否不相等
     * assertSame	    判断两个对象引用是否指向同一个对象
     * assertNotSame	判断两个对象引用是否指向不同的对象
     * assertTrue	    判断给定的布尔值是否为 true
     * assertFalse	    判断给定的布尔值是否为 false
     * assertNull	    判断给定的对象引用是否为 null
     * assertNotNull	判断给定的对象引用是否不为 null
     */
    /**
     * 一些简单的AssertDemo
     */
    @Test
    @DisplayName("simple assertion")
    public void simple() {
        assertEquals(3, 1 + 2, "simple math");
        assertNotEquals(3, 1 + 1);

        assertNotSame(new Object(), new Object());
        Object obj = new Object();
        assertSame(obj, obj);

        assertFalse(1 > 2);
        assertTrue(1 < 2);

        assertNull(null);
        assertNotNull(new Object());
    }

    @Test
    @DisplayName("array assertion")
    public void array() {
        assertArrayEquals(new int[]{1, 2}, new int[]{1, 2});
    }

    /**
     * 判断一组assert()是否满足
     * <p>
     * public static void assertAll(String heading, Executable... executables) throws MultipleFailuresError {
     * AssertAll.assertAll(heading, executables);
     * }
     */
    @Test
    @DisplayName("assert all ")
    public void all() {
        assertAll("Math",
                () -> assertEquals(2, 1 + 1),
                () -> assertTrue(1 > 0)
        );
    }

    /**
     * 判断是否抛出期望异常类型
     * <p>
     * public static <T extends Throwable> T assertThrows(Class<T> expectedType, Executable executable) {
     * return AssertThrows.assertThrows(expectedType, executable);
     * }
     */
    @Test
    @DisplayName("throws exception")
    public void exception() {
        assertThrows(ArithmeticException.class,
                () -> System.out.println(1 / 0));
    }

    /**
     * fail 方法，用来使一个测试方法失败。
     * fail测试会直接失败
     */
    @Test
    @DisplayName("fail")
    public void shouldFail() {
        fail("This should fail");
    }

}

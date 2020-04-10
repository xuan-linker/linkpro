package com.xlccc.core;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author Linker
 * @Date 2020/4/10 1:23 下午
 * @Version 1.0
 * @Todo: 嵌套测试
 */
@DisplayName("Nested tests for HashMap")
public class MapNestedTest {
    Map<String, Object> map;

    @Nested
    @DisplayName("when a new")
    class WhenNew {
        @BeforeEach
        void create() {
            map = new HashMap<>();
        }

        @Test
        @DisplayName("is empty")
        void isEmpty() {
            assertTrue(map.isEmpty());
        }

        @Nested
        @DisplayName("after adding a new entry")
        class afterAdd {
            String key = "key";
            Object value = "value";

            @BeforeEach
            void add() {
                map.put(key, value);
            }

            @Test
            @DisplayName("is not empty")
            void isNotEmpty() {
                assertFalse(map.isEmpty());
            }

            @Test
            @DisplayName("returns value when getting by key")
            void returnValueWhenGettingByKey() {
                assertEquals(value, map.get(key));
            }

            @Nested
            @DisplayName("after removing the entry")
            class AfterRemove {
                @BeforeEach
                void remove() {
                    map.remove(key);
                }

                @Test
                @DisplayName("is empty now ")
                void isEmpty() {
                    assertTrue(map.isEmpty());
                }

                @Test
                @DisplayName("returns null when getting by key")
                void returnNullForKey() {
                    assertNull(map.get(key));
                }
            }
        }
    }
}

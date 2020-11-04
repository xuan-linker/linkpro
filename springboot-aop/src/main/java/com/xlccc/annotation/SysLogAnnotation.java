package com.xlccc.annotation;

import java.lang.annotation.*;

/**
 * @author Linker
 * @date 2020/11/3 16:24
 * @description：自定义注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLogAnnotation {
    String value() default "";
}

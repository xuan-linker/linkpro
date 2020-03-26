package com.xlccc.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author Linker
 * @Date 2020/3/26 10:16 下午
 * @Version 1.0
 */
public class CustomPasswordEncoder implements PasswordEncoder {
    /**
     * 加密方法
     * @param charSequence
     * @return
     */
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    /**
     * 匹配方法
     * @param charSequence
     * @param s
     * @return
     */
    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());
    }
}

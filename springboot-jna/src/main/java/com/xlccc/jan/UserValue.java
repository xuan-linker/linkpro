package com.xlccc.jan;

import com.sun.jna.Structure;

/**
 * @author Linker
 * @date 4/12/2023 11:05 PM
 * @description：
 */
public class UserValue extends User implements Structure.ByValue {
    public UserValue(String name, int height, double weight) {
        super(name, height, weight);
    }
}

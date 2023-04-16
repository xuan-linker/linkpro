package com.xlccc.jan;

import com.sun.jna.Structure;
import lombok.Data;

/**
 * @author Linker
 * @date 4/12/2023 11:04 PM
 * @description：
 */
@Data
@Structure.FieldOrder({"name", "height", "weight"})
public class User extends Structure {
    public String name;

    public int height;

    public double weight;

    public User(String name, int height, double weight) {
        this.name = name;
        this.height = height;
        this.weight = weight;
    }
}

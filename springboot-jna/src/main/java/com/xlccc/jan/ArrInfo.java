package com.xlccc.jan;

import com.sun.jna.Pointer;
import com.sun.jna.Structure;
import lombok.Data;

/**
 * @author Linker
 * @date 4/12/2023 11:00 PM
 * @description：
 */
@Data
@Structure.FieldOrder({"vals", "len"})
public class ArrInfo extends Structure {
    public Pointer vals;
    public int len;

    public ArrInfo(Pointer vals, int len) {
        this.vals = vals;
        this.len = len;
    }
}

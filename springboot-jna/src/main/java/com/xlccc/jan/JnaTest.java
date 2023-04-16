package com.xlccc.jan;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

/**
 * @author Linker
 * @date 4/12/2023 10:45 PM
 * @description：
 */
public class JnaTest {
    public static void main(String[] args) {
        testBool();
        testMax();
        testArray();
        testUser();
        testPointer();
    }

    public static void testBool() {
        //c++ output:
        //bool: 255 in true
        JnaLibrary.INSTANCE.getBool(true);
    }

    public static void testMax() {
        int max = JnaLibrary.INSTANCE.max(100, 200);
        // out: 200
        System.out.println(max);
    }


    public static void testArray() {
        JnaLibrary.INSTANCE.testArray(new short[]{1, 2, 3, 4}, 4);
    }

    public static void testUser() {
        UserValue user1 = new UserValue("user1", 186, 65.2);
        JnaLibrary.INSTANCE.printUserRef(user1);
        JnaLibrary.INSTANCE.printUser(user1);
        //out:
        //        printUserRef user: user1 height: 186 weight: 65.20
        //        printUser user: user1 height: 186 weight: 65.20
    }

    public static void testPointer() {
        int len = 3;
        int shortSize = Native.getNativeSize(Short.class);
        Pointer pointer = new Memory(len * shortSize);
        for (int i = 0; i < len; i++) {
            pointer.setShort(shortSize * i, (short) i);
        }
        ArrInfo arrInfo = new ArrInfo(pointer, len);
        JnaLibrary.INSTANCE.testStruct(arrInfo);
    }
}

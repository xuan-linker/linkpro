package com.xlccc.jan;

import com.sun.jna.*;

/**
 * @author Linker
 * @date 4/12/2023 10:46 PM
 * @description：
 */
public interface JnaLibrary extends Library {

    JnaLibrary INSTANCE = Native.load("JNA", JnaLibrary.class);

    int max(int a, int b);

    void getBool(boolean x);

    void testArray(short[] vals, int len);

    void testStruct(ArrInfo arrInfo);

    void printUser(User.ByValue user);

    void printUserRef(User user);

}

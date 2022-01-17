package com.xlccc.demo.bytebuffer;

import java.nio.ByteBuffer;

public class ByteBufferDemo {
    public static void main(String[] args) {

        //init length=16 byte[]
        /**
         * capacity 缓冲区容量
         * limit 读写限制
         * position 读写位置
         */
        ByteBuffer byteBuffer = ByteBuffer.allocate(16);
        byteBuffer.put((byte) 0xabef0101);
        byteBuffer.putInt(1111);
        byteBuffer.put((byte) 1);
        byteBuffer.put((byte) 0);

        //读取消息头
        byteBuffer.flip();
        System.out.println(byteBuffer.toString());
        System.out.println(byteBuffer.position() +" "+byteBuffer.limit()+" "+byteBuffer.capacity());
    }
}

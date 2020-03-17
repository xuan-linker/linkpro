package com.xlccc.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Json与Object相互转换的工具类
 */
public class JsonUtils {

    // 定义Jackson对象
    private static ObjectMapper objectMapper=new ObjectMapper();

    /**
     * 将对象转换为json字符串
     */
    public static String objectToJson(Object data){
        try {
            String jsonString = objectMapper.writeValueAsString(data);
            return jsonString;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json字符串转换为对象
     */
    public static <T> T jsonToObject(String jsonString,Class<T> clazz){
        try {
            T t =objectMapper.readValue(jsonString,clazz);
            return t;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

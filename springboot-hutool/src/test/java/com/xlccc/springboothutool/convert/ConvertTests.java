package com.xlccc.springboothutool.convert;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.CharsetUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author Linker
 * @Date 2020/4/29 4:07 下午
 * @Version 1.0
 */
@SpringBootTest
public class ConvertTests {

    /**
     * Java常见类型转换
     */
    @Test
    @DisplayName("Java常见类型转换")
    public void test1() {
        //转换为字符串
        int a1 = 1;
        String aStr = Convert.toStr(a1);

        long[] a2 = {1, 2, 3, 4, 5};
        String bStr = Convert.toStr(a2);

        //转换为指定类型数组
        String[] b1 = {"1", "2", "3", "4"};
        Integer[] intArray = Convert.toIntArray(b1);

        long[] b2 = {1, 2, 3, 4, 5};
        Integer[] intArray2 = Convert.toIntArray(b2);

        //转换为日期对象
        String date = "2020-04-29";
        Date value = Convert.toDate(date);

        //转换为集合
        Object[] objects = {"a", "你", "好", "", 1};
        List<?> list1 = Convert.convert(List.class, objects);
        List<?> list2 = Convert.toList(objects);
    }

    /**
     * 半角和全角转换
     */
    @Test
    @DisplayName("半角和全角转换")
    public void test2() {
        String a = "123456789";
        //结果为："１２３４５６７８９"
        String sbc = Convert.toSBC(a);

        String b = "１２３４５６７８９";
        //结果为"123456789"
        String dbc = Convert.toDBC(b);

        System.out.println(sbc);
        System.out.println(dbc);
    }

    @Test
    @DisplayName("16进制（Hex）")
    public void test3() {
        String strDemo = "Hello World！";
        String hex = Convert.toHex(strDemo, CharsetUtil.CHARSET_UTF_8);
        System.out.println(hex);

        String raw = Convert.hexToStr(hex, CharsetUtil.CHARSET_UTF_8);
        System.out.println(raw);
    }

    @Test
    @DisplayName("Unicode和字符串转换")
    public void test4() {
        String strDemo = "Hello World";

        String unicode = Convert.strToUnicode(strDemo);
        System.out.println(unicode);

        String raw = Convert.unicodeToStr(unicode);
        System.out.println(raw);
    }

    /**
     * 注意 经过测试，UTF-8编码后用GBK解码再用GBK编码后用UTF-8解码会存在某些中文转换失败的问题。
     */
    @Test
    @DisplayName("编码转换")
    public void test5() {
        String a = "我不是乱码";
        //转换后result为乱码
        String result = Convert.convertCharset(a, CharsetUtil.UTF_8, CharsetUtil.ISO_8859_1);
        String raw = Convert.convertCharset(result, CharsetUtil.ISO_8859_1, "UTF-8");
        Assert.assertEquals(raw, a);
    }

    @Test
    @DisplayName("时间单位转换")
    public void test6() {
        long a = 4535345;
        long minutes = Convert.convertTime(a, TimeUnit.MILLISECONDS, TimeUnit.MINUTES);
        System.out.println(minutes);
    }

    @Test
    @DisplayName("金额大小写转换")
    public void test7() {
        double money = 123456789.01;

        //result:壹亿贰仟叁佰肆拾伍万陆仟柒佰捌拾玖元零壹分
        String digitUppercase = Convert.digitToChinese(money);
        System.out.println(digitUppercase);
    }
}

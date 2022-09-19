package com.bingege.algorithm.topic.base;

/**
 * @description: 异或
 * @author: xiaob 
 * @date: 2022/9/17 16:11
 **/
public class ExclusiveOr {

    /**
     * 1、a ^ 0 = a; a ^ a = 0
     * 2、满足 交换律 和 结合律
     * 3、（a,b,c,d）^ e = (a,c,b,d) ^ e ；结果和执行顺序无关，只和 01 位数上奇偶数有关，奇数为1，偶数为0
     * @description: 三要素
     * @author: xiaob
     * @date: 2022/9/17 16:19
     **/
    public static void exclusiveOr() {
        //值转换
        int a = 3;
        int b = 4;
        swap(a, b);
        System.out.println(a + ":" + b);

        commonSwap(a, b);
    }

    /**
     * 原理详解：
     * 假设：a = m; b = n;
     *
     * @author: xiaob
     * @date: 2022/9/17 16:21
     **/
    public static void swap(int a, int b) {
        //完成转换，利用
        a = a ^ b; //a = m ^ n
        b = a ^ b; //b = m ^ n ^ n = m
        a = a ^ b; //a = m ^ n ^ m = n
    }

    public static void commonSwap(int a, int b) {
        //通用方式，需要增加变量所占用空间
        int c = a;
        a = b;
        b = c;
    }
}

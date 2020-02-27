package com.common.utils;

import org.slf4j.helpers.MessageFormatter;

/**
 * 封装了sl4j的MessageFormatter的字符串格式化类
 */
public class M {
    /**
     *
     * 格式化字符串
     * M.format("hello {}", "people"); 会返回 hello people
     * @param message
     * @param objects
     * @return
     */
    public static String format(final String message, Object ... objects){
        return MessageFormatter.arrayFormat(message, objects).getMessage();
    }

    /**
     * 格式化字符串并打印输出到控制台
     * M.println("hello {}", "people");
     * @param message
     * @param objects
     */
    public static void println(final String message, Object ... objects){
        System.out.println(format(message, objects));
    }

    public static void println(final Object message){
        System.out.println(message);
    }

    /**
     * 格式化字符串并打印输出到控制台
     * M.print("hello {}", "people");
     * @param message
     * @param objects
     */
    public static void print(final String message, Object ... objects){
        System.out.print(format(message, objects));
    }
}

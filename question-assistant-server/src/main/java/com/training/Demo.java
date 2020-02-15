package com.training;

import java.text.MessageFormat;

public class Demo {
    public static void main(String[] args){
        var name="张三";
        var s=MessageFormat.format("hello {0}", "张飞");
        System.out.println(s);
    }
}

package com.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 处理进制的工具类
 **/
public class RadixUtil {
    final static String NAME = RadixUtil.class.getName();

    /**
     * 产生进制的数字
     **/
    final static char[] DIGIT = {//
            'Q','W','E','8','A','S','2','D','Z','X','9','C','7','P','O','5','I','K','3','M','J','U','F','R','4','V','Y','L',
            'T','N','6','B','G','H','q','w','e','a','s','d','z','x','c','p','o','i','k','m','j','u','f','r','v','y','l','t',
            'n','b','g','h'//邀请吗待选字符
    };

    final static Map<Character, Integer> digitMap = new HashMap<Character, Integer>();

    static {
        for (int i = 0; i < DIGIT.length; i++) {
            digitMap.put(Character.valueOf(DIGIT[i]), Integer.valueOf(i));
        }
    }

    /**
     * 支持的最小进制数
     */
    public static final int MIN_RADIX = 2;

    /**
     * 最多一次返回32个字符
     **/
    public static final int SIZE = 32;

    public static String toString(long i) {
        return xBin(i, DIGIT.length);
    }

    /**
     * 将长整型数值转换为指定的进制数（最大支持62进制）
     */
    public static String xBin(long i, int radix) {
        if (radix < MIN_RADIX || radix > DIGIT.length) {
            radix = 10;
        }
        if (radix == 10) {
            return Long.toString(i);
        }

        StringBuilder sb = new StringBuilder(SIZE);
        boolean negative = (i < 0);
        if (negative) {
            i = -i;
        }

        while (i >= radix) {
            sb.append(DIGIT[(int) (i % radix)]);
            i = i / radix;
        }
        sb.append(DIGIT[(int) (i)]);
        if (negative) {
            sb.append("-");
        }
        return sb.reverse().toString();
    }

    private static NumberFormatException forInputString(String s) {
        return new NumberFormatException("For input string: \"" + s + "\"");
    }

    /**
     * 将字符串转换为长整型数字
     */
    public static long toNumber(String s) {
        return toNumber(s, DIGIT.length);
    }

    public static long toNumberLetter(String s) {
        return toNumber(s, 26);
    }

    /**
     * 将字符串转换为长整型数字
     */
    public static long toNumber(String s, int radix) {
        if (s == null) {
            throw new NumberFormatException("null");
        }
        if (radix < MIN_RADIX) {
            throw new NumberFormatException("radix " + radix + " less than " + NAME + ".MIN_RADIX");
        }
        if (radix > DIGIT.length) {
            throw new NumberFormatException("radix " + radix + " greater than " + NAME + ".MAX_RADIX");
        }
        // 返回结果
        long result = 0;
        char[] ss = s.toCharArray();
        // 计算阶乘
        long factorial = 1;
        for (int i = (ss.length - 1); i >= 0; i--) {
            char c = ss[i];
            Integer v = digitMap.get(Character.valueOf(c));
            if (v != null) {
                result += v.intValue() * factorial;
                factorial *= radix;
            }
        }
        return result;
    }
}


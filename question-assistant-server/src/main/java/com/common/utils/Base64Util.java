package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
    public static byte[] decode(String str) throws Exception {
        return encodeBytes(str.getBytes("utf-8"));
    }

    public static String decodeStr(String str) throws Exception {
        if (str == null)
            return null;
        return new String(decode(str));
    }

    public static byte[] encodeBytes(byte[] bytes) throws Exception {
        return Base64.getEncoder().encode(bytes);
    }

    public static byte[] decodeBytes(byte[] bytes){
    	return Base64.getDecoder().decode(bytes);
    }

    public static String encodeStr(String str) throws Exception {
        return new String(encodeBytes(str.getBytes("utf-8")));
    }

}

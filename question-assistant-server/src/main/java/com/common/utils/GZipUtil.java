package com.common.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * GZIP工具类
 */
public class GZipUtil {
	/**
	 * 字节数组压缩
	 * @param data
	 * @return 返回字节数组
	 */
	public static byte[] compress(final byte[] data){
        if (data == null || data.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip;
        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(data);
            gzip.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
	}

	/**
	 * 字节数组解压缩
	 * @param data
	 * @return 字节数组
	 */
    public static byte[] deCompressString(byte[] data) {
        if (data == null || data.length == 0) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(data);

        try {
            GZIPInputStream gunzip = new GZIPInputStream(in);
            byte[] buffer = new byte[256];
            int n;
            while ((n = gunzip.read(buffer)) >= 0) {
                out.write(buffer, 0, n);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }

    public static void main(String[] args){
    	String s = "1002030102390aikdfjklajfdlkaldfkladjflkafdd";
    	byte[] b1 = compress(s.getBytes());
    	byte[] b2 = deCompressString(b1);
    	String s2 = new String(b2);
    	System.out.println(s.length());
    	System.out.println(s);
    	System.out.println(b1.length);
    	System.out.println(s2);
    }
}

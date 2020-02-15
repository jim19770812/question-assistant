package com.common.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class DES3Utils{
    private static final String ALGORITHM = "DESede";
    //默认为 DESede/ECB/PKCS5Padding
    private static final String CIPHER_TRANSFORMAT = "DESede/ECB/PKCS5Padding";
    private static final String ENCODING = "UTF-8";

    public static byte[] encrypt(byte[] plantextBytes, String key) throws Exception{
        SecretKey deskey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher c1 = Cipher.getInstance(CIPHER_TRANSFORMAT);
        c1.init(Cipher.ENCRYPT_MODE, deskey);
        byte[] result = c1.doFinal(plantextBytes);
        return result;
    }

    public static String decrypt(byte[] base64Bytes, final String key) throws Exception {
        SecretKey deskey = new SecretKeySpec(key.getBytes(), ALGORITHM);
        Cipher c1 = Cipher.getInstance(CIPHER_TRANSFORMAT);
        c1.init(Cipher.DECRYPT_MODE, deskey);
        byte[] result = c1.doFinal(base64Bytes);
        return new String(result, ENCODING);
    }

    public static void main(String[] args) throws Exception{
        String key = "thrift.build.this.rpc.ok";
        String sEnc = new String(Base64Util.encodeBytes(DES3Utils.encrypt("这是一个测试".getBytes("UTF-8"), key)));
        System.out.println(sEnc);
        String s =DES3Utils.decrypt(Base64Util.decode(sEnc), key);
        System.out.println(s);
    }
}

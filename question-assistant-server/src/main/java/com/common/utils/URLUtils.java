package com.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class URLUtils {
	private static List<Character> urlNoEncodeList = new ArrayList<Character>(100);
	static{
		urlNoEncodeList.add('-');
		urlNoEncodeList.add('_');
		urlNoEncodeList.add('.');
		for(char ch='A'; ch<='Z'; ch++) urlNoEncodeList.add(ch);
		for(char ch='a'; ch<='z'; ch++) urlNoEncodeList.add(ch);
		for(char ch='0'; ch<='9'; ch++) urlNoEncodeList.add(ch);
	}

	public static String decodeUrl(final String str) throws Exception{
        return URLDecoder.decode(str, "gbk");
    }

    public static String encodeUrl(final String str) throws Exception{
        String s = Base64Util.encodeStr(str);
        return URLEncoder.encode(s, "gbk");
    }

    public static String url2Base64(final String str){
        String s = str.replace(' ', '+');
        s = s.replace("xml+version", "xml version");
        return s;
    }

    public static String asmURLEncoder(final String str, boolean inQueryString){
    	StringBuilder result = new StringBuilder();
    	byte[] encBytes= null;
		try {
			encBytes = str.getBytes("gbk");
	    	for(byte ch : encBytes){
	    		if (ch == (byte)0x20){
	    			if (inQueryString)
	    				result.append("+");
	    			else
	    				result.append("%20");
	    		}else if (urlNoEncodeList.contains((char)ch)){
	    			result.append(ch);
	    		}else{
	    			result.append(String.format("%%%02x", new Object[]{ch}));
	    		}
	    	}
	    	return result.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
    }

//    public static String asmURLDecoder(final String str, boolean inQueryString){
//    	StringBuilder result = new StringBuilder();
//    	byte[] encBytes= null;
//		try {
//			encBytes = str.getBytes("gbk");
//	    	for(byte ch : encBytes){
//	    		if (ch == (byte)0x20){
//	    			if (inQueryString)
//	    				result.append("+");
//	    			else
//	    				result.append("%20");
//	    		}else if (urlNoEncodeList.contains(ch)){
//	    			result.append(ch);
//	    		}else{
//	    			result.append(String.format("%%%02x", new Object[]{ch}));
//	    		}
//	    	}
//	    	return result.toString();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return "";
//    }

    public static void main(String[] args) throws Exception{
    	String str = "1 2 3";
    	String s = URLUtils.asmURLEncoder(str, true);
    	System.out.println(s);
    	String s1 = String.format("%%%02x", new Object[]{10});
    	System.out.println(s1);
    }
}

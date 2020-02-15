package com.common.utils;

import java.util.*;

public class StringUtil {
	protected static final byte[] Hexhars = {
		'0', '1', '2', '3', '4', '5',
		'6', '7', '8', '9', 'a', 'b',
		'c', 'd', 'e', 'f' 
		};
	final protected static char[] LOWERCASE_NUMBERS_CHARS =new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	final protected static char[] NUMBERS_CHARS =new char[]{'0','1','2','3','4','5','6','7','8','9'};

	/**
	 * 根据传入数组对象及分隔符，返回连接字符串，最后加分隔符
	 * 
	 * @param object 数组对象
	 * @param split 分隔符
	 * @return 连接字符串 参数为空时返回""
	 */
	public synchronized static String arrayToString(Object[] object, String split) {
		StringBuffer sb = new StringBuffer("");
		if (object == null || split == null) {
			return sb.toString();
		}
		for (int i = 0; i < object.length; i++) {
			sb.append(object[i].toString());
			sb.append(split);
		}
		return sb.toString();
	}

	/**
	 * 把传入对象，按分隔符分隔后存入数组返回
	 * 
	 * @param str
	 *            源对象
	 * @param split
	 *            分隔符
	 * @return 生成的字符串数组，参数错误时返回NULL
	 */
	public synchronized static String[] stringToArray(Object str, String split) {
		if (str == null || split == null) {
			return null;
		}
		return str.toString().split(split);
	}

	/**
	 * 把对象转换成其字符串形式
	 * 
	 * @param object
	 * @return 对象的字符串表现形式 传入参数为NULL时返回NULL
	 */
	public synchronized static String objectToString(Object object) {
		if (object == null) {
			return null;
		} else {
			return object.toString();
		}
	}

	/**
	 * 过滤字符串的所有空格，并返回
	 * 
	 * @param str
	 * @return String 去掉空格的字符串
	 */
	public synchronized static String filterSpaces(String str) {
		StringBuffer string = new StringBuffer();
		if (str != null && str.length() > 0) {
			for (int i = 0; i < str.length(); i++) {
				String temp = str.substring(i, i + 1);
				if (temp != null && temp.length() > 0 && (!temp.equals(" "))) {
					string.append(temp);
				}
			}
			return string.toString();
		}
		return "";
	}

	/**
	 * 字符串解码，将特殊字符转换为HTML标签
	 * @param str
	 * @return String
	 */
	public synchronized static String encode(String str) {
		str = str.replaceAll("&", "&amp;");
		str = str.replaceAll("'", "''");
		str = str.replaceAll("\"", "&quot;");
		str = str.replaceAll(" ", "&nbsp;");
		str = str.replaceAll("<", "&lt;");
		str = str.replaceAll(">", "&gt;");
		str = str.replaceAll("\n", "<br>");
		return str;
	}

	/**
	 * 字符串编码
	 * @param str
	 * @return
	 */
	public synchronized static String decode(String str) {
		str = str.replaceAll("\n", "<br>");
		str = str.replaceAll("&gt;", ">");
		str = str.replaceAll("&lt;", "<");
		str = str.replaceAll("&nbsp;", " ");
		str = str.replaceAll("&quot;", "\"");
		return str;
	}

	/**
	 * 字符串过长时将多出部分转换成...(区分全、半角) 
	 * @param str 代表输入的字符串
	 * @param length 要截取得长度
	 * @return 返回一个转换后的字符串
	 */

	public synchronized static String getStringRD(String str, int length) {
		int tmp = 0;
		int ls = 0;
		char[] arr = str.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			ls = tmp;
			if (arr[i] < 255) {
				tmp++;
			} else {
				tmp = tmp + 2;
			}
			if (tmp > length) {
				return new String(str.getBytes(), 0, ls) + "...";
			}
		}
		return new String(str.getBytes(), 0, tmp);
	}
    /**
     * 判断输入字符串是否符合规定的日期格式
     * 时间格式默认为：yyyy-MM-dd
     * @param s The string to check
     * @return true if the string is a correctly date 
     * @throws Exception 
     */
    public synchronized static boolean isDate(final String s) throws Exception {
        if (s == null || s.trim().length() == 0)
            return false;

    	Date dtTemp = DateUtil.parseDate(s, DateUtil.DATE_FORMAT);
    	return dtTemp != null;
    }

    /**
     * 判断输入字符串是否符合规定的时间格式
     * 时间格式默认为：yyyy-MM-dd HH:mm:ss
     * @param s The string to check
     * @return true if the string is a correctly date 
     * @throws Exception 
     */
    public synchronized static boolean isDatetime(final String s) throws Exception{
        if (s == null || s.trim().length() == 0)
            return false;

    	Date dtTemp = DateUtil.parseDate(s, DateUtil.LONG_DATE_TIME_FORMAT);
    	return dtTemp != null;
    }
    /**
     * 判断字符是否是可打印字符，ascii 32<=ch<=126
     * @param ch
     * @return
     */
    public synchronized static boolean isPrintableChar(char ch){
    	return (int)ch >=32 && (int)ch < 127;
    }

	/**
	 * 检查输入字符串是否是数字
	 * @param numberStr 需要检查的字符串
	 * @return
	 */
	public synchronized static boolean isNumber(final String numberStr){{
	    if (StringUtil.isNull(numberStr)) {
	      return false;
	    }
	    char[] chars = numberStr.toCharArray();
	    int sz = chars.length;
	    boolean hasExp = false;
	    boolean hasDecPoint = false;
	    boolean allowSigns = false;
	    boolean foundDigit = false;

	    int start = (chars[0] == '-') ? 1 : 0;
	    if ((sz > start + 1) && 
	      (chars[start] == '0') && (chars[(start + 1)] == 'x')) {
	      int i = start + 2;
	      if (i == sz) {
	        return false;
	      }

	      for (; i < chars.length; ++i) {
	        if ((((chars[i] < '0') || (chars[i] > '9'))) && (((chars[i] < 'a') || (chars[i] > 'f'))) && (((chars[i] < 'A') || (chars[i] > 'F')))){
	          return false;
	        }
	      }
	      return true;
	    }
	    --sz;
	    int i = start;
	    while ((i < sz) || ((i < sz + 1) && (allowSigns) && (!(foundDigit)))) {
	      if ((chars[i] >= '0') && (chars[i] <= '9')) {
	        foundDigit = true;
	        allowSigns = false;
	      }
	      else if (chars[i] == '.') {
	        if ((hasDecPoint) || (hasExp)){
	          return false;
	        }
	        hasDecPoint = true;
	      } else if ((chars[i] == 'e') || (chars[i] == 'E'))
	      {
	        if (hasExp)
	        {
	          return false;
	        }
	        if (!(foundDigit)) {
	          return false;
	        }
	        hasExp = true;
	        allowSigns = true;
	      } else if ((chars[i] == '+') || (chars[i] == '-')) {
	        if (!(allowSigns)) {
	          return false;
	        }
	        allowSigns = false;
	        foundDigit = false;
	      } else {
	        return false;
	      }
	      ++i;
	    }
	    if (i < chars.length) {
	      if ((chars[i] >= '0') && (chars[i] <= '9')){
	        return true;
	      }
	      if ((chars[i] == 'e') || (chars[i] == 'E')){
	        return false;
	      }
	      if (chars[i] == '.') {
	        if ((hasDecPoint) || (hasExp)){
	          return false;
	        }

	        return foundDigit;
	      }
	      if ((!(allowSigns)) && (((chars[i] == 'd') || (chars[i] == 'D') || (chars[i] == 'f') || (chars[i] == 'F')))){
	        return foundDigit;
	      }
	      if ((chars[i] == 'l') || (chars[i] == 'L')){
	        return ((foundDigit) && (!(hasExp)));
	      }

	      return false;
	    }
	    return ((!(allowSigns)) && (foundDigit));
	  }
	}

//	public static boolean isCompatibleFloat(String str){
//        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
//        return pattern.matcher(str).matches();
//	}

	public synchronized static boolean isPrintable(byte ch){
		return ch >= 0x20 && ch <= 0x7e;
	}

	public synchronized static boolean isNull(String str){
		return str == null || "".equals(str);
	}

	public synchronized static String bytes2Hex(List<Byte> byteList, final String sp){
		StringBuffer s = new StringBuffer(3 * byteList.size());
		for (byte ch : byteList){
			int v = ch & 0xff;
			s.append((char)Hexhars[v >> 4]);
			s.append((char)Hexhars[v & 0xf]);
			s.append(sp);
		}
		int index =s.lastIndexOf(",");
		if (index > 1)
			s.delete(index, index+1);
		return s.toString().toUpperCase();
	}

	/**
	 * 把十六进制字符串列表转换成字节数组
	 * @param hexStr
	 * @param sp
	 * @return
	 */
	public synchronized static byte[] hex2Bytes(final String hexStr, final String sp){
		List<String> list = new ArrayList<String>(100);
		StringBuffer sb = new StringBuffer(hexStr);
		if (StringUtil.isNull(sp)){
			while (sb.length() > 1){
				String temp = sb.substring(0, 2);
				list.add(temp);
				sb.delete(0, 2);
			}
		}else{
			String[] strings = hexStr.split(sp);
			list.addAll(Arrays.asList(strings));
		}
		byte[] result = new byte[list.size()];
		for(int i=0;i<=list.size()-1;i++){
			String item = list.get(i);
			char c0 = item.charAt(0);
			char c1 = item.charAt(1);
			result[i] = (byte) ((parseChar(c0) << 4) | parseChar(c1));
		}
		return result;
	}

	public synchronized static String generateKey(){
        String s = UUID.randomUUID().toString();
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24);   
	}

	private static int parseChar(final char c){
		if (c >= 'a')
			return (c - 'a' + 10) & 0x0f;
		if (c >= 'A')
			return (c - 'A' + 10) & 0x0f;
		return (c - '0') & 0x0f;
	}

	/**
	 * 检查字符串是否是有效IPv4地址
	 * @param checkStr
	 * @return
	 */
	public static boolean isValidIPv4(String checkStr){
		if (StringUtil.isNull(checkStr))
			return false;

       try{
           String number = checkStr.substring(0,checkStr.indexOf('.'));
           if(Integer.parseInt(number) > 255)
              return false;

           checkStr = checkStr.substring(checkStr.indexOf('.')+ 1);
           number = checkStr.substring(0,checkStr.indexOf('.'));
           if(Integer.parseInt(number) > 255)
        	   return false;

           checkStr = checkStr.substring(checkStr.indexOf('.')+ 1);
           number = checkStr.substring(0,checkStr.indexOf('.'));
           if(Integer.parseInt(number) > 255)
        	   return false;

           number = checkStr.substring(checkStr.indexOf('.')+ 1);
           if (Integer.parseInt(number) > 255)
        	   return false;

           return true;
       }  catch (Exception e){
          return false;
       }
    }

	/**
	 * 生成随机字符串
	 * @param includeChars
	 * @param maxLength
	 * @return
	 */
    public static String genRandomStr(final boolean includeChars, int maxLength){
		StringBuilder result=new StringBuilder();
		Random r=new Random();
		char[] chars=null;
		if (includeChars){
			chars= LOWERCASE_NUMBERS_CHARS;
		}else{
			chars= NUMBERS_CHARS;
		}
		int cnt=chars.length;
		for(int i=0; i<maxLength; i++){
			int index=r.nextInt(cnt-1);
			result.append(chars[index]);
		}
		return result.toString();
	}

	/**
	 * 字符串前后加引号
	 * @param str
	 * @return
	 */
	public static String quotedStr(final String str){
		return "\""+str+"\"";
	}

	public static void main(String[] args) {
    	String s = "-1.2";
    	System.out.println(isNumber(s));
    	byte[] bytes = new byte[5];
    	bytes[0] = (byte)0;
    	bytes[1] = (byte)50;
    	bytes[2] = (byte)0x9c;
    	bytes[3] = (byte)0xa0;
    	bytes[4] = (byte)0xb0;
//    	byte[] bts = hex2Bytes("65,4A,77,72,79,63,67,73,56,67,43,69,52,49,57,53,31,4F,49,53,41,43,59,7A,42,52,59,3D", ",");
//    	s = "65,4A,77,72,79,63,67,73,56,67,43,69,52,49,57,53,31,4F,49,53,41,43,59,7A,42,52,59,3D";
//    	byte[] bts = hex2Bytes(s, ",");
//    	System.out.println(Bytes.asList(bts));
//    	String s1 = bytes2Hex(bts, ",");
//    	if (s.equals(s1)){
//    		System.out.println("OK");
//    	}
//    	String[] ss = s.split(",");
//    	System.out.println(Arrays.asList(ss));
	}
}

package com.common.utils;

import java.lang.management.ManagementFactory;

import java.util.UUID;

public class SystemUtil {
	public static synchronized String generateKey() {
		return UUID.randomUUID().toString().replace("-", "").toLowerCase();
	}

	public static void sleep(final int timeout) {
		try {
			Thread.sleep(timeout);
		} catch (InterruptedException e) {
			e.printStackTrace(); 
		}
	}

	/**
	 * 检查程序/服务器是否运行于调试模式
	 * 
	 * @return
	 */
	public static boolean inDebugMode() {
		return ManagementFactory.getRuntimeMXBean().getInputArguments()
				.toString().indexOf("-agentlib:jdwp") > 0;
	}

	public static boolean isUnix() {
		String osName = System.getProperty("os.name").toUpperCase();
		return osName.indexOf("WINDOWS") < 0;
	}

	public static boolean isWindows() {
		String osName = System.getProperty("os.name").toUpperCase();
		return osName.indexOf("WINDOWS") >= 0;
	}

//	public static String getWorkingDir() {
//		try {
//			return new ClassPathResource("/").getURI().getPath();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return "";
//		}
//	}

	public static void main(String[] args) {
		for (int i = 0; i <= 20; i++) {
			System.out.println(generateKey());
		}
		//32为的0字符串
		//00000000000000000000000000000000
		//String s = System.getenv("HOME");
		//System.out.println(s);
		//s = System.getProperty("user.dir");
		//System.out.println(s);
	}
}

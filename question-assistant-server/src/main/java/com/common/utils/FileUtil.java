package com.common.utils;

import java.io.BufferedReader;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class FileUtil {

	/**
	 * 读取绝对路径文件
	 * 
	 * @param fileName
	 * @return
	 */
	public synchronized static String readAbsolutFile(final String fileName)
			throws Exception {
		BufferedReader rd;
		rd = new BufferedReader(new FileReader(new File(fileName)));
		// FileInputStream fis = new FileInputStream(fileName);
		// InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
		// BufferedReader br = new BufferedReader(isr);
		try {
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();
		} finally {
			rd.close();
		}
	}

	public synchronized static String readAbsolutFile(final String fileName,
			final String enc) throws Exception {
		FileInputStream fis = new FileInputStream(fileName);
		InputStreamReader isr = new InputStreamReader(fis, enc);
		BufferedReader rd = new BufferedReader(isr);
		try {
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();
		} finally {
			rd.close();
		}
	}

	/**
	 * 读文件
	 * 
	 * @param fileName
	 * @return
	 */
	public synchronized static String readFile(final String fileName)
			throws Exception {
		BufferedReader rd;
		rd = new BufferedReader(new InputStreamReader(
				ClassLoader.getSystemResourceAsStream(fileName)));
		try {
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();
		} finally {
			rd.close();
		}
	}

	/**
	 * 读文件
	 * 
	 * @param is
	 * @return
	 */
	public synchronized static String readFile(final InputStream is,
			final String charsetName) throws Exception {
		BufferedReader rd = null;
		try {
			rd = new BufferedReader(new InputStreamReader(is, charsetName));
			StringBuilder stringBuilder = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuilder.append(line);
			}
			return stringBuilder.toString();
		} finally {
			if (rd != null)
				rd.close();
		}
	}

	public synchronized static String readFile(URL url, final String charsetName)
			throws Exception {
		return readFile(url.openStream(), charsetName);
	}

	/**
	 * 写文件
	 * 
	 * @param fileName
	 * @param fileContent
	 * @return
	 * @throws IOException
	 */
	public synchronized static int writeFile(final String fileName, final String fileContent) throws IOException {
		BufferedWriter bw = new BufferedWriter(new FileWriter(
				new File(fileName)));
		try {
			bw.write(fileContent);
			bw.close();
			return fileContent != null ? fileContent.length() : 0;
		} finally {
			bw.close();
		}
	}

	public synchronized static int writeFile(final String fileName, final byte[] bytes) throws IOException {
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(fileName));
		try{
			bos.write(bytes);
			return bytes.length;
		}finally{
			bos.close();
		}
	}

	public synchronized static String extractFileName(final String fullFileName) {
		return fullFileName.substring(fullFileName.lastIndexOf((byte)File.separatorChar) + 1,
				fullFileName.length());
	}

	public synchronized static String extractFileNameEx(final String fullFileName) {
		String result = fullFileName.substring(fullFileName.lastIndexOf((byte)'/') + 1, fullFileName.length());
		result = result.substring(result.lastIndexOf((byte)'\\') + 1, result.length());
		return result;
	}

	/**
	 * 修改文件扩展名
	 * @param fileName 文件名
	 * @param fileExt 扩展名，不包含.
	 * @return
	 */
	public synchronized static String changeFileExt(final String fileName, final String fileExt){
		int index = fileName.lastIndexOf('.');
		String sExt = fileExt.replace('.', ' ').trim();
		StringBuilder sb = new StringBuilder()
			.append(fileName.substring(0, index))
			.append(".")
			.append(sExt);
		return sb.toString();
	}

	/**
	 * 检查目录是否存在
	 * 
	 * @param dirName
	 * @return
	 */
	public synchronized static boolean directoryExists(final String dirName) {
		File f = new File(dirName);
		return f.isDirectory() && f.exists();
	}

	/**
	 * 检查文件是否存在
	 * 
	 * @param fullFileName
	 * @return
	 */
	public synchronized static boolean fileExists(final String fullFileName) {
		File f = new File(fullFileName);
		return f.isFile() && f.exists();
	}

	/**
	 * 删除文件
	 * @param delFile
	 * @return
	 */
	 public static void deleteFile(File delFile){
		 if(delFile.isDirectory()){
			 File files[] = delFile.listFiles();
			 for (File file : files) {
				 deleteFile(file);
			 }
		 }
		 delFile.delete();
	 }

	 /**
	  * 删除文件
	  * @param name
	  */
	 public static void deleteFile(final String name){
		 File f = new File(name);
		 deleteFile(f);
	 }

	 /**
	  * 获取目录路径（一定是以/或\结尾）
	  * @param dir
	  * @return
	  */
	 public synchronized static String getDirectory(final String dir){
		 if (!StringUtil.isNull(dir) && !dir.endsWith(String.valueOf(File.separatorChar))){
			 return new StringBuilder().append(dir).append(File.separatorChar).toString();
		 }
		 return dir;
	 }

	 /**
	  * 创建目录，不存在的多级目录，也可以自动创建
	  * @param dir
	  * @return
	  */
	 public synchronized static boolean mkdirs(final String dir){
		 if (StringUtil.isNull(dir)){
			 return false;
		 }
		 File f = new File(dir);
		 return f.mkdirs();
	 }

	 public static void main(String[] args){
		 //String s = "/home/jim/a123.doc";
		 String s = "{app}\\a123.doc";
		 String s1 = changeFileExt(s, "zip");
		 System.out.println(s1);
		 String s2 = extractFileNameEx(s1);
		 System.out.println(s2);
	 }
}

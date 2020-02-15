package com.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class ZIPUtil {
	public static class Entry{
		private String Name;
		private byte[] data;
		public Entry(){
			this.Name = null;
			this.data = null;
		}

		public Entry(final String name, final byte[] data){
			this.Name = name;
			this.data = data;
		}

		public String getName() {
			return Name;
		}
		public void setName(String name) {
			Name = name;
		}
		public byte[] getData() {
			return data;
		}
		public void setData(byte[] data) {
			this.data = data;
		}
	}

	public static class ZipCompress{
		private ZipOutputStream zipOut;
		private ByteArrayOutputStream  byteOut;

		public ZipCompress() {
			this.byteOut = new ByteArrayOutputStream();
			this.zipOut = new ZipOutputStream(byteOut);
		}

		public void compress(Entry entry) throws IOException{
			byte[] buf = new byte[1024];

			ZipEntry ze = null;
			// 创建一个ZipEntry，并设置Name和其它的一些属性
			ze = new ZipEntry(entry.getName());
			zipOut.putNextEntry(ze);
			ByteArrayInputStream is = new ByteArrayInputStream(entry.getData());
			int readLen = -1;
			while ((readLen = is.read(buf, 0, 1024)) != -1) {
				zipOut.write(buf, 0, readLen);
			}
			zipOut.closeEntry();
			is.close();
		}

		public void compress(List<Entry> entities) throws IOException{
			byte[] buf = new byte[1024];

			ZipEntry ze = null;
			for(Entry entry : entities){
				//创建一个ZipEntry，并设置Name和其它的一些属性
				ze = new ZipEntry(entry.getName());
				zipOut.putNextEntry(ze);
				ByteArrayInputStream is = new ByteArrayInputStream(entry.getData());
				int readLen = -1;
				while ((readLen = is.read(buf, 0, 1024)) != -1) {
					zipOut.write(buf, 0, readLen);
				}
				zipOut.closeEntry();
				is.close();
			}
		}

		public void close() throws IOException{
			byteOut.close();
			this.zipOut.flush();
			zipOut.close();
		}

		public byte[] getBytes(){
			try {
				this.close();
				return byteOut.toByteArray();
			} catch (IOException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public static class ZipDeCompress{
		private byte[] bytes;
		public ZipDeCompress(byte[] data) {
			this.bytes = data;
		}

		public byte[] deCompress(final String entryName) throws IOException{
			List<Entry> entrys = this.deCompress();
			for(Entry entry : entrys){
				if (entry.getName().equals(entryName)){
					return entry.getData();
				}
			}
			return null;
		}

		public List<Entry> deCompress() throws IOException{
			ByteArrayInputStream byteInput = new ByteArrayInputStream(bytes);
			ZipInputStream zipInput = new ZipInputStream(new BufferedInputStream(byteInput));
			final int BUFFER = 2048;
			List<Entry> result = new ArrayList<Entry>(100);
			try {
		         BufferedOutputStream dest = null;
		         ZipEntry entry;
		         while((entry = zipInput.getNextEntry()) != null) {
		            Entry et = new Entry();
		            et.setName(entry.getName());
		            int count;
		            byte data[] = new byte[BUFFER];
		            ByteArrayOutputStream bos = new ByteArrayOutputStream();
		            dest = new BufferedOutputStream(bos);
		            while ((count = zipInput.read(data, 0, BUFFER)) != -1) {
		               dest.write(data, 0, count);
		            }
		            dest.flush();
		            dest.close();
		            et.setData(bos.toByteArray());
		            result.add(et);
		         }
		         zipInput.close();
		      } catch(Exception e) {
		         e.printStackTrace();
		      }
			return result;
		}
	}

	public static byte[] compress(Entry entry) throws IOException{
		ZipCompress zc = new ZipCompress();
		zc.compress(entry);
		return zc.getBytes();
	}

	public static byte[] compress(List<Entry> entries) throws IOException{
		ZipCompress zc = new ZipCompress();
		zc.compress(entries);
		return zc.getBytes();
	}

	public static List<Entry> deCompress(byte[] data) throws IOException{
		ZipDeCompress de = new ZipDeCompress(data);
		List<Entry> entrys = de.deCompress();
		return entrys;
	}

	public static void main(String[] args) throws Exception{
//		String filename = "/home/jim/upload/1.sql";
//		File f = new File(filename);
//		FileInputStream fs = new FileInputStream(f);
//		int size = fs.available();
//		byte[] data = new byte[size]; 
//		int ret = fs.read(data);
//		System.out.println(ret);
//		String s = FileUtil.extractFileName(filename);
		List<Entry> list = new ArrayList<Entry>(100);
		list.add(new Entry("1.sql", FileUtil.readAbsolutFile("/home/jim/1.sql").getBytes()));
		list.add(new Entry("1.js", FileUtil.readAbsolutFile("/home/jim/1.js").getBytes()));
		byte[] b1 = compress(list);
		System.out.println(b1.length);
		FileOutputStream fo = new FileOutputStream("/home/jim/33.zip");
		fo.write(b1);
		fo.flush();
		fo.close();

		List<Entry> dList = deCompress(b1);
		for(Entry et : dList){
			FileOutputStream fo1 = new FileOutputStream("/home/jim/upload/de/" + et.getName());
			fo1.write(et.getData());
			fo1.close();
		}
	}
}

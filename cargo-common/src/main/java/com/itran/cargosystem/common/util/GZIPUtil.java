package com.itran.cargosystem.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GZIPUtil {
	/**
	 * 功能说明:将List对象压缩转化为byte[]
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] compressList(List<String> data) throws IOException {
		byte[] result = null;
		// 序列化使用的输出流
		ByteArrayOutputStream o = new ByteArrayOutputStream();

		GZIPOutputStream gzout = new GZIPOutputStream(o);

		// 建立对象序列化输出流
		ObjectOutputStream out = new ObjectOutputStream(gzout);
		out.writeObject(data);
		out.flush();
		out.close();
		gzout.close();
		result = o.toByteArray();
		o.close();
		return result;
	}

	/**
	 * 功能说明:将byte[]数据解压成List对象于上面过程逆向
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static List<String> uncompressList(byte[] data) throws IOException {
		List<String> result = null;

		ByteArrayInputStream i = new ByteArrayInputStream(data);

		GZIPInputStream gzin = new GZIPInputStream(i);

		ObjectInputStream in = new ObjectInputStream(gzin);

		try {
			result = (List<String>) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		i.close();
		gzin.close();
		in.close();
		return result;
	}
	

	/**
	 * 功能说明:将List对象压缩转化为byte[]
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] compressByte(byte[] data) throws IOException {
		if(data==null)return null;
		byte[] result = null;
		// 序列化使用的输出流
		ByteArrayOutputStream o = new ByteArrayOutputStream();

		GZIPOutputStream gzout = new GZIPOutputStream(o);

		// 建立对象序列化输出流
		ObjectOutputStream out = new ObjectOutputStream(gzout);
		out.writeObject(data);
		out.flush();
		out.close();
		gzout.close();
		result = o.toByteArray();
		o.close();
		return result;
	}

	/**
	 * 功能说明:将byte[]数据解压成List对象于上面过程逆向
	 * 
	 * @param data
	 * @return
	 * @throws IOException
	 */
	public static byte[] uncompressByte(byte[] data) throws IOException {
		if(data==null)return null;
		byte[] result = null;

		ByteArrayInputStream i = new ByteArrayInputStream(data);

		GZIPInputStream gzin = new GZIPInputStream(i);

		ObjectInputStream in = new ObjectInputStream(gzin);

		try {
			result = (byte[]) in.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		i.close();
		gzin.close();
		in.close();
		return result;
	}
}

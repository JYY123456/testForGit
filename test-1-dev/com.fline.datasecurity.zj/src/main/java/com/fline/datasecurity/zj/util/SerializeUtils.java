package com.fline.datasecurity.zj.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化/反序列化工具
 * 
 * @author Fline_FDP
 * 
 */
public class SerializeUtils {

	/**
	 * 对象序列化成字节数组
	 * 
	 * @param obj
	 * @return
	 * @throws IOException
	 */
	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(bos);
		oos.writeObject(obj);
		oos.close();
		bos.close();
		return bos.toByteArray();
	}

	/**
	 * 反序列化成对象
	 * 
	 * @param buf
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public static <T> T deserialize(byte[] buf) throws IOException,
			ClassNotFoundException {
		if (buf == null) {
			return null;
		}
		ByteArrayInputStream bis = new ByteArrayInputStream(buf);
		ObjectInputStream ois = new ObjectInputStream(bis);
		@SuppressWarnings("unchecked")
		T obj = (T) ois.readObject();
		ois.close();
		bis.close();
		return obj;
	}
}

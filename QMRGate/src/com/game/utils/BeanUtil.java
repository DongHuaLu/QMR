package com.game.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

public class BeanUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BeanUtil.class);
	
	
	public static Object getStack(){
		
		
		return null;
	}
	
	

	/**
	 * 通过反射获取对象的值
	 * @param obj			对象
	 * @param properties	字段名
	 * @return				字段所对应的值
	 * @throws Exception
	 */
	public static Object getMethodValue(Object obj,
			String properties) throws Exception {
		String methodname="get"+formatProperties(properties);
		Method method = obj.getClass().getMethod(methodname);
		Object keypropvalue = method.invoke(obj);
		return keypropvalue;
	}
	
	public static Object invokeMethod(Object obj,Method method) throws IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		return method.invoke(obj);
	}
	
	/**
	 * 获取BEAN中的属性值 包括私有属性 包括静态属性
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object getFieldValue(Object Bean,String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		Class<? extends Object> cls= Bean.getClass();
		Field declaredField = cls.getDeclaredField(fieldName);
		boolean before=declaredField.isAccessible();
		declaredField.setAccessible(true);
		Object object = declaredField.get(Bean);
		declaredField.setAccessible(before);
		return object;
	}
	
	//首字母大写
	private static String formatProperties(String name){
		if (name.length() > 1) {
			if (Character.isLowerCase(name.charAt(0))) {// 小写
				if (Character.isLowerCase(name.charAt(1))) {// 小写
					return String.valueOf(name.charAt(0)).toUpperCase()
							+ name.substring(1);
				} else {
					return String.valueOf(name.charAt(0)).toLowerCase()
							+ name.substring(1);
				}
			}else{
				return name;
			}
		}
		return String.valueOf(name.charAt(0)).toUpperCase();
	}
	
	public static byte[] convertToByteArray(Object obj) {

		try {
			ObjectOutputStream os = null;
			ByteArrayOutputStream byteStream = new ByteArrayOutputStream(5000);
			os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
			os.flush();
			os.writeObject(obj);
			os.flush();
			byte[] sendBuf = byteStream.toByteArray();
			os.close();
			return sendBuf;
		} catch (IOException e) {
			logger.error(e,e);
		}
		return null;
	}	
}

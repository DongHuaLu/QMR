package com.game.util;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
/**
 * 
 * @author 赵聪慧
 *
 */
public class BeanUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BeanUtil.class);
	
	
	public static String getStack(){
		Exception exception = new Exception();
		StackTraceElement[] stackTrace = exception.getStackTrace();
		StringBuffer buffer=new StringBuffer();
		buffer.append("\n");
		for (StackTraceElement stackTraceElement : stackTrace) {
			buffer.append(stackTraceElement.getClassName()).append(".").append(stackTraceElement.getMethodName()).append(".").append(stackTraceElement.getLineNumber()).append("\n");	
		}
		return buffer.toString();
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
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Map listToMap(List list,String properties,Class mapclass){
		try {
			Map hashmap = (Map)mapclass.newInstance();
			for (Iterator iter = list.iterator(); iter.hasNext();) {
				Object item = iter.next();
				try {
					Object key = getPropValue(item, properties);
					hashmap.put(key, item);
				} catch (Exception ex) {
					throw new RuntimeException(ex.getMessage());
				}
			}
			return hashmap;
		} catch (InstantiationException e) {			
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("rawtypes")
	public static Map listToMap(List list, String keyPropertiesName) {
		return listToMap(list,keyPropertiesName,HashMap.class);
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
	
	
	public static Object getPropValue(Object obj,
			String properties) throws Exception {
		String methodname="get"+formatProperties(properties);
		Method method = obj.getClass().getMethod(methodname);
		Object keypropvalue = method.invoke(obj);
		return keypropvalue;
	}
	
	/**
	 * 
	 * 
	 *
	 * @param oldmap
	 * @param newmap
	 * @param property
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void addOrUpdate(Map oldmap, Map newmap, String property) throws Exception{
		if(newmap==null){
			return;
		}
		/**
		 	for(Task newtask:newtaskmap.values()){
			Task oldnpc=cacheTaskmap.get(newtask.getId());
			if(oldnpc==null){
				cacheTaskmap.put(newtask.getId(), newtask);
			}else{
				BeanUtils.copyProperties(newtask, oldnpc);//��Ӱ������
			}
		}	 
		 */
		for(Object newobj:newmap.values()){
			Object key=getPropValue(newobj, property);
			Object oldobj=oldmap.get(key);
			if(oldobj==null){
				oldmap.put(key, newobj);
			}else{
				BeanUtils.copyProperties(newobj, oldobj);
			}			
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void addOrUpdate(Map oldmap,Collection collection,String property) throws Exception{
		if(collection==null){
			return;
		}
		/**
		 	for(Task newtask:newtaskmap.values()){
			Task oldnpc=cacheTaskmap.get(newtask.getId());
			if(oldnpc==null){
				cacheTaskmap.put(newtask.getId(), newtask);
			}else{
				BeanUtils.copyProperties(newtask, oldnpc);//��Ӱ������
			}
		}	 
		 */
		for(Object newobj:collection){
			Object key=getPropValue(newobj, property);
			Object oldobj=oldmap.get(key);
			if(oldobj==null){
				oldmap.put(key, newobj);
			}else{
				BeanUtils.copyProperties(newobj, oldobj);
			}			
		}
	}
	
	public static boolean isNull(Object object){
		return object==null;
	}
	
	public static List<String> toLowerCase(List<String> list){
		ArrayList<String> result=new ArrayList<String>();
		for (String string : list) {
			result.add(string.toLowerCase());
		}
		return result;
	}
	
	
	
	
	
}

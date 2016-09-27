package com.game.utils;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils{
	public static final String EMPTY_STRING = "";
	public static final int ZERO = 0;
	
	/**
	  * 查询一个字符串在另一个字符串中的出现频率 
	  * @param selectStr 查询字符
	  * @param targetStr 目标字符串
	  * @return
	  */
	public static int findStrIndexOfCount(String selectStr, String targetStr) {		
		return countMatches(selectStr, targetStr);
	}
	
	/**
	  * 查询一个字符串在另一个字符串中的出现频率忽略大小写 
	  * @param selectStr 查询字符
	  * @param targetStr 目标字符串
	  * @return
	  */
	public static int findStrIndexOfCountIgnoreCase(String selectStr, String targetStr) {
		selectStr=selectStr.toUpperCase();
		targetStr=targetStr.toUpperCase();
		return countMatches(selectStr,targetStr);
	}
	
	/**
	 * 判断是否空字符串 忽略空格
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s){
		if(s==null){
			return true;
		}
		if("".equals(s.trim())){
			return true;
		}
		return false;
	}
	
	/**
	 * equals比较
	 * @param objs
	 * @param obj
	 * @return
	 */
	public static <T> boolean hasObject(T[] objs, T obj) {
		if(obj==null){
			for (Object object : objs) {
				if(object==obj){
					return true;
				}
			}
		}else{
			for (Object object : objs) {
				if(obj.equals(object)){
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 获得一个JSON的数据 
	 * <br>注意：这里的格式是没有最外层的括号的如下:
	 * <br>“x:123, y:123, name:"张三" ”
	 * @param txt
	 * @return 
	 * 
	 */		
	public static String formatToJson(String txt)
	{
		txt = txt.substring(1, txt.length() - 1);
		txt = "{" + txt + "}"; 
		txt = txt.replaceAll(Symbol.DOUHAO_REG, Symbol.DOUHAO);
		String parse = txt.replaceAll("([{,，])\\s*([0-9a-zA-Z一-龟]+)\\s*:", "$1\"$2\":" );
		return parse;
	}
	
//	public static void main(String[] args) {
//		String data = "[攻击:100，防御:100，生命上限:100，暴击:100]";
//		System.out.println(StringUtil.formatToJson(data));
//	}
}

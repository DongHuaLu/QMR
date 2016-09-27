package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_global Bean
 */
public class Q_globalBean {

	//变量id
	private int q_id;
	
	//数值变量值
	private int q_int_value;
	
	//字符串变量值
	private String q_string_value;
	
	
	/**
	 * get 变量id
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 变量id
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 数值变量值
	 * @return 
	 */
	public int getQ_int_value(){
		return q_int_value;
	}
	
	/**
	 * set 数值变量值
	 */
	public void setQ_int_value(int q_int_value){
		this.q_int_value = q_int_value;
	}
	
	/**
	 * get 字符串变量值
	 * @return 
	 */
	public String getQ_string_value(){
		return q_string_value;
	}
	
	/**
	 * set 字符串变量值
	 */
	public void setQ_string_value(String q_string_value){
		this.q_string_value = q_string_value;
	}
	
}
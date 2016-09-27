package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_restw Bean
 */
public class Q_restwBean {

	//id
	private int q_id;
	
	//中文名称
	private String q_key;
	
	//其他语言
	private String q_value;
	
	
	/**
	 * get id
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set id
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 中文名称
	 * @return 
	 */
	public String getQ_key(){
		return q_key;
	}
	
	/**
	 * set 中文名称
	 */
	public void setQ_key(String q_key){
		this.q_key = q_key;
	}
	
	/**
	 * get 其他语言
	 * @return 
	 */
	public String getQ_value(){
		return q_value;
	}
	
	/**
	 * set 其他语言
	 */
	public void setQ_value(String q_value){
		this.q_value = q_value;
	}
	
}
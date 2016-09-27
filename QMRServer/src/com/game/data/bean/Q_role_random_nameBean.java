package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_role_random_name Bean
 */
public class Q_role_random_nameBean {

	//表编号
	private int q_id;
	
	//类型（1姓，2男名，3女名）
	private int q_type;
	
	//内容
	private String q_value;
	
	
	/**
	 * get 表编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 表编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 类型（1姓，2男名，3女名）
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 类型（1姓，2男名，3女名）
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 内容
	 * @return 
	 */
	public String getQ_value(){
		return q_value;
	}
	
	/**
	 * set 内容
	 */
	public void setQ_value(String q_value){
		this.q_value = q_value;
	}
	
}
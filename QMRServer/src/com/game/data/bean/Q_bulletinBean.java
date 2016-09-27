package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_bulletin Bean
 */
public class Q_bulletinBean {

	//id
	private int q_id;
	
	//内容
	private String q_content;
	
	//时间格式
	private String q_time;
	
	//国家（所有国家0）
	private int q_country;
	
	//公告提示位置，1横幅，2聊天栏，3横幅+聊天栏
	private String q_type;
	
	
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
	 * get 内容
	 * @return 
	 */
	public String getQ_content(){
		return q_content;
	}
	
	/**
	 * set 内容
	 */
	public void setQ_content(String q_content){
		this.q_content = q_content;
	}
	
	/**
	 * get 时间格式
	 * @return 
	 */
	public String getQ_time(){
		return q_time;
	}
	
	/**
	 * set 时间格式
	 */
	public void setQ_time(String q_time){
		this.q_time = q_time;
	}
	
	/**
	 * get 国家（所有国家0）
	 * @return 
	 */
	public int getQ_country(){
		return q_country;
	}
	
	/**
	 * set 国家（所有国家0）
	 */
	public void setQ_country(int q_country){
		this.q_country = q_country;
	}
	
	/**
	 * get 公告提示位置，1横幅，2聊天栏，3横幅+聊天栏
	 * @return 
	 */
	public String getQ_type(){
		return q_type;
	}
	
	/**
	 * set 公告提示位置，1横幅，2聊天栏，3横幅+聊天栏
	 */
	public void setQ_type(String q_type){
		this.q_type = q_type;
	}
	
}
package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_version Bean
 */
public class Q_versionBean {

	//表名
	private String q_tablename;
	
	//版本号
	private int q_int_value;
	
	//说明
	private String q_desc;
	
	
	/**
	 * get 表名
	 * @return 
	 */
	public String getQ_tablename(){
		return q_tablename;
	}
	
	/**
	 * set 表名
	 */
	public void setQ_tablename(String q_tablename){
		this.q_tablename = q_tablename;
	}
	
	/**
	 * get 版本号
	 * @return 
	 */
	public int getQ_int_value(){
		return q_int_value;
	}
	
	/**
	 * set 版本号
	 */
	public void setQ_int_value(int q_int_value){
		this.q_int_value = q_int_value;
	}
	
	/**
	 * get 说明
	 * @return 
	 */
	public String getQ_desc(){
		return q_desc;
	}
	
	/**
	 * set 说明
	 */
	public void setQ_desc(String q_desc){
		this.q_desc = q_desc;
	}
	
}
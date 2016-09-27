package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_filterword Bean
 */
public class Q_filterwordBean {

	//国家编号
	private String q_country;
	
	//关键词(逗号分隔)
	private String q_keys;
	
	
	/**
	 * get 国家编号
	 * @return 
	 */
	public String getQ_country(){
		return q_country;
	}
	
	/**
	 * set 国家编号
	 */
	public void setQ_country(String q_country){
		this.q_country = q_country;
	}
	
	/**
	 * get 关键词(逗号分隔)
	 * @return 
	 */
	public String getQ_keys(){
		return q_keys;
	}
	
	/**
	 * set 关键词(逗号分隔)
	 */
	public void setQ_keys(String q_keys){
		this.q_keys = q_keys;
	}
	
}
package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_rangevalue Bean
 */
public class Q_rangevalueBean {

	//配置编号
	private int q_id;
	
	//最低值(0-100)
	private int q_floor;
	
	//最高值(0-100)
	private int q_ceiling;
	
	//比值
	private int q_ratio;
	
	//分组编号
	private int q_groupid;
	
	
	/**
	 * get 配置编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 配置编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 最低值(0-100)
	 * @return 
	 */
	public int getQ_floor(){
		return q_floor;
	}
	
	/**
	 * set 最低值(0-100)
	 */
	public void setQ_floor(int q_floor){
		this.q_floor = q_floor;
	}
	
	/**
	 * get 最高值(0-100)
	 * @return 
	 */
	public int getQ_ceiling(){
		return q_ceiling;
	}
	
	/**
	 * set 最高值(0-100)
	 */
	public void setQ_ceiling(int q_ceiling){
		this.q_ceiling = q_ceiling;
	}
	
	/**
	 * get 比值
	 * @return 
	 */
	public int getQ_ratio(){
		return q_ratio;
	}
	
	/**
	 * set 比值
	 */
	public void setQ_ratio(int q_ratio){
		this.q_ratio = q_ratio;
	}
	
	/**
	 * get 分组编号
	 * @return 
	 */
	public int getQ_groupid(){
		return q_groupid;
	}
	
	/**
	 * set 分组编号
	 */
	public void setQ_groupid(int q_groupid){
		this.q_groupid = q_groupid;
	}
	
}
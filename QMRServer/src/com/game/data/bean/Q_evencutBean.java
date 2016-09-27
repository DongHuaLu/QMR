package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_evencut Bean
 */
public class Q_evencutBean {

	//连斩环数
	private int q_id;
	
	//连斩次数,小于当前数，则进入
	private int q_evencut_num;
	
	//计数倒计时
	private int q_countdown;
	
	//BUFFID
	private int q_buff_id;
	
	//BUFF光环资源
	private int q_buff_res_effect;
	
	//BUFF名称
	private String q_buff_name;
	
	//经验加成
	private int q_exp;
	
	
	/**
	 * get 连斩环数
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 连斩环数
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 连斩次数,小于当前数，则进入
	 * @return 
	 */
	public int getQ_evencut_num(){
		return q_evencut_num;
	}
	
	/**
	 * set 连斩次数,小于当前数，则进入
	 */
	public void setQ_evencut_num(int q_evencut_num){
		this.q_evencut_num = q_evencut_num;
	}
	
	/**
	 * get 计数倒计时
	 * @return 
	 */
	public int getQ_countdown(){
		return q_countdown;
	}
	
	/**
	 * set 计数倒计时
	 */
	public void setQ_countdown(int q_countdown){
		this.q_countdown = q_countdown;
	}
	
	/**
	 * get BUFFID
	 * @return 
	 */
	public int getQ_buff_id(){
		return q_buff_id;
	}
	
	/**
	 * set BUFFID
	 */
	public void setQ_buff_id(int q_buff_id){
		this.q_buff_id = q_buff_id;
	}
	
	/**
	 * get BUFF光环资源
	 * @return 
	 */
	public int getQ_buff_res_effect(){
		return q_buff_res_effect;
	}
	
	/**
	 * set BUFF光环资源
	 */
	public void setQ_buff_res_effect(int q_buff_res_effect){
		this.q_buff_res_effect = q_buff_res_effect;
	}
	
	/**
	 * get BUFF名称
	 * @return 
	 */
	public String getQ_buff_name(){
		return q_buff_name;
	}
	
	/**
	 * set BUFF名称
	 */
	public void setQ_buff_name(String q_buff_name){
		this.q_buff_name = q_buff_name;
	}
	
	/**
	 * get 经验加成
	 * @return 
	 */
	public int getQ_exp(){
		return q_exp;
	}
	
	/**
	 * set 经验加成
	 */
	public void setQ_exp(int q_exp){
		this.q_exp = q_exp;
	}
	
}
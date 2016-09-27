package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scheduler Bean
 */
public class Q_schedulerBean {

	//任务id
	private int q_scheduler_id;
	
	//执行时间
	private String q_scheduler_time;
	
	//执行函数
	private String q_scheduler_class;
	
	
	/**
	 * get 任务id
	 * @return 
	 */
	public int getQ_scheduler_id(){
		return q_scheduler_id;
	}
	
	/**
	 * set 任务id
	 */
	public void setQ_scheduler_id(int q_scheduler_id){
		this.q_scheduler_id = q_scheduler_id;
	}
	
	/**
	 * get 执行时间
	 * @return 
	 */
	public String getQ_scheduler_time(){
		return q_scheduler_time;
	}
	
	/**
	 * set 执行时间
	 */
	public void setQ_scheduler_time(String q_scheduler_time){
		this.q_scheduler_time = q_scheduler_time;
	}
	
	/**
	 * get 执行函数
	 * @return 
	 */
	public String getQ_scheduler_class(){
		return q_scheduler_class;
	}
	
	/**
	 * set 执行函数
	 */
	public void setQ_scheduler_class(String q_scheduler_class){
		this.q_scheduler_class = q_scheduler_class;
	}
	
}
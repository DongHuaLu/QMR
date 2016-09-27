package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_special_event Bean
 */
public class Q_special_eventBean {

	//触发事件编号
	private int q_id;
	
	//触发事件点,map_x_y_范围
	private String q_event_pos;
	
	//触发事件脚本
	private int q_event_scriptid;
	
	
	/**
	 * get 触发事件编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 触发事件编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 触发事件点,map_x_y_范围
	 * @return 
	 */
	public String getQ_event_pos(){
		return q_event_pos;
	}
	
	/**
	 * set 触发事件点,map_x_y_范围
	 */
	public void setQ_event_pos(String q_event_pos){
		this.q_event_pos = q_event_pos;
	}
	
	/**
	 * get 触发事件脚本
	 * @return 
	 */
	public int getQ_event_scriptid(){
		return q_event_scriptid;
	}
	
	/**
	 * set 触发事件脚本
	 */
	public void setQ_event_scriptid(int q_event_scriptid){
		this.q_event_scriptid = q_event_scriptid;
	}
	
}
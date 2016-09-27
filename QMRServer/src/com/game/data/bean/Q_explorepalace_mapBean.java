package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_explorepalace_map Bean
 */
public class Q_explorepalace_mapBean {

	//地宫格子di
	private int q_id;
	
	//上边格子
	private int q_on;
	
	//下边格子
	private int q_under;
	
	//左边格子
	private int q_left;
	
	//右边格子
	private int q_right;
	
	//事件ID
	private int q_eventid;
	
	
	/**
	 * get 地宫格子di
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 地宫格子di
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 上边格子
	 * @return 
	 */
	public int getQ_on(){
		return q_on;
	}
	
	/**
	 * set 上边格子
	 */
	public void setQ_on(int q_on){
		this.q_on = q_on;
	}
	
	/**
	 * get 下边格子
	 * @return 
	 */
	public int getQ_under(){
		return q_under;
	}
	
	/**
	 * set 下边格子
	 */
	public void setQ_under(int q_under){
		this.q_under = q_under;
	}
	
	/**
	 * get 左边格子
	 * @return 
	 */
	public int getQ_left(){
		return q_left;
	}
	
	/**
	 * set 左边格子
	 */
	public void setQ_left(int q_left){
		this.q_left = q_left;
	}
	
	/**
	 * get 右边格子
	 * @return 
	 */
	public int getQ_right(){
		return q_right;
	}
	
	/**
	 * set 右边格子
	 */
	public void setQ_right(int q_right){
		this.q_right = q_right;
	}
	
	/**
	 * get 事件ID
	 * @return 
	 */
	public int getQ_eventid(){
		return q_eventid;
	}
	
	/**
	 * set 事件ID
	 */
	public void setQ_eventid(int q_eventid){
		this.q_eventid = q_eventid;
	}
	
}
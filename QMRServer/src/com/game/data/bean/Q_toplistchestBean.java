package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_toplistchest Bean
 */
public class Q_toplistchestBean {

	//宝箱编号
	private int q_chest_id;
	
	//宝箱类型(1等级 2坐骑 3武功 4龙元 5连斩)
	private int q_chest_type;
	
	//宝箱领取条件
	private int q_recieve_cont;
	
	//对应礼包ID
	private int q_gift;
	
	//领取时的按钮描述
	private String q_receive_description;
	
	//不能领取时的按钮描述
	private String q_notcompleted_description;
	
	
	/**
	 * get 宝箱编号
	 * @return 
	 */
	public int getQ_chest_id(){
		return q_chest_id;
	}
	
	/**
	 * set 宝箱编号
	 */
	public void setQ_chest_id(int q_chest_id){
		this.q_chest_id = q_chest_id;
	}
	
	/**
	 * get 宝箱类型(1等级 2坐骑 3武功 4龙元 5连斩)
	 * @return 
	 */
	public int getQ_chest_type(){
		return q_chest_type;
	}
	
	/**
	 * set 宝箱类型(1等级 2坐骑 3武功 4龙元 5连斩)
	 */
	public void setQ_chest_type(int q_chest_type){
		this.q_chest_type = q_chest_type;
	}
	
	/**
	 * get 宝箱领取条件
	 * @return 
	 */
	public int getQ_recieve_cont(){
		return q_recieve_cont;
	}
	
	/**
	 * set 宝箱领取条件
	 */
	public void setQ_recieve_cont(int q_recieve_cont){
		this.q_recieve_cont = q_recieve_cont;
	}
	
	/**
	 * get 对应礼包ID
	 * @return 
	 */
	public int getQ_gift(){
		return q_gift;
	}
	
	/**
	 * set 对应礼包ID
	 */
	public void setQ_gift(int q_gift){
		this.q_gift = q_gift;
	}
	
	/**
	 * get 领取时的按钮描述
	 * @return 
	 */
	public String getQ_receive_description(){
		return q_receive_description;
	}
	
	/**
	 * set 领取时的按钮描述
	 */
	public void setQ_receive_description(String q_receive_description){
		this.q_receive_description = q_receive_description;
	}
	
	/**
	 * get 不能领取时的按钮描述
	 * @return 
	 */
	public String getQ_notcompleted_description(){
		return q_notcompleted_description;
	}
	
	/**
	 * set 不能领取时的按钮描述
	 */
	public void setQ_notcompleted_description(String q_notcompleted_description){
		this.q_notcompleted_description = q_notcompleted_description;
	}
	
}
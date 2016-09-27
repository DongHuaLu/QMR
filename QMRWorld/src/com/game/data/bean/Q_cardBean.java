package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_card Bean
 */
public class Q_cardBean {

	//平台ID
	private int q_ag_id;
	
	//卡类型
	private int q_card_type;
	
	//新手卡对应道具ID
	private int q_item_id;
	
	
	/**
	 * get 平台ID
	 * @return 
	 */
	public int getQ_ag_id(){
		return q_ag_id;
	}
	
	/**
	 * set 平台ID
	 */
	public void setQ_ag_id(int q_ag_id){
		this.q_ag_id = q_ag_id;
	}
	
	/**
	 * get 卡类型
	 * @return 
	 */
	public int getQ_card_type(){
		return q_card_type;
	}
	
	/**
	 * set 卡类型
	 */
	public void setQ_card_type(int q_card_type){
		this.q_card_type = q_card_type;
	}
	
	/**
	 * get 新手卡对应道具ID
	 * @return 
	 */
	public int getQ_item_id(){
		return q_item_id;
	}
	
	/**
	 * set 新手卡对应道具ID
	 */
	public void setQ_item_id(int q_item_id){
		this.q_item_id = q_item_id;
	}
	
}
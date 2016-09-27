package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_platformgift Bean
 */
public class Q_platformgiftBean {

	//礼包编号
	private int q_id;
	
	//所属平台
	private String q_agent;
	
	//礼包类型
	private int q_type;
	
	//领取最小等级
	private int q_min_lv;
	
	//领取最大等级
	private int q_max_lv;
	
	//礼包名称
	private String q_name;
	
	//礼包ID
	private int q_giftid;
	
	//显示内容
	private String q_display;
	
	//领取限制(0-无限制 1-每日一次 2-一生一次)
	private int q_limit;
	
	
	/**
	 * get 礼包编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 礼包编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 所属平台
	 * @return 
	 */
	public String getQ_agent(){
		return q_agent;
	}
	
	/**
	 * set 所属平台
	 */
	public void setQ_agent(String q_agent){
		this.q_agent = q_agent;
	}
	
	/**
	 * get 礼包类型
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 礼包类型
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 领取最小等级
	 * @return 
	 */
	public int getQ_min_lv(){
		return q_min_lv;
	}
	
	/**
	 * set 领取最小等级
	 */
	public void setQ_min_lv(int q_min_lv){
		this.q_min_lv = q_min_lv;
	}
	
	/**
	 * get 领取最大等级
	 * @return 
	 */
	public int getQ_max_lv(){
		return q_max_lv;
	}
	
	/**
	 * set 领取最大等级
	 */
	public void setQ_max_lv(int q_max_lv){
		this.q_max_lv = q_max_lv;
	}
	
	/**
	 * get 礼包名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 礼包名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 礼包ID
	 * @return 
	 */
	public int getQ_giftid(){
		return q_giftid;
	}
	
	/**
	 * set 礼包ID
	 */
	public void setQ_giftid(int q_giftid){
		this.q_giftid = q_giftid;
	}
	
	/**
	 * get 显示内容
	 * @return 
	 */
	public String getQ_display(){
		return q_display;
	}
	
	/**
	 * set 显示内容
	 */
	public void setQ_display(String q_display){
		this.q_display = q_display;
	}
	
	/**
	 * get 领取限制(0-无限制 1-每日一次 2-一生一次)
	 * @return 
	 */
	public int getQ_limit(){
		return q_limit;
	}
	
	/**
	 * set 领取限制(0-无限制 1-每日一次 2-一生一次)
	 */
	public void setQ_limit(int q_limit){
		this.q_limit = q_limit;
	}
	
}
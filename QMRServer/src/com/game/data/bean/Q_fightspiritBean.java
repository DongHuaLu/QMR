package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_fightspirit Bean
 */
public class Q_fightspiritBean {

	//战魂id
	private String q_id;
	
	//搜索次数
	private int q_fightspirit_num;
	
	//战魂得到类型
	private int q_fightspirit_type;
	
	//默认获得战魂数
	private int q_getfightspirit;
	
	//默认所需元宝数
	private int q_yuanbao;
	
	
	/**
	 * get 战魂id
	 * @return 
	 */
	public String getQ_id(){
		return q_id;
	}
	
	/**
	 * set 战魂id
	 */
	public void setQ_id(String q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 搜索次数
	 * @return 
	 */
	public int getQ_fightspirit_num(){
		return q_fightspirit_num;
	}
	
	/**
	 * set 搜索次数
	 */
	public void setQ_fightspirit_num(int q_fightspirit_num){
		this.q_fightspirit_num = q_fightspirit_num;
	}
	
	/**
	 * get 战魂得到类型
	 * @return 
	 */
	public int getQ_fightspirit_type(){
		return q_fightspirit_type;
	}
	
	/**
	 * set 战魂得到类型
	 */
	public void setQ_fightspirit_type(int q_fightspirit_type){
		this.q_fightspirit_type = q_fightspirit_type;
	}
	
	/**
	 * get 默认获得战魂数
	 * @return 
	 */
	public int getQ_getfightspirit(){
		return q_getfightspirit;
	}
	
	/**
	 * set 默认获得战魂数
	 */
	public void setQ_getfightspirit(int q_getfightspirit){
		this.q_getfightspirit = q_getfightspirit;
	}
	
	/**
	 * get 默认所需元宝数
	 * @return 
	 */
	public int getQ_yuanbao(){
		return q_yuanbao;
	}
	
	/**
	 * set 默认所需元宝数
	 */
	public void setQ_yuanbao(int q_yuanbao){
		this.q_yuanbao = q_yuanbao;
	}
	
}
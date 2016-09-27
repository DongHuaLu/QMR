package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_longyuan_exp Bean
 */
public class Q_longyuan_expBean {

	//人物等级
	private int q_lv;
	
	//星位激活失败增加经验
	private int q_fail_exp;
	
	//星位激活成功增加他人经验
	private int q_success_others_exp;
	
	//星位激活成功增加经验
	private int q_success_exp;
	
	//每日最多可获得星图激活分享经验数量
	private int q_max_exp;
	
	
	/**
	 * get 人物等级
	 * @return 
	 */
	public int getQ_lv(){
		return q_lv;
	}
	
	/**
	 * set 人物等级
	 */
	public void setQ_lv(int q_lv){
		this.q_lv = q_lv;
	}
	
	/**
	 * get 星位激活失败增加经验
	 * @return 
	 */
	public int getQ_fail_exp(){
		return q_fail_exp;
	}
	
	/**
	 * set 星位激活失败增加经验
	 */
	public void setQ_fail_exp(int q_fail_exp){
		this.q_fail_exp = q_fail_exp;
	}
	
	/**
	 * get 星位激活成功增加他人经验
	 * @return 
	 */
	public int getQ_success_others_exp(){
		return q_success_others_exp;
	}
	
	/**
	 * set 星位激活成功增加他人经验
	 */
	public void setQ_success_others_exp(int q_success_others_exp){
		this.q_success_others_exp = q_success_others_exp;
	}
	
	/**
	 * get 星位激活成功增加经验
	 * @return 
	 */
	public int getQ_success_exp(){
		return q_success_exp;
	}
	
	/**
	 * set 星位激活成功增加经验
	 */
	public void setQ_success_exp(int q_success_exp){
		this.q_success_exp = q_success_exp;
	}
	
	/**
	 * get 每日最多可获得星图激活分享经验数量
	 * @return 
	 */
	public int getQ_max_exp(){
		return q_max_exp;
	}
	
	/**
	 * set 每日最多可获得星图激活分享经验数量
	 */
	public void setQ_max_exp(int q_max_exp){
		this.q_max_exp = q_max_exp;
	}
	
}
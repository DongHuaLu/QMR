package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_energy_prize Bean
 */
public class Q_energy_prizeBean {

	//编号
	private int q_id;
	
	//类型(1:日常,2:剧情,3:七曜,4:文韬武略,5:讨伐,6:闭关)
	private int q_type;
	
	//最小等级
	private int q_level_min;
	
	//最大等级
	private int q_level_max;
	
	//消耗精力值
	private int q_expend;
	
	//奖励字符串(id,num,sex,bind,overtime),多个的话以;分割
	private String q_prize;
	
	//参数
	private int q_para;
	
	
	/**
	 * get 编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 类型(1:日常,2:剧情,3:七曜,4:文韬武略,5:讨伐,6:闭关)
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 类型(1:日常,2:剧情,3:七曜,4:文韬武略,5:讨伐,6:闭关)
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 最小等级
	 * @return 
	 */
	public int getQ_level_min(){
		return q_level_min;
	}
	
	/**
	 * set 最小等级
	 */
	public void setQ_level_min(int q_level_min){
		this.q_level_min = q_level_min;
	}
	
	/**
	 * get 最大等级
	 * @return 
	 */
	public int getQ_level_max(){
		return q_level_max;
	}
	
	/**
	 * set 最大等级
	 */
	public void setQ_level_max(int q_level_max){
		this.q_level_max = q_level_max;
	}
	
	/**
	 * get 消耗精力值
	 * @return 
	 */
	public int getQ_expend(){
		return q_expend;
	}
	
	/**
	 * set 消耗精力值
	 */
	public void setQ_expend(int q_expend){
		this.q_expend = q_expend;
	}
	
	/**
	 * get 奖励字符串(id,num,sex,bind,overtime),多个的话以;分割
	 * @return 
	 */
	public String getQ_prize(){
		return q_prize;
	}
	
	/**
	 * set 奖励字符串(id,num,sex,bind,overtime),多个的话以;分割
	 */
	public void setQ_prize(String q_prize){
		this.q_prize = q_prize;
	}
	
	/**
	 * get 参数
	 * @return 
	 */
	public int getQ_para(){
		return q_para;
	}
	
	/**
	 * set 参数
	 */
	public void setQ_para(int q_para){
		this.q_para = q_para;
	}
	
}
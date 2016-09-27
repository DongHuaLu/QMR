package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horse_skill_exp Bean
 */
public class Q_horse_skill_expBean {

	//技能ID_技能等级
	private String q_skillid;
	
	//升级所需要的经验
	private int q_need_exp;
	
	
	/**
	 * get 技能ID_技能等级
	 * @return 
	 */
	public String getQ_skillid(){
		return q_skillid;
	}
	
	/**
	 * set 技能ID_技能等级
	 */
	public void setQ_skillid(String q_skillid){
		this.q_skillid = q_skillid;
	}
	
	/**
	 * get 升级所需要的经验
	 * @return 
	 */
	public int getQ_need_exp(){
		return q_need_exp;
	}
	
	/**
	 * set 升级所需要的经验
	 */
	public void setQ_need_exp(int q_need_exp){
		this.q_need_exp = q_need_exp;
	}
	
}
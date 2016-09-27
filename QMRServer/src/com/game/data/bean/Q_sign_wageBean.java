package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_sign_wage Bean
 */
public class Q_sign_wageBean {

	//签到次数
	private int q_sign_num;
	
	//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
	private String q_reward;
	
	//VIP额外奖励
	private String q_vip_reward;
	
	
	/**
	 * get 签到次数
	 * @return 
	 */
	public int getQ_sign_num(){
		return q_sign_num;
	}
	
	/**
	 * set 签到次数
	 */
	public void setQ_sign_num(int q_sign_num){
		this.q_sign_num = q_sign_num;
	}
	
	/**
	 * get 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
	 * @return 
	 */
	public String getQ_reward(){
		return q_reward;
	}
	
	/**
	 * set 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
	 */
	public void setQ_reward(String q_reward){
		this.q_reward = q_reward;
	}
	
	/**
	 * get VIP额外奖励
	 * @return 
	 */
	public String getQ_vip_reward(){
		return q_vip_reward;
	}
	
	/**
	 * set VIP额外奖励
	 */
	public void setQ_vip_reward(String q_vip_reward){
		this.q_vip_reward = q_vip_reward;
	}
	
}
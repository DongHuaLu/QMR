package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_phone_update Bean
 */
public class Q_phone_updateBean {

	//索引
	private int q_id;
	
	//版本号
	private String q_edition;
	
	//等级
	private int q_level;
	
	//大小
	private int q_size;
	
	//内容
	private String q_content;
	
	//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,(1212311059消失时间),强化等级,附加属性(类型|值);道具ID,数量）
	private String q_reward;
	
	
	/**
	 * get 索引
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 索引
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 版本号
	 * @return 
	 */
	public String getQ_edition(){
		return q_edition;
	}
	
	/**
	 * set 版本号
	 */
	public void setQ_edition(String q_edition){
		this.q_edition = q_edition;
	}
	
	/**
	 * get 等级
	 * @return 
	 */
	public int getQ_level(){
		return q_level;
	}
	
	/**
	 * set 等级
	 */
	public void setQ_level(int q_level){
		this.q_level = q_level;
	}
	
	/**
	 * get 大小
	 * @return 
	 */
	public int getQ_size(){
		return q_size;
	}
	
	/**
	 * set 大小
	 */
	public void setQ_size(int q_size){
		this.q_size = q_size;
	}
	
	/**
	 * get 内容
	 * @return 
	 */
	public String getQ_content(){
		return q_content;
	}
	
	/**
	 * set 内容
	 */
	public void setQ_content(String q_content){
		this.q_content = q_content;
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
	
}
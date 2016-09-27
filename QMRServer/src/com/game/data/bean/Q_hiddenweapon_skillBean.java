package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_hiddenweapon_skill Bean
 */
public class Q_hiddenweapon_skillBean {

	//技能ID
	private String q_skill;
	
	//所需暗器阶数
	private int q_need_level;
	
	//技能书减少投掷次数
	private int q_reduce_times;
	
	//技能类型(0:非攻击次数触发 1:攻击次数触发)
	private int q_type;
	
	//替换互斥概率（暂时不用）
	private int q_prob;
	
	//关联物品id
	private int q_item_id;
	
	
	/**
	 * get 技能ID
	 * @return 
	 */
	public String getQ_skill(){
		return q_skill;
	}
	
	/**
	 * set 技能ID
	 */
	public void setQ_skill(String q_skill){
		this.q_skill = q_skill;
	}
	
	/**
	 * get 所需暗器阶数
	 * @return 
	 */
	public int getQ_need_level(){
		return q_need_level;
	}
	
	/**
	 * set 所需暗器阶数
	 */
	public void setQ_need_level(int q_need_level){
		this.q_need_level = q_need_level;
	}
	
	/**
	 * get 技能书减少投掷次数
	 * @return 
	 */
	public int getQ_reduce_times(){
		return q_reduce_times;
	}
	
	/**
	 * set 技能书减少投掷次数
	 */
	public void setQ_reduce_times(int q_reduce_times){
		this.q_reduce_times = q_reduce_times;
	}
	
	/**
	 * get 技能类型(0:非攻击次数触发 1:攻击次数触发)
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 技能类型(0:非攻击次数触发 1:攻击次数触发)
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 替换互斥概率（暂时不用）
	 * @return 
	 */
	public int getQ_prob(){
		return q_prob;
	}
	
	/**
	 * set 替换互斥概率（暂时不用）
	 */
	public void setQ_prob(int q_prob){
		this.q_prob = q_prob;
	}
	
	/**
	 * get 关联物品id
	 * @return 
	 */
	public int getQ_item_id(){
		return q_item_id;
	}
	
	/**
	 * set 关联物品id
	 */
	public void setQ_item_id(int q_item_id){
		this.q_item_id = q_item_id;
	}
	
}
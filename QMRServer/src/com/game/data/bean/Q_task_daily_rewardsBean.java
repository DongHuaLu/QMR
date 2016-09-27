package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_daily_rewards Bean
 */
public class Q_task_daily_rewardsBean {

	//奖励编号
	private int q_id;
	
	//玩家等级区间min
	private int q_mingrade;
	
	//玩家等级区间max
	private int q_maxgrade;
	
	//任务奖励丰厚度级别
	private int q_rich;
	
	//任务奖励达成某成就序列（成就编号;成就编号）
	private String q_rewards_achieve;
	
	//任务奖励经验
	private int q_rewards_exp;
	
	//任务奖励铜钱
	private int q_rewards_coin;
	
	//任务奖励真气
	private int q_rewards_zq;
	
	//任务奖励声望
	private int q_rewards_prestige;
	
	//任务奖励绑定元宝
	private int q_rewards_bindyuanbao;
	
	//任务奖励物品序列（物品ID_数量_性别需求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;物品ID_数量_性别需求;物品ID_数量）
	private String q_rewards_goods;
	
	
	/**
	 * get 奖励编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 奖励编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 玩家等级区间min
	 * @return 
	 */
	public int getQ_mingrade(){
		return q_mingrade;
	}
	
	/**
	 * set 玩家等级区间min
	 */
	public void setQ_mingrade(int q_mingrade){
		this.q_mingrade = q_mingrade;
	}
	
	/**
	 * get 玩家等级区间max
	 * @return 
	 */
	public int getQ_maxgrade(){
		return q_maxgrade;
	}
	
	/**
	 * set 玩家等级区间max
	 */
	public void setQ_maxgrade(int q_maxgrade){
		this.q_maxgrade = q_maxgrade;
	}
	
	/**
	 * get 任务奖励丰厚度级别
	 * @return 
	 */
	public int getQ_rich(){
		return q_rich;
	}
	
	/**
	 * set 任务奖励丰厚度级别
	 */
	public void setQ_rich(int q_rich){
		this.q_rich = q_rich;
	}
	
	/**
	 * get 任务奖励达成某成就序列（成就编号;成就编号）
	 * @return 
	 */
	public String getQ_rewards_achieve(){
		return q_rewards_achieve;
	}
	
	/**
	 * set 任务奖励达成某成就序列（成就编号;成就编号）
	 */
	public void setQ_rewards_achieve(String q_rewards_achieve){
		this.q_rewards_achieve = q_rewards_achieve;
	}
	
	/**
	 * get 任务奖励经验
	 * @return 
	 */
	public int getQ_rewards_exp(){
		return q_rewards_exp;
	}
	
	/**
	 * set 任务奖励经验
	 */
	public void setQ_rewards_exp(int q_rewards_exp){
		this.q_rewards_exp = q_rewards_exp;
	}
	
	/**
	 * get 任务奖励铜钱
	 * @return 
	 */
	public int getQ_rewards_coin(){
		return q_rewards_coin;
	}
	
	/**
	 * set 任务奖励铜钱
	 */
	public void setQ_rewards_coin(int q_rewards_coin){
		this.q_rewards_coin = q_rewards_coin;
	}
	
	/**
	 * get 任务奖励真气
	 * @return 
	 */
	public int getQ_rewards_zq(){
		return q_rewards_zq;
	}
	
	/**
	 * set 任务奖励真气
	 */
	public void setQ_rewards_zq(int q_rewards_zq){
		this.q_rewards_zq = q_rewards_zq;
	}
	
	/**
	 * get 任务奖励声望
	 * @return 
	 */
	public int getQ_rewards_prestige(){
		return q_rewards_prestige;
	}
	
	/**
	 * set 任务奖励声望
	 */
	public void setQ_rewards_prestige(int q_rewards_prestige){
		this.q_rewards_prestige = q_rewards_prestige;
	}
	
	/**
	 * get 任务奖励绑定元宝
	 * @return 
	 */
	public int getQ_rewards_bindyuanbao(){
		return q_rewards_bindyuanbao;
	}
	
	/**
	 * set 任务奖励绑定元宝
	 */
	public void setQ_rewards_bindyuanbao(int q_rewards_bindyuanbao){
		this.q_rewards_bindyuanbao = q_rewards_bindyuanbao;
	}
	
	/**
	 * get 任务奖励物品序列（物品ID_数量_性别需求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;物品ID_数量_性别需求;物品ID_数量）
	 * @return 
	 */
	public String getQ_rewards_goods(){
		return q_rewards_goods;
	}
	
	/**
	 * set 任务奖励物品序列（物品ID_数量_性别需求_强化等级_附加属性类型1|附加属性比例,附加属性类型2|附加属性比例;物品ID_数量_性别需求;物品ID_数量）
	 */
	public void setQ_rewards_goods(String q_rewards_goods){
		this.q_rewards_goods = q_rewards_goods;
	}
	
}
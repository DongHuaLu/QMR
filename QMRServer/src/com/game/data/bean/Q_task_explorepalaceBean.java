package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_explorepalace Bean
 */
public class Q_task_explorepalaceBean {

	//任务编号
	private int q_id;
	
	//玩家等级区间min
	private int q_mingrade;
	
	//玩家等级区间max
	private int q_maxgrade;
	
	//任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
	private String q_end_needkillmonster;
	
	//任务奖励经验
	private int q_rewards_exp;
	
	//任务奖励铜币
	private int q_rewards_coin;
	
	//任务奖励真气
	private int q_rewards_zq;
	
	//任务奖励礼金
	private int q_rewards_bindyuanbao;
	
	//任务奖励声望
	private int q_rewards_prestige;
	
	//完成任务需消耗元宝
	private int q_usegold;
	
	
	/**
	 * get 任务编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 任务编号
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
	 * get 任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
	 * @return 
	 */
	public String getQ_end_needkillmonster(){
		return q_end_needkillmonster;
	}
	
	/**
	 * set 任务条件检测杀死怪物序列（怪物ID_需求数量_寻径链接）
	 */
	public void setQ_end_needkillmonster(String q_end_needkillmonster){
		this.q_end_needkillmonster = q_end_needkillmonster;
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
	 * get 任务奖励铜币
	 * @return 
	 */
	public int getQ_rewards_coin(){
		return q_rewards_coin;
	}
	
	/**
	 * set 任务奖励铜币
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
	 * get 任务奖励礼金
	 * @return 
	 */
	public int getQ_rewards_bindyuanbao(){
		return q_rewards_bindyuanbao;
	}
	
	/**
	 * set 任务奖励礼金
	 */
	public void setQ_rewards_bindyuanbao(int q_rewards_bindyuanbao){
		this.q_rewards_bindyuanbao = q_rewards_bindyuanbao;
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
	 * get 完成任务需消耗元宝
	 * @return 
	 */
	public int getQ_usegold(){
		return q_usegold;
	}
	
	/**
	 * set 完成任务需消耗元宝
	 */
	public void setQ_usegold(int q_usegold){
		this.q_usegold = q_usegold;
	}
	
}
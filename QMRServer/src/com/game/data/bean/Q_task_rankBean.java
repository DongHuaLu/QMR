package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_rank Bean
 */
public class Q_task_rankBean {

	//军衔任务编号
	private int q_id;
	
	//任务名称
	private String q_name;
	
	//军衔日常任务描述【每个任务每天可完成一次】
	private String q_desc;
	
	//任务类型
	private int q_type;
	
	//任务要求（[目标,需要条件]）
	private String q_condition;
	
	//前一个任务(没有就填写0)
	private int q_front_task;
	
	//下一个任务(没有就填写0)
	private int q_next_task;
	
	//完成任务军功值奖励
	private int q_rewards_rank;
	
	//任务显示等级
	private int q_show_level;
	
	//快速完成花费元宝数量
	private int q_quickfinsh_gold;
	
	
	/**
	 * get 军衔任务编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 军衔任务编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 任务名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set 任务名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 军衔日常任务描述【每个任务每天可完成一次】
	 * @return 
	 */
	public String getQ_desc(){
		return q_desc;
	}
	
	/**
	 * set 军衔日常任务描述【每个任务每天可完成一次】
	 */
	public void setQ_desc(String q_desc){
		this.q_desc = q_desc;
	}
	
	/**
	 * get 任务类型
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 任务类型
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 任务要求（[目标,需要条件]）
	 * @return 
	 */
	public String getQ_condition(){
		return q_condition;
	}
	
	/**
	 * set 任务要求（[目标,需要条件]）
	 */
	public void setQ_condition(String q_condition){
		this.q_condition = q_condition;
	}
	
	/**
	 * get 前一个任务(没有就填写0)
	 * @return 
	 */
	public int getQ_front_task(){
		return q_front_task;
	}
	
	/**
	 * set 前一个任务(没有就填写0)
	 */
	public void setQ_front_task(int q_front_task){
		this.q_front_task = q_front_task;
	}
	
	/**
	 * get 下一个任务(没有就填写0)
	 * @return 
	 */
	public int getQ_next_task(){
		return q_next_task;
	}
	
	/**
	 * set 下一个任务(没有就填写0)
	 */
	public void setQ_next_task(int q_next_task){
		this.q_next_task = q_next_task;
	}
	
	/**
	 * get 完成任务军功值奖励
	 * @return 
	 */
	public int getQ_rewards_rank(){
		return q_rewards_rank;
	}
	
	/**
	 * set 完成任务军功值奖励
	 */
	public void setQ_rewards_rank(int q_rewards_rank){
		this.q_rewards_rank = q_rewards_rank;
	}
	
	/**
	 * get 任务显示等级
	 * @return 
	 */
	public int getQ_show_level(){
		return q_show_level;
	}
	
	/**
	 * set 任务显示等级
	 */
	public void setQ_show_level(int q_show_level){
		this.q_show_level = q_show_level;
	}
	
	/**
	 * get 快速完成花费元宝数量
	 * @return 
	 */
	public int getQ_quickfinsh_gold(){
		return q_quickfinsh_gold;
	}
	
	/**
	 * set 快速完成花费元宝数量
	 */
	public void setQ_quickfinsh_gold(int q_quickfinsh_gold){
		this.q_quickfinsh_gold = q_quickfinsh_gold;
	}
	
}
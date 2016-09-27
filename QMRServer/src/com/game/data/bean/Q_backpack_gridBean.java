package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_backpack_grid Bean
 */
public class Q_backpack_gridBean {

	//所属系统（1背包格子，2仓库格子）+格子编号
	private String q_backpack_q_grid;
	
	//所属系统（1背包格子，2仓库格子）
	private int q_backpack;
	
	//格子编号
	private int q_grid;
	
	//自动成长开格所需时间（单位:秒）
	private int q_time;
	
	//手动开启所需元宝费用
	private int q_cost;
	
	//成功开启后增加人物经验
	private int q_exp;
	
	//成功开启后增加人物血量
	private int q_maxhp;
	
	
	/**
	 * get 所属系统（1背包格子，2仓库格子）+格子编号
	 * @return 
	 */
	public String getQ_backpack_q_grid(){
		return q_backpack_q_grid;
	}
	
	/**
	 * set 所属系统（1背包格子，2仓库格子）+格子编号
	 */
	public void setQ_backpack_q_grid(String q_backpack_q_grid){
		this.q_backpack_q_grid = q_backpack_q_grid;
	}
	
	/**
	 * get 所属系统（1背包格子，2仓库格子）
	 * @return 
	 */
	public int getQ_backpack(){
		return q_backpack;
	}
	
	/**
	 * set 所属系统（1背包格子，2仓库格子）
	 */
	public void setQ_backpack(int q_backpack){
		this.q_backpack = q_backpack;
	}
	
	/**
	 * get 格子编号
	 * @return 
	 */
	public int getQ_grid(){
		return q_grid;
	}
	
	/**
	 * set 格子编号
	 */
	public void setQ_grid(int q_grid){
		this.q_grid = q_grid;
	}
	
	/**
	 * get 自动成长开格所需时间（单位:秒）
	 * @return 
	 */
	public int getQ_time(){
		return q_time;
	}
	
	/**
	 * set 自动成长开格所需时间（单位:秒）
	 */
	public void setQ_time(int q_time){
		this.q_time = q_time;
	}
	
	/**
	 * get 手动开启所需元宝费用
	 * @return 
	 */
	public int getQ_cost(){
		return q_cost;
	}
	
	/**
	 * set 手动开启所需元宝费用
	 */
	public void setQ_cost(int q_cost){
		this.q_cost = q_cost;
	}
	
	/**
	 * get 成功开启后增加人物经验
	 * @return 
	 */
	public int getQ_exp(){
		return q_exp;
	}
	
	/**
	 * set 成功开启后增加人物经验
	 */
	public void setQ_exp(int q_exp){
		this.q_exp = q_exp;
	}
	
	/**
	 * get 成功开启后增加人物血量
	 * @return 
	 */
	public int getQ_maxhp(){
		return q_maxhp;
	}
	
	/**
	 * set 成功开启后增加人物血量
	 */
	public void setQ_maxhp(int q_maxhp){
		this.q_maxhp = q_maxhp;
	}
	
}
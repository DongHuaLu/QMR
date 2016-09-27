package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_task_daily_cond Bean
 */
public class Q_task_daily_condBean {

	//任务编号
	private int q_id;
	
	//玩家等级区间min
	private int q_mingrade;
	
	//玩家等级区间max
	private int q_maxgrade;
	
	//任务完成条件检测物品（物品ID_需求数量_寻径链接(地图ID_x_y);物品ID_需求数量_寻径链接(地图ID_x_y);）
	private String q_end_needgoods;
	
	//任务完成条件检测杀死怪物序列（怪物ID_需求数量_寻径链接(地图ID_x_y);怪物ID_需求数量_寻径链接(地图ID_x_y);）
	private String q_end_needkillmonster;
	
	//任务完成条件检测成就序列（成就编号_寻径链接;成就编号_寻径链接）
	private String q_end_needachieve;
	
	//任务目标难度级别
	private int q_hard;
	
	//完成时收取物品序列(物品ID_数量;物品ID_数量）
	private String q_resume_goods;
	
	
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
	 * get 任务完成条件检测物品（物品ID_需求数量_寻径链接(地图ID_x_y);物品ID_需求数量_寻径链接(地图ID_x_y);）
	 * @return 
	 */
	public String getQ_end_needgoods(){
		return q_end_needgoods;
	}
	
	/**
	 * set 任务完成条件检测物品（物品ID_需求数量_寻径链接(地图ID_x_y);物品ID_需求数量_寻径链接(地图ID_x_y);）
	 */
	public void setQ_end_needgoods(String q_end_needgoods){
		this.q_end_needgoods = q_end_needgoods;
	}
	
	/**
	 * get 任务完成条件检测杀死怪物序列（怪物ID_需求数量_寻径链接(地图ID_x_y);怪物ID_需求数量_寻径链接(地图ID_x_y);）
	 * @return 
	 */
	public String getQ_end_needkillmonster(){
		return q_end_needkillmonster;
	}
	
	/**
	 * set 任务完成条件检测杀死怪物序列（怪物ID_需求数量_寻径链接(地图ID_x_y);怪物ID_需求数量_寻径链接(地图ID_x_y);）
	 */
	public void setQ_end_needkillmonster(String q_end_needkillmonster){
		this.q_end_needkillmonster = q_end_needkillmonster;
	}
	
	/**
	 * get 任务完成条件检测成就序列（成就编号_寻径链接;成就编号_寻径链接）
	 * @return 
	 */
	public String getQ_end_needachieve(){
		return q_end_needachieve;
	}
	
	/**
	 * set 任务完成条件检测成就序列（成就编号_寻径链接;成就编号_寻径链接）
	 */
	public void setQ_end_needachieve(String q_end_needachieve){
		this.q_end_needachieve = q_end_needachieve;
	}
	
	/**
	 * get 任务目标难度级别
	 * @return 
	 */
	public int getQ_hard(){
		return q_hard;
	}
	
	/**
	 * set 任务目标难度级别
	 */
	public void setQ_hard(int q_hard){
		this.q_hard = q_hard;
	}
	
	/**
	 * get 完成时收取物品序列(物品ID_数量;物品ID_数量）
	 * @return 
	 */
	public String getQ_resume_goods(){
		return q_resume_goods;
	}
	
	/**
	 * set 完成时收取物品序列(物品ID_数量;物品ID_数量）
	 */
	public void setQ_resume_goods(String q_resume_goods){
		this.q_resume_goods = q_resume_goods;
	}
	
}
package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_fourjinsuo Bean
 */
public class Q_fourjinsuoBean {

	//四门金锁阵波次
	private int q_id;
	
	//本波刷新怪物数量
	private int q_num;
	
	//本波小怪出现间隔时间（毫秒）
	private int q_mobs_interval_num;
	
	//本波小怪移动速度
	private int q_move_speed;
	
	//本波怪物等级加成参数
	private int q_mon_level_add;
	
	//本波守将等级加成参数
	private int q_morimasa_level_add;
	
	//波次刷新间隔时间（单位：秒）
	private int q_interval_num;
	
	
	/**
	 * get 四门金锁阵波次
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 四门金锁阵波次
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 本波刷新怪物数量
	 * @return 
	 */
	public int getQ_num(){
		return q_num;
	}
	
	/**
	 * set 本波刷新怪物数量
	 */
	public void setQ_num(int q_num){
		this.q_num = q_num;
	}
	
	/**
	 * get 本波小怪出现间隔时间（毫秒）
	 * @return 
	 */
	public int getQ_mobs_interval_num(){
		return q_mobs_interval_num;
	}
	
	/**
	 * set 本波小怪出现间隔时间（毫秒）
	 */
	public void setQ_mobs_interval_num(int q_mobs_interval_num){
		this.q_mobs_interval_num = q_mobs_interval_num;
	}
	
	/**
	 * get 本波小怪移动速度
	 * @return 
	 */
	public int getQ_move_speed(){
		return q_move_speed;
	}
	
	/**
	 * set 本波小怪移动速度
	 */
	public void setQ_move_speed(int q_move_speed){
		this.q_move_speed = q_move_speed;
	}
	
	/**
	 * get 本波怪物等级加成参数
	 * @return 
	 */
	public int getQ_mon_level_add(){
		return q_mon_level_add;
	}
	
	/**
	 * set 本波怪物等级加成参数
	 */
	public void setQ_mon_level_add(int q_mon_level_add){
		this.q_mon_level_add = q_mon_level_add;
	}
	
	/**
	 * get 本波守将等级加成参数
	 * @return 
	 */
	public int getQ_morimasa_level_add(){
		return q_morimasa_level_add;
	}
	
	/**
	 * set 本波守将等级加成参数
	 */
	public void setQ_morimasa_level_add(int q_morimasa_level_add){
		this.q_morimasa_level_add = q_morimasa_level_add;
	}
	
	/**
	 * get 波次刷新间隔时间（单位：秒）
	 * @return 
	 */
	public int getQ_interval_num(){
		return q_interval_num;
	}
	
	/**
	 * set 波次刷新间隔时间（单位：秒）
	 */
	public void setQ_interval_num(int q_interval_num){
		this.q_interval_num = q_interval_num;
	}
	
}
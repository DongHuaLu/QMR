package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scene_obj Bean
 */
public class Q_scene_objBean {

	//id
	private int q_id;
	
	//NPCID
	private String q_npc_id;
	
	//怪物ID[怪物ID,怪物ID]
	private String q_monster_id;
	
	//刷新时间
	private String q_refresh_time;
	
	//刷新地图
	private int q_refresh_map;
	
	//刷新坐标
	private String q_refresh_coordinate;
	
	//附带奖励
	private String q_reward;
	
	//调用类型(服务器端调用的类)
	private String q_class;
	
	
	/**
	 * get id
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set id
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get NPCID
	 * @return 
	 */
	public String getQ_npc_id(){
		return q_npc_id;
	}
	
	/**
	 * set NPCID
	 */
	public void setQ_npc_id(String q_npc_id){
		this.q_npc_id = q_npc_id;
	}
	
	/**
	 * get 怪物ID[怪物ID,怪物ID]
	 * @return 
	 */
	public String getQ_monster_id(){
		return q_monster_id;
	}
	
	/**
	 * set 怪物ID[怪物ID,怪物ID]
	 */
	public void setQ_monster_id(String q_monster_id){
		this.q_monster_id = q_monster_id;
	}
	
	/**
	 * get 刷新时间
	 * @return 
	 */
	public String getQ_refresh_time(){
		return q_refresh_time;
	}
	
	/**
	 * set 刷新时间
	 */
	public void setQ_refresh_time(String q_refresh_time){
		this.q_refresh_time = q_refresh_time;
	}
	
	/**
	 * get 刷新地图
	 * @return 
	 */
	public int getQ_refresh_map(){
		return q_refresh_map;
	}
	
	/**
	 * set 刷新地图
	 */
	public void setQ_refresh_map(int q_refresh_map){
		this.q_refresh_map = q_refresh_map;
	}
	
	/**
	 * get 刷新坐标
	 * @return 
	 */
	public String getQ_refresh_coordinate(){
		return q_refresh_coordinate;
	}
	
	/**
	 * set 刷新坐标
	 */
	public void setQ_refresh_coordinate(String q_refresh_coordinate){
		this.q_refresh_coordinate = q_refresh_coordinate;
	}
	
	/**
	 * get 附带奖励
	 * @return 
	 */
	public String getQ_reward(){
		return q_reward;
	}
	
	/**
	 * set 附带奖励
	 */
	public void setQ_reward(String q_reward){
		this.q_reward = q_reward;
	}
	
	/**
	 * get 调用类型(服务器端调用的类)
	 * @return 
	 */
	public String getQ_class(){
		return q_class;
	}
	
	/**
	 * set 调用类型(服务器端调用的类)
	 */
	public void setQ_class(String q_class){
		this.q_class = q_class;
	}
	
}
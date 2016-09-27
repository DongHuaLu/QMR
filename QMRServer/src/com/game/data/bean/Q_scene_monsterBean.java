package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_scene_monster Bean
 */
public class Q_scene_monsterBean {

	//刷怪编号
	private int q_id;
	
	//刷怪地图编号
	private int q_mapid;
	
	//怪物ID
	private int q_monster_model;
	
	//刷新坐标点X
	private int q_x;
	
	//刷新坐标点Y
	private int q_y;
	
	//重生时间脱离怪物数据库控制变更为：0本规则不启用， >0则为变更后的重生时间，单位：秒
	private int q_relive;
	
	//攻击类型脱离怪物数据库控制变更为：0本规则不启用，1变更为被动攻击类怪物，2变更为主动攻击类怪物
	private int q_attack;
	
	//巡逻间隔
	private int q_patrol_time;
	
	//巡逻几率
	private int q_patrol_pro;
	
	//是否显示怪物
	private int q_whether_display;
	
	
	/**
	 * get 刷怪编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 刷怪编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 刷怪地图编号
	 * @return 
	 */
	public int getQ_mapid(){
		return q_mapid;
	}
	
	/**
	 * set 刷怪地图编号
	 */
	public void setQ_mapid(int q_mapid){
		this.q_mapid = q_mapid;
	}
	
	/**
	 * get 怪物ID
	 * @return 
	 */
	public int getQ_monster_model(){
		return q_monster_model;
	}
	
	/**
	 * set 怪物ID
	 */
	public void setQ_monster_model(int q_monster_model){
		this.q_monster_model = q_monster_model;
	}
	
	/**
	 * get 刷新坐标点X
	 * @return 
	 */
	public int getQ_x(){
		return q_x;
	}
	
	/**
	 * set 刷新坐标点X
	 */
	public void setQ_x(int q_x){
		this.q_x = q_x;
	}
	
	/**
	 * get 刷新坐标点Y
	 * @return 
	 */
	public int getQ_y(){
		return q_y;
	}
	
	/**
	 * set 刷新坐标点Y
	 */
	public void setQ_y(int q_y){
		this.q_y = q_y;
	}
	
	/**
	 * get 重生时间脱离怪物数据库控制变更为：0本规则不启用， >0则为变更后的重生时间，单位：秒
	 * @return 
	 */
	public int getQ_relive(){
		return q_relive;
	}
	
	/**
	 * set 重生时间脱离怪物数据库控制变更为：0本规则不启用， >0则为变更后的重生时间，单位：秒
	 */
	public void setQ_relive(int q_relive){
		this.q_relive = q_relive;
	}
	
	/**
	 * get 攻击类型脱离怪物数据库控制变更为：0本规则不启用，1变更为被动攻击类怪物，2变更为主动攻击类怪物
	 * @return 
	 */
	public int getQ_attack(){
		return q_attack;
	}
	
	/**
	 * set 攻击类型脱离怪物数据库控制变更为：0本规则不启用，1变更为被动攻击类怪物，2变更为主动攻击类怪物
	 */
	public void setQ_attack(int q_attack){
		this.q_attack = q_attack;
	}
	
	/**
	 * get 巡逻间隔
	 * @return 
	 */
	public int getQ_patrol_time(){
		return q_patrol_time;
	}
	
	/**
	 * set 巡逻间隔
	 */
	public void setQ_patrol_time(int q_patrol_time){
		this.q_patrol_time = q_patrol_time;
	}
	
	/**
	 * get 巡逻几率
	 * @return 
	 */
	public int getQ_patrol_pro(){
		return q_patrol_pro;
	}
	
	/**
	 * set 巡逻几率
	 */
	public void setQ_patrol_pro(int q_patrol_pro){
		this.q_patrol_pro = q_patrol_pro;
	}
	
	/**
	 * get 是否显示怪物
	 * @return 
	 */
	public int getQ_whether_display(){
		return q_whether_display;
	}
	
	/**
	 * set 是否显示怪物
	 */
	public void setQ_whether_display(int q_whether_display){
		this.q_whether_display = q_whether_display;
	}
	
}
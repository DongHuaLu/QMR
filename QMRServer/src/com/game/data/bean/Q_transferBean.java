package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_transfer Bean
 */
public class Q_transferBean {

	//传送点编号
	private int q_tran_id;
	
	//开始传送地图ID
	private int q_tran_from_map;
	
	//传送出发点坐标，半径（格式：{X,Y}#半径）
	private String q_tran_from_range;
	
	//传送至地图ID
	private int q_tran_to_map;
	
	//传送至坐标点，半径，判定等级（格式：{X,Y}#半径@判断等级 ）
	private String q_tran_to_range;
	
	//传送点造型资源编号
	private String q_tran_icon;
	
	//地图名称描述（小地图TIPS，支持xml）
	private String q_mapdesc;
	
	//脚本调用（填写后传送至坐标点将无效）
	private int q_scriptid;
	
	
	/**
	 * get 传送点编号
	 * @return 
	 */
	public int getQ_tran_id(){
		return q_tran_id;
	}
	
	/**
	 * set 传送点编号
	 */
	public void setQ_tran_id(int q_tran_id){
		this.q_tran_id = q_tran_id;
	}
	
	/**
	 * get 开始传送地图ID
	 * @return 
	 */
	public int getQ_tran_from_map(){
		return q_tran_from_map;
	}
	
	/**
	 * set 开始传送地图ID
	 */
	public void setQ_tran_from_map(int q_tran_from_map){
		this.q_tran_from_map = q_tran_from_map;
	}
	
	/**
	 * get 传送出发点坐标，半径（格式：{X,Y}#半径）
	 * @return 
	 */
	public String getQ_tran_from_range(){
		return q_tran_from_range;
	}
	
	/**
	 * set 传送出发点坐标，半径（格式：{X,Y}#半径）
	 */
	public void setQ_tran_from_range(String q_tran_from_range){
		this.q_tran_from_range = q_tran_from_range;
	}
	
	/**
	 * get 传送至地图ID
	 * @return 
	 */
	public int getQ_tran_to_map(){
		return q_tran_to_map;
	}
	
	/**
	 * set 传送至地图ID
	 */
	public void setQ_tran_to_map(int q_tran_to_map){
		this.q_tran_to_map = q_tran_to_map;
	}
	
	/**
	 * get 传送至坐标点，半径，判定等级（格式：{X,Y}#半径@判断等级 ）
	 * @return 
	 */
	public String getQ_tran_to_range(){
		return q_tran_to_range;
	}
	
	/**
	 * set 传送至坐标点，半径，判定等级（格式：{X,Y}#半径@判断等级 ）
	 */
	public void setQ_tran_to_range(String q_tran_to_range){
		this.q_tran_to_range = q_tran_to_range;
	}
	
	/**
	 * get 传送点造型资源编号
	 * @return 
	 */
	public String getQ_tran_icon(){
		return q_tran_icon;
	}
	
	/**
	 * set 传送点造型资源编号
	 */
	public void setQ_tran_icon(String q_tran_icon){
		this.q_tran_icon = q_tran_icon;
	}
	
	/**
	 * get 地图名称描述（小地图TIPS，支持xml）
	 * @return 
	 */
	public String getQ_mapdesc(){
		return q_mapdesc;
	}
	
	/**
	 * set 地图名称描述（小地图TIPS，支持xml）
	 */
	public void setQ_mapdesc(String q_mapdesc){
		this.q_mapdesc = q_mapdesc;
	}
	
	/**
	 * get 脚本调用（填写后传送至坐标点将无效）
	 * @return 
	 */
	public int getQ_scriptid(){
		return q_scriptid;
	}
	
	/**
	 * set 脚本调用（填写后传送至坐标点将无效）
	 */
	public void setQ_scriptid(int q_scriptid){
		this.q_scriptid = q_scriptid;
	}
	
}
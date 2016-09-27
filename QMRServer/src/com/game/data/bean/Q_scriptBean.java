package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_script Bean
 */
public class Q_scriptBean {

	//脚本Id
	private int q_script_id;
	
	//脚本类(格式如com.game.item.script.Name)
	private String q_script_name;
	
	//游戏服务端脚本
	private int q_server;
	
	//世界服务器脚本
	private int q_world;
	
	
	/**
	 * get 脚本Id
	 * @return 
	 */
	public int getQ_script_id(){
		return q_script_id;
	}
	
	/**
	 * set 脚本Id
	 */
	public void setQ_script_id(int q_script_id){
		this.q_script_id = q_script_id;
	}
	
	/**
	 * get 脚本类(格式如com.game.item.script.Name)
	 * @return 
	 */
	public String getQ_script_name(){
		return q_script_name;
	}
	
	/**
	 * set 脚本类(格式如com.game.item.script.Name)
	 */
	public void setQ_script_name(String q_script_name){
		this.q_script_name = q_script_name;
	}
	
	/**
	 * get 游戏服务端脚本
	 * @return 
	 */
	public int getQ_server(){
		return q_server;
	}
	
	/**
	 * set 游戏服务端脚本
	 */
	public void setQ_server(int q_server){
		this.q_server = q_server;
	}
	
	/**
	 * get 世界服务器脚本
	 * @return 
	 */
	public int getQ_world(){
		return q_world;
	}
	
	/**
	 * set 世界服务器脚本
	 */
	public void setQ_world(int q_world){
		this.q_world = q_world;
	}
	
}
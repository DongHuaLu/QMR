package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_map_block Bean
 */
public class Q_map_blockBean {

	//场景id
	private int q_id;
	
	//阻挡信息
	private byte[] q_block;
	
	//透明信息
	private byte[] q_alpha;
	
	//安全区信息
	private byte[] q_safe;
	
	//跳跃信息
	private byte[] q_jump;
	
	//地图特效信息
	private byte[] q_effect;
	
	//禁止布怪信息
	private byte[] q_ban_monster;
	
	
	/**
	 * get 场景id
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 场景id
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 阻挡信息
	 * @return 
	 */
	public byte[] getQ_block(){
		return q_block;
	}
	
	/**
	 * set 阻挡信息
	 */
	public void setQ_block(byte[] q_block){
		this.q_block = q_block;
	}
	
	/**
	 * get 透明信息
	 * @return 
	 */
	public byte[] getQ_alpha(){
		return q_alpha;
	}
	
	/**
	 * set 透明信息
	 */
	public void setQ_alpha(byte[] q_alpha){
		this.q_alpha = q_alpha;
	}
	
	/**
	 * get 安全区信息
	 * @return 
	 */
	public byte[] getQ_safe(){
		return q_safe;
	}
	
	/**
	 * set 安全区信息
	 */
	public void setQ_safe(byte[] q_safe){
		this.q_safe = q_safe;
	}
	
	/**
	 * get 跳跃信息
	 * @return 
	 */
	public byte[] getQ_jump(){
		return q_jump;
	}
	
	/**
	 * set 跳跃信息
	 */
	public void setQ_jump(byte[] q_jump){
		this.q_jump = q_jump;
	}
	
	/**
	 * get 地图特效信息
	 * @return 
	 */
	public byte[] getQ_effect(){
		return q_effect;
	}
	
	/**
	 * set 地图特效信息
	 */
	public void setQ_effect(byte[] q_effect){
		this.q_effect = q_effect;
	}
	
	/**
	 * get 禁止布怪信息
	 * @return 
	 */
	public byte[] getQ_ban_monster(){
		return q_ban_monster;
	}
	
	/**
	 * set 禁止布怪信息
	 */
	public void setQ_ban_monster(byte[] q_ban_monster){
		this.q_ban_monster = q_ban_monster;
	}
	
}
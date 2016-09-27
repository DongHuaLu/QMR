package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gem_activation Bean
 */
public class Q_gem_activationBean {

	//q_pos_id+q_grid_id
	private String q_id;
	
	//部位编号
	private int q_pos_id;
	
	//宝石面板格子编号
	private int q_grid_id;
	
	//所允许镶嵌宝石类型
	private int q_gem_type;
	
	//初始宝石等级
	private int q_initial_level;
	
	//激活消耗材料ID序列与数量（物品ID_数量;物品ID_数量）
	private String q_consumable_item;
	
	//激活消耗真气
	private int q_consumable_zhenqi;
	
	//客户端显示用激活成功几率（本处需支持html）
	private String q_client_show_rnd;
	
	//激活成功所需次数min
	private int q_act_num_min;
	
	//服务器端计算用激活成功几率（万分比，本处填分子）
	private int q_act_rnd;
	
	//激活成功所需次数max
	private int q_act_num_max;
	
	//本部位上的宝石升到顶级是否发送全服公告（0不发送，1发送）
	private int q_notice;
	
	
	/**
	 * get q_pos_id+q_grid_id
	 * @return 
	 */
	public String getQ_id(){
		return q_id;
	}
	
	/**
	 * set q_pos_id+q_grid_id
	 */
	public void setQ_id(String q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 部位编号
	 * @return 
	 */
	public int getQ_pos_id(){
		return q_pos_id;
	}
	
	/**
	 * set 部位编号
	 */
	public void setQ_pos_id(int q_pos_id){
		this.q_pos_id = q_pos_id;
	}
	
	/**
	 * get 宝石面板格子编号
	 * @return 
	 */
	public int getQ_grid_id(){
		return q_grid_id;
	}
	
	/**
	 * set 宝石面板格子编号
	 */
	public void setQ_grid_id(int q_grid_id){
		this.q_grid_id = q_grid_id;
	}
	
	/**
	 * get 所允许镶嵌宝石类型
	 * @return 
	 */
	public int getQ_gem_type(){
		return q_gem_type;
	}
	
	/**
	 * set 所允许镶嵌宝石类型
	 */
	public void setQ_gem_type(int q_gem_type){
		this.q_gem_type = q_gem_type;
	}
	
	/**
	 * get 初始宝石等级
	 * @return 
	 */
	public int getQ_initial_level(){
		return q_initial_level;
	}
	
	/**
	 * set 初始宝石等级
	 */
	public void setQ_initial_level(int q_initial_level){
		this.q_initial_level = q_initial_level;
	}
	
	/**
	 * get 激活消耗材料ID序列与数量（物品ID_数量;物品ID_数量）
	 * @return 
	 */
	public String getQ_consumable_item(){
		return q_consumable_item;
	}
	
	/**
	 * set 激活消耗材料ID序列与数量（物品ID_数量;物品ID_数量）
	 */
	public void setQ_consumable_item(String q_consumable_item){
		this.q_consumable_item = q_consumable_item;
	}
	
	/**
	 * get 激活消耗真气
	 * @return 
	 */
	public int getQ_consumable_zhenqi(){
		return q_consumable_zhenqi;
	}
	
	/**
	 * set 激活消耗真气
	 */
	public void setQ_consumable_zhenqi(int q_consumable_zhenqi){
		this.q_consumable_zhenqi = q_consumable_zhenqi;
	}
	
	/**
	 * get 客户端显示用激活成功几率（本处需支持html）
	 * @return 
	 */
	public String getQ_client_show_rnd(){
		return q_client_show_rnd;
	}
	
	/**
	 * set 客户端显示用激活成功几率（本处需支持html）
	 */
	public void setQ_client_show_rnd(String q_client_show_rnd){
		this.q_client_show_rnd = q_client_show_rnd;
	}
	
	/**
	 * get 激活成功所需次数min
	 * @return 
	 */
	public int getQ_act_num_min(){
		return q_act_num_min;
	}
	
	/**
	 * set 激活成功所需次数min
	 */
	public void setQ_act_num_min(int q_act_num_min){
		this.q_act_num_min = q_act_num_min;
	}
	
	/**
	 * get 服务器端计算用激活成功几率（万分比，本处填分子）
	 * @return 
	 */
	public int getQ_act_rnd(){
		return q_act_rnd;
	}
	
	/**
	 * set 服务器端计算用激活成功几率（万分比，本处填分子）
	 */
	public void setQ_act_rnd(int q_act_rnd){
		this.q_act_rnd = q_act_rnd;
	}
	
	/**
	 * get 激活成功所需次数max
	 * @return 
	 */
	public int getQ_act_num_max(){
		return q_act_num_max;
	}
	
	/**
	 * set 激活成功所需次数max
	 */
	public void setQ_act_num_max(int q_act_num_max){
		this.q_act_num_max = q_act_num_max;
	}
	
	/**
	 * get 本部位上的宝石升到顶级是否发送全服公告（0不发送，1发送）
	 * @return 
	 */
	public int getQ_notice(){
		return q_notice;
	}
	
	/**
	 * set 本部位上的宝石升到顶级是否发送全服公告（0不发送，1发送）
	 */
	public void setQ_notice(int q_notice){
		this.q_notice = q_notice;
	}
	
}
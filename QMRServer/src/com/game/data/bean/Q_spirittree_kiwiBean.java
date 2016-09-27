package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_spirittree_kiwi Bean
 */
public class Q_spirittree_kiwiBean {

	//编号
	private String q_id;
	
	//类型（1银色奇异果，2金色奇异果）
	private int q_type;
	
	//组包ID（关联细则表字段）
	private int q_packet_id;
	
	//组包名称（显示于TIPS）
	private String q_packet_name;
	
	//组包描述（显示于TIPS）
	private String q_describe;
	
	//是否显示于奖励面板上
	private int q_is_show;
	
	//天生光效资源编号
	private int q_lightefficiency_id;
	
	//出现几率（万分比）
	private int q_arise_rnd;
	
	//参选所需最低人物等级
	private int q_need_level;
	
	//中选后的复现次数（最大为10）
	private int q_check_num;
	
	//参选所需排出等级（小于等于）
	private int q_exclude_level;
	
	
	/**
	 * get 编号
	 * @return 
	 */
	public String getQ_id(){
		return q_id;
	}
	
	/**
	 * set 编号
	 */
	public void setQ_id(String q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 类型（1银色奇异果，2金色奇异果）
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 类型（1银色奇异果，2金色奇异果）
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 组包ID（关联细则表字段）
	 * @return 
	 */
	public int getQ_packet_id(){
		return q_packet_id;
	}
	
	/**
	 * set 组包ID（关联细则表字段）
	 */
	public void setQ_packet_id(int q_packet_id){
		this.q_packet_id = q_packet_id;
	}
	
	/**
	 * get 组包名称（显示于TIPS）
	 * @return 
	 */
	public String getQ_packet_name(){
		return q_packet_name;
	}
	
	/**
	 * set 组包名称（显示于TIPS）
	 */
	public void setQ_packet_name(String q_packet_name){
		this.q_packet_name = q_packet_name;
	}
	
	/**
	 * get 组包描述（显示于TIPS）
	 * @return 
	 */
	public String getQ_describe(){
		return q_describe;
	}
	
	/**
	 * set 组包描述（显示于TIPS）
	 */
	public void setQ_describe(String q_describe){
		this.q_describe = q_describe;
	}
	
	/**
	 * get 是否显示于奖励面板上
	 * @return 
	 */
	public int getQ_is_show(){
		return q_is_show;
	}
	
	/**
	 * set 是否显示于奖励面板上
	 */
	public void setQ_is_show(int q_is_show){
		this.q_is_show = q_is_show;
	}
	
	/**
	 * get 天生光效资源编号
	 * @return 
	 */
	public int getQ_lightefficiency_id(){
		return q_lightefficiency_id;
	}
	
	/**
	 * set 天生光效资源编号
	 */
	public void setQ_lightefficiency_id(int q_lightefficiency_id){
		this.q_lightefficiency_id = q_lightefficiency_id;
	}
	
	/**
	 * get 出现几率（万分比）
	 * @return 
	 */
	public int getQ_arise_rnd(){
		return q_arise_rnd;
	}
	
	/**
	 * set 出现几率（万分比）
	 */
	public void setQ_arise_rnd(int q_arise_rnd){
		this.q_arise_rnd = q_arise_rnd;
	}
	
	/**
	 * get 参选所需最低人物等级
	 * @return 
	 */
	public int getQ_need_level(){
		return q_need_level;
	}
	
	/**
	 * set 参选所需最低人物等级
	 */
	public void setQ_need_level(int q_need_level){
		this.q_need_level = q_need_level;
	}
	
	/**
	 * get 中选后的复现次数（最大为10）
	 * @return 
	 */
	public int getQ_check_num(){
		return q_check_num;
	}
	
	/**
	 * set 中选后的复现次数（最大为10）
	 */
	public void setQ_check_num(int q_check_num){
		this.q_check_num = q_check_num;
	}
	
	/**
	 * get 参选所需排出等级（小于等于）
	 * @return 
	 */
	public int getQ_exclude_level(){
		return q_exclude_level;
	}
	
	/**
	 * set 参选所需排出等级（小于等于）
	 */
	public void setQ_exclude_level(int q_exclude_level){
		this.q_exclude_level = q_exclude_level;
	}
	
}
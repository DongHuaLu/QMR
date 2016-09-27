package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_skill_realm Bean
 */
public class Q_skill_realmBean {

	//境界编号
	private int q_jingjieid;
	
	//境界名称
	private String q_jingjie_name;
	
	//显示所需人物等级
	private int q_show_needgrade;
	
	//达成所需的武功层数之和
	private int q_arrive_levelsum;
	
	//武功境界加成攻击力比例（本处填万分比的分子）
	private int q_jiache_ratio;
	
	//在面板上显示的文字描述（需支持Html）
	private String q_tips;
	
	//在面板上显示的境界SWF资源编号
	private String q_swf;
	
	//在场景中人物名字前显示的境界SWF资源编号
	private String q_head_swf;
	
	//人物面板上显示的描述
	private String q_player_tips;
	
	
	/**
	 * get 境界编号
	 * @return 
	 */
	public int getQ_jingjieid(){
		return q_jingjieid;
	}
	
	/**
	 * set 境界编号
	 */
	public void setQ_jingjieid(int q_jingjieid){
		this.q_jingjieid = q_jingjieid;
	}
	
	/**
	 * get 境界名称
	 * @return 
	 */
	public String getQ_jingjie_name(){
		return q_jingjie_name;
	}
	
	/**
	 * set 境界名称
	 */
	public void setQ_jingjie_name(String q_jingjie_name){
		this.q_jingjie_name = q_jingjie_name;
	}
	
	/**
	 * get 显示所需人物等级
	 * @return 
	 */
	public int getQ_show_needgrade(){
		return q_show_needgrade;
	}
	
	/**
	 * set 显示所需人物等级
	 */
	public void setQ_show_needgrade(int q_show_needgrade){
		this.q_show_needgrade = q_show_needgrade;
	}
	
	/**
	 * get 达成所需的武功层数之和
	 * @return 
	 */
	public int getQ_arrive_levelsum(){
		return q_arrive_levelsum;
	}
	
	/**
	 * set 达成所需的武功层数之和
	 */
	public void setQ_arrive_levelsum(int q_arrive_levelsum){
		this.q_arrive_levelsum = q_arrive_levelsum;
	}
	
	/**
	 * get 武功境界加成攻击力比例（本处填万分比的分子）
	 * @return 
	 */
	public int getQ_jiache_ratio(){
		return q_jiache_ratio;
	}
	
	/**
	 * set 武功境界加成攻击力比例（本处填万分比的分子）
	 */
	public void setQ_jiache_ratio(int q_jiache_ratio){
		this.q_jiache_ratio = q_jiache_ratio;
	}
	
	/**
	 * get 在面板上显示的文字描述（需支持Html）
	 * @return 
	 */
	public String getQ_tips(){
		return q_tips;
	}
	
	/**
	 * set 在面板上显示的文字描述（需支持Html）
	 */
	public void setQ_tips(String q_tips){
		this.q_tips = q_tips;
	}
	
	/**
	 * get 在面板上显示的境界SWF资源编号
	 * @return 
	 */
	public String getQ_swf(){
		return q_swf;
	}
	
	/**
	 * set 在面板上显示的境界SWF资源编号
	 */
	public void setQ_swf(String q_swf){
		this.q_swf = q_swf;
	}
	
	/**
	 * get 在场景中人物名字前显示的境界SWF资源编号
	 * @return 
	 */
	public String getQ_head_swf(){
		return q_head_swf;
	}
	
	/**
	 * set 在场景中人物名字前显示的境界SWF资源编号
	 */
	public void setQ_head_swf(String q_head_swf){
		this.q_head_swf = q_head_swf;
	}
	
	/**
	 * get 人物面板上显示的描述
	 * @return 
	 */
	public String getQ_player_tips(){
		return q_player_tips;
	}
	
	/**
	 * set 人物面板上显示的描述
	 */
	public void setQ_player_tips(String q_player_tips){
		this.q_player_tips = q_player_tips;
	}
	
}
package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_activities_drop Bean
 */
public class Q_activities_dropBean {

	//索引不要重复（无用）
	private int q_id;
	
	//平台ID列表（37wan|aaa|xxx）不写表示所有平台
	private String q_platform;
	
	//活动ID（写0不检查）
	private int q_activityid;
	
	//开区天数(小于X天不掉落)
	private int q_openday;
	
	//掉落时间范围[[2013],[1],[15,18],[-1],[0,2359],[-1]]
	private String q_time_interval;
	
	//掉落道具列表（平均随机一个）[100,222,333,4444,555]
	private String q_drop_items;
	
	//掉落几率（万分比）
	private int q_probability;
	
	//道具过期时间  2013-1-9 23:59:59 (不写就无设置)
	private String q_expired;
	
	//怪物等级衰减区间，（写0不检查）
	private int q_monlevel_Interval;
	
	//其他
	private String q_other;
	
	
	/**
	 * get 索引不要重复（无用）
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 索引不要重复（无用）
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 平台ID列表（37wan|aaa|xxx）不写表示所有平台
	 * @return 
	 */
	public String getQ_platform(){
		return q_platform;
	}
	
	/**
	 * set 平台ID列表（37wan|aaa|xxx）不写表示所有平台
	 */
	public void setQ_platform(String q_platform){
		this.q_platform = q_platform;
	}
	
	/**
	 * get 活动ID（写0不检查）
	 * @return 
	 */
	public int getQ_activityid(){
		return q_activityid;
	}
	
	/**
	 * set 活动ID（写0不检查）
	 */
	public void setQ_activityid(int q_activityid){
		this.q_activityid = q_activityid;
	}
	
	/**
	 * get 开区天数(小于X天不掉落)
	 * @return 
	 */
	public int getQ_openday(){
		return q_openday;
	}
	
	/**
	 * set 开区天数(小于X天不掉落)
	 */
	public void setQ_openday(int q_openday){
		this.q_openday = q_openday;
	}
	
	/**
	 * get 掉落时间范围[[2013],[1],[15,18],[-1],[0,2359],[-1]]
	 * @return 
	 */
	public String getQ_time_interval(){
		return q_time_interval;
	}
	
	/**
	 * set 掉落时间范围[[2013],[1],[15,18],[-1],[0,2359],[-1]]
	 */
	public void setQ_time_interval(String q_time_interval){
		this.q_time_interval = q_time_interval;
	}
	
	/**
	 * get 掉落道具列表（平均随机一个）[100,222,333,4444,555]
	 * @return 
	 */
	public String getQ_drop_items(){
		return q_drop_items;
	}
	
	/**
	 * set 掉落道具列表（平均随机一个）[100,222,333,4444,555]
	 */
	public void setQ_drop_items(String q_drop_items){
		this.q_drop_items = q_drop_items;
	}
	
	/**
	 * get 掉落几率（万分比）
	 * @return 
	 */
	public int getQ_probability(){
		return q_probability;
	}
	
	/**
	 * set 掉落几率（万分比）
	 */
	public void setQ_probability(int q_probability){
		this.q_probability = q_probability;
	}
	
	/**
	 * get 道具过期时间  2013-1-9 23:59:59 (不写就无设置)
	 * @return 
	 */
	public String getQ_expired(){
		return q_expired;
	}
	
	/**
	 * set 道具过期时间  2013-1-9 23:59:59 (不写就无设置)
	 */
	public void setQ_expired(String q_expired){
		this.q_expired = q_expired;
	}
	
	/**
	 * get 怪物等级衰减区间，（写0不检查）
	 * @return 
	 */
	public int getQ_monlevel_Interval(){
		return q_monlevel_Interval;
	}
	
	/**
	 * set 怪物等级衰减区间，（写0不检查）
	 */
	public void setQ_monlevel_Interval(int q_monlevel_Interval){
		this.q_monlevel_Interval = q_monlevel_Interval;
	}
	
	/**
	 * get 其他
	 * @return 
	 */
	public String getQ_other(){
		return q_other;
	}
	
	/**
	 * set 其他
	 */
	public void setQ_other(String q_other){
		this.q_other = q_other;
	}
	
}
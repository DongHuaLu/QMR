package com.game.db.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * System_grant Bean
 */
public class System_grantBean {

	//id
	private int q_id;
	
	//key保存标记（每条数据都要不同）
	private String q_key;
	
	//类型：1上线补偿，2在线邮件
	private int q_type;
	
	//平台ID列表(平台，开始区，结束区|平台，开始区，结束区)
	private String q_platform;
	
	//排除区(平台，排除区ID1，排除区ID2|平台，排除区ID1)
	private String q_exclude_platform;
	
	//道具列表1011,10,0,1;1022,50,0,1（物品模板ID，物品数量，性别，是否绑定（0或1);物品模板ID，物品数量，性别，是否绑定（0或1)）
	private String q_items;
	
	//时间范围(起始时间定好后，不要修改)2013-1-9 00:01:00|2013-1-22 00:01:00
	private String q_time_interval;
	
	//道具过期时间  2013-1-9 23:59:59 (不写就无设置)
	private String q_expired;
	
	//角色创建时间 2013-1-9 23:59:59
	private String q_rolecreated;
	
	//发放道具的说明文字(邮件内容或者上线补偿提示)
	private String q_caption;
	
	//等级区间(1|999)
	private String q_level;
	
	//其他
	private String q_other;
	
	
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
	 * get 类型：1上线补偿，2在线邮件
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 类型：1上线补偿，2在线邮件
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 平台ID列表(平台，开始区，结束区|平台，开始区，结束区)
	 * @return 
	 */
	public String getQ_platform(){
		return q_platform;
	}
	
	/**
	 * set 平台ID列表(平台，开始区，结束区|平台，开始区，结束区)
	 */
	public void setQ_platform(String q_platform){
		this.q_platform = q_platform;
	}
	
	/**
	 * get 排除区(平台，排除区ID1，排除区ID2|平台，排除区ID1)
	 * @return 
	 */
	public String getQ_exclude_platform(){
		return q_exclude_platform;
	}
	
	/**
	 * set 排除区(平台，排除区ID1，排除区ID2|平台，排除区ID1)
	 */
	public void setQ_exclude_platform(String q_exclude_platform){
		this.q_exclude_platform = q_exclude_platform;
	}
	
	/**
	 * get 道具列表1011,10,0,1;1022,50,0,1（物品模板ID，物品数量，性别，是否绑定（0或1);物品模板ID，物品数量，性别，是否绑定（0或1)）
	 * @return 
	 */
	public String getQ_items(){
		return q_items;
	}
	
	/**
	 * set 道具列表1011,10,0,1;1022,50,0,1（物品模板ID，物品数量，性别，是否绑定（0或1);物品模板ID，物品数量，性别，是否绑定（0或1)）
	 */
	public void setQ_items(String q_items){
		this.q_items = q_items;
	}
	
	/**
	 * get 时间范围(起始时间定好后，不要修改)2013-1-9 00:01:00|2013-1-22 00:01:00
	 * @return 
	 */
	public String getQ_time_interval(){
		return q_time_interval;
	}
	
	/**
	 * set 时间范围(起始时间定好后，不要修改)2013-1-9 00:01:00|2013-1-22 00:01:00
	 */
	public void setQ_time_interval(String q_time_interval){
		this.q_time_interval = q_time_interval;
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
	 * get 角色创建时间 2013-1-9 23:59:59
	 * @return 
	 */
	public String getQ_rolecreated(){
		return q_rolecreated;
	}
	
	/**
	 * set 角色创建时间 2013-1-9 23:59:59
	 */
	public void setQ_rolecreated(String q_rolecreated){
		this.q_rolecreated = q_rolecreated;
	}
	
	/**
	 * get 发放道具的说明文字(邮件内容或者上线补偿提示)
	 * @return 
	 */
	public String getQ_caption(){
		return q_caption;
	}
	
	/**
	 * set 发放道具的说明文字(邮件内容或者上线补偿提示)
	 */
	public void setQ_caption(String q_caption){
		this.q_caption = q_caption;
	}
	
	/**
	 * get 等级区间(1|999)
	 * @return 
	 */
	public String getQ_level(){
		return q_level;
	}
	
	/**
	 * set 等级区间(1|999)
	 */
	public void setQ_level(String q_level){
		this.q_level = q_level;
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
	/**
	 * key保存标记（每条数据都要不同）
	 * @return
	 */
	public String getQ_key() {
		return q_key;
	}
	/**
	 * key保存标记（每条数据都要不同）
	 * @param q_key
	 */
	public void setQ_key(String q_key) {
		this.q_key = q_key;
	}
	
}
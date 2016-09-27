package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_clone_activity Bean
 */
public class Q_clone_activityBean {

	//副本编号
	private int q_id;
	
	//副本类型(小标签)，1小地图副本，2活动日常,3多人活动
	private int q_zone_type;
	
	//副本关联地图编号[xxxx,xxxx]
	private String q_mapid;
	
	//副本名称（支持HTML）
	private String q_duplicate_name;
	
	//副本存在时间（毫秒）
	private int q_exist_time;
	
	//开放时间
	private String q_open_time;
	
	//副本重置时间(type=1日，2=周，3=月)time=秒数
	private String q_reset_time;
	
	//进入所需最小等级
	private int q_min_lv;
	
	//最高等级进入限制
	private int q_max_lv;
	
	//进入最少人数限制
	private int q_min_num;
	
	//进入最多人数限制
	private int q_max_num;
	
	//进入状态（1为单人，2为组队，3为不限）
	private int q_type;
	
	//每日手动挑战次数(-1表示未开放)
	private int q_manual_num;
	
	//每日扫荡次数
	private int q_raids_num;
	
	//扫荡收取元宝
	private int q_raids_yuanbao;
	
	//扫荡每分钟所需元宝
	private int q_raids_min_yuanbao;
	
	//通关奖励(-1到-5)和ITEM表里的对应(-1铜币，-2元宝，-3真气，-4经验  -5绑定元宝)
	private String q_reward;
	
	//副本时间评价（填写时间，精确到毫秒，如果玩家通关时间小于填写时间，则获得一颗星）
	private int q_time_evaluate;
	
	//副本说明（支持HTML）
	private String q_explain;
	
	//进入地图的默认坐标x
	private int q_x;
	
	//进入地图的默认坐标y
	private int q_y;
	
	//是否调用脚本,1调用，0不用
	private int q_isscript;
	
	//活动参与奖励
	private String q_Participation_Award;
	
	//通关随机奖励描述
	private String q_Random_Description;
	
	//地图存在的BOSS（BOSSid,x,y）
	private String q_map_boss;
	
	//是否分为多组，0不分组，>1 表示分为几组
	private int q_map_group;
	
	
	/**
	 * get 副本编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 副本编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 副本类型(小标签)，1小地图副本，2活动日常,3多人活动
	 * @return 
	 */
	public int getQ_zone_type(){
		return q_zone_type;
	}
	
	/**
	 * set 副本类型(小标签)，1小地图副本，2活动日常,3多人活动
	 */
	public void setQ_zone_type(int q_zone_type){
		this.q_zone_type = q_zone_type;
	}
	
	/**
	 * get 副本关联地图编号[xxxx,xxxx]
	 * @return 
	 */
	public String getQ_mapid(){
		return q_mapid;
	}
	
	/**
	 * set 副本关联地图编号[xxxx,xxxx]
	 */
	public void setQ_mapid(String q_mapid){
		this.q_mapid = q_mapid;
	}
	
	/**
	 * get 副本名称（支持HTML）
	 * @return 
	 */
	public String getQ_duplicate_name(){
		return q_duplicate_name;
	}
	
	/**
	 * set 副本名称（支持HTML）
	 */
	public void setQ_duplicate_name(String q_duplicate_name){
		this.q_duplicate_name = q_duplicate_name;
	}
	
	/**
	 * get 副本存在时间（毫秒）
	 * @return 
	 */
	public int getQ_exist_time(){
		return q_exist_time;
	}
	
	/**
	 * set 副本存在时间（毫秒）
	 */
	public void setQ_exist_time(int q_exist_time){
		this.q_exist_time = q_exist_time;
	}
	
	/**
	 * get 开放时间
	 * @return 
	 */
	public String getQ_open_time(){
		return q_open_time;
	}
	
	/**
	 * set 开放时间
	 */
	public void setQ_open_time(String q_open_time){
		this.q_open_time = q_open_time;
	}
	
	/**
	 * get 副本重置时间(type=1日，2=周，3=月)time=秒数
	 * @return 
	 */
	public String getQ_reset_time(){
		return q_reset_time;
	}
	
	/**
	 * set 副本重置时间(type=1日，2=周，3=月)time=秒数
	 */
	public void setQ_reset_time(String q_reset_time){
		this.q_reset_time = q_reset_time;
	}
	
	/**
	 * get 进入所需最小等级
	 * @return 
	 */
	public int getQ_min_lv(){
		return q_min_lv;
	}
	
	/**
	 * set 进入所需最小等级
	 */
	public void setQ_min_lv(int q_min_lv){
		this.q_min_lv = q_min_lv;
	}
	
	/**
	 * get 最高等级进入限制
	 * @return 
	 */
	public int getQ_max_lv(){
		return q_max_lv;
	}
	
	/**
	 * set 最高等级进入限制
	 */
	public void setQ_max_lv(int q_max_lv){
		this.q_max_lv = q_max_lv;
	}
	
	/**
	 * get 进入最少人数限制
	 * @return 
	 */
	public int getQ_min_num(){
		return q_min_num;
	}
	
	/**
	 * set 进入最少人数限制
	 */
	public void setQ_min_num(int q_min_num){
		this.q_min_num = q_min_num;
	}
	
	/**
	 * get 进入最多人数限制
	 * @return 
	 */
	public int getQ_max_num(){
		return q_max_num;
	}
	
	/**
	 * set 进入最多人数限制
	 */
	public void setQ_max_num(int q_max_num){
		this.q_max_num = q_max_num;
	}
	
	/**
	 * get 进入状态（1为单人，2为组队，3为不限）
	 * @return 
	 */
	public int getQ_type(){
		return q_type;
	}
	
	/**
	 * set 进入状态（1为单人，2为组队，3为不限）
	 */
	public void setQ_type(int q_type){
		this.q_type = q_type;
	}
	
	/**
	 * get 每日手动挑战次数(-1表示未开放)
	 * @return 
	 */
	public int getQ_manual_num(){
		return q_manual_num;
	}
	
	/**
	 * set 每日手动挑战次数(-1表示未开放)
	 */
	public void setQ_manual_num(int q_manual_num){
		this.q_manual_num = q_manual_num;
	}
	
	/**
	 * get 每日扫荡次数
	 * @return 
	 */
	public int getQ_raids_num(){
		return q_raids_num;
	}
	
	/**
	 * set 每日扫荡次数
	 */
	public void setQ_raids_num(int q_raids_num){
		this.q_raids_num = q_raids_num;
	}
	
	/**
	 * get 扫荡收取元宝
	 * @return 
	 */
	public int getQ_raids_yuanbao(){
		return q_raids_yuanbao;
	}
	
	/**
	 * set 扫荡收取元宝
	 */
	public void setQ_raids_yuanbao(int q_raids_yuanbao){
		this.q_raids_yuanbao = q_raids_yuanbao;
	}
	
	/**
	 * get 扫荡每分钟所需元宝
	 * @return 
	 */
	public int getQ_raids_min_yuanbao(){
		return q_raids_min_yuanbao;
	}
	
	/**
	 * set 扫荡每分钟所需元宝
	 */
	public void setQ_raids_min_yuanbao(int q_raids_min_yuanbao){
		this.q_raids_min_yuanbao = q_raids_min_yuanbao;
	}
	
	/**
	 * get 通关奖励(-1到-5)和ITEM表里的对应(-1铜币，-2元宝，-3真气，-4经验  -5绑定元宝)
	 * @return 
	 */
	public String getQ_reward(){
		return q_reward;
	}
	
	/**
	 * set 通关奖励(-1到-5)和ITEM表里的对应(-1铜币，-2元宝，-3真气，-4经验  -5绑定元宝)
	 */
	public void setQ_reward(String q_reward){
		this.q_reward = q_reward;
	}
	
	/**
	 * get 副本时间评价（填写时间，精确到毫秒，如果玩家通关时间小于填写时间，则获得一颗星）
	 * @return 
	 */
	public int getQ_time_evaluate(){
		return q_time_evaluate;
	}
	
	/**
	 * set 副本时间评价（填写时间，精确到毫秒，如果玩家通关时间小于填写时间，则获得一颗星）
	 */
	public void setQ_time_evaluate(int q_time_evaluate){
		this.q_time_evaluate = q_time_evaluate;
	}
	
	/**
	 * get 副本说明（支持HTML）
	 * @return 
	 */
	public String getQ_explain(){
		return q_explain;
	}
	
	/**
	 * set 副本说明（支持HTML）
	 */
	public void setQ_explain(String q_explain){
		this.q_explain = q_explain;
	}
	
	/**
	 * get 进入地图的默认坐标x
	 * @return 
	 */
	public int getQ_x(){
		return q_x;
	}
	
	/**
	 * set 进入地图的默认坐标x
	 */
	public void setQ_x(int q_x){
		this.q_x = q_x;
	}
	
	/**
	 * get 进入地图的默认坐标y
	 * @return 
	 */
	public int getQ_y(){
		return q_y;
	}
	
	/**
	 * set 进入地图的默认坐标y
	 */
	public void setQ_y(int q_y){
		this.q_y = q_y;
	}
	
	/**
	 * get 是否调用脚本,1调用，0不用
	 * @return 
	 */
	public int getQ_isscript(){
		return q_isscript;
	}
	
	/**
	 * set 是否调用脚本,1调用，0不用
	 */
	public void setQ_isscript(int q_isscript){
		this.q_isscript = q_isscript;
	}
	
	/**
	 * get 活动参与奖励
	 * @return 
	 */
	public String getQ_Participation_Award(){
		return q_Participation_Award;
	}
	
	/**
	 * set 活动参与奖励
	 */
	public void setQ_Participation_Award(String q_Participation_Award){
		this.q_Participation_Award = q_Participation_Award;
	}
	
	/**
	 * get 通关随机奖励描述
	 * @return 
	 */
	public String getQ_Random_Description(){
		return q_Random_Description;
	}
	
	/**
	 * set 通关随机奖励描述
	 */
	public void setQ_Random_Description(String q_Random_Description){
		this.q_Random_Description = q_Random_Description;
	}
	
	/**
	 * get 地图存在的BOSS（BOSSid,x,y）
	 * @return 
	 */
	public String getQ_map_boss(){
		return q_map_boss;
	}
	
	/**
	 * set 地图存在的BOSS（BOSSid,x,y）
	 */
	public void setQ_map_boss(String q_map_boss){
		this.q_map_boss = q_map_boss;
	}
	
	/**
	 * get 是否分为多组，0不分组，>1 表示分为几组
	 * @return 
	 */
	public int getQ_map_group(){
		return q_map_group;
	}
	
	/**
	 * set 是否分为多组，0不分组，>1 表示分为几组
	 */
	public void setQ_map_group(int q_map_group){
		this.q_map_group = q_map_group;
	}
	
}
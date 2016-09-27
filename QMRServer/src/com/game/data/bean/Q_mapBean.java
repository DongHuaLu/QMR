package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_map Bean
 */
public class Q_mapBean {

	//地图中文名
	private String q_map_name;
	
	//地图ID
	private int q_map_id;
	
	//地图资源ID
	private int q_mapresid;
	
	//地图宽度
	private int q_map_width;
	
	//地图高度
	private int q_map_height;
	
	//进入等级下限
	private int q_map_min_level;
	
	//进入等级上限
	private int q_map_max_level;
	
	//是否安全区(0战斗区域,1安全区域)
	private int q_map_safe;
	
	//是否可跳跃(0不可跳跃,1可跳跃)
	private int q_map_jump;
	
	//是否可骑乘(0不可骑乘,1可骑乘)
	private int q_map_ride;
	
	//是否可展示坐骑(0不允许,1允许)
	private int q_map_show;
	
	//被PK死亡后是否给予和平保护BUFF（0给予，1不给予）
	private int q_map_buff;
	
	//等级差距过大是否可PK（0可PK，1不可PK）
	private int q_map_pk;
	
	//是否可以PK新人（0可PK，1不可PK）
	private int q_map_pkprotection;
	
	//夜晚挂机是否开启挂机保护（0不开启，1开启）
	private int q_map_hangprotection;
	
	//死亡回到地图ID
	private int q_map_die;
	
	//回到X坐标
	private int q_map_die_x;
	
	//回到Y坐标
	private int q_map_die_y;
	
	//下线回到地图ID
	private int q_map_quit;
	
	//下线回到X坐标
	private int q_map_quit_x;
	
	//下线回到Y坐标
	private int q_map_quit_y;
	
	//地图开放线路（格式 线id|线id）
	private String q_map_lines;
	
	//是否为公共区地图（0普通 1公共区）
	private int q_map_public;
	
	//是否为副本（0普通 1副本）
	private int q_map_zones;
	
	//承载最大人数
	private int q_max_online;
	
	//本地图默认帮旗，0不可插，其他填指定怪物ID
	private int q_default_flag;
	
	//帮旗坐标[x,y]
	private String q_flag_pos;
	
	//默认开启线路
	private int q_default_line;
	
	//轮询人数
	private int q_poll_num;
	
	//音乐关联（音乐资源编号_播放完后空闲秒数;音乐资源编号_播放完后空闲秒数)
	private String q_music;
	
	//地图是否是附属地图（1是，0否）
	private int q_map_subsidiary;
	
	//附属地图的主地图id
	private int q_subsidiary_main;
	
	//死亡随机回城坐标（x_y;x_y）
	private String q_rnd_die_xy;
	
	//退出随机回城坐标（x_y;x_y）
	private String q_rnd_quit_xy;
	
	//关联BOSSid（寻径时，找有BOSS的地图）
	private int q_boss_id;
	
	//是否可用玫瑰复活（0是，1否）
	private int q_rose_resurrection;
	
	
	/**
	 * get 地图中文名
	 * @return 
	 */
	public String getQ_map_name(){
		return q_map_name;
	}
	
	/**
	 * set 地图中文名
	 */
	public void setQ_map_name(String q_map_name){
		this.q_map_name = q_map_name;
	}
	
	/**
	 * get 地图ID
	 * @return 
	 */
	public int getQ_map_id(){
		return q_map_id;
	}
	
	/**
	 * set 地图ID
	 */
	public void setQ_map_id(int q_map_id){
		this.q_map_id = q_map_id;
	}
	
	/**
	 * get 地图资源ID
	 * @return 
	 */
	public int getQ_mapresid(){
		return q_mapresid;
	}
	
	/**
	 * set 地图资源ID
	 */
	public void setQ_mapresid(int q_mapresid){
		this.q_mapresid = q_mapresid;
	}
	
	/**
	 * get 地图宽度
	 * @return 
	 */
	public int getQ_map_width(){
		return q_map_width;
	}
	
	/**
	 * set 地图宽度
	 */
	public void setQ_map_width(int q_map_width){
		this.q_map_width = q_map_width;
	}
	
	/**
	 * get 地图高度
	 * @return 
	 */
	public int getQ_map_height(){
		return q_map_height;
	}
	
	/**
	 * set 地图高度
	 */
	public void setQ_map_height(int q_map_height){
		this.q_map_height = q_map_height;
	}
	
	/**
	 * get 进入等级下限
	 * @return 
	 */
	public int getQ_map_min_level(){
		return q_map_min_level;
	}
	
	/**
	 * set 进入等级下限
	 */
	public void setQ_map_min_level(int q_map_min_level){
		this.q_map_min_level = q_map_min_level;
	}
	
	/**
	 * get 进入等级上限
	 * @return 
	 */
	public int getQ_map_max_level(){
		return q_map_max_level;
	}
	
	/**
	 * set 进入等级上限
	 */
	public void setQ_map_max_level(int q_map_max_level){
		this.q_map_max_level = q_map_max_level;
	}
	
	/**
	 * get 是否安全区(0战斗区域,1安全区域)
	 * @return 
	 */
	public int getQ_map_safe(){
		return q_map_safe;
	}
	
	/**
	 * set 是否安全区(0战斗区域,1安全区域)
	 */
	public void setQ_map_safe(int q_map_safe){
		this.q_map_safe = q_map_safe;
	}
	
	/**
	 * get 是否可跳跃(0不可跳跃,1可跳跃)
	 * @return 
	 */
	public int getQ_map_jump(){
		return q_map_jump;
	}
	
	/**
	 * set 是否可跳跃(0不可跳跃,1可跳跃)
	 */
	public void setQ_map_jump(int q_map_jump){
		this.q_map_jump = q_map_jump;
	}
	
	/**
	 * get 是否可骑乘(0不可骑乘,1可骑乘)
	 * @return 
	 */
	public int getQ_map_ride(){
		return q_map_ride;
	}
	
	/**
	 * set 是否可骑乘(0不可骑乘,1可骑乘)
	 */
	public void setQ_map_ride(int q_map_ride){
		this.q_map_ride = q_map_ride;
	}
	
	/**
	 * get 是否可展示坐骑(0不允许,1允许)
	 * @return 
	 */
	public int getQ_map_show(){
		return q_map_show;
	}
	
	/**
	 * set 是否可展示坐骑(0不允许,1允许)
	 */
	public void setQ_map_show(int q_map_show){
		this.q_map_show = q_map_show;
	}
	
	/**
	 * get 被PK死亡后是否给予和平保护BUFF（0给予，1不给予）
	 * @return 
	 */
	public int getQ_map_buff(){
		return q_map_buff;
	}
	
	/**
	 * set 被PK死亡后是否给予和平保护BUFF（0给予，1不给予）
	 */
	public void setQ_map_buff(int q_map_buff){
		this.q_map_buff = q_map_buff;
	}
	
	/**
	 * get 等级差距过大是否可PK（0可PK，1不可PK）
	 * @return 
	 */
	public int getQ_map_pk(){
		return q_map_pk;
	}
	
	/**
	 * set 等级差距过大是否可PK（0可PK，1不可PK）
	 */
	public void setQ_map_pk(int q_map_pk){
		this.q_map_pk = q_map_pk;
	}
	
	/**
	 * get 是否可以PK新人（0可PK，1不可PK）
	 * @return 
	 */
	public int getQ_map_pkprotection(){
		return q_map_pkprotection;
	}
	
	/**
	 * set 是否可以PK新人（0可PK，1不可PK）
	 */
	public void setQ_map_pkprotection(int q_map_pkprotection){
		this.q_map_pkprotection = q_map_pkprotection;
	}
	
	/**
	 * get 夜晚挂机是否开启挂机保护（0不开启，1开启）
	 * @return 
	 */
	public int getQ_map_hangprotection(){
		return q_map_hangprotection;
	}
	
	/**
	 * set 夜晚挂机是否开启挂机保护（0不开启，1开启）
	 */
	public void setQ_map_hangprotection(int q_map_hangprotection){
		this.q_map_hangprotection = q_map_hangprotection;
	}
	
	/**
	 * get 死亡回到地图ID
	 * @return 
	 */
	public int getQ_map_die(){
		return q_map_die;
	}
	
	/**
	 * set 死亡回到地图ID
	 */
	public void setQ_map_die(int q_map_die){
		this.q_map_die = q_map_die;
	}
	
	/**
	 * get 回到X坐标
	 * @return 
	 */
	public int getQ_map_die_x(){
		return q_map_die_x;
	}
	
	/**
	 * set 回到X坐标
	 */
	public void setQ_map_die_x(int q_map_die_x){
		this.q_map_die_x = q_map_die_x;
	}
	
	/**
	 * get 回到Y坐标
	 * @return 
	 */
	public int getQ_map_die_y(){
		return q_map_die_y;
	}
	
	/**
	 * set 回到Y坐标
	 */
	public void setQ_map_die_y(int q_map_die_y){
		this.q_map_die_y = q_map_die_y;
	}
	
	/**
	 * get 下线回到地图ID
	 * @return 
	 */
	public int getQ_map_quit(){
		return q_map_quit;
	}
	
	/**
	 * set 下线回到地图ID
	 */
	public void setQ_map_quit(int q_map_quit){
		this.q_map_quit = q_map_quit;
	}
	
	/**
	 * get 下线回到X坐标
	 * @return 
	 */
	public int getQ_map_quit_x(){
		return q_map_quit_x;
	}
	
	/**
	 * set 下线回到X坐标
	 */
	public void setQ_map_quit_x(int q_map_quit_x){
		this.q_map_quit_x = q_map_quit_x;
	}
	
	/**
	 * get 下线回到Y坐标
	 * @return 
	 */
	public int getQ_map_quit_y(){
		return q_map_quit_y;
	}
	
	/**
	 * set 下线回到Y坐标
	 */
	public void setQ_map_quit_y(int q_map_quit_y){
		this.q_map_quit_y = q_map_quit_y;
	}
	
	/**
	 * get 地图开放线路（格式 线id|线id）
	 * @return 
	 */
	public String getQ_map_lines(){
		return q_map_lines;
	}
	
	/**
	 * set 地图开放线路（格式 线id|线id）
	 */
	public void setQ_map_lines(String q_map_lines){
		this.q_map_lines = q_map_lines;
	}
	
	/**
	 * get 是否为公共区地图（0普通 1公共区）
	 * @return 
	 */
	public int getQ_map_public(){
		return q_map_public;
	}
	
	/**
	 * set 是否为公共区地图（0普通 1公共区）
	 */
	public void setQ_map_public(int q_map_public){
		this.q_map_public = q_map_public;
	}
	
	/**
	 * get 是否为副本（0普通 1副本）
	 * @return 
	 */
	public int getQ_map_zones(){
		return q_map_zones;
	}
	
	/**
	 * set 是否为副本（0普通 1副本）
	 */
	public void setQ_map_zones(int q_map_zones){
		this.q_map_zones = q_map_zones;
	}
	
	/**
	 * get 承载最大人数
	 * @return 
	 */
	public int getQ_max_online(){
		return q_max_online;
	}
	
	/**
	 * set 承载最大人数
	 */
	public void setQ_max_online(int q_max_online){
		this.q_max_online = q_max_online;
	}
	
	/**
	 * get 本地图默认帮旗，0不可插，其他填指定怪物ID
	 * @return 
	 */
	public int getQ_default_flag(){
		return q_default_flag;
	}
	
	/**
	 * set 本地图默认帮旗，0不可插，其他填指定怪物ID
	 */
	public void setQ_default_flag(int q_default_flag){
		this.q_default_flag = q_default_flag;
	}
	
	/**
	 * get 帮旗坐标[x,y]
	 * @return 
	 */
	public String getQ_flag_pos(){
		return q_flag_pos;
	}
	
	/**
	 * set 帮旗坐标[x,y]
	 */
	public void setQ_flag_pos(String q_flag_pos){
		this.q_flag_pos = q_flag_pos;
	}
	
	/**
	 * get 默认开启线路
	 * @return 
	 */
	public int getQ_default_line(){
		return q_default_line;
	}
	
	/**
	 * set 默认开启线路
	 */
	public void setQ_default_line(int q_default_line){
		this.q_default_line = q_default_line;
	}
	
	/**
	 * get 轮询人数
	 * @return 
	 */
	public int getQ_poll_num(){
		return q_poll_num;
	}
	
	/**
	 * set 轮询人数
	 */
	public void setQ_poll_num(int q_poll_num){
		this.q_poll_num = q_poll_num;
	}
	
	/**
	 * get 音乐关联（音乐资源编号_播放完后空闲秒数;音乐资源编号_播放完后空闲秒数)
	 * @return 
	 */
	public String getQ_music(){
		return q_music;
	}
	
	/**
	 * set 音乐关联（音乐资源编号_播放完后空闲秒数;音乐资源编号_播放完后空闲秒数)
	 */
	public void setQ_music(String q_music){
		this.q_music = q_music;
	}
	
	/**
	 * get 地图是否是附属地图（1是，0否）
	 * @return 
	 */
	public int getQ_map_subsidiary(){
		return q_map_subsidiary;
	}
	
	/**
	 * set 地图是否是附属地图（1是，0否）
	 */
	public void setQ_map_subsidiary(int q_map_subsidiary){
		this.q_map_subsidiary = q_map_subsidiary;
	}
	
	/**
	 * get 附属地图的主地图id
	 * @return 
	 */
	public int getQ_subsidiary_main(){
		return q_subsidiary_main;
	}
	
	/**
	 * set 附属地图的主地图id
	 */
	public void setQ_subsidiary_main(int q_subsidiary_main){
		this.q_subsidiary_main = q_subsidiary_main;
	}
	
	/**
	 * get 死亡随机回城坐标（x_y;x_y）
	 * @return 
	 */
	public String getQ_rnd_die_xy(){
		return q_rnd_die_xy;
	}
	
	/**
	 * set 死亡随机回城坐标（x_y;x_y）
	 */
	public void setQ_rnd_die_xy(String q_rnd_die_xy){
		this.q_rnd_die_xy = q_rnd_die_xy;
	}
	
	/**
	 * get 退出随机回城坐标（x_y;x_y）
	 * @return 
	 */
	public String getQ_rnd_quit_xy(){
		return q_rnd_quit_xy;
	}
	
	/**
	 * set 退出随机回城坐标（x_y;x_y）
	 */
	public void setQ_rnd_quit_xy(String q_rnd_quit_xy){
		this.q_rnd_quit_xy = q_rnd_quit_xy;
	}
	
	/**
	 * get 关联BOSSid（寻径时，找有BOSS的地图）
	 * @return 
	 */
	public int getQ_boss_id(){
		return q_boss_id;
	}
	
	/**
	 * set 关联BOSSid（寻径时，找有BOSS的地图）
	 */
	public void setQ_boss_id(int q_boss_id){
		this.q_boss_id = q_boss_id;
	}
	
	/**
	 * get 是否可用玫瑰复活（0是，1否）
	 * @return 
	 */
	public int getQ_rose_resurrection(){
		return q_rose_resurrection;
	}
	
	/**
	 * set 是否可用玫瑰复活（0是，1否）
	 */
	public void setQ_rose_resurrection(int q_rose_resurrection){
		this.q_rose_resurrection = q_rose_resurrection;
	}
	
}
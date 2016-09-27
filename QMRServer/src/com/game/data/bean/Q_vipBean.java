package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_vip Bean
 */
public class Q_vipBean {

	//VIP编号
	private int q_id;
	
	//VIP名称
	private String q_name;
	
	//文字描述
	private String q_desc;
	
	//VIP卡物品ID
	private int q_itemid;
	
	//VIP对应buffid
	private int q_buffid;
	
	//每日领取的礼包id
	private int q_dayreceivegiftid;
	
	//每日领取铜币
	private int q_dayreceive_money;
	
	//每日领取礼金
	private int q_dayreceive_lijin;
	
	//免费传送次数(-1表示无次数限制)
	private int q_fly;
	
	//称号资源图
	private int q_ico;
	
	//加速仙露
	private int q_xianlu;
	
	//正面BUFF增加时间
	private int q_pos_buff;
	
	//自动摘取果实(0-不自动 1-自动)
	private int q_auto_fruit;
	
	//自动给果实浇水(0-不自动 1-自动)
	private int q_auto_water;
	
	//副本扫荡次数
	private int q_zone_time;
	
	//附加功能
	private String q_append;
	
	//领取军功
	private int q_rank;
	
	
	/**
	 * get VIP编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set VIP编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get VIP名称
	 * @return 
	 */
	public String getQ_name(){
		return q_name;
	}
	
	/**
	 * set VIP名称
	 */
	public void setQ_name(String q_name){
		this.q_name = q_name;
	}
	
	/**
	 * get 文字描述
	 * @return 
	 */
	public String getQ_desc(){
		return q_desc;
	}
	
	/**
	 * set 文字描述
	 */
	public void setQ_desc(String q_desc){
		this.q_desc = q_desc;
	}
	
	/**
	 * get VIP卡物品ID
	 * @return 
	 */
	public int getQ_itemid(){
		return q_itemid;
	}
	
	/**
	 * set VIP卡物品ID
	 */
	public void setQ_itemid(int q_itemid){
		this.q_itemid = q_itemid;
	}
	
	/**
	 * get VIP对应buffid
	 * @return 
	 */
	public int getQ_buffid(){
		return q_buffid;
	}
	
	/**
	 * set VIP对应buffid
	 */
	public void setQ_buffid(int q_buffid){
		this.q_buffid = q_buffid;
	}
	
	/**
	 * get 每日领取的礼包id
	 * @return 
	 */
	public int getQ_dayreceivegiftid(){
		return q_dayreceivegiftid;
	}
	
	/**
	 * set 每日领取的礼包id
	 */
	public void setQ_dayreceivegiftid(int q_dayreceivegiftid){
		this.q_dayreceivegiftid = q_dayreceivegiftid;
	}
	
	/**
	 * get 每日领取铜币
	 * @return 
	 */
	public int getQ_dayreceive_money(){
		return q_dayreceive_money;
	}
	
	/**
	 * set 每日领取铜币
	 */
	public void setQ_dayreceive_money(int q_dayreceive_money){
		this.q_dayreceive_money = q_dayreceive_money;
	}
	
	/**
	 * get 每日领取礼金
	 * @return 
	 */
	public int getQ_dayreceive_lijin(){
		return q_dayreceive_lijin;
	}
	
	/**
	 * set 每日领取礼金
	 */
	public void setQ_dayreceive_lijin(int q_dayreceive_lijin){
		this.q_dayreceive_lijin = q_dayreceive_lijin;
	}
	
	/**
	 * get 免费传送次数(-1表示无次数限制)
	 * @return 
	 */
	public int getQ_fly(){
		return q_fly;
	}
	
	/**
	 * set 免费传送次数(-1表示无次数限制)
	 */
	public void setQ_fly(int q_fly){
		this.q_fly = q_fly;
	}
	
	/**
	 * get 称号资源图
	 * @return 
	 */
	public int getQ_ico(){
		return q_ico;
	}
	
	/**
	 * set 称号资源图
	 */
	public void setQ_ico(int q_ico){
		this.q_ico = q_ico;
	}
	
	/**
	 * get 加速仙露
	 * @return 
	 */
	public int getQ_xianlu(){
		return q_xianlu;
	}
	
	/**
	 * set 加速仙露
	 */
	public void setQ_xianlu(int q_xianlu){
		this.q_xianlu = q_xianlu;
	}
	
	/**
	 * get 正面BUFF增加时间
	 * @return 
	 */
	public int getQ_pos_buff(){
		return q_pos_buff;
	}
	
	/**
	 * set 正面BUFF增加时间
	 */
	public void setQ_pos_buff(int q_pos_buff){
		this.q_pos_buff = q_pos_buff;
	}
	
	/**
	 * get 自动摘取果实(0-不自动 1-自动)
	 * @return 
	 */
	public int getQ_auto_fruit(){
		return q_auto_fruit;
	}
	
	/**
	 * set 自动摘取果实(0-不自动 1-自动)
	 */
	public void setQ_auto_fruit(int q_auto_fruit){
		this.q_auto_fruit = q_auto_fruit;
	}
	
	/**
	 * get 自动给果实浇水(0-不自动 1-自动)
	 * @return 
	 */
	public int getQ_auto_water(){
		return q_auto_water;
	}
	
	/**
	 * set 自动给果实浇水(0-不自动 1-自动)
	 */
	public void setQ_auto_water(int q_auto_water){
		this.q_auto_water = q_auto_water;
	}
	
	/**
	 * get 副本扫荡次数
	 * @return 
	 */
	public int getQ_zone_time(){
		return q_zone_time;
	}
	
	/**
	 * set 副本扫荡次数
	 */
	public void setQ_zone_time(int q_zone_time){
		this.q_zone_time = q_zone_time;
	}
	
	/**
	 * get 附加功能
	 * @return 
	 */
	public String getQ_append(){
		return q_append;
	}
	
	/**
	 * set 附加功能
	 */
	public void setQ_append(String q_append){
		this.q_append = q_append;
	}
	
	/**
	 * get 领取军功
	 * @return 
	 */
	public int getQ_rank(){
		return q_rank;
	}
	
	/**
	 * set 领取军功
	 */
	public void setQ_rank(int q_rank){
		this.q_rank = q_rank;
	}
	
}
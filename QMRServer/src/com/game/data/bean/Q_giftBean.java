package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_gift Bean
 */
public class Q_giftBean {

	//礼包ID
	private int q_gift_id;
	
	//礼包类型(1.常规礼包，升级礼包；2.推广玩家礼品；3.连续登陆；4.周在线时间；5.延时礼品；6.签到奖励；7.累计签到次数领取奖励)
	private int q_gift_type;
	
	//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
	private String q_gift_data;
	
	//互斥随机道具
	private String q_huchi_random_gift_data;
	
	//随机道具
	private String q_random_gift_data;
	
	//奖励铜币
	private int q_gift_money;
	
	//奖励元宝
	private int q_gift_gold;
	
	//延时时间
	private int q_time_limit;
	
	//推广人数
	private int q_invite_limit;
	
	//登入天数领取
	private int q_login_limit;
	
	//周在线小时领取
	private int q_online_time_limit;
	
	//签到次数统计
	private int q_qiandao_count_limit;
	
	
	/**
	 * get 礼包ID
	 * @return 
	 */
	public int getQ_gift_id(){
		return q_gift_id;
	}
	
	/**
	 * set 礼包ID
	 */
	public void setQ_gift_id(int q_gift_id){
		this.q_gift_id = q_gift_id;
	}
	
	/**
	 * get 礼包类型(1.常规礼包，升级礼包；2.推广玩家礼品；3.连续登陆；4.周在线时间；5.延时礼品；6.签到奖励；7.累计签到次数领取奖励)
	 * @return 
	 */
	public int getQ_gift_type(){
		return q_gift_type;
	}
	
	/**
	 * set 礼包类型(1.常规礼包，升级礼包；2.推广玩家礼品；3.连续登陆；4.周在线时间；5.延时礼品；6.签到奖励；7.累计签到次数领取奖励)
	 */
	public void setQ_gift_type(int q_gift_type){
		this.q_gift_type = q_gift_type;
	}
	
	/**
	 * get 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
	 * @return 
	 */
	public String getQ_gift_data(){
		return q_gift_data;
	}
	
	/**
	 * set 奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
	 */
	public void setQ_gift_data(String q_gift_data){
		this.q_gift_data = q_gift_data;
	}
	
	/**
	 * get 互斥随机道具
	 * @return 
	 */
	public String getQ_huchi_random_gift_data(){
		return q_huchi_random_gift_data;
	}
	
	/**
	 * set 互斥随机道具
	 */
	public void setQ_huchi_random_gift_data(String q_huchi_random_gift_data){
		this.q_huchi_random_gift_data = q_huchi_random_gift_data;
	}
	
	/**
	 * get 随机道具
	 * @return 
	 */
	public String getQ_random_gift_data(){
		return q_random_gift_data;
	}
	
	/**
	 * set 随机道具
	 */
	public void setQ_random_gift_data(String q_random_gift_data){
		this.q_random_gift_data = q_random_gift_data;
	}
	
	/**
	 * get 奖励铜币
	 * @return 
	 */
	public int getQ_gift_money(){
		return q_gift_money;
	}
	
	/**
	 * set 奖励铜币
	 */
	public void setQ_gift_money(int q_gift_money){
		this.q_gift_money = q_gift_money;
	}
	
	/**
	 * get 奖励元宝
	 * @return 
	 */
	public int getQ_gift_gold(){
		return q_gift_gold;
	}
	
	/**
	 * set 奖励元宝
	 */
	public void setQ_gift_gold(int q_gift_gold){
		this.q_gift_gold = q_gift_gold;
	}
	
	/**
	 * get 延时时间
	 * @return 
	 */
	public int getQ_time_limit(){
		return q_time_limit;
	}
	
	/**
	 * set 延时时间
	 */
	public void setQ_time_limit(int q_time_limit){
		this.q_time_limit = q_time_limit;
	}
	
	/**
	 * get 推广人数
	 * @return 
	 */
	public int getQ_invite_limit(){
		return q_invite_limit;
	}
	
	/**
	 * set 推广人数
	 */
	public void setQ_invite_limit(int q_invite_limit){
		this.q_invite_limit = q_invite_limit;
	}
	
	/**
	 * get 登入天数领取
	 * @return 
	 */
	public int getQ_login_limit(){
		return q_login_limit;
	}
	
	/**
	 * set 登入天数领取
	 */
	public void setQ_login_limit(int q_login_limit){
		this.q_login_limit = q_login_limit;
	}
	
	/**
	 * get 周在线小时领取
	 * @return 
	 */
	public int getQ_online_time_limit(){
		return q_online_time_limit;
	}
	
	/**
	 * set 周在线小时领取
	 */
	public void setQ_online_time_limit(int q_online_time_limit){
		this.q_online_time_limit = q_online_time_limit;
	}
	
	/**
	 * get 签到次数统计
	 * @return 
	 */
	public int getQ_qiandao_count_limit(){
		return q_qiandao_count_limit;
	}
	
	/**
	 * set 签到次数统计
	 */
	public void setQ_qiandao_count_limit(int q_qiandao_count_limit){
		this.q_qiandao_count_limit = q_qiandao_count_limit;
	}
	
}
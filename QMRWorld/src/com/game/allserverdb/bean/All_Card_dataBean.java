package com.game.allserverdb.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Card_data Bean
 */
public class All_Card_dataBean {

	//卡id
	private String cardid;
	
	//平台
	private int agid;
	
	//类型
	private int type;
	
	//玩家账号
	private String account;
	
	//玩家id
	private long roleid;
	
	//实际平台
	private String web;

	public String getWeb() {
		return web;
	}

	public void setWeb(String web) {
		this.web = web;
	}

	/**
	 * get 卡id
	 * @return 
	 */
	public String getCardid(){
		return cardid;
	}
	
	/**
	 * set 卡id
	 */
	public void setCardid(String cardid){
		this.cardid = cardid;
	}
	
	/**
	 * get 平台
	 * @return 
	 */
	public int getAgid(){
		return agid;
	}
	
	/**
	 * set 平台
	 */
	public void setAgid(int agid){
		this.agid = agid;
	}
	
	/**
	 * get 类型
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 类型
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 玩家账号
	 * @return 
	 */
	public String getAccount(){
		return account;
	}
	
	/**
	 * set 玩家账号
	 */
	public void setAccount(String account){
		this.account = account;
	}
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getRoleid(){
		return roleid;
	}
	
	/**
	 * set 玩家id
	 */
	public void setRoleid(long roleid){
		this.roleid = roleid;
	}
	
}
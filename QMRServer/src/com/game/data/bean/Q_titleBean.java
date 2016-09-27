package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_title Bean
 */
public class Q_titleBean {

	//称号id
	private int q_titleid;
	
	//排行榜类型
	private int q_toptype;
	
	//排行榜名次
	private int q_topidx;
	
	//称号名
	private String q_titlename;
	
	
	/**
	 * get 称号id
	 * @return 
	 */
	public int getQ_titleid(){
		return q_titleid;
	}
	
	/**
	 * set 称号id
	 */
	public void setQ_titleid(int q_titleid){
		this.q_titleid = q_titleid;
	}
	
	/**
	 * get 排行榜类型
	 * @return 
	 */
	public int getQ_toptype(){
		return q_toptype;
	}
	
	/**
	 * set 排行榜类型
	 */
	public void setQ_toptype(int q_toptype){
		this.q_toptype = q_toptype;
	}
	
	/**
	 * get 排行榜名次
	 * @return 
	 */
	public int getQ_topidx(){
		return q_topidx;
	}
	
	/**
	 * set 排行榜名次
	 */
	public void setQ_topidx(int q_topidx){
		this.q_topidx = q_topidx;
	}
	
	/**
	 * get 称号名
	 * @return 
	 */
	public String getQ_titlename(){
		return q_titlename;
	}
	
	/**
	 * set 称号名
	 */
	public void setQ_titlename(String q_titlename){
		this.q_titlename = q_titlename;
	}
	
}
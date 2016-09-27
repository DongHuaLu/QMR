package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_meting_random Bean
 */
public class Q_meting_randomBean {

	//熔炼id(装备等级_属性类型)
	private String q_metingid;
	
	//装备等级
	private int q_itemlv;
	
	//属性类型
	private int q_attrtype;
	
	//属性条数
	private int q_num;
	
	//几率
	private int q_random;
	
	
	/**
	 * get 熔炼id(装备等级_属性类型)
	 * @return 
	 */
	public String getQ_metingid(){
		return q_metingid;
	}
	
	/**
	 * set 熔炼id(装备等级_属性类型)
	 */
	public void setQ_metingid(String q_metingid){
		this.q_metingid = q_metingid;
	}
	
	/**
	 * get 装备等级
	 * @return 
	 */
	public int getQ_itemlv(){
		return q_itemlv;
	}
	
	/**
	 * set 装备等级
	 */
	public void setQ_itemlv(int q_itemlv){
		this.q_itemlv = q_itemlv;
	}
	
	/**
	 * get 属性类型
	 * @return 
	 */
	public int getQ_attrtype(){
		return q_attrtype;
	}
	
	/**
	 * set 属性类型
	 */
	public void setQ_attrtype(int q_attrtype){
		this.q_attrtype = q_attrtype;
	}
	
	/**
	 * get 属性条数
	 * @return 
	 */
	public int getQ_num(){
		return q_num;
	}
	
	/**
	 * set 属性条数
	 */
	public void setQ_num(int q_num){
		this.q_num = q_num;
	}
	
	/**
	 * get 几率
	 * @return 
	 */
	public int getQ_random(){
		return q_random;
	}
	
	/**
	 * set 几率
	 */
	public void setQ_random(int q_random){
		this.q_random = q_random;
	}
	
}
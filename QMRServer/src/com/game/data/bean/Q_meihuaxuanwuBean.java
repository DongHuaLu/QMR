package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_meihuaxuanwu Bean
 */
public class Q_meihuaxuanwuBean {

	//阵形编号
	private int q_id;
	
	//阵形分布(红,蓝,绿,黄)
	private String q_zhenxing;
	
	//通关梅花桩所需数量上限
	private int q_limitneedhit;
	
	//通关破桩阶段所需时间上限(单位：秒)
	private int q_limitneedtime;
	
	//每次破桩减少BOSS血量
	private int q_decbosshp;
	
	//副本追踪面板提示描述信息（支持HTML）
	private String q_desc;
	
	
	/**
	 * get 阵形编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 阵形编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 阵形分布(红,蓝,绿,黄)
	 * @return 
	 */
	public String getQ_zhenxing(){
		return q_zhenxing;
	}
	
	/**
	 * set 阵形分布(红,蓝,绿,黄)
	 */
	public void setQ_zhenxing(String q_zhenxing){
		this.q_zhenxing = q_zhenxing;
	}
	
	/**
	 * get 通关梅花桩所需数量上限
	 * @return 
	 */
	public int getQ_limitneedhit(){
		return q_limitneedhit;
	}
	
	/**
	 * set 通关梅花桩所需数量上限
	 */
	public void setQ_limitneedhit(int q_limitneedhit){
		this.q_limitneedhit = q_limitneedhit;
	}
	
	/**
	 * get 通关破桩阶段所需时间上限(单位：秒)
	 * @return 
	 */
	public int getQ_limitneedtime(){
		return q_limitneedtime;
	}
	
	/**
	 * set 通关破桩阶段所需时间上限(单位：秒)
	 */
	public void setQ_limitneedtime(int q_limitneedtime){
		this.q_limitneedtime = q_limitneedtime;
	}
	
	/**
	 * get 每次破桩减少BOSS血量
	 * @return 
	 */
	public int getQ_decbosshp(){
		return q_decbosshp;
	}
	
	/**
	 * set 每次破桩减少BOSS血量
	 */
	public void setQ_decbosshp(int q_decbosshp){
		this.q_decbosshp = q_decbosshp;
	}
	
	/**
	 * get 副本追踪面板提示描述信息（支持HTML）
	 * @return 
	 */
	public String getQ_desc(){
		return q_desc;
	}
	
	/**
	 * set 副本追踪面板提示描述信息（支持HTML）
	 */
	public void setQ_desc(String q_desc){
		this.q_desc = q_desc;
	}
	
}
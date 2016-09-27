package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_horseweapon_skill Bean
 */
public class Q_horseweapon_skillBean {

	//技能格子
	private int q_grid;
	
	//技能类型（1,：龙元武学组合技能，2：固定组合技能）
	private int q_skilltype;
	
	//骑兵技能编号（1：龙元武学组合技能，固定组合技能ID）
	private String q_skillid;
	
	//显示所需骑兵阶数
	private int q_show_rank;
	
	//激活所需骑兵阶数
	private int q_active_rank;
	
	
	/**
	 * get 技能格子
	 * @return 
	 */
	public int getQ_grid(){
		return q_grid;
	}
	
	/**
	 * set 技能格子
	 */
	public void setQ_grid(int q_grid){
		this.q_grid = q_grid;
	}
	
	/**
	 * get 技能类型（1,：龙元武学组合技能，2：固定组合技能）
	 * @return 
	 */
	public int getQ_skilltype(){
		return q_skilltype;
	}
	
	/**
	 * set 技能类型（1,：龙元武学组合技能，2：固定组合技能）
	 */
	public void setQ_skilltype(int q_skilltype){
		this.q_skilltype = q_skilltype;
	}
	
	/**
	 * get 骑兵技能编号（1：龙元武学组合技能，固定组合技能ID）
	 * @return 
	 */
	public String getQ_skillid(){
		return q_skillid;
	}
	
	/**
	 * set 骑兵技能编号（1：龙元武学组合技能，固定组合技能ID）
	 */
	public void setQ_skillid(String q_skillid){
		this.q_skillid = q_skillid;
	}
	
	/**
	 * get 显示所需骑兵阶数
	 * @return 
	 */
	public int getQ_show_rank(){
		return q_show_rank;
	}
	
	/**
	 * set 显示所需骑兵阶数
	 */
	public void setQ_show_rank(int q_show_rank){
		this.q_show_rank = q_show_rank;
	}
	
	/**
	 * get 激活所需骑兵阶数
	 * @return 
	 */
	public int getQ_active_rank(){
		return q_active_rank;
	}
	
	/**
	 * set 激活所需骑兵阶数
	 */
	public void setQ_active_rank(int q_active_rank){
		this.q_active_rank = q_active_rank;
	}
	
}
package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_qingfengguyun Bean
 */
public class Q_qingfengguyunBean {

	//诗词编号
	private int q_id;
	
	//诗词名句
	private String q_shichi;
	
	//刷新波次min
	private int q_min_refresh;
	
	//属性波次max
	private int q_max_refresh;
	
	
	/**
	 * get 诗词编号
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 诗词编号
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 诗词名句
	 * @return 
	 */
	public String getQ_shichi(){
		return q_shichi;
	}
	
	/**
	 * set 诗词名句
	 */
	public void setQ_shichi(String q_shichi){
		this.q_shichi = q_shichi;
	}
	
	/**
	 * get 刷新波次min
	 * @return 
	 */
	public int getQ_min_refresh(){
		return q_min_refresh;
	}
	
	/**
	 * set 刷新波次min
	 */
	public void setQ_min_refresh(int q_min_refresh){
		this.q_min_refresh = q_min_refresh;
	}
	
	/**
	 * get 属性波次max
	 * @return 
	 */
	public int getQ_max_refresh(){
		return q_max_refresh;
	}
	
	/**
	 * set 属性波次max
	 */
	public void setQ_max_refresh(int q_max_refresh){
		this.q_max_refresh = q_max_refresh;
	}
	
}
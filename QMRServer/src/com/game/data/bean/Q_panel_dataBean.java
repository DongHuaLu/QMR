package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_panel_data Bean
 */
public class Q_panel_dataBean {

	//索引(面板名称)
	private int q_id;
	
	//面板xml代码
	private String q_panel_xml;
	
	//面板id
	private String q_panel_id;
	
	//面板说明，名字
	private String q_explain;
	
	
	/**
	 * get 索引(面板名称)
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 索引(面板名称)
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 面板xml代码
	 * @return 
	 */
	public String getQ_panel_xml(){
		return q_panel_xml;
	}
	
	/**
	 * set 面板xml代码
	 */
	public void setQ_panel_xml(String q_panel_xml){
		this.q_panel_xml = q_panel_xml;
	}
	
	/**
	 * get 面板id
	 * @return 
	 */
	public String getQ_panel_id(){
		return q_panel_id;
	}
	
	/**
	 * set 面板id
	 */
	public void setQ_panel_id(String q_panel_id){
		this.q_panel_id = q_panel_id;
	}
	
	/**
	 * get 面板说明，名字
	 * @return 
	 */
	public String getQ_explain(){
		return q_explain;
	}
	
	/**
	 * set 面板说明，名字
	 */
	public void setQ_explain(String q_explain){
		this.q_explain = q_explain;
	}
	
}
package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_tipconfig Bean
 */
public class Q_tipconfigBean {

	//tips名
	private String tipsID;
	
	//渲染类名称
	private String classID;
	
	//固定宽-1为自动
	private int width;
	
	//固定高,-1为自动
	private int heigth;
	
	//html模板
	private String html;
	
	
	/**
	 * get tips名
	 * @return 
	 */
	public String getTipsID(){
		return tipsID;
	}
	
	/**
	 * set tips名
	 */
	public void setTipsID(String tipsID){
		this.tipsID = tipsID;
	}
	
	/**
	 * get 渲染类名称
	 * @return 
	 */
	public String getClassID(){
		return classID;
	}
	
	/**
	 * set 渲染类名称
	 */
	public void setClassID(String classID){
		this.classID = classID;
	}
	
	/**
	 * get 固定宽-1为自动
	 * @return 
	 */
	public int getWidth(){
		return width;
	}
	
	/**
	 * set 固定宽-1为自动
	 */
	public void setWidth(int width){
		this.width = width;
	}
	
	/**
	 * get 固定高,-1为自动
	 * @return 
	 */
	public int getHeigth(){
		return heigth;
	}
	
	/**
	 * set 固定高,-1为自动
	 */
	public void setHeigth(int heigth){
		this.heigth = heigth;
	}
	
	/**
	 * get html模板
	 * @return 
	 */
	public String getHtml(){
		return html;
	}
	
	/**
	 * set html模板
	 */
	public void setHtml(String html){
		this.html = html;
	}
	
}
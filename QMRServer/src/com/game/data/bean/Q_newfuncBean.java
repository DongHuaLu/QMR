package com.game.data.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_newfunc Bean
 */
public class Q_newfuncBean {

	//编号id
	private int q_id;
	
	//功能名字
	private String q_string_name;
	
	//所属类型
	private String q_main_type;
	
	//开放等级
	private int q_level;
	
	//需要完成任务编号
	private int q_need_taskid;
	
	//表现形式（0为不弹，1为弹出，2弹出tips框）
	private int q_show_panel;
	
	//功能描述（支持html）
	private String q_info;
	
	//图片路径
	private String q_img_url;
	
	
	/**
	 * get 编号id
	 * @return 
	 */
	public int getQ_id(){
		return q_id;
	}
	
	/**
	 * set 编号id
	 */
	public void setQ_id(int q_id){
		this.q_id = q_id;
	}
	
	/**
	 * get 功能名字
	 * @return 
	 */
	public String getQ_string_name(){
		return q_string_name;
	}
	
	/**
	 * set 功能名字
	 */
	public void setQ_string_name(String q_string_name){
		this.q_string_name = q_string_name;
	}
	
	/**
	 * get 所属类型
	 * @return 
	 */
	public String getQ_main_type(){
		return q_main_type;
	}
	
	/**
	 * set 所属类型
	 */
	public void setQ_main_type(String q_main_type){
		this.q_main_type = q_main_type;
	}
	
	/**
	 * get 开放等级
	 * @return 
	 */
	public int getQ_level(){
		return q_level;
	}
	
	/**
	 * set 开放等级
	 */
	public void setQ_level(int q_level){
		this.q_level = q_level;
	}
	
	/**
	 * get 需要完成任务编号
	 * @return 
	 */
	public int getQ_need_taskid(){
		return q_need_taskid;
	}
	
	/**
	 * set 需要完成任务编号
	 */
	public void setQ_need_taskid(int q_need_taskid){
		this.q_need_taskid = q_need_taskid;
	}
	
	/**
	 * get 表现形式（0为不弹，1为弹出，2弹出tips框）
	 * @return 
	 */
	public int getQ_show_panel(){
		return q_show_panel;
	}
	
	/**
	 * set 表现形式（0为不弹，1为弹出，2弹出tips框）
	 */
	public void setQ_show_panel(int q_show_panel){
		this.q_show_panel = q_show_panel;
	}
	
	/**
	 * get 功能描述（支持html）
	 * @return 
	 */
	public String getQ_info(){
		return q_info;
	}
	
	/**
	 * set 功能描述（支持html）
	 */
	public void setQ_info(String q_info){
		this.q_info = q_info;
	}
	
	/**
	 * get 图片路径
	 * @return 
	 */
	public String getQ_img_url(){
		return q_img_url;
	}
	
	/**
	 * set 图片路径
	 */
	public void setQ_img_url(String q_img_url){
		this.q_img_url = q_img_url;
	}
	
}
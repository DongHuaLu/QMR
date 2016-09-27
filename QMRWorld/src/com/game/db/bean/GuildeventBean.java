package com.game.db.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Guildevent Bean
 */
public class GuildeventBean {

	//事件id
	private long eventid;
	
	//公会id
	private long guildid;
	
	//事件类型
	private String eventtype;
	
	//事件时间
	private int eventtime;
	
	//事件数据
	private String eventdata;
	
	
	/**
	 * get 事件id
	 * @return 
	 */
	public long getEventid(){
		return eventid;
	}
	
	/**
	 * set 事件id
	 */
	public void setEventid(long eventid){
		this.eventid = eventid;
	}
	
	/**
	 * get 公会id
	 * @return 
	 */
	public long getGuildid(){
		return guildid;
	}
	
	/**
	 * set 公会id
	 */
	public void setGuildid(long guildid){
		this.guildid = guildid;
	}
	
	/**
	 * get 事件类型
	 * @return 
	 */
	public String getEventtype(){
		return eventtype;
	}
	
	/**
	 * set 事件类型
	 */
	public void setEventtype(String eventtype){
		this.eventtype = eventtype;
	}
	
	/**
	 * get 事件时间
	 * @return 
	 */
	public int getEventtime(){
		return eventtime;
	}
	
	/**
	 * set 事件时间
	 */
	public void setEventtime(int eventtime){
		this.eventtime = eventtime;
	}
	
	/**
	 * get 事件数据
	 * @return 
	 */
	public String getEventdata(){
		return eventdata;
	}
	
	/**
	 * set 事件数据
	 */
	public void setEventdata(String eventdata){
		this.eventdata = eventdata;
	}
	
}
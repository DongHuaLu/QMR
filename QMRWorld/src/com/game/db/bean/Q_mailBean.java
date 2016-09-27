package com.game.db.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Q_mail Bean
 */
public class Q_mailBean {

	//邮件id
	private long mail_id;
	
	//发送者名字
	private String send_name;
	
	//接受者id
	private long receiver_id;
	
	//接受者名字
	private String receiver_name;
	
	//发送时间
	private int send_time;
	
	//是否有附件（1是0否）
	private int btAccessory;
	
	//是否系统邮件（1是0否）
	private int btSystem;
	
	//是否已读取（1是0否）
	private int btRead;
	
	//是否是回信（1是0否）
	private int btReturn;
	
	//邮件数据
	private String mail_data;
	
	
	/**
	 * get 邮件id
	 * @return 
	 */
	public long getMail_id(){
		return mail_id;
	}
	
	/**
	 * set 邮件id
	 */
	public void setMail_id(long mail_id){
		this.mail_id = mail_id;
	}
	
	/**
	 * get 发送者名字
	 * @return 
	 */
	public String getSend_name(){
		return send_name;
	}
	
	/**
	 * set 发送者名字
	 */
	public void setSend_name(String send_name){
		this.send_name = send_name;
	}
	
	/**
	 * get 接受者id
	 * @return 
	 */
	public long getReceiver_id() {
		return receiver_id;
	}

	/**
	 * set 接受者id
	 */
	public void setReceiver_id(long receiver_id) {
		this.receiver_id = receiver_id;
	}
	
	/**
	 * get 接受者名字
	 * @return 
	 */
	public String getReceiver_name(){
		return receiver_name;
	}
	
	/**
	 * set 接受者名字
	 */
	public void setReceiver_name(String receiver_name){
		this.receiver_name = receiver_name;
	}
	
	/**
	 * get 发送时间
	 * @return 
	 */
	public int getSend_time(){
		return send_time;
	}
	
	/**
	 * set 发送时间
	 */
	public void setSend_time(int send_time){
		this.send_time = send_time;
	}
	
	/**
	 * get 是否有附件（1是0否）
	 * @return 
	 */
	public int getBtAccessory(){
		return btAccessory;
	}
	
	/**
	 * set 是否有附件（1是0否）
	 */
	public void setBtAccessory(int btAccessory){
		this.btAccessory = btAccessory;
	}
	
	/**
	 * get 是否系统邮件（1是0否）
	 * @return 
	 */
	public int getBtSystem(){
		return btSystem;
	}
	
	/**
	 * set 是否系统邮件（1是0否）
	 */
	public void setBtSystem(int btSystem){
		this.btSystem = btSystem;
	}
	
	/**
	 * get 是否已读取（1是0否）
	 * @return 
	 */
	public int getBtRead(){
		return btRead;
	}
	
	/**
	 * set 是否已读取（1是0否）
	 */
	public void setBtRead(int btRead){
		this.btRead = btRead;
	}
	
	/**
	 * get 是否是回信（1是0否）
	 * @return 
	 */
	public int getBtReturn(){
		return btReturn;
	}
	
	/**
	 * set 是否是回信（1是0否）
	 */
	public void setBtReturn(int btReturn){
		this.btReturn = btReturn;
	}
	
	/**
	 * get 邮件数据
	 * @return 
	 */
	public String getMail_data(){
		return mail_data;
	}
	
	/**
	 * set 邮件数据
	 */
	public void setMail_data(String mail_data){
		this.mail_data = mail_data;
	}
	
}
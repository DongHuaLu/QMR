package com.game.db.bean;

/** 
 * @author ExcelUtil Auto Maker
 * 
 * @version 1.0.0
 * 
 * Phone_card Bean
 */
public class Phone_cardBean {

	//账号id
	private String account;
	
	//平台
	private int agid;
	
	//类型
	private int type;
	
	//手机号码
	private String phone;
	
	//玩家id
	private long roleid;
	
	//验证码
	private String vercode;
	
	//手机验证次数，每天只有3次
	private int phonevernum;
	
	//验证码错误次数，根据每个手机的更换清除
	private int vererrornum;
	
	//手机验证时间
	private long phonevertime;
	
	
	/**
	 * get 账号id
	 * @return 
	 */
	public String getAccount(){
		return account;
	}
	
	/**
	 * set 账号id
	 */
	public void setAccount(String account){
		this.account = account;
	}
	
	/**
	 * get 平台
	 * @return 
	 */
	public int getAgid(){
		return agid;
	}
	
	/**
	 * set 平台
	 */
	public void setAgid(int agid){
		this.agid = agid;
	}
	
	/**
	 * get 类型
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 类型
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 手机号码
	 * @return 
	 */
	public String getPhone(){
		return phone;
	}
	
	/**
	 * set 手机号码
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getRoleid(){
		return roleid;
	}
	
	/**
	 * set 玩家id
	 */
	public void setRoleid(long roleid){
		this.roleid = roleid;
	}
	
	/**
	 * get 验证码
	 * @return 
	 */
	public String getVercode(){
		return vercode;
	}
	
	/**
	 * set 验证码
	 */
	public void setVercode(String vercode){
		this.vercode = vercode;
	}
	
	/**
	 * get 手机验证次数，每天只有3次
	 * @return 
	 */
	public int getPhonevernum(){
		return phonevernum;
	}
	
	/**
	 * set 手机验证次数，每天只有3次
	 */
	public void setPhonevernum(int phonevernum){
		this.phonevernum = phonevernum;
	}
	
	/**
	 * get 验证码错误次数，根据每个手机的更换清除
	 * @return 
	 */
	public int getVererrornum(){
		return vererrornum;
	}
	
	/**
	 * set 验证码错误次数，根据每个手机的更换清除
	 */
	public void setVererrornum(int vererrornum){
		this.vererrornum = vererrornum;
	}
	
	/**
	 * get 手机验证时间
	 * @return 
	 */
	public long getPhonevertime(){
		return phonevertime;
	}
	
	/**
	 * set 手机验证时间
	 */
	public void setPhonevertime(long phonevertime){
		this.phonevertime = phonevertime;
	}
	
}
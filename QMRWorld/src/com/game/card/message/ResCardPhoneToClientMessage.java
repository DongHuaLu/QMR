package com.game.card.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求手机绑定返回消息
 */
public class ResCardPhoneToClientMessage extends Message{

	//错误代码
	private byte errorcode;
	
	//状态
	private byte status;
	
	//账号
	private String account;
	
	//角色id
	private long playerid;
	
	//电话
	private String phone;
	
	//平台id
	private int agid;
	
	//礼包类型
	private int type;
	
	//验证码
	private String vercode;
	
	//手机验证次数，每天只有3次
	private int phonevernum;
	
	//验证码错误次数，根据每个手机的更换清除
	private int vererrornum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.errorcode);
		//状态
		writeByte(buf, this.status);
		//账号
		writeString(buf, this.account);
		//角色id
		writeLong(buf, this.playerid);
		//电话
		writeString(buf, this.phone);
		//平台id
		writeInt(buf, this.agid);
		//礼包类型
		writeInt(buf, this.type);
		//验证码
		writeString(buf, this.vercode);
		//手机验证次数，每天只有3次
		writeInt(buf, this.phonevernum);
		//验证码错误次数，根据每个手机的更换清除
		writeInt(buf, this.vererrornum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.errorcode = readByte(buf);
		//状态
		this.status = readByte(buf);
		//账号
		this.account = readString(buf);
		//角色id
		this.playerid = readLong(buf);
		//电话
		this.phone = readString(buf);
		//平台id
		this.agid = readInt(buf);
		//礼包类型
		this.type = readInt(buf);
		//验证码
		this.vercode = readString(buf);
		//手机验证次数，每天只有3次
		this.phonevernum = readInt(buf);
		//验证码错误次数，根据每个手机的更换清除
		this.vererrornum = readInt(buf);
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getErrorcode(){
		return errorcode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setErrorcode(byte errorcode){
		this.errorcode = errorcode;
	}
	
	/**
	 * get 状态
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 状态
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	/**
	 * get 账号
	 * @return 
	 */
	public String getAccount(){
		return account;
	}
	
	/**
	 * set 账号
	 */
	public void setAccount(String account){
		this.account = account;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 电话
	 * @return 
	 */
	public String getPhone(){
		return phone;
	}
	
	/**
	 * set 电话
	 */
	public void setPhone(String phone){
		this.phone = phone;
	}
	
	/**
	 * get 平台id
	 * @return 
	 */
	public int getAgid(){
		return agid;
	}
	
	/**
	 * set 平台id
	 */
	public void setAgid(int agid){
		this.agid = agid;
	}
	
	/**
	 * get 礼包类型
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 礼包类型
	 */
	public void setType(int type){
		this.type = type;
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
	
	
	@Override
	public int getId() {
		return 137251;
	}

	@Override
	public String getQueue() {
		return null;
	}
	
	@Override
	public String getServer() {
		return null;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//错误代码
		buf.append("errorcode:" + errorcode +",");
		//状态
		buf.append("status:" + status +",");
		//账号
		if(this.account!=null) buf.append("account:" + account.toString() +",");
		//角色id
		buf.append("playerid:" + playerid +",");
		//电话
		if(this.phone!=null) buf.append("phone:" + phone.toString() +",");
		//平台id
		buf.append("agid:" + agid +",");
		//礼包类型
		buf.append("type:" + type +",");
		//验证码
		if(this.vercode!=null) buf.append("vercode:" + vercode.toString() +",");
		//手机验证次数，每天只有3次
		buf.append("phonevernum:" + phonevernum +",");
		//验证码错误次数，根据每个手机的更换清除
		buf.append("vererrornum:" + vererrornum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
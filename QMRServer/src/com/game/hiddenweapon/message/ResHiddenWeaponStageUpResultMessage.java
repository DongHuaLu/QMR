package com.game.hiddenweapon.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送暗器升阶结果消息
 */
public class ResHiddenWeaponStageUpResultMessage extends Message{

	//升阶结果，0未升级，1升级
	private byte type;
	
	//当前祝福值
	private int bless;
	
	//获得祝福值
	private int gotbless;
	
	//获得经验
	private int gotexp;
	
	//是否暴击经验，0正常，1小暴击，2大暴击
	private byte crit;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//升阶结果，0未升级，1升级
		writeByte(buf, this.type);
		//当前祝福值
		writeInt(buf, this.bless);
		//获得祝福值
		writeInt(buf, this.gotbless);
		//获得经验
		writeInt(buf, this.gotexp);
		//是否暴击经验，0正常，1小暴击，2大暴击
		writeByte(buf, this.crit);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//升阶结果，0未升级，1升级
		this.type = readByte(buf);
		//当前祝福值
		this.bless = readInt(buf);
		//获得祝福值
		this.gotbless = readInt(buf);
		//获得经验
		this.gotexp = readInt(buf);
		//是否暴击经验，0正常，1小暴击，2大暴击
		this.crit = readByte(buf);
		return true;
	}
	
	/**
	 * get 升阶结果，0未升级，1升级
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 升阶结果，0未升级，1升级
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 当前祝福值
	 * @return 
	 */
	public int getBless(){
		return bless;
	}
	
	/**
	 * set 当前祝福值
	 */
	public void setBless(int bless){
		this.bless = bless;
	}
	
	/**
	 * get 获得祝福值
	 * @return 
	 */
	public int getGotbless(){
		return gotbless;
	}
	
	/**
	 * set 获得祝福值
	 */
	public void setGotbless(int gotbless){
		this.gotbless = gotbless;
	}
	
	/**
	 * get 获得经验
	 * @return 
	 */
	public int getGotexp(){
		return gotexp;
	}
	
	/**
	 * set 获得经验
	 */
	public void setGotexp(int gotexp){
		this.gotexp = gotexp;
	}
	
	/**
	 * get 是否暴击经验，0正常，1小暴击，2大暴击
	 * @return 
	 */
	public byte getCrit(){
		return crit;
	}
	
	/**
	 * set 是否暴击经验，0正常，1小暴击，2大暴击
	 */
	public void setCrit(byte crit){
		this.crit = crit;
	}
	
	
	@Override
	public int getId() {
		return 162104;
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
		//升阶结果，0未升级，1升级
		buf.append("type:" + type +",");
		//当前祝福值
		buf.append("bless:" + bless +",");
		//获得祝福值
		buf.append("gotbless:" + gotbless +",");
		//获得经验
		buf.append("gotexp:" + gotexp +",");
		//是否暴击经验，0正常，1小暴击，2大暴击
		buf.append("crit:" + crit +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.player.message;

import com.game.player.bean.PlayerAppearanceInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获取玩家造型信息消息
 */
public class ResGetPlayerAppearanceInfMessage extends Message{

	//他人ID
	private long othersid;
	
	//面板类型
	private byte type;
	
	//外观信息
	private PlayerAppearanceInfo appearanceInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//他人ID
		writeLong(buf, this.othersid);
		//面板类型
		writeByte(buf, this.type);
		//外观信息
		writeBean(buf, this.appearanceInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//他人ID
		this.othersid = readLong(buf);
		//面板类型
		this.type = readByte(buf);
		//外观信息
		this.appearanceInfo = (PlayerAppearanceInfo)readBean(buf, PlayerAppearanceInfo.class);
		return true;
	}
	
	/**
	 * get 他人ID
	 * @return 
	 */
	public long getOthersid(){
		return othersid;
	}
	
	/**
	 * set 他人ID
	 */
	public void setOthersid(long othersid){
		this.othersid = othersid;
	}
	
	/**
	 * get 面板类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 面板类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 外观信息
	 * @return 
	 */
	public PlayerAppearanceInfo getAppearanceInfo(){
		return appearanceInfo;
	}
	
	/**
	 * set 外观信息
	 */
	public void setAppearanceInfo(PlayerAppearanceInfo appearanceInfo){
		this.appearanceInfo = appearanceInfo;
	}
	
	
	@Override
	public int getId() {
		return 103126;
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
		//他人ID
		buf.append("othersid:" + othersid +",");
		//面板类型
		buf.append("type:" + type +",");
		//外观信息
		if(this.appearanceInfo!=null) buf.append("appearanceInfo:" + appearanceInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
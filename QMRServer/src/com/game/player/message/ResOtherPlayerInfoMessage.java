package com.game.player.message;

import com.game.player.bean.OtherPlayerInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 他人玩家信息消息
 */
public class ResOtherPlayerInfoMessage extends Message{

	//他人玩家信息
	private OtherPlayerInfo otherPlayerInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//他人玩家信息
		writeBean(buf, this.otherPlayerInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//他人玩家信息
		this.otherPlayerInfo = (OtherPlayerInfo)readBean(buf, OtherPlayerInfo.class);
		return true;
	}
	
	/**
	 * get 他人玩家信息
	 * @return 
	 */
	public OtherPlayerInfo getOtherPlayerInfo(){
		return otherPlayerInfo;
	}
	
	/**
	 * set 他人玩家信息
	 */
	public void setOtherPlayerInfo(OtherPlayerInfo otherPlayerInfo){
		this.otherPlayerInfo = otherPlayerInfo;
	}
	
	
	@Override
	public int getId() {
		return 103108;
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
		//他人玩家信息
		if(this.otherPlayerInfo!=null) buf.append("otherPlayerInfo:" + otherPlayerInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
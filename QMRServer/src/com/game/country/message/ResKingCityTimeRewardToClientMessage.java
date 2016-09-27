package com.game.country.message;

import com.game.country.bean.WarRewardInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 在线时间奖励消息
 */
public class ResKingCityTimeRewardToClientMessage extends Message{

	//在线奖励
	private WarRewardInfo warrewardinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//在线奖励
		writeBean(buf, this.warrewardinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//在线奖励
		this.warrewardinfo = (WarRewardInfo)readBean(buf, WarRewardInfo.class);
		return true;
	}
	
	/**
	 * get 在线奖励
	 * @return 
	 */
	public WarRewardInfo getWarrewardinfo(){
		return warrewardinfo;
	}
	
	/**
	 * set 在线奖励
	 */
	public void setWarrewardinfo(WarRewardInfo warrewardinfo){
		this.warrewardinfo = warrewardinfo;
	}
	
	
	@Override
	public int getId() {
		return 146110;
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
		//在线奖励
		if(this.warrewardinfo!=null) buf.append("warrewardinfo:" + warrewardinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
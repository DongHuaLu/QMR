package com.game.vip.message;

import com.game.vip.bean.VipInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回玩家VIP信息消息
 */
public class ResPlayerVIPInfoMessage extends Message{

	//玩家VIP信息
	private VipInfo vipinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家VIP信息
		writeBean(buf, this.vipinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家VIP信息
		this.vipinfo = (VipInfo)readBean(buf, VipInfo.class);
		return true;
	}
	
	/**
	 * get 玩家VIP信息
	 * @return 
	 */
	public VipInfo getVipinfo(){
		return vipinfo;
	}
	
	/**
	 * set 玩家VIP信息
	 */
	public void setVipinfo(VipInfo vipinfo){
		this.vipinfo = vipinfo;
	}
	
	
	@Override
	public int getId() {
		return 147101;
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
		//玩家VIP信息
		if(this.vipinfo!=null) buf.append("vipinfo:" + vipinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.vip.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知客户端广播成为VIP公告消息
 */
public class ResVIPAnnounceMessage extends Message{

	//玩家角色名
	private String playername;
	
	//玩家的vipid
	private int vipid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家角色名
		writeString(buf, this.playername);
		//玩家的vipid
		writeInt(buf, this.vipid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家角色名
		this.playername = readString(buf);
		//玩家的vipid
		this.vipid = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家角色名
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玩家角色名
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 玩家的vipid
	 * @return 
	 */
	public int getVipid(){
		return vipid;
	}
	
	/**
	 * set 玩家的vipid
	 */
	public void setVipid(int vipid){
		this.vipid = vipid;
	}
	
	
	@Override
	public int getId() {
		return 147103;
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
		//玩家角色名
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//玩家的vipid
		buf.append("vipid:" + vipid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
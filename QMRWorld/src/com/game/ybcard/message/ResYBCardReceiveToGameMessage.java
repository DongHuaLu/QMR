package com.game.ybcard.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 领取公测元宝卡内的元宝消息
 */
public class ResYBCardReceiveToGameMessage extends Message{

	//玩家Id
	private long playerid;
	
	//领取的元宝总数量
	private int yuanbaonum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家Id
		writeLong(buf, this.playerid);
		//领取的元宝总数量
		writeInt(buf, this.yuanbaonum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家Id
		this.playerid = readLong(buf);
		//领取的元宝总数量
		this.yuanbaonum = readInt(buf);
		return true;
	}
	
	/**
	 * get 玩家Id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家Id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 领取的元宝总数量
	 * @return 
	 */
	public int getYuanbaonum(){
		return yuanbaonum;
	}
	
	/**
	 * set 领取的元宝总数量
	 */
	public void setYuanbaonum(int yuanbaonum){
		this.yuanbaonum = yuanbaonum;
	}
	
	
	@Override
	public int getId() {
		return 139302;
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
		//玩家Id
		buf.append("playerid:" + playerid +",");
		//领取的元宝总数量
		buf.append("yuanbaonum:" + yuanbaonum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
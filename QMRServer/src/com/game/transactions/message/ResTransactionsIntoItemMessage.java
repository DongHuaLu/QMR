package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 放入道具消息
 */
public class ResTransactionsIntoItemMessage extends Message{

	//放入道具的玩家ID
	private long playerid;
	
	//放入交易框的位置
	private short itemposition;
	
	//道具信息
	private com.game.backpack.bean.ItemInfo iteminfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//放入道具的玩家ID
		writeLong(buf, this.playerid);
		//放入交易框的位置
		writeShort(buf, this.itemposition);
		//道具信息
		writeBean(buf, this.iteminfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//放入道具的玩家ID
		this.playerid = readLong(buf);
		//放入交易框的位置
		this.itemposition = readShort(buf);
		//道具信息
		this.iteminfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		return true;
	}
	
	/**
	 * get 放入道具的玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 放入道具的玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 放入交易框的位置
	 * @return 
	 */
	public short getItemposition(){
		return itemposition;
	}
	
	/**
	 * set 放入交易框的位置
	 */
	public void setItemposition(short itemposition){
		this.itemposition = itemposition;
	}
	
	/**
	 * get 道具信息
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getIteminfo(){
		return iteminfo;
	}
	
	/**
	 * set 道具信息
	 */
	public void setIteminfo(com.game.backpack.bean.ItemInfo iteminfo){
		this.iteminfo = iteminfo;
	}
	
	
	@Override
	public int getId() {
		return 122104;
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
		//放入道具的玩家ID
		buf.append("playerid:" + playerid +",");
		//放入交易框的位置
		buf.append("itemposition:" + itemposition +",");
		//道具信息
		if(this.iteminfo!=null) buf.append("iteminfo:" + iteminfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
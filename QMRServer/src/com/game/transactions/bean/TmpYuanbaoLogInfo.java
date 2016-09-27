package com.game.transactions.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 临时元宝日志信息
 */
public class TmpYuanbaoLogInfo extends Bean {

	//和本人交易的玩家ID
	private long playerid;
	
	//玩家名字
	private String playername;
	
	//交易类型
	private byte type;
	
	//交易时间
	private int time;
	
	//道具信息
	private com.game.backpack.bean.ItemInfo iteminfo;
	
	//交易的道具数量
	private int num;
	
	//获得金币数量
	private int goldnum;
	
	//获得元宝数量
	private int yuanbaonum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//和本人交易的玩家ID
		writeLong(buf, this.playerid);
		//玩家名字
		writeString(buf, this.playername);
		//交易类型
		writeByte(buf, this.type);
		//交易时间
		writeInt(buf, this.time);
		//道具信息
		writeBean(buf, this.iteminfo);
		//交易的道具数量
		writeInt(buf, this.num);
		//获得金币数量
		writeInt(buf, this.goldnum);
		//获得元宝数量
		writeInt(buf, this.yuanbaonum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//和本人交易的玩家ID
		this.playerid = readLong(buf);
		//玩家名字
		this.playername = readString(buf);
		//交易类型
		this.type = readByte(buf);
		//交易时间
		this.time = readInt(buf);
		//道具信息
		this.iteminfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		//交易的道具数量
		this.num = readInt(buf);
		//获得金币数量
		this.goldnum = readInt(buf);
		//获得元宝数量
		this.yuanbaonum = readInt(buf);
		return true;
	}
	
	/**
	 * get 和本人交易的玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 和本人交易的玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 玩家名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玩家名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 交易类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 交易类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 交易时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 交易时间
	 */
	public void setTime(int time){
		this.time = time;
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
	
	/**
	 * get 交易的道具数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 交易的道具数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 获得金币数量
	 * @return 
	 */
	public int getGoldnum(){
		return goldnum;
	}
	
	/**
	 * set 获得金币数量
	 */
	public void setGoldnum(int goldnum){
		this.goldnum = goldnum;
	}
	
	/**
	 * get 获得元宝数量
	 * @return 
	 */
	public int getYuanbaonum(){
		return yuanbaonum;
	}
	
	/**
	 * set 获得元宝数量
	 */
	public void setYuanbaonum(int yuanbaonum){
		this.yuanbaonum = yuanbaonum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//和本人交易的玩家ID
		buf.append("playerid:" + playerid +",");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//交易类型
		buf.append("type:" + type +",");
		//交易时间
		buf.append("time:" + time +",");
		//道具信息
		if(this.iteminfo!=null) buf.append("iteminfo:" + iteminfo.toString() +",");
		//交易的道具数量
		buf.append("num:" + num +",");
		//获得金币数量
		buf.append("goldnum:" + goldnum +",");
		//获得元宝数量
		buf.append("yuanbaonum:" + yuanbaonum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
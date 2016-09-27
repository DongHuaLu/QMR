package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送给GAME产生道具奖励，找不到玩家就发邮件消息
 */
public class ResGameMakeFruitInfoMessage extends Message{

	//玩家ID
	private long playerid;
	
	//果实ID
	private long fruitid;
	
	//果实类型：0普通果实，1银色奇异果，2金色奇异果
	private byte type;
	
	//序列化的果实奖励数据
	private String jsFruitdata;
	
	//果实主人ID
	private long hostid;
	
	//事件ID
	private long eventid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//果实ID
		writeLong(buf, this.fruitid);
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		writeByte(buf, this.type);
		//序列化的果实奖励数据
		writeString(buf, this.jsFruitdata);
		//果实主人ID
		writeLong(buf, this.hostid);
		//事件ID
		writeLong(buf, this.eventid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//果实ID
		this.fruitid = readLong(buf);
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		this.type = readByte(buf);
		//序列化的果实奖励数据
		this.jsFruitdata = readString(buf);
		//果实主人ID
		this.hostid = readLong(buf);
		//事件ID
		this.eventid = readLong(buf);
		return true;
	}
	
	/**
	 * get 玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 果实ID
	 * @return 
	 */
	public long getFruitid(){
		return fruitid;
	}
	
	/**
	 * set 果实ID
	 */
	public void setFruitid(long fruitid){
		this.fruitid = fruitid;
	}
	
	/**
	 * get 果实类型：0普通果实，1银色奇异果，2金色奇异果
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 果实类型：0普通果实，1银色奇异果，2金色奇异果
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 序列化的果实奖励数据
	 * @return 
	 */
	public String getJsFruitdata(){
		return jsFruitdata;
	}
	
	/**
	 * set 序列化的果实奖励数据
	 */
	public void setJsFruitdata(String jsFruitdata){
		this.jsFruitdata = jsFruitdata;
	}
	
	/**
	 * get 果实主人ID
	 * @return 
	 */
	public long getHostid(){
		return hostid;
	}
	
	/**
	 * set 果实主人ID
	 */
	public void setHostid(long hostid){
		this.hostid = hostid;
	}
	
	/**
	 * get 事件ID
	 * @return 
	 */
	public long getEventid(){
		return eventid;
	}
	
	/**
	 * set 事件ID
	 */
	public void setEventid(long eventid){
		this.eventid = eventid;
	}
	
	
	@Override
	public int getId() {
		return 133303;
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
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//果实ID
		buf.append("fruitid:" + fruitid +",");
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		buf.append("type:" + type +",");
		//序列化的果实奖励数据
		if(this.jsFruitdata!=null) buf.append("jsFruitdata:" + jsFruitdata.toString() +",");
		//果实主人ID
		buf.append("hostid:" + hostid +",");
		//事件ID
		buf.append("eventid:" + eventid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.backend.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 禁言玩家消息
 */
public class ReqJinYanMessage extends Message{

	//角色id
	private long playerId;
	
	//服务器编号
	private int createServerId;
	
	//用户id
	private String userId;
	
	//开始禁言时间
	private long startTime;
	
	//禁言时长
	private long times;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//服务器编号
		writeInt(buf, this.createServerId);
		//用户id
		writeString(buf, this.userId);
		//开始禁言时间
		writeLong(buf, this.startTime);
		//禁言时长
		writeLong(buf, this.times);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//服务器编号
		this.createServerId = readInt(buf);
		//用户id
		this.userId = readString(buf);
		//开始禁言时间
		this.startTime = readLong(buf);
		//禁言时长
		this.times = readLong(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 服务器编号
	 * @return 
	 */
	public int getCreateServerId(){
		return createServerId;
	}
	
	/**
	 * set 服务器编号
	 */
	public void setCreateServerId(int createServerId){
		this.createServerId = createServerId;
	}
	
	/**
	 * get 用户id
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 用户id
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * get 开始禁言时间
	 * @return 
	 */
	public long getStartTime(){
		return startTime;
	}
	
	/**
	 * set 开始禁言时间
	 */
	public void setStartTime(long startTime){
		this.startTime = startTime;
	}
	
	/**
	 * get 禁言时长
	 * @return 
	 */
	public long getTimes(){
		return times;
	}
	
	/**
	 * set 禁言时长
	 */
	public void setTimes(long times){
		this.times = times;
	}
	
	
	@Override
	public int getId() {
		return 135305;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//服务器编号
		buf.append("createServerId:" + createServerId +",");
		//用户id
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//开始禁言时间
		buf.append("startTime:" + startTime +",");
		//禁言时长
		buf.append("times:" + times +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
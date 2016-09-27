package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求公共服务器玩家结束跨服（公共游戏服务器到公共数据服务器）消息
 */
public class ReqPlayerQuitToDataServerMessage extends Message{

	//跨服服务器id
	private int serverId;
	
	//角色id
	private long dataServerPlayerId;
	
	//奖励数据
	private String reward;
	
	//1-胜利0-未胜利
	private int victory;
	
	//战斗力
	private int power;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//跨服服务器id
		writeInt(buf, this.serverId);
		//角色id
		writeLong(buf, this.dataServerPlayerId);
		//奖励数据
		writeString(buf, this.reward);
		//1-胜利0-未胜利
		writeInt(buf, this.victory);
		//战斗力
		writeInt(buf, this.power);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//跨服服务器id
		this.serverId = readInt(buf);
		//角色id
		this.dataServerPlayerId = readLong(buf);
		//奖励数据
		this.reward = readString(buf);
		//1-胜利0-未胜利
		this.victory = readInt(buf);
		//战斗力
		this.power = readInt(buf);
		return true;
	}
	
	/**
	 * get 跨服服务器id
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 跨服服务器id
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getDataServerPlayerId(){
		return dataServerPlayerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setDataServerPlayerId(long dataServerPlayerId){
		this.dataServerPlayerId = dataServerPlayerId;
	}
	
	/**
	 * get 奖励数据
	 * @return 
	 */
	public String getReward(){
		return reward;
	}
	
	/**
	 * set 奖励数据
	 */
	public void setReward(String reward){
		this.reward = reward;
	}
	
	/**
	 * get 1-胜利0-未胜利
	 * @return 
	 */
	public int getVictory(){
		return victory;
	}
	
	/**
	 * set 1-胜利0-未胜利
	 */
	public void setVictory(int victory){
		this.victory = victory;
	}
	
	/**
	 * get 战斗力
	 * @return 
	 */
	public int getPower(){
		return power;
	}
	
	/**
	 * set 战斗力
	 */
	public void setPower(int power){
		this.power = power;
	}
	
	
	@Override
	public int getId() {
		return 203308;
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
		//跨服服务器id
		buf.append("serverId:" + serverId +",");
		//角色id
		buf.append("dataServerPlayerId:" + dataServerPlayerId +",");
		//奖励数据
		if(this.reward!=null) buf.append("reward:" + reward.toString() +",");
		//1-胜利0-未胜利
		buf.append("victory:" + victory +",");
		//战斗力
		buf.append("power:" + power +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
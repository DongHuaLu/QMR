package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家龙元心法阶数消息
 */
public class ReqSyncPlayerLongyuanMessage extends Message{

	//角色id
	private long playerId;
	
	//角色龙元心法星图
	private int longyuanSection;
	
	//角色龙元心法阶数
	private int longyuanLevel;
	
	//角色龙元心法时间
	private long longyuanTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//角色龙元心法星图
		writeInt(buf, this.longyuanSection);
		//角色龙元心法阶数
		writeInt(buf, this.longyuanLevel);
		//角色龙元心法时间
		writeLong(buf, this.longyuanTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//角色龙元心法星图
		this.longyuanSection = readInt(buf);
		//角色龙元心法阶数
		this.longyuanLevel = readInt(buf);
		//角色龙元心法时间
		this.longyuanTime = readLong(buf);
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
	 * get 角色龙元心法星图
	 * @return 
	 */
	public int getLongyuanSection(){
		return longyuanSection;
	}
	
	/**
	 * set 角色龙元心法星图
	 */
	public void setLongyuanSection(int longyuanSection){
		this.longyuanSection = longyuanSection;
	}
	
	/**
	 * get 角色龙元心法阶数
	 * @return 
	 */
	public int getLongyuanLevel(){
		return longyuanLevel;
	}
	
	/**
	 * set 角色龙元心法阶数
	 */
	public void setLongyuanLevel(int longyuanLevel){
		this.longyuanLevel = longyuanLevel;
	}
	
	/**
	 * get 角色龙元心法时间
	 * @return 
	 */
	public long getLongyuanTime(){
		return longyuanTime;
	}
	
	/**
	 * set 角色龙元心法时间
	 */
	public void setLongyuanTime(long longyuanTime){
		this.longyuanTime = longyuanTime;
	}
	
	
	@Override
	public int getId() {
		return 103323;
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
		//角色龙元心法星图
		buf.append("longyuanSection:" + longyuanSection +",");
		//角色龙元心法阶数
		buf.append("longyuanLevel:" + longyuanLevel +",");
		//角色龙元心法时间
		buf.append("longyuanTime:" + longyuanTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
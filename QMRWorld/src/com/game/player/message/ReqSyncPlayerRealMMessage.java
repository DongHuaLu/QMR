package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家境界消息
 */
public class ReqSyncPlayerRealMMessage extends Message{

	//角色id
	private long playerId;
	
	//境界等级
	private int realmlevel;
	
	//境界强化等级
	private int realmintensifylevel;
	
	//角色境界阶数时间
	private long realmTime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//境界等级
		writeInt(buf, this.realmlevel);
		//境界强化等级
		writeInt(buf, this.realmintensifylevel);
		//角色境界阶数时间
		writeLong(buf, this.realmTime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//境界等级
		this.realmlevel = readInt(buf);
		//境界强化等级
		this.realmintensifylevel = readInt(buf);
		//角色境界阶数时间
		this.realmTime = readLong(buf);
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
	 * get 境界等级
	 * @return 
	 */
	public int getRealmlevel(){
		return realmlevel;
	}
	
	/**
	 * set 境界等级
	 */
	public void setRealmlevel(int realmlevel){
		this.realmlevel = realmlevel;
	}
	
	/**
	 * get 境界强化等级
	 * @return 
	 */
	public int getRealmintensifylevel(){
		return realmintensifylevel;
	}
	
	/**
	 * set 境界强化等级
	 */
	public void setRealmintensifylevel(int realmintensifylevel){
		this.realmintensifylevel = realmintensifylevel;
	}
	
	/**
	 * get 角色境界阶数时间
	 * @return 
	 */
	public long getRealmTime(){
		return realmTime;
	}
	
	/**
	 * set 角色境界阶数时间
	 */
	public void setRealmTime(long realmTime){
		this.realmTime = realmTime;
	}
	
	
	@Override
	public int getId() {
		return 103336;
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
		//境界等级
		buf.append("realmlevel:" + realmlevel +",");
		//境界强化等级
		buf.append("realmintensifylevel:" + realmintensifylevel +",");
		//角色境界阶数时间
		buf.append("realmTime:" + realmTime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
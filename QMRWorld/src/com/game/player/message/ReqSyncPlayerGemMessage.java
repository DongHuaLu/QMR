package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家宝石消息
 */
public class ReqSyncPlayerGemMessage extends Message{

	//角色id
	private long playerId;
	
	//宝石部位
	private byte site;
	
	//单件宝石
	private String gem;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//宝石部位
		writeByte(buf, this.site);
		//单件宝石
		writeString(buf, this.gem);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//宝石部位
		this.site = readByte(buf);
		//单件宝石
		this.gem = readString(buf);
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
	 * get 宝石部位
	 * @return 
	 */
	public byte getSite(){
		return site;
	}
	
	/**
	 * set 宝石部位
	 */
	public void setSite(byte site){
		this.site = site;
	}
	
	/**
	 * get 单件宝石
	 * @return 
	 */
	public String getGem(){
		return gem;
	}
	
	/**
	 * set 单件宝石
	 */
	public void setGem(String gem){
		this.gem = gem;
	}
	
	
	@Override
	public int getId() {
		return 103326;
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
		//宝石部位
		buf.append("site:" + site +",");
		//单件宝石
		if(this.gem!=null) buf.append("gem:" + gem.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.guild.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器玩家修改自动同意加入帮会设置消息
 */
public class ReqInnerGuildAutoArgeeAddGuildToWorldMessage extends Message{

	//角色Id
	private long playerId;
	
	//自动同意参数
	private byte autoArgeeAddGuild;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.playerId);
		//自动同意参数
		writeByte(buf, this.autoArgeeAddGuild);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.playerId = readLong(buf);
		//自动同意参数
		this.autoArgeeAddGuild = readByte(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 自动同意参数
	 * @return 
	 */
	public byte getAutoArgeeAddGuild(){
		return autoArgeeAddGuild;
	}
	
	/**
	 * set 自动同意参数
	 */
	public void setAutoArgeeAddGuild(byte autoArgeeAddGuild){
		this.autoArgeeAddGuild = autoArgeeAddGuild;
	}
	
	
	@Override
	public int getId() {
		return 121302;
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
		//角色Id
		buf.append("playerId:" + playerId +",");
		//自动同意参数
		buf.append("autoArgeeAddGuild:" + autoArgeeAddGuild +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
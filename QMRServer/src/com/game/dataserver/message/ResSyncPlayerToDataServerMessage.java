package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步公共服务器玩家全部信息返回消息
 */
public class ResSyncPlayerToDataServerMessage extends Message{

	//账号
	private String userId;
	
	//账号名字
	private String userName;
	
	//角色id
	private long playerId;
	
	//公共区角色id
	private long dataServerPlayerId;
	
	//返回结果 0-成功 1-跨服中...
	private int result;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//账号
		writeString(buf, this.userId);
		//账号名字
		writeString(buf, this.userName);
		//角色id
		writeLong(buf, this.playerId);
		//公共区角色id
		writeLong(buf, this.dataServerPlayerId);
		//返回结果 0-成功 1-跨服中...
		writeInt(buf, this.result);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//账号
		this.userId = readString(buf);
		//账号名字
		this.userName = readString(buf);
		//角色id
		this.playerId = readLong(buf);
		//公共区角色id
		this.dataServerPlayerId = readLong(buf);
		//返回结果 0-成功 1-跨服中...
		this.result = readInt(buf);
		return true;
	}
	
	/**
	 * get 账号
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 账号
	 */
	public void setUserId(String userId){
		this.userId = userId;
	}
	
	/**
	 * get 账号名字
	 * @return 
	 */
	public String getUserName(){
		return userName;
	}
	
	/**
	 * set 账号名字
	 */
	public void setUserName(String userName){
		this.userName = userName;
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
	 * get 公共区角色id
	 * @return 
	 */
	public long getDataServerPlayerId(){
		return dataServerPlayerId;
	}
	
	/**
	 * set 公共区角色id
	 */
	public void setDataServerPlayerId(long dataServerPlayerId){
		this.dataServerPlayerId = dataServerPlayerId;
	}
	
	/**
	 * get 返回结果 0-成功 1-跨服中...
	 * @return 
	 */
	public int getResult(){
		return result;
	}
	
	/**
	 * set 返回结果 0-成功 1-跨服中...
	 */
	public void setResult(int result){
		this.result = result;
	}
	
	
	@Override
	public int getId() {
		return 203302;
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
		//账号
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//账号名字
		if(this.userName!=null) buf.append("userName:" + userName.toString() +",");
		//角色id
		buf.append("playerId:" + playerId +",");
		//公共区角色id
		buf.append("dataServerPlayerId:" + dataServerPlayerId +",");
		//返回结果 0-成功 1-跨服中...
		buf.append("result:" + result +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
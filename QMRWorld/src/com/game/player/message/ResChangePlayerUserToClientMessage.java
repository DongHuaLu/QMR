package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家改帐号，完成后通知客户端消息
 */
public class ResChangePlayerUserToClientMessage extends Message{

	//角色ID
	private long playerId;
	
	//新帐号
	private String newname;
	
	//返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
	private byte result;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色ID
		writeLong(buf, this.playerId);
		//新帐号
		writeString(buf, this.newname);
		//返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
		writeByte(buf, this.result);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色ID
		this.playerId = readLong(buf);
		//新帐号
		this.newname = readString(buf);
		//返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
		this.result = readByte(buf);
		return true;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色ID
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 新帐号
	 * @return 
	 */
	public String getNewname(){
		return newname;
	}
	
	/**
	 * set 新帐号
	 */
	public void setNewname(String newname){
		this.newname = newname;
	}
	
	/**
	 * get 返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
	 * @return 
	 */
	public byte getResult(){
		return result;
	}
	
	/**
	 * set 返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
	 */
	public void setResult(byte result){
		this.result = result;
	}
	
	
	@Override
	public int getId() {
		return 103129;
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
		//角色ID
		buf.append("playerId:" + playerId +",");
		//新帐号
		if(this.newname!=null) buf.append("newname:" + newname.toString() +",");
		//返回结果，0：成功   1：用户存在   2：注册错误    3：用户名不符合规定     4：密码不符合规定    6：加密验证失效
		buf.append("result:" + result +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
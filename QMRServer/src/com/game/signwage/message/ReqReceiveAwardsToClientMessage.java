package com.game.signwage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 领取签到奖励消息
 */
public class ReqReceiveAwardsToClientMessage extends Message{

	//领取奖励的类型，奖励对应的签到次数
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//领取奖励的类型，奖励对应的签到次数
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//领取奖励的类型，奖励对应的签到次数
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 领取奖励的类型，奖励对应的签到次数
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 领取奖励的类型，奖励对应的签到次数
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 152203;
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
		//领取奖励的类型，奖励对应的签到次数
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
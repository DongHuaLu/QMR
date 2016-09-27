package com.game.fightpower.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求战斗力消息
 */
public class ReqFightPowerToServerMessage extends Message{

	//总战斗力
	private int fightPower;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//总战斗力
		writeInt(buf, this.fightPower);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//总战斗力
		this.fightPower = readInt(buf);
		return true;
	}
	
	/**
	 * get 总战斗力
	 * @return 
	 */
	public int getFightPower(){
		return fightPower;
	}
	
	/**
	 * set 总战斗力
	 */
	public void setFightPower(int fightPower){
		this.fightPower = fightPower;
	}
	
	
	@Override
	public int getId() {
		return 127101;
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
		//总战斗力
		buf.append("fightPower:" + fightPower +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
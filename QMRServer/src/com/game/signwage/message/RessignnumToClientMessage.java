package com.game.signwage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送累计签到次数消息
 */
public class RessignnumToClientMessage extends Message{

	//累计签到次数
	private int signnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//累计签到次数
		writeInt(buf, this.signnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//累计签到次数
		this.signnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 累计签到次数
	 * @return 
	 */
	public int getSignnum(){
		return signnum;
	}
	
	/**
	 * set 累计签到次数
	 */
	public void setSignnum(int signnum){
		this.signnum = signnum;
	}
	
	
	@Override
	public int getId() {
		return 152104;
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
		//累计签到次数
		buf.append("signnum:" + signnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
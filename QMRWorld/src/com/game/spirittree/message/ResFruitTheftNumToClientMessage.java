package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 灵树抢摘次数信息消息
 */
public class ResFruitTheftNumToClientMessage extends Message{

	//抢摘次数
	private int theftnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//抢摘次数
		writeInt(buf, this.theftnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//抢摘次数
		this.theftnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 抢摘次数
	 * @return 
	 */
	public int getTheftnum(){
		return theftnum;
	}
	
	/**
	 * set 抢摘次数
	 */
	public void setTheftnum(int theftnum){
		this.theftnum = theftnum;
	}
	
	
	@Override
	public int getId() {
		return 133109;
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
		//抢摘次数
		buf.append("theftnum:" + theftnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
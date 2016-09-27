package com.game.gem.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 点击宝石激活或者升级消息
 */
public class ReqGemActivationORUpMessage extends Message{

	//装备部位
	private byte pos;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备部位
		writeByte(buf, this.pos);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//装备部位
		this.pos = readByte(buf);
		return true;
	}
	
	/**
	 * get 装备部位
	 * @return 
	 */
	public byte getPos(){
		return pos;
	}
	
	/**
	 * set 装备部位
	 */
	public void setPos(byte pos){
		this.pos = pos;
	}
	
	
	@Override
	public int getId() {
		return 132203;
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
		//装备部位
		buf.append("pos:" + pos +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
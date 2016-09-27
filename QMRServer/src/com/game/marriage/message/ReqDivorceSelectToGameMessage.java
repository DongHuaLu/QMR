package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 协议离婚选择消息
 */
public class ReqDivorceSelectToGameMessage extends Message{

	//对协议离婚做选择，1同意，0拒绝
	private byte select;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//对协议离婚做选择，1同意，0拒绝
		writeByte(buf, this.select);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//对协议离婚做选择，1同意，0拒绝
		this.select = readByte(buf);
		return true;
	}
	
	/**
	 * get 对协议离婚做选择，1同意，0拒绝
	 * @return 
	 */
	public byte getSelect(){
		return select;
	}
	
	/**
	 * set 对协议离婚做选择，1同意，0拒绝
	 */
	public void setSelect(byte select){
		this.select = select;
	}
	
	
	@Override
	public int getId() {
		return 163208;
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
		//对协议离婚做选择，1同意，0拒绝
		buf.append("select:" + select +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
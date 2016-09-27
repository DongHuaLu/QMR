package com.game.card.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求签证CDKEY消息
 */
public class ReqCardToServerMessage extends Message{

	//CDKEY
	private String card;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//CDKEY
		writeString(buf, this.card);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//CDKEY
		this.card = readString(buf);
		return true;
	}
	
	/**
	 * get CDKEY
	 * @return 
	 */
	public String getCard(){
		return card;
	}
	
	/**
	 * set CDKEY
	 */
	public void setCard(String card){
		this.card = card;
	}
	
	
	@Override
	public int getId() {
		return 137101;
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
		//CDKEY
		if(this.card!=null) buf.append("card:" + card.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
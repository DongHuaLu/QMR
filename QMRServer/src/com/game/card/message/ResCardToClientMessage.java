package com.game.card.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求签证CDKEY返回消息
 */
public class ResCardToClientMessage extends Message{

	//错误代码
	private byte errorcode;
	
	//平台id
	private int agid;
	
	//礼包类型
	private int type;
	
	//礼包编号
	private int giftid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.errorcode);
		//平台id
		writeInt(buf, this.agid);
		//礼包类型
		writeInt(buf, this.type);
		//礼包编号
		writeInt(buf, this.giftid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.errorcode = readByte(buf);
		//平台id
		this.agid = readInt(buf);
		//礼包类型
		this.type = readInt(buf);
		//礼包编号
		this.giftid = readInt(buf);
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getErrorcode(){
		return errorcode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setErrorcode(byte errorcode){
		this.errorcode = errorcode;
	}
	
	/**
	 * get 平台id
	 * @return 
	 */
	public int getAgid(){
		return agid;
	}
	
	/**
	 * set 平台id
	 */
	public void setAgid(int agid){
		this.agid = agid;
	}
	
	/**
	 * get 礼包类型
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 礼包类型
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 礼包编号
	 * @return 
	 */
	public int getGiftid(){
		return giftid;
	}
	
	/**
	 * set 礼包编号
	 */
	public void setGiftid(int giftid){
		this.giftid = giftid;
	}
	
	
	@Override
	public int getId() {
		return 137151;
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
		//错误代码
		buf.append("errorcode:" + errorcode +",");
		//平台id
		buf.append("agid:" + agid +",");
		//礼包类型
		buf.append("type:" + type +",");
		//礼包编号
		buf.append("giftid:" + giftid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
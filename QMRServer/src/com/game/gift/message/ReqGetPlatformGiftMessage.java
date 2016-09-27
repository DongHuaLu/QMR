package com.game.gift.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求领取平台礼包消息
 */
public class ReqGetPlatformGiftMessage extends Message{

	//平台名称
	private String platform;
	
	//礼包id
	private int giftid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//平台名称
		writeString(buf, this.platform);
		//礼包id
		writeInt(buf, this.giftid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//平台名称
		this.platform = readString(buf);
		//礼包id
		this.giftid = readInt(buf);
		return true;
	}
	
	/**
	 * get 平台名称
	 * @return 
	 */
	public String getPlatform(){
		return platform;
	}
	
	/**
	 * set 平台名称
	 */
	public void setPlatform(String platform){
		this.platform = platform;
	}
	
	/**
	 * get 礼包id
	 * @return 
	 */
	public int getGiftid(){
		return giftid;
	}
	
	/**
	 * set 礼包id
	 */
	public void setGiftid(int giftid){
		this.giftid = giftid;
	}
	
	
	@Override
	public int getId() {
		return 129203;
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
		//平台名称
		if(this.platform!=null) buf.append("platform:" + platform.toString() +",");
		//礼包id
		buf.append("giftid:" + giftid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
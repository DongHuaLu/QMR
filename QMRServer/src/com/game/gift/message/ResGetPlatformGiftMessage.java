package com.game.gift.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送领取结果消息
 */
public class ResGetPlatformGiftMessage extends Message{

	//礼包id
	private int giftid;
	
	//礼包领取结果 0-成功  1-失败
	private int result;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//礼包id
		writeInt(buf, this.giftid);
		//礼包领取结果 0-成功  1-失败
		writeInt(buf, this.result);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//礼包id
		this.giftid = readInt(buf);
		//礼包领取结果 0-成功  1-失败
		this.result = readInt(buf);
		return true;
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
	
	/**
	 * get 礼包领取结果 0-成功  1-失败
	 * @return 
	 */
	public int getResult(){
		return result;
	}
	
	/**
	 * set 礼包领取结果 0-成功  1-失败
	 */
	public void setResult(int result){
		this.result = result;
	}
	
	
	@Override
	public int getId() {
		return 129104;
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
		//礼包id
		buf.append("giftid:" + giftid +",");
		//礼包领取结果 0-成功  1-失败
		buf.append("result:" + result +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
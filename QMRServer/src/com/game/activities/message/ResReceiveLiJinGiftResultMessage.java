package com.game.activities.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 封测礼金领取结果消息
 */
public class ResReceiveLiJinGiftResultMessage extends Message{

	//下次领取所需时间
	private int nextReceiveNeedTime;
	
	//领取结果 1成功 0失败
	private byte result;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//下次领取所需时间
		writeInt(buf, this.nextReceiveNeedTime);
		//领取结果 1成功 0失败
		writeByte(buf, this.result);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//下次领取所需时间
		this.nextReceiveNeedTime = readInt(buf);
		//领取结果 1成功 0失败
		this.result = readByte(buf);
		return true;
	}
	
	/**
	 * get 下次领取所需时间
	 * @return 
	 */
	public int getNextReceiveNeedTime(){
		return nextReceiveNeedTime;
	}
	
	/**
	 * set 下次领取所需时间
	 */
	public void setNextReceiveNeedTime(int nextReceiveNeedTime){
		this.nextReceiveNeedTime = nextReceiveNeedTime;
	}
	
	/**
	 * get 领取结果 1成功 0失败
	 * @return 
	 */
	public byte getResult(){
		return result;
	}
	
	/**
	 * set 领取结果 1成功 0失败
	 */
	public void setResult(byte result){
		this.result = result;
	}
	
	
	@Override
	public int getId() {
		return 138102;
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
		//下次领取所需时间
		buf.append("nextReceiveNeedTime:" + nextReceiveNeedTime +",");
		//领取结果 1成功 0失败
		buf.append("result:" + result +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
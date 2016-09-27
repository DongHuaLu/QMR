package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 错误消息消息
 */
public class ResTransactionsErrorMessage extends Message{

	//错误id（1放入道具异常，2金币量超出，3元宝量超出）
	private int errorid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误id（1放入道具异常，2金币量超出，3元宝量超出）
		writeInt(buf, this.errorid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误id（1放入道具异常，2金币量超出，3元宝量超出）
		this.errorid = readInt(buf);
		return true;
	}
	
	/**
	 * get 错误id（1放入道具异常，2金币量超出，3元宝量超出）
	 * @return 
	 */
	public int getErrorid(){
		return errorid;
	}
	
	/**
	 * set 错误id（1放入道具异常，2金币量超出，3元宝量超出）
	 */
	public void setErrorid(int errorid){
		this.errorid = errorid;
	}
	
	
	@Override
	public int getId() {
		return 122111;
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
		//错误id（1放入道具异常，2金币量超出，3元宝量超出）
		buf.append("errorid:" + errorid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获取配偶其他资料消息
 */
public class ReqGetSpouseOtherToGameMessage extends Message{

	//类型：1获取配偶龙元心法，2获取配偶武功
	private int type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//类型：1获取配偶龙元心法，2获取配偶武功
		writeInt(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//类型：1获取配偶龙元心法，2获取配偶武功
		this.type = readInt(buf);
		return true;
	}
	
	/**
	 * get 类型：1获取配偶龙元心法，2获取配偶武功
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 类型：1获取配偶龙元心法，2获取配偶武功
	 */
	public void setType(int type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 163217;
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
		//类型：1获取配偶龙元心法，2获取配偶武功
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
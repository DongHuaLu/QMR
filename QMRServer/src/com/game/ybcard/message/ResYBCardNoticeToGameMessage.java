package com.game.ybcard.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知GAME服务器，元宝已经到达上限消息
 */
public class ResYBCardNoticeToGameMessage extends Message{

	//当前领取的元宝总量
	private int yuanbaonum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前领取的元宝总量
		writeInt(buf, this.yuanbaonum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前领取的元宝总量
		this.yuanbaonum = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前领取的元宝总量
	 * @return 
	 */
	public int getYuanbaonum(){
		return yuanbaonum;
	}
	
	/**
	 * set 当前领取的元宝总量
	 */
	public void setYuanbaonum(int yuanbaonum){
		this.yuanbaonum = yuanbaonum;
	}
	
	
	@Override
	public int getId() {
		return 139303;
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
		//当前领取的元宝总量
		buf.append("yuanbaonum:" + yuanbaonum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
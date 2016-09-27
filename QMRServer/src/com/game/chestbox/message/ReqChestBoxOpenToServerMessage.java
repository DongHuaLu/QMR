package com.game.chestbox.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 客户端请求开始转动宝箱消息
 */
public class ReqChestBoxOpenToServerMessage extends Message{

	//动作类型
	private int actiontype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//动作类型
		writeInt(buf, this.actiontype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//动作类型
		this.actiontype = readInt(buf);
		return true;
	}
	
	/**
	 * get 动作类型
	 * @return 
	 */
	public int getActiontype(){
		return actiontype;
	}
	
	/**
	 * set 动作类型
	 */
	public void setActiontype(int actiontype){
		this.actiontype = actiontype;
	}
	
	
	@Override
	public int getId() {
		return 156202;
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
		//动作类型
		buf.append("actiontype:" + actiontype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
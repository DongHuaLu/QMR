package com.game.biwudao.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 选择是否参加比武岛消息
 */
public class ReqBiWuDaoSelectToGameMessage extends Message{

	//0进入，1离开
	private int type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0进入，1离开
		writeInt(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0进入，1离开
		this.type = readInt(buf);
		return true;
	}
	
	/**
	 * get 0进入，1离开
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 0进入，1离开
	 */
	public void setType(int type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 157201;
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
		//0进入，1离开
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.npc.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求服务器npc服务列表消息
 */
public class ReqNpcServicesMessage extends Message{

	//npcId
	private long npcId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//npcId
		writeLong(buf, this.npcId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//npcId
		this.npcId = readLong(buf);
		return true;
	}
	
	/**
	 * get npcId
	 * @return 
	 */
	public long getNpcId(){
		return npcId;
	}
	
	/**
	 * set npcId
	 */
	public void setNpcId(long npcId){
		this.npcId = npcId;
	}
	
	
	@Override
	public int getId() {
		return 140201;
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
		//npcId
		buf.append("npcId:" + npcId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 开始吃菜消息
 */
public class ReqWeddingEatingToGameMessage extends Message{

	//结婚id
	private long marriageid;
	
	//NPC唯一id
	private long npcid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//结婚id
		writeLong(buf, this.marriageid);
		//NPC唯一id
		writeLong(buf, this.npcid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//结婚id
		this.marriageid = readLong(buf);
		//NPC唯一id
		this.npcid = readLong(buf);
		return true;
	}
	
	/**
	 * get 结婚id
	 * @return 
	 */
	public long getMarriageid(){
		return marriageid;
	}
	
	/**
	 * set 结婚id
	 */
	public void setMarriageid(long marriageid){
		this.marriageid = marriageid;
	}
	
	/**
	 * get NPC唯一id
	 * @return 
	 */
	public long getNpcid(){
		return npcid;
	}
	
	/**
	 * set NPC唯一id
	 */
	public void setNpcid(long npcid){
		this.npcid = npcid;
	}
	
	
	@Override
	public int getId() {
		return 163213;
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
		//结婚id
		buf.append("marriageid:" + marriageid +",");
		//NPC唯一id
		buf.append("npcid:" + npcid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 送上红包，参加婚宴消息
 */
public class ReqWeddingParticipateToGameMessage extends Message{

	//红包数量
	private int num;
	
	//结婚id
	private long marriageid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//红包数量
		writeInt(buf, this.num);
		//结婚id
		writeLong(buf, this.marriageid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//红包数量
		this.num = readInt(buf);
		//结婚id
		this.marriageid = readLong(buf);
		return true;
	}
	
	/**
	 * get 红包数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 红包数量
	 */
	public void setNum(int num){
		this.num = num;
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
	
	
	@Override
	public int getId() {
		return 163212;
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
		//红包数量
		buf.append("num:" + num +",");
		//结婚id
		buf.append("marriageid:" + marriageid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
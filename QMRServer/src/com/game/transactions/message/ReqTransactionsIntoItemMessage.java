package com.game.transactions.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 往交易框放入道具消息
 */
public class ReqTransactionsIntoItemMessage extends Message{

	//放入交易框的位置
	private short itemposition;
	
	//道具唯一ID
	private long itemid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//放入交易框的位置
		writeShort(buf, this.itemposition);
		//道具唯一ID
		writeLong(buf, this.itemid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//放入交易框的位置
		this.itemposition = readShort(buf);
		//道具唯一ID
		this.itemid = readLong(buf);
		return true;
	}
	
	/**
	 * get 放入交易框的位置
	 * @return 
	 */
	public short getItemposition(){
		return itemposition;
	}
	
	/**
	 * set 放入交易框的位置
	 */
	public void setItemposition(short itemposition){
		this.itemposition = itemposition;
	}
	
	/**
	 * get 道具唯一ID
	 * @return 
	 */
	public long getItemid(){
		return itemid;
	}
	
	/**
	 * set 道具唯一ID
	 */
	public void setItemid(long itemid){
		this.itemid = itemid;
	}
	
	
	@Override
	public int getId() {
		return 122205;
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
		//放入交易框的位置
		buf.append("itemposition:" + itemposition +",");
		//道具唯一ID
		buf.append("itemid:" + itemid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打开格子所需时间消息
 */
public class ResCellTimeMessage extends Message{

	//格子编号
	private int cellId;
	
	//剩余时间(秒)
	private int timeRemaining;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//格子编号
		writeInt(buf, this.cellId);
		//剩余时间(秒)
		writeInt(buf, this.timeRemaining);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//格子编号
		this.cellId = readInt(buf);
		//剩余时间(秒)
		this.timeRemaining = readInt(buf);
		return true;
	}
	
	/**
	 * get 格子编号
	 * @return 
	 */
	public int getCellId(){
		return cellId;
	}
	
	/**
	 * set 格子编号
	 */
	public void setCellId(int cellId){
		this.cellId = cellId;
	}
	
	/**
	 * get 剩余时间(秒)
	 * @return 
	 */
	public int getTimeRemaining(){
		return timeRemaining;
	}
	
	/**
	 * set 剩余时间(秒)
	 */
	public void setTimeRemaining(int timeRemaining){
		this.timeRemaining = timeRemaining;
	}
	
	
	@Override
	public int getId() {
		return 104109;
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
		//格子编号
		buf.append("cellId:" + cellId +",");
		//剩余时间(秒)
		buf.append("timeRemaining:" + timeRemaining +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
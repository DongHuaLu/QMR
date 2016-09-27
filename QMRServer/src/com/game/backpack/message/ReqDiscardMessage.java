package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 物品丢弃消息
 */
public class ReqDiscardMessage extends Message{

	//格子编号
	private int cellId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//格子编号
		writeInt(buf, this.cellId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//格子编号
		this.cellId = readInt(buf);
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
	
	
	@Override
	public int getId() {
		return 104209;
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
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
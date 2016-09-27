package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打开格子结果消息
 */
public class ResOpenCellResultMessage extends Message{

	//格子编号
	private int cellId;
	
	//1成功 0失败
	private int isSuccess;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//格子编号
		writeInt(buf, this.cellId);
		//1成功 0失败
		writeInt(buf, this.isSuccess);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//格子编号
		this.cellId = readInt(buf);
		//1成功 0失败
		this.isSuccess = readInt(buf);
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
	 * get 1成功 0失败
	 * @return 
	 */
	public int getIsSuccess(){
		return isSuccess;
	}
	
	/**
	 * set 1成功 0失败
	 */
	public void setIsSuccess(int isSuccess){
		this.isSuccess = isSuccess;
	}
	
	
	@Override
	public int getId() {
		return 104110;
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
		//1成功 0失败
		buf.append("isSuccess:" + isSuccess +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
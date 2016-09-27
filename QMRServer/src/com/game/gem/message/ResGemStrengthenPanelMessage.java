package com.game.gem.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 弹出强化石面板消息
 */
public class ResGemStrengthenPanelMessage extends Message{

	//宝石强化符模型ID
	private int itemmodelid;
	
	//宝石强化符唯一ID
	private long itemid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宝石强化符模型ID
		writeInt(buf, this.itemmodelid);
		//宝石强化符唯一ID
		writeLong(buf, this.itemid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宝石强化符模型ID
		this.itemmodelid = readInt(buf);
		//宝石强化符唯一ID
		this.itemid = readLong(buf);
		return true;
	}
	
	/**
	 * get 宝石强化符模型ID
	 * @return 
	 */
	public int getItemmodelid(){
		return itemmodelid;
	}
	
	/**
	 * set 宝石强化符模型ID
	 */
	public void setItemmodelid(int itemmodelid){
		this.itemmodelid = itemmodelid;
	}
	
	/**
	 * get 宝石强化符唯一ID
	 * @return 
	 */
	public long getItemid(){
		return itemid;
	}
	
	/**
	 * set 宝石强化符唯一ID
	 */
	public void setItemid(long itemid){
		this.itemid = itemid;
	}
	
	
	@Override
	public int getId() {
		return 132106;
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
		//宝石强化符模型ID
		buf.append("itemmodelid:" + itemmodelid +",");
		//宝石强化符唯一ID
		buf.append("itemid:" + itemid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
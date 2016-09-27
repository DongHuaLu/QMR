package com.game.store.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 花元宝打开格子申请消息
 */
public class ReqStoreOpenCellMessage extends Message{

	//格子编号
	private int cellId;
	
	//关联NPC
	private int npcid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//格子编号
		writeInt(buf, this.cellId);
		//关联NPC
		writeInt(buf, this.npcid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//格子编号
		this.cellId = readInt(buf);
		//关联NPC
		this.npcid = readInt(buf);
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
	 * get 关联NPC
	 * @return 
	 */
	public int getNpcid(){
		return npcid;
	}
	
	/**
	 * set 关联NPC
	 */
	public void setNpcid(int npcid){
		this.npcid = npcid;
	}
	
	
	@Override
	public int getId() {
		return 112203;
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
		//关联NPC
		buf.append("npcid:" + npcid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
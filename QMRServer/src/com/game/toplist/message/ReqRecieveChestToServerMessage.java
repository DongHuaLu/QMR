package com.game.toplist.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 领取宝箱消息
 */
public class ReqRecieveChestToServerMessage extends Message{

	//宝箱id
	private int chestid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宝箱id
		writeInt(buf, this.chestid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宝箱id
		this.chestid = readInt(buf);
		return true;
	}
	
	/**
	 * get 宝箱id
	 * @return 
	 */
	public int getChestid(){
		return chestid;
	}
	
	/**
	 * set 宝箱id
	 */
	public void setChestid(int chestid){
		this.chestid = chestid;
	}
	
	
	@Override
	public int getId() {
		return 142204;
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
		//宝箱id
		buf.append("chestid:" + chestid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
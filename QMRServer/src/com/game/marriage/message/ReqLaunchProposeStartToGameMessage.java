package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家发起求婚（开始）消息
 */
public class ReqLaunchProposeStartToGameMessage extends Message{

	//戒指模版ID
	private int ringmodelid;
	
	//求婚对象ID
	private long suitorobjid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//戒指模版ID
		writeInt(buf, this.ringmodelid);
		//求婚对象ID
		writeLong(buf, this.suitorobjid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//戒指模版ID
		this.ringmodelid = readInt(buf);
		//求婚对象ID
		this.suitorobjid = readLong(buf);
		return true;
	}
	
	/**
	 * get 戒指模版ID
	 * @return 
	 */
	public int getRingmodelid(){
		return ringmodelid;
	}
	
	/**
	 * set 戒指模版ID
	 */
	public void setRingmodelid(int ringmodelid){
		this.ringmodelid = ringmodelid;
	}
	
	/**
	 * get 求婚对象ID
	 * @return 
	 */
	public long getSuitorobjid(){
		return suitorobjid;
	}
	
	/**
	 * set 求婚对象ID
	 */
	public void setSuitorobjid(long suitorobjid){
		this.suitorobjid = suitorobjid;
	}
	
	
	@Override
	public int getId() {
		return 163202;
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
		//戒指模版ID
		buf.append("ringmodelid:" + ringmodelid +",");
		//求婚对象ID
		buf.append("suitorobjid:" + suitorobjid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
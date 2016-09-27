package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 更换婚戒消息
 */
public class ReqChangeRingToGameMessage extends Message{

	//戒指模版ID
	private int ringmodelid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//戒指模版ID
		writeInt(buf, this.ringmodelid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//戒指模版ID
		this.ringmodelid = readInt(buf);
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
	
	
	@Override
	public int getId() {
		return 163220;
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
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
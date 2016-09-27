package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 删除留言消息
 */
public class ReqDeleteLeaveMsgToGameMessage extends Message{

	//当前留言索引ID
	private long msgid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前留言索引ID
		writeLong(buf, this.msgid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前留言索引ID
		this.msgid = readLong(buf);
		return true;
	}
	
	/**
	 * get 当前留言索引ID
	 * @return 
	 */
	public long getMsgid(){
		return msgid;
	}
	
	/**
	 * set 当前留言索引ID
	 */
	public void setMsgid(long msgid){
		this.msgid = msgid;
	}
	
	
	@Override
	public int getId() {
		return 163219;
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
		//当前留言索引ID
		buf.append("msgid:" + msgid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
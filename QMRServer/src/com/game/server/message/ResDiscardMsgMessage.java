package com.game.server.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 被丢弃的消息消息
 */
public class ResDiscardMsgMessage extends Message{

	//消息内容
	private String msgcont;
	
	//消息ID
	private int msgid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消息内容
		writeString(buf, this.msgcont);
		//消息ID
		writeInt(buf, this.msgid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消息内容
		this.msgcont = readString(buf);
		//消息ID
		this.msgid = readInt(buf);
		return true;
	}
	
	/**
	 * get 消息内容
	 * @return 
	 */
	public String getMsgcont(){
		return msgcont;
	}
	
	/**
	 * set 消息内容
	 */
	public void setMsgcont(String msgcont){
		this.msgcont = msgcont;
	}
	
	/**
	 * get 消息ID
	 * @return 
	 */
	public int getMsgid(){
		return msgid;
	}
	
	/**
	 * set 消息ID
	 */
	public void setMsgid(int msgid){
		this.msgid = msgid;
	}
	
	
	@Override
	public int getId() {
		return 300102;
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
		//消息内容
		if(this.msgcont!=null) buf.append("msgcont:" + msgcont.toString() +",");
		//消息ID
		buf.append("msgid:" + msgid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
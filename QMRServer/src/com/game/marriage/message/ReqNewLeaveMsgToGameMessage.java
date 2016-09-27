package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 增加新留言消息
 */
public class ReqNewLeaveMsgToGameMessage extends Message{

	//留言内容
	private String content;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//留言内容
		writeString(buf, this.content);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//留言内容
		this.content = readString(buf);
		return true;
	}
	
	/**
	 * get 留言内容
	 * @return 
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * set 留言内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	
	@Override
	public int getId() {
		return 163205;
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
		//留言内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
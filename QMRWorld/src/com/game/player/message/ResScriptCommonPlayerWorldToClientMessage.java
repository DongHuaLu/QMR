package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 世界服务器发起脚本通用类消息消息
 */
public class ResScriptCommonPlayerWorldToClientMessage extends Message{

	//脚本编号
	private int scriptid;
	
	//脚本消息类型
	private int type;
	
	//脚本消息数据(json字符串)
	private String messageData;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//脚本编号
		writeInt(buf, this.scriptid);
		//脚本消息类型
		writeInt(buf, this.type);
		//脚本消息数据(json字符串)
		writeString(buf, this.messageData);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//脚本编号
		this.scriptid = readInt(buf);
		//脚本消息类型
		this.type = readInt(buf);
		//脚本消息数据(json字符串)
		this.messageData = readString(buf);
		return true;
	}
	
	/**
	 * get 脚本编号
	 * @return 
	 */
	public int getScriptid(){
		return scriptid;
	}
	
	/**
	 * set 脚本编号
	 */
	public void setScriptid(int scriptid){
		this.scriptid = scriptid;
	}
	
	/**
	 * get 脚本消息类型
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 脚本消息类型
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 脚本消息数据(json字符串)
	 * @return 
	 */
	public String getMessageData(){
		return messageData;
	}
	
	/**
	 * set 脚本消息数据(json字符串)
	 */
	public void setMessageData(String messageData){
		this.messageData = messageData;
	}
	
	
	@Override
	public int getId() {
		return 103131;
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
		//脚本编号
		buf.append("scriptid:" + scriptid +",");
		//脚本消息类型
		buf.append("type:" + type +",");
		//脚本消息数据(json字符串)
		if(this.messageData!=null) buf.append("messageData:" + messageData.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
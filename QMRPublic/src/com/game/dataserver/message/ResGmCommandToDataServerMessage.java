package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * GM指令消息
 */
public class ResGmCommandToDataServerMessage extends Message{

	//GM指令字符串
	private String command;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//GM指令字符串
		writeString(buf, this.command);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//GM指令字符串
		this.command = readString(buf);
		return true;
	}
	
	/**
	 * get GM指令字符串
	 * @return 
	 */
	public String getCommand(){
		return command;
	}
	
	/**
	 * set GM指令字符串
	 */
	public void setCommand(String command){
		this.command = command;
	}
	
	
	@Override
	public int getId() {
		return 203330;
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
		//GM指令字符串
		if(this.command!=null) buf.append("command:" + command.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
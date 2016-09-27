package com.game.gm.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知SERVER服GM指令消息
 */
public class GmCommandToServerMessage extends Message{

	//事务Id
	private String action;
	
	//Gm指令
	private String command;
	
	//执行回执接口
	private String httpresult;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//事务Id
		writeString(buf, this.action);
		//Gm指令
		writeString(buf, this.command);
		//执行回执接口
		writeString(buf, this.httpresult);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//事务Id
		this.action = readString(buf);
		//Gm指令
		this.command = readString(buf);
		//执行回执接口
		this.httpresult = readString(buf);
		return true;
	}
	
	/**
	 * get 事务Id
	 * @return 
	 */
	public String getAction(){
		return action;
	}
	
	/**
	 * set 事务Id
	 */
	public void setAction(String action){
		this.action = action;
	}
	
	/**
	 * get Gm指令
	 * @return 
	 */
	public String getCommand(){
		return command;
	}
	
	/**
	 * set Gm指令
	 */
	public void setCommand(String command){
		this.command = command;
	}
	
	/**
	 * get 执行回执接口
	 * @return 
	 */
	public String getHttpresult(){
		return httpresult;
	}
	
	/**
	 * set 执行回执接口
	 */
	public void setHttpresult(String httpresult){
		this.httpresult = httpresult;
	}
	
	
	@Override
	public int getId() {
		return 200303;
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
		//事务Id
		if(this.action!=null) buf.append("action:" + action.toString() +",");
		//Gm指令
		if(this.command!=null) buf.append("command:" + command.toString() +",");
		//执行回执接口
		if(this.httpresult!=null) buf.append("httpresult:" + httpresult.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
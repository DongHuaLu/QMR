package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 自动接受入队申请消息
 */
public class ReqAutoIntoTeamApplyGameMessage extends Message{

	//0手动，1自动接受
	private byte autointoteamapply;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0手动，1自动接受
		writeByte(buf, this.autointoteamapply);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0手动，1自动接受
		this.autointoteamapply = readByte(buf);
		return true;
	}
	
	/**
	 * get 0手动，1自动接受
	 * @return 
	 */
	public byte getAutointoteamapply(){
		return autointoteamapply;
	}
	
	/**
	 * set 0手动，1自动接受
	 */
	public void setAutointoteamapply(byte autointoteamapply){
		this.autointoteamapply = autointoteamapply;
	}
	
	
	@Override
	public int getId() {
		return 118210;
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
		//0手动，1自动接受
		buf.append("autointoteamapply:" + autointoteamapply +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 把组队设置发送给前端消息
 */
public class ResTeamSetToClientMessage extends Message{

	//自动接受入队申请 ，0手动 1自动申请
	private byte autoIntoteamapply;
	
	//自动接受组队邀请，0手动 1自动邀请
	private byte autoTeaminvited;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//自动接受入队申请 ，0手动 1自动申请
		writeByte(buf, this.autoIntoteamapply);
		//自动接受组队邀请，0手动 1自动邀请
		writeByte(buf, this.autoTeaminvited);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//自动接受入队申请 ，0手动 1自动申请
		this.autoIntoteamapply = readByte(buf);
		//自动接受组队邀请，0手动 1自动邀请
		this.autoTeaminvited = readByte(buf);
		return true;
	}
	
	/**
	 * get 自动接受入队申请 ，0手动 1自动申请
	 * @return 
	 */
	public byte getAutoIntoteamapply(){
		return autoIntoteamapply;
	}
	
	/**
	 * set 自动接受入队申请 ，0手动 1自动申请
	 */
	public void setAutoIntoteamapply(byte autoIntoteamapply){
		this.autoIntoteamapply = autoIntoteamapply;
	}
	
	/**
	 * get 自动接受组队邀请，0手动 1自动邀请
	 * @return 
	 */
	public byte getAutoTeaminvited(){
		return autoTeaminvited;
	}
	
	/**
	 * set 自动接受组队邀请，0手动 1自动邀请
	 */
	public void setAutoTeaminvited(byte autoTeaminvited){
		this.autoTeaminvited = autoTeaminvited;
	}
	
	
	@Override
	public int getId() {
		return 118113;
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
		//自动接受入队申请 ，0手动 1自动申请
		buf.append("autoIntoteamapply:" + autoIntoteamapply +",");
		//自动接受组队邀请，0手动 1自动邀请
		buf.append("autoTeaminvited:" + autoTeaminvited +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
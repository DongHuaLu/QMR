package com.game.protect.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 修改密码消息
 */
public class ReqModifyPasswordToGameMessage extends Message{

	//新密码
	private String newpassword;
	
	//原密码
	private String oldpassword;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//新密码
		writeString(buf, this.newpassword);
		//原密码
		writeString(buf, this.oldpassword);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//新密码
		this.newpassword = readString(buf);
		//原密码
		this.oldpassword = readString(buf);
		return true;
	}
	
	/**
	 * get 新密码
	 * @return 
	 */
	public String getNewpassword(){
		return newpassword;
	}
	
	/**
	 * set 新密码
	 */
	public void setNewpassword(String newpassword){
		this.newpassword = newpassword;
	}
	
	/**
	 * get 原密码
	 * @return 
	 */
	public String getOldpassword(){
		return oldpassword;
	}
	
	/**
	 * set 原密码
	 */
	public void setOldpassword(String oldpassword){
		this.oldpassword = oldpassword;
	}
	
	
	@Override
	public int getId() {
		return 164205;
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
		//新密码
		if(this.newpassword!=null) buf.append("newpassword:" + newpassword.toString() +",");
		//原密码
		if(this.oldpassword!=null) buf.append("oldpassword:" + oldpassword.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
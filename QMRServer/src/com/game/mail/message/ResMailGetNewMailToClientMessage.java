package com.game.mail.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获得新邮件提示消息
 */
public class ResMailGetNewMailToClientMessage extends Message{

	//新邮件数量
	private int nCount;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//新邮件数量
		writeInt(buf, this.nCount);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//新邮件数量
		this.nCount = readInt(buf);
		return true;
	}
	
	/**
	 * get 新邮件数量
	 * @return 
	 */
	public int getNCount(){
		return nCount;
	}
	
	/**
	 * set 新邮件数量
	 */
	public void setNCount(int nCount){
		this.nCount = nCount;
	}
	
	
	@Override
	public int getId() {
		return 124110;
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
		//新邮件数量
		buf.append("nCount:" + nCount +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
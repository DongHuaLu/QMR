package com.game.setupmenu.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 系统设置设定状态消息
 */
public class ReqSetMenuStatusMessage extends Message{

	//客户端设定值
	private int clientset;
	
	//服务端设定值
	private int menustatus;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//客户端设定值
		writeInt(buf, this.clientset);
		//服务端设定值
		writeInt(buf, this.menustatus);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//客户端设定值
		this.clientset = readInt(buf);
		//服务端设定值
		this.menustatus = readInt(buf);
		return true;
	}
	
	/**
	 * get 客户端设定值
	 * @return 
	 */
	public int getClientset(){
		return clientset;
	}
	
	/**
	 * set 客户端设定值
	 */
	public void setClientset(int clientset){
		this.clientset = clientset;
	}
	
	/**
	 * get 服务端设定值
	 * @return 
	 */
	public int getMenustatus(){
		return menustatus;
	}
	
	/**
	 * set 服务端设定值
	 */
	public void setMenustatus(int menustatus){
		this.menustatus = menustatus;
	}
	
	
	@Override
	public int getId() {
		return 125202;
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
		//客户端设定值
		buf.append("clientset:" + clientset +",");
		//服务端设定值
		buf.append("menustatus:" + menustatus +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
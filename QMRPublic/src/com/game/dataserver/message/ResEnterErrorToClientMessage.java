package com.game.dataserver.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回排位错误消息
 */
public class ResEnterErrorToClientMessage extends Message{

	//错误代码
	private int error;
	
	//结果参数
	private String paras;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeInt(buf, this.error);
		//结果参数
		writeString(buf, this.paras);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.error = readInt(buf);
		//结果参数
		this.paras = readString(buf);
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public int getError(){
		return error;
	}
	
	/**
	 * set 错误代码
	 */
	public void setError(int error){
		this.error = error;
	}
	
	/**
	 * get 结果参数
	 * @return 
	 */
	public String getParas(){
		return paras;
	}
	
	/**
	 * set 结果参数
	 */
	public void setParas(String paras){
		this.paras = paras;
	}
	
	
	@Override
	public int getId() {
		return 203109;
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
		//错误代码
		buf.append("error:" + error +",");
		//结果参数
		if(this.paras!=null) buf.append("paras:" + paras.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
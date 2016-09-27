package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 自定义双倍经验时间消息
 */
public class ReqMonsterDoubleTimeToGameMessage extends Message{

	//内容
	private String content;
	
	//服务器ID，0表示群发到所有服务器
	private int serverdi;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//内容
		writeString(buf, this.content);
		//服务器ID，0表示群发到所有服务器
		writeInt(buf, this.serverdi);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//内容
		this.content = readString(buf);
		//服务器ID，0表示群发到所有服务器
		this.serverdi = readInt(buf);
		return true;
	}
	
	/**
	 * get 内容
	 * @return 
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * set 内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	/**
	 * get 服务器ID，0表示群发到所有服务器
	 * @return 
	 */
	public int getServerdi(){
		return serverdi;
	}
	
	/**
	 * set 服务器ID，0表示群发到所有服务器
	 */
	public void setServerdi(int serverdi){
		this.serverdi = serverdi;
	}
	
	
	@Override
	public int getId() {
		return 114304;
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
		//内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		//服务器ID，0表示群发到所有服务器
		buf.append("serverdi:" + serverdi +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
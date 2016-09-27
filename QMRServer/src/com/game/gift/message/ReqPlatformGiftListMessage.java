package com.game.gift.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求平台礼包列表消息
 */
public class ReqPlatformGiftListMessage extends Message{

	//平台名称
	private String platform;
	
	//面板标识
	private String tag;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//平台名称
		writeString(buf, this.platform);
		//面板标识
		writeString(buf, this.tag);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//平台名称
		this.platform = readString(buf);
		//面板标识
		this.tag = readString(buf);
		return true;
	}
	
	/**
	 * get 平台名称
	 * @return 
	 */
	public String getPlatform(){
		return platform;
	}
	
	/**
	 * set 平台名称
	 */
	public void setPlatform(String platform){
		this.platform = platform;
	}
	
	/**
	 * get 面板标识
	 * @return 
	 */
	public String getTag(){
		return tag;
	}
	
	/**
	 * set 面板标识
	 */
	public void setTag(String tag){
		this.tag = tag;
	}
	
	
	@Override
	public int getId() {
		return 129204;
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
		//平台名称
		if(this.platform!=null) buf.append("platform:" + platform.toString() +",");
		//面板标识
		if(this.tag!=null) buf.append("tag:" + tag.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.shortcut.message;

import com.game.shortcut.bean.ShortCutInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 快捷增加消息
 */
public class ShortCutAddMessage extends Message{

	//快捷信息
	private ShortCutInfo shortcut;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//快捷信息
		writeBean(buf, this.shortcut);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//快捷信息
		this.shortcut = (ShortCutInfo)readBean(buf, ShortCutInfo.class);
		return true;
	}
	
	/**
	 * get 快捷信息
	 * @return 
	 */
	public ShortCutInfo getShortcut(){
		return shortcut;
	}
	
	/**
	 * set 快捷信息
	 */
	public void setShortcut(ShortCutInfo shortcut){
		this.shortcut = shortcut;
	}
	
	
	@Override
	public int getId() {
		return 108102;
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
		//快捷信息
		if(this.shortcut!=null) buf.append("shortcut:" + shortcut.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
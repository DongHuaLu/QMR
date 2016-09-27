package com.game.shortcut.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 删除快捷消息
 */
public class RemoveShortCutMessage extends Message{

	//快捷Id
	private long shortcutId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//快捷Id
		writeLong(buf, this.shortcutId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//快捷Id
		this.shortcutId = readLong(buf);
		return true;
	}
	
	/**
	 * get 快捷Id
	 * @return 
	 */
	public long getShortcutId(){
		return shortcutId;
	}
	
	/**
	 * set 快捷Id
	 */
	public void setShortcutId(long shortcutId){
		this.shortcutId = shortcutId;
	}
	
	
	@Override
	public int getId() {
		return 108202;
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
		//快捷Id
		buf.append("shortcutId:" + shortcutId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
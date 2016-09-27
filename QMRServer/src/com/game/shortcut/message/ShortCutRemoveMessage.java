package com.game.shortcut.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 快捷删除消息
 */
public class ShortCutRemoveMessage extends Message{

	//快捷位置
	private int shortcutGrid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//快捷位置
		writeInt(buf, this.shortcutGrid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//快捷位置
		this.shortcutGrid = readInt(buf);
		return true;
	}
	
	/**
	 * get 快捷位置
	 * @return 
	 */
	public int getShortcutGrid(){
		return shortcutGrid;
	}
	
	/**
	 * set 快捷位置
	 */
	public void setShortcutGrid(int shortcutGrid){
		this.shortcutGrid = shortcutGrid;
	}
	
	
	@Override
	public int getId() {
		return 108103;
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
		//快捷位置
		buf.append("shortcutGrid:" + shortcutGrid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
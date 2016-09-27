package com.game.shortcut.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 移动快捷消息
 */
public class MoveShortCutMessage extends Message{

	//快捷Id
	private long shortcutId;
	
	//快捷位置
	private int shortcutGrid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//快捷Id
		writeLong(buf, this.shortcutId);
		//快捷位置
		writeInt(buf, this.shortcutGrid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//快捷Id
		this.shortcutId = readLong(buf);
		//快捷位置
		this.shortcutGrid = readInt(buf);
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
		return 108203;
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
		//快捷位置
		buf.append("shortcutGrid:" + shortcutGrid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
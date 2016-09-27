package com.game.shortcut.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 增加快捷消息
 */
public class AddShortCutMessage extends Message{

	//快捷类型
	private int shortcutType;
	
	//快捷对象
	private long shortcutSource;
	
	//快捷对象模板
	private int shortcutSourceModel;
	
	//快捷位置
	private int shortcutGrid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//快捷类型
		writeInt(buf, this.shortcutType);
		//快捷对象
		writeLong(buf, this.shortcutSource);
		//快捷对象模板
		writeInt(buf, this.shortcutSourceModel);
		//快捷位置
		writeInt(buf, this.shortcutGrid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//快捷类型
		this.shortcutType = readInt(buf);
		//快捷对象
		this.shortcutSource = readLong(buf);
		//快捷对象模板
		this.shortcutSourceModel = readInt(buf);
		//快捷位置
		this.shortcutGrid = readInt(buf);
		return true;
	}
	
	/**
	 * get 快捷类型
	 * @return 
	 */
	public int getShortcutType(){
		return shortcutType;
	}
	
	/**
	 * set 快捷类型
	 */
	public void setShortcutType(int shortcutType){
		this.shortcutType = shortcutType;
	}
	
	/**
	 * get 快捷对象
	 * @return 
	 */
	public long getShortcutSource(){
		return shortcutSource;
	}
	
	/**
	 * set 快捷对象
	 */
	public void setShortcutSource(long shortcutSource){
		this.shortcutSource = shortcutSource;
	}
	
	/**
	 * get 快捷对象模板
	 * @return 
	 */
	public int getShortcutSourceModel(){
		return shortcutSourceModel;
	}
	
	/**
	 * set 快捷对象模板
	 */
	public void setShortcutSourceModel(int shortcutSourceModel){
		this.shortcutSourceModel = shortcutSourceModel;
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
		return 108201;
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
		//快捷类型
		buf.append("shortcutType:" + shortcutType +",");
		//快捷对象
		buf.append("shortcutSource:" + shortcutSource +",");
		//快捷对象模板
		buf.append("shortcutSourceModel:" + shortcutSourceModel +",");
		//快捷位置
		buf.append("shortcutGrid:" + shortcutGrid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
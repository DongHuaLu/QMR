package com.game.shortcut.message;

import com.game.shortcut.bean.ShortCutInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 快捷信息列表消息
 */
public class ShortCutInfosMessage extends Message{

	//快捷信息列表
	private List<ShortCutInfo> shortcuts = new ArrayList<ShortCutInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//快捷信息列表
		writeShort(buf, shortcuts.size());
		for (int i = 0; i < shortcuts.size(); i++) {
			writeBean(buf, shortcuts.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//快捷信息列表
		int shortcuts_length = readShort(buf);
		for (int i = 0; i < shortcuts_length; i++) {
			shortcuts.add((ShortCutInfo)readBean(buf, ShortCutInfo.class));
		}
		return true;
	}
	
	/**
	 * get 快捷信息列表
	 * @return 
	 */
	public List<ShortCutInfo> getShortcuts(){
		return shortcuts;
	}
	
	/**
	 * set 快捷信息列表
	 */
	public void setShortcuts(List<ShortCutInfo> shortcuts){
		this.shortcuts = shortcuts;
	}
	
	
	@Override
	public int getId() {
		return 108101;
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
		//快捷信息列表
		buf.append("shortcuts:{");
		for (int i = 0; i < shortcuts.size(); i++) {
			buf.append(shortcuts.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
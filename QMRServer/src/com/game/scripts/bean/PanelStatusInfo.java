package com.game.scripts.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 面板改变状态信息
 */
public class PanelStatusInfo extends Bean {

	//面板ID名字
	private String panelname;
	
	//1关闭
	private byte type;
	
	//按钮列表
	private List<ButtonInfo> buttoninfolist = new ArrayList<ButtonInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//面板ID名字
		writeString(buf, this.panelname);
		//1关闭
		writeByte(buf, this.type);
		//按钮列表
		writeShort(buf, buttoninfolist.size());
		for (int i = 0; i < buttoninfolist.size(); i++) {
			writeBean(buf, buttoninfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//面板ID名字
		this.panelname = readString(buf);
		//1关闭
		this.type = readByte(buf);
		//按钮列表
		int buttoninfolist_length = readShort(buf);
		for (int i = 0; i < buttoninfolist_length; i++) {
			buttoninfolist.add((ButtonInfo)readBean(buf, ButtonInfo.class));
		}
		return true;
	}
	
	/**
	 * get 面板ID名字
	 * @return 
	 */
	public String getPanelname(){
		return panelname;
	}
	
	/**
	 * set 面板ID名字
	 */
	public void setPanelname(String panelname){
		this.panelname = panelname;
	}
	
	/**
	 * get 1关闭
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 1关闭
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 按钮列表
	 * @return 
	 */
	public List<ButtonInfo> getButtoninfolist(){
		return buttoninfolist;
	}
	
	/**
	 * set 按钮列表
	 */
	public void setButtoninfolist(List<ButtonInfo> buttoninfolist){
		this.buttoninfolist = buttoninfolist;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//面板ID名字
		if(this.panelname!=null) buf.append("panelname:" + panelname.toString() +",");
		//1关闭
		buf.append("type:" + type +",");
		//按钮列表
		buf.append("buttoninfolist:{");
		for (int i = 0; i < buttoninfolist.size(); i++) {
			buf.append(buttoninfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
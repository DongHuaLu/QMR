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
 * 面板信息
 */
public class PanelInfo extends Bean {

	//面板信息
	private String panelmap;
	
	//面板按钮信息列表
	private List<ButtonInfo> buttoninfolist = new ArrayList<ButtonInfo>();
	//面板文字信息列表
	private List<PanelTxtInfo> paneltxtinfolist = new ArrayList<PanelTxtInfo>();
	//面板道具框信息列表
	private List<PanelItemInfo> paneliteminfolist = new ArrayList<PanelItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//面板信息
		writeString(buf, this.panelmap);
		//面板按钮信息列表
		writeShort(buf, buttoninfolist.size());
		for (int i = 0; i < buttoninfolist.size(); i++) {
			writeBean(buf, buttoninfolist.get(i));
		}
		//面板文字信息列表
		writeShort(buf, paneltxtinfolist.size());
		for (int i = 0; i < paneltxtinfolist.size(); i++) {
			writeBean(buf, paneltxtinfolist.get(i));
		}
		//面板道具框信息列表
		writeShort(buf, paneliteminfolist.size());
		for (int i = 0; i < paneliteminfolist.size(); i++) {
			writeBean(buf, paneliteminfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//面板信息
		this.panelmap = readString(buf);
		//面板按钮信息列表
		int buttoninfolist_length = readShort(buf);
		for (int i = 0; i < buttoninfolist_length; i++) {
			buttoninfolist.add((ButtonInfo)readBean(buf, ButtonInfo.class));
		}
		//面板文字信息列表
		int paneltxtinfolist_length = readShort(buf);
		for (int i = 0; i < paneltxtinfolist_length; i++) {
			paneltxtinfolist.add((PanelTxtInfo)readBean(buf, PanelTxtInfo.class));
		}
		//面板道具框信息列表
		int paneliteminfolist_length = readShort(buf);
		for (int i = 0; i < paneliteminfolist_length; i++) {
			paneliteminfolist.add((PanelItemInfo)readBean(buf, PanelItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 面板信息
	 * @return 
	 */
	public String getPanelmap(){
		return panelmap;
	}
	
	/**
	 * set 面板信息
	 */
	public void setPanelmap(String panelmap){
		this.panelmap = panelmap;
	}
	
	/**
	 * get 面板按钮信息列表
	 * @return 
	 */
	public List<ButtonInfo> getButtoninfolist(){
		return buttoninfolist;
	}
	
	/**
	 * set 面板按钮信息列表
	 */
	public void setButtoninfolist(List<ButtonInfo> buttoninfolist){
		this.buttoninfolist = buttoninfolist;
	}
	
	/**
	 * get 面板文字信息列表
	 * @return 
	 */
	public List<PanelTxtInfo> getPaneltxtinfolist(){
		return paneltxtinfolist;
	}
	
	/**
	 * set 面板文字信息列表
	 */
	public void setPaneltxtinfolist(List<PanelTxtInfo> paneltxtinfolist){
		this.paneltxtinfolist = paneltxtinfolist;
	}
	
	/**
	 * get 面板道具框信息列表
	 * @return 
	 */
	public List<PanelItemInfo> getPaneliteminfolist(){
		return paneliteminfolist;
	}
	
	/**
	 * set 面板道具框信息列表
	 */
	public void setPaneliteminfolist(List<PanelItemInfo> paneliteminfolist){
		this.paneliteminfolist = paneliteminfolist;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//面板信息
		if(this.panelmap!=null) buf.append("panelmap:" + panelmap.toString() +",");
		//面板按钮信息列表
		buf.append("buttoninfolist:{");
		for (int i = 0; i < buttoninfolist.size(); i++) {
			buf.append(buttoninfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//面板文字信息列表
		buf.append("paneltxtinfolist:{");
		for (int i = 0; i < paneltxtinfolist.size(); i++) {
			buf.append(paneltxtinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//面板道具框信息列表
		buf.append("paneliteminfolist:{");
		for (int i = 0; i < paneliteminfolist.size(); i++) {
			buf.append(paneliteminfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
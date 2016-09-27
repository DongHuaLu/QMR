package com.game.scripts.message;

import com.game.scripts.bean.PanelInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送前端面板信息消息
 */
public class ResShowPaneleInfoToClientMessage extends Message{

	//面板信息
	private PanelInfo panelInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//面板信息
		writeBean(buf, this.panelInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//面板信息
		this.panelInfo = (PanelInfo)readBean(buf, PanelInfo.class);
		return true;
	}
	
	/**
	 * get 面板信息
	 * @return 
	 */
	public PanelInfo getPanelInfo(){
		return panelInfo;
	}
	
	/**
	 * set 面板信息
	 */
	public void setPanelInfo(PanelInfo panelInfo){
		this.panelInfo = panelInfo;
	}
	
	
	@Override
	public int getId() {
		return 148102;
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
		//面板信息
		if(this.panelInfo!=null) buf.append("panelInfo:" + panelInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
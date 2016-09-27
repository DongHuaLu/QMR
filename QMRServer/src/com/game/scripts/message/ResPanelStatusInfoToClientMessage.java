package com.game.scripts.message;

import com.game.scripts.bean.PanelStatusInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送前端面板改变状态信息消息
 */
public class ResPanelStatusInfoToClientMessage extends Message{

	//面板改变状态信息
	private PanelStatusInfo panelStatusInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//面板改变状态信息
		writeBean(buf, this.panelStatusInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//面板改变状态信息
		this.panelStatusInfo = (PanelStatusInfo)readBean(buf, PanelStatusInfo.class);
		return true;
	}
	
	/**
	 * get 面板改变状态信息
	 * @return 
	 */
	public PanelStatusInfo getPanelStatusInfo(){
		return panelStatusInfo;
	}
	
	/**
	 * set 面板改变状态信息
	 */
	public void setPanelStatusInfo(PanelStatusInfo panelStatusInfo){
		this.panelStatusInfo = panelStatusInfo;
	}
	
	
	@Override
	public int getId() {
		return 148103;
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
		//面板改变状态信息
		if(this.panelStatusInfo!=null) buf.append("panelStatusInfo:" + panelStatusInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.scripts.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 脚本列表消息
 */
public class ResScriptPaneleMessage extends Message{

	//脚本Id
	private int scriptId;
	
	//面板信息
	private String panel;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//脚本Id
		writeInt(buf, this.scriptId);
		//面板信息
		writeString(buf, this.panel);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//脚本Id
		this.scriptId = readInt(buf);
		//面板信息
		this.panel = readString(buf);
		return true;
	}
	
	/**
	 * get 脚本Id
	 * @return 
	 */
	public int getScriptId(){
		return scriptId;
	}
	
	/**
	 * set 脚本Id
	 */
	public void setScriptId(int scriptId){
		this.scriptId = scriptId;
	}
	
	/**
	 * get 面板信息
	 * @return 
	 */
	public String getPanel(){
		return panel;
	}
	
	/**
	 * set 面板信息
	 */
	public void setPanel(String panel){
		this.panel = panel;
	}
	
	
	@Override
	public int getId() {
		return 148101;
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
		//脚本Id
		buf.append("scriptId:" + scriptId +",");
		//面板信息
		if(this.panel!=null) buf.append("panel:" + panel.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
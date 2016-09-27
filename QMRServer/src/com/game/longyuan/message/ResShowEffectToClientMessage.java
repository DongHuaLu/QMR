package com.game.longyuan.message;

import com.game.longyuan.bean.ShowEffectInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 特效展示消息
 */
public class ResShowEffectToClientMessage extends Message{

	//特效信息
	private ShowEffectInfo showeffectinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//特效信息
		writeBean(buf, this.showeffectinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//特效信息
		this.showeffectinfo = (ShowEffectInfo)readBean(buf, ShowEffectInfo.class);
		return true;
	}
	
	/**
	 * get 特效信息
	 * @return 
	 */
	public ShowEffectInfo getShoweffectinfo(){
		return showeffectinfo;
	}
	
	/**
	 * set 特效信息
	 */
	public void setShoweffectinfo(ShowEffectInfo showeffectinfo){
		this.showeffectinfo = showeffectinfo;
	}
	
	
	@Override
	public int getId() {
		return 115106;
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
		//特效信息
		if(this.showeffectinfo!=null) buf.append("showeffectinfo:" + showeffectinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.plugset.message;

import com.game.plugset.bean.PlugSetInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送自动挂机设置内容消息
 */
public class ResPlugSetInfoMessage extends Message{

	//自动挂机设置内容
	private PlugSetInfo plugsetinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//自动挂机设置内容
		writeBean(buf, this.plugsetinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//自动挂机设置内容
		this.plugsetinfo = (PlugSetInfo)readBean(buf, PlugSetInfo.class);
		return true;
	}
	
	/**
	 * get 自动挂机设置内容
	 * @return 
	 */
	public PlugSetInfo getPlugsetinfo(){
		return plugsetinfo;
	}
	
	/**
	 * set 自动挂机设置内容
	 */
	public void setPlugsetinfo(PlugSetInfo plugsetinfo){
		this.plugsetinfo = plugsetinfo;
	}
	
	
	@Override
	public int getId() {
		return 131101;
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
		//自动挂机设置内容
		if(this.plugsetinfo!=null) buf.append("plugsetinfo:" + plugsetinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
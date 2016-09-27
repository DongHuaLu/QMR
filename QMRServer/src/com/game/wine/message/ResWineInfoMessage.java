package com.game.wine.message;

import com.game.wine.bean.WineConfig;
import com.game.wine.bean.WineUpdate;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 活动信息消息
 */
public class ResWineInfoMessage extends Message{

	//配置信息
	private WineConfig config;
	
	//变动信息
	private WineUpdate update;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//配置信息
		writeBean(buf, this.config);
		//变动信息
		writeBean(buf, this.update);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//配置信息
		this.config = (WineConfig)readBean(buf, WineConfig.class);
		//变动信息
		this.update = (WineUpdate)readBean(buf, WineUpdate.class);
		return true;
	}
	
	/**
	 * get 配置信息
	 * @return 
	 */
	public WineConfig getConfig(){
		return config;
	}
	
	/**
	 * set 配置信息
	 */
	public void setConfig(WineConfig config){
		this.config = config;
	}
	
	/**
	 * get 变动信息
	 * @return 
	 */
	public WineUpdate getUpdate(){
		return update;
	}
	
	/**
	 * set 变动信息
	 */
	public void setUpdate(WineUpdate update){
		this.update = update;
	}
	
	
	@Override
	public int getId() {
		return 159101;
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
		//配置信息
		if(this.config!=null) buf.append("config:" + config.toString() +",");
		//变动信息
		if(this.update!=null) buf.append("update:" + update.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
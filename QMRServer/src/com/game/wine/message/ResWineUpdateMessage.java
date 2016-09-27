package com.game.wine.message;

import com.game.wine.bean.WineUpdate;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 变动信息消息
 */
public class ResWineUpdateMessage extends Message{

	//变动信息
	private WineUpdate update;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//变动信息
		writeBean(buf, this.update);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//变动信息
		this.update = (WineUpdate)readBean(buf, WineUpdate.class);
		return true;
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
		return 159102;
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
		//变动信息
		if(this.update!=null) buf.append("update:" + update.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
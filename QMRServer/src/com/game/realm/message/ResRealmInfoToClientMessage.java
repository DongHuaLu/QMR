package com.game.realm.message;

import com.game.realm.bean.RealmInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 境界信息消息
 */
public class ResRealmInfoToClientMessage extends Message{

	//境界信息
	private RealmInfo bealmInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//境界信息
		writeBean(buf, this.bealmInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//境界信息
		this.bealmInfo = (RealmInfo)readBean(buf, RealmInfo.class);
		return true;
	}
	
	/**
	 * get 境界信息
	 * @return 
	 */
	public RealmInfo getBealmInfo(){
		return bealmInfo;
	}
	
	/**
	 * set 境界信息
	 */
	public void setBealmInfo(RealmInfo bealmInfo){
		this.bealmInfo = bealmInfo;
	}
	
	
	@Override
	public int getId() {
		return 158101;
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
		//境界信息
		if(this.bealmInfo!=null) buf.append("bealmInfo:" + bealmInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
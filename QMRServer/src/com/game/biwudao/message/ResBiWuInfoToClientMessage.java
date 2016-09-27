package com.game.biwudao.message;

import com.game.biwudao.bean.BiWuInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送比武岛信息消息
 */
public class ResBiWuInfoToClientMessage extends Message{

	//比武岛信息
	private BiWuInfo biWuInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//比武岛信息
		writeBean(buf, this.biWuInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//比武岛信息
		this.biWuInfo = (BiWuInfo)readBean(buf, BiWuInfo.class);
		return true;
	}
	
	/**
	 * get 比武岛信息
	 * @return 
	 */
	public BiWuInfo getBiWuInfo(){
		return biWuInfo;
	}
	
	/**
	 * set 比武岛信息
	 */
	public void setBiWuInfo(BiWuInfo biWuInfo){
		this.biWuInfo = biWuInfo;
	}
	
	
	@Override
	public int getId() {
		return 157101;
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
		//比武岛信息
		if(this.biWuInfo!=null) buf.append("biWuInfo:" + biWuInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
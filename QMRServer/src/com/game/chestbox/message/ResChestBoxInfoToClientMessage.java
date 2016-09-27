package com.game.chestbox.message;

import com.game.chestbox.bean.ChestBoxInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宝箱盒子信息发送到客户端消息
 */
public class ResChestBoxInfoToClientMessage extends Message{

	//宝箱盒子信息
	private ChestBoxInfo chestboxinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宝箱盒子信息
		writeBean(buf, this.chestboxinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宝箱盒子信息
		this.chestboxinfo = (ChestBoxInfo)readBean(buf, ChestBoxInfo.class);
		return true;
	}
	
	/**
	 * get 宝箱盒子信息
	 * @return 
	 */
	public ChestBoxInfo getChestboxinfo(){
		return chestboxinfo;
	}
	
	/**
	 * set 宝箱盒子信息
	 */
	public void setChestboxinfo(ChestBoxInfo chestboxinfo){
		this.chestboxinfo = chestboxinfo;
	}
	
	
	@Override
	public int getId() {
		return 156101;
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
		//宝箱盒子信息
		if(this.chestboxinfo!=null) buf.append("chestboxinfo:" + chestboxinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
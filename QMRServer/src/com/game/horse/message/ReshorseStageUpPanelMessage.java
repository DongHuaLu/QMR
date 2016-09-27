package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送坐骑升阶面板信息消息
 */
public class ReshorseStageUpPanelMessage extends Message{

	//消耗金币
	private int gold;
	
	//消耗元宝
	private int yuanbao;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消耗金币
		writeInt(buf, this.gold);
		//消耗元宝
		writeInt(buf, this.yuanbao);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消耗金币
		this.gold = readInt(buf);
		//消耗元宝
		this.yuanbao = readInt(buf);
		return true;
	}
	
	/**
	 * get 消耗金币
	 * @return 
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * set 消耗金币
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	/**
	 * get 消耗元宝
	 * @return 
	 */
	public int getYuanbao(){
		return yuanbao;
	}
	
	/**
	 * set 消耗元宝
	 */
	public void setYuanbao(int yuanbao){
		this.yuanbao = yuanbao;
	}
	
	
	@Override
	public int getId() {
		return 126104;
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
		//消耗金币
		buf.append("gold:" + gold +",");
		//消耗元宝
		buf.append("yuanbao:" + yuanbao +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
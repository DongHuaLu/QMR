package com.game.ybcard.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打开公测元宝卡后展示窗口消息
 */
public class ResYBCardToClientMessage extends Message{

	//公测元宝总数量
	private int yuanbaonum;
	
	//抽到的元宝数量
	private int num;
	
	//类型，0打开面板，1使用元宝卡，2领取元宝
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//公测元宝总数量
		writeInt(buf, this.yuanbaonum);
		//抽到的元宝数量
		writeInt(buf, this.num);
		//类型，0打开面板，1使用元宝卡，2领取元宝
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//公测元宝总数量
		this.yuanbaonum = readInt(buf);
		//抽到的元宝数量
		this.num = readInt(buf);
		//类型，0打开面板，1使用元宝卡，2领取元宝
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 公测元宝总数量
	 * @return 
	 */
	public int getYuanbaonum(){
		return yuanbaonum;
	}
	
	/**
	 * set 公测元宝总数量
	 */
	public void setYuanbaonum(int yuanbaonum){
		this.yuanbaonum = yuanbaonum;
	}
	
	/**
	 * get 抽到的元宝数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 抽到的元宝数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 类型，0打开面板，1使用元宝卡，2领取元宝
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型，0打开面板，1使用元宝卡，2领取元宝
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 139101;
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
		//公测元宝总数量
		buf.append("yuanbaonum:" + yuanbaonum +",");
		//抽到的元宝数量
		buf.append("num:" + num +",");
		//类型，0打开面板，1使用元宝卡，2领取元宝
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
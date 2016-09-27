package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 催熟果实成功后通知前端消息
 */
public class ResRipeningDecYBToClientMessage extends Message{

	//果实ID
	private long fruitid;
	
	//需要的元宝数量
	private int yuanbao;
	
	//类型：1扣元宝成功，0失败
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//果实ID
		writeLong(buf, this.fruitid);
		//需要的元宝数量
		writeInt(buf, this.yuanbao);
		//类型：1扣元宝成功，0失败
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//果实ID
		this.fruitid = readLong(buf);
		//需要的元宝数量
		this.yuanbao = readInt(buf);
		//类型：1扣元宝成功，0失败
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 果实ID
	 * @return 
	 */
	public long getFruitid(){
		return fruitid;
	}
	
	/**
	 * set 果实ID
	 */
	public void setFruitid(long fruitid){
		this.fruitid = fruitid;
	}
	
	/**
	 * get 需要的元宝数量
	 * @return 
	 */
	public int getYuanbao(){
		return yuanbao;
	}
	
	/**
	 * set 需要的元宝数量
	 */
	public void setYuanbao(int yuanbao){
		this.yuanbao = yuanbao;
	}
	
	/**
	 * get 类型：1扣元宝成功，0失败
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：1扣元宝成功，0失败
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 133106;
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
		//果实ID
		buf.append("fruitid:" + fruitid +",");
		//需要的元宝数量
		buf.append("yuanbao:" + yuanbao +",");
		//类型：1扣元宝成功，0失败
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
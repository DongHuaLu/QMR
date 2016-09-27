package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摘果实，奖励展示消息
 */
public class ResShowToRewardListToClientMessage extends Message{

	//果实ID
	private long fruitid;
	
	//果实类型：0普通果实，1银色奇异果，2金色奇异果
	private byte type;
	
	//奖励数据索引
	private int index;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//果实ID
		writeLong(buf, this.fruitid);
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		writeByte(buf, this.type);
		//奖励数据索引
		writeInt(buf, this.index);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//果实ID
		this.fruitid = readLong(buf);
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		this.type = readByte(buf);
		//奖励数据索引
		this.index = readInt(buf);
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
	 * get 果实类型：0普通果实，1银色奇异果，2金色奇异果
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 果实类型：0普通果实，1银色奇异果，2金色奇异果
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 奖励数据索引
	 * @return 
	 */
	public int getIndex(){
		return index;
	}
	
	/**
	 * set 奖励数据索引
	 */
	public void setIndex(int index){
		this.index = index;
	}
	
	
	@Override
	public int getId() {
		return 133104;
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
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		buf.append("type:" + type +",");
		//奖励数据索引
		buf.append("index:" + index +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
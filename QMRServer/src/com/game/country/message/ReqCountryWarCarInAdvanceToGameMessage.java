package com.game.country.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 战车攻击提前移动镜像观察消息
 */
public class ReqCountryWarCarInAdvanceToGameMessage extends Message{

	//NPCid
	private int npcid;
	
	//战车类型
	private byte type;
	
	//攻击坐标X
	private int x;
	
	//攻击坐标Y
	private int y;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//NPCid
		writeInt(buf, this.npcid);
		//战车类型
		writeByte(buf, this.type);
		//攻击坐标X
		writeInt(buf, this.x);
		//攻击坐标Y
		writeInt(buf, this.y);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//NPCid
		this.npcid = readInt(buf);
		//战车类型
		this.type = readByte(buf);
		//攻击坐标X
		this.x = readInt(buf);
		//攻击坐标Y
		this.y = readInt(buf);
		return true;
	}
	
	/**
	 * get NPCid
	 * @return 
	 */
	public int getNpcid(){
		return npcid;
	}
	
	/**
	 * set NPCid
	 */
	public void setNpcid(int npcid){
		this.npcid = npcid;
	}
	
	/**
	 * get 战车类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 战车类型
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 攻击坐标X
	 * @return 
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * set 攻击坐标X
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * get 攻击坐标Y
	 * @return 
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * set 攻击坐标Y
	 */
	public void setY(int y){
		this.y = y;
	}
	
	
	@Override
	public int getId() {
		return 146206;
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
		//NPCid
		buf.append("npcid:" + npcid +",");
		//战车类型
		buf.append("type:" + type +",");
		//攻击坐标X
		buf.append("x:" + x +",");
		//攻击坐标Y
		buf.append("y:" + y +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
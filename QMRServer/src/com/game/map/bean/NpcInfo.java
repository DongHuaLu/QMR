package com.game.map.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * NPC信息
 */
public class NpcInfo extends Bean {

	//NPCID
	private long npcId;
	
	//NPC模型ID
	private int npcModelId;
	
	//NPC名字
	private String npcName;
	
	//NPC资源造型
	private String npcRes;
	
	//NPC头像造型
	private String npcIcon;
	
	//方向
	private byte dir;
	
	//坐标X
	private short x;
	
	//坐标Y
	private short y;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//NPCID
		writeLong(buf, this.npcId);
		//NPC模型ID
		writeInt(buf, this.npcModelId);
		//NPC名字
		writeString(buf, this.npcName);
		//NPC资源造型
		writeString(buf, this.npcRes);
		//NPC头像造型
		writeString(buf, this.npcIcon);
		//方向
		writeByte(buf, this.dir);
		//坐标X
		writeShort(buf, this.x);
		//坐标Y
		writeShort(buf, this.y);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//NPCID
		this.npcId = readLong(buf);
		//NPC模型ID
		this.npcModelId = readInt(buf);
		//NPC名字
		this.npcName = readString(buf);
		//NPC资源造型
		this.npcRes = readString(buf);
		//NPC头像造型
		this.npcIcon = readString(buf);
		//方向
		this.dir = readByte(buf);
		//坐标X
		this.x = readShort(buf);
		//坐标Y
		this.y = readShort(buf);
		return true;
	}
	
	/**
	 * get NPCID
	 * @return 
	 */
	public long getNpcId(){
		return npcId;
	}
	
	/**
	 * set NPCID
	 */
	public void setNpcId(long npcId){
		this.npcId = npcId;
	}
	
	/**
	 * get NPC模型ID
	 * @return 
	 */
	public int getNpcModelId(){
		return npcModelId;
	}
	
	/**
	 * set NPC模型ID
	 */
	public void setNpcModelId(int npcModelId){
		this.npcModelId = npcModelId;
	}
	
	/**
	 * get NPC名字
	 * @return 
	 */
	public String getNpcName(){
		return npcName;
	}
	
	/**
	 * set NPC名字
	 */
	public void setNpcName(String npcName){
		this.npcName = npcName;
	}
	
	/**
	 * get NPC资源造型
	 * @return 
	 */
	public String getNpcRes(){
		return npcRes;
	}
	
	/**
	 * set NPC资源造型
	 */
	public void setNpcRes(String npcRes){
		this.npcRes = npcRes;
	}
	
	/**
	 * get NPC头像造型
	 * @return 
	 */
	public String getNpcIcon(){
		return npcIcon;
	}
	
	/**
	 * set NPC头像造型
	 */
	public void setNpcIcon(String npcIcon){
		this.npcIcon = npcIcon;
	}
	
	/**
	 * get 方向
	 * @return 
	 */
	public byte getDir(){
		return dir;
	}
	
	/**
	 * set 方向
	 */
	public void setDir(byte dir){
		this.dir = dir;
	}
	
	/**
	 * get 坐标X
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 坐标X
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 坐标Y
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 坐标Y
	 */
	public void setY(short y){
		this.y = y;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//NPCID
		buf.append("npcId:" + npcId +",");
		//NPC模型ID
		buf.append("npcModelId:" + npcModelId +",");
		//NPC名字
		if(this.npcName!=null) buf.append("npcName:" + npcName.toString() +",");
		//NPC资源造型
		if(this.npcRes!=null) buf.append("npcRes:" + npcRes.toString() +",");
		//NPC头像造型
		if(this.npcIcon!=null) buf.append("npcIcon:" + npcIcon.toString() +",");
		//方向
		buf.append("dir:" + dir +",");
		//坐标X
		buf.append("x:" + x +",");
		//坐标Y
		buf.append("y:" + y +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
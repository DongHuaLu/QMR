package com.game.gem.message;

import com.game.gem.bean.GemInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 告诉前端要升级或激活的宝石消息
 */
public class ResGemIntoMessage extends Message{

	//装备部位
	private byte pos;
	
	//升级的宝石信息
	private GemInfo geminfo;
	
	//类型：0激活，1升级
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备部位
		writeByte(buf, this.pos);
		//升级的宝石信息
		writeBean(buf, this.geminfo);
		//类型：0激活，1升级
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//装备部位
		this.pos = readByte(buf);
		//升级的宝石信息
		this.geminfo = (GemInfo)readBean(buf, GemInfo.class);
		//类型：0激活，1升级
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 装备部位
	 * @return 
	 */
	public byte getPos(){
		return pos;
	}
	
	/**
	 * set 装备部位
	 */
	public void setPos(byte pos){
		this.pos = pos;
	}
	
	/**
	 * get 升级的宝石信息
	 * @return 
	 */
	public GemInfo getGeminfo(){
		return geminfo;
	}
	
	/**
	 * set 升级的宝石信息
	 */
	public void setGeminfo(GemInfo geminfo){
		this.geminfo = geminfo;
	}
	
	/**
	 * get 类型：0激活，1升级
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：0激活，1升级
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 132102;
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
		//装备部位
		buf.append("pos:" + pos +",");
		//升级的宝石信息
		if(this.geminfo!=null) buf.append("geminfo:" + geminfo.toString() +",");
		//类型：0激活，1升级
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
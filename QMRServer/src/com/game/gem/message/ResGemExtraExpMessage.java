package com.game.gem.message;

import com.game.gem.bean.GemInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 经验溢出后给另外一颗宝石加经验消息
 */
public class ResGemExtraExpMessage extends Message{

	//装备部位
	private byte pos;
	
	//升级的宝石信息
	private GemInfo geminfo;
	
	//获得的经验
	private int exp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备部位
		writeByte(buf, this.pos);
		//升级的宝石信息
		writeBean(buf, this.geminfo);
		//获得的经验
		writeInt(buf, this.exp);
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
		//获得的经验
		this.exp = readInt(buf);
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
	 * get 获得的经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 获得的经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	
	@Override
	public int getId() {
		return 132104;
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
		//获得的经验
		buf.append("exp:" + exp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
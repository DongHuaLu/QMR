package com.game.gem.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 每个部位宝石信息
 */
public class PosGemInfo extends Bean {

	//装备部位（0-9）
	private byte pos;
	
	//每个部位宝石信息（最多5个）
	private List<GemInfo> geminfo = new ArrayList<GemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备部位（0-9）
		writeByte(buf, this.pos);
		//每个部位宝石信息（最多5个）
		writeShort(buf, geminfo.size());
		for (int i = 0; i < geminfo.size(); i++) {
			writeBean(buf, geminfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//装备部位（0-9）
		this.pos = readByte(buf);
		//每个部位宝石信息（最多5个）
		int geminfo_length = readShort(buf);
		for (int i = 0; i < geminfo_length; i++) {
			geminfo.add((GemInfo)readBean(buf, GemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 装备部位（0-9）
	 * @return 
	 */
	public byte getPos(){
		return pos;
	}
	
	/**
	 * set 装备部位（0-9）
	 */
	public void setPos(byte pos){
		this.pos = pos;
	}
	
	/**
	 * get 每个部位宝石信息（最多5个）
	 * @return 
	 */
	public List<GemInfo> getGeminfo(){
		return geminfo;
	}
	
	/**
	 * set 每个部位宝石信息（最多5个）
	 */
	public void setGeminfo(List<GemInfo> geminfo){
		this.geminfo = geminfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//装备部位（0-9）
		buf.append("pos:" + pos +",");
		//每个部位宝石信息（最多5个）
		buf.append("geminfo:{");
		for (int i = 0; i < geminfo.size(); i++) {
			buf.append(geminfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.map.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 场景效果信息
 */
public class EffectInfo extends Bean {

	//EffectID
	private long effectId;
	
	//Effect模型ID
	private int effectModelId;
	
	//播放类型
	private byte type;
	
	//坐标X
	private short x;
	
	//坐标Y
	private short y;
	
	//播放时间
	private int play;
	
	//对象
	private long target;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//EffectID
		writeLong(buf, this.effectId);
		//Effect模型ID
		writeInt(buf, this.effectModelId);
		//播放类型
		writeByte(buf, this.type);
		//坐标X
		writeShort(buf, this.x);
		//坐标Y
		writeShort(buf, this.y);
		//播放时间
		writeInt(buf, this.play);
		//对象
		writeLong(buf, this.target);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//EffectID
		this.effectId = readLong(buf);
		//Effect模型ID
		this.effectModelId = readInt(buf);
		//播放类型
		this.type = readByte(buf);
		//坐标X
		this.x = readShort(buf);
		//坐标Y
		this.y = readShort(buf);
		//播放时间
		this.play = readInt(buf);
		//对象
		this.target = readLong(buf);
		return true;
	}
	
	/**
	 * get EffectID
	 * @return 
	 */
	public long getEffectId(){
		return effectId;
	}
	
	/**
	 * set EffectID
	 */
	public void setEffectId(long effectId){
		this.effectId = effectId;
	}
	
	/**
	 * get Effect模型ID
	 * @return 
	 */
	public int getEffectModelId(){
		return effectModelId;
	}
	
	/**
	 * set Effect模型ID
	 */
	public void setEffectModelId(int effectModelId){
		this.effectModelId = effectModelId;
	}
	
	/**
	 * get 播放类型
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 播放类型
	 */
	public void setType(byte type){
		this.type = type;
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
	
	/**
	 * get 播放时间
	 * @return 
	 */
	public int getPlay(){
		return play;
	}
	
	/**
	 * set 播放时间
	 */
	public void setPlay(int play){
		this.play = play;
	}
	
	/**
	 * get 对象
	 * @return 
	 */
	public long getTarget(){
		return target;
	}
	
	/**
	 * set 对象
	 */
	public void setTarget(long target){
		this.target = target;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//EffectID
		buf.append("effectId:" + effectId +",");
		//Effect模型ID
		buf.append("effectModelId:" + effectModelId +",");
		//播放类型
		buf.append("type:" + type +",");
		//坐标X
		buf.append("x:" + x +",");
		//坐标Y
		buf.append("y:" + y +",");
		//播放时间
		buf.append("play:" + play +",");
		//对象
		buf.append("target:" + target +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
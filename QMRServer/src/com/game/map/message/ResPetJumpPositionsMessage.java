package com.game.map.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 角色跳跃坐标广播消息
 */
public class ResPetJumpPositionsMessage extends Message{

	//侍宠Id
	private long petId;
	
	//跳跃状态 0 一跳  1 二跳
	private byte state;
	
	//跳跃剩余时间
	private int time;
	
	//跳跃坐标集合 只有两个元素 起点 终点
	private List<com.game.structs.Position> positions = new ArrayList<com.game.structs.Position>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//侍宠Id
		writeLong(buf, this.petId);
		//跳跃状态 0 一跳  1 二跳
		writeByte(buf, this.state);
		//跳跃剩余时间
		writeInt(buf, this.time);
		//跳跃坐标集合 只有两个元素 起点 终点
		writeShort(buf, positions.size());
		for (int i = 0; i < positions.size(); i++) {
			writeBean(buf, positions.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//侍宠Id
		this.petId = readLong(buf);
		//跳跃状态 0 一跳  1 二跳
		this.state = readByte(buf);
		//跳跃剩余时间
		this.time = readInt(buf);
		//跳跃坐标集合 只有两个元素 起点 终点
		int positions_length = readShort(buf);
		for (int i = 0; i < positions_length; i++) {
			positions.add((com.game.structs.Position)readBean(buf, com.game.structs.Position.class));
		}
		return true;
	}
	
	/**
	 * get 侍宠Id
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 侍宠Id
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	/**
	 * get 跳跃状态 0 一跳  1 二跳
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 跳跃状态 0 一跳  1 二跳
	 */
	public void setState(byte state){
		this.state = state;
	}
	
	/**
	 * get 跳跃剩余时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 跳跃剩余时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 跳跃坐标集合 只有两个元素 起点 终点
	 * @return 
	 */
	public List<com.game.structs.Position> getPositions(){
		return positions;
	}
	
	/**
	 * set 跳跃坐标集合 只有两个元素 起点 终点
	 */
	public void setPositions(List<com.game.structs.Position> positions){
		this.positions = positions;
	}
	
	
	@Override
	public int getId() {
		return 101133;
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
		//侍宠Id
		buf.append("petId:" + petId +",");
		//跳跃状态 0 一跳  1 二跳
		buf.append("state:" + state +",");
		//跳跃剩余时间
		buf.append("time:" + time +",");
		//跳跃坐标集合 只有两个元素 起点 终点
		buf.append("positions:{");
		for (int i = 0; i < positions.size(); i++) {
			buf.append(positions.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
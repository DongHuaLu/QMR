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
public class ResPlayerJumpPositionsMessage extends Message{

	//角色Id
	private long personId;
	
	//角色状态
	private byte state;
	
	//跳跃剩余时间
	private int time;
	
	//跳跃坐标集合
	private List<com.game.structs.Position> positions = new ArrayList<com.game.structs.Position>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//角色状态
		writeByte(buf, this.state);
		//跳跃剩余时间
		writeInt(buf, this.time);
		//跳跃坐标集合
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
		//角色Id
		this.personId = readLong(buf);
		//角色状态
		this.state = readByte(buf);
		//跳跃剩余时间
		this.time = readInt(buf);
		//跳跃坐标集合
		int positions_length = readShort(buf);
		for (int i = 0; i < positions_length; i++) {
			positions.add((com.game.structs.Position)readBean(buf, com.game.structs.Position.class));
		}
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPersonId(){
		return personId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPersonId(long personId){
		this.personId = personId;
	}
	
	/**
	 * get 角色状态
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 角色状态
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
	 * get 跳跃坐标集合
	 * @return 
	 */
	public List<com.game.structs.Position> getPositions(){
		return positions;
	}
	
	/**
	 * set 跳跃坐标集合
	 */
	public void setPositions(List<com.game.structs.Position> positions){
		this.positions = positions;
	}
	
	
	@Override
	public int getId() {
		return 101112;
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
		//角色Id
		buf.append("personId:" + personId +",");
		//角色状态
		buf.append("state:" + state +",");
		//跳跃剩余时间
		buf.append("time:" + time +",");
		//跳跃坐标集合
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
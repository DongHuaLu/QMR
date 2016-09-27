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
 * 玩家跳跃信息消息
 */
public class ReqJumpMessage extends Message{

	//跳跃类型
	private int type;
	
	//跳跃坐标集合
	private List<com.game.structs.Position> positions = new ArrayList<com.game.structs.Position>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//跳跃类型
		writeInt(buf, this.type);
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
		//跳跃类型
		this.type = readInt(buf);
		//跳跃坐标集合
		int positions_length = readShort(buf);
		for (int i = 0; i < positions_length; i++) {
			positions.add((com.game.structs.Position)readBean(buf, com.game.structs.Position.class));
		}
		return true;
	}
	
	/**
	 * get 跳跃类型
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 跳跃类型
	 */
	public void setType(int type){
		this.type = type;
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
		return 101202;
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
		//跳跃类型
		buf.append("type:" + type +",");
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
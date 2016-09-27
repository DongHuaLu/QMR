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
 * 宠物跑步度座标消息
 */
public class ResPetRunPositionsMessage extends Message{

	//宠物Id
	private long petId;
	
	//当前坐标
	private com.game.structs.Position position;
	
	//跑步坐标集合
	private List<Byte> positions = new ArrayList<Byte>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物Id
		writeLong(buf, this.petId);
		//当前坐标
		writeBean(buf, this.position);
		//跑步坐标集合
		writeShort(buf, positions.size());
		for (int i = 0; i < positions.size(); i++) {
			writeByte(buf, positions.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物Id
		this.petId = readLong(buf);
		//当前坐标
		this.position = (com.game.structs.Position)readBean(buf, com.game.structs.Position.class);
		//跑步坐标集合
		int positions_length = readShort(buf);
		for (int i = 0; i < positions_length; i++) {
			positions.add(readByte(buf));
		}
		return true;
	}
	
	/**
	 * get 宠物Id
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 宠物Id
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	/**
	 * get 当前坐标
	 * @return 
	 */
	public com.game.structs.Position getPosition(){
		return position;
	}
	
	/**
	 * set 当前坐标
	 */
	public void setPosition(com.game.structs.Position position){
		this.position = position;
	}
	
	/**
	 * get 跑步坐标集合
	 * @return 
	 */
	public List<Byte> getPositions(){
		return positions;
	}
	
	/**
	 * set 跑步坐标集合
	 */
	public void setPositions(List<Byte> positions){
		this.positions = positions;
	}
	
	
	@Override
	public int getId() {
		return 101122;
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
		//宠物Id
		buf.append("petId:" + petId +",");
		//当前坐标
		if(this.position!=null) buf.append("position:" + position.toString() +",");
		//跑步坐标集合
		buf.append("positions:{");
		for (int i = 0; i < positions.size(); i++) {
			buf.append(positions.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
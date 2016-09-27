package com.game.maze.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送换层消息消息
 */
public class ResChangeFloorMessage extends Message{

	//换层类型 0-未换层， 1-成功 2-后退 3-最后一层
	private int type;
	
	//现在层数
	private int floor;
	
	//剩余层数
	private int remain;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//换层类型 0-未换层， 1-成功 2-后退 3-最后一层
		writeInt(buf, this.type);
		//现在层数
		writeInt(buf, this.floor);
		//剩余层数
		writeInt(buf, this.remain);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//换层类型 0-未换层， 1-成功 2-后退 3-最后一层
		this.type = readInt(buf);
		//现在层数
		this.floor = readInt(buf);
		//剩余层数
		this.remain = readInt(buf);
		return true;
	}
	
	/**
	 * get 换层类型 0-未换层， 1-成功 2-后退 3-最后一层
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 换层类型 0-未换层， 1-成功 2-后退 3-最后一层
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 现在层数
	 * @return 
	 */
	public int getFloor(){
		return floor;
	}
	
	/**
	 * set 现在层数
	 */
	public void setFloor(int floor){
		this.floor = floor;
	}
	
	/**
	 * get 剩余层数
	 * @return 
	 */
	public int getRemain(){
		return remain;
	}
	
	/**
	 * set 剩余层数
	 */
	public void setRemain(int remain){
		this.remain = remain;
	}
	
	
	@Override
	public int getId() {
		return 145101;
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
		//换层类型 0-未换层， 1-成功 2-后退 3-最后一层
		buf.append("type:" + type +",");
		//现在层数
		buf.append("floor:" + floor +",");
		//剩余层数
		buf.append("remain:" + remain +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
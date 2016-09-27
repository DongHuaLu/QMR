package com.game.epalace.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回前端骰子数值消息
 */
public class ResEpalaceDiceToClientMessage extends Message{

	//骰子数值
	private byte num;
	
	//已经移动次数
	private byte movenum;
	
	//恢复次数冷却时间
	private int time;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//骰子数值
		writeByte(buf, this.num);
		//已经移动次数
		writeByte(buf, this.movenum);
		//恢复次数冷却时间
		writeInt(buf, this.time);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//骰子数值
		this.num = readByte(buf);
		//已经移动次数
		this.movenum = readByte(buf);
		//恢复次数冷却时间
		this.time = readInt(buf);
		return true;
	}
	
	/**
	 * get 骰子数值
	 * @return 
	 */
	public byte getNum(){
		return num;
	}
	
	/**
	 * set 骰子数值
	 */
	public void setNum(byte num){
		this.num = num;
	}
	
	/**
	 * get 已经移动次数
	 * @return 
	 */
	public byte getMovenum(){
		return movenum;
	}
	
	/**
	 * set 已经移动次数
	 */
	public void setMovenum(byte movenum){
		this.movenum = movenum;
	}
	
	/**
	 * get 恢复次数冷却时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 恢复次数冷却时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	
	@Override
	public int getId() {
		return 143102;
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
		//骰子数值
		buf.append("num:" + num +",");
		//已经移动次数
		buf.append("movenum:" + movenum +",");
		//恢复次数冷却时间
		buf.append("time:" + time +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
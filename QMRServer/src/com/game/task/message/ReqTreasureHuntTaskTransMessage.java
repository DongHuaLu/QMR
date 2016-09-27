package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 讨伐任务传送消息
 */
public class ReqTreasureHuntTaskTransMessage extends Message{

	//任务ID
	private long taskId;
	
	//目标地图ID
	private int mapid;
	
	//目标x
	private int x;
	
	//目标y
	private int y;
	
	//目标line
	private int line;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务ID
		writeLong(buf, this.taskId);
		//目标地图ID
		writeInt(buf, this.mapid);
		//目标x
		writeInt(buf, this.x);
		//目标y
		writeInt(buf, this.y);
		//目标line
		writeInt(buf, this.line);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务ID
		this.taskId = readLong(buf);
		//目标地图ID
		this.mapid = readInt(buf);
		//目标x
		this.x = readInt(buf);
		//目标y
		this.y = readInt(buf);
		//目标line
		this.line = readInt(buf);
		return true;
	}
	
	/**
	 * get 任务ID
	 * @return 
	 */
	public long getTaskId(){
		return taskId;
	}
	
	/**
	 * set 任务ID
	 */
	public void setTaskId(long taskId){
		this.taskId = taskId;
	}
	
	/**
	 * get 目标地图ID
	 * @return 
	 */
	public int getMapid(){
		return mapid;
	}
	
	/**
	 * set 目标地图ID
	 */
	public void setMapid(int mapid){
		this.mapid = mapid;
	}
	
	/**
	 * get 目标x
	 * @return 
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * set 目标x
	 */
	public void setX(int x){
		this.x = x;
	}
	
	/**
	 * get 目标y
	 * @return 
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * set 目标y
	 */
	public void setY(int y){
		this.y = y;
	}
	
	/**
	 * get 目标line
	 * @return 
	 */
	public int getLine(){
		return line;
	}
	
	/**
	 * set 目标line
	 */
	public void setLine(int line){
		this.line = line;
	}
	
	
	@Override
	public int getId() {
		return 120212;
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
		//任务ID
		buf.append("taskId:" + taskId +",");
		//目标地图ID
		buf.append("mapid:" + mapid +",");
		//目标x
		buf.append("x:" + x +",");
		//目标y
		buf.append("y:" + y +",");
		//目标line
		buf.append("line:" + line +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
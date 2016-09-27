package com.game.epalace.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打开面板返回消息消息
 */
public class ResEpalaceOpenToGameMessage extends Message{

	//恢复次数冷却时间
	private int time;
	
	//任务ID
	private int task;
	
	//当前站立位置
	private byte pos;
	
	//已经移动次数
	private byte movenum;
	
	//造型信息
	private com.game.player.bean.PlayerAppearanceInfo appearanceInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//恢复次数冷却时间
		writeInt(buf, this.time);
		//任务ID
		writeInt(buf, this.task);
		//当前站立位置
		writeByte(buf, this.pos);
		//已经移动次数
		writeByte(buf, this.movenum);
		//造型信息
		writeBean(buf, this.appearanceInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//恢复次数冷却时间
		this.time = readInt(buf);
		//任务ID
		this.task = readInt(buf);
		//当前站立位置
		this.pos = readByte(buf);
		//已经移动次数
		this.movenum = readByte(buf);
		//造型信息
		this.appearanceInfo = (com.game.player.bean.PlayerAppearanceInfo)readBean(buf, com.game.player.bean.PlayerAppearanceInfo.class);
		return true;
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
	
	/**
	 * get 任务ID
	 * @return 
	 */
	public int getTask(){
		return task;
	}
	
	/**
	 * set 任务ID
	 */
	public void setTask(int task){
		this.task = task;
	}
	
	/**
	 * get 当前站立位置
	 * @return 
	 */
	public byte getPos(){
		return pos;
	}
	
	/**
	 * set 当前站立位置
	 */
	public void setPos(byte pos){
		this.pos = pos;
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
	 * get 造型信息
	 * @return 
	 */
	public com.game.player.bean.PlayerAppearanceInfo getAppearanceInfo(){
		return appearanceInfo;
	}
	
	/**
	 * set 造型信息
	 */
	public void setAppearanceInfo(com.game.player.bean.PlayerAppearanceInfo appearanceInfo){
		this.appearanceInfo = appearanceInfo;
	}
	
	
	@Override
	public int getId() {
		return 143103;
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
		//恢复次数冷却时间
		buf.append("time:" + time +",");
		//任务ID
		buf.append("task:" + task +",");
		//当前站立位置
		buf.append("pos:" + pos +",");
		//已经移动次数
		buf.append("movenum:" + movenum +",");
		//造型信息
		if(this.appearanceInfo!=null) buf.append("appearanceInfo:" + appearanceInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
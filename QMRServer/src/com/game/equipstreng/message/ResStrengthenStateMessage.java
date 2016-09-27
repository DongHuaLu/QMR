package com.game.equipstreng.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送当前强化状态消息
 */
public class ResStrengthenStateMessage extends Message{

	//道具唯一ID，0表示没有道具在强化
	private long itemid;
	
	//强化完成剩余时间
	private int time;
	
	//强化完成需要的总时间
	private int timesum;
	
	//消耗的元宝基础值
	private int yuanbao;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//道具唯一ID，0表示没有道具在强化
		writeLong(buf, this.itemid);
		//强化完成剩余时间
		writeInt(buf, this.time);
		//强化完成需要的总时间
		writeInt(buf, this.timesum);
		//消耗的元宝基础值
		writeInt(buf, this.yuanbao);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//道具唯一ID，0表示没有道具在强化
		this.itemid = readLong(buf);
		//强化完成剩余时间
		this.time = readInt(buf);
		//强化完成需要的总时间
		this.timesum = readInt(buf);
		//消耗的元宝基础值
		this.yuanbao = readInt(buf);
		return true;
	}
	
	/**
	 * get 道具唯一ID，0表示没有道具在强化
	 * @return 
	 */
	public long getItemid(){
		return itemid;
	}
	
	/**
	 * set 道具唯一ID，0表示没有道具在强化
	 */
	public void setItemid(long itemid){
		this.itemid = itemid;
	}
	
	/**
	 * get 强化完成剩余时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 强化完成剩余时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 强化完成需要的总时间
	 * @return 
	 */
	public int getTimesum(){
		return timesum;
	}
	
	/**
	 * set 强化完成需要的总时间
	 */
	public void setTimesum(int timesum){
		this.timesum = timesum;
	}
	
	/**
	 * get 消耗的元宝基础值
	 * @return 
	 */
	public int getYuanbao(){
		return yuanbao;
	}
	
	/**
	 * set 消耗的元宝基础值
	 */
	public void setYuanbao(int yuanbao){
		this.yuanbao = yuanbao;
	}
	
	
	@Override
	public int getId() {
		return 130104;
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
		//道具唯一ID，0表示没有道具在强化
		buf.append("itemid:" + itemid +",");
		//强化完成剩余时间
		buf.append("time:" + time +",");
		//强化完成需要的总时间
		buf.append("timesum:" + timesum +",");
		//消耗的元宝基础值
		buf.append("yuanbao:" + yuanbao +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
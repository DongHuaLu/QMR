package com.game.maze.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送奖励消息消息
 */
public class ResRewardMessage extends Message{

	//NPC ID
	private long npc;
	
	//排名
	private int sort;
	
	//花费时间
	private int time;
	
	//礼金
	private int bindgold;
	
	//经验
	private int exp;
	
	//真气
	private int zhenqi;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//NPC ID
		writeLong(buf, this.npc);
		//排名
		writeInt(buf, this.sort);
		//花费时间
		writeInt(buf, this.time);
		//礼金
		writeInt(buf, this.bindgold);
		//经验
		writeInt(buf, this.exp);
		//真气
		writeInt(buf, this.zhenqi);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//NPC ID
		this.npc = readLong(buf);
		//排名
		this.sort = readInt(buf);
		//花费时间
		this.time = readInt(buf);
		//礼金
		this.bindgold = readInt(buf);
		//经验
		this.exp = readInt(buf);
		//真气
		this.zhenqi = readInt(buf);
		return true;
	}
	
	/**
	 * get NPC ID
	 * @return 
	 */
	public long getNpc(){
		return npc;
	}
	
	/**
	 * set NPC ID
	 */
	public void setNpc(long npc){
		this.npc = npc;
	}
	
	/**
	 * get 排名
	 * @return 
	 */
	public int getSort(){
		return sort;
	}
	
	/**
	 * set 排名
	 */
	public void setSort(int sort){
		this.sort = sort;
	}
	
	/**
	 * get 花费时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 花费时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 礼金
	 * @return 
	 */
	public int getBindgold(){
		return bindgold;
	}
	
	/**
	 * set 礼金
	 */
	public void setBindgold(int bindgold){
		this.bindgold = bindgold;
	}
	
	/**
	 * get 经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 真气
	 * @return 
	 */
	public int getZhenqi(){
		return zhenqi;
	}
	
	/**
	 * set 真气
	 */
	public void setZhenqi(int zhenqi){
		this.zhenqi = zhenqi;
	}
	
	
	@Override
	public int getId() {
		return 145102;
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
		//NPC ID
		buf.append("npc:" + npc +",");
		//排名
		buf.append("sort:" + sort +",");
		//花费时间
		buf.append("time:" + time +",");
		//礼金
		buf.append("bindgold:" + bindgold +",");
		//经验
		buf.append("exp:" + exp +",");
		//真气
		buf.append("zhenqi:" + zhenqi +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
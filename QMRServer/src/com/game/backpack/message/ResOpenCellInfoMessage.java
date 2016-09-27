package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打开格子所需条件以及奖励信息消息
 */
public class ResOpenCellInfoMessage extends Message{

	//格子编号
	private int cellId;
	
	//剩余时间(秒)
	private int timeRemaining;
	
	//所需元宝数
	private int gold;
	
	//开启后获得的经验数
	private int exp;
	
	//增大的最大血量
	private int maxhp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//格子编号
		writeInt(buf, this.cellId);
		//剩余时间(秒)
		writeInt(buf, this.timeRemaining);
		//所需元宝数
		writeInt(buf, this.gold);
		//开启后获得的经验数
		writeInt(buf, this.exp);
		//增大的最大血量
		writeInt(buf, this.maxhp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//格子编号
		this.cellId = readInt(buf);
		//剩余时间(秒)
		this.timeRemaining = readInt(buf);
		//所需元宝数
		this.gold = readInt(buf);
		//开启后获得的经验数
		this.exp = readInt(buf);
		//增大的最大血量
		this.maxhp = readInt(buf);
		return true;
	}
	
	/**
	 * get 格子编号
	 * @return 
	 */
	public int getCellId(){
		return cellId;
	}
	
	/**
	 * set 格子编号
	 */
	public void setCellId(int cellId){
		this.cellId = cellId;
	}
	
	/**
	 * get 剩余时间(秒)
	 * @return 
	 */
	public int getTimeRemaining(){
		return timeRemaining;
	}
	
	/**
	 * set 剩余时间(秒)
	 */
	public void setTimeRemaining(int timeRemaining){
		this.timeRemaining = timeRemaining;
	}
	
	/**
	 * get 所需元宝数
	 * @return 
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * set 所需元宝数
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	/**
	 * get 开启后获得的经验数
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 开启后获得的经验数
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 增大的最大血量
	 * @return 
	 */
	public int getMaxhp(){
		return maxhp;
	}
	
	/**
	 * set 增大的最大血量
	 */
	public void setMaxhp(int maxhp){
		this.maxhp = maxhp;
	}
	
	
	@Override
	public int getId() {
		return 104108;
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
		//格子编号
		buf.append("cellId:" + cellId +",");
		//剩余时间(秒)
		buf.append("timeRemaining:" + timeRemaining +",");
		//所需元宝数
		buf.append("gold:" + gold +",");
		//开启后获得的经验数
		buf.append("exp:" + exp +",");
		//增大的最大血量
		buf.append("maxhp:" + maxhp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
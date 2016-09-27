package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 扫荡剩余总次数消息
 */
public class ResZoneSurplusSumMessage extends Message{

	//单人战役剩余次数
	private int num;
	
	//七曜战将剩余次数
	private int qiyaonum;
	
	//最近的可挑战七曜战将副本
	private int qiyaozoneid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//单人战役剩余次数
		writeInt(buf, this.num);
		//七曜战将剩余次数
		writeInt(buf, this.qiyaonum);
		//最近的可挑战七曜战将副本
		writeInt(buf, this.qiyaozoneid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//单人战役剩余次数
		this.num = readInt(buf);
		//七曜战将剩余次数
		this.qiyaonum = readInt(buf);
		//最近的可挑战七曜战将副本
		this.qiyaozoneid = readInt(buf);
		return true;
	}
	
	/**
	 * get 单人战役剩余次数
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 单人战役剩余次数
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 七曜战将剩余次数
	 * @return 
	 */
	public int getQiyaonum(){
		return qiyaonum;
	}
	
	/**
	 * set 七曜战将剩余次数
	 */
	public void setQiyaonum(int qiyaonum){
		this.qiyaonum = qiyaonum;
	}
	
	/**
	 * get 最近的可挑战七曜战将副本
	 * @return 
	 */
	public int getQiyaozoneid(){
		return qiyaozoneid;
	}
	
	/**
	 * set 最近的可挑战七曜战将副本
	 */
	public void setQiyaozoneid(int qiyaozoneid){
		this.qiyaozoneid = qiyaozoneid;
	}
	
	
	@Override
	public int getId() {
		return 128113;
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
		//单人战役剩余次数
		buf.append("num:" + num +",");
		//七曜战将剩余次数
		buf.append("qiyaonum:" + qiyaonum +",");
		//最近的可挑战七曜战将副本
		buf.append("qiyaozoneid:" + qiyaozoneid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
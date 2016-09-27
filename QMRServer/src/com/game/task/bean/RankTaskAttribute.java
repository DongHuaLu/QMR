package com.game.task.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 任务追踪
 */
public class RankTaskAttribute extends Bean {

	//任务类型
	private int type;
	
	//数量
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务类型
		writeInt(buf, this.type);
		//数量
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务类型
		this.type = readInt(buf);
		//数量
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 任务类型
	 * @return 
	 */
	public int getType(){
		return type;
	}
	
	/**
	 * set 任务类型
	 */
	public void setType(int type){
		this.type = type;
	}
	
	/**
	 * get 数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//任务类型
		buf.append("type:" + type +",");
		//数量
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
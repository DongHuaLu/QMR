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
public class TaskAttribute extends Bean {

	//模型
	private int model;
	
	//数量
	private int num;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//模型
		writeInt(buf, this.model);
		//数量
		writeInt(buf, this.num);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//模型
		this.model = readInt(buf);
		//数量
		this.num = readInt(buf);
		return true;
	}
	
	/**
	 * get 模型
	 * @return 
	 */
	public int getModel(){
		return model;
	}
	
	/**
	 * set 模型
	 */
	public void setModel(int model){
		this.model = model;
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
		//模型
		buf.append("model:" + model +",");
		//数量
		buf.append("num:" + num +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
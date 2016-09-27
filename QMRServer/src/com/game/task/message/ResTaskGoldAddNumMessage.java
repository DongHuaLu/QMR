package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 任务增加次数消息
 */
public class ResTaskGoldAddNumMessage extends Message{

	//任务类型
	private int tasktype;
	
	//现在的最大次数
	private int nowmaxnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务类型
		writeInt(buf, this.tasktype);
		//现在的最大次数
		writeInt(buf, this.nowmaxnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务类型
		this.tasktype = readInt(buf);
		//现在的最大次数
		this.nowmaxnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 任务类型
	 * @return 
	 */
	public int getTasktype(){
		return tasktype;
	}
	
	/**
	 * set 任务类型
	 */
	public void setTasktype(int tasktype){
		this.tasktype = tasktype;
	}
	
	/**
	 * get 现在的最大次数
	 * @return 
	 */
	public int getNowmaxnum(){
		return nowmaxnum;
	}
	
	/**
	 * set 现在的最大次数
	 */
	public void setNowmaxnum(int nowmaxnum){
		this.nowmaxnum = nowmaxnum;
	}
	
	
	@Override
	public int getId() {
		return 120118;
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
		//任务类型
		buf.append("tasktype:" + tasktype +",");
		//现在的最大次数
		buf.append("nowmaxnum:" + nowmaxnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 任务通过元宝增加次数上限消息
 */
public class ReqTaskGoldAddNumMessage extends Message{

	//任务类型
	private int tasktype;
	
	//增加次数
	private int addnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务类型
		writeInt(buf, this.tasktype);
		//增加次数
		writeInt(buf, this.addnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务类型
		this.tasktype = readInt(buf);
		//增加次数
		this.addnum = readInt(buf);
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
	 * get 增加次数
	 * @return 
	 */
	public int getAddnum(){
		return addnum;
	}
	
	/**
	 * set 增加次数
	 */
	public void setAddnum(int addnum){
		this.addnum = addnum;
	}
	
	
	@Override
	public int getId() {
		return 120216;
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
		//增加次数
		buf.append("addnum:" + addnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.task.message;

import com.game.task.bean.MainTaskInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 主线任务变更消息
 */
public class ResMainTaskChangeMessage extends Message{

	//主线任务
	private MainTaskInfo task;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//主线任务
		writeBean(buf, this.task);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//主线任务
		this.task = (MainTaskInfo)readBean(buf, MainTaskInfo.class);
		return true;
	}
	
	/**
	 * get 主线任务
	 * @return 
	 */
	public MainTaskInfo getTask(){
		return task;
	}
	
	/**
	 * set 主线任务
	 */
	public void setTask(MainTaskInfo task){
		this.task = task;
	}
	
	
	@Override
	public int getId() {
		return 120104;
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
		//主线任务
		if(this.task!=null) buf.append("task:" + task.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
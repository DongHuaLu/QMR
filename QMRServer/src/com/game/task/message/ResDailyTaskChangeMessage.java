package com.game.task.message;

import com.game.task.bean.DailyTaskInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 日常任务变更消息
 */
public class ResDailyTaskChangeMessage extends Message{

	//当日日常任务己接取次数
	private int daylyTaskacceptcount;
	
	//日常任务
	private DailyTaskInfo task;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当日日常任务己接取次数
		writeInt(buf, this.daylyTaskacceptcount);
		//日常任务
		writeBean(buf, this.task);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当日日常任务己接取次数
		this.daylyTaskacceptcount = readInt(buf);
		//日常任务
		this.task = (DailyTaskInfo)readBean(buf, DailyTaskInfo.class);
		return true;
	}
	
	/**
	 * get 当日日常任务己接取次数
	 * @return 
	 */
	public int getDaylyTaskacceptcount(){
		return daylyTaskacceptcount;
	}
	
	/**
	 * set 当日日常任务己接取次数
	 */
	public void setDaylyTaskacceptcount(int daylyTaskacceptcount){
		this.daylyTaskacceptcount = daylyTaskacceptcount;
	}
	
	/**
	 * get 日常任务
	 * @return 
	 */
	public DailyTaskInfo getTask(){
		return task;
	}
	
	/**
	 * set 日常任务
	 */
	public void setTask(DailyTaskInfo task){
		this.task = task;
	}
	
	
	@Override
	public int getId() {
		return 120105;
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
		//当日日常任务己接取次数
		buf.append("daylyTaskacceptcount:" + daylyTaskacceptcount +",");
		//日常任务
		if(this.task!=null) buf.append("task:" + task.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.task.message;

import com.game.task.bean.RankTaskInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 军衔任务列表变更消息
 */
public class ResRankTaskChangeMessage extends Message{

	//军衔任务变更
	private RankTaskInfo taskInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//军衔任务变更
		writeBean(buf, this.taskInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//军衔任务变更
		this.taskInfo = (RankTaskInfo)readBean(buf, RankTaskInfo.class);
		return true;
	}
	
	/**
	 * get 军衔任务变更
	 * @return 
	 */
	public RankTaskInfo getTaskInfo(){
		return taskInfo;
	}
	
	/**
	 * set 军衔任务变更
	 */
	public void setTaskInfo(RankTaskInfo taskInfo){
		this.taskInfo = taskInfo;
	}
	
	
	@Override
	public int getId() {
		return 120115;
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
		//军衔任务变更
		if(this.taskInfo!=null) buf.append("taskInfo:" + taskInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
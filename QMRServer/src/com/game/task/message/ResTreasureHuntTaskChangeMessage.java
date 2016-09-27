package com.game.task.message;

import com.game.task.bean.TreasureHuntTaskInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 寻宝任务变更消息
 */
public class ResTreasureHuntTaskChangeMessage extends Message{

	//寻宝任务
	private TreasureHuntTaskInfo task;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//寻宝任务
		writeBean(buf, this.task);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//寻宝任务
		this.task = (TreasureHuntTaskInfo)readBean(buf, TreasureHuntTaskInfo.class);
		return true;
	}
	
	/**
	 * get 寻宝任务
	 * @return 
	 */
	public TreasureHuntTaskInfo getTask(){
		return task;
	}
	
	/**
	 * set 寻宝任务
	 */
	public void setTask(TreasureHuntTaskInfo task){
		this.task = task;
	}
	
	
	@Override
	public int getId() {
		return 120111;
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
		//寻宝任务
		if(this.task!=null) buf.append("task:" + task.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.task.message;

import com.game.task.bean.ConquerTaskInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 讨伐任务重排消息
 */
public class ResConquerTaskListUpdateMessage extends Message{

	//当日讨伐接取次数
	private int conquerTaskAcceptCount;
	
	//当日讨伐接取最大次数
	private int conquerTaskAcceptMaxCount;
	
	//今日吞噬数
	private int devourCount;
	
	//讨伐任务
	private List<ConquerTaskInfo> task = new ArrayList<ConquerTaskInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当日讨伐接取次数
		writeInt(buf, this.conquerTaskAcceptCount);
		//当日讨伐接取最大次数
		writeInt(buf, this.conquerTaskAcceptMaxCount);
		//今日吞噬数
		writeInt(buf, this.devourCount);
		//讨伐任务
		writeShort(buf, task.size());
		for (int i = 0; i < task.size(); i++) {
			writeBean(buf, task.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当日讨伐接取次数
		this.conquerTaskAcceptCount = readInt(buf);
		//当日讨伐接取最大次数
		this.conquerTaskAcceptMaxCount = readInt(buf);
		//今日吞噬数
		this.devourCount = readInt(buf);
		//讨伐任务
		int task_length = readShort(buf);
		for (int i = 0; i < task_length; i++) {
			task.add((ConquerTaskInfo)readBean(buf, ConquerTaskInfo.class));
		}
		return true;
	}
	
	/**
	 * get 当日讨伐接取次数
	 * @return 
	 */
	public int getConquerTaskAcceptCount(){
		return conquerTaskAcceptCount;
	}
	
	/**
	 * set 当日讨伐接取次数
	 */
	public void setConquerTaskAcceptCount(int conquerTaskAcceptCount){
		this.conquerTaskAcceptCount = conquerTaskAcceptCount;
	}
	
	/**
	 * get 当日讨伐接取最大次数
	 * @return 
	 */
	public int getConquerTaskAcceptMaxCount(){
		return conquerTaskAcceptMaxCount;
	}
	
	/**
	 * set 当日讨伐接取最大次数
	 */
	public void setConquerTaskAcceptMaxCount(int conquerTaskAcceptMaxCount){
		this.conquerTaskAcceptMaxCount = conquerTaskAcceptMaxCount;
	}
	
	/**
	 * get 今日吞噬数
	 * @return 
	 */
	public int getDevourCount(){
		return devourCount;
	}
	
	/**
	 * set 今日吞噬数
	 */
	public void setDevourCount(int devourCount){
		this.devourCount = devourCount;
	}
	
	/**
	 * get 讨伐任务
	 * @return 
	 */
	public List<ConquerTaskInfo> getTask(){
		return task;
	}
	
	/**
	 * set 讨伐任务
	 */
	public void setTask(List<ConquerTaskInfo> task){
		this.task = task;
	}
	
	
	@Override
	public int getId() {
		return 120109;
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
		//当日讨伐接取次数
		buf.append("conquerTaskAcceptCount:" + conquerTaskAcceptCount +",");
		//当日讨伐接取最大次数
		buf.append("conquerTaskAcceptMaxCount:" + conquerTaskAcceptMaxCount +",");
		//今日吞噬数
		buf.append("devourCount:" + devourCount +",");
		//讨伐任务
		buf.append("task:{");
		for (int i = 0; i < task.size(); i++) {
			buf.append(task.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
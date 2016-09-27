package com.game.task.message;

import com.game.task.bean.RankTaskInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 任务列表消息
 */
public class ResRankTaskListMessage extends Message{

	//军衔任务列表
	private List<RankTaskInfo> tasklist = new ArrayList<RankTaskInfo>();
	//完成军衔任务列表
	private List<Integer> completetasklist = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//军衔任务列表
		writeShort(buf, tasklist.size());
		for (int i = 0; i < tasklist.size(); i++) {
			writeBean(buf, tasklist.get(i));
		}
		//完成军衔任务列表
		writeShort(buf, completetasklist.size());
		for (int i = 0; i < completetasklist.size(); i++) {
			writeInt(buf, completetasklist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//军衔任务列表
		int tasklist_length = readShort(buf);
		for (int i = 0; i < tasklist_length; i++) {
			tasklist.add((RankTaskInfo)readBean(buf, RankTaskInfo.class));
		}
		//完成军衔任务列表
		int completetasklist_length = readShort(buf);
		for (int i = 0; i < completetasklist_length; i++) {
			completetasklist.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 军衔任务列表
	 * @return 
	 */
	public List<RankTaskInfo> getTasklist(){
		return tasklist;
	}
	
	/**
	 * set 军衔任务列表
	 */
	public void setTasklist(List<RankTaskInfo> tasklist){
		this.tasklist = tasklist;
	}
	
	/**
	 * get 完成军衔任务列表
	 * @return 
	 */
	public List<Integer> getCompletetasklist(){
		return completetasklist;
	}
	
	/**
	 * set 完成军衔任务列表
	 */
	public void setCompletetasklist(List<Integer> completetasklist){
		this.completetasklist = completetasklist;
	}
	
	
	@Override
	public int getId() {
		return 120114;
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
		//军衔任务列表
		buf.append("tasklist:{");
		for (int i = 0; i < tasklist.size(); i++) {
			buf.append(tasklist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//完成军衔任务列表
		buf.append("completetasklist:{");
		for (int i = 0; i < completetasklist.size(); i++) {
			buf.append(completetasklist.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.task.message;

import com.game.task.bean.ConquerTaskInfo;
import com.game.task.bean.MainTaskInfo;
import com.game.task.bean.DailyTaskInfo;
import com.game.task.bean.TreasureHuntTaskInfo;
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
public class ResTaskListMessage extends Message{

	//当日日常任务己接取次数
	private int daylyTaskacceptcount;
	
	//当日讨伐接取次数
	private int conquerTaskAcceptCount;
	
	//当日讨伐接取最大次数
	private int conquerTaskAcceptMaxCount;
	
	//今日吞噬数
	private int devourCount;
	
	//日常任务列表
	private List<DailyTaskInfo> DailyTask = new ArrayList<DailyTaskInfo>();
	//讨伐任务
	private List<ConquerTaskInfo> conqueryTask = new ArrayList<ConquerTaskInfo>();
	//主线任务
	private List<MainTaskInfo> mainTask = new ArrayList<MainTaskInfo>();
	//寻宝任务
	private List<TreasureHuntTaskInfo> treasureHuntTask = new ArrayList<TreasureHuntTaskInfo>();
	//可领取
	private List<com.game.backpack.bean.ItemInfo> ableAct = new ArrayList<com.game.backpack.bean.ItemInfo>();
	//是否有新的日常任务1是 0否
	private int ishasnewdailytask;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当日日常任务己接取次数
		writeInt(buf, this.daylyTaskacceptcount);
		//当日讨伐接取次数
		writeInt(buf, this.conquerTaskAcceptCount);
		//当日讨伐接取最大次数
		writeInt(buf, this.conquerTaskAcceptMaxCount);
		//今日吞噬数
		writeInt(buf, this.devourCount);
		//日常任务列表
		writeShort(buf, DailyTask.size());
		for (int i = 0; i < DailyTask.size(); i++) {
			writeBean(buf, DailyTask.get(i));
		}
		//讨伐任务
		writeShort(buf, conqueryTask.size());
		for (int i = 0; i < conqueryTask.size(); i++) {
			writeBean(buf, conqueryTask.get(i));
		}
		//主线任务
		writeShort(buf, mainTask.size());
		for (int i = 0; i < mainTask.size(); i++) {
			writeBean(buf, mainTask.get(i));
		}
		//寻宝任务
		writeShort(buf, treasureHuntTask.size());
		for (int i = 0; i < treasureHuntTask.size(); i++) {
			writeBean(buf, treasureHuntTask.get(i));
		}
		//可领取
		writeShort(buf, ableAct.size());
		for (int i = 0; i < ableAct.size(); i++) {
			writeBean(buf, ableAct.get(i));
		}
		//是否有新的日常任务1是 0否
		writeInt(buf, this.ishasnewdailytask);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当日日常任务己接取次数
		this.daylyTaskacceptcount = readInt(buf);
		//当日讨伐接取次数
		this.conquerTaskAcceptCount = readInt(buf);
		//当日讨伐接取最大次数
		this.conquerTaskAcceptMaxCount = readInt(buf);
		//今日吞噬数
		this.devourCount = readInt(buf);
		//日常任务列表
		int DailyTask_length = readShort(buf);
		for (int i = 0; i < DailyTask_length; i++) {
			DailyTask.add((DailyTaskInfo)readBean(buf, DailyTaskInfo.class));
		}
		//讨伐任务
		int conqueryTask_length = readShort(buf);
		for (int i = 0; i < conqueryTask_length; i++) {
			conqueryTask.add((ConquerTaskInfo)readBean(buf, ConquerTaskInfo.class));
		}
		//主线任务
		int mainTask_length = readShort(buf);
		for (int i = 0; i < mainTask_length; i++) {
			mainTask.add((MainTaskInfo)readBean(buf, MainTaskInfo.class));
		}
		//寻宝任务
		int treasureHuntTask_length = readShort(buf);
		for (int i = 0; i < treasureHuntTask_length; i++) {
			treasureHuntTask.add((TreasureHuntTaskInfo)readBean(buf, TreasureHuntTaskInfo.class));
		}
		//可领取
		int ableAct_length = readShort(buf);
		for (int i = 0; i < ableAct_length; i++) {
			ableAct.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		//是否有新的日常任务1是 0否
		this.ishasnewdailytask = readInt(buf);
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
	 * get 日常任务列表
	 * @return 
	 */
	public List<DailyTaskInfo> getDailyTask(){
		return DailyTask;
	}
	
	/**
	 * set 日常任务列表
	 */
	public void setDailyTask(List<DailyTaskInfo> DailyTask){
		this.DailyTask = DailyTask;
	}
	
	/**
	 * get 讨伐任务
	 * @return 
	 */
	public List<ConquerTaskInfo> getConqueryTask(){
		return conqueryTask;
	}
	
	/**
	 * set 讨伐任务
	 */
	public void setConqueryTask(List<ConquerTaskInfo> conqueryTask){
		this.conqueryTask = conqueryTask;
	}
	
	/**
	 * get 主线任务
	 * @return 
	 */
	public List<MainTaskInfo> getMainTask(){
		return mainTask;
	}
	
	/**
	 * set 主线任务
	 */
	public void setMainTask(List<MainTaskInfo> mainTask){
		this.mainTask = mainTask;
	}
	
	/**
	 * get 寻宝任务
	 * @return 
	 */
	public List<TreasureHuntTaskInfo> getTreasureHuntTask(){
		return treasureHuntTask;
	}
	
	/**
	 * set 寻宝任务
	 */
	public void setTreasureHuntTask(List<TreasureHuntTaskInfo> treasureHuntTask){
		this.treasureHuntTask = treasureHuntTask;
	}
	
	/**
	 * get 可领取
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getAbleAct(){
		return ableAct;
	}
	
	/**
	 * set 可领取
	 */
	public void setAbleAct(List<com.game.backpack.bean.ItemInfo> ableAct){
		this.ableAct = ableAct;
	}
	
	/**
	 * get 是否有新的日常任务1是 0否
	 * @return 
	 */
	public int getIshasnewdailytask(){
		return ishasnewdailytask;
	}
	
	/**
	 * set 是否有新的日常任务1是 0否
	 */
	public void setIshasnewdailytask(int ishasnewdailytask){
		this.ishasnewdailytask = ishasnewdailytask;
	}
	
	
	@Override
	public int getId() {
		return 120101;
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
		//当日讨伐接取次数
		buf.append("conquerTaskAcceptCount:" + conquerTaskAcceptCount +",");
		//当日讨伐接取最大次数
		buf.append("conquerTaskAcceptMaxCount:" + conquerTaskAcceptMaxCount +",");
		//今日吞噬数
		buf.append("devourCount:" + devourCount +",");
		//日常任务列表
		buf.append("DailyTask:{");
		for (int i = 0; i < DailyTask.size(); i++) {
			buf.append(DailyTask.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//讨伐任务
		buf.append("conqueryTask:{");
		for (int i = 0; i < conqueryTask.size(); i++) {
			buf.append(conqueryTask.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//主线任务
		buf.append("mainTask:{");
		for (int i = 0; i < mainTask.size(); i++) {
			buf.append(mainTask.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//寻宝任务
		buf.append("treasureHuntTask:{");
		for (int i = 0; i < treasureHuntTask.size(); i++) {
			buf.append(treasureHuntTask.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//可领取
		buf.append("ableAct:{");
		for (int i = 0; i < ableAct.size(); i++) {
			buf.append(ableAct.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//是否有新的日常任务1是 0否
		buf.append("ishasnewdailytask:" + ishasnewdailytask +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
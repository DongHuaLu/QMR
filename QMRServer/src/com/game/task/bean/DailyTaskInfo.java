package com.game.task.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 日常任务
 */
public class DailyTaskInfo extends Bean {

	//奖励模型ID
	private int jlId;
	
	//完成条件模型ID
	private int premiseId;
	
	//额外奖励模型ID
	private int otherjlId;
	
	//达成物品成数
	private List<TaskAttribute> permiseGoods = new ArrayList<TaskAttribute>();
	//达成怪物
	private List<TaskAttribute> permiseMonster = new ArrayList<TaskAttribute>();
	//达成成就
	private List<Integer> permiseAchieve = new ArrayList<Integer>();
	//任务完成奖励物品
	private List<com.game.backpack.bean.ItemInfo> rewards = new ArrayList<com.game.backpack.bean.ItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//奖励模型ID
		writeInt(buf, this.jlId);
		//完成条件模型ID
		writeInt(buf, this.premiseId);
		//额外奖励模型ID
		writeInt(buf, this.otherjlId);
		//达成物品成数
		writeShort(buf, permiseGoods.size());
		for (int i = 0; i < permiseGoods.size(); i++) {
			writeBean(buf, permiseGoods.get(i));
		}
		//达成怪物
		writeShort(buf, permiseMonster.size());
		for (int i = 0; i < permiseMonster.size(); i++) {
			writeBean(buf, permiseMonster.get(i));
		}
		//达成成就
		writeShort(buf, permiseAchieve.size());
		for (int i = 0; i < permiseAchieve.size(); i++) {
			writeInt(buf, permiseAchieve.get(i));
		}
		//任务完成奖励物品
		writeShort(buf, rewards.size());
		for (int i = 0; i < rewards.size(); i++) {
			writeBean(buf, rewards.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//奖励模型ID
		this.jlId = readInt(buf);
		//完成条件模型ID
		this.premiseId = readInt(buf);
		//额外奖励模型ID
		this.otherjlId = readInt(buf);
		//达成物品成数
		int permiseGoods_length = readShort(buf);
		for (int i = 0; i < permiseGoods_length; i++) {
			permiseGoods.add((TaskAttribute)readBean(buf, TaskAttribute.class));
		}
		//达成怪物
		int permiseMonster_length = readShort(buf);
		for (int i = 0; i < permiseMonster_length; i++) {
			permiseMonster.add((TaskAttribute)readBean(buf, TaskAttribute.class));
		}
		//达成成就
		int permiseAchieve_length = readShort(buf);
		for (int i = 0; i < permiseAchieve_length; i++) {
			permiseAchieve.add(readInt(buf));
		}
		//任务完成奖励物品
		int rewards_length = readShort(buf);
		for (int i = 0; i < rewards_length; i++) {
			rewards.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 奖励模型ID
	 * @return 
	 */
	public int getJlId(){
		return jlId;
	}
	
	/**
	 * set 奖励模型ID
	 */
	public void setJlId(int jlId){
		this.jlId = jlId;
	}
	
	/**
	 * get 完成条件模型ID
	 * @return 
	 */
	public int getPremiseId(){
		return premiseId;
	}
	
	/**
	 * set 完成条件模型ID
	 */
	public void setPremiseId(int premiseId){
		this.premiseId = premiseId;
	}
	
	/**
	 * get 额外奖励模型ID
	 * @return 
	 */
	public int getOtherjlId(){
		return otherjlId;
	}
	
	/**
	 * set 额外奖励模型ID
	 */
	public void setOtherjlId(int otherjlId){
		this.otherjlId = otherjlId;
	}
	
	/**
	 * get 达成物品成数
	 * @return 
	 */
	public List<TaskAttribute> getPermiseGoods(){
		return permiseGoods;
	}
	
	/**
	 * set 达成物品成数
	 */
	public void setPermiseGoods(List<TaskAttribute> permiseGoods){
		this.permiseGoods = permiseGoods;
	}
	
	/**
	 * get 达成怪物
	 * @return 
	 */
	public List<TaskAttribute> getPermiseMonster(){
		return permiseMonster;
	}
	
	/**
	 * set 达成怪物
	 */
	public void setPermiseMonster(List<TaskAttribute> permiseMonster){
		this.permiseMonster = permiseMonster;
	}
	
	/**
	 * get 达成成就
	 * @return 
	 */
	public List<Integer> getPermiseAchieve(){
		return permiseAchieve;
	}
	
	/**
	 * set 达成成就
	 */
	public void setPermiseAchieve(List<Integer> permiseAchieve){
		this.permiseAchieve = permiseAchieve;
	}
	
	/**
	 * get 任务完成奖励物品
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getRewards(){
		return rewards;
	}
	
	/**
	 * set 任务完成奖励物品
	 */
	public void setRewards(List<com.game.backpack.bean.ItemInfo> rewards){
		this.rewards = rewards;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//奖励模型ID
		buf.append("jlId:" + jlId +",");
		//完成条件模型ID
		buf.append("premiseId:" + premiseId +",");
		//额外奖励模型ID
		buf.append("otherjlId:" + otherjlId +",");
		//达成物品成数
		buf.append("permiseGoods:{");
		for (int i = 0; i < permiseGoods.size(); i++) {
			buf.append(permiseGoods.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//达成怪物
		buf.append("permiseMonster:{");
		for (int i = 0; i < permiseMonster.size(); i++) {
			buf.append(permiseMonster.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//达成成就
		buf.append("permiseAchieve:{");
		for (int i = 0; i < permiseAchieve.size(); i++) {
			buf.append(permiseAchieve.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//任务完成奖励物品
		buf.append("rewards:{");
		for (int i = 0; i < rewards.size(); i++) {
			buf.append(rewards.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
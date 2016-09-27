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
 * 寻宝任务
 */
public class TreasureHuntTaskInfo extends Bean {

	//任务ID
	private long id;
	
	//任务模型
	private int modelId;
	
	//达成物品成数
	private List<TaskAttribute> permiseGoods = new ArrayList<TaskAttribute>();
	//达成怪物
	private List<TaskAttribute> permiseMonster = new ArrayList<TaskAttribute>();
	//达成成就
	private List<Integer> permiseAchieve = new ArrayList<Integer>();
	//奖励经验
	private int rewardsExp;
	
	//奖励铜币
	private int rewardsCopper;
	
	//奖励真气
	private int rewardsZQ;
	
	//奖励声望
	private int rewardsSW;
	
	//奖励绑定元宝
	private int rewardsBindGold;
	
	//任务完成奖励物品
	private List<com.game.backpack.bean.ItemInfo> rewards = new ArrayList<com.game.backpack.bean.ItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务ID
		writeLong(buf, this.id);
		//任务模型
		writeInt(buf, this.modelId);
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
		//奖励经验
		writeInt(buf, this.rewardsExp);
		//奖励铜币
		writeInt(buf, this.rewardsCopper);
		//奖励真气
		writeInt(buf, this.rewardsZQ);
		//奖励声望
		writeInt(buf, this.rewardsSW);
		//奖励绑定元宝
		writeInt(buf, this.rewardsBindGold);
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
		//任务ID
		this.id = readLong(buf);
		//任务模型
		this.modelId = readInt(buf);
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
		//奖励经验
		this.rewardsExp = readInt(buf);
		//奖励铜币
		this.rewardsCopper = readInt(buf);
		//奖励真气
		this.rewardsZQ = readInt(buf);
		//奖励声望
		this.rewardsSW = readInt(buf);
		//奖励绑定元宝
		this.rewardsBindGold = readInt(buf);
		//任务完成奖励物品
		int rewards_length = readShort(buf);
		for (int i = 0; i < rewards_length; i++) {
			rewards.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 任务ID
	 * @return 
	 */
	public long getId(){
		return id;
	}
	
	/**
	 * set 任务ID
	 */
	public void setId(long id){
		this.id = id;
	}
	
	/**
	 * get 任务模型
	 * @return 
	 */
	public int getModelId(){
		return modelId;
	}
	
	/**
	 * set 任务模型
	 */
	public void setModelId(int modelId){
		this.modelId = modelId;
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
	 * get 奖励经验
	 * @return 
	 */
	public int getRewardsExp(){
		return rewardsExp;
	}
	
	/**
	 * set 奖励经验
	 */
	public void setRewardsExp(int rewardsExp){
		this.rewardsExp = rewardsExp;
	}
	
	/**
	 * get 奖励铜币
	 * @return 
	 */
	public int getRewardsCopper(){
		return rewardsCopper;
	}
	
	/**
	 * set 奖励铜币
	 */
	public void setRewardsCopper(int rewardsCopper){
		this.rewardsCopper = rewardsCopper;
	}
	
	/**
	 * get 奖励真气
	 * @return 
	 */
	public int getRewardsZQ(){
		return rewardsZQ;
	}
	
	/**
	 * set 奖励真气
	 */
	public void setRewardsZQ(int rewardsZQ){
		this.rewardsZQ = rewardsZQ;
	}
	
	/**
	 * get 奖励声望
	 * @return 
	 */
	public int getRewardsSW(){
		return rewardsSW;
	}
	
	/**
	 * set 奖励声望
	 */
	public void setRewardsSW(int rewardsSW){
		this.rewardsSW = rewardsSW;
	}
	
	/**
	 * get 奖励绑定元宝
	 * @return 
	 */
	public int getRewardsBindGold(){
		return rewardsBindGold;
	}
	
	/**
	 * set 奖励绑定元宝
	 */
	public void setRewardsBindGold(int rewardsBindGold){
		this.rewardsBindGold = rewardsBindGold;
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
		//任务ID
		buf.append("id:" + id +",");
		//任务模型
		buf.append("modelId:" + modelId +",");
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
		//奖励经验
		buf.append("rewardsExp:" + rewardsExp +",");
		//奖励铜币
		buf.append("rewardsCopper:" + rewardsCopper +",");
		//奖励真气
		buf.append("rewardsZQ:" + rewardsZQ +",");
		//奖励声望
		buf.append("rewardsSW:" + rewardsSW +",");
		//奖励绑定元宝
		buf.append("rewardsBindGold:" + rewardsBindGold +",");
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
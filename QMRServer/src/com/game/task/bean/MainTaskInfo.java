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
 * 主线任务
 */
public class MainTaskInfo extends Bean {

	//任务模型
	private int modelId;
	
	//是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
	private byte isFinshAction;
	
	//达成物品成数
	private List<TaskAttribute> permiseGoods = new ArrayList<TaskAttribute>();
	//达成怪物
	private List<TaskAttribute> permiseMonster = new ArrayList<TaskAttribute>();
	//达成成就
	private List<Integer> permiseAchieve = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务模型
		writeInt(buf, this.modelId);
		//是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
		writeByte(buf, this.isFinshAction);
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
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务模型
		this.modelId = readInt(buf);
		//是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
		this.isFinshAction = readByte(buf);
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
		return true;
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
	 * get 是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
	 * @return 
	 */
	public byte getIsFinshAction(){
		return isFinshAction;
	}
	
	/**
	 * set 是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
	 */
	public void setIsFinshAction(byte isFinshAction){
		this.isFinshAction = isFinshAction;
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
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//任务模型
		buf.append("modelId:" + modelId +",");
		//是否完成指定动作 1完成 2未完成 无动作条件时默认为完成
		buf.append("isFinshAction:" + isFinshAction +",");
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
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
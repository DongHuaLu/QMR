package com.game.task.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 更新在可领取区域物品变更消息
 */
public class ResRewardsAbleActMessage extends Message{

	//任务完成待领取的物品
	private List<com.game.backpack.bean.ItemInfo> ableAct = new ArrayList<com.game.backpack.bean.ItemInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务完成待领取的物品
		writeShort(buf, ableAct.size());
		for (int i = 0; i < ableAct.size(); i++) {
			writeBean(buf, ableAct.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务完成待领取的物品
		int ableAct_length = readShort(buf);
		for (int i = 0; i < ableAct_length; i++) {
			ableAct.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		return true;
	}
	
	/**
	 * get 任务完成待领取的物品
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getAbleAct(){
		return ableAct;
	}
	
	/**
	 * set 任务完成待领取的物品
	 */
	public void setAbleAct(List<com.game.backpack.bean.ItemInfo> ableAct){
		this.ableAct = ableAct;
	}
	
	
	@Override
	public int getId() {
		return 120103;
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
		//任务完成待领取的物品
		buf.append("ableAct:{");
		for (int i = 0; i < ableAct.size(); i++) {
			buf.append(ableAct.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
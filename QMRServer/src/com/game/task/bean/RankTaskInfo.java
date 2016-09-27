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
 * 军功任务
 */
public class RankTaskInfo extends Bean {

	//任务模型
	private int modelId;
	
	//任务追踪
	private List<RankTaskAttribute> permiseGoods = new ArrayList<RankTaskAttribute>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//任务模型
		writeInt(buf, this.modelId);
		//任务追踪
		writeShort(buf, permiseGoods.size());
		for (int i = 0; i < permiseGoods.size(); i++) {
			writeBean(buf, permiseGoods.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//任务模型
		this.modelId = readInt(buf);
		//任务追踪
		int permiseGoods_length = readShort(buf);
		for (int i = 0; i < permiseGoods_length; i++) {
			permiseGoods.add((RankTaskAttribute)readBean(buf, RankTaskAttribute.class));
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
	 * get 任务追踪
	 * @return 
	 */
	public List<RankTaskAttribute> getPermiseGoods(){
		return permiseGoods;
	}
	
	/**
	 * set 任务追踪
	 */
	public void setPermiseGoods(List<RankTaskAttribute> permiseGoods){
		this.permiseGoods = permiseGoods;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//任务模型
		buf.append("modelId:" + modelId +",");
		//任务追踪
		buf.append("permiseGoods:{");
		for (int i = 0; i < permiseGoods.size(); i++) {
			buf.append(permiseGoods.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
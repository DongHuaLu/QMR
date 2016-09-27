package com.game.player.message;

import com.game.player.bean.PlayerAttributeItem;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 个人属性值变化消息
 */
public class ResPlayerAttributeChangeMessage extends Message{

	//变化的属性原因
	private int attributeChangeReason;
	
	//模型Id
	private int modelId;
	
	//变化的属性列表
	private List<PlayerAttributeItem> attributeChangeList = new ArrayList<PlayerAttributeItem>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//变化的属性原因
		writeInt(buf, this.attributeChangeReason);
		//模型Id
		writeInt(buf, this.modelId);
		//变化的属性列表
		writeShort(buf, attributeChangeList.size());
		for (int i = 0; i < attributeChangeList.size(); i++) {
			writeBean(buf, attributeChangeList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//变化的属性原因
		this.attributeChangeReason = readInt(buf);
		//模型Id
		this.modelId = readInt(buf);
		//变化的属性列表
		int attributeChangeList_length = readShort(buf);
		for (int i = 0; i < attributeChangeList_length; i++) {
			attributeChangeList.add((PlayerAttributeItem)readBean(buf, PlayerAttributeItem.class));
		}
		return true;
	}
	
	/**
	 * get 变化的属性原因
	 * @return 
	 */
	public int getAttributeChangeReason(){
		return attributeChangeReason;
	}
	
	/**
	 * set 变化的属性原因
	 */
	public void setAttributeChangeReason(int attributeChangeReason){
		this.attributeChangeReason = attributeChangeReason;
	}
	
	/**
	 * get 模型Id
	 * @return 
	 */
	public int getModelId(){
		return modelId;
	}
	
	/**
	 * set 模型Id
	 */
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	/**
	 * get 变化的属性列表
	 * @return 
	 */
	public List<PlayerAttributeItem> getAttributeChangeList(){
		return attributeChangeList;
	}
	
	/**
	 * set 变化的属性列表
	 */
	public void setAttributeChangeList(List<PlayerAttributeItem> attributeChangeList){
		this.attributeChangeList = attributeChangeList;
	}
	
	
	@Override
	public int getId() {
		return 103119;
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
		//变化的属性原因
		buf.append("attributeChangeReason:" + attributeChangeReason +",");
		//模型Id
		buf.append("modelId:" + modelId +",");
		//变化的属性列表
		buf.append("attributeChangeList:{");
		for (int i = 0; i < attributeChangeList.size(); i++) {
			buf.append(attributeChangeList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
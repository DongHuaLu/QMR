package com.game.backpack.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 拾取成功消息
 */
public class ResTakeUpSuccessMessage extends Message{

	//物品ID
	private long goodsId;
	
	//物品模型ID
	private int goodModelId;
	
	//产生效果类型
	private int effectType;
	
	//产生效果值
	private int effectValue;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品ID
		writeLong(buf, this.goodsId);
		//物品模型ID
		writeInt(buf, this.goodModelId);
		//产生效果类型
		writeInt(buf, this.effectType);
		//产生效果值
		writeInt(buf, this.effectValue);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品ID
		this.goodsId = readLong(buf);
		//物品模型ID
		this.goodModelId = readInt(buf);
		//产生效果类型
		this.effectType = readInt(buf);
		//产生效果值
		this.effectValue = readInt(buf);
		return true;
	}
	
	/**
	 * get 物品ID
	 * @return 
	 */
	public long getGoodsId(){
		return goodsId;
	}
	
	/**
	 * set 物品ID
	 */
	public void setGoodsId(long goodsId){
		this.goodsId = goodsId;
	}
	
	/**
	 * get 物品模型ID
	 * @return 
	 */
	public int getGoodModelId(){
		return goodModelId;
	}
	
	/**
	 * set 物品模型ID
	 */
	public void setGoodModelId(int goodModelId){
		this.goodModelId = goodModelId;
	}
	
	/**
	 * get 产生效果类型
	 * @return 
	 */
	public int getEffectType(){
		return effectType;
	}
	
	/**
	 * set 产生效果类型
	 */
	public void setEffectType(int effectType){
		this.effectType = effectType;
	}
	
	/**
	 * get 产生效果值
	 * @return 
	 */
	public int getEffectValue(){
		return effectValue;
	}
	
	/**
	 * set 产生效果值
	 */
	public void setEffectValue(int effectValue){
		this.effectValue = effectValue;
	}
	
	
	@Override
	public int getId() {
		return 104212;
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
		//物品ID
		buf.append("goodsId:" + goodsId +",");
		//物品模型ID
		buf.append("goodModelId:" + goodModelId +",");
		//产生效果类型
		buf.append("effectType:" + effectType +",");
		//产生效果值
		buf.append("effectValue:" + effectValue +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
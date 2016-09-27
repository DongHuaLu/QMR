package com.game.shop.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 请求商店出售物品列表消息
 */
public class ReqShopListMessage extends Message{

	//模型编号
	private int shopModelId;
	
	//页号
	private byte page;
	
	//是否显示高于玩家等级的商品1 显示 0 不显示
	private byte gradeLimit;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//模型编号
		writeInt(buf, this.shopModelId);
		//页号
		writeByte(buf, this.page);
		//是否显示高于玩家等级的商品1 显示 0 不显示
		writeByte(buf, this.gradeLimit);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//模型编号
		this.shopModelId = readInt(buf);
		//页号
		this.page = readByte(buf);
		//是否显示高于玩家等级的商品1 显示 0 不显示
		this.gradeLimit = readByte(buf);
		return true;
	}
	
	/**
	 * get 模型编号
	 * @return 
	 */
	public int getShopModelId(){
		return shopModelId;
	}
	
	/**
	 * set 模型编号
	 */
	public void setShopModelId(int shopModelId){
		this.shopModelId = shopModelId;
	}
	
	/**
	 * get 页号
	 * @return 
	 */
	public byte getPage(){
		return page;
	}
	
	/**
	 * set 页号
	 */
	public void setPage(byte page){
		this.page = page;
	}
	
	/**
	 * get 是否显示高于玩家等级的商品1 显示 0 不显示
	 * @return 
	 */
	public byte getGradeLimit(){
		return gradeLimit;
	}
	
	/**
	 * set 是否显示高于玩家等级的商品1 显示 0 不显示
	 */
	public void setGradeLimit(byte gradeLimit){
		this.gradeLimit = gradeLimit;
	}
	
	
	@Override
	public int getId() {
		return 105206;
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
		//模型编号
		buf.append("shopModelId:" + shopModelId +",");
		//页号
		buf.append("page:" + page +",");
		//是否显示高于玩家等级的商品1 显示 0 不显示
		buf.append("gradeLimit:" + gradeLimit +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.shop.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 购买物品消息
 */
public class BuyItemMessage extends Message{

	//NPC Id
	private int npcId;
	
	//销售Id
	private int sellId;
	
	//物品数量
	private int num;
	
	//花费类型
	private int costType;
	
	//物品模型ID
	private int modelId;
	
	//金币价格
	private int coin;
	
	//元宝价格
	private int gold;
	
	//绑定元宝价格
	private int bindgold;
	
	//原金币价格
	private int originalCoin;
	
	//原元宝价格
	private int originalGold;
	
	//原绑定元宝价格
	private int originalBindGold;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//NPC Id
		writeInt(buf, this.npcId);
		//销售Id
		writeInt(buf, this.sellId);
		//物品数量
		writeInt(buf, this.num);
		//花费类型
		writeInt(buf, this.costType);
		//物品模型ID
		writeInt(buf, this.modelId);
		//金币价格
		writeInt(buf, this.coin);
		//元宝价格
		writeInt(buf, this.gold);
		//绑定元宝价格
		writeInt(buf, this.bindgold);
		//原金币价格
		writeInt(buf, this.originalCoin);
		//原元宝价格
		writeInt(buf, this.originalGold);
		//原绑定元宝价格
		writeInt(buf, this.originalBindGold);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//NPC Id
		this.npcId = readInt(buf);
		//销售Id
		this.sellId = readInt(buf);
		//物品数量
		this.num = readInt(buf);
		//花费类型
		this.costType = readInt(buf);
		//物品模型ID
		this.modelId = readInt(buf);
		//金币价格
		this.coin = readInt(buf);
		//元宝价格
		this.gold = readInt(buf);
		//绑定元宝价格
		this.bindgold = readInt(buf);
		//原金币价格
		this.originalCoin = readInt(buf);
		//原元宝价格
		this.originalGold = readInt(buf);
		//原绑定元宝价格
		this.originalBindGold = readInt(buf);
		return true;
	}
	
	/**
	 * get NPC Id
	 * @return 
	 */
	public int getNpcId(){
		return npcId;
	}
	
	/**
	 * set NPC Id
	 */
	public void setNpcId(int npcId){
		this.npcId = npcId;
	}
	
	/**
	 * get 销售Id
	 * @return 
	 */
	public int getSellId(){
		return sellId;
	}
	
	/**
	 * set 销售Id
	 */
	public void setSellId(int sellId){
		this.sellId = sellId;
	}
	
	/**
	 * get 物品数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 物品数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 花费类型
	 * @return 
	 */
	public int getCostType(){
		return costType;
	}
	
	/**
	 * set 花费类型
	 */
	public void setCostType(int costType){
		this.costType = costType;
	}
	
	/**
	 * get 物品模型ID
	 * @return 
	 */
	public int getModelId(){
		return modelId;
	}
	
	/**
	 * set 物品模型ID
	 */
	public void setModelId(int modelId){
		this.modelId = modelId;
	}
	
	/**
	 * get 金币价格
	 * @return 
	 */
	public int getCoin(){
		return coin;
	}
	
	/**
	 * set 金币价格
	 */
	public void setCoin(int coin){
		this.coin = coin;
	}
	
	/**
	 * get 元宝价格
	 * @return 
	 */
	public int getGold(){
		return gold;
	}
	
	/**
	 * set 元宝价格
	 */
	public void setGold(int gold){
		this.gold = gold;
	}
	
	/**
	 * get 绑定元宝价格
	 * @return 
	 */
	public int getBindgold(){
		return bindgold;
	}
	
	/**
	 * set 绑定元宝价格
	 */
	public void setBindgold(int bindgold){
		this.bindgold = bindgold;
	}
	
	/**
	 * get 原金币价格
	 * @return 
	 */
	public int getOriginalCoin(){
		return originalCoin;
	}
	
	/**
	 * set 原金币价格
	 */
	public void setOriginalCoin(int originalCoin){
		this.originalCoin = originalCoin;
	}
	
	/**
	 * get 原元宝价格
	 * @return 
	 */
	public int getOriginalGold(){
		return originalGold;
	}
	
	/**
	 * set 原元宝价格
	 */
	public void setOriginalGold(int originalGold){
		this.originalGold = originalGold;
	}
	
	/**
	 * get 原绑定元宝价格
	 * @return 
	 */
	public int getOriginalBindGold(){
		return originalBindGold;
	}
	
	/**
	 * set 原绑定元宝价格
	 */
	public void setOriginalBindGold(int originalBindGold){
		this.originalBindGold = originalBindGold;
	}
	
	
	@Override
	public int getId() {
		return 105201;
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
		//NPC Id
		buf.append("npcId:" + npcId +",");
		//销售Id
		buf.append("sellId:" + sellId +",");
		//物品数量
		buf.append("num:" + num +",");
		//花费类型
		buf.append("costType:" + costType +",");
		//物品模型ID
		buf.append("modelId:" + modelId +",");
		//金币价格
		buf.append("coin:" + coin +",");
		//元宝价格
		buf.append("gold:" + gold +",");
		//绑定元宝价格
		buf.append("bindgold:" + bindgold +",");
		//原金币价格
		buf.append("originalCoin:" + originalCoin +",");
		//原元宝价格
		buf.append("originalGold:" + originalGold +",");
		//原绑定元宝价格
		buf.append("originalBindGold:" + originalBindGold +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
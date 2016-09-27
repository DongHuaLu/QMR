package com.game.backpack.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 物品信息类
 */
public class ItemInfo extends Bean {

	//角色Id
	private long itemId;
	
	//物品模板Id
	private int itemModelId;
	
	//物品数量
	private int num;
	
	//角色所在格子Id
	private int gridId;
	
	//是否绑定 1是 0否 
	private byte isbind;
	
	//强化等级
	private byte intensify;
	
	//扩展属性数量
	private byte attributs;
	
	//是否顶级附加 1是 0否
	private byte isFullAppend;
	
	//过期时间
	private int lostTime;
	
	//扩展属性
	private List<GoodsAttribute> goodAttributes = new ArrayList<GoodsAttribute>();
	//参数，额外信息
	private String parameters;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.itemId);
		//物品模板Id
		writeInt(buf, this.itemModelId);
		//物品数量
		writeInt(buf, this.num);
		//角色所在格子Id
		writeInt(buf, this.gridId);
		//是否绑定 1是 0否 
		writeByte(buf, this.isbind);
		//强化等级
		writeByte(buf, this.intensify);
		//扩展属性数量
		writeByte(buf, this.attributs);
		//是否顶级附加 1是 0否
		writeByte(buf, this.isFullAppend);
		//过期时间
		writeInt(buf, this.lostTime);
		//扩展属性
		writeShort(buf, goodAttributes.size());
		for (int i = 0; i < goodAttributes.size(); i++) {
			writeBean(buf, goodAttributes.get(i));
		}
		//参数，额外信息
		writeString(buf, this.parameters);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.itemId = readLong(buf);
		//物品模板Id
		this.itemModelId = readInt(buf);
		//物品数量
		this.num = readInt(buf);
		//角色所在格子Id
		this.gridId = readInt(buf);
		//是否绑定 1是 0否 
		this.isbind = readByte(buf);
		//强化等级
		this.intensify = readByte(buf);
		//扩展属性数量
		this.attributs = readByte(buf);
		//是否顶级附加 1是 0否
		this.isFullAppend = readByte(buf);
		//过期时间
		this.lostTime = readInt(buf);
		//扩展属性
		int goodAttributes_length = readShort(buf);
		for (int i = 0; i < goodAttributes_length; i++) {
			goodAttributes.add((GoodsAttribute)readBean(buf, GoodsAttribute.class));
		}
		//参数，额外信息
		this.parameters = readString(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getItemId(){
		return itemId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setItemId(long itemId){
		this.itemId = itemId;
	}
	
	/**
	 * get 物品模板Id
	 * @return 
	 */
	public int getItemModelId(){
		return itemModelId;
	}
	
	/**
	 * set 物品模板Id
	 */
	public void setItemModelId(int itemModelId){
		this.itemModelId = itemModelId;
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
	 * get 角色所在格子Id
	 * @return 
	 */
	public int getGridId(){
		return gridId;
	}
	
	/**
	 * set 角色所在格子Id
	 */
	public void setGridId(int gridId){
		this.gridId = gridId;
	}
	
	/**
	 * get 是否绑定 1是 0否 
	 * @return 
	 */
	public byte getIsbind(){
		return isbind;
	}
	
	/**
	 * set 是否绑定 1是 0否 
	 */
	public void setIsbind(byte isbind){
		this.isbind = isbind;
	}
	
	/**
	 * get 强化等级
	 * @return 
	 */
	public byte getIntensify(){
		return intensify;
	}
	
	/**
	 * set 强化等级
	 */
	public void setIntensify(byte intensify){
		this.intensify = intensify;
	}
	
	/**
	 * get 扩展属性数量
	 * @return 
	 */
	public byte getAttributs(){
		return attributs;
	}
	
	/**
	 * set 扩展属性数量
	 */
	public void setAttributs(byte attributs){
		this.attributs = attributs;
	}
	
	/**
	 * get 是否顶级附加 1是 0否
	 * @return 
	 */
	public byte getIsFullAppend(){
		return isFullAppend;
	}
	
	/**
	 * set 是否顶级附加 1是 0否
	 */
	public void setIsFullAppend(byte isFullAppend){
		this.isFullAppend = isFullAppend;
	}
	
	/**
	 * get 过期时间
	 * @return 
	 */
	public int getLostTime(){
		return lostTime;
	}
	
	/**
	 * set 过期时间
	 */
	public void setLostTime(int lostTime){
		this.lostTime = lostTime;
	}
	
	/**
	 * get 扩展属性
	 * @return 
	 */
	public List<GoodsAttribute> getGoodAttributes(){
		return goodAttributes;
	}
	
	/**
	 * set 扩展属性
	 */
	public void setGoodAttributes(List<GoodsAttribute> goodAttributes){
		this.goodAttributes = goodAttributes;
	}
	
	/**
	 * get 参数，额外信息
	 * @return 
	 */
	public String getParameters(){
		return parameters;
	}
	
	/**
	 * set 参数，额外信息
	 */
	public void setParameters(String parameters){
		this.parameters = parameters;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色Id
		buf.append("itemId:" + itemId +",");
		//物品模板Id
		buf.append("itemModelId:" + itemModelId +",");
		//物品数量
		buf.append("num:" + num +",");
		//角色所在格子Id
		buf.append("gridId:" + gridId +",");
		//是否绑定 1是 0否 
		buf.append("isbind:" + isbind +",");
		//强化等级
		buf.append("intensify:" + intensify +",");
		//扩展属性数量
		buf.append("attributs:" + attributs +",");
		//是否顶级附加 1是 0否
		buf.append("isFullAppend:" + isFullAppend +",");
		//过期时间
		buf.append("lostTime:" + lostTime +",");
		//扩展属性
		buf.append("goodAttributes:{");
		for (int i = 0; i < goodAttributes.size(); i++) {
			buf.append(goodAttributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//参数，额外信息
		if(this.parameters!=null) buf.append("parameters:" + parameters.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
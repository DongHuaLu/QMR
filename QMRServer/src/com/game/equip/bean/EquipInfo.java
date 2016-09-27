package com.game.equip.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 装备信息类
 */
public class EquipInfo extends Bean {

	//角色Id
	private long itemId;
	
	//物品模板Id
	private int itemModelId;
	
	//物品强化等级
	private byte itemLevel;
	
	//物品附加属性
	private List<EquipAttribute> itemAttributes = new ArrayList<EquipAttribute>();
	//物品绑定
	private byte itemBind;
	
	//物品过期时间
	private long itemLosttime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.itemId);
		//物品模板Id
		writeInt(buf, this.itemModelId);
		//物品强化等级
		writeByte(buf, this.itemLevel);
		//物品附加属性
		writeShort(buf, itemAttributes.size());
		for (int i = 0; i < itemAttributes.size(); i++) {
			writeBean(buf, itemAttributes.get(i));
		}
		//物品绑定
		writeByte(buf, this.itemBind);
		//物品过期时间
		writeLong(buf, this.itemLosttime);
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
		//物品强化等级
		this.itemLevel = readByte(buf);
		//物品附加属性
		int itemAttributes_length = readShort(buf);
		for (int i = 0; i < itemAttributes_length; i++) {
			itemAttributes.add((EquipAttribute)readBean(buf, EquipAttribute.class));
		}
		//物品绑定
		this.itemBind = readByte(buf);
		//物品过期时间
		this.itemLosttime = readLong(buf);
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
	 * get 物品强化等级
	 * @return 
	 */
	public byte getItemLevel(){
		return itemLevel;
	}
	
	/**
	 * set 物品强化等级
	 */
	public void setItemLevel(byte itemLevel){
		this.itemLevel = itemLevel;
	}
	
	/**
	 * get 物品附加属性
	 * @return 
	 */
	public List<EquipAttribute> getItemAttributes(){
		return itemAttributes;
	}
	
	/**
	 * set 物品附加属性
	 */
	public void setItemAttributes(List<EquipAttribute> itemAttributes){
		this.itemAttributes = itemAttributes;
	}
	
	/**
	 * get 物品绑定
	 * @return 
	 */
	public byte getItemBind(){
		return itemBind;
	}
	
	/**
	 * set 物品绑定
	 */
	public void setItemBind(byte itemBind){
		this.itemBind = itemBind;
	}
	
	/**
	 * get 物品过期时间
	 * @return 
	 */
	public long getItemLosttime(){
		return itemLosttime;
	}
	
	/**
	 * set 物品过期时间
	 */
	public void setItemLosttime(long itemLosttime){
		this.itemLosttime = itemLosttime;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//角色Id
		buf.append("itemId:" + itemId +",");
		//物品模板Id
		buf.append("itemModelId:" + itemModelId +",");
		//物品强化等级
		buf.append("itemLevel:" + itemLevel +",");
		//物品附加属性
		buf.append("itemAttributes:{");
		for (int i = 0; i < itemAttributes.size(); i++) {
			buf.append(itemAttributes.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//物品绑定
		buf.append("itemBind:" + itemBind +",");
		//物品过期时间
		buf.append("itemLosttime:" + itemLosttime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
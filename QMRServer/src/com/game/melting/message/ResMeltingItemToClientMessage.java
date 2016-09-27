package com.game.melting.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送熔炼结果消息
 */
public class ResMeltingItemToClientMessage extends Message{

	//装备详细信息
	private com.game.backpack.bean.ItemInfo equipInfo;
	
	//替换的位置
	private byte idx;
	
	//替换的属性
	private com.game.backpack.bean.GoodsAttribute repattr;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//装备详细信息
		writeBean(buf, this.equipInfo);
		//替换的位置
		writeByte(buf, this.idx);
		//替换的属性
		writeBean(buf, this.repattr);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//装备详细信息
		this.equipInfo = (com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class);
		//替换的位置
		this.idx = readByte(buf);
		//替换的属性
		this.repattr = (com.game.backpack.bean.GoodsAttribute)readBean(buf, com.game.backpack.bean.GoodsAttribute.class);
		return true;
	}
	
	/**
	 * get 装备详细信息
	 * @return 
	 */
	public com.game.backpack.bean.ItemInfo getEquipInfo(){
		return equipInfo;
	}
	
	/**
	 * set 装备详细信息
	 */
	public void setEquipInfo(com.game.backpack.bean.ItemInfo equipInfo){
		this.equipInfo = equipInfo;
	}
	
	/**
	 * get 替换的位置
	 * @return 
	 */
	public byte getIdx(){
		return idx;
	}
	
	/**
	 * set 替换的位置
	 */
	public void setIdx(byte idx){
		this.idx = idx;
	}
	
	/**
	 * get 替换的属性
	 * @return 
	 */
	public com.game.backpack.bean.GoodsAttribute getRepattr(){
		return repattr;
	}
	
	/**
	 * set 替换的属性
	 */
	public void setRepattr(com.game.backpack.bean.GoodsAttribute repattr){
		this.repattr = repattr;
	}
	
	
	@Override
	public int getId() {
		return 154101;
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
		//装备详细信息
		if(this.equipInfo!=null) buf.append("equipInfo:" + equipInfo.toString() +",");
		//替换的位置
		buf.append("idx:" + idx +",");
		//替换的属性
		if(this.repattr!=null) buf.append("repattr:" + repattr.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
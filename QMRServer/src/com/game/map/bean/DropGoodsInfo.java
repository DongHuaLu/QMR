package com.game.map.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 掉落物品信息
 */
public class DropGoodsInfo extends Bean {

	//物品Id
	private long dropGoodsId;
	
	//物品模板Id
	private int itemModelId;
	
	//数量
	private int num;
	
	//所有者ID 无主为0
	private long ownerId;
	
	//掉落时间
	private long dropTime;
	
	//强化等级
	private byte intensify;
	
	//扩展属性数量
	private byte attributs;
	
	//是否顶级附加 1是 0否
	private byte isFullAppend;
	
	//是否满强化 1是 0否
	private byte isFullStrength;
	
	//坐标X
	private short x;
	
	//坐标Y
	private short y;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//物品Id
		writeLong(buf, this.dropGoodsId);
		//物品模板Id
		writeInt(buf, this.itemModelId);
		//数量
		writeInt(buf, this.num);
		//所有者ID 无主为0
		writeLong(buf, this.ownerId);
		//掉落时间
		writeLong(buf, this.dropTime);
		//强化等级
		writeByte(buf, this.intensify);
		//扩展属性数量
		writeByte(buf, this.attributs);
		//是否顶级附加 1是 0否
		writeByte(buf, this.isFullAppend);
		//是否满强化 1是 0否
		writeByte(buf, this.isFullStrength);
		//坐标X
		writeShort(buf, this.x);
		//坐标Y
		writeShort(buf, this.y);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//物品Id
		this.dropGoodsId = readLong(buf);
		//物品模板Id
		this.itemModelId = readInt(buf);
		//数量
		this.num = readInt(buf);
		//所有者ID 无主为0
		this.ownerId = readLong(buf);
		//掉落时间
		this.dropTime = readLong(buf);
		//强化等级
		this.intensify = readByte(buf);
		//扩展属性数量
		this.attributs = readByte(buf);
		//是否顶级附加 1是 0否
		this.isFullAppend = readByte(buf);
		//是否满强化 1是 0否
		this.isFullStrength = readByte(buf);
		//坐标X
		this.x = readShort(buf);
		//坐标Y
		this.y = readShort(buf);
		return true;
	}
	
	/**
	 * get 物品Id
	 * @return 
	 */
	public long getDropGoodsId(){
		return dropGoodsId;
	}
	
	/**
	 * set 物品Id
	 */
	public void setDropGoodsId(long dropGoodsId){
		this.dropGoodsId = dropGoodsId;
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
	 * get 数量
	 * @return 
	 */
	public int getNum(){
		return num;
	}
	
	/**
	 * set 数量
	 */
	public void setNum(int num){
		this.num = num;
	}
	
	/**
	 * get 所有者ID 无主为0
	 * @return 
	 */
	public long getOwnerId(){
		return ownerId;
	}
	
	/**
	 * set 所有者ID 无主为0
	 */
	public void setOwnerId(long ownerId){
		this.ownerId = ownerId;
	}
	
	/**
	 * get 掉落时间
	 * @return 
	 */
	public long getDropTime(){
		return dropTime;
	}
	
	/**
	 * set 掉落时间
	 */
	public void setDropTime(long dropTime){
		this.dropTime = dropTime;
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
	 * get 是否满强化 1是 0否
	 * @return 
	 */
	public byte getIsFullStrength(){
		return isFullStrength;
	}
	
	/**
	 * set 是否满强化 1是 0否
	 */
	public void setIsFullStrength(byte isFullStrength){
		this.isFullStrength = isFullStrength;
	}
	
	/**
	 * get 坐标X
	 * @return 
	 */
	public short getX(){
		return x;
	}
	
	/**
	 * set 坐标X
	 */
	public void setX(short x){
		this.x = x;
	}
	
	/**
	 * get 坐标Y
	 * @return 
	 */
	public short getY(){
		return y;
	}
	
	/**
	 * set 坐标Y
	 */
	public void setY(short y){
		this.y = y;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//物品Id
		buf.append("dropGoodsId:" + dropGoodsId +",");
		//物品模板Id
		buf.append("itemModelId:" + itemModelId +",");
		//数量
		buf.append("num:" + num +",");
		//所有者ID 无主为0
		buf.append("ownerId:" + ownerId +",");
		//掉落时间
		buf.append("dropTime:" + dropTime +",");
		//强化等级
		buf.append("intensify:" + intensify +",");
		//扩展属性数量
		buf.append("attributs:" + attributs +",");
		//是否顶级附加 1是 0否
		buf.append("isFullAppend:" + isFullAppend +",");
		//是否满强化 1是 0否
		buf.append("isFullStrength:" + isFullStrength +",");
		//坐标X
		buf.append("x:" + x +",");
		//坐标Y
		buf.append("y:" + y +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
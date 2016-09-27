package com.game.horse.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送坐骑升阶结果消息
 */
public class ReshorseStageUpResultMessage extends Message{

	//升阶结果，0失败，1成功
	private byte type;
	
	//失败后更新当前祝福值
	private int dayblessvalue;
	
	//失败后是否暴击经验，0正常，1小暴击，2大暴击
	private byte crit;
	
	//失败后加的exp
	private int exp;
	
	//进阶使用道具模组ID
	private int itemmodelid;
	
	//进阶使用道具数量
	private int itemnum;
	
	//铜币数量
	private int money;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//升阶结果，0失败，1成功
		writeByte(buf, this.type);
		//失败后更新当前祝福值
		writeInt(buf, this.dayblessvalue);
		//失败后是否暴击经验，0正常，1小暴击，2大暴击
		writeByte(buf, this.crit);
		//失败后加的exp
		writeInt(buf, this.exp);
		//进阶使用道具模组ID
		writeInt(buf, this.itemmodelid);
		//进阶使用道具数量
		writeInt(buf, this.itemnum);
		//铜币数量
		writeInt(buf, this.money);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//升阶结果，0失败，1成功
		this.type = readByte(buf);
		//失败后更新当前祝福值
		this.dayblessvalue = readInt(buf);
		//失败后是否暴击经验，0正常，1小暴击，2大暴击
		this.crit = readByte(buf);
		//失败后加的exp
		this.exp = readInt(buf);
		//进阶使用道具模组ID
		this.itemmodelid = readInt(buf);
		//进阶使用道具数量
		this.itemnum = readInt(buf);
		//铜币数量
		this.money = readInt(buf);
		return true;
	}
	
	/**
	 * get 升阶结果，0失败，1成功
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 升阶结果，0失败，1成功
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 失败后更新当前祝福值
	 * @return 
	 */
	public int getDayblessvalue(){
		return dayblessvalue;
	}
	
	/**
	 * set 失败后更新当前祝福值
	 */
	public void setDayblessvalue(int dayblessvalue){
		this.dayblessvalue = dayblessvalue;
	}
	
	/**
	 * get 失败后是否暴击经验，0正常，1小暴击，2大暴击
	 * @return 
	 */
	public byte getCrit(){
		return crit;
	}
	
	/**
	 * set 失败后是否暴击经验，0正常，1小暴击，2大暴击
	 */
	public void setCrit(byte crit){
		this.crit = crit;
	}
	
	/**
	 * get 失败后加的exp
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 失败后加的exp
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	/**
	 * get 进阶使用道具模组ID
	 * @return 
	 */
	public int getItemmodelid(){
		return itemmodelid;
	}
	
	/**
	 * set 进阶使用道具模组ID
	 */
	public void setItemmodelid(int itemmodelid){
		this.itemmodelid = itemmodelid;
	}
	
	/**
	 * get 进阶使用道具数量
	 * @return 
	 */
	public int getItemnum(){
		return itemnum;
	}
	
	/**
	 * set 进阶使用道具数量
	 */
	public void setItemnum(int itemnum){
		this.itemnum = itemnum;
	}
	
	/**
	 * get 铜币数量
	 * @return 
	 */
	public int getMoney(){
		return money;
	}
	
	/**
	 * set 铜币数量
	 */
	public void setMoney(int money){
		this.money = money;
	}
	
	
	@Override
	public int getId() {
		return 126105;
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
		//升阶结果，0失败，1成功
		buf.append("type:" + type +",");
		//失败后更新当前祝福值
		buf.append("dayblessvalue:" + dayblessvalue +",");
		//失败后是否暴击经验，0正常，1小暴击，2大暴击
		buf.append("crit:" + crit +",");
		//失败后加的exp
		buf.append("exp:" + exp +",");
		//进阶使用道具模组ID
		buf.append("itemmodelid:" + itemmodelid +",");
		//进阶使用道具数量
		buf.append("itemnum:" + itemnum +",");
		//铜币数量
		buf.append("money:" + money +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
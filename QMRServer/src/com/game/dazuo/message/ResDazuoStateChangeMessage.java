package com.game.dazuo.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 打座状态变化 发给自己消息
 */
public class ResDazuoStateChangeMessage extends Message{

	//0未打座  1单人 1与宠物双修 2与玩家双修
	private byte state;
	
	//双修对象ID
	private long otherid;
	
	//双修对象坐标X
	private short otherx;
	
	//双修对象坐标Y
	private short othery;
	
	//打座开始时间
	private long startTime;
	
	//暴击次数
	private int eruptCount;
	
	//暴击获得经验
	private int eruptExp;
	
	//暴击获得真气
	private int eruptZQ;
	
	//自己出战的宠物列表
	private List<Long> onwerPets = new ArrayList<Long>();
	//自己出战的宠物列表
	private List<Long> otherPets = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//0未打座  1单人 1与宠物双修 2与玩家双修
		writeByte(buf, this.state);
		//双修对象ID
		writeLong(buf, this.otherid);
		//双修对象坐标X
		writeShort(buf, this.otherx);
		//双修对象坐标Y
		writeShort(buf, this.othery);
		//打座开始时间
		writeLong(buf, this.startTime);
		//暴击次数
		writeInt(buf, this.eruptCount);
		//暴击获得经验
		writeInt(buf, this.eruptExp);
		//暴击获得真气
		writeInt(buf, this.eruptZQ);
		//自己出战的宠物列表
		writeShort(buf, onwerPets.size());
		for (int i = 0; i < onwerPets.size(); i++) {
			writeLong(buf, onwerPets.get(i));
		}
		//自己出战的宠物列表
		writeShort(buf, otherPets.size());
		for (int i = 0; i < otherPets.size(); i++) {
			writeLong(buf, otherPets.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//0未打座  1单人 1与宠物双修 2与玩家双修
		this.state = readByte(buf);
		//双修对象ID
		this.otherid = readLong(buf);
		//双修对象坐标X
		this.otherx = readShort(buf);
		//双修对象坐标Y
		this.othery = readShort(buf);
		//打座开始时间
		this.startTime = readLong(buf);
		//暴击次数
		this.eruptCount = readInt(buf);
		//暴击获得经验
		this.eruptExp = readInt(buf);
		//暴击获得真气
		this.eruptZQ = readInt(buf);
		//自己出战的宠物列表
		int onwerPets_length = readShort(buf);
		for (int i = 0; i < onwerPets_length; i++) {
			onwerPets.add(readLong(buf));
		}
		//自己出战的宠物列表
		int otherPets_length = readShort(buf);
		for (int i = 0; i < otherPets_length; i++) {
			otherPets.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 0未打座  1单人 1与宠物双修 2与玩家双修
	 * @return 
	 */
	public byte getState(){
		return state;
	}
	
	/**
	 * set 0未打座  1单人 1与宠物双修 2与玩家双修
	 */
	public void setState(byte state){
		this.state = state;
	}
	
	/**
	 * get 双修对象ID
	 * @return 
	 */
	public long getOtherid(){
		return otherid;
	}
	
	/**
	 * set 双修对象ID
	 */
	public void setOtherid(long otherid){
		this.otherid = otherid;
	}
	
	/**
	 * get 双修对象坐标X
	 * @return 
	 */
	public short getOtherx(){
		return otherx;
	}
	
	/**
	 * set 双修对象坐标X
	 */
	public void setOtherx(short otherx){
		this.otherx = otherx;
	}
	
	/**
	 * get 双修对象坐标Y
	 * @return 
	 */
	public short getOthery(){
		return othery;
	}
	
	/**
	 * set 双修对象坐标Y
	 */
	public void setOthery(short othery){
		this.othery = othery;
	}
	
	/**
	 * get 打座开始时间
	 * @return 
	 */
	public long getStartTime(){
		return startTime;
	}
	
	/**
	 * set 打座开始时间
	 */
	public void setStartTime(long startTime){
		this.startTime = startTime;
	}
	
	/**
	 * get 暴击次数
	 * @return 
	 */
	public int getEruptCount(){
		return eruptCount;
	}
	
	/**
	 * set 暴击次数
	 */
	public void setEruptCount(int eruptCount){
		this.eruptCount = eruptCount;
	}
	
	/**
	 * get 暴击获得经验
	 * @return 
	 */
	public int getEruptExp(){
		return eruptExp;
	}
	
	/**
	 * set 暴击获得经验
	 */
	public void setEruptExp(int eruptExp){
		this.eruptExp = eruptExp;
	}
	
	/**
	 * get 暴击获得真气
	 * @return 
	 */
	public int getEruptZQ(){
		return eruptZQ;
	}
	
	/**
	 * set 暴击获得真气
	 */
	public void setEruptZQ(int eruptZQ){
		this.eruptZQ = eruptZQ;
	}
	
	/**
	 * get 自己出战的宠物列表
	 * @return 
	 */
	public List<Long> getOnwerPets(){
		return onwerPets;
	}
	
	/**
	 * set 自己出战的宠物列表
	 */
	public void setOnwerPets(List<Long> onwerPets){
		this.onwerPets = onwerPets;
	}
	
	/**
	 * get 自己出战的宠物列表
	 * @return 
	 */
	public List<Long> getOtherPets(){
		return otherPets;
	}
	
	/**
	 * set 自己出战的宠物列表
	 */
	public void setOtherPets(List<Long> otherPets){
		this.otherPets = otherPets;
	}
	
	
	@Override
	public int getId() {
		return 136101;
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
		//0未打座  1单人 1与宠物双修 2与玩家双修
		buf.append("state:" + state +",");
		//双修对象ID
		buf.append("otherid:" + otherid +",");
		//双修对象坐标X
		buf.append("otherx:" + otherx +",");
		//双修对象坐标Y
		buf.append("othery:" + othery +",");
		//打座开始时间
		buf.append("startTime:" + startTime +",");
		//暴击次数
		buf.append("eruptCount:" + eruptCount +",");
		//暴击获得经验
		buf.append("eruptExp:" + eruptExp +",");
		//暴击获得真气
		buf.append("eruptZQ:" + eruptZQ +",");
		//自己出战的宠物列表
		buf.append("onwerPets:{");
		for (int i = 0; i < onwerPets.size(); i++) {
			buf.append(onwerPets.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//自己出战的宠物列表
		buf.append("otherPets:{");
		for (int i = 0; i < otherPets.size(); i++) {
			buf.append(otherPets.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
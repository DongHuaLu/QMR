package com.game.pet.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 合体结果消息
 */
public class ResPetHeTiResultMessage extends Message{

	//宠物Id
	private long petId;
	
	//合体次数
	private int htCount;
	
	//今日合体次数
	private int dayCount;
	
	//合体冷确时间
	private int htCoolDownTime;
	
	//1成功  0失败
	private byte isSuccess;
	
	//合体加成
	private List<com.game.player.bean.PlayerAttributeItem> htAddition = new ArrayList<com.game.player.bean.PlayerAttributeItem>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//宠物Id
		writeLong(buf, this.petId);
		//合体次数
		writeInt(buf, this.htCount);
		//今日合体次数
		writeInt(buf, this.dayCount);
		//合体冷确时间
		writeInt(buf, this.htCoolDownTime);
		//1成功  0失败
		writeByte(buf, this.isSuccess);
		//合体加成
		writeShort(buf, htAddition.size());
		for (int i = 0; i < htAddition.size(); i++) {
			writeBean(buf, htAddition.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//宠物Id
		this.petId = readLong(buf);
		//合体次数
		this.htCount = readInt(buf);
		//今日合体次数
		this.dayCount = readInt(buf);
		//合体冷确时间
		this.htCoolDownTime = readInt(buf);
		//1成功  0失败
		this.isSuccess = readByte(buf);
		//合体加成
		int htAddition_length = readShort(buf);
		for (int i = 0; i < htAddition_length; i++) {
			htAddition.add((com.game.player.bean.PlayerAttributeItem)readBean(buf, com.game.player.bean.PlayerAttributeItem.class));
		}
		return true;
	}
	
	/**
	 * get 宠物Id
	 * @return 
	 */
	public long getPetId(){
		return petId;
	}
	
	/**
	 * set 宠物Id
	 */
	public void setPetId(long petId){
		this.petId = petId;
	}
	
	/**
	 * get 合体次数
	 * @return 
	 */
	public int getHtCount(){
		return htCount;
	}
	
	/**
	 * set 合体次数
	 */
	public void setHtCount(int htCount){
		this.htCount = htCount;
	}
	
	/**
	 * get 今日合体次数
	 * @return 
	 */
	public int getDayCount(){
		return dayCount;
	}
	
	/**
	 * set 今日合体次数
	 */
	public void setDayCount(int dayCount){
		this.dayCount = dayCount;
	}
	
	/**
	 * get 合体冷确时间
	 * @return 
	 */
	public int getHtCoolDownTime(){
		return htCoolDownTime;
	}
	
	/**
	 * set 合体冷确时间
	 */
	public void setHtCoolDownTime(int htCoolDownTime){
		this.htCoolDownTime = htCoolDownTime;
	}
	
	/**
	 * get 1成功  0失败
	 * @return 
	 */
	public byte getIsSuccess(){
		return isSuccess;
	}
	
	/**
	 * set 1成功  0失败
	 */
	public void setIsSuccess(byte isSuccess){
		this.isSuccess = isSuccess;
	}
	
	/**
	 * get 合体加成
	 * @return 
	 */
	public List<com.game.player.bean.PlayerAttributeItem> getHtAddition(){
		return htAddition;
	}
	
	/**
	 * set 合体加成
	 */
	public void setHtAddition(List<com.game.player.bean.PlayerAttributeItem> htAddition){
		this.htAddition = htAddition;
	}
	
	
	@Override
	public int getId() {
		return 110104;
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
		//宠物Id
		buf.append("petId:" + petId +",");
		//合体次数
		buf.append("htCount:" + htCount +",");
		//今日合体次数
		buf.append("dayCount:" + dayCount +",");
		//合体冷确时间
		buf.append("htCoolDownTime:" + htCoolDownTime +",");
		//1成功  0失败
		buf.append("isSuccess:" + isSuccess +",");
		//合体加成
		buf.append("htAddition:{");
		for (int i = 0; i < htAddition.size(); i++) {
			buf.append(htAddition.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
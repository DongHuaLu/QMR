package com.game.zones.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 副本通关奖励展示（已经打乱的）消息
 */
public class ResZonePassShowMessage extends Message{

	//道具奖励列表
	private List<com.game.backpack.bean.ItemInfo> itemlist = new ArrayList<com.game.backpack.bean.ItemInfo>();
	//类型:0手动，1自动扫荡
	private byte type;
	
	//副本编号
	private int zoneid;
	
	//死亡次数
	private int deathnum;
	
	//通关时间
	private int time;
	
	//杀怪数量
	private int killmonstrnum;
	
	//最快通关时间（时间秒）
	private int throughtime;
	
	//是否第一次，0不是，1是第一次
	private int isfirst;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//道具奖励列表
		writeShort(buf, itemlist.size());
		for (int i = 0; i < itemlist.size(); i++) {
			writeBean(buf, itemlist.get(i));
		}
		//类型:0手动，1自动扫荡
		writeByte(buf, this.type);
		//副本编号
		writeInt(buf, this.zoneid);
		//死亡次数
		writeInt(buf, this.deathnum);
		//通关时间
		writeInt(buf, this.time);
		//杀怪数量
		writeInt(buf, this.killmonstrnum);
		//最快通关时间（时间秒）
		writeInt(buf, this.throughtime);
		//是否第一次，0不是，1是第一次
		writeInt(buf, this.isfirst);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//道具奖励列表
		int itemlist_length = readShort(buf);
		for (int i = 0; i < itemlist_length; i++) {
			itemlist.add((com.game.backpack.bean.ItemInfo)readBean(buf, com.game.backpack.bean.ItemInfo.class));
		}
		//类型:0手动，1自动扫荡
		this.type = readByte(buf);
		//副本编号
		this.zoneid = readInt(buf);
		//死亡次数
		this.deathnum = readInt(buf);
		//通关时间
		this.time = readInt(buf);
		//杀怪数量
		this.killmonstrnum = readInt(buf);
		//最快通关时间（时间秒）
		this.throughtime = readInt(buf);
		//是否第一次，0不是，1是第一次
		this.isfirst = readInt(buf);
		return true;
	}
	
	/**
	 * get 道具奖励列表
	 * @return 
	 */
	public List<com.game.backpack.bean.ItemInfo> getItemlist(){
		return itemlist;
	}
	
	/**
	 * set 道具奖励列表
	 */
	public void setItemlist(List<com.game.backpack.bean.ItemInfo> itemlist){
		this.itemlist = itemlist;
	}
	
	/**
	 * get 类型:0手动，1自动扫荡
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型:0手动，1自动扫荡
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 副本编号
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本编号
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	/**
	 * get 死亡次数
	 * @return 
	 */
	public int getDeathnum(){
		return deathnum;
	}
	
	/**
	 * set 死亡次数
	 */
	public void setDeathnum(int deathnum){
		this.deathnum = deathnum;
	}
	
	/**
	 * get 通关时间
	 * @return 
	 */
	public int getTime(){
		return time;
	}
	
	/**
	 * set 通关时间
	 */
	public void setTime(int time){
		this.time = time;
	}
	
	/**
	 * get 杀怪数量
	 * @return 
	 */
	public int getKillmonstrnum(){
		return killmonstrnum;
	}
	
	/**
	 * set 杀怪数量
	 */
	public void setKillmonstrnum(int killmonstrnum){
		this.killmonstrnum = killmonstrnum;
	}
	
	/**
	 * get 最快通关时间（时间秒）
	 * @return 
	 */
	public int getThroughtime(){
		return throughtime;
	}
	
	/**
	 * set 最快通关时间（时间秒）
	 */
	public void setThroughtime(int throughtime){
		this.throughtime = throughtime;
	}
	
	/**
	 * get 是否第一次，0不是，1是第一次
	 * @return 
	 */
	public int getIsfirst(){
		return isfirst;
	}
	
	/**
	 * set 是否第一次，0不是，1是第一次
	 */
	public void setIsfirst(int isfirst){
		this.isfirst = isfirst;
	}
	
	
	@Override
	public int getId() {
		return 128103;
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
		//道具奖励列表
		buf.append("itemlist:{");
		for (int i = 0; i < itemlist.size(); i++) {
			buf.append(itemlist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//类型:0手动，1自动扫荡
		buf.append("type:" + type +",");
		//副本编号
		buf.append("zoneid:" + zoneid +",");
		//死亡次数
		buf.append("deathnum:" + deathnum +",");
		//通关时间
		buf.append("time:" + time +",");
		//杀怪数量
		buf.append("killmonstrnum:" + killmonstrnum +",");
		//最快通关时间（时间秒）
		buf.append("throughtime:" + throughtime +",");
		//是否第一次，0不是，1是第一次
		buf.append("isfirst:" + isfirst +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.spirittree.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 果实信息
 */
public class FruitInfo extends Bean {

	//果实ID
	private long fruitid;
	
	//果实类型：0普通果实，1银色奇异果，2金色奇异果
	private byte type;
	
	//果实成熟剩余时间(秒)，-1表示成熟
	private int timeleft;
	
	//是否干旱，0否，1是
	private byte isarid;
	
	//组包ID列表
	private List<Integer> groupidlist = new ArrayList<Integer>();
	//果实奖励
	private List<FruitRewardinfo> fruitrewardinfo = new ArrayList<FruitRewardinfo>();
	//剩余产量
	private int yield;
	
	//果实主人ID
	private long hostid;
	
	//果实主人名字
	private String hostname;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//果实ID
		writeLong(buf, this.fruitid);
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		writeByte(buf, this.type);
		//果实成熟剩余时间(秒)，-1表示成熟
		writeInt(buf, this.timeleft);
		//是否干旱，0否，1是
		writeByte(buf, this.isarid);
		//组包ID列表
		writeShort(buf, groupidlist.size());
		for (int i = 0; i < groupidlist.size(); i++) {
			writeInt(buf, groupidlist.get(i));
		}
		//果实奖励
		writeShort(buf, fruitrewardinfo.size());
		for (int i = 0; i < fruitrewardinfo.size(); i++) {
			writeBean(buf, fruitrewardinfo.get(i));
		}
		//剩余产量
		writeInt(buf, this.yield);
		//果实主人ID
		writeLong(buf, this.hostid);
		//果实主人名字
		writeString(buf, this.hostname);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//果实ID
		this.fruitid = readLong(buf);
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		this.type = readByte(buf);
		//果实成熟剩余时间(秒)，-1表示成熟
		this.timeleft = readInt(buf);
		//是否干旱，0否，1是
		this.isarid = readByte(buf);
		//组包ID列表
		int groupidlist_length = readShort(buf);
		for (int i = 0; i < groupidlist_length; i++) {
			groupidlist.add(readInt(buf));
		}
		//果实奖励
		int fruitrewardinfo_length = readShort(buf);
		for (int i = 0; i < fruitrewardinfo_length; i++) {
			fruitrewardinfo.add((FruitRewardinfo)readBean(buf, FruitRewardinfo.class));
		}
		//剩余产量
		this.yield = readInt(buf);
		//果实主人ID
		this.hostid = readLong(buf);
		//果实主人名字
		this.hostname = readString(buf);
		return true;
	}
	
	/**
	 * get 果实ID
	 * @return 
	 */
	public long getFruitid(){
		return fruitid;
	}
	
	/**
	 * set 果实ID
	 */
	public void setFruitid(long fruitid){
		this.fruitid = fruitid;
	}
	
	/**
	 * get 果实类型：0普通果实，1银色奇异果，2金色奇异果
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 果实类型：0普通果实，1银色奇异果，2金色奇异果
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 果实成熟剩余时间(秒)，-1表示成熟
	 * @return 
	 */
	public int getTimeleft(){
		return timeleft;
	}
	
	/**
	 * set 果实成熟剩余时间(秒)，-1表示成熟
	 */
	public void setTimeleft(int timeleft){
		this.timeleft = timeleft;
	}
	
	/**
	 * get 是否干旱，0否，1是
	 * @return 
	 */
	public byte getIsarid(){
		return isarid;
	}
	
	/**
	 * set 是否干旱，0否，1是
	 */
	public void setIsarid(byte isarid){
		this.isarid = isarid;
	}
	
	/**
	 * get 组包ID列表
	 * @return 
	 */
	public List<Integer> getGroupidlist(){
		return groupidlist;
	}
	
	/**
	 * set 组包ID列表
	 */
	public void setGroupidlist(List<Integer> groupidlist){
		this.groupidlist = groupidlist;
	}
	
	/**
	 * get 果实奖励
	 * @return 
	 */
	public List<FruitRewardinfo> getFruitrewardinfo(){
		return fruitrewardinfo;
	}
	
	/**
	 * set 果实奖励
	 */
	public void setFruitrewardinfo(List<FruitRewardinfo> fruitrewardinfo){
		this.fruitrewardinfo = fruitrewardinfo;
	}
	
	/**
	 * get 剩余产量
	 * @return 
	 */
	public int getYield(){
		return yield;
	}
	
	/**
	 * set 剩余产量
	 */
	public void setYield(int yield){
		this.yield = yield;
	}
	
	/**
	 * get 果实主人ID
	 * @return 
	 */
	public long getHostid(){
		return hostid;
	}
	
	/**
	 * set 果实主人ID
	 */
	public void setHostid(long hostid){
		this.hostid = hostid;
	}
	
	/**
	 * get 果实主人名字
	 * @return 
	 */
	public String getHostname(){
		return hostname;
	}
	
	/**
	 * set 果实主人名字
	 */
	public void setHostname(String hostname){
		this.hostname = hostname;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//果实ID
		buf.append("fruitid:" + fruitid +",");
		//果实类型：0普通果实，1银色奇异果，2金色奇异果
		buf.append("type:" + type +",");
		//果实成熟剩余时间(秒)，-1表示成熟
		buf.append("timeleft:" + timeleft +",");
		//是否干旱，0否，1是
		buf.append("isarid:" + isarid +",");
		//组包ID列表
		buf.append("groupidlist:{");
		for (int i = 0; i < groupidlist.size(); i++) {
			buf.append(groupidlist.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//果实奖励
		buf.append("fruitrewardinfo:{");
		for (int i = 0; i < fruitrewardinfo.size(); i++) {
			buf.append(fruitrewardinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//剩余产量
		buf.append("yield:" + yield +",");
		//果实主人ID
		buf.append("hostid:" + hostid +",");
		//果实主人名字
		if(this.hostname!=null) buf.append("hostname:" + hostname.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.zones.message;

import com.game.zones.bean.ZoneMonstrInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 副本内过关计时器（进入副本后开始）消息
 */
public class ResZoneTimerMessage extends Message{

	//副本编号
	private int zoneid;
	
	//怪物总数
	private int monstrssum;
	
	//当前怪物数量
	private int monstrsnum;
	
	//死亡次数
	private int deathnum;
	
	//剩余时间
	private int surplustime;
	
	//面板开关，0结束时关闭，1开启
	private byte status;
	
	//副本怪物信息列表
	private List<ZoneMonstrInfo> zoenmonstrinfolist = new ArrayList<ZoneMonstrInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//副本编号
		writeInt(buf, this.zoneid);
		//怪物总数
		writeInt(buf, this.monstrssum);
		//当前怪物数量
		writeInt(buf, this.monstrsnum);
		//死亡次数
		writeInt(buf, this.deathnum);
		//剩余时间
		writeInt(buf, this.surplustime);
		//面板开关，0结束时关闭，1开启
		writeByte(buf, this.status);
		//副本怪物信息列表
		writeShort(buf, zoenmonstrinfolist.size());
		for (int i = 0; i < zoenmonstrinfolist.size(); i++) {
			writeBean(buf, zoenmonstrinfolist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//副本编号
		this.zoneid = readInt(buf);
		//怪物总数
		this.monstrssum = readInt(buf);
		//当前怪物数量
		this.monstrsnum = readInt(buf);
		//死亡次数
		this.deathnum = readInt(buf);
		//剩余时间
		this.surplustime = readInt(buf);
		//面板开关，0结束时关闭，1开启
		this.status = readByte(buf);
		//副本怪物信息列表
		int zoenmonstrinfolist_length = readShort(buf);
		for (int i = 0; i < zoenmonstrinfolist_length; i++) {
			zoenmonstrinfolist.add((ZoneMonstrInfo)readBean(buf, ZoneMonstrInfo.class));
		}
		return true;
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
	 * get 怪物总数
	 * @return 
	 */
	public int getMonstrssum(){
		return monstrssum;
	}
	
	/**
	 * set 怪物总数
	 */
	public void setMonstrssum(int monstrssum){
		this.monstrssum = monstrssum;
	}
	
	/**
	 * get 当前怪物数量
	 * @return 
	 */
	public int getMonstrsnum(){
		return monstrsnum;
	}
	
	/**
	 * set 当前怪物数量
	 */
	public void setMonstrsnum(int monstrsnum){
		this.monstrsnum = monstrsnum;
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
	 * get 剩余时间
	 * @return 
	 */
	public int getSurplustime(){
		return surplustime;
	}
	
	/**
	 * set 剩余时间
	 */
	public void setSurplustime(int surplustime){
		this.surplustime = surplustime;
	}
	
	/**
	 * get 面板开关，0结束时关闭，1开启
	 * @return 
	 */
	public byte getStatus(){
		return status;
	}
	
	/**
	 * set 面板开关，0结束时关闭，1开启
	 */
	public void setStatus(byte status){
		this.status = status;
	}
	
	/**
	 * get 副本怪物信息列表
	 * @return 
	 */
	public List<ZoneMonstrInfo> getZoenmonstrinfolist(){
		return zoenmonstrinfolist;
	}
	
	/**
	 * set 副本怪物信息列表
	 */
	public void setZoenmonstrinfolist(List<ZoneMonstrInfo> zoenmonstrinfolist){
		this.zoenmonstrinfolist = zoenmonstrinfolist;
	}
	
	
	@Override
	public int getId() {
		return 128102;
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
		//副本编号
		buf.append("zoneid:" + zoneid +",");
		//怪物总数
		buf.append("monstrssum:" + monstrssum +",");
		//当前怪物数量
		buf.append("monstrsnum:" + monstrsnum +",");
		//死亡次数
		buf.append("deathnum:" + deathnum +",");
		//剩余时间
		buf.append("surplustime:" + surplustime +",");
		//面板开关，0结束时关闭，1开启
		buf.append("status:" + status +",");
		//副本怪物信息列表
		buf.append("zoenmonstrinfolist:{");
		for (int i = 0; i < zoenmonstrinfolist.size(); i++) {
			buf.append(zoenmonstrinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
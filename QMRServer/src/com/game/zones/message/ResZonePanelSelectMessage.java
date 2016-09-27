package com.game.zones.message;

import com.game.zones.bean.RaidZoneInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回当前副本信息消息
 */
public class ResZonePanelSelectMessage extends Message{

	//返回当前副本信息
	private RaidZoneInfo raidzoneinfolist;
	
	//手动可领取状态，1有奖励，0没有
	private int manualstatus;
	
	//自动扫荡可领取状态，1有奖励，0没有
	private int autostatus;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//返回当前副本信息
		writeBean(buf, this.raidzoneinfolist);
		//手动可领取状态，1有奖励，0没有
		writeInt(buf, this.manualstatus);
		//自动扫荡可领取状态，1有奖励，0没有
		writeInt(buf, this.autostatus);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//返回当前副本信息
		this.raidzoneinfolist = (RaidZoneInfo)readBean(buf, RaidZoneInfo.class);
		//手动可领取状态，1有奖励，0没有
		this.manualstatus = readInt(buf);
		//自动扫荡可领取状态，1有奖励，0没有
		this.autostatus = readInt(buf);
		return true;
	}
	
	/**
	 * get 返回当前副本信息
	 * @return 
	 */
	public RaidZoneInfo getRaidzoneinfolist(){
		return raidzoneinfolist;
	}
	
	/**
	 * set 返回当前副本信息
	 */
	public void setRaidzoneinfolist(RaidZoneInfo raidzoneinfolist){
		this.raidzoneinfolist = raidzoneinfolist;
	}
	
	/**
	 * get 手动可领取状态，1有奖励，0没有
	 * @return 
	 */
	public int getManualstatus(){
		return manualstatus;
	}
	
	/**
	 * set 手动可领取状态，1有奖励，0没有
	 */
	public void setManualstatus(int manualstatus){
		this.manualstatus = manualstatus;
	}
	
	/**
	 * get 自动扫荡可领取状态，1有奖励，0没有
	 * @return 
	 */
	public int getAutostatus(){
		return autostatus;
	}
	
	/**
	 * set 自动扫荡可领取状态，1有奖励，0没有
	 */
	public void setAutostatus(int autostatus){
		this.autostatus = autostatus;
	}
	
	
	@Override
	public int getId() {
		return 128101;
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
		//返回当前副本信息
		if(this.raidzoneinfolist!=null) buf.append("raidzoneinfolist:" + raidzoneinfolist.toString() +",");
		//手动可领取状态，1有奖励，0没有
		buf.append("manualstatus:" + manualstatus +",");
		//自动扫荡可领取状态，1有奖励，0没有
		buf.append("autostatus:" + autostatus +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
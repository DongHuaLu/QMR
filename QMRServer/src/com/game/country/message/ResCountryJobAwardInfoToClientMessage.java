package com.game.country.message;

import com.game.country.bean.JobAwardInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 王城职位领奖消息消息
 */
public class ResCountryJobAwardInfoToClientMessage extends Message{

	//职位奖励领取情况
	private List<JobAwardInfo> damageinfo = new ArrayList<JobAwardInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//职位奖励领取情况
		writeShort(buf, damageinfo.size());
		for (int i = 0; i < damageinfo.size(); i++) {
			writeBean(buf, damageinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//职位奖励领取情况
		int damageinfo_length = readShort(buf);
		for (int i = 0; i < damageinfo_length; i++) {
			damageinfo.add((JobAwardInfo)readBean(buf, JobAwardInfo.class));
		}
		return true;
	}
	
	/**
	 * get 职位奖励领取情况
	 * @return 
	 */
	public List<JobAwardInfo> getDamageinfo(){
		return damageinfo;
	}
	
	/**
	 * set 职位奖励领取情况
	 */
	public void setDamageinfo(List<JobAwardInfo> damageinfo){
		this.damageinfo = damageinfo;
	}
	
	
	@Override
	public int getId() {
		return 146113;
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
		//职位奖励领取情况
		buf.append("damageinfo:{");
		for (int i = 0; i < damageinfo.size(); i++) {
			buf.append(damageinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
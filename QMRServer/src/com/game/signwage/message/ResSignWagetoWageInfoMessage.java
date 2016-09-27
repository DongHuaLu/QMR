package com.game.signwage.message;

import com.game.signwage.bean.WageInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 工资消息消息
 */
public class ResSignWagetoWageInfoMessage extends Message{

	//工资消息
	private WageInfo wageInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//工资消息
		writeBean(buf, this.wageInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//工资消息
		this.wageInfo = (WageInfo)readBean(buf, WageInfo.class);
		return true;
	}
	
	/**
	 * get 工资消息
	 * @return 
	 */
	public WageInfo getWageInfo(){
		return wageInfo;
	}
	
	/**
	 * set 工资消息
	 */
	public void setWageInfo(WageInfo wageInfo){
		this.wageInfo = wageInfo;
	}
	
	
	@Override
	public int getId() {
		return 152102;
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
		//工资消息
		if(this.wageInfo!=null) buf.append("wageInfo:" + wageInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
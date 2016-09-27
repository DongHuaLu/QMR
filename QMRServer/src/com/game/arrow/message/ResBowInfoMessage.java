package com.game.arrow.message;

import com.game.arrow.bean.BowInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回箭支信息消息
 */
public class ResBowInfoMessage extends Message{

	//通知类型
	private int notifytype;
	
	//箭支信息
	private BowInfo bowinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知类型
		writeInt(buf, this.notifytype);
		//箭支信息
		writeBean(buf, this.bowinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知类型
		this.notifytype = readInt(buf);
		//箭支信息
		this.bowinfo = (BowInfo)readBean(buf, BowInfo.class);
		return true;
	}
	
	/**
	 * get 通知类型
	 * @return 
	 */
	public int getNotifytype(){
		return notifytype;
	}
	
	/**
	 * set 通知类型
	 */
	public void setNotifytype(int notifytype){
		this.notifytype = notifytype;
	}
	
	/**
	 * get 箭支信息
	 * @return 
	 */
	public BowInfo getBowinfo(){
		return bowinfo;
	}
	
	/**
	 * set 箭支信息
	 */
	public void setBowinfo(BowInfo bowinfo){
		this.bowinfo = bowinfo;
	}
	
	
	@Override
	public int getId() {
		return 151103;
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
		//通知类型
		buf.append("notifytype:" + notifytype +",");
		//箭支信息
		if(this.bowinfo!=null) buf.append("bowinfo:" + bowinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
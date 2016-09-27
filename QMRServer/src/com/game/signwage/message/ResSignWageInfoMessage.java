package com.game.signwage.message;

import com.game.signwage.bean.SignInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 签到消息消息
 */
public class ResSignWageInfoMessage extends Message{

	//签到消息
	private SignInfo signInfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//签到消息
		writeBean(buf, this.signInfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//签到消息
		this.signInfo = (SignInfo)readBean(buf, SignInfo.class);
		return true;
	}
	
	/**
	 * get 签到消息
	 * @return 
	 */
	public SignInfo getSignInfo(){
		return signInfo;
	}
	
	/**
	 * set 签到消息
	 */
	public void setSignInfo(SignInfo signInfo){
		this.signInfo = signInfo;
	}
	
	
	@Override
	public int getId() {
		return 152101;
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
		//签到消息
		if(this.signInfo!=null) buf.append("signInfo:" + signInfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
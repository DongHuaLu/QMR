package com.game.offline.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回闭关信息消息
 */
public class ResRetreatInfoMessage extends Message{

	//通知类型
	private int notifyType;
	
	//当前闭关时间
	private int curTime;
	
	//当前闭关经验
	private int curExp;
	
	//当前闭关真气
	private int curZhenqi;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//通知类型
		writeInt(buf, this.notifyType);
		//当前闭关时间
		writeInt(buf, this.curTime);
		//当前闭关经验
		writeInt(buf, this.curExp);
		//当前闭关真气
		writeInt(buf, this.curZhenqi);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//通知类型
		this.notifyType = readInt(buf);
		//当前闭关时间
		this.curTime = readInt(buf);
		//当前闭关经验
		this.curExp = readInt(buf);
		//当前闭关真气
		this.curZhenqi = readInt(buf);
		return true;
	}
	
	/**
	 * get 通知类型
	 * @return 
	 */
	public int getNotifyType(){
		return notifyType;
	}
	
	/**
	 * set 通知类型
	 */
	public void setNotifyType(int notifyType){
		this.notifyType = notifyType;
	}
	
	/**
	 * get 当前闭关时间
	 * @return 
	 */
	public int getCurTime(){
		return curTime;
	}
	
	/**
	 * set 当前闭关时间
	 */
	public void setCurTime(int curTime){
		this.curTime = curTime;
	}
	
	/**
	 * get 当前闭关经验
	 * @return 
	 */
	public int getCurExp(){
		return curExp;
	}
	
	/**
	 * set 当前闭关经验
	 */
	public void setCurExp(int curExp){
		this.curExp = curExp;
	}
	
	/**
	 * get 当前闭关真气
	 * @return 
	 */
	public int getCurZhenqi(){
		return curZhenqi;
	}
	
	/**
	 * set 当前闭关真气
	 */
	public void setCurZhenqi(int curZhenqi){
		this.curZhenqi = curZhenqi;
	}
	
	
	@Override
	public int getId() {
		return 150101;
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
		buf.append("notifyType:" + notifyType +",");
		//当前闭关时间
		buf.append("curTime:" + curTime +",");
		//当前闭关经验
		buf.append("curExp:" + curExp +",");
		//当前闭关真气
		buf.append("curZhenqi:" + curZhenqi +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
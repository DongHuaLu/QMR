package com.game.biwudao.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 比武岛活动剩余时间消息
 */
public class ResBiWuDaoSurplusTimeToClientMessage extends Message{

	//活动剩余时间(秒),大于0表示活动进行中
	private int surplustime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//活动剩余时间(秒),大于0表示活动进行中
		writeInt(buf, this.surplustime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//活动剩余时间(秒),大于0表示活动进行中
		this.surplustime = readInt(buf);
		return true;
	}
	
	/**
	 * get 活动剩余时间(秒),大于0表示活动进行中
	 * @return 
	 */
	public int getSurplustime(){
		return surplustime;
	}
	
	/**
	 * set 活动剩余时间(秒),大于0表示活动进行中
	 */
	public void setSurplustime(int surplustime){
		this.surplustime = surplustime;
	}
	
	
	@Override
	public int getId() {
		return 157106;
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
		//活动剩余时间(秒),大于0表示活动进行中
		buf.append("surplustime:" + surplustime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
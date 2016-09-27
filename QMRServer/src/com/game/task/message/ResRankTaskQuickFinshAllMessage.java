package com.game.task.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 军衔任务一键快速完成后所有军功值消息
 */
public class ResRankTaskQuickFinshAllMessage extends Message{

	//所有军功值
	private int allrankexp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//所有军功值
		writeInt(buf, this.allrankexp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//所有军功值
		this.allrankexp = readInt(buf);
		return true;
	}
	
	/**
	 * get 所有军功值
	 * @return 
	 */
	public int getAllrankexp(){
		return allrankexp;
	}
	
	/**
	 * set 所有军功值
	 */
	public void setAllrankexp(int allrankexp){
		this.allrankexp = allrankexp;
	}
	
	
	@Override
	public int getId() {
		return 120117;
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
		//所有军功值
		buf.append("allrankexp:" + allrankexp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
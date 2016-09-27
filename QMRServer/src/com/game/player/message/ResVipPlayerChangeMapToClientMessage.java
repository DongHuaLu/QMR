package com.game.player.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送VIP玩家改变地图消息消息
 */
public class ResVipPlayerChangeMapToClientMessage extends Message{

	//当前次数
	private int curnum;
	
	//最大次数
	private int maxnum;
	
	//VIP等级
	private int viplv;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前次数
		writeInt(buf, this.curnum);
		//最大次数
		writeInt(buf, this.maxnum);
		//VIP等级
		writeInt(buf, this.viplv);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前次数
		this.curnum = readInt(buf);
		//最大次数
		this.maxnum = readInt(buf);
		//VIP等级
		this.viplv = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前次数
	 * @return 
	 */
	public int getCurnum(){
		return curnum;
	}
	
	/**
	 * set 当前次数
	 */
	public void setCurnum(int curnum){
		this.curnum = curnum;
	}
	
	/**
	 * get 最大次数
	 * @return 
	 */
	public int getMaxnum(){
		return maxnum;
	}
	
	/**
	 * set 最大次数
	 */
	public void setMaxnum(int maxnum){
		this.maxnum = maxnum;
	}
	
	/**
	 * get VIP等级
	 * @return 
	 */
	public int getViplv(){
		return viplv;
	}
	
	/**
	 * set VIP等级
	 */
	public void setViplv(int viplv){
		this.viplv = viplv;
	}
	
	
	@Override
	public int getId() {
		return 103132;
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
		//当前次数
		buf.append("curnum:" + curnum +",");
		//最大次数
		buf.append("maxnum:" + maxnum +",");
		//VIP等级
		buf.append("viplv:" + viplv +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
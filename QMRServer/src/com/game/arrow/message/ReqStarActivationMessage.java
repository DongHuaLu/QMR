package com.game.arrow.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 激活星斗消息
 */
public class ReqStarActivationMessage extends Message{

	//星斗主等阶
	private int starmainlv;
	
	//星斗子等阶
	private int starsublv;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//星斗主等阶
		writeInt(buf, this.starmainlv);
		//星斗子等阶
		writeInt(buf, this.starsublv);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//星斗主等阶
		this.starmainlv = readInt(buf);
		//星斗子等阶
		this.starsublv = readInt(buf);
		return true;
	}
	
	/**
	 * get 星斗主等阶
	 * @return 
	 */
	public int getStarmainlv(){
		return starmainlv;
	}
	
	/**
	 * set 星斗主等阶
	 */
	public void setStarmainlv(int starmainlv){
		this.starmainlv = starmainlv;
	}
	
	/**
	 * get 星斗子等阶
	 * @return 
	 */
	public int getStarsublv(){
		return starsublv;
	}
	
	/**
	 * set 星斗子等阶
	 */
	public void setStarsublv(int starsublv){
		this.starsublv = starsublv;
	}
	
	
	@Override
	public int getId() {
		return 151202;
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
		//星斗主等阶
		buf.append("starmainlv:" + starmainlv +",");
		//星斗子等阶
		buf.append("starsublv:" + starsublv +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
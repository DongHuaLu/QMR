package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摆摊交易经验获得消息
 */
public class ResStallsExpMessage extends Message{

	//摆摊交易经验
	private int exp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//摆摊交易经验
		writeInt(buf, this.exp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//摆摊交易经验
		this.exp = readInt(buf);
		return true;
	}
	
	/**
	 * get 摆摊交易经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 摆摊交易经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	
	@Override
	public int getId() {
		return 123112;
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
		//摆摊交易经验
		buf.append("exp:" + exp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
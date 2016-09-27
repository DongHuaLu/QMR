package com.game.biwudao.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送比武岛区域更新消息
 */
public class ResBiWuDaoAreadoubleToClientMessage extends Message{

	//区域经验倍率
	private int areadouble;
	
	//每次可获得经验值
	private int availableexp;
	
	//每次可获得真气值
	private int availablezhenqi;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//区域经验倍率
		writeInt(buf, this.areadouble);
		//每次可获得经验值
		writeInt(buf, this.availableexp);
		//每次可获得真气值
		writeInt(buf, this.availablezhenqi);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//区域经验倍率
		this.areadouble = readInt(buf);
		//每次可获得经验值
		this.availableexp = readInt(buf);
		//每次可获得真气值
		this.availablezhenqi = readInt(buf);
		return true;
	}
	
	/**
	 * get 区域经验倍率
	 * @return 
	 */
	public int getAreadouble(){
		return areadouble;
	}
	
	/**
	 * set 区域经验倍率
	 */
	public void setAreadouble(int areadouble){
		this.areadouble = areadouble;
	}
	
	/**
	 * get 每次可获得经验值
	 * @return 
	 */
	public int getAvailableexp(){
		return availableexp;
	}
	
	/**
	 * set 每次可获得经验值
	 */
	public void setAvailableexp(int availableexp){
		this.availableexp = availableexp;
	}
	
	/**
	 * get 每次可获得真气值
	 * @return 
	 */
	public int getAvailablezhenqi(){
		return availablezhenqi;
	}
	
	/**
	 * set 每次可获得真气值
	 */
	public void setAvailablezhenqi(int availablezhenqi){
		this.availablezhenqi = availablezhenqi;
	}
	
	
	@Override
	public int getId() {
		return 157102;
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
		//区域经验倍率
		buf.append("areadouble:" + areadouble +",");
		//每次可获得经验值
		buf.append("availableexp:" + availableexp +",");
		//每次可获得真气值
		buf.append("availablezhenqi:" + availablezhenqi +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
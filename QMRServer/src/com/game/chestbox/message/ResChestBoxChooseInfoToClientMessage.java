package com.game.chestbox.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 宝箱盒子选择信息发送到客户端消息
 */
public class ResChestBoxChooseInfoToClientMessage extends Message{

	//开启次数
	private int opennum;
	
	//内圈选中编号
	private int inchooseidx;
	
	//外圈选中编号
	private int outchooseidx;
	
	//外圈飞入内圈编号
	private int chooseOutSideToInSide;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//开启次数
		writeInt(buf, this.opennum);
		//内圈选中编号
		writeInt(buf, this.inchooseidx);
		//外圈选中编号
		writeInt(buf, this.outchooseidx);
		//外圈飞入内圈编号
		writeInt(buf, this.chooseOutSideToInSide);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//开启次数
		this.opennum = readInt(buf);
		//内圈选中编号
		this.inchooseidx = readInt(buf);
		//外圈选中编号
		this.outchooseidx = readInt(buf);
		//外圈飞入内圈编号
		this.chooseOutSideToInSide = readInt(buf);
		return true;
	}
	
	/**
	 * get 开启次数
	 * @return 
	 */
	public int getOpennum(){
		return opennum;
	}
	
	/**
	 * set 开启次数
	 */
	public void setOpennum(int opennum){
		this.opennum = opennum;
	}
	
	/**
	 * get 内圈选中编号
	 * @return 
	 */
	public int getInchooseidx(){
		return inchooseidx;
	}
	
	/**
	 * set 内圈选中编号
	 */
	public void setInchooseidx(int inchooseidx){
		this.inchooseidx = inchooseidx;
	}
	
	/**
	 * get 外圈选中编号
	 * @return 
	 */
	public int getOutchooseidx(){
		return outchooseidx;
	}
	
	/**
	 * set 外圈选中编号
	 */
	public void setOutchooseidx(int outchooseidx){
		this.outchooseidx = outchooseidx;
	}
	
	/**
	 * get 外圈飞入内圈编号
	 * @return 
	 */
	public int getChooseOutSideToInSide(){
		return chooseOutSideToInSide;
	}
	
	/**
	 * set 外圈飞入内圈编号
	 */
	public void setChooseOutSideToInSide(int chooseOutSideToInSide){
		this.chooseOutSideToInSide = chooseOutSideToInSide;
	}
	
	
	@Override
	public int getId() {
		return 156102;
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
		//开启次数
		buf.append("opennum:" + opennum +",");
		//内圈选中编号
		buf.append("inchooseidx:" + inchooseidx +",");
		//外圈选中编号
		buf.append("outchooseidx:" + outchooseidx +",");
		//外圈飞入内圈编号
		buf.append("chooseOutSideToInSide:" + chooseOutSideToInSide +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
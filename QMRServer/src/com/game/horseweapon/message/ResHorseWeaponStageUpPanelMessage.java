package com.game.horseweapon.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送坐骑升阶面板信息消息
 */
public class ResHorseWeaponStageUpPanelMessage extends Message{

	//当前升阶经验
	private int exp;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//当前升阶经验
		writeInt(buf, this.exp);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//当前升阶经验
		this.exp = readInt(buf);
		return true;
	}
	
	/**
	 * get 当前升阶经验
	 * @return 
	 */
	public int getExp(){
		return exp;
	}
	
	/**
	 * set 当前升阶经验
	 */
	public void setExp(int exp){
		this.exp = exp;
	}
	
	
	@Override
	public int getId() {
		return 155103;
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
		//当前升阶经验
		buf.append("exp:" + exp +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
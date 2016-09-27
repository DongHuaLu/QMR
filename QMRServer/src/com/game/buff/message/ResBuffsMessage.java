package com.game.buff.message;

import com.game.buff.bean.BuffInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * Buff列表消息
 */
public class ResBuffsMessage extends Message{

	//战斗状态
	private int fightState;
	
	//buff
	private List<BuffInfo> buff = new ArrayList<BuffInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//战斗状态
		writeInt(buf, this.fightState);
		//buff
		writeShort(buf, buff.size());
		for (int i = 0; i < buff.size(); i++) {
			writeBean(buf, buff.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//战斗状态
		this.fightState = readInt(buf);
		//buff
		int buff_length = readShort(buf);
		for (int i = 0; i < buff_length; i++) {
			buff.add((BuffInfo)readBean(buf, BuffInfo.class));
		}
		return true;
	}
	
	/**
	 * get 战斗状态
	 * @return 
	 */
	public int getFightState(){
		return fightState;
	}
	
	/**
	 * set 战斗状态
	 */
	public void setFightState(int fightState){
		this.fightState = fightState;
	}
	
	/**
	 * get buff
	 * @return 
	 */
	public List<BuffInfo> getBuff(){
		return buff;
	}
	
	/**
	 * set buff
	 */
	public void setBuff(List<BuffInfo> buff){
		this.buff = buff;
	}
	
	
	@Override
	public int getId() {
		return 113101;
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
		//战斗状态
		buf.append("fightState:" + fightState +",");
		//buff
		buf.append("buff:{");
		for (int i = 0; i < buff.size(); i++) {
			buf.append(buff.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.buff.message;

import com.game.buff.bean.BuffInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 增加Buff消息
 */
public class ResAddBuffMessage extends Message{

	//角色Id
	private long personId;
	
	//来源Id
	private long sourceId;
	
	//战斗状态
	private int fightState;
	
	//buff
	private BuffInfo buff;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.personId);
		//来源Id
		writeLong(buf, this.sourceId);
		//战斗状态
		writeInt(buf, this.fightState);
		//buff
		writeBean(buf, this.buff);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.personId = readLong(buf);
		//来源Id
		this.sourceId = readLong(buf);
		//战斗状态
		this.fightState = readInt(buf);
		//buff
		this.buff = (BuffInfo)readBean(buf, BuffInfo.class);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getPersonId(){
		return personId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setPersonId(long personId){
		this.personId = personId;
	}
	
	/**
	 * get 来源Id
	 * @return 
	 */
	public long getSourceId(){
		return sourceId;
	}
	
	/**
	 * set 来源Id
	 */
	public void setSourceId(long sourceId){
		this.sourceId = sourceId;
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
	public BuffInfo getBuff(){
		return buff;
	}
	
	/**
	 * set buff
	 */
	public void setBuff(BuffInfo buff){
		this.buff = buff;
	}
	
	
	@Override
	public int getId() {
		return 113102;
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
		//角色Id
		buf.append("personId:" + personId +",");
		//来源Id
		buf.append("sourceId:" + sourceId +",");
		//战斗状态
		buf.append("fightState:" + fightState +",");
		//buff
		if(this.buff!=null) buf.append("buff:" + buff.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.monster.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 怪物死亡消息
 */
public class ResMonsterDieMessage extends Message{

	//角色Id
	private long monsterId;
	
	//死亡状态 2-假死 3-死亡
	private byte die;
	
	//杀死人Id
	private long killer;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色Id
		writeLong(buf, this.monsterId);
		//死亡状态 2-假死 3-死亡
		writeByte(buf, this.die);
		//杀死人Id
		writeLong(buf, this.killer);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色Id
		this.monsterId = readLong(buf);
		//死亡状态 2-假死 3-死亡
		this.die = readByte(buf);
		//杀死人Id
		this.killer = readLong(buf);
		return true;
	}
	
	/**
	 * get 角色Id
	 * @return 
	 */
	public long getMonsterId(){
		return monsterId;
	}
	
	/**
	 * set 角色Id
	 */
	public void setMonsterId(long monsterId){
		this.monsterId = monsterId;
	}
	
	/**
	 * get 死亡状态 2-假死 3-死亡
	 * @return 
	 */
	public byte getDie(){
		return die;
	}
	
	/**
	 * set 死亡状态 2-假死 3-死亡
	 */
	public void setDie(byte die){
		this.die = die;
	}
	
	/**
	 * get 杀死人Id
	 * @return 
	 */
	public long getKiller(){
		return killer;
	}
	
	/**
	 * set 杀死人Id
	 */
	public void setKiller(long killer){
		this.killer = killer;
	}
	
	
	@Override
	public int getId() {
		return 114108;
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
		buf.append("monsterId:" + monsterId +",");
		//死亡状态 2-假死 3-死亡
		buf.append("die:" + die +",");
		//杀死人Id
		buf.append("killer:" + killer +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.fight.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 切换攻击锁定目标消息
 */
public class ReqChangeAttackTargetMessage extends Message{

	//攻击目标类型 1玩家 2怪物 3美人
	private int targetType;
	
	//目标唯一标识
	private long targetId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//攻击目标类型 1玩家 2怪物 3美人
		writeInt(buf, this.targetType);
		//目标唯一标识
		writeLong(buf, this.targetId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//攻击目标类型 1玩家 2怪物 3美人
		this.targetType = readInt(buf);
		//目标唯一标识
		this.targetId = readLong(buf);
		return true;
	}
	
	/**
	 * get 攻击目标类型 1玩家 2怪物 3美人
	 * @return 
	 */
	public int getTargetType(){
		return targetType;
	}
	
	/**
	 * set 攻击目标类型 1玩家 2怪物 3美人
	 */
	public void setTargetType(int targetType){
		this.targetType = targetType;
	}
	
	/**
	 * get 目标唯一标识
	 * @return 
	 */
	public long getTargetId(){
		return targetId;
	}
	
	/**
	 * set 目标唯一标识
	 */
	public void setTargetId(long targetId){
		this.targetId = targetId;
	}
	
	
	@Override
	public int getId() {
		return 102203;
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
		//攻击目标类型 1玩家 2怪物 3美人
		buf.append("targetType:" + targetType +",");
		//目标唯一标识
		buf.append("targetId:" + targetId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
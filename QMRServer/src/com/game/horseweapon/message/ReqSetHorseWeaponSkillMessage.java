package com.game.horseweapon.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 开始设置骑乘兵器技能消息
 */
public class ReqSetHorseWeaponSkillMessage extends Message{

	//设置技能格子
	private int grid;
	
	//设置技能ID
	private int skillId;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//设置技能格子
		writeInt(buf, this.grid);
		//设置技能ID
		writeInt(buf, this.skillId);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//设置技能格子
		this.grid = readInt(buf);
		//设置技能ID
		this.skillId = readInt(buf);
		return true;
	}
	
	/**
	 * get 设置技能格子
	 * @return 
	 */
	public int getGrid(){
		return grid;
	}
	
	/**
	 * set 设置技能格子
	 */
	public void setGrid(int grid){
		this.grid = grid;
	}
	
	/**
	 * get 设置技能ID
	 * @return 
	 */
	public int getSkillId(){
		return skillId;
	}
	
	/**
	 * set 设置技能ID
	 */
	public void setSkillId(int skillId){
		this.skillId = skillId;
	}
	
	
	@Override
	public int getId() {
		return 155205;
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
		//设置技能格子
		buf.append("grid:" + grid +",");
		//设置技能ID
		buf.append("skillId:" + skillId +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
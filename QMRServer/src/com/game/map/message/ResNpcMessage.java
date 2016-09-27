package com.game.map.message;

import com.game.map.bean.NpcInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围NPC消息
 */
public class ResNpcMessage extends Message{

	//周围NPC信息
	private NpcInfo npc;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//周围NPC信息
		writeBean(buf, this.npc);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//周围NPC信息
		this.npc = (NpcInfo)readBean(buf, NpcInfo.class);
		return true;
	}
	
	/**
	 * get 周围NPC信息
	 * @return 
	 */
	public NpcInfo getNpc(){
		return npc;
	}
	
	/**
	 * set 周围NPC信息
	 */
	public void setNpc(NpcInfo npc){
		this.npc = npc;
	}
	
	
	@Override
	public int getId() {
		return 101129;
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
		//周围NPC信息
		if(this.npc!=null) buf.append("npc:" + npc.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
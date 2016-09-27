package com.game.map.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围消失NPC消息
 */
public class ResRoundNpcDisappearMessage extends Message{

	//消失NPC列表
	private List<Long> npcids = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消失NPC列表
		writeShort(buf, npcids.size());
		for (int i = 0; i < npcids.size(); i++) {
			writeLong(buf, npcids.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消失NPC列表
		int npcids_length = readShort(buf);
		for (int i = 0; i < npcids_length; i++) {
			npcids.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 消失NPC列表
	 * @return 
	 */
	public List<Long> getNpcids(){
		return npcids;
	}
	
	/**
	 * set 消失NPC列表
	 */
	public void setNpcids(List<Long> npcids){
		this.npcids = npcids;
	}
	
	
	@Override
	public int getId() {
		return 101130;
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
		//消失NPC列表
		buf.append("npcids:{");
		for (int i = 0; i < npcids.size(); i++) {
			buf.append(npcids.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
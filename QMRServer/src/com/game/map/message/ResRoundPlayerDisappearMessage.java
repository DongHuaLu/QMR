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
 * 周围消失角色消息
 */
public class ResRoundPlayerDisappearMessage extends Message{

	//消失角色列表
	private List<Long> playerIds = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//消失角色列表
		writeShort(buf, playerIds.size());
		for (int i = 0; i < playerIds.size(); i++) {
			writeLong(buf, playerIds.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//消失角色列表
		int playerIds_length = readShort(buf);
		for (int i = 0; i < playerIds_length; i++) {
			playerIds.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 消失角色列表
	 * @return 
	 */
	public List<Long> getPlayerIds(){
		return playerIds;
	}
	
	/**
	 * set 消失角色列表
	 */
	public void setPlayerIds(List<Long> playerIds){
		this.playerIds = playerIds;
	}
	
	
	@Override
	public int getId() {
		return 101105;
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
		//消失角色列表
		buf.append("playerIds:{");
		for (int i = 0; i < playerIds.size(); i++) {
			buf.append(playerIds.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.map.message;

import com.game.map.bean.PlayerInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 周围玩家消息
 */
public class ResRoundPlayerMessage extends Message{

	//周围玩家信息
	private PlayerInfo player;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//周围玩家信息
		writeBean(buf, this.player);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//周围玩家信息
		this.player = (PlayerInfo)readBean(buf, PlayerInfo.class);
		return true;
	}
	
	/**
	 * get 周围玩家信息
	 * @return 
	 */
	public PlayerInfo getPlayer(){
		return player;
	}
	
	/**
	 * set 周围玩家信息
	 */
	public void setPlayer(PlayerInfo player){
		this.player = player;
	}
	
	
	@Override
	public int getId() {
		return 101101;
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
		//周围玩家信息
		if(this.player!=null) buf.append("player:" + player.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
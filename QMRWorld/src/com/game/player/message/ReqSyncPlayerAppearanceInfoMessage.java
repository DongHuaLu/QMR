package com.game.player.message;

import com.game.player.bean.PlayerAppearanceInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 同步世界服务器玩家外观消息
 */
public class ReqSyncPlayerAppearanceInfoMessage extends Message{

	//角色id
	private long playerId;
	
	//外观信息
	private PlayerAppearanceInfo appearanceInfo;
	
	//王城BUFFid
	private int kingcitybuffid;
	
	//VIPid
	private int vipid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//外观信息
		writeBean(buf, this.appearanceInfo);
		//王城BUFFid
		writeInt(buf, this.kingcitybuffid);
		//VIPid
		writeInt(buf, this.vipid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//外观信息
		this.appearanceInfo = (PlayerAppearanceInfo)readBean(buf, PlayerAppearanceInfo.class);
		//王城BUFFid
		this.kingcitybuffid = readInt(buf);
		//VIPid
		this.vipid = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 外观信息
	 * @return 
	 */
	public PlayerAppearanceInfo getAppearanceInfo(){
		return appearanceInfo;
	}
	
	/**
	 * set 外观信息
	 */
	public void setAppearanceInfo(PlayerAppearanceInfo appearanceInfo){
		this.appearanceInfo = appearanceInfo;
	}
	
	/**
	 * get 王城BUFFid
	 * @return 
	 */
	public int getKingcitybuffid(){
		return kingcitybuffid;
	}
	
	/**
	 * set 王城BUFFid
	 */
	public void setKingcitybuffid(int kingcitybuffid){
		this.kingcitybuffid = kingcitybuffid;
	}
	
	/**
	 * get VIPid
	 * @return 
	 */
	public int getVipid(){
		return vipid;
	}
	
	/**
	 * set VIPid
	 */
	public void setVipid(int vipid){
		this.vipid = vipid;
	}
	
	
	@Override
	public int getId() {
		return 103306;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//外观信息
		if(this.appearanceInfo!=null) buf.append("appearanceInfo:" + appearanceInfo.toString() +",");
		//王城BUFFid
		buf.append("kingcitybuffid:" + kingcitybuffid +",");
		//VIPid
		buf.append("vipid:" + vipid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.toplist.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知世界服务器获取排行榜信息消息
 */
public class ReqGetTopListToWorldMessage extends Message{

	//玩家id
	private long playerid;
	
	//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
	private byte toptype;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家id
		writeLong(buf, this.playerid);
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		writeByte(buf, this.toptype);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家id
		this.playerid = readLong(buf);
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		this.toptype = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家id
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家id
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
	 * @return 
	 */
	public byte getToptype(){
		return toptype;
	}
	
	/**
	 * set 排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
	 */
	public void setToptype(byte toptype){
		this.toptype = toptype;
	}
	
	
	@Override
	public int getId() {
		return 142301;
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
		//玩家id
		buf.append("playerid:" + playerid +",");
		//排行类型 1等级 2坐骑 3武功 4龙元 5 连斩 6 侍宠 7 战斗力
		buf.append("toptype:" + toptype +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
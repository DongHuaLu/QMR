package com.game.stalls.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摊位条件排序（目前是前端做排序，这里返回新数据给前端）消息
 */
public class ReqStallsSortToWorldMessage extends Message{

	//角色ID
	private long playerid;
	
	//按照类型排序，0，摊位星级，1，玩家等级，2，物品数量，3在售货币
	private byte type;
	
	//索引起点
	private int indexlittle;
	
	//索引终点
	private int indexLarge;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色ID
		writeLong(buf, this.playerid);
		//按照类型排序，0，摊位星级，1，玩家等级，2，物品数量，3在售货币
		writeByte(buf, this.type);
		//索引起点
		writeInt(buf, this.indexlittle);
		//索引终点
		writeInt(buf, this.indexLarge);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色ID
		this.playerid = readLong(buf);
		//按照类型排序，0，摊位星级，1，玩家等级，2，物品数量，3在售货币
		this.type = readByte(buf);
		//索引起点
		this.indexlittle = readInt(buf);
		//索引终点
		this.indexLarge = readInt(buf);
		return true;
	}
	
	/**
	 * get 角色ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 角色ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 按照类型排序，0，摊位星级，1，玩家等级，2，物品数量，3在售货币
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 按照类型排序，0，摊位星级，1，玩家等级，2，物品数量，3在售货币
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 索引起点
	 * @return 
	 */
	public int getIndexlittle(){
		return indexlittle;
	}
	
	/**
	 * set 索引起点
	 */
	public void setIndexlittle(int indexlittle){
		this.indexlittle = indexlittle;
	}
	
	/**
	 * get 索引终点
	 * @return 
	 */
	public int getIndexLarge(){
		return indexLarge;
	}
	
	/**
	 * set 索引终点
	 */
	public void setIndexLarge(int indexLarge){
		this.indexLarge = indexLarge;
	}
	
	
	@Override
	public int getId() {
		return 123303;
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
		//角色ID
		buf.append("playerid:" + playerid +",");
		//按照类型排序，0，摊位星级，1，玩家等级，2，物品数量，3在售货币
		buf.append("type:" + type +",");
		//索引起点
		buf.append("indexlittle:" + indexlittle +",");
		//索引终点
		buf.append("indexLarge:" + indexLarge +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
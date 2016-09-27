package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 玩家对求婚做选择消息
 */
public class ReqProposeSelectToGameMessage extends Message{

	//求婚者ID
	private long playerid;
	
	//对求婚做选择，1同意，0拒绝（取消）
	private byte select;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//求婚者ID
		writeLong(buf, this.playerid);
		//对求婚做选择，1同意，0拒绝（取消）
		writeByte(buf, this.select);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//求婚者ID
		this.playerid = readLong(buf);
		//对求婚做选择，1同意，0拒绝（取消）
		this.select = readByte(buf);
		return true;
	}
	
	/**
	 * get 求婚者ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 求婚者ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 对求婚做选择，1同意，0拒绝（取消）
	 * @return 
	 */
	public byte getSelect(){
		return select;
	}
	
	/**
	 * set 对求婚做选择，1同意，0拒绝（取消）
	 */
	public void setSelect(byte select){
		this.select = select;
	}
	
	
	@Override
	public int getId() {
		return 163203;
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
		//求婚者ID
		buf.append("playerid:" + playerid +",");
		//对求婚做选择，1同意，0拒绝（取消）
		buf.append("select:" + select +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
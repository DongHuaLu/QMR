package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 任命新队长-玩家选择消息
 */
public class ReqAppointGameSelectMessage extends Message{

	//队伍Id
	private long teamid;
	
	//0同意，1拒绝
	private byte select;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队伍Id
		writeLong(buf, this.teamid);
		//0同意，1拒绝
		writeByte(buf, this.select);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队伍Id
		this.teamid = readLong(buf);
		//0同意，1拒绝
		this.select = readByte(buf);
		return true;
	}
	
	/**
	 * get 队伍Id
	 * @return 
	 */
	public long getTeamid(){
		return teamid;
	}
	
	/**
	 * set 队伍Id
	 */
	public void setTeamid(long teamid){
		this.teamid = teamid;
	}
	
	/**
	 * get 0同意，1拒绝
	 * @return 
	 */
	public byte getSelect(){
		return select;
	}
	
	/**
	 * set 0同意，1拒绝
	 */
	public void setSelect(byte select){
		this.select = select;
	}
	
	
	@Override
	public int getId() {
		return 118208;
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
		//队伍Id
		buf.append("teamid:" + teamid +",");
		//0同意，1拒绝
		buf.append("select:" + select +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
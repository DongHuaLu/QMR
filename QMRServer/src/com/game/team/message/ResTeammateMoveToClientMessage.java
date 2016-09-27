package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 队友移动XY，显示在小地图，返回给前端消息
 */
public class ResTeammateMoveToClientMessage extends Message{

	//队员ID
	private long memberid;
	
	//队员所在坐标X
	private short mx;
	
	//队员所在坐标Y
	private short my;
	
	//类型：0移动，1首次坐标
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队员ID
		writeLong(buf, this.memberid);
		//队员所在坐标X
		writeShort(buf, this.mx);
		//队员所在坐标Y
		writeShort(buf, this.my);
		//类型：0移动，1首次坐标
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队员ID
		this.memberid = readLong(buf);
		//队员所在坐标X
		this.mx = readShort(buf);
		//队员所在坐标Y
		this.my = readShort(buf);
		//类型：0移动，1首次坐标
		this.type = readByte(buf);
		return true;
	}
	
	/**
	 * get 队员ID
	 * @return 
	 */
	public long getMemberid(){
		return memberid;
	}
	
	/**
	 * set 队员ID
	 */
	public void setMemberid(long memberid){
		this.memberid = memberid;
	}
	
	/**
	 * get 队员所在坐标X
	 * @return 
	 */
	public short getMx(){
		return mx;
	}
	
	/**
	 * set 队员所在坐标X
	 */
	public void setMx(short mx){
		this.mx = mx;
	}
	
	/**
	 * get 队员所在坐标Y
	 * @return 
	 */
	public short getMy(){
		return my;
	}
	
	/**
	 * set 队员所在坐标Y
	 */
	public void setMy(short my){
		this.my = my;
	}
	
	/**
	 * get 类型：0移动，1首次坐标
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 类型：0移动，1首次坐标
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 118112;
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
		//队员ID
		buf.append("memberid:" + memberid +",");
		//队员所在坐标X
		buf.append("mx:" + mx +",");
		//队员所在坐标Y
		buf.append("my:" + my +",");
		//类型：0移动，1首次坐标
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
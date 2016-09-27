package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 组队多人副本进入选择通知其他队员消息
 */
public class ResZoneTeamNoticeSelectToClientMessage extends Message{

	//选项（默认是0等待选择），1拒绝，2同意
	private byte select;
	
	//队员ID
	private long memberid;
	
	//副本ID
	private int zoneid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//选项（默认是0等待选择），1拒绝，2同意
		writeByte(buf, this.select);
		//队员ID
		writeLong(buf, this.memberid);
		//副本ID
		writeInt(buf, this.zoneid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//选项（默认是0等待选择），1拒绝，2同意
		this.select = readByte(buf);
		//队员ID
		this.memberid = readLong(buf);
		//副本ID
		this.zoneid = readInt(buf);
		return true;
	}
	
	/**
	 * get 选项（默认是0等待选择），1拒绝，2同意
	 * @return 
	 */
	public byte getSelect(){
		return select;
	}
	
	/**
	 * set 选项（默认是0等待选择），1拒绝，2同意
	 */
	public void setSelect(byte select){
		this.select = select;
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
	 * get 副本ID
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本ID
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	
	@Override
	public int getId() {
		return 128118;
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
		//选项（默认是0等待选择），1拒绝，2同意
		buf.append("select:" + select +",");
		//队员ID
		buf.append("memberid:" + memberid +",");
		//副本ID
		buf.append("zoneid:" + zoneid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
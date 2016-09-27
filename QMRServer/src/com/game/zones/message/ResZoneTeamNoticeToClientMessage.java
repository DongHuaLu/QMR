package com.game.zones.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 组队多人进入副本通知队员消息
 */
public class ResZoneTeamNoticeToClientMessage extends Message{

	//队长ID
	private long leaderid;
	
	//队长名字
	private String leadername;
	
	//副本ID
	private int zoneid;
	
	//等待选择倒计时
	private int waittime;
	
	//进入倒计时
	private int entertime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队长ID
		writeLong(buf, this.leaderid);
		//队长名字
		writeString(buf, this.leadername);
		//副本ID
		writeInt(buf, this.zoneid);
		//等待选择倒计时
		writeInt(buf, this.waittime);
		//进入倒计时
		writeInt(buf, this.entertime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队长ID
		this.leaderid = readLong(buf);
		//队长名字
		this.leadername = readString(buf);
		//副本ID
		this.zoneid = readInt(buf);
		//等待选择倒计时
		this.waittime = readInt(buf);
		//进入倒计时
		this.entertime = readInt(buf);
		return true;
	}
	
	/**
	 * get 队长ID
	 * @return 
	 */
	public long getLeaderid(){
		return leaderid;
	}
	
	/**
	 * set 队长ID
	 */
	public void setLeaderid(long leaderid){
		this.leaderid = leaderid;
	}
	
	/**
	 * get 队长名字
	 * @return 
	 */
	public String getLeadername(){
		return leadername;
	}
	
	/**
	 * set 队长名字
	 */
	public void setLeadername(String leadername){
		this.leadername = leadername;
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
	
	/**
	 * get 等待选择倒计时
	 * @return 
	 */
	public int getWaittime(){
		return waittime;
	}
	
	/**
	 * set 等待选择倒计时
	 */
	public void setWaittime(int waittime){
		this.waittime = waittime;
	}
	
	/**
	 * get 进入倒计时
	 * @return 
	 */
	public int getEntertime(){
		return entertime;
	}
	
	/**
	 * set 进入倒计时
	 */
	public void setEntertime(int entertime){
		this.entertime = entertime;
	}
	
	
	@Override
	public int getId() {
		return 128117;
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
		//队长ID
		buf.append("leaderid:" + leaderid +",");
		//队长名字
		if(this.leadername!=null) buf.append("leadername:" + leadername.toString() +",");
		//副本ID
		buf.append("zoneid:" + zoneid +",");
		//等待选择倒计时
		buf.append("waittime:" + waittime +",");
		//进入倒计时
		buf.append("entertime:" + entertime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
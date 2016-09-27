package com.game.spirittree.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 显示行会灵树上一棵和下一棵果实成熟时间信息消息
 */
public class ResShowGuildMatureClientMessage extends Message{

	//上一个果实成熟的玩家名字
	private String lastname;
	
	//上一个果实成熟时间（当前时间-成熟时间）
	private int lasttime;
	
	//下一个果实成熟的玩家名字
	private String nextname;
	
	//下一个果实成熟时间（当前时间-成熟时间）
	private int nexttime;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//上一个果实成熟的玩家名字
		writeString(buf, this.lastname);
		//上一个果实成熟时间（当前时间-成熟时间）
		writeInt(buf, this.lasttime);
		//下一个果实成熟的玩家名字
		writeString(buf, this.nextname);
		//下一个果实成熟时间（当前时间-成熟时间）
		writeInt(buf, this.nexttime);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//上一个果实成熟的玩家名字
		this.lastname = readString(buf);
		//上一个果实成熟时间（当前时间-成熟时间）
		this.lasttime = readInt(buf);
		//下一个果实成熟的玩家名字
		this.nextname = readString(buf);
		//下一个果实成熟时间（当前时间-成熟时间）
		this.nexttime = readInt(buf);
		return true;
	}
	
	/**
	 * get 上一个果实成熟的玩家名字
	 * @return 
	 */
	public String getLastname(){
		return lastname;
	}
	
	/**
	 * set 上一个果实成熟的玩家名字
	 */
	public void setLastname(String lastname){
		this.lastname = lastname;
	}
	
	/**
	 * get 上一个果实成熟时间（当前时间-成熟时间）
	 * @return 
	 */
	public int getLasttime(){
		return lasttime;
	}
	
	/**
	 * set 上一个果实成熟时间（当前时间-成熟时间）
	 */
	public void setLasttime(int lasttime){
		this.lasttime = lasttime;
	}
	
	/**
	 * get 下一个果实成熟的玩家名字
	 * @return 
	 */
	public String getNextname(){
		return nextname;
	}
	
	/**
	 * set 下一个果实成熟的玩家名字
	 */
	public void setNextname(String nextname){
		this.nextname = nextname;
	}
	
	/**
	 * get 下一个果实成熟时间（当前时间-成熟时间）
	 * @return 
	 */
	public int getNexttime(){
		return nexttime;
	}
	
	/**
	 * set 下一个果实成熟时间（当前时间-成熟时间）
	 */
	public void setNexttime(int nexttime){
		this.nexttime = nexttime;
	}
	
	
	@Override
	public int getId() {
		return 133105;
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
		//上一个果实成熟的玩家名字
		if(this.lastname!=null) buf.append("lastname:" + lastname.toString() +",");
		//上一个果实成熟时间（当前时间-成熟时间）
		buf.append("lasttime:" + lasttime +",");
		//下一个果实成熟的玩家名字
		if(this.nextname!=null) buf.append("nextname:" + nextname.toString() +",");
		//下一个果实成熟时间（当前时间-成熟时间）
		buf.append("nexttime:" + nexttime +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
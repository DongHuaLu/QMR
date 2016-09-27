package com.game.toplist.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送给客户端膜拜玩家消息
 */
public class ResWorShipToClientMessage extends Message{

	//错误代码
	private byte errorcode;
	
	//自己今天膜拜次数
	private byte worshipnum;
	
	//膜拜玩家id
	private long worshipplayerid;
	
	//总膜拜次数
	private int allworshipnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.errorcode);
		//自己今天膜拜次数
		writeByte(buf, this.worshipnum);
		//膜拜玩家id
		writeLong(buf, this.worshipplayerid);
		//总膜拜次数
		writeInt(buf, this.allworshipnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.errorcode = readByte(buf);
		//自己今天膜拜次数
		this.worshipnum = readByte(buf);
		//膜拜玩家id
		this.worshipplayerid = readLong(buf);
		//总膜拜次数
		this.allworshipnum = readInt(buf);
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getErrorcode(){
		return errorcode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setErrorcode(byte errorcode){
		this.errorcode = errorcode;
	}
	
	/**
	 * get 自己今天膜拜次数
	 * @return 
	 */
	public byte getWorshipnum(){
		return worshipnum;
	}
	
	/**
	 * set 自己今天膜拜次数
	 */
	public void setWorshipnum(byte worshipnum){
		this.worshipnum = worshipnum;
	}
	
	/**
	 * get 膜拜玩家id
	 * @return 
	 */
	public long getWorshipplayerid(){
		return worshipplayerid;
	}
	
	/**
	 * set 膜拜玩家id
	 */
	public void setWorshipplayerid(long worshipplayerid){
		this.worshipplayerid = worshipplayerid;
	}
	
	/**
	 * get 总膜拜次数
	 * @return 
	 */
	public int getAllworshipnum(){
		return allworshipnum;
	}
	
	/**
	 * set 总膜拜次数
	 */
	public void setAllworshipnum(int allworshipnum){
		this.allworshipnum = allworshipnum;
	}
	
	
	@Override
	public int getId() {
		return 142102;
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
		//错误代码
		buf.append("errorcode:" + errorcode +",");
		//自己今天膜拜次数
		buf.append("worshipnum:" + worshipnum +",");
		//膜拜玩家id
		buf.append("worshipplayerid:" + worshipplayerid +",");
		//总膜拜次数
		buf.append("allworshipnum:" + allworshipnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
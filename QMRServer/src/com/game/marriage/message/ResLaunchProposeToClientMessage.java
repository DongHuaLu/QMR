package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 得到求婚对象信息消息
 */
public class ResLaunchProposeToClientMessage extends Message{

	//求婚对象ID
	private long suitorobjid;
	
	//求婚对象名字
	private String suitorobjname;
	
	//求婚对象等级
	private short suitorobjlv;
	
	//求婚对象帮会名字
	private String guildname;
	
	//求婚对象头像
	private int avatarid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//求婚对象ID
		writeLong(buf, this.suitorobjid);
		//求婚对象名字
		writeString(buf, this.suitorobjname);
		//求婚对象等级
		writeShort(buf, this.suitorobjlv);
		//求婚对象帮会名字
		writeString(buf, this.guildname);
		//求婚对象头像
		writeInt(buf, this.avatarid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//求婚对象ID
		this.suitorobjid = readLong(buf);
		//求婚对象名字
		this.suitorobjname = readString(buf);
		//求婚对象等级
		this.suitorobjlv = readShort(buf);
		//求婚对象帮会名字
		this.guildname = readString(buf);
		//求婚对象头像
		this.avatarid = readInt(buf);
		return true;
	}
	
	/**
	 * get 求婚对象ID
	 * @return 
	 */
	public long getSuitorobjid(){
		return suitorobjid;
	}
	
	/**
	 * set 求婚对象ID
	 */
	public void setSuitorobjid(long suitorobjid){
		this.suitorobjid = suitorobjid;
	}
	
	/**
	 * get 求婚对象名字
	 * @return 
	 */
	public String getSuitorobjname(){
		return suitorobjname;
	}
	
	/**
	 * set 求婚对象名字
	 */
	public void setSuitorobjname(String suitorobjname){
		this.suitorobjname = suitorobjname;
	}
	
	/**
	 * get 求婚对象等级
	 * @return 
	 */
	public short getSuitorobjlv(){
		return suitorobjlv;
	}
	
	/**
	 * set 求婚对象等级
	 */
	public void setSuitorobjlv(short suitorobjlv){
		this.suitorobjlv = suitorobjlv;
	}
	
	/**
	 * get 求婚对象帮会名字
	 * @return 
	 */
	public String getGuildname(){
		return guildname;
	}
	
	/**
	 * set 求婚对象帮会名字
	 */
	public void setGuildname(String guildname){
		this.guildname = guildname;
	}
	
	/**
	 * get 求婚对象头像
	 * @return 
	 */
	public int getAvatarid(){
		return avatarid;
	}
	
	/**
	 * set 求婚对象头像
	 */
	public void setAvatarid(int avatarid){
		this.avatarid = avatarid;
	}
	
	
	@Override
	public int getId() {
		return 163101;
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
		//求婚对象ID
		buf.append("suitorobjid:" + suitorobjid +",");
		//求婚对象名字
		if(this.suitorobjname!=null) buf.append("suitorobjname:" + suitorobjname.toString() +",");
		//求婚对象等级
		buf.append("suitorobjlv:" + suitorobjlv +",");
		//求婚对象帮会名字
		if(this.guildname!=null) buf.append("guildname:" + guildname.toString() +",");
		//求婚对象头像
		buf.append("avatarid:" + avatarid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
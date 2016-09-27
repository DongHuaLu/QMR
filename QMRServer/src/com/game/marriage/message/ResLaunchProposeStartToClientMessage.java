package com.game.marriage.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 求婚信息展示消息
 */
public class ResLaunchProposeStartToClientMessage extends Message{

	//求婚发起人ID
	private long playerid;
	
	//求婚发起人名字
	private String playername;
	
	//求婚发起人等级
	private short playerlv;
	
	//求婚发起人帮会名字
	private String guildname;
	
	//戒指模版ID
	private int ringmodelid;
	
	//求婚发起人头像
	private int avatarid;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//求婚发起人ID
		writeLong(buf, this.playerid);
		//求婚发起人名字
		writeString(buf, this.playername);
		//求婚发起人等级
		writeShort(buf, this.playerlv);
		//求婚发起人帮会名字
		writeString(buf, this.guildname);
		//戒指模版ID
		writeInt(buf, this.ringmodelid);
		//求婚发起人头像
		writeInt(buf, this.avatarid);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//求婚发起人ID
		this.playerid = readLong(buf);
		//求婚发起人名字
		this.playername = readString(buf);
		//求婚发起人等级
		this.playerlv = readShort(buf);
		//求婚发起人帮会名字
		this.guildname = readString(buf);
		//戒指模版ID
		this.ringmodelid = readInt(buf);
		//求婚发起人头像
		this.avatarid = readInt(buf);
		return true;
	}
	
	/**
	 * get 求婚发起人ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 求婚发起人ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 求婚发起人名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 求婚发起人名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 求婚发起人等级
	 * @return 
	 */
	public short getPlayerlv(){
		return playerlv;
	}
	
	/**
	 * set 求婚发起人等级
	 */
	public void setPlayerlv(short playerlv){
		this.playerlv = playerlv;
	}
	
	/**
	 * get 求婚发起人帮会名字
	 * @return 
	 */
	public String getGuildname(){
		return guildname;
	}
	
	/**
	 * set 求婚发起人帮会名字
	 */
	public void setGuildname(String guildname){
		this.guildname = guildname;
	}
	
	/**
	 * get 戒指模版ID
	 * @return 
	 */
	public int getRingmodelid(){
		return ringmodelid;
	}
	
	/**
	 * set 戒指模版ID
	 */
	public void setRingmodelid(int ringmodelid){
		this.ringmodelid = ringmodelid;
	}
	
	/**
	 * get 求婚发起人头像
	 * @return 
	 */
	public int getAvatarid(){
		return avatarid;
	}
	
	/**
	 * set 求婚发起人头像
	 */
	public void setAvatarid(int avatarid){
		this.avatarid = avatarid;
	}
	
	
	@Override
	public int getId() {
		return 163102;
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
		//求婚发起人ID
		buf.append("playerid:" + playerid +",");
		//求婚发起人名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//求婚发起人等级
		buf.append("playerlv:" + playerlv +",");
		//求婚发起人帮会名字
		if(this.guildname!=null) buf.append("guildname:" + guildname.toString() +",");
		//戒指模版ID
		buf.append("ringmodelid:" + ringmodelid +",");
		//求婚发起人头像
		buf.append("avatarid:" + avatarid +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
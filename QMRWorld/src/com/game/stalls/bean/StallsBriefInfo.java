package com.game.stalls.bean;


import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 单个摊位简要信息
 */
public class StallsBriefInfo extends Bean {

	//玩家名字
	private String playername;
	
	//玩家ID
	private long playerid;
	
	//玩家等级
	private short playerlv;
	
	//摊位号（只用于排序）
	private int stallsid;
	
	//摊位等级，0，1，2，3
	private byte stallslv;
	
	//摊位名字(如果为空，前端自行组合)
	private String stallsname;
	
	//出售金币数量
	private int sellgold;
	
	//出售元宝数量
	private int sellyuanbao;
	
	//出售道具数量
	private byte sellgoodsnum;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家名字
		writeString(buf, this.playername);
		//玩家ID
		writeLong(buf, this.playerid);
		//玩家等级
		writeShort(buf, this.playerlv);
		//摊位号（只用于排序）
		writeInt(buf, this.stallsid);
		//摊位等级，0，1，2，3
		writeByte(buf, this.stallslv);
		//摊位名字(如果为空，前端自行组合)
		writeString(buf, this.stallsname);
		//出售金币数量
		writeInt(buf, this.sellgold);
		//出售元宝数量
		writeInt(buf, this.sellyuanbao);
		//出售道具数量
		writeByte(buf, this.sellgoodsnum);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家名字
		this.playername = readString(buf);
		//玩家ID
		this.playerid = readLong(buf);
		//玩家等级
		this.playerlv = readShort(buf);
		//摊位号（只用于排序）
		this.stallsid = readInt(buf);
		//摊位等级，0，1，2，3
		this.stallslv = readByte(buf);
		//摊位名字(如果为空，前端自行组合)
		this.stallsname = readString(buf);
		//出售金币数量
		this.sellgold = readInt(buf);
		//出售元宝数量
		this.sellyuanbao = readInt(buf);
		//出售道具数量
		this.sellgoodsnum = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家名字
	 * @return 
	 */
	public String getPlayername(){
		return playername;
	}
	
	/**
	 * set 玩家名字
	 */
	public void setPlayername(String playername){
		this.playername = playername;
	}
	
	/**
	 * get 玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 玩家等级
	 * @return 
	 */
	public short getPlayerlv(){
		return playerlv;
	}
	
	/**
	 * set 玩家等级
	 */
	public void setPlayerlv(short playerlv){
		this.playerlv = playerlv;
	}
	
	/**
	 * get 摊位号（只用于排序）
	 * @return 
	 */
	public int getStallsid(){
		return stallsid;
	}
	
	/**
	 * set 摊位号（只用于排序）
	 */
	public void setStallsid(int stallsid){
		this.stallsid = stallsid;
	}
	
	/**
	 * get 摊位等级，0，1，2，3
	 * @return 
	 */
	public byte getStallslv(){
		return stallslv;
	}
	
	/**
	 * set 摊位等级，0，1，2，3
	 */
	public void setStallslv(byte stallslv){
		this.stallslv = stallslv;
	}
	
	/**
	 * get 摊位名字(如果为空，前端自行组合)
	 * @return 
	 */
	public String getStallsname(){
		return stallsname;
	}
	
	/**
	 * set 摊位名字(如果为空，前端自行组合)
	 */
	public void setStallsname(String stallsname){
		this.stallsname = stallsname;
	}
	
	/**
	 * get 出售金币数量
	 * @return 
	 */
	public int getSellgold(){
		return sellgold;
	}
	
	/**
	 * set 出售金币数量
	 */
	public void setSellgold(int sellgold){
		this.sellgold = sellgold;
	}
	
	/**
	 * get 出售元宝数量
	 * @return 
	 */
	public int getSellyuanbao(){
		return sellyuanbao;
	}
	
	/**
	 * set 出售元宝数量
	 */
	public void setSellyuanbao(int sellyuanbao){
		this.sellyuanbao = sellyuanbao;
	}
	
	/**
	 * get 出售道具数量
	 * @return 
	 */
	public byte getSellgoodsnum(){
		return sellgoodsnum;
	}
	
	/**
	 * set 出售道具数量
	 */
	public void setSellgoodsnum(byte sellgoodsnum){
		this.sellgoodsnum = sellgoodsnum;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//玩家等级
		buf.append("playerlv:" + playerlv +",");
		//摊位号（只用于排序）
		buf.append("stallsid:" + stallsid +",");
		//摊位等级，0，1，2，3
		buf.append("stallslv:" + stallslv +",");
		//摊位名字(如果为空，前端自行组合)
		if(this.stallsname!=null) buf.append("stallsname:" + stallsname.toString() +",");
		//出售金币数量
		buf.append("sellgold:" + sellgold +",");
		//出售元宝数量
		buf.append("sellyuanbao:" + sellyuanbao +",");
		//出售道具数量
		buf.append("sellgoodsnum:" + sellgoodsnum +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
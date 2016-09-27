package com.game.stalls.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 摊位信息（发送前端）
 */
public class StallsInfo extends Bean {

	//玩家名字
	private String playername;
	
	//玩家ID
	private long playerid;
	
	//摊位等级，0，1，2，3
	private byte stallslv;
	
	//摊位名字
	private String stallsname;
	
	//摊位物品信息列表
	private List<StallsGoodsInfo> stallsgoodsinfo = new ArrayList<StallsGoodsInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家名字
		writeString(buf, this.playername);
		//玩家ID
		writeLong(buf, this.playerid);
		//摊位等级，0，1，2，3
		writeByte(buf, this.stallslv);
		//摊位名字
		writeString(buf, this.stallsname);
		//摊位物品信息列表
		writeShort(buf, stallsgoodsinfo.size());
		for (int i = 0; i < stallsgoodsinfo.size(); i++) {
			writeBean(buf, stallsgoodsinfo.get(i));
		}
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
		//摊位等级，0，1，2，3
		this.stallslv = readByte(buf);
		//摊位名字
		this.stallsname = readString(buf);
		//摊位物品信息列表
		int stallsgoodsinfo_length = readShort(buf);
		for (int i = 0; i < stallsgoodsinfo_length; i++) {
			stallsgoodsinfo.add((StallsGoodsInfo)readBean(buf, StallsGoodsInfo.class));
		}
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
	 * get 摊位名字
	 * @return 
	 */
	public String getStallsname(){
		return stallsname;
	}
	
	/**
	 * set 摊位名字
	 */
	public void setStallsname(String stallsname){
		this.stallsname = stallsname;
	}
	
	/**
	 * get 摊位物品信息列表
	 * @return 
	 */
	public List<StallsGoodsInfo> getStallsgoodsinfo(){
		return stallsgoodsinfo;
	}
	
	/**
	 * set 摊位物品信息列表
	 */
	public void setStallsgoodsinfo(List<StallsGoodsInfo> stallsgoodsinfo){
		this.stallsgoodsinfo = stallsgoodsinfo;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//玩家名字
		if(this.playername!=null) buf.append("playername:" + playername.toString() +",");
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//摊位等级，0，1，2，3
		buf.append("stallslv:" + stallslv +",");
		//摊位名字
		if(this.stallsname!=null) buf.append("stallsname:" + stallsname.toString() +",");
		//摊位物品信息列表
		buf.append("stallsgoodsinfo:{");
		for (int i = 0; i < stallsgoodsinfo.size(); i++) {
			buf.append(stallsgoodsinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
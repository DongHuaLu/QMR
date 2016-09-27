package com.game.zones.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 副本报名名单
 */
public class ZoneApplyDataInfo extends Bean {

	//副本id
	private int zoneid;
	
	//玩家名字列表
	private List<String> playernamelist = new ArrayList<String>();
	//玩家等级列表
	private List<Integer> playerlvlist = new ArrayList<Integer>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//副本id
		writeInt(buf, this.zoneid);
		//玩家名字列表
		writeShort(buf, playernamelist.size());
		for (int i = 0; i < playernamelist.size(); i++) {
			writeString(buf, playernamelist.get(i));
		}
		//玩家等级列表
		writeShort(buf, playerlvlist.size());
		for (int i = 0; i < playerlvlist.size(); i++) {
			writeInt(buf, playerlvlist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//副本id
		this.zoneid = readInt(buf);
		//玩家名字列表
		int playernamelist_length = readShort(buf);
		for (int i = 0; i < playernamelist_length; i++) {
			playernamelist.add(readString(buf));
		}
		//玩家等级列表
		int playerlvlist_length = readShort(buf);
		for (int i = 0; i < playerlvlist_length; i++) {
			playerlvlist.add(readInt(buf));
		}
		return true;
	}
	
	/**
	 * get 副本id
	 * @return 
	 */
	public int getZoneid(){
		return zoneid;
	}
	
	/**
	 * set 副本id
	 */
	public void setZoneid(int zoneid){
		this.zoneid = zoneid;
	}
	
	/**
	 * get 玩家名字列表
	 * @return 
	 */
	public List<String> getPlayernamelist(){
		return playernamelist;
	}
	
	/**
	 * set 玩家名字列表
	 */
	public void setPlayernamelist(List<String> playernamelist){
		this.playernamelist = playernamelist;
	}
	
	/**
	 * get 玩家等级列表
	 * @return 
	 */
	public List<Integer> getPlayerlvlist(){
		return playerlvlist;
	}
	
	/**
	 * set 玩家等级列表
	 */
	public void setPlayerlvlist(List<Integer> playerlvlist){
		this.playerlvlist = playerlvlist;
	}
	
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//副本id
		buf.append("zoneid:" + zoneid +",");
		//玩家名字列表
		buf.append("playernamelist:{");
		for (int i = 0; i < playernamelist.size(); i++) {
			buf.append(playernamelist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//玩家等级列表
		buf.append("playerlvlist:{");
		for (int i = 0; i < playerlvlist.size(); i++) {
			buf.append(playerlvlist.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
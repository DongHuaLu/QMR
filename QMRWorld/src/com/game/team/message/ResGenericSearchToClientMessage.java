package com.game.team.message;

import com.game.team.bean.SearchPlayerInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 搜索到的玩家列表，返回给前端消息
 */
public class ResGenericSearchToClientMessage extends Message{

	//玩家列表
	private List<SearchPlayerInfo> playerinfolist = new ArrayList<SearchPlayerInfo>();
	//面板类型(返回给前端)，1组队，2好友，3行会，4聊天
	private byte paneltype;
	
	//排序，1按照等级
	private byte sort;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家列表
		writeShort(buf, playerinfolist.size());
		for (int i = 0; i < playerinfolist.size(); i++) {
			writeBean(buf, playerinfolist.get(i));
		}
		//面板类型(返回给前端)，1组队，2好友，3行会，4聊天
		writeByte(buf, this.paneltype);
		//排序，1按照等级
		writeByte(buf, this.sort);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家列表
		int playerinfolist_length = readShort(buf);
		for (int i = 0; i < playerinfolist_length; i++) {
			playerinfolist.add((SearchPlayerInfo)readBean(buf, SearchPlayerInfo.class));
		}
		//面板类型(返回给前端)，1组队，2好友，3行会，4聊天
		this.paneltype = readByte(buf);
		//排序，1按照等级
		this.sort = readByte(buf);
		return true;
	}
	
	/**
	 * get 玩家列表
	 * @return 
	 */
	public List<SearchPlayerInfo> getPlayerinfolist(){
		return playerinfolist;
	}
	
	/**
	 * set 玩家列表
	 */
	public void setPlayerinfolist(List<SearchPlayerInfo> playerinfolist){
		this.playerinfolist = playerinfolist;
	}
	
	/**
	 * get 面板类型(返回给前端)，1组队，2好友，3行会，4聊天
	 * @return 
	 */
	public byte getPaneltype(){
		return paneltype;
	}
	
	/**
	 * set 面板类型(返回给前端)，1组队，2好友，3行会，4聊天
	 */
	public void setPaneltype(byte paneltype){
		this.paneltype = paneltype;
	}
	
	/**
	 * get 排序，1按照等级
	 * @return 
	 */
	public byte getSort(){
		return sort;
	}
	
	/**
	 * set 排序，1按照等级
	 */
	public void setSort(byte sort){
		this.sort = sort;
	}
	
	
	@Override
	public int getId() {
		return 118111;
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
		//玩家列表
		buf.append("playerinfolist:{");
		for (int i = 0; i < playerinfolist.size(); i++) {
			buf.append(playerinfolist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//面板类型(返回给前端)，1组队，2好友，3行会，4聊天
		buf.append("paneltype:" + paneltype +",");
		//排序，1按照等级
		buf.append("sort:" + sort +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
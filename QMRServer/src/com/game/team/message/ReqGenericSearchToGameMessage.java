package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通用玩家搜索，转发到世界服务器消息
 */
public class ReqGenericSearchToGameMessage extends Message{

	//搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
	private byte type;
	
	//面板类型，1组队，2好友，3行会，4聊天
	private byte paneltype;
	
	//搜索内容
	private String searchcontent;
	
	//索引开始
	private short indexstart;
	
	//索引结束
	private short indexend;
	
	//排序，1按照等级
	private byte sort;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
		writeByte(buf, this.type);
		//面板类型，1组队，2好友，3行会，4聊天
		writeByte(buf, this.paneltype);
		//搜索内容
		writeString(buf, this.searchcontent);
		//索引开始
		writeShort(buf, this.indexstart);
		//索引结束
		writeShort(buf, this.indexend);
		//排序，1按照等级
		writeByte(buf, this.sort);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
		this.type = readByte(buf);
		//面板类型，1组队，2好友，3行会，4聊天
		this.paneltype = readByte(buf);
		//搜索内容
		this.searchcontent = readString(buf);
		//索引开始
		this.indexstart = readShort(buf);
		//索引结束
		this.indexend = readShort(buf);
		//排序，1按照等级
		this.sort = readByte(buf);
		return true;
	}
	
	/**
	 * get 搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	/**
	 * get 面板类型，1组队，2好友，3行会，4聊天
	 * @return 
	 */
	public byte getPaneltype(){
		return paneltype;
	}
	
	/**
	 * set 面板类型，1组队，2好友，3行会，4聊天
	 */
	public void setPaneltype(byte paneltype){
		this.paneltype = paneltype;
	}
	
	/**
	 * get 搜索内容
	 * @return 
	 */
	public String getSearchcontent(){
		return searchcontent;
	}
	
	/**
	 * set 搜索内容
	 */
	public void setSearchcontent(String searchcontent){
		this.searchcontent = searchcontent;
	}
	
	/**
	 * get 索引开始
	 * @return 
	 */
	public short getIndexstart(){
		return indexstart;
	}
	
	/**
	 * set 索引开始
	 */
	public void setIndexstart(short indexstart){
		this.indexstart = indexstart;
	}
	
	/**
	 * get 索引结束
	 * @return 
	 */
	public short getIndexend(){
		return indexend;
	}
	
	/**
	 * set 索引结束
	 */
	public void setIndexend(short indexend){
		this.indexend = indexend;
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
		return 118215;
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
		//搜索类型，0搜索世界，1，搜索本国，2，搜索所有线的当前地图，3搜索本线地图，4搜索所有线本地图未组队的人
		buf.append("type:" + type +",");
		//面板类型，1组队，2好友，3行会，4聊天
		buf.append("paneltype:" + paneltype +",");
		//搜索内容
		if(this.searchcontent!=null) buf.append("searchcontent:" + searchcontent.toString() +",");
		//索引开始
		buf.append("indexstart:" + indexstart +",");
		//索引结束
		buf.append("indexend:" + indexend +",");
		//排序，1按照等级
		buf.append("sort:" + sort +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
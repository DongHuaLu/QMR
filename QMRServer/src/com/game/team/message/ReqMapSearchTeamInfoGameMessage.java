package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 前端请求搜索本地图队伍信息，转发到世界服务器消息
 */
public class ReqMapSearchTeamInfoGameMessage extends Message{

	//搜索内容
	private String searchcontent;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//搜索内容
		writeString(buf, this.searchcontent);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//搜索内容
		this.searchcontent = readString(buf);
		return true;
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
	
	
	@Override
	public int getId() {
		return 118212;
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
		//搜索内容
		if(this.searchcontent!=null) buf.append("searchcontent:" + searchcontent.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
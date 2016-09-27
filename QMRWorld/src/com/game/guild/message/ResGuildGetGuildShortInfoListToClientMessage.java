package com.game.guild.message;

import com.game.guild.bean.GuildShortInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获取帮会简略信息列表返回消息
 */
public class ResGuildGetGuildShortInfoListToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//帮会简略信息列表
	private List<GuildShortInfo> guildShortInfoList = new ArrayList<GuildShortInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//帮会简略信息列表
		writeShort(buf, guildShortInfoList.size());
		for (int i = 0; i < guildShortInfoList.size(); i++) {
			writeBean(buf, guildShortInfoList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//帮会简略信息列表
		int guildShortInfoList_length = readShort(buf);
		for (int i = 0; i < guildShortInfoList_length; i++) {
			guildShortInfoList.add((GuildShortInfo)readBean(buf, GuildShortInfo.class));
		}
		return true;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public byte getBtErrorCode(){
		return btErrorCode;
	}
	
	/**
	 * set 错误代码
	 */
	public void setBtErrorCode(byte btErrorCode){
		this.btErrorCode = btErrorCode;
	}
	
	/**
	 * get 帮会简略信息列表
	 * @return 
	 */
	public List<GuildShortInfo> getGuildShortInfoList(){
		return guildShortInfoList;
	}
	
	/**
	 * set 帮会简略信息列表
	 */
	public void setGuildShortInfoList(List<GuildShortInfo> guildShortInfoList){
		this.guildShortInfoList = guildShortInfoList;
	}
	
	
	@Override
	public int getId() {
		return 121122;
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
		buf.append("btErrorCode:" + btErrorCode +",");
		//帮会简略信息列表
		buf.append("guildShortInfoList:{");
		for (int i = 0; i < guildShortInfoList.size(); i++) {
			buf.append(guildShortInfoList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
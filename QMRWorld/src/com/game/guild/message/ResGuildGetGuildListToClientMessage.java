package com.game.guild.message;

import com.game.guild.bean.GuildInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 获取帮会列表返回消息
 */
public class ResGuildGetGuildListToClientMessage extends Message{

	//错误代码
	private byte btErrorCode;
	
	//所有帮会列表
	private List<GuildInfo> guildList = new ArrayList<GuildInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//错误代码
		writeByte(buf, this.btErrorCode);
		//所有帮会列表
		writeShort(buf, guildList.size());
		for (int i = 0; i < guildList.size(); i++) {
			writeBean(buf, guildList.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//错误代码
		this.btErrorCode = readByte(buf);
		//所有帮会列表
		int guildList_length = readShort(buf);
		for (int i = 0; i < guildList_length; i++) {
			guildList.add((GuildInfo)readBean(buf, GuildInfo.class));
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
	 * get 所有帮会列表
	 * @return 
	 */
	public List<GuildInfo> getGuildList(){
		return guildList;
	}
	
	/**
	 * set 所有帮会列表
	 */
	public void setGuildList(List<GuildInfo> guildList){
		this.guildList = guildList;
	}
	
	
	@Override
	public int getId() {
		return 121103;
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
		//所有帮会列表
		buf.append("guildList:{");
		for (int i = 0; i < guildList.size(); i++) {
			buf.append(guildList.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
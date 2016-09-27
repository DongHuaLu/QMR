package com.game.spirittree.message;

import com.game.spirittree.bean.GuildFruitLog;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 帮会神树操作日志消息
 */
public class ResGuildFruitLogToClientMessage extends Message{

	//帮会神树操作日志列表
	private List<GuildFruitLog> guildfruitlog = new ArrayList<GuildFruitLog>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会神树操作日志列表
		writeShort(buf, guildfruitlog.size());
		for (int i = 0; i < guildfruitlog.size(); i++) {
			writeBean(buf, guildfruitlog.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会神树操作日志列表
		int guildfruitlog_length = readShort(buf);
		for (int i = 0; i < guildfruitlog_length; i++) {
			guildfruitlog.add((GuildFruitLog)readBean(buf, GuildFruitLog.class));
		}
		return true;
	}
	
	/**
	 * get 帮会神树操作日志列表
	 * @return 
	 */
	public List<GuildFruitLog> getGuildfruitlog(){
		return guildfruitlog;
	}
	
	/**
	 * set 帮会神树操作日志列表
	 */
	public void setGuildfruitlog(List<GuildFruitLog> guildfruitlog){
		this.guildfruitlog = guildfruitlog;
	}
	
	
	@Override
	public int getId() {
		return 133107;
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
		//帮会神树操作日志列表
		buf.append("guildfruitlog:{");
		for (int i = 0; i < guildfruitlog.size(); i++) {
			buf.append(guildfruitlog.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
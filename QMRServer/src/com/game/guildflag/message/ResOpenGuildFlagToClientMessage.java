package com.game.guildflag.message;

import com.game.guildflag.bean.GuildFlagInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 发送帮会领地信息消息
 */
public class ResOpenGuildFlagToClientMessage extends Message{

	//帮会领地信息列表
	private List<GuildFlagInfo> challengeInfo = new ArrayList<GuildFlagInfo>();
	//领地战文字提示
	private String status;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//帮会领地信息列表
		writeShort(buf, challengeInfo.size());
		for (int i = 0; i < challengeInfo.size(); i++) {
			writeBean(buf, challengeInfo.get(i));
		}
		//领地战文字提示
		writeString(buf, this.status);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//帮会领地信息列表
		int challengeInfo_length = readShort(buf);
		for (int i = 0; i < challengeInfo_length; i++) {
			challengeInfo.add((GuildFlagInfo)readBean(buf, GuildFlagInfo.class));
		}
		//领地战文字提示
		this.status = readString(buf);
		return true;
	}
	
	/**
	 * get 帮会领地信息列表
	 * @return 
	 */
	public List<GuildFlagInfo> getChallengeInfo(){
		return challengeInfo;
	}
	
	/**
	 * set 帮会领地信息列表
	 */
	public void setChallengeInfo(List<GuildFlagInfo> challengeInfo){
		this.challengeInfo = challengeInfo;
	}
	
	/**
	 * get 领地战文字提示
	 * @return 
	 */
	public String getStatus(){
		return status;
	}
	
	/**
	 * set 领地战文字提示
	 */
	public void setStatus(String status){
		this.status = status;
	}
	
	
	@Override
	public int getId() {
		return 149101;
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
		//帮会领地信息列表
		buf.append("challengeInfo:{");
		for (int i = 0; i < challengeInfo.size(); i++) {
			buf.append(challengeInfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//领地战文字提示
		if(this.status!=null) buf.append("status:" + status.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
package com.game.dataserver.message;

import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 返回排位错误消息
 */
public class ResEnterErrorToGameServerMessage extends Message{

	//角色id
	private long playerId;
	
	//服务器编号
	private int serverId;
	
	//服务器平台
	private String web;
	
	//队伍id
	private long teamId;
	
	//错误代码
	private int error;
	
	//结果参数
	private String paras;
	
	//队伍成员
	private List<Long> members = new ArrayList<Long>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//服务器编号
		writeInt(buf, this.serverId);
		//服务器平台
		writeString(buf, this.web);
		//队伍id
		writeLong(buf, this.teamId);
		//错误代码
		writeInt(buf, this.error);
		//结果参数
		writeString(buf, this.paras);
		//队伍成员
		writeShort(buf, members.size());
		for (int i = 0; i < members.size(); i++) {
			writeLong(buf, members.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//服务器编号
		this.serverId = readInt(buf);
		//服务器平台
		this.web = readString(buf);
		//队伍id
		this.teamId = readLong(buf);
		//错误代码
		this.error = readInt(buf);
		//结果参数
		this.paras = readString(buf);
		//队伍成员
		int members_length = readShort(buf);
		for (int i = 0; i < members_length; i++) {
			members.add(readLong(buf));
		}
		return true;
	}
	
	/**
	 * get 角色id
	 * @return 
	 */
	public long getPlayerId(){
		return playerId;
	}
	
	/**
	 * set 角色id
	 */
	public void setPlayerId(long playerId){
		this.playerId = playerId;
	}
	
	/**
	 * get 服务器编号
	 * @return 
	 */
	public int getServerId(){
		return serverId;
	}
	
	/**
	 * set 服务器编号
	 */
	public void setServerId(int serverId){
		this.serverId = serverId;
	}
	
	/**
	 * get 服务器平台
	 * @return 
	 */
	public String getWeb(){
		return web;
	}
	
	/**
	 * set 服务器平台
	 */
	public void setWeb(String web){
		this.web = web;
	}
	
	/**
	 * get 队伍id
	 * @return 
	 */
	public long getTeamId(){
		return teamId;
	}
	
	/**
	 * set 队伍id
	 */
	public void setTeamId(long teamId){
		this.teamId = teamId;
	}
	
	/**
	 * get 错误代码
	 * @return 
	 */
	public int getError(){
		return error;
	}
	
	/**
	 * set 错误代码
	 */
	public void setError(int error){
		this.error = error;
	}
	
	/**
	 * get 结果参数
	 * @return 
	 */
	public String getParas(){
		return paras;
	}
	
	/**
	 * set 结果参数
	 */
	public void setParas(String paras){
		this.paras = paras;
	}
	
	/**
	 * get 队伍成员
	 * @return 
	 */
	public List<Long> getMembers(){
		return members;
	}
	
	/**
	 * set 队伍成员
	 */
	public void setMembers(List<Long> members){
		this.members = members;
	}
	
	
	@Override
	public int getId() {
		return 203331;
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
		//角色id
		buf.append("playerId:" + playerId +",");
		//服务器编号
		buf.append("serverId:" + serverId +",");
		//服务器平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//队伍id
		buf.append("teamId:" + teamId +",");
		//错误代码
		buf.append("error:" + error +",");
		//结果参数
		if(this.paras!=null) buf.append("paras:" + paras.toString() +",");
		//队伍成员
		buf.append("members:{");
		for (int i = 0; i < members.size(); i++) {
			buf.append(members.get(i) +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
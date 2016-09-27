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
 * 队伍报名校验结果返回（游戏服务器到公共数据服务器）消息
 */
public class ResCheckTeamEnterToDataServerMessage extends Message{

	//角色id
	private long playerId;
	
	//服务器编号
	private int serverId;
	
	//服务器平台
	private String web;
	
	//队伍id
	private long teamId;
	
	//公共服队伍id
	private long dataServerTeamId;
	
	//比赛id
	private long entryId;
	
	//队伍报名校验结果
	private int result;
	
	//队伍成员校验结果
	private List<Integer> members = new ArrayList<Integer>();
	
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
		//公共服队伍id
		writeLong(buf, this.dataServerTeamId);
		//比赛id
		writeLong(buf, this.entryId);
		//队伍报名校验结果
		writeInt(buf, this.result);
		//队伍成员校验结果
		writeShort(buf, members.size());
		for (int i = 0; i < members.size(); i++) {
			writeInt(buf, members.get(i));
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
		//公共服队伍id
		this.dataServerTeamId = readLong(buf);
		//比赛id
		this.entryId = readLong(buf);
		//队伍报名校验结果
		this.result = readInt(buf);
		//队伍成员校验结果
		int members_length = readShort(buf);
		for (int i = 0; i < members_length; i++) {
			members.add(readInt(buf));
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
	 * get 公共服队伍id
	 * @return 
	 */
	public long getDataServerTeamId(){
		return dataServerTeamId;
	}
	
	/**
	 * set 公共服队伍id
	 */
	public void setDataServerTeamId(long dataServerTeamId){
		this.dataServerTeamId = dataServerTeamId;
	}
	
	/**
	 * get 比赛id
	 * @return 
	 */
	public long getEntryId(){
		return entryId;
	}
	
	/**
	 * set 比赛id
	 */
	public void setEntryId(long entryId){
		this.entryId = entryId;
	}
	
	/**
	 * get 队伍报名校验结果
	 * @return 
	 */
	public int getResult(){
		return result;
	}
	
	/**
	 * set 队伍报名校验结果
	 */
	public void setResult(int result){
		this.result = result;
	}
	
	/**
	 * get 队伍成员校验结果
	 * @return 
	 */
	public List<Integer> getMembers(){
		return members;
	}
	
	/**
	 * set 队伍成员校验结果
	 */
	public void setMembers(List<Integer> members){
		this.members = members;
	}
	
	
	@Override
	public int getId() {
		return 203322;
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
		//公共服队伍id
		buf.append("dataServerTeamId:" + dataServerTeamId +",");
		//比赛id
		buf.append("entryId:" + entryId +",");
		//队伍报名校验结果
		buf.append("result:" + result +",");
		//队伍成员校验结果
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
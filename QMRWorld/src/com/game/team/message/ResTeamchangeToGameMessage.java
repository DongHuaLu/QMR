package com.game.team.message;

import com.game.team.bean.TeamMemberInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 队伍改变发送给改变的人消息
 */
public class ResTeamchangeToGameMessage extends Message{

	//玩家ID
	private long playerid;
	
	//队伍Id
	private long teamid;
	
	//队伍列表
	private List<TeamMemberInfo> memberinfo = new ArrayList<TeamMemberInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//玩家ID
		writeLong(buf, this.playerid);
		//队伍Id
		writeLong(buf, this.teamid);
		//队伍列表
		writeShort(buf, memberinfo.size());
		for (int i = 0; i < memberinfo.size(); i++) {
			writeBean(buf, memberinfo.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//玩家ID
		this.playerid = readLong(buf);
		//队伍Id
		this.teamid = readLong(buf);
		//队伍列表
		int memberinfo_length = readShort(buf);
		for (int i = 0; i < memberinfo_length; i++) {
			memberinfo.add((TeamMemberInfo)readBean(buf, TeamMemberInfo.class));
		}
		return true;
	}
	
	/**
	 * get 玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 队伍Id
	 * @return 
	 */
	public long getTeamid(){
		return teamid;
	}
	
	/**
	 * set 队伍Id
	 */
	public void setTeamid(long teamid){
		this.teamid = teamid;
	}
	
	/**
	 * get 队伍列表
	 * @return 
	 */
	public List<TeamMemberInfo> getMemberinfo(){
		return memberinfo;
	}
	
	/**
	 * set 队伍列表
	 */
	public void setMemberinfo(List<TeamMemberInfo> memberinfo){
		this.memberinfo = memberinfo;
	}
	
	
	@Override
	public int getId() {
		return 118321;
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
		//玩家ID
		buf.append("playerid:" + playerid +",");
		//队伍Id
		buf.append("teamid:" + teamid +",");
		//队伍列表
		buf.append("memberinfo:{");
		for (int i = 0; i < memberinfo.size(); i++) {
			buf.append(memberinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
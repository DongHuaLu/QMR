package com.game.team.message;

import com.game.team.bean.TeamMemberInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 邀请入队-通知玩家做选择消息
 */
public class ResInviteClientMessage extends Message{

	//队伍Id
	private long teamid;
	
	//队长信息
	private TeamMemberInfo captaininfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队伍Id
		writeLong(buf, this.teamid);
		//队长信息
		writeBean(buf, this.captaininfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队伍Id
		this.teamid = readLong(buf);
		//队长信息
		this.captaininfo = (TeamMemberInfo)readBean(buf, TeamMemberInfo.class);
		return true;
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
	 * get 队长信息
	 * @return 
	 */
	public TeamMemberInfo getCaptaininfo(){
		return captaininfo;
	}
	
	/**
	 * set 队长信息
	 */
	public void setCaptaininfo(TeamMemberInfo captaininfo){
		this.captaininfo = captaininfo;
	}
	
	
	@Override
	public int getId() {
		return 118103;
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
		//队伍Id
		buf.append("teamid:" + teamid +",");
		//队长信息
		if(this.captaininfo!=null) buf.append("captaininfo:" + captaininfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
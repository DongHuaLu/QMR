package com.game.team.message;

import com.game.team.bean.TeamMemberInfo;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 入队申请-通知队长做选择消息
 */
public class ResApplyClientMessage extends Message{

	//队伍Id
	private long teamid;
	
	//新队员信息
	private TeamMemberInfo newmemberinfo;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队伍Id
		writeLong(buf, this.teamid);
		//新队员信息
		writeBean(buf, this.newmemberinfo);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队伍Id
		this.teamid = readLong(buf);
		//新队员信息
		this.newmemberinfo = (TeamMemberInfo)readBean(buf, TeamMemberInfo.class);
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
	 * get 新队员信息
	 * @return 
	 */
	public TeamMemberInfo getNewmemberinfo(){
		return newmemberinfo;
	}
	
	/**
	 * set 新队员信息
	 */
	public void setNewmemberinfo(TeamMemberInfo newmemberinfo){
		this.newmemberinfo = newmemberinfo;
	}
	
	
	@Override
	public int getId() {
		return 118102;
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
		//新队员信息
		if(this.newmemberinfo!=null) buf.append("newmemberinfo:" + newmemberinfo.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
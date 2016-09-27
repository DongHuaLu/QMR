package com.game.team.bean;

import java.util.List;
import java.util.ArrayList;

import com.game.message.Bean;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 组队信息类
 */
public class TeamInfo extends Bean {

	//队伍Id
	private long teamid;
	
	//自动接受入队申请 1自动
	private byte autoIntoteamapply;
	
	//队伍列表
	private List<TeamMemberInfo> memberinfo = new ArrayList<TeamMemberInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队伍Id
		writeLong(buf, this.teamid);
		//自动接受入队申请 1自动
		writeByte(buf, this.autoIntoteamapply);
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
		//队伍Id
		this.teamid = readLong(buf);
		//自动接受入队申请 1自动
		this.autoIntoteamapply = readByte(buf);
		//队伍列表
		int memberinfo_length = readShort(buf);
		for (int i = 0; i < memberinfo_length; i++) {
			memberinfo.add((TeamMemberInfo)readBean(buf, TeamMemberInfo.class));
		}
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
	 * get 自动接受入队申请 1自动
	 * @return 
	 */
	public byte getAutoIntoteamapply(){
		return autoIntoteamapply;
	}
	
	/**
	 * set 自动接受入队申请 1自动
	 */
	public void setAutoIntoteamapply(byte autoIntoteamapply){
		this.autoIntoteamapply = autoIntoteamapply;
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
	public String toString(){
		StringBuffer buf = new StringBuffer("[");
		//队伍Id
		buf.append("teamid:" + teamid +",");
		//自动接受入队申请 1自动
		buf.append("autoIntoteamapply:" + autoIntoteamapply +",");
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
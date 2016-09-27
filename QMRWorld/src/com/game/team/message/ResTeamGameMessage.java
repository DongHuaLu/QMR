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
 * 下发队伍更新到GAME(通用)消息
 */
public class ResTeamGameMessage extends Message{

	//队伍Id
	private long teamid;
	
	//队伍列表
	private List<TeamMemberInfo> memberinfo = new ArrayList<TeamMemberInfo>();
	//0更新外观 ，1 更新到GAME 
	private byte type;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队伍Id
		writeLong(buf, this.teamid);
		//队伍列表
		writeShort(buf, memberinfo.size());
		for (int i = 0; i < memberinfo.size(); i++) {
			writeBean(buf, memberinfo.get(i));
		}
		//0更新外观 ，1 更新到GAME 
		writeByte(buf, this.type);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队伍Id
		this.teamid = readLong(buf);
		//队伍列表
		int memberinfo_length = readShort(buf);
		for (int i = 0; i < memberinfo_length; i++) {
			memberinfo.add((TeamMemberInfo)readBean(buf, TeamMemberInfo.class));
		}
		//0更新外观 ，1 更新到GAME 
		this.type = readByte(buf);
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
	
	/**
	 * get 0更新外观 ，1 更新到GAME 
	 * @return 
	 */
	public byte getType(){
		return type;
	}
	
	/**
	 * set 0更新外观 ，1 更新到GAME 
	 */
	public void setType(byte type){
		this.type = type;
	}
	
	
	@Override
	public int getId() {
		return 118302;
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
		//队伍列表
		buf.append("memberinfo:{");
		for (int i = 0; i < memberinfo.size(); i++) {
			buf.append(memberinfo.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		//0更新外观 ，1 更新到GAME 
		buf.append("type:" + type +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
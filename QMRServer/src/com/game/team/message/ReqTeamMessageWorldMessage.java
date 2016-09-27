package com.game.team.message;

import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 全队伍广播提示信息，转发到世界服务器消息
 */
public class ReqTeamMessageWorldMessage extends Message{

	//队伍Id
	private long teamid;
	
	//消息内容
	private String content;
	
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//队伍Id
		writeLong(buf, this.teamid);
		//消息内容
		writeString(buf, this.content);
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//队伍Id
		this.teamid = readLong(buf);
		//消息内容
		this.content = readString(buf);
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
	 * get 消息内容
	 * @return 
	 */
	public String getContent(){
		return content;
	}
	
	/**
	 * set 消息内容
	 */
	public void setContent(String content){
		this.content = content;
	}
	
	
	@Override
	public int getId() {
		return 118318;
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
		//消息内容
		if(this.content!=null) buf.append("content:" + content.toString() +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
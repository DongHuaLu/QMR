package com.game.marriage.message;

import com.game.marriage.bean.LeaveMsgInfo;
import java.util.List;
import java.util.ArrayList;
import com.game.message.Message;

import org.apache.mina.core.buffer.IoBuffer;

/** 
 * @author Commuication Auto Maker
 * 
 * @version 1.0.0
 * 
 * 通知新留言消息
 */
public class ResNoticeNewLeaveMsgToClientMessage extends Message{

	//被通知玩家ID
	private long playerid;
	
	//留言列表
	private List<LeaveMsgInfo> leavemsglist = new ArrayList<LeaveMsgInfo>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//被通知玩家ID
		writeLong(buf, this.playerid);
		//留言列表
		writeShort(buf, leavemsglist.size());
		for (int i = 0; i < leavemsglist.size(); i++) {
			writeBean(buf, leavemsglist.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//被通知玩家ID
		this.playerid = readLong(buf);
		//留言列表
		int leavemsglist_length = readShort(buf);
		for (int i = 0; i < leavemsglist_length; i++) {
			leavemsglist.add((LeaveMsgInfo)readBean(buf, LeaveMsgInfo.class));
		}
		return true;
	}
	
	/**
	 * get 被通知玩家ID
	 * @return 
	 */
	public long getPlayerid(){
		return playerid;
	}
	
	/**
	 * set 被通知玩家ID
	 */
	public void setPlayerid(long playerid){
		this.playerid = playerid;
	}
	
	/**
	 * get 留言列表
	 * @return 
	 */
	public List<LeaveMsgInfo> getLeavemsglist(){
		return leavemsglist;
	}
	
	/**
	 * set 留言列表
	 */
	public void setLeavemsglist(List<LeaveMsgInfo> leavemsglist){
		this.leavemsglist = leavemsglist;
	}
	
	
	@Override
	public int getId() {
		return 163111;
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
		//被通知玩家ID
		buf.append("playerid:" + playerid +",");
		//留言列表
		buf.append("leavemsglist:{");
		for (int i = 0; i < leavemsglist.size(); i++) {
			buf.append(leavemsglist.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
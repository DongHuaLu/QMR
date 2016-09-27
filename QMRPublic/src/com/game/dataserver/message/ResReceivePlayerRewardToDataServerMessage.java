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
 * 返回游戏服务器玩家奖励信息（公共数据服务器到游戏服务器）消息
 */
public class ResReceivePlayerRewardToDataServerMessage extends Message{

	//角色id
	private long playerId;
	
	//账号
	private String userId;
	
	//服务器平台
	private String web;
	
	//奖励数据
	private List<String> reward = new ArrayList<String>();
	
	/**
	 * 写入字节缓存
	 */
	public boolean write(IoBuffer buf){
		//角色id
		writeLong(buf, this.playerId);
		//账号
		writeString(buf, this.userId);
		//服务器平台
		writeString(buf, this.web);
		//奖励数据
		writeShort(buf, reward.size());
		for (int i = 0; i < reward.size(); i++) {
			writeString(buf, reward.get(i));
		}
		return true;
	}
	
	/**
	 * 读取字节缓存
	 */
	public boolean read(IoBuffer buf){
		//角色id
		this.playerId = readLong(buf);
		//账号
		this.userId = readString(buf);
		//服务器平台
		this.web = readString(buf);
		//奖励数据
		int reward_length = readShort(buf);
		for (int i = 0; i < reward_length; i++) {
			reward.add(readString(buf));
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
	 * get 账号
	 * @return 
	 */
	public String getUserId(){
		return userId;
	}
	
	/**
	 * set 账号
	 */
	public void setUserId(String userId){
		this.userId = userId;
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
	 * get 奖励数据
	 * @return 
	 */
	public List<String> getReward(){
		return reward;
	}
	
	/**
	 * set 奖励数据
	 */
	public void setReward(List<String> reward){
		this.reward = reward;
	}
	
	
	@Override
	public int getId() {
		return 203311;
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
		//账号
		if(this.userId!=null) buf.append("userId:" + userId.toString() +",");
		//服务器平台
		if(this.web!=null) buf.append("web:" + web.toString() +",");
		//奖励数据
		buf.append("reward:{");
		for (int i = 0; i < reward.size(); i++) {
			buf.append(reward.get(i).toString() +",");
		}
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("},");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
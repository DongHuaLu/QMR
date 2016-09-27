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
 * 返回请求公共服务器玩家奖励信息（查看）（公共数据服务器到游戏服务器）消息
 */
public class ResPlayerRewardFromDataServerMessage extends Message{

	//角色id
	private long playerId;
	
	//账号
	private String userId;
	
	//服务器平台
	private String web;
	
	//奖励数据
	private List<String> reward = new ArrayList<String>();
	//返回是否需要现在跨服 0-不需要 1-需要
	private int result;
	
	//当前连胜次数
	private int currwinningstreak;
	
	//总连胜次数
	private int winningstreak;
	
	//最近场次胜负记录
	private int recvord;
	
	//今日连胜次数
	private int dayconvictory;
	
	//今日最大连胜次数
	private int dayconvictorymax;
	
	
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
		//返回是否需要现在跨服 0-不需要 1-需要
		writeInt(buf, this.result);
		//当前连胜次数
		writeInt(buf, this.currwinningstreak);
		//总连胜次数
		writeInt(buf, this.winningstreak);
		//最近场次胜负记录
		writeInt(buf, this.recvord);
		//今日连胜次数
		writeInt(buf, this.dayconvictory);
		//今日最大连胜次数
		writeInt(buf, this.dayconvictorymax);
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
		//返回是否需要现在跨服 0-不需要 1-需要
		this.result = readInt(buf);
		//当前连胜次数
		this.currwinningstreak = readInt(buf);
		//总连胜次数
		this.winningstreak = readInt(buf);
		//最近场次胜负记录
		this.recvord = readInt(buf);
		//今日连胜次数
		this.dayconvictory = readInt(buf);
		//今日最大连胜次数
		this.dayconvictorymax = readInt(buf);
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
	
	/**
	 * get 返回是否需要现在跨服 0-不需要 1-需要
	 * @return 
	 */
	public int getResult(){
		return result;
	}
	
	/**
	 * set 返回是否需要现在跨服 0-不需要 1-需要
	 */
	public void setResult(int result){
		this.result = result;
	}
	
	/**
	 * get 当前连胜次数
	 * @return 
	 */
	public int getCurrwinningstreak(){
		return currwinningstreak;
	}
	
	/**
	 * set 当前连胜次数
	 */
	public void setCurrwinningstreak(int currwinningstreak){
		this.currwinningstreak = currwinningstreak;
	}
	
	/**
	 * get 总连胜次数
	 * @return 
	 */
	public int getWinningstreak(){
		return winningstreak;
	}
	
	/**
	 * set 总连胜次数
	 */
	public void setWinningstreak(int winningstreak){
		this.winningstreak = winningstreak;
	}
	
	/**
	 * get 最近场次胜负记录
	 * @return 
	 */
	public int getRecvord(){
		return recvord;
	}
	
	/**
	 * set 最近场次胜负记录
	 */
	public void setRecvord(int recvord){
		this.recvord = recvord;
	}
	
	/**
	 * get 今日连胜次数
	 * @return 
	 */
	public int getDayconvictory(){
		return dayconvictory;
	}
	
	/**
	 * set 今日连胜次数
	 */
	public void setDayconvictory(int dayconvictory){
		this.dayconvictory = dayconvictory;
	}
	
	/**
	 * get 今日最大连胜次数
	 * @return 
	 */
	public int getDayconvictorymax(){
		return dayconvictorymax;
	}
	
	/**
	 * set 今日最大连胜次数
	 */
	public void setDayconvictorymax(int dayconvictorymax){
		this.dayconvictorymax = dayconvictorymax;
	}
	
	
	@Override
	public int getId() {
		return 203313;
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
		//返回是否需要现在跨服 0-不需要 1-需要
		buf.append("result:" + result +",");
		//当前连胜次数
		buf.append("currwinningstreak:" + currwinningstreak +",");
		//总连胜次数
		buf.append("winningstreak:" + winningstreak +",");
		//最近场次胜负记录
		buf.append("recvord:" + recvord +",");
		//今日连胜次数
		buf.append("dayconvictory:" + dayconvictory +",");
		//今日最大连胜次数
		buf.append("dayconvictorymax:" + dayconvictorymax +",");
		if(buf.charAt(buf.length()-1)==',') buf.deleteCharAt(buf.length()-1);
		buf.append("]");
		return buf.toString();
	}
}
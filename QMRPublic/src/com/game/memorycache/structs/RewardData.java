package com.game.memorycache.structs;

public class RewardData {
	//奖励id
	private long rewardId;
	//玩家角色id
	private long playerId;
	//数据服务器player id
	private long dataServerPlayerId;
	//玩家账号id
	private String userId;
	//玩家账号所在服务器
	private int serverId;
	//玩家账号平台
	private String web;
	//奖励信息数据
	private String reward;
	//插入数据库时间
	private long time;
	//是否领取
	private int receive;
	
	public long getRewardId() {
		return rewardId;
	}
	public void setRewardId(long rewardId) {
		this.rewardId = rewardId;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public long getDataServerPlayerId() {
		return dataServerPlayerId;
	}
	public void setDataServerPlayerId(long dataServerPlayerId) {
		this.dataServerPlayerId = dataServerPlayerId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getServerId() {
		return serverId;
	}
	public void setServerId(int serverId) {
		this.serverId = serverId;
	}
	public String getWeb() {
		return web;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public String getReward() {
		return reward;
	}
	public void setReward(String reward) {
		this.reward = reward;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getReceive() {
		return receive;
	}
	public void setReceive(int receive) {
		this.receive = receive;
	}
	
}

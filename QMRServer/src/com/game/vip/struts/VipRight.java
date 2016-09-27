package com.game.vip.struts;

import com.game.data.bean.Q_vipBean;
import com.game.data.manager.DataManager;
import com.game.player.structs.Player;
import com.game.vip.manager.VipManager;


//VIP特权
public class VipRight {
	
	private long lastReceiveVipRewardTime = 0;  //上次领取礼包时间
	private long lastFreeFlyTime=0;             //上次使用免费传送的时间
	private int  freeflytime = 0;				//免费元宝传送次数
	private int  receivedTopReward=0; 	        //是否领取过至尊VIP的奖励 
	private transient int webVipLevel;				//vip等级（平台,QQ为蓝钻等级）
	private transient int webVipLevel2;				//vip等级2（平台，3366为包子等级）
	
	public void resetVipRight(Player player){
		int vipid = VipManager.getInstance().getPlayerVipId(player);
		if(vipid>0){
			Q_vipBean bean = DataManager.getInstance().q_vipContainer.getMap().get(vipid);
			freeflytime = bean.getQ_fly();
		}
	}
	public long getLastReceiveVipRewardTime() {
		return lastReceiveVipRewardTime;
	}


	public void setLastReceiveVipRewardTime(long lastReceiveVipRewardTime) {
		this.lastReceiveVipRewardTime = lastReceiveVipRewardTime;
	}

	public int getReceivedTopReward() {
		return receivedTopReward;
	}
	public void setReceivedTopReward(int receivedTopReward) {
		this.receivedTopReward = receivedTopReward;
	}
	public long getLastFreeFlyTime() {
		return lastFreeFlyTime;
	}
	public void setLastFreeFlyTime(long lastFreeFlyTime) {
		this.lastFreeFlyTime = lastFreeFlyTime;
	}
	public int getFreeflytime() {
		return freeflytime;
	}
	public void setFreeflytime(int freeflytime) {
		this.freeflytime = freeflytime;
	}
	public int getWebVipLevel() {
		return webVipLevel;
	}
	public void setWebVipLevel(int webVipLevel) {
		this.webVipLevel = webVipLevel;
	}
	public int getWebVipLevel2() {
		return webVipLevel2;
	}
	public void setWebVipLevel2(int webVipLevel2) {
		this.webVipLevel2 = webVipLevel2;
	}
	
}

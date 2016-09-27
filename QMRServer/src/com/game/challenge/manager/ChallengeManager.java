package com.game.challenge.manager;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.biwudao.message.ResBiWuDaoStatusShowToClientMessage;
import com.game.challenge.bean.ChallengeInfo;
import com.game.challenge.bean.ChallengeRewardInfo;
import com.game.challenge.message.ReqOpenChallengeToGameMessage;
import com.game.challenge.message.ReqSelectChallengeToGameMessage;
import com.game.challenge.message.ResOpenChallengeToClientMessage;
import com.game.challenge.message.ResRewardChallengeToClientMessage;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_vipBean;
import com.game.data.manager.DataManager;
import com.game.epalace.structs.Epalace;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;
import com.game.vip.manager.VipManager;


public class ChallengeManager {
	private static Object obj = new Object();
	protected Logger log = Logger.getLogger(ChallengeManager.class);
	//玩家管理类实例
	private static ChallengeManager manager;
	
	private ChallengeManager(){}
	
	public static ChallengeManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new ChallengeManager();
			}
		}
		return manager;
	}


	
	
	/**选择挑战面板
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqSelectChallengeToGameMessage(Player player ,ReqSelectChallengeToGameMessage msg) {
		if (msg.getType() == 1) {
			ManagerPool.zonesFlopManager.LoginRaidRewarded(player);//检测是否可领奖励
		}else if (msg.getType() == 6 ) {//迷宫进入前的展示信息
			ResRewardChallengeToClientMessage cmsg = new ResRewardChallengeToClientMessage();
			HashMap<Integer, Integer> map1 = ManagerPool.zonesFlopManager.getZoneCountReward(player , 7 , 0,false);
			HashMap<Integer, Integer> map2 = ManagerPool.zonesFlopManager.getZoneCountReward(player , 7 , 1,false);
			Iterator<Entry<Integer, Integer>> it1 = map1.entrySet().iterator();
			while (it1.hasNext()) {
				Entry<Integer, Integer> entry = (Entry<Integer, Integer>) it1.next();
				ChallengeRewardInfo info = new ChallengeRewardInfo();
				info.setId(entry.getKey());
				info.setNum(entry.getValue());
				info.setType(0);
				cmsg.getRewardInfo().add(info);
			}
			Iterator<Entry<Integer, Integer>> it2 = map2.entrySet().iterator();
			while (it2.hasNext()) {
				Entry<Integer, Integer> entry = (Entry<Integer, Integer>) it2.next();
				ChallengeRewardInfo info = new ChallengeRewardInfo();
				info.setId(entry.getKey());
				info.setNum(entry.getValue());
				info.setType(1);
				cmsg.getRewardInfo().add(info);
			}
			MessageUtil.tell_player_message(player, cmsg);
		}else if (msg.getType() == 10) {	//比武岛活动状态
			ResBiWuDaoStatusShowToClientMessage cmsg = new ResBiWuDaoStatusShowToClientMessage();
			cmsg.setStatusshow( ManagerPool.biWuDaoManager.getbiwudaotimeinfo());
			MessageUtil.tell_player_message(player, cmsg);
		}
			
	}

	
	/**
	 * 打开挑战面板消息
	 * @param player
	 * @param msg
	 */
	public void stReqOpenChallengeToGameMessage(Player player,ReqOpenChallengeToGameMessage msg) {
		ChallengeInfo challengeInfo = new  ChallengeInfo();
		
		//地宫剩余次数
		Epalace epalace = player.getEpalace();
		ManagerPool.epalaceManeger.restorationEpalaceTime(player);
		if (player.getLevel() >= 40) {
			if (epalace.getMovenum() > 0) {
				challengeInfo.setEpalacenum(ResManager.getInstance().getString("剩余")+epalace.getMovenum()+ResManager.getInstance().getString("次"));
			}else {
				int ms = (int)(System.currentTimeMillis()/1000)-epalace.getTime();
				int shengyu = 2*60*60 - ms;
				String timestr = TimeUtil.millisecondToStr(shengyu*1000);
				challengeInfo.setEpalacenum(timestr+ResManager.getInstance().getString("后增加次数"));
			}
		}else{
			challengeInfo.setEpalacenum(ResManager.getInstance().getString("达到40级后开放"));
		}

		
		//VIP附加副本次数
		int vipnum = 0;
		int vipid = VipManager.getInstance().getPlayerVipId(player);
		if(vipid>0){
			Q_vipBean vipdb = DataManager.getInstance().q_vipContainer.getMap().get(vipid);
			if (vipdb != null) {
				vipnum = vipdb.getQ_zone_time();
			}
		}
		
		//世界地图副本总次数
		List<Q_clone_activityBean> data = ManagerPool.dataManager.q_clone_activityContainer.getList();
		long sum = 0;
		long manual = 0;
		for (Q_clone_activityBean q_clone_activityBean : data) {
			if (q_clone_activityBean.getQ_zone_type() == 1) {
				if (player.getLevel() >= q_clone_activityBean.getQ_min_lv()) {
					sum = vipnum + sum + q_clone_activityBean.getQ_manual_num();
					manual =   manual + ManagerPool.countManager.getCount(player, CountTypes.ZONE_MANUAL, ""+q_clone_activityBean.getQ_id());	
				}
			}
		}
		if (sum - manual >  0) {
			challengeInfo.setZonesnum((int) (sum - manual));	
		}
		
		//校场剩余次数
		Q_clone_activityBean jiaochangdata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(3);
		long jiaochang = ManagerPool.countManager.getCount(player, CountTypes.JIAOCHANG_NUM, "4003");
		challengeInfo.setJiaochangnum((int) (jiaochangdata.getQ_manual_num() - jiaochang));
		long min = TimeUtil.getDayOfMin(System.currentTimeMillis());
		long hour =TimeUtil.getDayOfHour(System.currentTimeMillis());
		long week =TimeUtil.getDayOfWeek(System.currentTimeMillis());
		//[*][*][*][*][8:30-8:30];[*][*][*][*][12:30-12:30];[*][*][*][*][18:30-18:30];[*][*][*][*][23:00-23:00]
		
		if (hour < 10 || (hour == 10 && min <= 30)) {
			challengeInfo.setBosstime("10:30");
		}else if (hour < 14 || (hour == 14 && min <= 30)) {
			challengeInfo.setBosstime("14:30");
		}else if (hour < 18 || (hour == 18 && min <= 30)) {
			challengeInfo.setBosstime("18:30");
		}else if (hour < 22 || (hour == 22 && min <= 30))  {
			challengeInfo.setBosstime("22:30");
		}else{
			challengeInfo.setBosstime("10:30");
		}
		
		
		challengeInfo.setLingditime(ResManager.getInstance().getString("暂未开放"));
		challengeInfo.setGongchengtime(ResManager.getInstance().getString("暂未开放"));
		
		if (week  >= 6) {
			challengeInfo.setDoubletime("10:00-20:00");
		}else {
			if (hour <= 12 ) {
				challengeInfo.setDoubletime("12:00-13:00");
			}else {
				challengeInfo.setDoubletime("19:00-20:00");
			}
		}
		
		challengeInfo.setMazetime(ResManager.getInstance().getString("周三，周四20:30-21:00")); //迷宫

		challengeInfo.setGongchengtime(ManagerPool.countryManager.getstrtimeinfo(0));	//攻城战
		challengeInfo.setBiwudaotime(ResManager.getInstance().getString("周三，周日21:00-21:30"));
		ResOpenChallengeToClientMessage cmsg = new ResOpenChallengeToClientMessage();
		cmsg.setChallengeInfo(challengeInfo);
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	

	
	
	
	
	
	
}

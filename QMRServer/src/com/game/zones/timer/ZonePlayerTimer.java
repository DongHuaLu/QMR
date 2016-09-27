package com.game.zones.timer;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_clone_activityBean;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;
import com.game.zones.log.ZoneLog;
import com.game.zones.message.ResAutoRaidInfoMessage;
import com.game.zones.message.ResZoneContinuousRaidsEndMessage;
import com.game.zones.message.ResZoneContinuousRaidsMessage;
import com.game.zones.structs.ContinuousRaidsInfo;
import com.game.zones.structs.Raid;

/**自动扫荡计时器
 * 
 * @author zhangrong
 *
 */
public class ZonePlayerTimer extends TimerEvent{

	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public ZonePlayerTimer(int serverId, int lineId, int mapId) {
		super(-1, 1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId = mapId;
	}
	
	private Logger log = Logger.getLogger(ZonePlayerTimer.class);
	
	
	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		//遍历玩家列表
		Iterator<Player> iter = map.getPlayers().values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			//不是本线玩家
			if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
			//RaidFlop raidflop = player.getRaidflop();
			Raid raidinfo = player.getRaidinfo();
			
			int systime = (int)(System.currentTimeMillis()/1000);
			try {

				if ( raidinfo.getQiyaoraidzonemodelid() > 0 && raidinfo.getQiyaoraidautoendtime() > 0 && raidinfo.getQiyaoraidautoendtime() <= systime) {
					int zonemodelid = raidinfo.getQiyaoraidzonemodelid();
					ResAutoRaidInfoMessage reidmsg = new ResAutoRaidInfoMessage();
					ManagerPool.zonesFlopManager.addZoneReward(player,zonemodelid, true);
					raidinfo.setQiyaoraidautoendtime(0);
					reidmsg.setSurplustime(0);
					reidmsg.setZoneid(zonemodelid);
					MessageUtil.tell_player_message(player, reidmsg);
					ManagerPool.zonesFlopManager.LoginRaidRewarded(player);
					
					ZoneLog zlog = new ZoneLog();
					zlog.setPlayerid(player.getId());
					zlog.setType(1);
					zlog.setZonemodelid(zonemodelid);
					zlog.setSid(player.getCreateServerId());
					LogService.getInstance().execute(zlog);
				}else {

					//连续自动扫荡
					List<ContinuousRaidsInfo> qiyaocontinuouslist = player.getQiyaocontinuouslist();
					if (qiyaocontinuouslist.size() > 0) {
						int status = 0;
						ContinuousRaidsInfo zuihouinfo = qiyaocontinuouslist.get(qiyaocontinuouslist.size()-1);
						int znum = qiyaocontinuouslist.size();	//当前扫荡副本总数量
						boolean  allend = false;
						for (int j = 0; j < qiyaocontinuouslist.size(); j++) {
							ContinuousRaidsInfo info = qiyaocontinuouslist.get(j);
							int biaoshi = info.getStatus();
							int ms = info.getTime();
							if (biaoshi == 0) {
								if (ms > 0 && systime > ms) {
									znum = znum - 1;
									status = 1;
									info.setStatus(1);	//设置成完成状态
									ManagerPool.zonesFlopManager.addZoneReward(player,info.getZonemodelid(), true);	//增加可领奖次数
									ResZoneContinuousRaidsEndMessage cmsg = new ResZoneContinuousRaidsEndMessage();
									cmsg.setZoneid(info.getZonemodelid());
									cmsg.setZonetype(4);
									if (j == qiyaocontinuouslist.size()-1) {	//检测是否完成最后一个副本
										cmsg.setType((byte) 1);	//全部完成了
										allend = true;
									}
									MessageUtil.tell_player_message(player, cmsg);
	
									ZoneLog zlog = new ZoneLog();
									zlog.setPlayerid(player.getId());
									zlog.setType(2);
									zlog.setZonemodelid(info.getZonemodelid());
									LogService.getInstance().execute(zlog);
									
									Q_clone_activityBean data = ManagerPool.zonesManager.getContainer(info.getZonemodelid());
									MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("【{1}】扫荡完成"), data.getQ_duplicate_name());
								}
							}else {
								znum = znum - 1;
							}
						}	
						
						if (status == 1) {
							//更新连续扫荡信息消息
							int rewnum = 0;
							ResZoneContinuousRaidsMessage conmsg= new ResZoneContinuousRaidsMessage();
							conmsg.setZonenum(znum);
							conmsg.setZonetype(4);
							conmsg.setPassedime(systime - player.getQiyaocontinuoustime());
							conmsg.setSumtime(zuihouinfo.getTime() - player.getQiyaocontinuoustime());
							HashMap<String, Integer> rewardmap = player.getZoneqiyaorewardmap();//可领取奖励次数
							Iterator<Entry<String, Integer>> it = rewardmap.entrySet().iterator();
							while (it.hasNext()) {
								Entry<String, Integer> entry = it.next();
								rewnum = rewnum + entry.getValue();
							}
							conmsg.setRewardnum(rewnum);
							MessageUtil.tell_player_message(player, conmsg);
							
							if (allend) {	//全部完成，清除已经扫荡副本数据
								qiyaocontinuouslist.clear();
							}
						}
					}					
				}
			
			} catch (Exception e) {
				log.error(e,e);
			}
			
			//----------------剧情副本---------------------------
			
			try {
				//单个自动扫荡
				if ( raidinfo.getRaidzonemodelid() > 0 && raidinfo.getRaidautoendtime() > 0 && raidinfo.getRaidautoendtime() <= systime) {
					int zonemodelid = raidinfo.getRaidzonemodelid();
					ResAutoRaidInfoMessage reidmsg = new ResAutoRaidInfoMessage();
					ManagerPool.zonesFlopManager.addZoneReward(player,zonemodelid, true);
					raidinfo.setRaidautoendtime(0);
					reidmsg.setSurplustime(0);
					reidmsg.setZoneid(zonemodelid);
					MessageUtil.tell_player_message(player, reidmsg);
					ManagerPool.zonesFlopManager.LoginRaidRewarded(player);
					
					ZoneLog zlog = new ZoneLog();
					zlog.setPlayerid(player.getId());
					zlog.setType(1);
					zlog.setZonemodelid(zonemodelid);
					LogService.getInstance().execute(zlog);
				}else {
					//连续自动扫荡
					List<ContinuousRaidsInfo> Raidcontinuouslist = player.getRaidcontinuouslist();
					if (Raidcontinuouslist.size() > 0) {
						int status = 0;
						ContinuousRaidsInfo zuihouinfo = Raidcontinuouslist.get(Raidcontinuouslist.size()-1);
						int znum = Raidcontinuouslist.size();	//当前扫荡副本总数量
						boolean  allend = false;
						for (int j = 0; j < Raidcontinuouslist.size(); j++) {
							ContinuousRaidsInfo info = Raidcontinuouslist.get(j);
							int biaoshi = info.getStatus();
							int ms = info.getTime();
							if (biaoshi == 0) {
								if (ms > 0 && systime > ms) {
									znum = znum - 1;
									status = 1;
									info.setStatus(1);	//设置成完成状态
									ManagerPool.zonesFlopManager.addZoneReward(player,info.getZonemodelid(), true);	//增加可领奖次数
									ResZoneContinuousRaidsEndMessage cmsg = new ResZoneContinuousRaidsEndMessage();
									cmsg.setZoneid(info.getZonemodelid());
									cmsg.setZonetype(1);
									if (j == Raidcontinuouslist.size()-1) {	//检测是否完成最后一个副本
										cmsg.setType((byte) 1);	//全部完成了
										allend = true;
									}
									MessageUtil.tell_player_message(player, cmsg);
	
									ZoneLog zlog = new ZoneLog();
									zlog.setPlayerid(player.getId());
									zlog.setType(2);
									zlog.setZonemodelid(info.getZonemodelid());
									LogService.getInstance().execute(zlog);
									
									Q_clone_activityBean data = ManagerPool.zonesManager.getContainer(info.getZonemodelid());
									MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("【{1}】扫荡完成"), data.getQ_duplicate_name());
								}
							}else {
								znum = znum - 1;
							}
						}	
						
						if (status == 1) {
							//更新连续扫荡信息消息
							int rewnum = 0;
							ResZoneContinuousRaidsMessage conmsg= new ResZoneContinuousRaidsMessage();
							conmsg.setZonenum(znum);
							conmsg.setZonetype(1);
							conmsg.setPassedime(systime - player.getRaidcontinuoustime());
							conmsg.setSumtime(zuihouinfo.getTime() - player.getRaidcontinuoustime());
							HashMap<String, Integer> rewardmap = player.getZonerewardmap();//可领取奖励次数
							Iterator<Entry<String, Integer>> it = rewardmap.entrySet().iterator();
							while (it.hasNext()) {
								Entry<String, Integer> entry = it.next();
								rewnum = rewnum + entry.getValue();
							}
							conmsg.setRewardnum(rewnum);
							MessageUtil.tell_player_message(player, conmsg);
							
							if (allend) {	//全部完成，清除已经扫荡副本数据
								Raidcontinuouslist.clear();
							}
						}
					}
				}
			} catch (Exception e) {
				log.error(e,e);
			}
		}
	}	
	

	
	
	
	
	
	
	
	
	
	
	
	
}

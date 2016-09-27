package com.game.zones.timer;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_clone_activityBean;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.server.config.MapConfig;
import com.game.server.impl.MServer;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;
import com.game.zones.message.ResZoneLifeTimeMessage;
import com.game.zones.structs.ZoneContext;

public class ZoneDeleteTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(ZoneDeleteTimer.class);
	
	//基础设为5分钟
	private static int MINTIME =  1000 * 60 * 5;
	
	private long zoneId;
	
	private int zoneModelId;
	
	public ZoneDeleteTimer(long zoneId, int zoneModelId){
		super(-1, 1000);
		this.zoneId = zoneId;
		this.zoneModelId = zoneModelId;
	}

	@Override
	public void action() {
		if(zoneId==0 || zoneModelId==0) return;
		Q_clone_activityBean zone = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zoneModelId);
		if(zone==null) return;
		//获取副本线程
		MServer server = ManagerPool.zonesManager.getmServers().get(zoneId);
		if(server==null) return;
		//到时间后移除地图
		long time = System.currentTimeMillis();
		ZoneContext zoneContext = ManagerPool.zonesManager.getContexts().get(zoneId);
		int playernum = ManagerPool.zonesManager.checkZonePlayerNum(zoneContext);

		if ( zoneContext.isIsautoremove() && time > zoneContext.getCktime() + MINTIME && playernum == 0 ){	//副本没有人,最多存在5分钟后移除
			for (int i = 0; i < server.getMapConfigs().size(); i++) {
				MapConfig config = server.getMapConfigs().get(i);
				Map map = ManagerPool.mapManager.getMap(config.getServerId(), config.getLineId(), config.getMapId());
				if(map==null || map.getPlayerNumber()==0){
					continue;
				}else{
					return;
				}
			}
			//移除副本
			ManagerPool.zonesManager.removeZone(zoneId);

			//到时间后移除地图
		}else if((zone.getQ_exist_time() > 0 && time > server.getCreateTime() + zone.getQ_exist_time()) || (server.isDelete())){
			for (int i = 0; i < server.getMapConfigs().size(); i++) {
				MapConfig config = server.getMapConfigs().get(i);
				Map map = ManagerPool.mapManager.getMap(config.getServerId(), config.getLineId(), config.getMapId());
				if(map==null) continue;

				List<Player> players = new ArrayList<Player>();
				players.addAll(map.getPlayers().values());
				//复活玩家列表
				while (players.size() > 0) {
					Player player = players.remove(0);
					//死亡的话自动回城
					if(player.isDie()){
						ManagerPool.playerManager.revive(player, 2);
						continue;
					}
					
					//切换地图
					ManagerPool.zonesManager.outZone(player);
				}
			}
			//移除副本
			ManagerPool.zonesManager.removeZone(zoneId);
			
		}else if (zone.getQ_zone_type()==1) {		//每分钟通知前端已过时间
			long sum = time - server.getCreateTime() ;
			int timesum = (int) (sum/1000);
			if (timesum %60 == 0) {
				ResZoneLifeTimeMessage timemsg = new ResZoneLifeTimeMessage();
				for (int i = 0; i < server.getMapConfigs().size(); i++) {
					MapConfig config = server.getMapConfigs().get(i);
					Map map = ManagerPool.mapManager.getMap(config.getServerId(), config.getLineId(), config.getMapId());
					if(map==null) continue;
					//玩家列表
					List<Player> players = new ArrayList<Player>();
					players.addAll(map.getPlayers().values());
					if (players.size() > 0) {
						timemsg.setSurplustime(timesum);
						timemsg.setZoneid(zone.getQ_id());
						for (Player player : players) {
							MessageUtil.tell_player_message(player, timemsg);
						}
					}
				}
			}
		}
	}
}

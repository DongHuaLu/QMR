package com.game.horse.timer;


import java.util.Iterator;

import com.game.data.bean.Q_globalBean;
import com.game.horse.struts.Horse;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;

public class HorseTimer extends TimerEvent {
//	private Logger log=Logger.getLogger(HorseTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public HorseTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId=mapId;
	}

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
			int unlocktime = player.getAccunonlinetime() ;
			//领取第一只坐骑所需在线时间，单位（秒）
			Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(73);
			int sm = global.getQ_int_value();
			if(unlocktime > sm){
				Horse horse = ManagerPool.horseManager.getHorse(player);
				if(horse != null && horse.getNewhorse() == 0){
					horse.setNewhorse((byte) 1);	//设置可领取
					ManagerPool.horseManager.isGiveNewHorse(player);
				}
			}
		}
	}
}

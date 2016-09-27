package com.game.equipstreng.timer;

import java.util.Iterator;

import com.game.equipstreng.structs.EquipStreng;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;

public class StrengTimer extends TimerEvent{

	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public StrengTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId = mapId;
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
			EquipStreng esdata = player.getEquipStreng();
			if(esdata.getItemid() > 0){
				int s = esdata.getStarttime();
				if(s > 0){
					esdata.setStarttime(s-1);
					if (s == 1) {	//最后一秒通知玩家强化完成
						ManagerPool.equipstrengManager.atonceStrengthen(player);
					}
				}
			}
		}
	}	
	
	
}

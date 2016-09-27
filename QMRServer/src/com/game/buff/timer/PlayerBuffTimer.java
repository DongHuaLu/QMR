package com.game.buff.timer;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;

/**
 * 玩家buff定时计算
 * @author heyang
 *
 */
public class PlayerBuffTimer extends TimerEvent {

	private int lineId;
	
	private int serverId;
	
	private int mapId;
	
	private static int step=1000;
	
	protected Logger log = Logger.getLogger(PlayerBuffTimer.class);
	
	public PlayerBuffTimer(int serverId, int lineId, int mapId) {
		super(-1, step);
		this.serverId = serverId;
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
			if(player==null){
				continue;
			}
			if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
			try{
				ManagerPool.buffManager.countBuff(player);
			}catch (Exception e) {
				log.error(e, e);
			}
		}
	}
}

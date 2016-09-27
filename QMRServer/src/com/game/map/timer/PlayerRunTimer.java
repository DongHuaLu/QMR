package com.game.map.timer;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.cooldown.structs.CooldownTypes;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-8-31
 * 
 * 玩家移动
 */
public class PlayerRunTimer extends TimerEvent {
	
	protected Logger log = Logger.getLogger(PlayerRunTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public PlayerRunTimer(int serverId, int lineId, int mapId){
		super(-1, 100);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		
		//获取玩家
		Iterator<Player> iter = map.getPlayers().values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			
			if(player.getMap()!=mapId || player.getLine()!=lineId){
				iter.remove();
				continue;
			}
			
			if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.RUN_STEP, null, 0)){
				continue;
			}else if(player.getLastPosition()==null || player.getLastRoads()==null || player.getLastRoads().size()==0){
				continue;
			}else{
				ManagerPool.mapManager.playerRunning(player, player.getLastPosition(), player.getLastRoads(), System.currentTimeMillis());
			}
		}
	}
	
}

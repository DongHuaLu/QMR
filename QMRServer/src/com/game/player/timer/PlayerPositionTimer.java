package com.game.player.timer;

import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.message.ResSynPlayerPositionMessage;
import com.game.map.structs.Map;
import com.game.monster.timer.MonsterAiTimer;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;

//测试专用，前后台位置同步
public class PlayerPositionTimer extends TimerEvent {

	protected Logger log = Logger.getLogger(MonsterAiTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public PlayerPositionTimer(int serverId, int lineId, int mapId){
		super(-1, 100);
		this.serverId = serverId;
		this.lineId = lineId;
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
			if(player==null) continue;
			//不是本线玩家
			if(player.getServerId()!=this.serverId || player.getLine()!=this.lineId || player.getMap()!=this.mapId) continue;
			if(!PlayerManager.getSyncPosition().contains(player.getId())) continue;
			
			//log.error("玩家坐标：" + player.getPosition());
			ResSynPlayerPositionMessage msg = new ResSynPlayerPositionMessage();
			msg.setPosition(player.getPosition());
			MessageUtil.tell_player_message(player, msg);
		}
	}
}

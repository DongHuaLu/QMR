package com.game.equip.timmer;

import java.util.Iterator;

import com.game.backpack.structs.Equip;
import com.game.map.manager.MapManager;
import com.game.map.structs.Map;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;

public class EquipLostTimeCheck extends TimerEvent {
	int serverId; int lineId; int mapId;
	public EquipLostTimeCheck(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId=mapId;
	}

	@Override
	public void action() {
		Map map = MapManager.getInstance().getMap(serverId,lineId,mapId);
		Iterator<Player> iterator = map.getPlayers().values().iterator();
		while (iterator.hasNext()) {
			Player player = (Player) iterator.next();
			Equip[] equips = player.getEquips();
			for (Equip equip : equips) {
				if(equip!=null&&equip.isLost()){
					equip.unuse(player,Equip.BODYEQUIPLOST);
				}
			}
		}
	}

}

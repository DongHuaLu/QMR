package com.game.map.timer;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.timer.TimerEvent;

public class MapCountTimer extends TimerEvent {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MapCountTimer.class);
	
	private int mapId;
	
	private int lineId;

	private int serverId;

	public MapCountTimer(int serverId, int lineId, int mapId) {
		super(-1,60000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId=mapId;
	}

	@Override
	public void action() {
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		int goods=0;
		int monsters=0;
		int players=0;
		int pets=0;
		
//		List<String> names = new ArrayList<String>();
		HashMap<Integer,Area> areas = map.getAreas();
		Iterator<Area> iterator = areas.values().iterator();
		while (iterator.hasNext()) {
			Area area = (Area) iterator.next();
			//if(area.getPlayers().size()>0) logger.info("Area=" + area.getId() + ", players=" + area.getPlayers().size());
			goods+=area.getDropGoods().size();
			monsters+=area.getMonsters().size();
			players+=area.getPlayers().size();
			pets+=area.getPets().size();
		}

		if (logger.isInfoEnabled()) {
			logger.error("action(line:" + lineId + " map:" + mapId + "), goods=" + goods
					+ ", monsters=" + monsters + ", players=" + players
					+ ", pets=" + pets);
					//+ ", player:[" + MessageUtil.castListToString(area) + "]");
		}
	}

}

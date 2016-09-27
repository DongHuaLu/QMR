package com.game.map.timer;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.map.script.IMapHeartScript;
import com.game.map.structs.Map;
import com.game.script.structs.ScriptEnum;
import com.game.timer.TimerEvent;

public class MapHeartTimer extends TimerEvent {
	/**
	 * Logger for this class
	 */
	protected static final Logger log = Logger.getLogger(MapHeartTimer.class);
	
	private int mapId;
	
	private int lineId;

	private int serverId;

	public MapHeartTimer(int serverId, int lineId, int mapId) {
		super(-1, 1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId=mapId;
	}

	@Override
	public void action() {
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		
		IMapHeartScript script = (IMapHeartScript) ManagerPool.scriptManager.getScript(ScriptEnum.MAP_HEART);
		if (script != null) {
			try {
				script.onHeart(map);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("地图心跳脚本不存在！");
		}
	}

}

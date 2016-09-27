package com.game.zones.timer;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.manager.ManagerPool;
import com.game.timer.TimerEvent;
import com.game.zones.script.IZoneEventTimerScript;

public class ZoneEventTimer extends TimerEvent {
	
	private Logger log = Logger.getLogger(ZoneEventTimer.class);
	//脚本Id
	private int scriptId;
	//副本Id
	private long zoneId;
	//副本模板id
	private int zoneModelId;
	//副本参数
	private List<Object> parameters;
		
	public ZoneEventTimer(int times, int scriptId, long zoneId, int zoneModelId, List<Object> parameters, long delay) {
		super(times, delay);
		this.scriptId = scriptId;
		this.zoneId = zoneId;
		this.zoneModelId = zoneModelId;
		this.parameters = parameters;
	}
	
	@Override
	public void action() {
		IZoneEventTimerScript script = (IZoneEventTimerScript) ManagerPool.scriptManager.getScript(scriptId);
		if (script != null) {
			try {
				script.action(zoneId, zoneModelId, parameters);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("副本延时事件脚本不存在！");
		}
	}
}

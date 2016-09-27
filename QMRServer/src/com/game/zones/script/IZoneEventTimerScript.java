package com.game.zones.script;

import java.util.List;

import com.game.script.IScript;

public interface IZoneEventTimerScript extends IScript {
	
	public void action(long zoneId, int zoneModelId, List<Object> parameters);

}

package com.game.zones.script;

import com.game.script.IScript;

public interface IServerZoneEventTimerScript extends IScript {
	
	public void action(int scriptID, int zoneModelId);

}

package com.game.zones.script;

import java.util.List;

import com.game.script.IScript;
import com.game.server.impl.MServer;
import com.game.zones.structs.ZoneContext;

public interface IZoneDeleteScript extends IScript {
	
	public void onDelete(MServer mServer, ZoneContext context);

}

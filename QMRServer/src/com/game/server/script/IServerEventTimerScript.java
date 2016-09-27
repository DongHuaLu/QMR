package com.game.server.script;

import com.game.script.IScript;

public interface IServerEventTimerScript extends IScript {

	public void action(int serverId, String serverWeb);
}

package com.game.server.script;

import com.game.script.IScript;

public interface IServerStartScript extends IScript {

	public void onStart(String web, int serverId);
}

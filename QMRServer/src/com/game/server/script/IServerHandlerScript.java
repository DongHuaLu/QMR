package com.game.server.script;

import com.game.command.Handler;
import com.game.script.IScript;

public interface IServerHandlerScript extends IScript {

	public boolean check(Handler handler);
}

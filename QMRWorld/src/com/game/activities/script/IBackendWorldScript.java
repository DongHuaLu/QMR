package com.game.activities.script;

import com.game.backend.struct.ServerRequest;
import com.game.script.IScript;

public interface IBackendWorldScript extends IScript {
	
	public String doServerRequest(ServerRequest sr, String requeststr);
	
}

package com.game.server.command;

import com.game.command.Handler;
import com.game.dataserver.manager.DataServerManager;
import com.game.enter.manager.EnterManager;

public class ServerCloseCommand extends Handler {

	private String web;
	
	private int serverId;
	
	private int version;
	
	public ServerCloseCommand(String web, int serverId, int version){
		this.web = web;
		this.serverId = serverId;
		this.version = version;
	}
	
	@Override
	public void action() {
		DataServerManager.getInstance().serverQuit(serverId);
		EnterManager.getInstance().serverQuit(web, serverId, version);
	}

}

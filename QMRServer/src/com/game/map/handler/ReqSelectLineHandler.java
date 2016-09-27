package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.map.message.ReqSelectLineMessage;
import com.game.command.Handler;
import com.game.map.manager.MapManager;
import com.game.player.structs.Player;

public class ReqSelectLineHandler extends Handler {

	Logger log = Logger.getLogger(ReqSelectLineHandler.class);

	public void action() {
		try {
			ReqSelectLineMessage msg = (ReqSelectLineMessage) this.getMessage();
			MapManager.getInstance().reqSelectLine((Player) this.getParameter(), msg);

		} catch (ClassCastException e) {
			log.error(e);
		}
	}
}
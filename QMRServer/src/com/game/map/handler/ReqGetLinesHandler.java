package com.game.map.handler;

import org.apache.log4j.Logger;

import com.game.map.message.ReqGetLinesMessage;
import com.game.command.Handler;
import com.game.map.manager.MapManager;
import com.game.player.structs.Player;

public class ReqGetLinesHandler extends Handler {

	Logger log = Logger.getLogger(ReqGetLinesHandler.class);

	public void action() {
		try {
			ReqGetLinesMessage msg = (ReqGetLinesMessage) this.getMessage();
			MapManager.getInstance().reqGetLines((Player) this.getParameter(), msg);

		} catch (ClassCastException e) {
			log.error(e);
		}
	}
}
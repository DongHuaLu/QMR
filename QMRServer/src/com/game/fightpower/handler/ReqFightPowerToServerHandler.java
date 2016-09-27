package com.game.fightpower.handler;

import org.apache.log4j.Logger;

import com.game.fightpower.message.ReqFightPowerToServerMessage;
import com.game.command.Handler;
import com.game.fightpower.manager.FightPowerManager;
import com.game.player.structs.Player;

public class ReqFightPowerToServerHandler extends Handler {

	Logger log = Logger.getLogger(ReqFightPowerToServerHandler.class);

	public void action() {
		try {
			ReqFightPowerToServerMessage msg = (ReqFightPowerToServerMessage) this.getMessage();
			FightPowerManager.getInstance().reqFightPowerToServer((Player) this.getParameter(), msg);

		} catch (ClassCastException e) {
			log.error(e);
		}
	}
}
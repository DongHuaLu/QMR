package com.game.guild.handler;

import org.apache.log4j.Logger;

import com.game.guild.message.ResInnerGuildAloneGuildInfoToServerMessage;
import com.game.command.Handler;
import com.game.guild.manager.GuildServerManager;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;

public class ResInnerGuildAloneGuildInfoToServerHandler extends Handler {

	Logger log = Logger.getLogger(ResInnerGuildAloneGuildInfoToServerHandler.class);

	public void action() {
		try {
			ResInnerGuildAloneGuildInfoToServerMessage msg = (ResInnerGuildAloneGuildInfoToServerMessage) this.getMessage();
			if (msg.getRoleId().size() == 1) {
				GuildServerManager.getInstance().ResInnerGuildAloneGuildInfoToServer((Player) this.getParameter(), msg);
			} else {
				for (int i = 0; i < msg.getRoleId().size(); i++) {
					Player player = PlayerManager.getInstance().getPlayer(msg.getRoleId().get(i));
					if (player != null) {
						GuildServerManager.getInstance().ResInnerGuildAloneGuildInfoToServer(player, msg);
					}
				}
			}

		} catch (ClassCastException e) {
			log.error(e);
		}
	}
}
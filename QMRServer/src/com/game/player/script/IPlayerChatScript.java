package com.game.player.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

public interface IPlayerChatScript extends IScript {
	
	public void onChat(Player player, int type, String content);
	
}

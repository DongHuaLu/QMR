package com.game.player.script;

import java.util.List;

import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.structs.Position;

public interface IPlayerJumpScript extends IScript {
	
	public boolean onJump(Player player, List<Position> positions, int type);
	
}

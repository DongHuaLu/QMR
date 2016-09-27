package com.game.player.manager;

import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeCalculator;
import com.game.player.structs.PlayerAttributeType;

public class OtherAttributeCalculator implements PlayerAttributeCalculator {

	@Override
	public int getType() {
		return PlayerAttributeType.OTHER;
	}

	@Override
	public PlayerAttribute getPlayerAttribute(Player player) {
		return player.getOtherAttribute();
	}

}

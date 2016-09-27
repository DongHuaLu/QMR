package com.game.buff.structs;

import com.game.fight.structs.Fighter;
import com.game.player.structs.Player;

public class KillBuff extends Buff {

	private static final long serialVersionUID = -7876965288766520956L;

	@Override
	public int add(Fighter source, Fighter target) {
		if(source instanceof Player){
			Player player = (Player)source;
			if(player.getEvencutnum() >= player.getEvencutatk()) player.setEvencutatk(player.getEvencutnum());
		}
		return 0;
	}

	@Override
	public int action(Fighter source, Fighter target) {
		return 0;
	}

	@Override
	public int remove(Fighter source) {
		if(source instanceof Player){
			Player player = (Player)source;
			player.setEvencutatk(0);
		}
		return 0;
	}

	@Override
	public long countTotalRemainTime(Fighter source){
		if(source instanceof Player){
			Player player = (Player)source;
			return (getTotalTime() - (System.currentTimeMillis() -  player.getEvencutbufftime()));
		}
		return getTotalRemainTime();
	}
}

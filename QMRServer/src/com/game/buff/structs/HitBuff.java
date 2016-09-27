package com.game.buff.structs;

import com.game.fight.structs.Fighter;
import com.game.player.structs.Player;

public class HitBuff extends Buff {

	private static final long serialVersionUID = -3981260456768272453L;

	@Override
	public int add(Fighter source, Fighter target) {
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
			player.setBossbatternum(0);
		}
		return 0;
	}

	@Override
	public long countTotalRemainTime(Fighter source){
		if(source instanceof Player){
			Player player = (Player)source;
			return (getTotalTime() - (System.currentTimeMillis() -  player.getBossbattertime()));
		}
		return getTotalRemainTime();
	}
}

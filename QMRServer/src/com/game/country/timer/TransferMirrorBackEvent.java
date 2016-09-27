package com.game.country.timer;

import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;

public class TransferMirrorBackEvent extends TimerEvent {

	private Player mirror;
	
	private Player player;
	
	public TransferMirrorBackEvent(Player mirror, Player player){
		super(1, 5 * 1000);
		this.mirror = mirror;
		this.player = player;
	}
	
	@Override
	public void action() {
		ManagerPool.countryManager.transferMirrorBack(mirror, player.getPosition());
	}
}

package com.game.map.structs;

import com.game.player.structs.Player;
import com.game.structs.Position;

public interface IMapObject {

	public long getId();
	
	public int getServerId();
	
	public int getLine();
	
	public int getMap();
	
	public Position getPosition();
	
	public boolean canSee(Player player);
}

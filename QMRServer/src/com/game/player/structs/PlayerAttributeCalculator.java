package com.game.player.structs;

public interface PlayerAttributeCalculator {
	/**
	 * 类型
	 * @return
	 */
	public int getType();
	/**
	 * 获取玩家属性
	 */
	public PlayerAttribute getPlayerAttribute(Player player);
	
}

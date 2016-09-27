package com.game.activities.script;

import com.game.player.structs.Player;
import com.game.script.IScript;

/**
 * 定时领取礼金
 * @author 赵聪慧
 * @2012-9-12 下午8:23:33
 */
public interface IReceiveLiJinGift extends IScript {
	
	public void receive(Player player);
	
}

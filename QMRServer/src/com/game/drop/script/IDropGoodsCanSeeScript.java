package com.game.drop.script;

import com.game.drop.structs.MapDropInfo;
import com.game.player.structs.Player;
import com.game.script.IScript;

/**
 * 
 * @author 赵聪慧
 * @2012-9-12 上午2:39:16
 */
public interface IDropGoodsCanSeeScript extends IScript {

	public boolean cansee(Player player, MapDropInfo mapDropInfo);
}

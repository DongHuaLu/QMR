package com.game.drop.script;

import com.game.drop.structs.DropItem;
import com.game.drop.structs.MapDropInfo;
import com.game.monster.structs.Monster;
import com.game.script.IScript;

/**
 * 脚本掉落 
 * @author 赵聪慧
 * @2012-9-28 上午3:00:02
 */
public interface IDropGoodsScript extends IScript {

	public MapDropInfo buildGoodsInfo(DropItem dropItem,Monster monster); 
}

package com.game.task.script;

import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.task.struts.MainTask;

/**
 * 任务达成
 * @author 赵聪慧
 * @2012-9-14 上午1:33:23
 */
public interface IMainTaskReachAction extends IScript {
	public void action(Player player, MainTask task);
}

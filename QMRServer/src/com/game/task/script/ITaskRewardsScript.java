package com.game.task.script;

import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.task.struts.Task;

/**
 *	任务奖励脚本 
 * @author 赵聪慧
 * @2012-9-13 上午7:04:14
 */
public interface ITaskRewardsScript extends IScript {
	public void rewards(Player player,Task task);
}

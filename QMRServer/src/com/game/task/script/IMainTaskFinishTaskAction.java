package com.game.task.script;

import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.task.struts.MainTask;
/**
 * 任务完成后
 * @author 赵聪慧
 *
 */
public interface IMainTaskFinishTaskAction extends IScript {
	/**
	 * 
	 * @param player 事件角色	
	 * @param task	 主线任务完成后
	 */
	public void finishMainTaskAfter(Player player,MainTask task);
}

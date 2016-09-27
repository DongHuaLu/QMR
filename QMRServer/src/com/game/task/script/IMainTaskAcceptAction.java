package com.game.task.script;

import com.game.player.structs.Player;
import com.game.script.IScript;
import com.game.task.struts.MainTask;
/**
 * 任务接收后
 * @author 赵聪慧
 *
 */
public interface IMainTaskAcceptAction extends IScript{
	/**
	 * 
	 * @param player 事件角色
	 * @param maintask	接收到到的任务
	 */
	public void acceptMainTaskAfter(Player player,MainTask maintask);

}

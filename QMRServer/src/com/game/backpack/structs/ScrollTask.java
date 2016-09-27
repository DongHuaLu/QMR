package com.game.backpack.structs;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.task.manager.TaskManager;
import com.game.task.struts.MainTask;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

/**
 * 任务卷轴
 * @author 赵聪慧
 *
 */
public class ScrollTask extends Item {
	private static  int needfinshchapter=1;//需要完成的任务章节
	/**
	 * 
	 */
	private static final long serialVersionUID = -8446131291198340700L;

	@Override
	public void use(Player player, String... parameters) {

		// 卷轴任务可以重复接取
		int a = 0;
		if (a == 1) {
			// TODO 判断玩家是否得到了某成就
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有完成第一章剧情任务后才可以讨伐"));
			return;
		}

		if (TimeUtil.isSameDay(player.getConquerTaskTime(), player.getConquerTaskTime())) {
			// 是同一天 检查次数
			if (player.getConquerTaskCount() >= getPlayerCONQUERTASK_DAYMAXACCEPT(player)) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("今日已接过的讨伐任务数已达{1}个，请明天再试"),getPlayerCONQUERTASK_DAYMAXACCEPT(player) + "");
				return;
			}
		} else {
			player.setConquerTaskCount(0);
			player.setConquerTaskMaxCount(0);
		}
		int num=1;
		//批量使用检查
		if(parameters!=null&&parameters.length>=1){
			num=Integer.parseInt(parameters[0]);
			if(player.getConquerTaskCount() +num> getPlayerCONQUERTASK_DAYMAXACCEPT(player)){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("批量使用{1}个卷轴超上限，今日已接过的讨伐任务数已达{2}个，每日最大接取数量{3}"), num+"",player.getConquerTaskCount()+"",getPlayerCONQUERTASK_DAYMAXACCEPT(player) + "");
				return;
			}
		}
		if (player.getCurrentConquerTasks().size() >= TaskManager.CONQUERTASK_MAXNUM) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("尚未完成的讨伐任务数超过{1}个，请先完成部分"), TaskManager.CONQUERTASK_MAXNUM + "");
			return;
		}
		
		if(player.getCurrentMainTasks().size()<=0){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有完成第{1}章剧情任务后才可以讨伐"), needfinshchapter + "");
			return;
		}
		MainTask mainTask = player.getCurrentMainTasks().get(0);
		Q_task_mainBean q_task_mainBean = DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
		if(q_task_mainBean.getQ_chapter()<needfinshchapter){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有完成第{1}章剧情任务后才可以讨伐"), needfinshchapter + "");
			return;
		}
		
		if (BackpackManager.getInstance().removeItem(player, this, num, Reasons.GOODUSE, Config.getId())) {
			for (int i = 0; i < num; i++) {
				TaskManager.getInstance().acceptConquerTask(player, getItemModelId());
			}
		}	
	}

	@Override
	public void unuse(Player player, String... parameters) {

	}

	public int getPlayerCONQUERTASK_DAYMAXACCEPT(Player player) {
		return TaskManager.CONQUERTASK_DAYMAXACCEPT + player.getConquerTaskMaxCount();
	}
}

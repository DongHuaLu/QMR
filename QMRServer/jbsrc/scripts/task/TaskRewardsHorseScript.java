package scripts.task;
import com.game.horse.manager.HorseManager;
import com.game.player.structs.Player;
import com.game.task.script.ITaskRewardsScript;
import com.game.task.struts.Task;

/**
 * 任务奖励座骑脚本 6001
 * @author 赵聪慧
 * @2012-9-13 上午7:11:24
 */
public class TaskRewardsHorseScript implements ITaskRewardsScript {
	@Override
	public int getId() {
		return 6001;
	}
	@Override
	public void rewards(Player player, Task task) {
		HorseManager.getInstance().giveNewHorse(player);
	}

}

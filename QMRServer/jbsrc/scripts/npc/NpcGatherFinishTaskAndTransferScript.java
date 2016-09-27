package scripts.npc;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_npcBean;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.npc.script.INpcGatherActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.task.struts.MainTask;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;

/**
 * 采集后传送脚本
 * @author heyang
 *
 */
public class NpcGatherFinishTaskAndTransferScript implements INpcGatherActionScript {
	
	protected Logger log = Logger.getLogger(NpcGatherFinishTaskAndTransferScript.class);
	
	public static int scriptId = 5009;		//scriptid
	
	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void gather(Player player, NPC npc){
		//根据npc判断采集物品
		int taskModelId = 0;
		Q_npcBean bean = ManagerPool.dataManager.q_npcContainer.getMap().get(npc.getModelId());
		if(bean==null){
			return;
		}
		taskModelId = bean.getQ_acquisition_task();
		if(taskModelId==0){
			//player停止采集
			ManagerPool.npcManager.playerStopGather(player);
			return;
		}else{
			boolean canFinish = true;

			MainTask mainTask = player.getCurrentMainTasks().get(0);
			Q_task_mainBean taskModel= DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
			if(taskModelId!=mainTask.getModelid()){
				canFinish = false;
			}
			if(player.getLevel()<taskModel.getQ_accept_needmingrade()){
				//等级不足
				MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("采集需要的等级不足"));
				canFinish = false;
			}

			if(canFinish){
				//触发完成任务
				ManagerPool.taskManager.finishMainTask(player, taskModelId);
				//player传送
				ManagerPool.mapManager.changeMap(player, 20002, 20002, 0, MapUtils.getGrid(177, 32, ManagerPool.mapManager.getMapBlocks(20002)).getCenter(), this.getClass().getName() + ".gather");
			}
		}
		
		//player停止采集
		ManagerPool.npcManager.playerStopGather(player);
		
	}

}

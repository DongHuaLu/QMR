package scripts.npc;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_npcBean;
import com.game.data.bean.Q_task_mainBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.npc.script.INpcGatherActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Reasons;
import com.game.task.struts.MainTask;
import com.game.utils.MessageUtil;

/**
 * 采集后给予物品并完成任务
 * @author heyang
 *
 */
public class NpcGatherNoHideScript implements INpcGatherActionScript {
	
	protected Logger log = Logger.getLogger(NpcGatherNoHideScript.class);
	
	public static int scriptId = 5005;		//scriptid
	
	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void gather(Player player, NPC npc){
		//根据npc判断采集物品
		int itemModelId = 0;
		int itemNum = 1;
		Q_npcBean bean = ManagerPool.dataManager.q_npcContainer.getMap().get(npc.getModelId());
		if(bean==null){
			return;
		}
		itemModelId = bean.getQ_acquisition_item();
		if(itemModelId==0 || itemNum==0){
			//player停止采集
			ManagerPool.npcManager.playerStopGather(player);
			return;
		}
		//判断包裹是否能够放下此物品
		int num = ManagerPool.backpackManager.getAbleAddNum(player, itemModelId, true, 0);
		if(num < itemNum){
			//提示包裹空间不足
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("背包空间不足"));
			//player停止采集
			ManagerPool.npcManager.playerStopGather(player);
			return;
		}
		
		//根据npc判断采集物品
		int taskModelId = bean.getQ_acquisition_task();
		if(taskModelId!=0){
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
				log.error("玩家(" + player.getId() + ")采集成功准备完成任务(" + taskModelId + ")!");
				//触发完成任务
				ManagerPool.taskManager.finishMainTask(player, taskModelId);
				log.error("玩家(" + player.getId() + ")采集成功完成任务(" + taskModelId + ")了!");
				
				//增加player物品
				List<Item> item = Item.createItems(itemModelId, itemNum, true, 0);
				log.error("玩家(" + player.getId() + ")完成任务(" + taskModelId + ")构建物品(" + itemModelId + ")完成!");
				if(BackpackManager.getInstance().hasAddSpace(player, item)){
					log.error("玩家(" + player.getId() + ")完成任务(" + taskModelId + ")检查物品(" + itemModelId + ")有地儿放!");
				}else{
					log.error("玩家(" + player.getId() + ")完成任务(" + taskModelId + ")检查物品(" + itemModelId + ")没地儿放!");
				}				
				if(ManagerPool.backpackManager.addItems(player, item, Reasons.TAKEUP, Config.getId())){
					log.error("玩家(" + player.getId() + ")完成任务(" + taskModelId + ")获得物品(" + itemModelId + ")成功!");;
				}else{
					log.error("玩家(" + player.getId() + ")完成任务(" + taskModelId + ")获得物品(" + itemModelId + ")失败!");;
				}
				
			}
		}
		
		//player停止采集
		ManagerPool.npcManager.playerStopGather(player);
	}

}

package scripts.npc;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.data.bean.Q_npcBean;
import com.game.manager.ManagerPool;
import com.game.npc.script.INpcGatherActionScript;
import com.game.npc.struts.NPC;
import com.game.npc.timer.NpcEventTimer;
import com.game.player.structs.Player;
import com.game.structs.Reasons;
import com.game.util.TimerUtil;

/**
 * npc出现
 * @author heyang
 *
 */
public class NpcGatherScript implements INpcGatherActionScript {
	
	protected Logger log = Logger.getLogger(NpcGatherScript.class);
	
	public static int scriptId = 5002;		//scriptid

	private static int show_scriptId = 5001;		//scriptid
	
	private static long show_delay = 3000;		//重新显示事件
	
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
			
			//player停止采集
			ManagerPool.npcManager.playerStopGather(player);
			return;
		}
//		if(player.getCurrentMainTasks().size()<0){
//			log.error("身上没有主线任务");
//			return;
//		}
//		
//		MainTask mainTask = player.getCurrentMainTasks().get(0);
//		Q_task_mainBean taskModel= DataManager.getInstance().q_task_mainContainer.getMap().get(mainTask.getModelid());
//		if(player.getLevel()<taskModel.getQ_accept_needmingrade()){
//			//等级不足
//			MessageUtil.notify_player(player, Notifys.ERROR,"采集需要的等级不足");
//			return;
//		}
		
		
		
		//增加player物品
		List<Item> item = Item.createItems(itemModelId, itemNum, true, 0);
		ManagerPool.backpackManager.addItems(player, item, Reasons.TAKEUP, Config.getId());
		
		//player停止采集
		ManagerPool.npcManager.playerStopGather(player, true);
		
		//npc消失
		ManagerPool.npcManager.hideNpc(npc);
		
		//增加npc出现事件
		NpcEventTimer timer = new NpcEventTimer(npc, show_scriptId, null, show_delay);
		TimerUtil.addTimerEvent(timer);
	}

}

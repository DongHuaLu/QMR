package scripts.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_task_mainBean;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.task.script.IMainTaskAcceptAction;
import com.game.task.struts.MainTask;
import com.game.utils.MapUtils;
import com.game.utils.Symbol;

public class MainTaskAcceptAction implements IMainTaskAcceptAction {

	protected Logger log = Logger.getLogger(MainTaskAcceptAction.class);
	
	@Override
	public int getId() {
		return ScriptEnum.TASK_ACCEPTAFTER;
	}

	@Override
	public void acceptMainTaskAfter(Player player, MainTask maintask) {
		Q_task_mainBean taskBean = ManagerPool.dataManager.q_task_mainContainer.getMap().get(maintask.getModelid());
		if(taskBean!=null){
			if(taskBean.getQ_acc_show()!=null && !("").equals(taskBean.getQ_acc_show())){
				String[] shows = taskBean.getQ_acc_show().split(Symbol.FENHAO_REG);
				for (int i = 0; i < shows.length; i++) {
					try{
						int npcid = Integer.parseInt(shows[i]);
						List<NPC> npcs = ManagerPool.npcManager.findNpc(player, npcid);
						if(npcs.size() > 0){
							for (int j = 0; j < npcs.size(); j++) {
								ManagerPool.npcManager.showNpc(player, npcs.get(j));
							}
						}else{
							player.getShowSet().add(String.valueOf(npcid));
						}
					}catch (Exception e) {
						log.error(e, e);
					}
				}
			}
			if(taskBean.getQ_acc_hide()!=null && !("").equals(taskBean.getQ_acc_hide())){
				String[] hides = taskBean.getQ_acc_hide().split(Symbol.FENHAO_REG);
				for (int i = 0; i < hides.length; i++) {
					try{
						int npcid = Integer.parseInt(hides[i]);
						List<NPC> npcs = ManagerPool.npcManager.findNpc(player, npcid);
						if(npcs.size() > 0){
							for (int j = 0; j < npcs.size(); j++) {
								ManagerPool.npcManager.hideNpc(player, npcs.get(j));
							}
						}else{
							player.getHideSet().add(String.valueOf(npcid));
						}
					}catch (Exception e) {
						log.error(e, e);
					}
				}
			}
			if(taskBean.getQ_task_brush_monid()!=0){
				String[] strs = taskBean.getQ_brush_mon_xy().split(Symbol.XIAHUAXIAN_REG);
				Map map = ManagerPool.mapManager.getMap(player);
				if(map!=null && map.getMapModelid() == taskBean.getQ_brush_mon_map()){
					Grid grid = MapUtils.getGrid(Integer.parseInt(strs[0]), Integer.parseInt(strs[1]), ManagerPool.mapManager.getMapBlocks(map.getMapModelid()));
					if(grid!=null) ManagerPool.monsterManager.createStoryMonsterAndEnterMap(player, taskBean.getQ_task_brush_monid(), WServer.getInstance().getServerId(), player.getLine(), player.getMap(), grid.getCenter());
				}
			}
		}

	}

}

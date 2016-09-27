package scripts.task;

import java.util.List;

import org.apache.log4j.Logger;

import com.game.data.bean.Q_task_mainBean;
import com.game.manager.ManagerPool;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.script.structs.ScriptEnum;
import com.game.task.script.IMainTaskReachAction;
import com.game.task.struts.MainTask;
import com.game.utils.Symbol;

public class MainTaskReachAction implements IMainTaskReachAction {

	protected Logger log = Logger.getLogger(MainTaskReachAction.class);
	
	@Override
	public int getId() {
		return ScriptEnum.MAINTASK_REACH;
	}

	@Override
	public void action(Player player, MainTask maintask) {
		Q_task_mainBean taskBean = ManagerPool.dataManager.q_task_mainContainer.getMap().get(maintask.getModelid());
		if(taskBean!=null){
			if(taskBean.getQ_accomplish_show()!=null && !("").equals(taskBean.getQ_accomplish_show())){
				String[] shows = taskBean.getQ_accomplish_show().split(Symbol.FENHAO_REG);
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
			if(taskBean.getQ_accomplish_hide()!=null && !("").equals(taskBean.getQ_accomplish_hide())){
				String[] hides = taskBean.getQ_accomplish_hide().split(Symbol.FENHAO_REG);
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
		}

	}

}

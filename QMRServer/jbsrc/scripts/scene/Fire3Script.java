package scripts.scene;

import java.util.List;

import com.game.config.Config;
import com.game.manager.ManagerPool;
import com.game.map.bean.EffectInfo;
import com.game.map.message.ResRoundEffectMessage;
import com.game.map.script.IEnterGridScript;
import com.game.map.structs.Map;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.structs.Grid;
import com.game.utils.MessageUtil;

public class Fire3Script implements IEnterGridScript {

	private int scriptId = 8003;
	
	private int[] showNpc = {20023, 20024, 20027};
	
	private int effectId = 35;
	
	private String key="Fire3";
	
	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void onEnterGrid(Player player, Map map, Grid grid) {
		if(!player.getVariables().containsKey(key)){
			player.getVariables().put(key, "1");

			NPC[] npcs = new NPC[showNpc.length];
			for (int i = 0; i < showNpc.length; i++) {
				List<NPC> npcList = ManagerPool.npcManager.findNpc(map, showNpc[i]);
				if(npcList.size() > 0){
					npcs[i] = npcList.get(0);
				}
			}

			for (int i = 0; i < npcs.length; i++) {
				if(npcs[i]==null) continue;
				ResRoundEffectMessage msg = new ResRoundEffectMessage();
				EffectInfo info = new EffectInfo();
				info.setEffectId(Config.getId());
				info.setEffectModelId(effectId);
				info.setX(npcs[i].getPosition().getX());
				info.setY(npcs[i].getPosition().getY());
				msg.setEffect(info);
				MessageUtil.tell_player_message(player, msg);
			}
			
//			ArrayList<Object> paras = new ArrayList<Object>();
//			paras.add(player);
//			paras.add(showNpc);
//			//增加npc出现事件
//			NpcEventTimer timer = new NpcEventTimer(null, hideAndShow, paras, delay);
//			TimerUtil.addTimerEvent(timer);
			
			for (int i = 0; i < npcs.length; i++) {
				if(npcs[i]==null) continue;
				ManagerPool.npcManager.showNpc(player, npcs[i]);
			}
		}
	}

}

package scripts.npc;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.structs.Item;
import com.game.config.Config;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.npc.manager.NpcManager;
import com.game.npc.script.INpcDefaultActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.scripts.bean.PanelInfo;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.NpcParamUtil;

/**
 * 圣诞说话行为
 * @author heyang
 *
 */
public class ChristmasTalkActionScript implements INpcDefaultActionScript {
	
	protected Logger log = Logger.getLogger(ChristmasTalkActionScript.class);
	
	public static int scriptId = 5012;		//scriptid

	//对话面板ID
	private int panelId = 4;
	//圣诞袜子ID
	private int itemModelId = 8508;
	
	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void defaultAction(Player player, NPC npc){
		if(npc.getModelId()==50000){
			//npc距离
			if (!NpcManager.checkDistance(npc, player.getPosition())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您离NPC太远了"));
				return;
			}

			//打开对话
			PanelInfo panel = NpcParamUtil.getPanelInfo(player, panelId);
			List<String> list = new ArrayList<String>();
			list.add("btnGetRewards#" + this.getId() + "#receive#"+npc.getId());
			panel.setButtoninfolist(NpcParamUtil.getbuttonInfo(player , list));
			NpcParamUtil.showPanel(player, panel);
		}
	}

	
	public void receive(List<Object> para){
		Player player = (Player) para.get(0);
		if (player == null ) {
			return;
		}
		long npcid = Long.valueOf((String)para.get(1));
		NPC npc = ManagerPool.npcManager.findNpc(player, npcid);
		if(npc == null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("东西已经被别人拿走啦，下次可要快点哦。"));
			return;
		}
		//npc距离
		if (!NpcManager.checkDistance(npc, player.getPosition())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，您离NPC太远了"));
			return;
		}

		//包裹空间是否足够
		if(ManagerPool.backpackManager.getEmptyGridNum(player) >= 1){
			//给予圣诞袜子
			List<Item> items = Item.createItems(itemModelId, 1, true, 0);
			ManagerPool.backpackManager.addItems(player, items, Reasons.ACTIVITY_GIFT, Config.getId());
			
			//npc消失
			ManagerPool.mapManager.quitMap(npc);
		}
		//关闭面板
		NpcParamUtil.setPanel(player, panelId, new ArrayList<String>(), 1);
		
	}
}

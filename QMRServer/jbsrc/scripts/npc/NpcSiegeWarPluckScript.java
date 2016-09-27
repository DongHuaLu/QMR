
package scripts.npc;

import org.apache.log4j.Logger;

import com.game.country.message.ResCountrySiegeUpYuxiToClientMessage;
import com.game.data.bean.Q_npcBean;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.npc.script.INpcDefaultActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.structs.Position;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;

/**王城争霸战
 * 点击NPC 开始采集玉玺
 *
 */
public class NpcSiegeWarPluckScript implements INpcDefaultActionScript {
	
	protected Logger log = Logger.getLogger(NpcSiegeWarPluckScript.class);
	
	public static int scriptId = 5007;		//scriptid
	
	//玉玺
	public int YuXiNpc = 12300;

	public int YuXiRange = 10 ;//玉玺范围
	
//	public void NpcSiegeWarPluckScript(){
//		//必要的时候可在这里重载玉玺NCP
//		//ManagerPool.countryManager.setYuXiNpc(YuXiNpc);
//	}
	
	
	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void defaultAction(Player player, NPC npc){
		if (ManagerPool.countryManager.getSiegestate() != 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("现在不是攻城战期间，不能对玉玺操作."));
			return;
		}
		
		int money = ManagerPool.countryManager.getYuXiGuildGold();
		if (player.getGuildId() >0) {
			if (player.getMemberInfo().getGuildPowerLevel() == 1 || player.getMemberInfo().getGuildPowerLevel() == 2) {
				if (player.getGuildInfo().getStockGold()  >= money) {
					Q_npcBean npcdata = ManagerPool.dataManager.q_npcContainer.getMap().get(YuXiNpc);
					Position npcposition = new Position();
					npcposition.setX((short) (npcdata.getQ_x()*MapUtils.GRID_BORDER));
					npcposition.setY((short) (npcdata.getQ_y()*MapUtils.GRID_BORDER));
					double dis = MapUtils.countDistance(npcposition, player.getPosition());	//得到距离
					if (dis > YuXiRange * MapUtils.GRID_BORDER) {
						MessageUtil.notify_player(player, Notifys.CUTOUT, ResManager.getInstance().getString("您离玉玺远了，无法取得。"));
						return;
					} 
					
					ManagerPool.npcManager.playerGather(player, npc);
					if (System.currentTimeMillis()  - ManagerPool.countryManager.getYuxitime() > 10*1000) {
						ManagerPool.countryManager.setYuxitime(System.currentTimeMillis());
						ResCountrySiegeUpYuxiToClientMessage cmsg= new ResCountrySiegeUpYuxiToClientMessage();
						cmsg.setPlayerid(player.getId());
						cmsg.setPlayername(player.getName());
						MessageUtil.tell_world_message(cmsg);
					}
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("帮贡仓库铜币不足{1}，无法拔起秦王玉玺"),money+"");
				}
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有帮主或者副帮主才能持有秦王玉玺"));
			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有帮主或者副帮主才能持有秦王玉玺"));
		}
	}

}

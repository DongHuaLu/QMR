
package scripts.npc;

import org.apache.log4j.Logger;

import com.game.guild.manager.GuildServerManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.npc.script.INpcGatherActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.utils.MessageUtil;

/**王城争霸战
 * NPC玉玺采集完成
 *
 */
public class NpcSiegeWarGiveYuXiScript implements INpcGatherActionScript {
	
	protected Logger log = Logger.getLogger(NpcSiegeWarGiveYuXiScript.class);
	
	public static int scriptId = 5008;		//scriptid
	
	@Override
	public int getId() {
		return scriptId;
	}

	@Override
	public void gather(Player player, NPC npc){
		if (ManagerPool.countryManager.getSiegestate() != 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("攻城战已结束，不能对玉玺操作."));
			return;
		}
		int money = ManagerPool.countryManager.getYuXiGuildGold();
		if (player.getGuildId() >0) {
			if (player.getMemberInfo().getGuildPowerLevel() == 1 || player.getMemberInfo().getGuildPowerLevel() == 2) {
				if (player.getGuildInfo().getStockGold()  >= money) {
					GuildServerManager.getInstance().reqInnerKingCityEventToWorld(player,1,money+"");	//扣钱
					ManagerPool.countryManager.setYuxitime(0);//清除点击冷却
					
					ManagerPool.countryManager.SiegeGrabYuXi(player);
					//npc消失
					ManagerPool.npcManager.hideNpc(npc);
					//player停止采集
					ManagerPool.npcManager.playerStopGather(player, true);
					return;
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("帮贡仓库铜币不足{1}，无法拔起秦王玉玺"),money+"");
				}
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有帮主或者副帮主才能持有秦王玉玺"));
			}
		}
		//player停止采集
		ManagerPool.npcManager.playerStopGather(player, true);
	}
	
}

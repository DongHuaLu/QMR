package scripts.biwudao;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.game.biwudao.manager.BiWuDaoManager;
import com.game.biwudao.message.ResBiWuDaoFlagCoolDownToClientMessage;
import com.game.biwudao.message.ResBiWuDaoGuildnameToClientMessage;
import com.game.chat.bean.GoodsInfoRes;
import com.game.guild.structs.GuildTmpInfo;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.npc.script.INpcDefaultActionScript;
import com.game.npc.script.INpcGatherActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.script.structs.ScriptEnum;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.ScriptsUtils;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

/**比武岛旗帜抢夺
 * 
 * @author zhangrong
 *
 */
public class BiWuDaoFlagPluck implements INpcDefaultActionScript,INpcGatherActionScript{

	protected Logger log = Logger.getLogger(BiWuDaoFlagPluck.class);
	
	public static int scriptId = 5010;		//scriptid
	
	
	
	@Override
	public int getId() {
		return scriptId;
	}

	/**比武岛旗帜抢夺
	 * 开始，检查
	 */
	@Override
	public void defaultAction(Player player, NPC npc){
		if (ManagerPool.biWuDaoManager.getBiwudaostate() != 1) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("现在不是比武岛活动时间，不能对旗帜操作."));
			return;
		}
		if (player.isDie() == true) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("死亡状态下不能夺旗"));
			return;
		}

		
		
		if (player.getGuildId() > 0) {
			if (ManagerPool.biWuDaoManager.getBiwudaoguildid() == player.getGuildId()) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您的帮会已经是占领者."));
				return;
			}
			
			long time = System.currentTimeMillis()/1000 - ManagerPool.biWuDaoManager.getFlagcooldown();
			if ( time < BiWuDaoManager.BIWUDAO_FLAGCOOLDOWNMAX) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("{1}秒后才可争夺。"),""+(BiWuDaoManager.BIWUDAO_FLAGCOOLDOWNMAX - time));
				return;
			}
			
			ManagerPool.npcManager.playerGather(player, npc);	//开始采集
		}else {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("没有加入帮会，不能夺旗"));
		}
	}
	
	
	/**比武岛旗帜抢夺
	 * 结束
	 */
	@Override
	public void gather(Player player, NPC npc){
		
		if (ManagerPool.biWuDaoManager.getBiwudaostate() != 1) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("现在不是比武岛活动时间，不能对旗帜操作."));
			return;
		}

		if (player.getGuildId() >0) {
			String gname = ResManager.getInstance().getString("未知帮会");
			Map map = ManagerPool.mapManager.getMap(player);

			//npc消失
			ManagerPool.npcManager.hideNpc(npc);
			
			//夺旗剩余冷却时间地图广播
			ResBiWuDaoFlagCoolDownToClientMessage cmsg = new ResBiWuDaoFlagCoolDownToClientMessage();
			cmsg.setFlagcooldown(BiWuDaoManager.BIWUDAO_FLAGCOOLDOWNMAX);
			MessageUtil.tell_map_message(map, cmsg); 

			GuildTmpInfo guildTmpInfo = ManagerPool.guildServerManager.getGuildTmpInfo(player.getGuildId());
			if (guildTmpInfo != null) {
				gname = guildTmpInfo.getGuildname();
				npc.setName(gname);
			}
			
			//设定当前占领者和冷却开始时间
			ManagerPool.biWuDaoManager.setBiwudaoguildid(player.getGuildId());
			ManagerPool.biWuDaoManager.setBiwudaoguildname(gname);
			ManagerPool.biWuDaoManager.setFlagcooldown(System.currentTimeMillis()/1000);
			
			//比武岛旗帜占领者帮会名字地图广播
			ResBiWuDaoGuildnameToClientMessage gmsg = new ResBiWuDaoGuildnameToClientMessage();
			gmsg.setGuildid(player.getGuildId());
			gmsg.setGuildname(gname);
			MessageUtil.tell_map_message(map, gmsg);
			
			ManagerPool.npcManager.showNpc(npc);
			//夺旗者加军功
			int num = 10;
			if(player.getBiwudaototalrank() < BiWuDaoManager.BIWUDAO_RAMKMAX){
				if (BiWuDaoManager.BIWUDAO_RAMKMAX - player.getBiwudaototalrank() < num) {
					num = BiWuDaoManager.BIWUDAO_RAMKMAX - player.getBiwudaototalrank();
				}
				ManagerPool.rankManager.addranknum(player, num, RankType.BIWUDAO);	
				player.setBiwudaototalrank(player.getBiwudaototalrank() + num);
			}else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("已到达活动可获得军功300点上限"));
			}

			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】的【%s】在比武岛占领战旗获得10点军功奖励，该帮派成员获得真气、经验收益翻倍！{@}"), gname,player.getName()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.BIWUDAO_MOVE.getValue()));
			MessageUtil.notify_All_player(Notifys.CHAT_BULL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BIWUDAO_MOVE.getValue());
			//player停止采集
			ManagerPool.npcManager.playerStopGather(player, true);
			ScriptsUtils.call(ScriptEnum.BIWUDAO, "setmapbuff");	//地图所有玩家加减BUFF
			return;
			
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("只有帮会成员才能夺旗"));
		}
		//player停止采集
		ManagerPool.npcManager.playerStopGather(player, true);
	}
}

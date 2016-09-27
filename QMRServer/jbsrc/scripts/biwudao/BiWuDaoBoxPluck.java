package scripts.biwudao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.biwudao.manager.BiWuDaoManager;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_npcBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.npc.message.ResNpcActionMessage;
import com.game.npc.script.INpcDefaultActionScript;
import com.game.npc.script.INpcGatherActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.server.impl.WServer;
import com.game.structs.Grid;
import com.game.structs.Reasons;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

/**比武岛宝箱采集
 * 
 * @author zhangrong
 *
 */
public class BiWuDaoBoxPluck implements INpcDefaultActionScript,INpcGatherActionScript{

	protected Logger log = Logger.getLogger(BiWuDaoBoxPluck.class);
	
	public static int scriptId = 5011;		//scriptid
	
	//初始化宝箱奖励内容   // （经验，真气都是等级基础值*倍率）   
	public BiWuDaoBoxPluck(){
		boxrewardmap.put(40001, "1001_1;1004_1;1007_5;1011_1;1012_2;1015_1;1100_1;16010_1;30204_1;30301_1;30302_1;30303_1");	//道具
		boxrewardmap.put(40002, "-4_180");	//经验
		boxrewardmap.put(40003, "-5_100");	//礼金
		boxrewardmap.put(40004, "-3_120");	//真气
		boxrewardmap.put(40005, "30205_1;1011_2;1007_30;30301_2;1001_5;3011_1;3012_1;3013_1;3014_1");	//勇者
	}
	
	//宝箱奖励表
	private HashMap<Integer, String> boxrewardmap = new HashMap<Integer, String>();
	
	//宝箱ID列表
	int[] boxlist = {40001,40002,40003,40004,40005};
	
	//勇者宝箱ID
	private int YONGZHEBAOXIANGID = 40005;

	
	@Override
	public int getId() {
		return scriptId;
	}


	
	/**采集宝箱
	 * 
	 */
	@Override
	public void defaultAction(Player player, NPC npc){
		if (ManagerPool.biWuDaoManager.getBiwudaostate() != 1) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("现在不是比武岛活动时间"));
			return;
		}
		Q_npcBean npcdata = ManagerPool.dataManager.q_npcContainer.getMap().get(npc.getModelId());
		if (player.isDie() == true) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("死亡状态下不能开启【{1}】"),npcdata.getQ_name());
			return;
		}

		if (npc.getModelId() == YONGZHEBAOXIANGID) {
			if (player.getGuildId() > 0 && ManagerPool.biWuDaoManager.getBiwudaoguildid() == player.getGuildId()) {
				ManagerPool.npcManager.playerGather(player, npc);	//开始采集
			}else {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("只有比武岛占领者帮会成员才能开启{1}"),npcdata.getQ_name());
				return;
			}
		}else {
			ManagerPool.npcManager.playerGather(player, npc);	//开始采集
		}
	}
	
	
	/**宝箱打开 结束
	 */
	@Override
	public void gather(Player player, NPC npc){
		int npcid = npc.getModelId();
		if (npcid == YONGZHEBAOXIANGID) {
			if (player.getGuildId() > 0 && ManagerPool.biWuDaoManager.getBiwudaoguildid() == player.getGuildId()) {
				//发送给周围玩家，打开宝箱效果
				ResNpcActionMessage actmsg = new ResNpcActionMessage();
				actmsg.setActionType(2);
				actmsg.setNpcId(npc.getId());
				actmsg.setTatget(player.getId());
				MessageUtil.tell_round_message(player, actmsg);
				
				ManagerPool.npcManager.playerStopGather(player, true);
				ManagerPool.mapManager.quitMap(npc);
				giveboxreward(player,npcid);
				ManagerPool.biWuDaoManager.totalGainToClien(player);
				
			}else {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("只有比武岛占领者帮会成员才能开启本宝箱"));
				ManagerPool.npcManager.playerStopGather(player, true);
				return;
			}
		}else {
			
			//发送给周围玩家，打开宝箱效果
			ResNpcActionMessage actmsg = new ResNpcActionMessage();
			actmsg.setActionType(2);
			actmsg.setNpcId(npc.getId());
			actmsg.setTatget(player.getId());
			MessageUtil.tell_round_message(player, actmsg);
			
			ManagerPool.npcManager.playerStopGather(player, true);
			ManagerPool.mapManager.quitMap(npc);
			
			player.setBiwudaototalBox(player.getBiwudaototalBox() + 1);
			giveboxreward(player,npcid);	
			ManagerPool.biWuDaoManager.totalGainToClien(player);
			
		}
			
		//player停止采集
		ManagerPool.npcManager.playerStopGather(player, true);
	}
	
	
	
	
	
	/**打开宝箱后给奖励(真气和经验在这里是基础值*系数)
	 * 
	 * @param player
	 * @param npcid
	 */
	public void giveboxreward(Player player,int npcid ){
		long action = Config.getId();
		if (!boxrewardmap.containsKey(npcid)) {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("没有设置奖励，请联系管理员"));
			return;
		}

		int[][] rewtab = ManagerPool.zonesFlopManager.getZoneFixedReward(boxrewardmap.get(npcid));
		String str = "";
		int[] rewdb = rewtab[0];
		if (rewtab.length > 1) {
			int rnd = RandomUtils.random(rewtab.length);
			rewdb = rewtab[rnd];
		}
		
		//-1铜币，-2元宝，-3真气，-4经验，-5礼金，-6战魂，-7军功
		if (rewdb[0] == -1) {
			ManagerPool.backpackManager.changeMoney(player, rewdb[1], Reasons.BIWUDAO, action);
			str = str + ResManager.getInstance().getString(" 铜币:") + rewdb[1];
		} else if (rewdb[0] == -2) {
			//元宝为特殊，暂不加
		} else if (rewdb[0] == -3) {
			Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
			ManagerPool.playerManager.addZhenqi(player, rewdb[1]*model.getQ_basis_zhenqi(), AttributeChangeReason.BIWUDAO_BOX);
			str = str + ResManager.getInstance().getString(" 真气:") + (rewdb[1]*model.getQ_basis_zhenqi());
		} else if (rewdb[0] == -4) {
			Q_characterBean model = DataManager.getInstance().q_characterContainer.getMap().get(player.getLevel());
			ManagerPool.playerManager.addExp(player, rewdb[1]*model.getQ_basis_exp(), AttributeChangeReason.BIWUDAO_BOX);
			str = str + ResManager.getInstance().getString(" 经验:") + (rewdb[1]*model.getQ_basis_exp());
		} else if (rewdb[0] == -5) {
			ManagerPool.backpackManager.changeBindGold(player, rewdb[1], Reasons.BIWUDAO, action);
			str = str + ResManager.getInstance().getString(" 礼金:") + rewdb[1];
		} else if (rewdb[0] == -6) {
			ManagerPool.arrowManager.addFightSpiritNum(player, 1, rewdb[1], true, ArrowReasonsType.BIWUDAO);
			str = str + ResManager.getInstance().getString(" 战魂:") + rewdb[1];
		} else if (rewdb[0] == -7) {
			ManagerPool.rankManager.addranknum(player, rewdb[1], RankType.BIWUDAO);
			str = str + ResManager.getInstance().getString(" 军功:") + rewdb[1];
		} else if (rewdb[0] > 0) {
			List<Item> createItems = Item.createItems(rewdb[0], rewdb[1], true, 0);
			if (!createItems.isEmpty()) {
				if (ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
					if (!BackpackManager.getInstance().addItems(player, createItems, Reasons.BIWUDAO, action)) {
						MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(),ResManager.getInstance().getString("系统邮件"),  ResManager.getInstance().getString("比武岛宝箱奖励"), (byte) 0, 0, createItems);
					}
				} else {
					MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("系统邮件"),  ResManager.getInstance().getString("比武岛宝箱奖励"), (byte) 0, 0, createItems);
				}
				str = str + String.format(" %s*（%d）", BackpackManager.getInstance().getName(rewdb[0]), rewdb[1]);
			}
		}
		Q_npcBean npcdata = ManagerPool.dataManager.q_npcContainer.getMap().get(npcid);
   		ParseUtil parseUtil = new ParseUtil();
		parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】在决战比武岛活动中开启【%s】获得%s {@}"),player.getName(),npcdata.getQ_name(),str), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.BIWUDAO_MOVE.getValue()));
		MessageUtil.notify_All_player(Notifys.CHAT_SYSTEM,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BIWUDAO_MOVE.getValue());
		MessageUtil.notify_player(player, Notifys.SUCCESS, "开启{1}获得了{2}",npcdata.getQ_name(),str );
	}
	
	
	
	
	/**比武岛刷新刷新宝箱
	 * 
	 */
	public void refreshBox(List<Object> list){
		Map map = ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(),1,BiWuDaoManager.BIWUDAO_MAPID);
	
		//得到范围内格子
		List<Grid> gridlist = MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(71, 119, map.getMapModelid()),28*MapUtils.GRID_BORDER , BiWuDaoManager.BIWUDAO_MAPID);
		
		gridlist.addAll(MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(68, 43, map.getMapModelid()),28*MapUtils.GRID_BORDER , BiWuDaoManager.BIWUDAO_MAPID));

		gridlist.addAll(MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(139, 38, map.getMapModelid()),28*MapUtils.GRID_BORDER , BiWuDaoManager.BIWUDAO_MAPID));

		gridlist.addAll(MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(174, 107, map.getMapModelid()),28*MapUtils.GRID_BORDER , BiWuDaoManager.BIWUDAO_MAPID));
		
		if (map != null && gridlist != null) {
			for (int i = 0; i < boxlist.length; i++) {
				List<NPC> npcs = ManagerPool.npcManager.findNpc(map, boxlist[i]);
				int num = 10 - npcs.size();	
				for (int j = 0; j < num; j++) {//补足10个
					int rnd = RandomUtils.random( gridlist.size());//随机格子
					NPC npc = ManagerPool.npcManager.createNpc(boxlist[i], map, true);
					npc.setPosition(gridlist.get(rnd).getCenter());
					ManagerPool.mapManager.enterMap(npc);
				}
			}
		}
		ParseUtil parseUtil = new ParseUtil();
		parseUtil.setValue(String.format(ResManager.getInstance().getString("比武岛天降宝箱！采集即有！海量奖励抢不停！{@}")), new ParseUtil.VipParm(0,GuideType.BIWUDAO_MOVE.getValue()));
		MessageUtil.notify_All_player(Notifys.CHAT_BULL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BIWUDAO_MOVE.getValue());
	}
	
	
	
	
	
	
}

package scripts.biwudao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.biwudao.bean.BiWuInfo;
import com.game.biwudao.manager.BiWuDaoManager;
import com.game.biwudao.message.ResBiWuDaoSurplusTimeToClientMessage;
import com.game.biwudao.message.ResBiWuInfoToClientMessage;
import com.game.buff.structs.Buff;
import com.game.chat.bean.GoodsInfoRes;
import com.game.data.bean.Q_mapBean;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterMapScript;
import com.game.map.structs.Map;
import com.game.npc.struts.NPC;
import com.game.pet.struts.Pet;
import com.game.player.script.IPlayerDieScript;
import com.game.player.script.IPlayerLoginEndScript;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.server.script.IServerEventTimerScript;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.ScriptsUtils;
import com.game.utils.TimeUtil;
import com.game.vip.struts.GuideType;

/**比武岛脚本
 * 
 * @author zhangrong
 *
 */
public class BiWuDaoScript implements IPlayerLoginEndScript,IEnterMapScript ,IServerEventTimerScript ,IPlayerDieScript {
	protected Logger log = Logger.getLogger(BiWuDaoScript.class);
	@Override
	public int getId() {
		return ScriptEnum.BIWUDAO;
	}

	//进入地图的坐标列表 
	private int[][] coordinates = {{60,115},{52,68},{55,42},{99,127},{154,122},{191,99},{195,63},{153,38},{70,34}};
	
	
	//玩家死亡
	@Override
	public void onPlayerDie(Player player, Fighter attacker) {
		Map map = ManagerPool.mapManager.getMap(player);
		if (map !=null && map.getMapModelid() == BiWuDaoManager.BIWUDAO_MAPID ) {
			Player attackPlayer = null;
			if (attacker instanceof Pet) {
				 attackPlayer = ManagerPool.petInfoManager.getPetHost((Pet) attacker);
			} else if (attacker instanceof Player) {
				attackPlayer = (Player)attacker;
			}
			
			if (attackPlayer != null) {
				//击杀同帮派不加军功
				if (ManagerPool.biWuDaoManager.getBiwudaostate() == 1) {
					if (player.getGuildId() > 0 && player.getGuildId() == attackPlayer.getGuildId()) {
						
					}else {
						//若对方为旗帜占领方则获得2点军功值,否则+1
						if(attackPlayer.getBiwudaototalrank() < BiWuDaoManager.BIWUDAO_RAMKMAX){
							int num = 1;
							if (player.getGuildId() > 0 && player.getGuildId() == ManagerPool.biWuDaoManager.getBiwudaoguildid() ) {
								num = 2;
							}
							if (BiWuDaoManager.BIWUDAO_RAMKMAX - attackPlayer.getBiwudaototalrank() < num) {
								num = BiWuDaoManager.BIWUDAO_RAMKMAX - attackPlayer.getBiwudaototalrank();
							}
							attackPlayer.setBiwudaototalrank(attackPlayer.getBiwudaototalrank() + num);
							ManagerPool.rankManager.addranknum(attackPlayer,num, RankType.BIWUDAO_KILL);
							ManagerPool.biWuDaoManager.totalGainToClien(attackPlayer);
						}  
					}
				}else if (ManagerPool.biWuDaoManager.getBiwudaostate() == 2) {
					MessageUtil.notify_player(attackPlayer,Notifys.ERROR, ResManager.getInstance().getString("【决战比武岛】活动已结束，不能获得军功"));
				}
			}
		}
	}

	


	
	/**进入比武岛后发送指定消息
	 * 
	 */
	@Override
	public void onEnterMap(Player player, Map map) {
		if(ManagerPool.biWuDaoManager.getBiwudaostate() == 1){
			if (map.getMapModelid() == BiWuDaoManager.BIWUDAO_MAPID ) {
				BiWuInfo biWuInfo = new  BiWuInfo();
				biWuInfo.setAreadouble(0);
				biWuInfo.setAvailableexp(0);
				biWuInfo.setAvailablezhenqi(0);
				if (ManagerPool.biWuDaoManager.getFlagcooldown() > 0) {	//每次插旗后的冷却时间
					int num = (int)((System.currentTimeMillis()/1000) - ManagerPool.biWuDaoManager.getFlagcooldown());
					biWuInfo.setFlagcooldown(BiWuDaoManager.BIWUDAO_FLAGCOOLDOWNMAX - num);
				}
				biWuInfo.setGuildid(ManagerPool.biWuDaoManager.getBiwudaoguildid());
				biWuInfo.setGuildname(ManagerPool.biWuDaoManager.getBiwudaoguildname());
				//活动结束时间倒计时
				int time = (int)((System.currentTimeMillis()/1000) - ManagerPool.biWuDaoManager.getBiwudaocountdown());
				biWuInfo.setSurplustime(BiWuDaoManager.BIWUDAO_TIME_MAX - time);
				biWuInfo.setTotalBox(player.getBiwudaototalBox());
				biWuInfo.setTotalexp(player.getBiwudaototalexp());
				biWuInfo.setTotalrank(player.getBiwudaototalrank());
				biWuInfo.setTotalzhenqi(player.getBiwudaototalzhenqi());
				ResBiWuInfoToClientMessage cmsg = new ResBiWuInfoToClientMessage();
				cmsg.setBiWuInfo(biWuInfo);
				MessageUtil.tell_player_message(player, cmsg);
				//删除原来的死亡复活保护BUFF和 大恶人BUFF
				ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_FOR_KILLED);
				ManagerPool.buffManager.removeByBuffId(player, Global.EVIL_BUFF);
				
				if(player.getGuildId() > 0 && ManagerPool.biWuDaoManager.getBiwudaoguildid() == player.getGuildId()){
					//增加比武岛BUFF
					ManagerPool.buffManager.addBuff(player, player, BiWuDaoManager.BIWUDAO_BUFF, 0, 0, 0);
				}else {
					//删除比武岛BUFF
					ManagerPool.buffManager.removeByBuffId(player, BiWuDaoManager.BIWUDAO_BUFF);
				}
			}
		}
	}

	
	
	
	@Override
	public void action(int serverId, String serverWeb) {
		long millis = System.currentTimeMillis();
		long week = TimeUtil.getDayOfWeek(millis);
		long min = TimeUtil.getDayOfMin(millis);
		long hour = TimeUtil.getDayOfHour(millis);
		int kday = TimeUtil.getOpenAreaDay();
		if (kday >= BiWuDaoManager.OPEN_DAY) {
			if (week == 3 || week == 7 ) {
				if (hour == 20 &&  min == 30) {
					ParseUtil parseUtil = new ParseUtil();
					parseUtil.setValue(String.format(ResManager.getInstance().getString("【决战比武岛】活动将于30分钟后准时开启（持续时间21:00至21:30）！{@}")), new ParseUtil.VipParm(0,GuideType.BIWUDAO.getValue()));
					MessageUtil.notify_All_player(Notifys.CHAT_BULL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BIWUDAO.getValue());
					MessageUtil.notify_All_player(Notifys.SROLL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BIWUDAO.getValue());
				}else if (hour == 20 &&  min == 50) {
					ParseUtil parseUtil = new ParseUtil();
					parseUtil.setValue(String.format(ResManager.getInstance().getString("【决战比武岛】活动将于10分钟后准时开启（持续时间21:00至21:30）！{@}")), new ParseUtil.VipParm(0,GuideType.BIWUDAO.getValue()));
					MessageUtil.notify_All_player(Notifys.CHAT_BULL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BIWUDAO.getValue());
					MessageUtil.notify_All_player(Notifys.SROLL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BIWUDAO.getValue());
				}else if (hour == 21 && (min == 0 || min == 1)) {	//开放入口
					if (ManagerPool.biWuDaoManager.getBiwudaostate() != 1) {
						biwudaoinitialize(new ArrayList<Object>());
					}
				}else if (hour == 21 &&  (min == 30 || min == 31) ){//结束，关闭入口
					if (ManagerPool.biWuDaoManager.getBiwudaostate() == 1) {
						biwudaoend(new ArrayList<Object>());
					}
				}else if (hour == 21 && min == 32){	//所有人传送离开地图
					Map map = ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(),1,BiWuDaoManager.BIWUDAO_MAPID);
					map.getParameters().put("move", 1);	//标记全部回城
				}else if (hour == 23 && min >= 58) {
					ManagerPool.biWuDaoManager.setBiwudaostate(0);
				}
			}
			
			//刷宝箱NPC
			if (ManagerPool.biWuDaoManager.getBiwudaostate() == 1) {
				int stime = (int)((System.currentTimeMillis()/1000) - ManagerPool.biWuDaoManager.getBiwudaocountdown());
				if (min%3 == 0 && stime > 60) {//3分钟 刷一次
					ScriptsUtils.call(5011, "refreshBox");	//刷新宝箱调用 BiWuDaoBoxPluck
				}
			}
			//如果是手动开启，会在这里执行自动关闭
			if (ManagerPool.biWuDaoManager.getBiwudaostate() == 1) {
				int time = (int)((System.currentTimeMillis()/1000) - ManagerPool.biWuDaoManager.getBiwudaocountdown());
				if(time >= BiWuDaoManager.BIWUDAO_TIME_MAX ){
					biwudaoend(new ArrayList<Object>());
				}
			}
		}
	}
	
	
	
	/**比武岛开始，进行初始化
	 * 
	 */
	public void biwudaoinitialize(List<Object> list){
		Map map = ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(),1,BiWuDaoManager.BIWUDAO_MAPID);
		if (map != null) {
			List<NPC> npcs = ManagerPool.npcManager.findNpc(map, BiWuDaoManager.BIWUDAO_FLAG);
			if (npcs.size() > 0) {
				NPC npc = npcs.get(0);
				npc.setName(ResManager.getInstance().getString("未被占领"));
			}
			
			ManagerPool.biWuDaoManager.setBiwudaoguildid(0);
			ManagerPool.biWuDaoManager.setBiwudaoguildname("");
			ManagerPool.biWuDaoManager.setBiwudaocountdown((int) (System.currentTimeMillis()/1000));
			ManagerPool.biWuDaoManager.setBiwudaostate(1);
			ManagerPool.biWuDaoManager.setFlagcooldown(0);
			
			ResBiWuDaoSurplusTimeToClientMessage cmsg = new ResBiWuDaoSurplusTimeToClientMessage();
			cmsg.setSurplustime(BiWuDaoManager.BIWUDAO_TIME_MAX);
			MessageUtil.tell_world_message(cmsg);
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("【决战比武岛】活动开始了，海量经验拿不停！请达到40级的勇士们参与活动！{@}")), new ParseUtil.VipParm(0,GuideType.BIWUDAO.getValue()));
			MessageUtil.notify_All_player(Notifys.SROLL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.BIWUDAO.getValue());

		}else {
			log.error("错误！比武岛活动开始：比武岛地图找不到:"+BiWuDaoManager.BIWUDAO_MAPID);
		}

		
	}
	
	
	/**比武岛结束
	 * 
	 */
	public void biwudaoend(List<Object> list){
		ManagerPool.biWuDaoManager.setBiwudaoguildid(0);
		ManagerPool.biWuDaoManager.setBiwudaoguildname("");
		ManagerPool.biWuDaoManager.setBiwudaocountdown(0);
		ManagerPool.biWuDaoManager.setBiwudaostate(2);
		ManagerPool.biWuDaoManager.setFlagcooldown(0);
		ResBiWuDaoSurplusTimeToClientMessage cmsg = new ResBiWuDaoSurplusTimeToClientMessage();
		MessageUtil.tell_world_message(cmsg);//时间0就表示结束
		MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("决战比武岛活动已经结束，请期待下次活动开启，祝大家游戏愉快！"));
		setmapbuff(new ArrayList<Object>()); //清理BUFF
		
	}
	
	
	
	/**找出地图所有玩家，设置BUFF
	 * 
	 */
	public void setmapbuff(List<Object> list){	//BiWuDaoFlagPluck调用
		Map map = ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(),1,BiWuDaoManager.BIWUDAO_MAPID);
		if (map != null) {
			if (map.getMapModelid() == BiWuDaoManager.BIWUDAO_MAPID) {
				Player[] players = map.getPlayers().values().toArray(new Player[0]);
				for (Player player : players) {
					if(player.getGuildId() > 0 && ManagerPool.biWuDaoManager.getBiwudaoguildid() == player.getGuildId()){
						ManagerPool.buffManager.addBuff(player, player, BiWuDaoManager.BIWUDAO_BUFF, 0, 0, 0);
					}else {
						ManagerPool.buffManager.removeByBuffId(player, BiWuDaoManager.BIWUDAO_BUFF);
					}
				}
			}
		}
	}
	
	
	/**每日清理数据
	 * 
	 * @param player
	 */
	public void clearDay(Player player){
		long day=TimeUtil.GetCurTimeInMin(4);	//每天清理奖励数据
		if(player.getBiwudaoday() != day ){
			player.setBiwudaoday((int) day);
			player.setBiwudaototalBox(0);
			player.setBiwudaototalexp(0);
			player.setBiwudaototalrank(0);
			player.setBiwudaototalzhenqi(0);
		}
	}
	
	
	
	/**进入比武岛
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void biwudaoentr(List<Object> list) {
		Player player = (Player)list.get(0);
		if (player != null ) {
			Map map = ManagerPool.mapManager.getMap(player);
			if (map.getMapModelid() == BiWuDaoManager.BIWUDAO_MAPID) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您已经在比武岛内。"));
				return;
			}
			if (ManagerPool.biWuDaoManager.getBiwudaostate() == 2) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("决战比武岛活动已经结束，下次再来吧。"));
				return;
			}else if (ManagerPool.biWuDaoManager.getBiwudaostate() != 1) {
				String str = ManagerPool.biWuDaoManager.getbiwudaotimeinfo();
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("决战比武岛活动还未开启。（{1}）"),str);
				return;
			}
			if (player.getLevel() < 40) {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("40级以上才能参加比武岛活动。"));
				return;
			}
			
			clearDay(player);

			//和平保护BUFF
			List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, Global.PROTECT_IN_SIEGE);
			if (buffs.size() == 0) {
				ManagerPool.buffManager.addBuff(player, player, Global.PROTECT_IN_SIEGE, 0, 0, 0);
			}
			//改变PK模式 0-和平 1-组队 2-帮会 3-全体
			if (player.getGuildId() > 0 && player.getPkState() != 2 ) {
				ManagerPool.playerManager.changePkState(player, 2, 0);
			}else if (player.getGuildId() == 0 && player.getPkState() != 3 ) {
				ManagerPool.playerManager.changePkState(player, 3, 0);
			}

			int r = RandomUtils.random(coordinates.length);	//随机坐标
			Grid grid = MapUtils.getGrid(coordinates[r][0], coordinates[r][1], map.getMapModelid());
			if (grid != null) {
				List<Grid> gridlist = MapUtils.getRoundNoBlockGrid(grid.getCenter(),3*MapUtils.GRID_BORDER ,BiWuDaoManager.BIWUDAO_MAPID);
				int rnd = RandomUtils.random(gridlist.size());
				ManagerPool.mapManager.changeMap(player,BiWuDaoManager.BIWUDAO_MAPID,BiWuDaoManager.BIWUDAO_MAPID, 1, gridlist.get(rnd).getCenter(), this.getClass().getName() + ".biwudaoentr");
			}
		}
	}
	
	
	/**离开比武岛
	 * 地图
	 * @param player
	 */
	public void biwudaoleave(List<Object> list) {
		Player player = (Player)list.get(0);
		Map map = ManagerPool.mapManager.getMap(player);
		if (map.getMapModelid() == BiWuDaoManager.BIWUDAO_MAPID) {
			ManagerPool.buffManager.removeByBuffId(player, BiWuDaoManager.BIWUDAO_BUFF);//移除BUFF
			Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(BiWuDaoManager.BIWUDAO_MAPID);
			Position position = ManagerPool.mapManager.RandomDieBackCity(mapBean);
			List<Grid> gridlist = MapUtils.getRoundNoBlockGrid(position,15*MapUtils.GRID_BORDER , mapBean.getQ_map_quit());
			int rnd = RandomUtils.random(gridlist.size());
			ManagerPool.playerManager.changePkState(player, 0, 0);
			ManagerPool.mapManager.changeMap(player, mapBean.getQ_map_quit(), mapBean.getQ_map_quit(), 0, gridlist.get(rnd).getCenter(), this.getClass().getName() + ".biwudaoleave");
		}
	}





	/**登录发送活动时间
	 * 
	 */
	@Override
	public void onLogin(Player player, int type) {
		if (ManagerPool.biWuDaoManager.getBiwudaostate() == 1) {
			//活动结束时间倒计时
			int time = (int)((System.currentTimeMillis()/1000) - ManagerPool.biWuDaoManager.getBiwudaocountdown());
			ResBiWuDaoSurplusTimeToClientMessage cmsg = new ResBiWuDaoSurplusTimeToClientMessage();
			cmsg.setSurplustime(BiWuDaoManager.BIWUDAO_TIME_MAX - time);
			MessageUtil.tell_player_message(player, cmsg);
			ManagerPool.buffManager.removeByBuffId(player, BiWuDaoManager.BIWUDAO_BUFF);//移除BUFF
		}else if (ManagerPool.biWuDaoManager.getBiwudaostate() == 2 && player.getLevel() >= 40) {
			ManagerPool.buffManager.removeByBuffId(player, BiWuDaoManager.BIWUDAO_BUFF);//移除BUFF
		}
	}
		
	
	

	
	
	
	
}

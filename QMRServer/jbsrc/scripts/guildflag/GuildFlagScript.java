package scripts.guildflag;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.game.backpack.structs.Item;
import com.game.buff.structs.BuffConst;
import com.game.data.bean.Q_mapBean;
import com.game.dblog.LogService;
import com.game.drop.structs.MapDropInfo;
import com.game.fight.structs.Fighter;
import com.game.guildflag.log.GuildFlagLog;
import com.game.guildflag.manager.GuildFlagManager;
import com.game.guildflag.message.ResGuildFlagStatusToClientMessage;
import com.game.guildflag.structs.GuildTerritoryFlag;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.bean.DropGoodsInfo;
import com.game.map.manager.MapManager;
import com.game.map.script.IEnterMapScript;
import com.game.map.structs.Map;
import com.game.monster.script.IMonsterDieScript;
import com.game.monster.structs.Monster;
import com.game.pet.struts.Pet;
import com.game.player.script.IPlayerLoginEndScript;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.server.script.IServerEventTimerScript;
import com.game.task.struts.RankTaskEnum;
import com.game.task.struts.Task;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;

public class GuildFlagScript implements IEnterMapScript ,IServerEventTimerScript ,IMonsterDieScript ,IPlayerLoginEndScript {

	@Override
	public int getId() {
		return ScriptEnum.GUILDFLAG;
	}

	//日期标记
	private int dropday = 0;
	//掉落令牌计数
	private int dropcount = 0;
	//每日掉落令牌最大数量
	private int dropcountmax=0;
	
	//**进入地图触发
	@Override
	public void onEnterMap(Player player, Map map) {
		ManagerPool.guildFlagManager.checkAndAddGuildFlagBUFF(player,map);
	}


	//每分钟调用
	@Override
	public void action(int serverId, String serverWeb) {
		long millis = System.currentTimeMillis();
		long week = TimeUtil.getDayOfWeek(millis);
		long min = TimeUtil.getDayOfMin(millis);
		long hour = TimeUtil.getDayOfHour(millis);
		int kday = TimeUtil.getOpenAreaDay();
		int oday = ManagerPool.guildFlagManager.getOpenArea();
		if (kday >= oday) {
			if (week == 2 || week == 5) {
				if (hour == 20 && ( min == 30 || min == 31)) {  	//开始
					if (ManagerPool.guildFlagManager.getFlagwarstatus() == 0) {
						ManagerPool.guildFlagManager.setFlagwarstatus(1);
						ManagerPool.guildFlagManager.setFlagwarendtime((int)(millis/1000) + GuildFlagManager.flagwarmaxtime);
						GuildFlagManager.guildnamemap.clear();
						GuildFlagManager.attackjfmap.clear();
						MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("帮会领地争夺战开始了！各帮正副帮主可在【咸阳城】及【赵苑】以上更高级地图中安插帮旗，在安插本帮帮旗的地图中打怪、打坐有额外收益加成。"));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("帮会领地争夺战开始了！各帮正副帮主可在【咸阳城】及【赵苑】以上更高级地图中安插帮旗，在安插本帮帮旗的地图中打怪、打坐有额外收益加成。"));
						ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
						cmsg.setTime(GuildFlagManager.flagwarmaxtime);
						MessageUtil.tell_world_message(cmsg);
						
						try {
							GuildFlagLog glog = new GuildFlagLog();
							glog.setType(0);
							glog.setData(JSON.toJSONString(ManagerPool.guildFlagManager.getTerritorymap(), SerializerFeature.WriteClassName));
							LogService.getInstance().execute(glog);
						} catch (Exception e) {
							
						}
					}
				}else if (hour == 21 && ( min == 30 || min == 31 )){	//结束
					
					if (ManagerPool.guildFlagManager.getFlagwarstatus() == 1) {
						ManagerPool.guildFlagManager.saveguildfiag();
						ManagerPool.guildFlagManager.setFlagwarstatus(2);
						ManagerPool.guildFlagManager.setFlagwarendtime((int)(System.currentTimeMillis()/1000)+5);
						MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("今日的帮会领地争夺战已经结束，为了帮派，请在下次时间准时参与。"));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("今日的帮会领地争夺战已经结束，为了帮派，请在下次时间准时参与。"));
						ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
						MessageUtil.tell_world_message(cmsg);
					}
	
				}else if (hour == 23 && min >= 58){	
					ManagerPool.guildFlagManager.setFlagwarstatus(0);
					
				}else if (hour == 0 && min == 1) {
					ManagerPool.guildFlagManager.setFlagmaplist(ManagerPool.guildFlagManager.getmapidlist());	//重新加载可插旗地图列表
				}
			}
		}
		
		//1和2区有特殊处理（2012 10 22） 先开放一场
//		if (week == 1 ) {
//			int xday= TimeUtil.GetSeriesDay();
//			int sid = WServer.getInstance().getServerId();
//			if (xday == 20121022 && sid <= 2) {
//				if (hour == 20 &&  min == 30) {  	//开始
//					ManagerPool.guildFlagManager.setFlagwarstatus(1);
//					ManagerPool.guildFlagManager.setFlagwarendtime((int)(System.currentTimeMillis()/1000) + GuildFlagManager.flagwarmaxtime);
//					GuildFlagManager.guildnamemap.clear();
//					GuildFlagManager.attackjfmap.clear();
//					MessageUtil.notify_All_player(Notifys.SROLL, "帮会领地争夺战开始了！各帮帮主可在【襄阳城】及【赵苑】以上更高级地图中安插帮旗，在安插本帮帮旗的地图中打怪、打坐有额外收益加成。");
//					ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
//					cmsg.setTime(GuildFlagManager.flagwarmaxtime);
//					MessageUtil.tell_world_message(cmsg);
//				}else if (hour == 21 && min == 30){	//结束
//					ManagerPool.guildFlagManager.setFlagwarstatus(2);
//					ManagerPool.guildFlagManager.setFlagwarendtime((int)(System.currentTimeMillis()/1000)+5);
//					MessageUtil.notify_All_player(Notifys.SROLL, "今日的帮会领取争夺战已经结束，为了帮派，请在下次时间准时参与。");
//					ResGuildFlagStatusToClientMessage cmsg = new ResGuildFlagStatusToClientMessage();
//					MessageUtil.tell_world_message(cmsg);
//				}else if (hour == 23 && min >= 58){	
//					ManagerPool.guildFlagManager.setFlagwarstatus(0);
//				}else if (hour == 0 && min == 1) {
//					ManagerPool.guildFlagManager.setFlagmaplist(ManagerPool.guildFlagManager.getmapidlist());	//重新加载可插旗地图列表
//				}	
//			}
//		}	
	}


	
	
	//帮会领地争夺战，帮旗死亡
	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
		Player player = null;
		Map map = MapManager.getInstance().getMap(monster);
		if (map == null) {
			return;
		}
		if (ManagerPool.guildFlagManager.getFlagmaplist().contains(map.getMapModelid()) && 
			ManagerPool.guildFlagManager.getflagmonidlist().contains(monster.getModelId())) {
			ConcurrentHashMap<Integer, GuildTerritoryFlag> territorymap = ManagerPool.guildFlagManager.getTerritorymap();
			if(territorymap.containsKey(map.getMapModelid())){
				GuildTerritoryFlag guildTerritoryFlag = territorymap.get(map.getMapModelid());
				guildTerritoryFlag.setFlagid(0);
				long newgid = ManagerPool.guildFlagManager.getjifensequence(map.getMapModelid());
				long oldgid = guildTerritoryFlag.getGuildid();
				String newname = ManagerPool.guildFlagManager.getGuildName(newgid);
				String oldname = guildTerritoryFlag.getGuildname();
				territorymap.remove(map.getMapModelid());	//删除之前的占领帮会
				ManagerPool.guildFlagManager.saveguildfiag();	//更新到数据库
				Q_mapBean mapdb = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
				String mapname=mapdb.getQ_map_name();
				
				if (newgid == 0) {
					MessageUtil.notify_guild_all(oldgid , String.format(ResManager.getInstance().getString("本帮安插在【%s】的帮旗无帮派人士攻破，此仇必须铭记。"),mapname));
					MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("【{1}】帮派在【{2}】安插的帮旗被攻破了。"),oldname, mapname);
				}else {
					MessageUtil.notify_guild_all(newgid , String.format(ResManager.getInstance().getString("在本帮成员的一致团结下，成功攻破了【%s】帮派在【%s】安插的帮旗。"),oldname,mapname));
					MessageUtil.notify_guild_all(oldgid , String.format(ResManager.getInstance().getString("本帮安插在【%s】的帮旗被【%s】帮派攻破，此仇必须铭记。"),mapname,newname));
					MessageUtil.notify_All_player(Notifys.CUTOUT, ResManager.getInstance().getString("在【{1}】帮派成员的一致团结下，成功攻破了【{2}】帮派在【{3}】安插的帮旗。"),newname ,oldname, mapname);
				}
				if (killer instanceof Player) {
					player = (Player)killer;
				}else if (killer instanceof Pet) {
					player=ManagerPool.petInfoManager.getPetHost((Pet)killer);
				}
				if (player != null) {
					//军衔任务加次数
					ManagerPool.taskManager.action(player, Task.ACTION_TYPE_RANK, RankTaskEnum.KILLGUILDBANNER, 1);
				}
				
				//guildflagDrop(monster,map);
			}
		}
	}


	
	/**帮旗死亡
	 * 每日限量掉落令牌
	 * 
	 */
	public void guildflagDrop(Monster monster,Map map ){
		boolean isdrop = false;
		int curday = TimeUtil.GetCurDay(0);
		if (dropday  != curday) {//天数不符，则重置
			dropday = curday;
			dropcount=0;
			isdrop = true;
		}else {
			if (dropcount < dropcountmax) {
				isdrop = true;
			}
		}
		
		if (isdrop) {
			int[] itemids={3011,3012,3013,3014};
			List<Item> items = Item.createItems(itemids[RandomUtils.random(itemids.length)], 1, false,0);
			if (!items.isEmpty()) {
				dropcount = dropcount + 1;
				Item item = items.get(0);
				item.setGridId(0);
				DropGoodsInfo info = new DropGoodsInfo();
				info.setDropGoodsId(item.getId());
				info.setItemModelId(item.getItemModelId());
				info.setNum(item.getNum());
				info.setX(monster.getPosition().getX());
				info.setY(monster.getPosition().getY());
				info.setDropTime(System.currentTimeMillis());
				MapDropInfo mapDropInfo = new MapDropInfo(info, item, map, System.currentTimeMillis() + 60 * 5 * 1000);
				ManagerPool.mapManager.enterMap(mapDropInfo);
			}
		}
	}
	
	
	
	

	@Override
	public void onLogin(Player player, int type) {
		if (type == 0 && ManagerPool.guildFlagManager.getFlagwarstatus() != 1) {
			if(ManagerPool.guildFlagManager.ckFlagBuff(player)){
				ManagerPool.buffManager.removeByBuffId(player, BuffConst.FLAG_DEF_BUFF);
				ManagerPool.buffManager.removeByBuffId(player, BuffConst.FLAG_BUFF);
			}
		}
	}

}

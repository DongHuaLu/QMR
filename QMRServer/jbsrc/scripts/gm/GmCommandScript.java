package scripts.gm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.backpack.structs.Item;
import com.game.buff.structs.Buff;
import com.game.cache.impl.MemoryCache;
import com.game.cache.structs.LRULinkedHashMap;
import com.game.collect.struts.Collect;
import com.game.collect.struts.CollectItem;
import com.game.count.structs.CountTypes;
import com.game.country.manager.CountryAwardManager;
import com.game.country.manager.CountryManager;
import com.game.country.structs.KingCity;
import com.game.country.structs.KingData;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_scene_monsterBean;
import com.game.db.DBServer;
import com.game.db.bean.Gold;
import com.game.db.bean.Role;
import com.game.db.dao.RoleDao;
import com.game.gm.message.GmCommandToGateMessage;
import com.game.gm.message.GmCommandToWorldMessage;
import com.game.gm.script.IGmCommandScript;
import com.game.horseweapon.manager.HorseWeaponManager;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.structs.Area;
import com.game.monster.structs.Monster;
import com.game.pet.manager.PetInfoManager;
import com.game.pet.manager.PetOptManager;
import com.game.pet.struts.Pet;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ResPlayerZhenqiChangeMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.server.config.GameConfig;
import com.game.server.config.MapConfig;
import com.game.server.impl.MServer;
import com.game.server.impl.WServer;
import com.game.server.loader.CheckConfigXmlLoader;
import com.game.server.loader.ServerHeartConfigXmlLoader;
import com.game.server.thread.ServerThread;
import com.game.signwage.structs.Wage;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.task.manager.TaskManager;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.HttpUtil;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.ScriptsUtils;
import com.game.utils.ServerParamUtil;
import com.game.utils.StringUtil;
import com.game.utils.Symbol;
import com.game.utils.VersionUpdateUtil;
import com.game.utils.WordFilter;
import com.game.zones.structs.JiaoChangInfo;


public class GmCommandScript implements IGmCommandScript {

	private static Logger log = Logger.getLogger(GmCommandScript.class);
	
	@Override
	public int getId() {
		return ScriptEnum.GM_COMMAND;
	}

	@Override
	public void doCommand(Player player, String command) {
		//分割指令
		String[] strs = command.split(" ");
		
		if("&reloadmap".equalsIgnoreCase(strs[0])){
			List<MapConfig> mapConfigs = new ArrayList<MapConfig>();
			MapConfig mapConfig = new MapConfig();
			mapConfig.setLineId(Integer.parseInt(strs[1]));
			mapConfig.setMapModelId(Integer.parseInt(strs[2]));
			mapConfig.setServerId(WServer.getInstance().getServerId());
			mapConfigs.add(mapConfig);
			
			WServer.getInstance().createMapServer("掉线地图", 0, 0, mapConfigs);
		}else if("&showmapthreads".equalsIgnoreCase(strs[0])){
//			Thread[] threads = new Thread[4000];
//			Thread.enumerate(threads);
			Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
			Thread[] threads = map.keySet().toArray(new Thread[0]);
			for (int i = 0; i < threads.length; i++) {
				if(threads[i]!=null){
					log.error(threads[i].getId() + "-->" + threads[i].getName());
				}
			}
		
		}else if("&showplayermap".equalsIgnoreCase(strs[0])){
			log.error("检查地图");
			String roleid = strs[1];
			Player target = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
			if(target!=null){
				Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(target.getMapModelId());
				Position position = null;
				int id =0;
				try {
					id = mapBean.getQ_map_id();
					String xystr = mapBean.getQ_rnd_die_xy();
					List<Integer[]> xyList = JSON.parseArray(xystr, Integer[].class);
					int rnd = RandomUtils.random(1, xyList.size())-1;
					Integer[] xydata = xyList.get(rnd);
					Grid[][] grids = ManagerPool.mapManager.getMapBlocks(id);
					Grid grid = MapUtils.getGrid(xydata[0], xydata[1], grids);
					position = grid.getCenter();
				} catch (Exception e) {
					log.error(id+"没有设置随机回城点"+e, e);
				}
				log.error("取得坐标点:" + (position==null));
				log.error("玩家(" + target.getName() + ")在地图" + target.getMap() + "(" + target.getMapModelId() + "):" + target.getPosition() + "-->" + position);
			}else{
				log.error("玩家(" + roleid + ")未找到");
			}
		}else if("&setplayermap".equalsIgnoreCase(strs[0])){
			String roleid = strs[1];
			int mapid = Integer.parseInt(strs[2]);
			Player target = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
			if(target!=null){
				target.setMap(mapid);
				target.setMapModelId(mapid);
				Grid[][] grids = ManagerPool.mapManager.getMapBlocks(mapid);
				Grid grid = MapUtils.getGrid(144, 95, grids);
				target.setPosition(grid.getCenter());
			}
		}else if("&showmapthreadid".equalsIgnoreCase(strs[0])){
			log.error("NowThread-->" + Thread.currentThread().getId());
		}else if("&showmapthreadinfo".equalsIgnoreCase(strs[0])){
			int threadid = Integer.parseInt(strs[1]);
			Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
			Thread[] threads = map.keySet().toArray(new Thread[0]);
			for (int i = 0; i < threads.length; i++) {
				if(threads[i]!=null && threads[i].getId()==threadid){
					if(threads[i] instanceof ServerThread){
						log.error("findthread" + "-->" + threads[i].getId() + "-->" + threads[i].getName());
					}
				}
			}
		
		}else if("&showmaps".equalsIgnoreCase(strs[0])){
//			Thread[] threads = new Thread[4000];
//			Thread.enumerate(threads);
			MServer[] servers = WServer.getInstance().getMServers();
			for (int i = 0; i < servers.length; i++) {
				if(servers[i]!=null){
					log.error(servers[i].getName() + "-->" + servers[i].getMainThread().getId() + "-->" + servers[i].getMainThread().getName());
				}
			}
		
		}else if("&unloadmap".equalsIgnoreCase(strs[0])){
			if(strs.length > 1){
				int threadid = Integer.parseInt(strs[1]);
				Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
				Thread[] threads = map.keySet().toArray(new Thread[0]);
				for (int i = 0; i < threads.length; i++) {
					if(threads[i]!=null && threads[i].getId()==threadid){
						if(threads[i] instanceof ServerThread){
							((ServerThread)threads[i]).stop(true);
						}
					}
				}
			}else{
				com.game.map.structs.Map map = ManagerPool.mapManager.getMap(player);
				MServer server = WServer.getInstance().getMServer(map.getLineId(), (int)map.getId());
				//玩家列表
				List<Player> players = new ArrayList<Player>();
				players.addAll(map.getPlayers().values());
				//复活玩家列表
				while (players.size() > 0) {
					Player player2 = players.remove(0);
					
					//切换地图
					ManagerPool.mapManager.changeMap(player2, 3, (int)map.getId(), map.getLineId(), player2.getPosition(), this.getClass().getName() + ".gm 4");
				}
				
				server.stop(true);
			}
		}else if("&showmonsters".equalsIgnoreCase(strs[0])){
			com.game.map.structs.Map map = ManagerPool.mapManager.getMap(player);
			//战斗人员集合
//			List<Fighter> fighters = new ArrayList<Fighter>();
			log.error("Map-->" + map.getLineId() + "-->" + map.getMapModelid() + "-->" + map);
			//获得技能半径以内区域
			int[] rounds = ManagerPool.mapManager.getRoundAreas(player.getPosition(), map, Integer.parseInt(strs[1]));
			for (int i = 0; i < rounds.length; i++) {
				Area area = map.getAreas().get(rounds[i]);
//				System.out.println(area + "-->" + area.getMonsters().size());
				Iterator<Monster> iter = area.getMonsters().values().iterator();
				while (iter.hasNext()) {
					Monster monster = (Monster) iter.next();
					if(monster.getPosition().getX() > 4000 || monster.getPosition().getY() > 4000){
						log.error(monster.getId() + "-->" + monster + "-->" + monster.getModelId());
						iter.remove();
					}
				}
				
				Iterator<Pet> iter2 = area.getPets().iterator();
				while (iter2.hasNext()) {
					Pet pet = (Pet) iter2.next();
					if(pet.getPosition().getX() > 4000 || pet.getPosition().getY() > 4000){
						log.error(pet.getId() + "-->" + pet + "-->" + pet.getModelId());
						iter2.remove();
					}
				}
			}
			
		}else if("&showmonstersbymodel".equalsIgnoreCase(strs[0])){
			com.game.map.structs.Map map = ManagerPool.mapManager.getMap(player);
			
			int monsterModelId = Integer.parseInt(strs[1]);
			//遍历怪物列表
			Iterator<Monster> iter = map.getRevives().values().iterator();
			while (iter.hasNext()) {
				Monster monster = (Monster) iter.next();

				if(monster.getModelId()==monsterModelId)
					log.error("monster " + monster.getModelId() + " values " + JSONserializable.toString(monster));
			}
			
		}else if("&showmonsterinfo".equalsIgnoreCase(strs[0])){
			//获得怪物
			Monster monster = null;
			long monsterId = Long.parseLong(strs[1]);
			if(monsterId > 0){
				monster = ManagerPool.monsterManager.getMonster(player.getServerId(), player.getLine(), player.getMap(), monsterId);
				if(monster!=null){
					log.error("Monster:" + monster.getId() + "-->" + monster.getLine() + "[" + player.getLine() + "]" + monster.getMap() + "[" + player.getMap() + "]"  + "-->" + monster + "-->" + monster.getHp());
				}else{
					log.error("怪物不存在" + monsterId);
				}
			}
		}else if("&setplayeruserid".equalsIgnoreCase(strs[0])){
			//获得怪物
			Player player2 = ManagerPool.playerManager.getPlayer(Long.parseLong(strs[1]));
			player2.setUserId(strs[2]);
			
		}else if("&showmonstermodel".equalsIgnoreCase(strs[0])){
			//获得怪物
			int monsterModelId = Integer.parseInt(strs[1]);
			if(monsterModelId > 0){
				List<Q_scene_monsterBean> mons = ManagerPool.dataManager.q_scene_monsterContainer.getList();
				for (int i = 0; i < mons.size(); i++) {
					if(mons.get(i).getQ_monster_model()==monsterModelId)
					log.error("scene " + mons.get(i).getQ_mapid() + " monster " + mons.get(i).getQ_monster_model() + "[" + mons.get(i).getQ_x() + "," + mons.get(i).getQ_y() + "]");
				}
			}
		}else if("&refreshmonstermodel".equalsIgnoreCase(strs[0])){
			com.game.map.structs.Map map = ManagerPool.mapManager.getMap(player);
			//获得怪物
			int monsterModelId = Integer.parseInt(strs[1]);
			if(monsterModelId > 0){
				List<Q_scene_monsterBean> mons = ManagerPool.dataManager.q_scene_monsterContainer.getList();
				for (int i = 0; i < mons.size(); i++) {
					if(mons.get(i).getQ_monster_model()==monsterModelId && mons.get(i).getQ_mapid()==map.getMapModelid()){
						Grid grid = MapUtils.getGrid(mons.get(i).getQ_x(), mons.get(i).getQ_y(), map.getMapModelid());
						Monster createMonster = ManagerPool.monsterManager.createMonster(mons.get(i).getQ_monster_model(), map.getServerId(), map.getLineId(), (int)map.getId(), grid.getCenter());
						createMonster.setDistributeId(mons.get(i).getQ_id());
						createMonster.setDistributeType(1);
						ManagerPool.mapManager.enterMap(createMonster);
					}
				}
			}
		}else if("&removemonstermodel".equalsIgnoreCase(strs[0])){
			com.game.map.structs.Map map = ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(), Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
			//获得怪物
			int monsterModelId = Integer.parseInt(strs[3]);
			int num = 0;
			if(monsterModelId > 0){
				Monster[] monsters = map.getMonsters().values().toArray(new Monster[0]);
				for (Monster monster : monsters) {
					if(monster.getModelId() == monsterModelId){
						ManagerPool.monsterManager.removeMonster(monster);
						num++;
					}
				}
			}
			log.error("移除怪物：" + num);
		}else if("&testmaprevive".equalsIgnoreCase(strs[0])){
			com.game.map.structs.Map map = ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(), Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
			//获得怪物
			//遍历怪物列表
			Iterator<Monster> iter = map.getRevives().values().iterator();
			while (iter.hasNext()) {
				Monster monster = (Monster) iter.next();

				//获得怪物所在区域
				Area area = map.getAreas().get(ManagerPool.mapManager.getAreaId(monster.getPosition()));
				//地图上添加怪物
				if(area==null){
					log.error("monster " + monster.getModelId() + " pos " + monster.getBirthPos());
					iter.remove();
				}

			}
		}else if("&showmonstermodelinmap".equalsIgnoreCase(strs[0])){
			com.game.map.structs.Map map = ManagerPool.mapManager.getMap(WServer.getInstance().getServerId(), Integer.parseInt(strs[1]), Integer.parseInt(strs[2]));
			//获得怪物
			int num = 0;
			Iterator<Monster> iter = map.getMonsters().values().iterator();
			while (iter.hasNext()) {
				Monster monster = (Monster) iter.next();
				log.error("monster " + monster.getId() + " monstermodel " + monster.getModelId());
				num++;
			}
			
			log.error("共有怪物：" + num);
		}else if("&showplayerinfo".equalsIgnoreCase(strs[0])){
			//获得怪物
			Player player2 = ManagerPool.playerManager.getPlayer(Long.parseLong(strs[1]));
			log.error(JSONserializable.toString(player2));
		}else if("&showplayerinfo".equalsIgnoreCase(strs[0])){
			//获得怪物
			Player player2 = ManagerPool.playerManager.getPlayer(Long.parseLong(strs[1]));
			log.error(JSONserializable.toString(player2));
		}else if("&shownpctolplayer".equalsIgnoreCase(strs[0])){
			//获得怪物
			Player player2 = ManagerPool.playerManager.getPlayer(Long.parseLong(strs[1]));
			if(player2!=null){
				player2.getHideSet().remove(String.valueOf(strs[2]));
				player2.getShowSet().add(String.valueOf(strs[2]));
			}
		}else if("&setplayermap".equalsIgnoreCase(strs[0])){
			//获得怪物
			Player player2 = ManagerPool.playerManager.getPlayer(Long.parseLong(strs[1]));
			player2.setMap(Integer.parseInt(strs[2]));
		}else if("&insertplayers".equalsIgnoreCase(strs[0])){
			try{
				//获取玩家
				MemoryCache<Long, Player> players = ManagerPool.playerManager.getPlayers();
				Field field = players.getClass().getDeclaredField("cache");
	
				field.setAccessible(true);
				@SuppressWarnings("unchecked")
				LRULinkedHashMap<Long, Player> playermap = (LRULinkedHashMap<Long, Player>)field.get(players);
				Player[] playerArray = playermap.values().toArray(new Player[0]);
				log.error("插入角色数量:" + playerArray.length);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "插入角色数量"+playerArray.length);
				for (Player player2 : playerArray) {
					try{
						ManagerPool.playerManager.insertPlayer(player2);
						log.error("插入角色["+player2.getUserId()+"]["+player2.getName()+"]");
					}catch (Exception e) {
						log.error(e, e);
					}
				}
			}catch (Exception e) {
				log.error(e, e);
			}
			log.error("插入角色完成");
		}else if("&buyitem".equalsIgnoreCase(strs[0])){
			try{
				ManagerPool.shopManager.buyItem(player, 0, Integer.parseInt(strs[1]), Integer.parseInt(strs[2]), Integer.parseInt(strs[3]),0,0,0);
			}catch (Exception e) {
				log.error(e, e);
			}
		}else if("&resetlines".equalsIgnoreCase(strs[0])){
			List<Q_mapBean> mapbeans = ManagerPool.dataManager.q_mapContainer.getList();
			try{
				Field field = MapManager.getInstance().getClass().getDeclaredField("mapLines");
				@SuppressWarnings("unchecked")
				ConcurrentHashMap<Integer, Vector<Integer>> maplines = (ConcurrentHashMap<Integer, Vector<Integer>>)field.get(MapManager.getInstance());
				for (int i = 0; i < mapbeans.size(); i++) {
					Q_mapBean bean = mapbeans.get(i);
					Vector<Integer> lines=maplines.get(bean.getQ_map_id());
					if(lines==null){
						continue;
					}
					String[] maxlines = bean.getQ_map_lines().split(Symbol.SHUXIAN_REG);
					Iterator<Integer> iter = lines.iterator();
					while (iter.hasNext()) {
						Integer line = (Integer) iter.next();
						boolean finded = false;
						for (int k = 0; k < maxlines.length; k++) {
							if(line==Integer.parseInt(maxlines[k])){
								finded = true;
								break;
							}
						}
						if(!finded) iter.remove();
					}
				}
			}catch (Exception e) {
				log.error(e, e);
			}
			
		}else if("&setserverstarttime".equalsIgnoreCase(strs[0])){
			try{
				GameConfig config = WServer.getGameConfig();
				Field field = config.getClass().getDeclaredField("serverTimes");
				
				field.setAccessible(true);
				@SuppressWarnings("unchecked")
				ConcurrentHashMap<Integer, Date> servermap = (ConcurrentHashMap<Integer, Date>)field.get(config);
				
				int server =Integer.parseInt(strs[1]);
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date date = format.parse(strs[2] + " " + strs[3]);
				servermap.put(server, date);
			}catch (Exception e) {
				log.error(e, e);
			}
		}else if(strs[0].equalsIgnoreCase("&initWords")){
			log.error("进入 &initWords.");
			try {
				Method method = WordFilter.class.getDeclaredMethod("initWords");
				boolean before=method.isAccessible();
				method.setAccessible(true);
				method.invoke(WordFilter.getInstance());
				method.setAccessible(before);
				log.error("运行成功");
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			log.error("运行结束");
		}else if("&removeplayerfightstate".equalsIgnoreCase(strs[0])){
			//获得怪物
			Player player2 = ManagerPool.playerManager.getPlayer(Long.parseLong(strs[1]));
			player2.setState(PlayerState.NOTHING);
		}else if("&showplayersinmap".equalsIgnoreCase(strs[0])){
			com.game.map.structs.Map map = ManagerPool.mapManager.getMap(player);
			Iterator<Player> iter = map.getPlayers().values().iterator();
			while (iter.hasNext()) {
				Player player2 = (Player) iter.next();
				System.out.println("玩家信息：" + player2.getId() + "-->" + player2.getName());
			}
		}else if("&setcountry".equalsIgnoreCase(strs[0])){
			KingCity kingCity = ManagerPool.countryManager.getKingcity();
			ManagerPool.countryManager.addking(8251344980077718L,"[29区]尹康健");
			kingCity.setHoldplayername("[29区]尹康健");
			kingCity.setHoldplayerid(8251344980077718L);
			kingCity.setGuildid(5637469404263411119L);
			kingCity.setGuildname("[29区]恋站丶天下");
			ManagerPool.countryManager.savekingcity(kingCity);
			MessageUtil.notify_player(player, Notifys.NORMAL, "执行成功");
		}else if("&setcountryname".equalsIgnoreCase(strs[0])){
			KingCity kingCity = ManagerPool.countryManager.getKingcity();
			kingCity.setHoldplayername("[1区]高仁");
			kingCity.setHoldplayerid(369853264741336L);
			ManagerPool.countryManager.savekingcity(kingCity);
			MessageUtil.notify_player(player, Notifys.NORMAL, "执行成功");
		} else if("&insertsingleplayer".equalsIgnoreCase(strs[0])){
			try{
				//获取玩家
				MemoryCache<Long, Player> players = ManagerPool.playerManager.getPlayers();
				Field field = players.getClass().getDeclaredField("cache");
	
				field.setAccessible(true);
				@SuppressWarnings("unchecked")
				LRULinkedHashMap<Long, Player> playermap = (LRULinkedHashMap<Long, Player>)field.get(players);
				Player[] playerArray = playermap.values().toArray(new Player[0]);
				
				for (Player player2 : playerArray) {
					try{
						if(player2.getName().equals(strs[1])){
							ManagerPool.playerManager.insertPlayer(player2);
							MessageUtil.notify_player(player, Notifys.SUCCESS, "插入角色成功");
							break;
						}
					}catch (Exception e) {
						log.error(e, e);
					}
				}
			}catch (Exception e) {
				log.error(e, e);
			}
		} else if("&removezonenum".equalsIgnoreCase(strs[0])){	//清理指定玩家的 副本次数
			Player xplayer = ManagerPool.playerManager.getOnLinePlayer(Long.valueOf(strs[1]));
			if (xplayer != null) {
				List<Q_clone_activityBean> data = ManagerPool.dataManager.q_clone_activityContainer.getList();
				for (Q_clone_activityBean q_clone_activityBean : data) {
					if (q_clone_activityBean.getQ_zone_type() ==1) {
						ManagerPool.countManager.removeCount(xplayer, CountTypes.ZONE_MANUAL, q_clone_activityBean.getQ_id()+"");
						ManagerPool.countManager.removeCount(xplayer, CountTypes.ZONE_AUTO, q_clone_activityBean.getQ_id()+"");
					}
				}
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "{1}的副本次数已清除完成",xplayer.getName());
			}else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "玩家不在线");
			}
			
		} else if("&clearzonenum".equalsIgnoreCase(strs[0])){	//清理指定玩家的 指定多人副本次数
			Player xplayer = ManagerPool.playerManager.getOnLinePlayer(Long.valueOf(strs[1]));
			if (xplayer != null) {
				int zid = Integer.valueOf(strs[2]);
				Q_clone_activityBean data = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zid);
				if (data != null) {
					ManagerPool.countManager.removeCount(xplayer, CountTypes.ZONE_MANUAL, data.getQ_id()+"");
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "{1}的副本次数已清除完成",xplayer.getName());
				}else {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "{1}副本不存在",zid+"");
				}
			}else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "玩家不在线");
			}
			
		} else if("&sendmail".equalsIgnoreCase(strs[0])){
			//&sendmail roleid rolename title content id_num_bind_losttime&id_num_bind_losttime  //losttime 单位分钟
			long roleid = Long.valueOf(strs[1]);
			String rolename = strs[2];
			String title = strs[3];
			String content = strs[4];
			String items = "";
			List<Item> createitems = new ArrayList<Item>();
			if(strs.length>5){
				items = strs[5];
				String[] splititems = items.split("&");
				for(String i: splititems){
					String[] paras = i.split("_");
					if(paras.length!=4){ MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "发送邮件["+rolename+"]失败物品参数错误|"+items); return; }
					int itemid = Integer.parseInt(paras[0]);
					int num = Integer.parseInt(paras[1]);
					boolean bind = Integer.parseInt(paras[2])>0?true:false;
					long losttime = Long.valueOf(paras[3])==0?0: System.currentTimeMillis()+Long.valueOf(paras[3])*60*1000;
					if(itemid==-1){
						createitems.add(Item.createMoney(num));
					}else if(itemid==-2){
						return;  //不支持元宝
					}else if(itemid==-3){
						createitems.add(Item.createZhenQi(num));
					}else if(itemid==-4){
						createitems.add(Item.createExp(num));
					}else if(itemid==-5){
						createitems.add(Item.createBindGold(num));
					}else if(itemid==-6){ //战魂
						createitems.add(Item.createFightSpirit(num));
					}else if(itemid==-7){ //军功
						createitems.add(Item.createRank(num));
					}else{
						createitems.addAll(Item.createItems(itemid, num, bind, losttime));
					}
				}
			}
			if(MailServerManager.getInstance().sendSystemMail(roleid, rolename, title, content, (byte)1, 0, createitems)){
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "发送邮件["+rolename+"]成功|"+items);
			}else{
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "发送邮件["+rolename+"]sendSystemMail方法失败|"+items);
			}
		}else if ("&horselevel".equalsIgnoreCase(strs[0])) {
			Player xplayer = ManagerPool.playerManager.getOnLinePlayer(Long.valueOf(strs[1]));
			if (xplayer != null) {
				int level = Integer.valueOf(strs[2]);
				if (level <= 10) {
					ManagerPool.horseManager.testHorse(xplayer,level );
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "坐骑阶数已调整为{1}",strs[2]);
				}else {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "请输入1-10级范围",xplayer.getName());
				}
			}else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "您要操作的玩家不在线");
			}
		}else if("&deltopmap".equalsIgnoreCase(strs[0])){
			ScriptsUtils.callWorld(ScriptEnum.BACKEND, "deleteTopMap",strs[1]);
		}else if("&worldreload".equalsIgnoreCase(strs[0])){
			ScriptsUtils.callWorld(ScriptEnum.CARDPARSE, "worldreload", Long.toString(player.getId()), strs[1]);
		}else if("&showplayerpk".equalsIgnoreCase(strs[0])){
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "玩家PK状态为：" + player.getPkState());
		}else if("&testsourcecode".equalsIgnoreCase(strs[0])){
			ScriptsUtils.callWorld(ScriptEnum.CARDPARSE, "testsourcecode", Long.toString(player.getId()), strs[1], strs[2]);
		}else if("&delotherpet".equalsIgnoreCase(strs[0])){
			if(strs.length>2){
				long roleid = Long.valueOf(strs[1]);
				int petmodelid = Integer.parseInt(strs[2]);
				Player other = PlayerManager.getInstance().getPlayer(roleid);
				if(other!=null){
					if(other.getPetList().size()>0){
						Iterator<Pet> it = other.getPetList().iterator();
						while(it.hasNext()){
							Pet pet = it.next();
							if(pet.getModelId()==petmodelid){
								pet.setHp(pet.getMaxHp());
								log.error("角色[" + player.getId() + "]美人[" + pet.getId() + "]操作[gm delotherpet]");
								PetOptManager.getInstance().hidePet(player, pet.getId());
								PetInfoManager.getInstance().sendPetInfo(player);
								it.remove();
							}
						}
					}
				}
			}
		}else if("&migong".equalsIgnoreCase(strs[0])){
			if (strs[1].equals("1")) {
				ScriptsUtils.call(4007, "sysactMaze");
			}else {
				ScriptsUtils.call(4007, "sysOffMaze");
			}			
		}else if("&testbackendscript".equalsIgnoreCase(strs[0])){
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "如果你看到了这条公告 说明后台执行 &script 13 加载GM命令脚本成功！");
		}else if("&saveallguild".equalsIgnoreCase(strs[0])){
			ScriptsUtils.callWorld(ScriptEnum.BACKEND, "saveallguild",Long.toString(player.getId()));
		}else if("&loadserverparam".equalsIgnoreCase(strs[0])){
			ScriptsUtils.callWorld(ScriptEnum.BACKEND, "loadserverparam",Long.toString(player.getId()));
		}else if("&inspectplayergold".equalsIgnoreCase(strs[0])){
			if(strs.length>1){
				long roleid = Long.valueOf(strs[1]);
				Player target = PlayerManager.getInstance().getPlayer(roleid);
				if(target!=null){
					Gold gold = target.getGold();
					if(gold!=null){
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, target.getName()+"身上有"+gold.getGold()+"元宝");
					}else{
						MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, target.getName()+"身上没元宝");
					}
				}else{
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "没有玩家"+roleid);
				}
				
			}
		}else if("&everyDay0Clock".equalsIgnoreCase(strs[0])){
			ScriptsUtils.call(ScriptEnum.BASEACTIVITIES, "everyDay0Clock",Long.toString(player.getId()));
		}else if("&ADDWAGETIME".equalsIgnoreCase(strs[0])){
			Player xplayer = ManagerPool.playerManager.getOnLinePlayer(Long.valueOf(strs[1]));
			if (xplayer != null) {
				List<Wage> wagelist = xplayer.getWagelist();
				int time = Integer.valueOf(strs[2]);
				if (wagelist.size() >= 2) {
					Wage sdata = wagelist.get(1);
					sdata.setCumulativetime(sdata.getCumulativetime() + time);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "操作完成");
				}else if (wagelist.size() ==1) {
					Wage sdata = wagelist.get(0);
					sdata.setCumulativetime(sdata.getCumulativetime() + time);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "操作完成");
				}else {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "工资数据不存在，不能修改");
				}
			}else {
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "您要操作的玩家不在线");
			}
		}else if("&runhttp".equalsIgnoreCase(strs[0])){
			ScriptsUtils.callWorld(ScriptEnum.CARDPARSE, "runhttp",Long.toString(player.getId()));
		}else if("&reloadplayer".equalsIgnoreCase(strs[0])){
			if(strs.length>1){
				long roleid = Long.valueOf(strs[1]);
				Player target = ManagerPool.playerManager.getPlayer(roleid);
				
				if(target!=null){
					ManagerPool.playerManager.quit(target);
					ManagerPool.playerManager.getPlayers().remove(roleid);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "重加载 "+target.getName()+" 成功");
				}else{
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "roleid="+roleid+"的玩家没找到");
				}
			}
		}else if("&loadkingcity".equalsIgnoreCase(strs[0])){	//重读王城帮派
			ServerParamUtil.loadServerParam(WServer.getInstance().getServerId());
			int countryid = WServer.getGameConfig().getCountryByServer(WServer.getInstance().getServerId());
			String dataString=ServerParamUtil.getImportantParamMap().get(ServerParamUtil.KINGCITYWAR+countryid);
			KingCity jskingcity = JSON.parseObject(dataString, KingCity.class);
			ManagerPool.countryManager.setKingcity(jskingcity);
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "重读王城完成");
		}else if("&ClearDAN".equalsIgnoreCase(strs[0])){	//清理经验丹次数
			player.getActivitiesReward().put("DANYAO_ADDEXP","0");
			player.getActivitiesReward().put("DANYAO_ADDZHENQI","0");
			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "清理完成");
		}else if("&showplayerbuffs".equalsIgnoreCase(strs[0])){
			if(strs.length>1){
				long roleid = Long.valueOf(strs[1]);
				Player target = PlayerManager.getInstance().getPlayer(roleid);
				if(target!=null){
					Buff[] buffs = target.getBuffs().toArray(new Buff[0]);
					log.error(JSONserializable.toString(buffs));
				}else{
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "没有玩家"+roleid);
				}
				
			}
		}else if("&setsignnum".equalsIgnoreCase(strs[0])){
			if(strs.length>2){
				long roleid = Long.valueOf(strs[1]);
				int signnum = Integer.parseInt(strs[2]);
				Player target = PlayerManager.getInstance().getPlayer(roleid);
				if(target!=null){
					target.setSignsum(signnum);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "设置"+target.getName()+" 签到次数="+signnum+"成功");
				}else{
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "没有玩家"+roleid);
				}
			}
		}else if("&setparam".equalsIgnoreCase(strs[0])){
//			String type = strs[1];
//			if(type.equals("999")){ Global.HEART_WEB = strs[2]; } //服务器心跳地址字符串
//			else if(type.equals("998")){ Global.HEART_PARA = strs[2]; } //服务器心跳参数字符串
//			else if(type.equals("1000")){ ChatManager.AUTOPROHIBIT_LEVEL = Integer.parseInt(strs[2]); }//触发自动禁言最高等级
//			else if(type.equals("1001")){ ChatManager.AUTOPROHIBIT_LENGTH= Integer.parseInt(strs[2]); }//触发自动禁言聊天内容长度
//			else if(type.equals("1002")){ ChatManager.AUTOPROHIBIT_TIME = Integer.parseInt(strs[2]); }//自动禁言记录时长
//			else if(type.equals("1003")){ ChatManager.AUTOPROHIBIT_PROHIBITTIME = Integer.parseInt(strs[2]); }//禁言时长
//			else if(type.equals("1004")){ ChatManager.AUTOPROHIBIT_COUNT = Integer.parseInt(strs[2]); }//自动禁言相似重复次数
//			else if(type.equals("1005")){ ChatManager.AUTOPROHIBIT_SEMBLANCE = Integer.parseInt(strs[2]); }//自动禁言相似度
//			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "修改参数"+type+"="+strs[2]);
		}else if("&kicksence".equalsIgnoreCase(strs[0])){			
			com.game.map.structs.Map map = MapManager.getInstance().getMap(player.getServerId(), player.getLine(), player.getMap());
			int time=Integer.parseInt(strs[1]);
			int maxlevel=Integer.parseInt(strs[2]);			
			List<Area> round = MapManager.getInstance().getRound(map, MapUtils.buildPosition(102, 133));
			if (round != null) {
				for (Area area : round) {
					HashSet<Player> players = area.getPlayers();
					if(players==null||players.size()<=0){
						continue;
					}
					for (Player othPlayer : players) {
						if(othPlayer.getCurrentMainTasks().size()<=0){
							continue;
						}
						if(othPlayer.getCurrentMainTasks().get(0).getModelid()>TaskManager.CREATEPLAYERDEFAULTTASK){
							continue;
						}
						if(othPlayer.getLevel()>=maxlevel){
							continue;
						}
						if(System.currentTimeMillis()-player.getLoginTime()<=time*60*1000){
							continue;
						}
						GmCommandToWorldMessage wmsg = new GmCommandToWorldMessage();
						wmsg.setPlayerId(player.getId());
						wmsg.setCommand(command);
						MessageUtil.send_to_world(wmsg);
						MessageUtil.notify_All_player(Notifys.SUCCESS, "踢人成功");
					}
				}
			}
		}else if("&CLEARCOLLECT".equalsIgnoreCase(strs[0])){
			
			boolean issuccess = false;
			int docount = 0;
			while (!issuccess && docount <= 100) {
				docount++;
				try {
					Thread.sleep(10);
					// 获取玩家
					MemoryCache<Long, Player> players = ManagerPool.playerManager.getPlayers();
					Field field = players.getClass().getDeclaredField("cache");
					field.setAccessible(true);
					@SuppressWarnings("unchecked")
					LRULinkedHashMap<Long, Player> playermap = (LRULinkedHashMap<Long, Player>) field.get(players);
					Collection<Player> values = playermap.values();

					List<Player> rolelist = new ArrayList<Player>();
					rolelist.addAll(values);
					MessageUtil.notify_player(player, Notifys.SUCCESS, "开始清理" + rolelist.size());
					int count = 0;
					int error = 0;
					for (Player role : rolelist) {

						try {
							if (PlayerManager.getInstance().getOnLinePlayer(role.getId()) != null) {
								continue;
							}
							count++;
							// MessageUtil.notify_player(player,
							// Notifys.SUCCESS,
							// "清理玩家"+player.getName());''
							Collect collect = role.getCollect();
							Set<String> keySet = collect.getInfos().keySet();
							List<String> aaa = new ArrayList<String>();
							aaa.addAll(keySet);
							// String keys = "";
							// for (String string : aaa) {
							// keys += string + ";";
							// }
							String keys = "";
							for (String key : aaa) {
								try {
									if (StringUtil.isNumeric(key)) {
										CollectItem item = collect.getInfos().get(key);
										keys = item.getModelId() + "," + JSON.toJSONString(item.getCollectCount()) + ";";
									}
								} catch (Exception e) {
									log.error(e, e);
								}
							}
							log.error("玩家名字：" + role.getName() + "-->" + keys);

							log.error("清理玩家" + player.getName() + " " + keySet.size());
							boolean tag = false;
							int errorcount = 0;
							for (String key : aaa) {
								errorcount++;
								try {
									if (!StringUtil.isNumeric(key)) {
										CollectItem remove = collect.getInfos().remove(key);
										if (!collect.getInfos().containsKey(remove.getModelId())) {
											collect.getInfos().put(String.valueOf(remove.getModelId()), remove);
										}
										tag = true;
									}
								} catch (Exception e) {
									log.error(e, e);
								}
							}
							if (tag) {
								error++;
							}
							log.error("清理玩家" + player.getName() + "完成" + errorcount);
							// }

						} catch (Exception e) {
							log.error(e, e);
						}
						
					}
					issuccess=true;
					log.error("执行清理成功检查个数" + count + "修正错误玩家数" + error);
				} catch (Exception e) {
					log.error(e, e);
				}
			}
		}else if("&inspectupdatelogtag".equalsIgnoreCase(strs[0])){
			if(strs.length>1){
				String roleid = strs[1];
				Player target = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
				if(target!=null){
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,  target.getName()+" "+(target.isUpdatelogtag()?"true":"false"));
				}else{
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "角色["+roleid+"]不在线");
				}
			}
		}else if("&clearhorseweapon".equalsIgnoreCase(strs[0])){
			player.getHorseweaponlist().clear();
		}else if("&uphorseweapon".equalsIgnoreCase(strs[0])){
			if(strs.length>1){
				byte auto = Byte.parseByte(strs[1]);
				int count = 1;
				if(strs.length>2){
					count = Integer.parseInt(strs[2]);
					count = count>200? 200:count;
					int index=0;
					while(index<=200){
						HorseWeaponManager.getInstance().levelupHorseWeapon(player, auto);
						index++;
					}
				}
			}
		}else if("&setpetht".equalsIgnoreCase(strs[0])){
			if(strs.length>2){
				int modelid = Integer.parseInt(strs[1]);
				int count = Integer.parseInt(strs[2]);
				Pet p = PetInfoManager.getInstance().getPetByModelId(player, modelid);
				if(p!=null){
					p.setHtcount(count);
				}
			}
		} else if("&inspectcollectgarbage".equalsIgnoreCase(strs[0])){
			if(strs.length<2) return;
			if(!"confirm".contentEquals(strs[1])) return; //保险机制
			try{
				List<String> roleids = new ArrayList<String>();
				SqlSessionFactory sqlMapper = DBServer.getInstance().getSqlMapper();
				SqlSession session = sqlMapper.openSession();
				try {
					Connection conn = session.getConnection();
					PreparedStatement pstmt = conn.prepareStatement("SELECT roleid FROM role");
					
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()){
						roleids.add(rs.getString("roleid"));
					}
					rs.close();
				} catch (Exception e) {
					log.error(e, e);
				} finally {
					session.close();
				}
				int inspectplayer = roleids.size(); // 角色总数
				int garbageplayer = 0; // 有垃圾对象的角色数
				int totalgarbage = 0;  // 垃圾对象总数
				int count = 0 ;        // 检查次数
				RoleDao dao = new RoleDao();
				for(String roleid: roleids){
					//获取玩家
					Role role = dao.selectById(Long.valueOf(roleid));
					if (role == null) { inspectplayer--; log.info("为空:"+roleid); return; }
					Player loadplayer = (Player) JSONserializable.toObject(VersionUpdateUtil.dateLoad(role.getData()), Player.class);
					try{ //对每个角色的操作
						count++; //检查次数+1
						boolean isgarbage = false;
						Collect collect = loadplayer.getCollect();
						Iterator<String> it = collect.getInfos().keySet().iterator();
						while (it.hasNext()) {
							String key = it.next();
							if(!StringUtil.isNumeric(key)){ //是垃圾对象
								if(!isgarbage) isgarbage=true;
								totalgarbage++; //垃圾对象+1
							}
						}
						
						if(isgarbage) garbageplayer++; //有垃圾的角色数+1
						
						if(count%100==0){
							log.error("garbage:"+totalgarbage+" garbageplayer:"+garbageplayer+" average:"+(totalgarbage/garbageplayer)+" "+count+"/"+inspectplayer);
							MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "garbage:"+totalgarbage+" garbageplayer:"+garbageplayer+" average:"+(totalgarbage/garbageplayer)+" "+count+"/"+inspectplayer);
							Thread.sleep(100); //休息一下
						}
					}catch (Exception e) {
						log.error(e, e);
					}
				}			
				log.error("garbage:"+totalgarbage+" garbageplayer:"+garbageplayer+" average:"+ (garbageplayer==0?0:(totalgarbage/garbageplayer))+" "+count+"/"+inspectplayer);
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "garbage:"+totalgarbage+" garbageplayer:"+garbageplayer+" average:"+(garbageplayer==0?0:(totalgarbage/garbageplayer))+" "+count+"/"+inspectplayer);
			}catch (Exception e) {
				log.error(e, e);
			}
		} else if("&changezhenqi".equalsIgnoreCase(strs[0])){
			if(strs.length>2){
				long roleid = Long.valueOf(strs[1]);
				Player target = PlayerManager.getInstance().getPlayer(roleid);
				if(target!=null){
					int max = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ZHENGQI_MAX.getValue()).getQ_int_value();
					int changezhenqi = Integer.parseInt(strs[2]);
					int prezhenqi = target.getZhenqi();
					int aftzhenqi = prezhenqi+changezhenqi;
					aftzhenqi = aftzhenqi<0? 0: aftzhenqi;
					aftzhenqi = aftzhenqi>max? max: aftzhenqi;
					target.setZhenqi(aftzhenqi);
					//发送真气消息
					ResPlayerZhenqiChangeMessage msg = new ResPlayerZhenqiChangeMessage();
					msg.setCurrentZhenqi(target.getZhenqi());
					MessageUtil.tell_player_message(target, msg);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "修改角色"+target.getName()+" 真气 "+prezhenqi+"=>"+ aftzhenqi +"成功");
					log.error("修改角色"+target.getName()+" 真气 "+prezhenqi+"=>"+ aftzhenqi +"成功");
				}else{
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "角色["+roleid+"]不在线");
				}
			}
		} else if("&setheart".equalsIgnoreCase(strs[0])){
			GmCommandToGateMessage togatemsg = new GmCommandToGateMessage();
			togatemsg.setCommand(command);
			togatemsg.setPlayerId(player.getId());
			MessageUtil.send_to_gate(togatemsg);
		} else if("&inspectres".equalsIgnoreCase(strs[0])){
			try {
				Field field = ResManager.class.getDeclaredField("gameres");
				boolean before=field.isAccessible();
				field.setAccessible(true);
				HashMap<String, String> gameres = (HashMap<String, String>) field.get(ResManager.getInstance());
				log.error(gameres.size());
				for(Entry<String, String> entry: gameres.entrySet()){
					log.error(entry.getKey()+" => "+entry.getValue());
				}
				field.setAccessible(before);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		} else if("&reloadres".equalsIgnoreCase(strs[0])){
			try {
				Field field = ResManager.class.getDeclaredField("gameres");
				boolean before=field.isAccessible();
				field.setAccessible(true);
				HashMap<String, String> pregameres = (HashMap<String, String>) field.get(ResManager.getInstance());
				HashMap<String, String> newgameres = new HashMap<String, String>();
				String propesFile = "server-config/languageres/gameres.properties";
				File file = new File(propesFile);
				Properties prop = new Properties();
				prop.load(new FileInputStream(file));
				Iterator<Entry<Object, Object>> iterator = prop.entrySet().iterator();
				while (iterator.hasNext()) {
					Entry<Object, Object> entry = iterator.next();
					if (entry != null) {
						String Key = (String) entry.getKey();
						String Value = (String) entry.getValue();
						newgameres.put(Key, Value);
					}
				}
				if(newgameres!=null && newgameres.size()>0){
					log.error("new gameres "+ newgameres.size());
					pregameres = newgameres;
					log.error("reload res success");
				}else{
					log.error("reload res fail");
				}
				field.setAccessible(before);
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else if("&showplayerkingcity".equalsIgnoreCase(strs[0])){
			try {
				MessageUtil.notify_player(player, Notifys.SUCCESS, strs[1]);
				long playerid = Long.valueOf(strs[1]);
				Player showplayer = PlayerManager.getInstance().getPlayer(playerid);
				if (showplayer != null) {
					log.error(showplayer.getName() + "==" + showplayer.getGuildId());
					log.error("guildinfo");
					log.error(JSON.toJSONString(showplayer.getGuildInfo()));
					log.error("memberinfo");
					log.error(JSON.toJSONString(showplayer.getMemberInfo()));
					MessageUtil.notify_player(player, Notifys.SUCCESS, "showplayerkingcity成功");
				}
			} catch (Exception e) {
				log.error(e,e);
			}
		} else if("&showkingcity".equalsIgnoreCase(strs[0])){
			try {
				KingData kingData = CountryAwardManager.getInstance().getCurKingData();
				if (kingData != null) {
					log.error(JSON.toJSONString(kingData));
				} else {
					log.error("KINGDATA=NULL=" + CountryAwardManager.getInstance().getKingTerm());
				}
				Iterator<Entry<String, KingData>> iterator = CountryManager.getInstance().getKingcity().getKingdataMap().entrySet().iterator();
				while(iterator.hasNext()) {
					Entry<String, KingData> entry =  iterator.next();
					log.error(entry.getKey() + "=" + JSON.toJSONString(entry.getValue()));
				}
				MessageUtil.notify_player(player, Notifys.SUCCESS, "showkingcity成功");
			} catch (Exception e) {
				log.error(e,e);
			}
		} else if("&loadoneguild".equalsIgnoreCase(strs[0])){
			try {
				ScriptsUtils.callWorld(ScriptEnum.GM_COMMAND, "loadoneguild", String.valueOf(player.getId()), strs[1]);
			} catch (Exception e) {
				log.error(e,e);
			}
		} else if("&setcheckparam".equalsIgnoreCase(strs[0])){
			WServer.checkconfig = new CheckConfigXmlLoader().load("server-config/check-config.xml");
			Global.CHECK_BETWEEN = WServer.checkconfig.getCheckbetween();
			Global.DISTANCE = WServer.checkconfig.getDistance();
			Global.BASE_SPEED = WServer.checkconfig.getBasespeed();
		} else if("&putprison".equalsIgnoreCase(strs[0])){
			if(strs.length>1){
				String roleid = strs[1];
				Player target = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
				if(target!=null){
					target.setHeartCheckTimes(500); //丢进监狱
					if(strs.length>2){
						int time = Integer.parseInt(strs[2]);
						target.setPrisonRemainTime(time);
					}
				}
			}
		} else if("&outprison".equalsIgnoreCase(strs[0])){
			if(strs[1].equals("all")){ //全放出来 遍历地图
				com.game.map.structs.Map prisonmap = MapManager.getInstance().getMap(player.getServerId(), 1, 42122); //取监狱地图
				Iterator<Player> it = prisonmap.getPlayers().values().iterator();
				while(it.hasNext()){
					Player target = it.next();
					if(target!=null){
						if(strs.length>2){
							target.setPrisonTimes(0);
						}
						target.setPrisonRemainTime(0); //蹲监狱剩余时间
					}
				}
			}else{
				String roleid = strs[1];
				Player target = PlayerManager.getInstance().getPlayer(Long.valueOf(roleid));
				if(target!=null){
					target.setPrisonRemainTime(0); //蹲监狱剩余时间
					target.setPrisonEnterTime(0L);
					if(strs.length>2){
						target.setPrisonTimes(0); //
					}
				}
			}
		} 
		else if("&setserverheart".equalsIgnoreCase(strs[0])){
			WServer.serverheartconfig = new ServerHeartConfigXmlLoader().load("server-config/server-heart-config.xml");
			Global.HEART_PARA = WServer.serverheartconfig.getHeart_para();
			Global.HEART_WEB  = WServer.serverheartconfig.getHeart_web();
			log.info("修改服务器心跳地址配置成功 ["+Global.HEART_PARA+"||"+Global.HEART_WEB+"]");
		} else if("&setgateheart".equalsIgnoreCase(strs[0])){
			GmCommandToGateMessage togate = new GmCommandToGateMessage();
			togate.setCommand(command);
			togate.setPlayerId(player.getId());
			MessageUtil.send_to_gate(togate);
		} else if ("&sethorselevel".equalsIgnoreCase(strs[0])) {
			long playerid = Long.valueOf(strs[1]);
			Player showplayer = PlayerManager.getInstance().getPlayer(playerid);
			if (showplayer != null) {
				ManagerPool.horseManager.sethorselevel(showplayer, true);
				MessageUtil.notify_player(player, Notifys.SUCCESS, "同步坐骑等级成功");
			}else {
				MessageUtil.notify_player(player, Notifys.SUCCESS, "找不到玩家");
			}
		} else if("&clearjiaochang".equalsIgnoreCase(strs[0])){
			long playerid = Long.valueOf(strs[1]);
			Player target = PlayerManager.getInstance().getPlayer(playerid);
			if(target!=null){
				ManagerPool.countManager.removeCount(target, CountTypes.JIAOCHANG_NUM, "4003");
				MessageUtil.notify_player(player, Notifys.SUCCESS, target.getName()+"校场次数清除成功");
			}else{
				MessageUtil.notify_player(player, Notifys.SUCCESS, "玩家不在线");
			}
		} else if("&resetplayerskill".equalsIgnoreCase(strs[0])){
			long playerid = Long.valueOf(strs[1]);
			Player target = PlayerManager.getInstance().getPlayer(playerid);
			if(target!=null){
				int totalskill = 0;
				List<Skill> skills = player.getSkills();
				for (Skill skill : skills) {
					totalskill += skill.getSkillLevel();
				}
				target.setTotalSkillLevel(totalskill);
				MessageUtil.notify_player(player, Notifys.SUCCESS, "修改["+target.getName()+"]=>["+totalskill+"]成功");
			}
		} else if("&checkName".equalsIgnoreCase(strs[0])){
			if(ManagerPool.playerManager.checkName(strs[1])){
				MessageUtil.notify_player(player, Notifys.ERROR, "存在重名："+strs[1]);
			}else{
				MessageUtil.notify_player(player, Notifys.SUCCESS, "没有重名："+strs[1]);
			}
		}else if("&lookNames".equalsIgnoreCase(strs[0])){
			try {
				Object object=getFieldValue(ManagerPool.playerManager,"names");
				@SuppressWarnings("unchecked")
				HashSet<String> listaaa= (HashSet<String>)object;
				MessageUtil.notify_player(player, Notifys.SUCCESS, "名字数量："+listaaa.size());
			} catch (SecurityException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
		
				e.printStackTrace();
			} catch (NoSuchFieldException e) {
				
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				
				e.printStackTrace();
			} catch (InstantiationException e) {
			
				e.printStackTrace();
			}
		}else if("&loadNames".equalsIgnoreCase(strs[0])){
			ManagerPool.playerManager.loadNames();
			MessageUtil.notify_player(player, Notifys.SUCCESS, "重载名字列表完成");
		}else if("&ClearKing".equalsIgnoreCase(strs[0])){//清理王城数据和领地战数据
			ManagerPool.guildFlagManager.getTerritorymap().clear();
			ManagerPool.guildFlagManager.saveguildfiag();	//更新到数据库
			KingCity kingCity = new KingCity();
			ManagerPool.countryManager.savekingcity(kingCity);
			ManagerPool.countryManager.setKingcity(kingCity);
			MessageUtil.notify_player(player, Notifys.SUCCESS, "清理完成");
		}else if("&jiaochang".equalsIgnoreCase(strs[0])){//校场
			String infoString = player.getZoneSaveInfoMap().get("校场副本");
			if (infoString != null && !infoString.isEmpty()) {
				JiaoChangInfo jiaoChangInfo = JSON.parseObject(infoString, JiaoChangInfo.class);
				if (jiaoChangInfo != null ) {
					jiaoChangInfo.setCurjifen(Integer.parseInt(strs[1]));
					MessageUtil.notify_player(player, Notifys.SUCCESS, "已经设置为"+strs[1]);
					String inst = JSON.toJSONString(jiaoChangInfo);
					if (inst == null) {
						inst = "";
					}
					player.getZoneSaveInfoMap().put("校场副本", inst);
					return;
				}
			}
			MessageUtil.notify_player(player, Notifys.SUCCESS, "没有积分信息");
		}
		
	}

	public void changeres(List<String> paras){
		log.error("进入 &changeres");
		try {
			Field field = ResManager.class.getDeclaredField("gameres");
			boolean before=field.isAccessible();
			field.setAccessible(true);
			HashMap<String, String> gameres = (HashMap<String, String>) field.get(ResManager.getInstance());
			gameres.put("YYYY年MM月DD日HH时MM分", "yyyy年MM月dd日HH时mm分");
			field.setAccessible(before);
			log.error("运行成功");
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		log.error("运行结束");
	}
	
	public void clearCollect(List<String> paras){
		boolean issuccess = false;
		int docount = 0;
		while (!issuccess && docount <= 100) {
			docount++;
			try {
				Thread.sleep(10);
				// 获取玩家
				MemoryCache<Long, Player> players = ManagerPool.playerManager.getPlayers();
				Field field = players.getClass().getDeclaredField("cache");
				field.setAccessible(true);
				@SuppressWarnings("unchecked")
				LRULinkedHashMap<Long, Player> playermap = (LRULinkedHashMap<Long, Player>) field.get(players);
				Collection<Player> values = playermap.values();
				List<Player> rolelist = new ArrayList<Player>();
				rolelist.addAll(values);
				int count = 0;
				int error = 0;
				for (Player role : rolelist) {

					try {
						if (PlayerManager.getInstance().getOnLinePlayer(role.getId()) != null) {
							continue;
						}
						count++;
						Collect collect = role.getCollect();
						Set<String> keySet = collect.getInfos().keySet();
						List<String> aaa = new ArrayList<String>();
						aaa.addAll(keySet);
						String keys = "";
						for (String key : aaa) {
							try {
								if (StringUtil.isNumeric(key)) {
									CollectItem item = collect.getInfos().get(key);
									keys = item.getModelId() + "," + JSON.toJSONString(item.getCollectCount()) + ";";
								}
							} catch (Exception e) {
								log.error(e, e);
							}
						}
						log.error("玩家名字：" + role.getName() + "-->" + keys);
						
						boolean tag = false;
						for (String key : aaa) {
							try {
								if (!StringUtil.isNumeric(key)) {
									CollectItem remove = collect.getInfos().remove(key);
									if (!collect.getInfos().containsKey(remove.getModelId())) {
										collect.getInfos().put(String.valueOf(remove.getModelId()), remove);
									}
									tag = true;
								}
							} catch (Exception e) {
								log.error(e, e);
							}
						}
						if (tag) {
							error++;
						}
					} catch (Exception e) {
						log.error(e, e);
					}
				}
				issuccess=true;
				log.error("执行清理成功检查个数" + count + "修正错误玩家数" + error);
				HttpUtil.wget("http://221.237.152.208:8081/QMRBackend/r/callback.do?c=tablefixreturn&returnstr="+WServer.getInstance().getServerWeb()+"_"+WServer.getInstance().getServerId());
			} catch (Exception e) {
				log.error(e, e);
			}
		}
	}
	
	
	/**
	 * 获取BEAN中的属性值 包括私有属性 包括静态属性
	 * @param obj
	 * @param fieldName
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public static Object getFieldValue(Object PlayerManager,String fieldName) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException, InstantiationException{
		Class<? extends Object> cls= PlayerManager.getClass();
		Field declaredField = cls.getDeclaredField(fieldName);
		boolean before=declaredField.isAccessible();
		declaredField.setAccessible(true);
		Object object = declaredField.get(PlayerManager);
		declaredField.setAccessible(before);
		return object;
	}
	
}

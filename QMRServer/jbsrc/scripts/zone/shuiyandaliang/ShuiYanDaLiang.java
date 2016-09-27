package scripts.zone.shuiyandaliang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_characterBean;
import com.game.data.bean.Q_clone_activityBean;
import com.game.data.bean.Q_monsterBean;
import com.game.dblog.LogService;
import com.game.fight.structs.Fighter;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterMapScript;
import com.game.map.script.IQuitMapScript;
import com.game.map.structs.Map;
import com.game.monster.script.IMonsterAiScript;
import com.game.monster.script.IMonsterDieScript;
import com.game.monster.structs.Monster;
import com.game.monster.structs.MonsterState;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.script.IPlayerDieScript;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.server.impl.WServer;
import com.game.server.script.IServerEventTimerScript;
import com.game.skill.structs.Skill;
import com.game.structs.Grid;
import com.game.util.TimerUtil;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;
import com.game.zones.bean.ZoneApplyDataInfo;
import com.game.zones.log.ZoneLog;
import com.game.zones.manager.ZonesManager;
import com.game.zones.message.ResZoneApplyDataInfoToClientMessage;
import com.game.zones.message.ResZoneTeamOpenBullToClientMessage;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.script.IZoneEventTimerScript;
import com.game.zones.structs.ZoneContext;
import com.game.zones.timer.ZoneLoopEventTimer;
/**水淹大梁
 * 
 * @author zhangrong
 *  胜负规则  ：最后一波怪物谁先杀完谁赢，或者2方怪物存在数量差距超過60只，或者一方离开地图
 */
public class ShuiYanDaLiang implements ICreateZoneScript,IEnterMapScript ,IPlayerDieScript ,IZoneEventTimerScript,IMonsterDieScript,IMonsterAiScript,IQuitMapScript,IServerEventTimerScript{
	protected static Logger logx = Logger.getLogger(ShuiYanDaLiang.class);
	
	@Override
	public int getId() {
		return 4012;
	}
	
	
	private int[] resmodelid={140,160,170,180,190,200,220,230,240,241,250,270,280,290,310,320,330,350,360,370,410,420,430,450,460,470,480,490,500,510,520,530,550,560,570,590,600,610,620,640,650,660,670};
	
	
	
	private int zonemodelid = 3005;
	
	/**刷怪时间基础值（秒）
	 * 
	 */
	private int  basistime = 40;
	
	/**刷怪数量基础值
	 * 
	 */
	private int  basismonnum = 20;
	
	/**刷怪最大波次
	 * 
	 */
	private int  brushnummax = 20;
	
	/**双方怪物最大相差数量
	 * 
	 */
	private int  differnummax = 60;
	
	/**阵营累计死亡最大次数
	 * 
	 */
	private int dienummax = 12;
	
	
	
	/**比赛结果
	 * 
	 */
	
	private  String S_RESULT = "S_RESULT";
	
	/**
	 * 刷怪次数
	 */
	private  String S_REFRESHNUM = "S_REFRESHNUM";

	
	/**刷怪时间
	 * 
	 */
	private  String S_REFRESHTIME = "S_REFRESHTIME";
	

	/**
	 * 当前杀怪次数
	 */
	private  String S_CURKILLNUM = "S_CURKILLNUM";
	/**
	 * 累计杀怪次数
	 */
	private  String S_ADDUPKILLNUM = "S_ADDUPKILLNUM";
	/**
	 * 累计死亡
	 */
	private  String S_ADDUPDEATH = "S_ADDUPDEATH";
	
	/**
	 * 玩家死亡次数
	 */
	private  String S_PLAYERDEATH = "S_PLAYERDEATH";
	
	/**
	 * 杀怪后提前触发刷怪标记
	 */
	private  String S_UPREFRESH = "S_UPREFRESH";
	
	
	/**本波刷怪数量
	 * 
	 */
	private  String S_MONNUM = "S_MONNUM";

	
	
	/**本波额外刷怪数量
	 * 
	 */
	private  String S_MONNUMADD_A = "S_MONNUMADD_A";
	/**本波额外刷怪数量
	 * 
	 */
	private  String S_MONNUMADD_B = "S_MONNUMADD_B";
	
	

	
	
	/**设置数值
	 * 
	 * @param zonedata
	 * @param key
	 * @param num
	 */
	public void setn(HashMap<String, Object> zonedata,String key,int num ){
		zonedata.put(key, num);
	}
	
	
	/**读取数值
	 * 
	 * @param zonedata
	 * @param key
	 * @return
	 */
	public int getn(HashMap<String, Object> zonedata,String key ){
		if (zonedata.containsKey(key)) {
			int n = (Integer)zonedata.get(key);
			return n;
		}else {
			return 0;
		}
	}
	
	
	
	/**创建副本
	 * 
	 */
	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		if (zonemodelid != zoneId) {
			return null;
		}
		
		Q_clone_activityBean zonedata = ManagerPool.dataManager.q_clone_activityContainer.getMap().get(zoneId);
		HashMap<String, Object> others = new HashMap<String, Object>();
		others.put("time", (int)(System.currentTimeMillis()/1000));	//开始时间
		others.put("zonetype",zonedata.getQ_zone_type());			//副本类型 3
		others.put("teamtype",1);		//队伍类型，0，单人，1组队
		others.put("coordinate", "[[73,97],[119,80]]");	//分组坐标
		List<Integer> mapidlist = JSON.parseArray(zonedata.getQ_mapid(),Integer.class);
		ZoneContext zone =ManagerPool.zonesManager.setZone("水淹大梁",others,mapidlist,zonedata.getQ_id());	//创建副本，返回副本消息

		return zone;
	}




	
	/**进入地图
	 * 
	 */
	@Override
	public void onEnterMap(Player player, Map map) {
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
		if (zone == null) {
			return;
		}

		if(map.getZoneId() > 0 && map.getZoneModelId() == zonemodelid  ){
			ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_FOR_KILLED);
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("zonemodelid", zonemodelid);
			if (getn(zone.getOthers(), "Stop") >= 1) {	//副本已经结束，给玩家提示
				paramMap.put("isend", 1);	//结束标志
			}
			
			ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
			sendMessage.setScriptid(getId());
			sendMessage.setType(1);
			sendMessage.setMessageData(JSON.toJSONString(paramMap));
			MessageUtil.tell_player_message(player, sendMessage);	
			//改变攻击模式为全体
			ManagerPool.playerManager.changePkState(player, 3, 0);
			if( !zone.getOthers().containsKey("initialize")){//初始
				map.setBanusesp(1);//禁止使用体力类药水
				zone.getOthers().put("initialize", 1);
				ZoneLoopEventTimer timer = new ZoneLoopEventTimer(this.getId(), zone.getId(), zonemodelid, new ArrayList<Object>(), 1000);
				TimerUtil.addTimerEvent(timer);
			}else {
				//死亡3次触发
//				if (zone.getOthers().containsKey("S_PLAYERDEATH"+player.getId())) {
//					int num = (Integer)zone.getOthers().get("S_PLAYERDEATH"+player.getId());
//					if (num >= 3) {
//						List<Object> list=new ArrayList<Object>();
//						list.add(player);
//						deathprompts(list);
//					}
//				}
			}
		}
	}

	/**玩家死亡
	 * 
	 */
	@Override
	public void onPlayerDie(Player player, Fighter killer) {
		Map map = ManagerPool.mapManager.getMap(player);
		if (map != null && map.getZoneModelId() == zonemodelid) {
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if (zone != null) {
				HashMap<String, Object> zonedata = zone.getOthers();
				int num = getn(zonedata,S_ADDUPDEATH+player.getGroupmark());
				setn(zonedata,S_ADDUPDEATH+player.getGroupmark(),num +1);
				sendStatusBar(map.getZoneId());
				int pnum = getn(zonedata,S_PLAYERDEATH+player.getId());
				setn(zonedata,S_PLAYERDEATH+player.getId(),pnum +1);
				dieck(map,player);
			}
		}
	}

	
	
	/**玩家死亡分胜负
	 * 
	 * @param map
	 * @param player
	 */
	public void dieck(Map map,Player player){
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
		HashMap<String, Object> zonedata = zone.getOthers();
		int adie = getn(zonedata,S_ADDUPDEATH+"1");
		int bdie = getn(zonedata,S_ADDUPDEATH+"2");

		if (getn(zonedata,S_RESULT) == 0){
			if (adie >= dienummax || bdie >= dienummax ) {
				if (adie >= dienummax) {
					if (player != null) {
						ManagerPool.zonesManager.zoneplayerdie(player);//副本内死亡记录次数（这里是特殊处理，必须提前记录，需要显示在面板上）
					}
					setn(zonedata,S_RESULT,2);	//设置胜利方阵营
					setn(zonedata, "Stop", 1);	//停止标记
					ManagerPool.monsterManager.removeAllMonster(map);
					triggerReward(map,4);	//奖励面板
				}else if (bdie >= dienummax) {
					if (player != null) {
						ManagerPool.zonesManager.zoneplayerdie(player);//副本内死亡记录次数（这里是特殊处理，必须提前记录，需要显示在面板上）
					}
					setn(zonedata,S_RESULT,1);	//设置胜利方阵营
					setn(zonedata, "Stop", 1);	//停止标记
					ManagerPool.monsterManager.removeAllMonster(map);
					triggerReward(map,4);	//奖励面板
				}
			}
		}
	}
	

	/**获取特定等级怪物
	 * 
	 * @param level
	 * @param type == 2 取BOSS 
	 * @return
	 */

	public Q_monsterBean  getMobsBean(int level,int type ){
		int kaishi = 16001;
		int jieshu = 16071;
		if (type == 1) {
			if (level > 120) {
				level = 120;
			}else if (level < 40) {
				level = 40;
			}
		}else if (type == 2) {
			if (level > 120) {
				level = 120;
			}else if (level < 40) {
				level = 40;
			}
		}
		
		if (type ==2) {	//BOSS
			kaishi=16101;	
			jieshu=16171;
		}
		
		for (int i = kaishi; i <= jieshu; i++) {
			Q_monsterBean mondb = ManagerPool.dataManager.q_monsterContainer.getMap().get(i);
			if (mondb != null) {
				if(mondb.getQ_grade() >= level ){
					return mondb;
				}
			}
		}
		return null;
	}
	
	/**刷怪
	 * 
	 */
	@Override
	public void action(long zoneId, int zoneModelId, List<Object> parameters) {
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(zoneId);
		if (zone != null) {
			int ms = (int)(System.currentTimeMillis()/1000);
			HashMap<String, Object> zonedata = zone.getOthers();
			if (getn(zonedata, "Stop") >= 1) {
				return;
			}
			
			MapConfig mapconfig = zone.getConfigs().get(0);
			Map map = ManagerPool.mapManager.getMap(mapconfig.getServerId(), mapconfig.getLineId(), mapconfig.getMapId());
			if (map.getPlayerNumber() == 0) {
				return;
			}
			
			int num = getn(zonedata, S_REFRESHNUM)+1;
			int time = getn(zonedata, S_REFRESHTIME);
			if (time == 0) {
				time = ms + 5;
				setn(zonedata, S_REFRESHTIME, time);	//第一波准备时间5秒
			}
			
			if (getn(zonedata, "time") + 2*60 < ms &&  ms%5 == 0) {//副本创建2分钟后才检测双方是否在线
				offlinecheck(map);
			}
			
			if (ms%10 == 0) {//每10秒补充体力值
				int addsp = 20;
				for (Player mPlayer : map.getPlayers().values()) {
					if (mPlayer.getSp() < mPlayer.getMaxSp()) {
						if (mPlayer.getSp() + addsp > mPlayer.getMaxSp()) {
							mPlayer.setSp(mPlayer.getMaxSp());
						} else {
							mPlayer.setSp(mPlayer.getSp() + addsp);
						}
						ManagerPool.playerManager.onSpChange(mPlayer);
					}
				}
			}
			
			if (time - ms == 2) {
				if (num < brushnummax) {
					MessageUtil.notify_map(map, Notifys.CHAT_BULL, ResManager.getInstance().getString("第[{1}]波怪物3秒后来袭，请做好准备"),""+num);
				}else if (num == brushnummax){
					MessageUtil.notify_map(map, Notifys.CHAT_BULL, ResManager.getInstance().getString("最后一波怪物来袭！率先击杀全部怪物的一方将获得胜利！"),""+num);
				}
			}

			if (ms > time && num <= brushnummax) {//开始设定刷怪
				int[] bjmonnums = getMonsterNum(map,num,1);
				int add = bjmonnums[0] - bjmonnums[1];
				for (Player player : map.getPlayers().values()) {
					if (add > 0) {
						if(player.getGroupmark() == 1){
							MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("对方比我方多杀{1}只怪物，我方增加{2}只怪物，请尽快击杀"), ""+add,""+add);
						}else {
							MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("我方比对方多杀{1}只怪物，将给对方增加{2}只怪物增大压力"), ""+add,""+add);
						}
						
					}else if (add < 0) {//B方留下的怪物多
						int adds = Math.abs(add);
						if(player.getGroupmark() == 2){
							MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("对方比我方多杀{1}只怪物，我方增加{2}只怪物，请尽快击杀"), ""+adds,""+adds);
						}else {
							MessageUtil.notify_player(player, Notifys.CHAT_BULL, ResManager.getInstance().getString("我方比对方多杀{1}只怪物，将给对方增加{2}只怪物增大压力"), ""+adds,""+adds);
						}
					}
				}
				
				int newtime = basistime + (num*2);
				setn(zonedata, S_REFRESHTIME, ms + newtime);	//设置下一波刷怪时间
				setn(zonedata, S_REFRESHNUM, num);	//保存刷怪次数
				
				@SuppressWarnings({ "unchecked" })
				List<Integer> alevellist = (List<Integer>) zone.getOthers().get("alevel");
				@SuppressWarnings("unchecked")
				List<Integer> blevellist = (List<Integer>) zone.getOthers().get("blevel");
				int asumlv = 0;
				int bsumlv = 0;
				for (int lv : alevellist) {
					asumlv = asumlv +lv;
				}
				for (int lv : blevellist) {
					bsumlv = bsumlv +lv;
				}
				int aslv = asumlv/alevellist.size();	//A平均等级
				aslv = aslv+num; //怪物等级增加
				
				int bslv = bsumlv/blevellist.size();	//B平均等级
				bslv = bslv+num; //怪物等级增加
				
				int brush = basismonnum + num*2;//刷怪数量
				if (brush > 40) {
					brush = 40;
				}
				
				setn(zonedata, "A_LEVEL",aslv);
				setn(zonedata, "B_LEVEL",bslv);
				setn(zonedata, S_MONNUM,brush);
				setn(zonedata, S_CURKILLNUM+"1",0);	//当前杀怪清零
				setn(zonedata, S_CURKILLNUM+"2",0);	//当前杀怪清零
				setn(zonedata,S_UPREFRESH,0);//移除 可提前刷怪标记
				int[] monnums = getMonsterNum(map,num,1);
				int additional = monnums[0] - monnums[1];
				if (additional > 0 ) {	//A方留下的怪多
					setn(zonedata, S_MONNUMADD_A,additional);
				}else if (additional < 0) {	//B方留下的怪多
					setn(zonedata, S_MONNUMADD_B,Math.abs(additional));
				}
				
				if (num %5 == 0) {//刷BOSS
					Q_monsterBean abossmondb = getMobsBean(aslv,2);
					Q_monsterBean bbossmondb = getMobsBean(bslv,2);
					List<Grid> gridlista = MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(57, 64, map.getMapModelid()),5*MapUtils.GRID_BORDER , map.getMapModelid());
					List<Grid> gridlistb = MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(85, 52, map.getMapModelid()),5*MapUtils.GRID_BORDER , map.getMapModelid());
					int rnda = RandomUtils.random( gridlista.size());//随机格子
					int rndb = RandomUtils.random( gridlistb.size());//随机格子
					Monster monstera = ManagerPool.monsterManager.createMonster(abossmondb.getQ_id(), map.getServerId(),  map.getLineId(),  (int)map.getId(), gridlista.get(rnda).getCenter());
					Monster monsterb = ManagerPool.monsterManager.createMonster(bbossmondb.getQ_id(), map.getServerId(),  map.getLineId(),  (int)map.getId(), gridlistb.get(rndb).getCenter());
					
					monstera.setName(String.format(ResManager.getInstance().getString("破阵[第%d波]"),num));
					monstera.getParameters().put("group", 1);	
					monstera.setGroupmark(2);//设置怪物阵营
					
					monsterb.setName(String.format(ResManager.getInstance().getString("破阵[第%d波]"),num));
					monsterb.getParameters().put("group", 2);
					monsterb.setGroupmark(1);//设置怪物阵营
					
					ManagerPool.mapManager.enterMap(monstera);
					ManagerPool.mapManager.enterMap(monsterb);
				}
				
				
				
			}else {//---------------------------------分批刷怪------------------------------------------
				if (ms%2 != 0) {
					return;
				}
				num = num - 1;
				int aslv = getn(zonedata, "A_LEVEL");
				int bslv = getn(zonedata, "B_LEVEL");
				
				int monnum = getn(zonedata, S_MONNUM);
				if (monnum > 6 ) {
					monnum = 6;
				}

				int monnumadd_a = getn(zonedata, S_MONNUMADD_A);
				if (monnumadd_a > 6) {
					monnumadd_a = 6;
				}
				int monnumadd_b = getn(zonedata, S_MONNUMADD_B);
				if (monnumadd_b > 6) {
					monnumadd_b = 6;
				}
				
				Q_monsterBean amondb = getMobsBean(aslv,1);
				Q_monsterBean bmondb = getMobsBean(bslv,1);
				if (amondb != null && bmondb != null) {
					int idx = RandomUtils.random(resmodelid.length);	//随机造型
					Q_monsterBean resmondb = ManagerPool.dataManager.q_monsterContainer.getMap().get(resmodelid[idx]);
					//a=左边，b=右边
					List<Grid> gridlista = MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(57, 64, map.getMapModelid()),5*MapUtils.GRID_BORDER , map.getMapModelid());
					List<Grid> gridlistb = MapUtils.getRoundNoBlockAndSwimGrid(MapUtils.getGrid(85, 52, map.getMapModelid()),5*MapUtils.GRID_BORDER , map.getMapModelid());
					
					if (monnumadd_a > 0 ) {	//A方留下的怪多
						for (int j = 0; j < monnumadd_a ; j++) {
							int rnda = RandomUtils.random( gridlista.size());//随机格子
							Monster monstera = ManagerPool.monsterManager.createMonster(amondb.getQ_id(), map.getServerId(),  map.getLineId(),  (int)map.getId(), gridlista.get(rnda).getCenter());
							monstera.setRes(resmondb.getQ_sculpt_resid());
							monstera.setIcon(resmondb.getQ_head_resid());
							monstera.setName(String.format(ResManager.getInstance().getString("增援[第%d波]"),num));
							monstera.getParameters().put("group", 1);	
							monstera.setGroupmark(2);//设置怪物阵营
							ManagerPool.mapManager.enterMap(monstera);
						}
						setn(zonedata, S_MONNUMADD_A, getn(zonedata, S_MONNUMADD_A) - monnumadd_a);
					}
					
					if (monnumadd_b > 0) {	//B方留下的怪多
						for (int j = 0; j < monnumadd_b ; j++) {
							int rndb = RandomUtils.random( gridlistb.size());//随机格子
							Monster monsterb = ManagerPool.monsterManager.createMonster(bmondb.getQ_id(), map.getServerId(),  map.getLineId(),  (int)map.getId(), gridlistb.get(rndb).getCenter());
							monsterb.setRes(resmondb.getQ_sculpt_resid());
							monsterb.setIcon(resmondb.getQ_head_resid());
							monsterb.setName(String.format(ResManager.getInstance().getString("增援[第%d波]"),num));
							monsterb.getParameters().put("group", 2);	
							monsterb.setGroupmark(1);//设置怪物阵营
							ManagerPool.mapManager.enterMap(monsterb);
						}
						setn(zonedata, S_MONNUMADD_B, getn(zonedata, S_MONNUMADD_B) - monnumadd_b);
					}
					
					
					if (monnum > 0) {
						for (int j = 0; j < monnum ; j++) {
							int rnda = RandomUtils.random( gridlista.size());//随机格子
							int rndb = RandomUtils.random( gridlistb.size());//随机格子
							Monster monstera = ManagerPool.monsterManager.createMonster(amondb.getQ_id(), map.getServerId(),  map.getLineId(),  (int)map.getId(), gridlista.get(rnda).getCenter());
							Monster monsterb = ManagerPool.monsterManager.createMonster(bmondb.getQ_id(), map.getServerId(),  map.getLineId(),  (int)map.getId(), gridlistb.get(rndb).getCenter());
							
							monstera.setRes(resmondb.getQ_sculpt_resid());
							monstera.setIcon(resmondb.getQ_head_resid());
							monstera.setName(String.format(ResManager.getInstance().getString("突击[第%d波]"),num));
							monstera.getParameters().put("group", 1);	
							monstera.getParameters().put(S_REFRESHNUM, num);
							monstera.setGroupmark(2);//设置怪物阵营
							
							monsterb.setRes(resmondb.getQ_sculpt_resid());
							monsterb.setIcon(resmondb.getQ_head_resid());
							monsterb.setName(String.format(ResManager.getInstance().getString("突击[第%d波]"),num));
							monsterb.getParameters().put("group", 2);
							monsterb.getParameters().put(S_REFRESHNUM, num);
							monsterb.setGroupmark(1);//设置怪物阵营
							
							ManagerPool.mapManager.enterMap(monstera);
							ManagerPool.mapManager.enterMap(monsterb);
						}
						setn(zonedata, S_MONNUM, getn(zonedata, S_MONNUM) - monnum);
					}
				}
			}
			settarget(map,1);
			settarget(map,2);
			sendStatusBar(zoneId);
		}
	}
	
	
	
	/**发送任务栏
	 * 
	 * @param zoneId
	 */
	public void sendStatusBar(long zoneId){
		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(zoneId);
		
		if (zone != null) {
			MapConfig mapconfig = zone.getConfigs().get(0);
			Map map = ManagerPool.mapManager.getMap(mapconfig.getServerId(), mapconfig.getLineId(), mapconfig.getMapId());
			if (map.getPlayerNumber() == 0) {
				return;
			}
			HashMap<String, Object> zonedata = zone.getOthers();
			
			if(getn(zonedata,"fajiang") == 1){//检测是否发奖励
				return;
			}	

			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			//副本ID
			paramMap.put("zonemodelid", zonemodelid);
			//累计死亡
			paramMap.put(S_ADDUPDEATH+"1", getn(zonedata,S_ADDUPDEATH+"1"));
			paramMap.put(S_ADDUPDEATH+"2", getn(zonedata,S_ADDUPDEATH+"2"));
			//累计杀怪
			paramMap.put(S_ADDUPKILLNUM+"1", getn(zonedata,S_ADDUPKILLNUM+"1"));
			paramMap.put(S_ADDUPKILLNUM+"2", getn(zonedata,S_ADDUPKILLNUM+"2"));
			//当前双方怪物存活数量
			int[] monnums = getMonsterNum(map, 0,2);
			paramMap.put(S_CURKILLNUM+"1", monnums[0]);
			paramMap.put(S_CURKILLNUM+"2", monnums[1]);
			
			if (map.getParameters().containsKey("target_1")) {	//发送给玩家当前怪物目标
				long aid = (Long)map.getParameters().get("target_1");
				String string = Long.toString(aid, 16);
				paramMap.put("target_1",string);
			}
			
			if (map.getParameters().containsKey("target_2")) {
				long bid = (Long)map.getParameters().get("target_2");
				String string = Long.toString(bid, 16);
				paramMap.put("target_2",string);
			}
		
			for (Player player : map.getPlayers().values()) {
				//当前玩家阵营
				paramMap.put("groupmark", player.getGroupmark());
				ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
				sendMessage.setScriptid(getId());
				sendMessage.setType(1);
				sendMessage.setMessageData(JSON.toJSONString(paramMap));
				MessageUtil.tell_player_message(player, sendMessage);
			}
		}
	}
	
	
	
	
	
	
	/**获取活着的怪物
	 * 
	 * @param map
	 * @return  type = 1 统计上一波怪物，2表示所有怪物
	 */
	public int[] getMonsterNum(Map map,int cishu,int type) {
		int[] nums = {0,0};
		cishu = cishu -1;
		Monster[] monsters = map.getMonsters().values().toArray(new Monster[0]);
		for (Monster mon : monsters) {
			if (mon != null && !MonsterState.DIE.compare(mon.getState())) {
				HashMap<String, Object> monpara = mon.getParameters();
				if (type == 1) {
					String cishustr = ""+cishu;
					if ( monpara.containsKey(S_REFRESHNUM) && monpara.get(S_REFRESHNUM).toString().equals(cishustr)) {
						if (mon.getGroupmark() == 2) {
							nums[0]=nums[0]+1;
						}else if (mon.getGroupmark() == 1) {
							nums[1]=nums[1]+1;
						}
					}
				}else {
					if (mon.getGroupmark() == 2) {
						nums[0]=nums[0]+1;
					}else if (mon.getGroupmark() == 1) {
						nums[1]=nums[1]+1;
					}
				}
			}
		}
		return nums;
	}

	
//	
//	/**判定胜利方
//	 * 设置胜利方阵营
//	 * @param zoneId
//	 */
//	public void getresult(long zoneId){
//		ZoneContext zone = ManagerPool.zonesManager.getContexts().get(zoneId);
//		if (zone != null) {
//			MapConfig mapconfig = zone.getConfigs().get(0);
//			Map map = ManagerPool.mapManager.getMap(mapconfig.getServerId(), mapconfig.getLineId(), mapconfig.getMapId());
//			HashMap<String, Object> zonedata = zone.getOthers();
//			
//			int[] monnums = getMonsterNum(map,0,2);
//			int num = monnums[0] - monnums[1]; 
//			if (num > 0) {//A 方剩余怪物数量多 
//				 setn(zonedata,S_RESULT,2);	//设置胜利方阵营
//				 setn(zonedata, "Stop", 1);	//停止标记
//				 triggerReward(map);	//奖励面板
//			}else if (num < 0) {//B 方剩余怪物数量多 
//				setn(zonedata,S_RESULT,1);
//				setn(zonedata, "Stop", 1);	//停止标记
//				triggerReward(map);	//奖励面板
//			}
//		}
//	}
	 
	
	/**怪物死亡
	 * 
	 */

	@Override
	public void onMonsterDie(Monster monster, Fighter killer) {
		Player player = (Player) killer;
		if (player != null) {
			Map map = ManagerPool.mapManager.getMap(player);
			if (map != null && map.getZoneModelId() == zonemodelid) {
				ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
				if (zone != null) {
					HashMap<String, Object> zonedata = zone.getOthers();
					if (getn(zonedata, "Stop") >= 1) {
						return;
					}
					
					int monnum = getn(zonedata,S_ADDUPKILLNUM + monster.getParameters().get("group"));
					setn(zonedata,S_ADDUPKILLNUM+monster.getParameters().get("group"),monnum +1);
				
					int monsum = getn(zonedata,S_CURKILLNUM + monster.getParameters().get("group"));
					setn(zonedata,S_CURKILLNUM+monster.getParameters().get("group"),monsum +1);
					sendStatusBar(map.getZoneId());
					
					int num = player.getZoneinfo().get(ZonesManager.killmonsternum+"_"+zonemodelid);
					num = num +1;
					player.getZoneinfo().put(ZonesManager.killmonsternum+"_"+zonemodelid,num);

					if (getn(zonedata, S_REFRESHNUM) >= brushnummax) {	//最后一波
						int[] monnums = getMonsterNum(map, 0,2);
						if (monnums[0] ==0||monnums[1]==0 ) {
							setn(zonedata, "Stop", 1);	//停止标记
							if (monnums[0] == 0) {	//A胜利
								setn(zonedata,S_RESULT,1);	//设置胜利方阵营
							}else if (monnums[1] == 0) {//B胜利
								setn(zonedata,S_RESULT,2);	//设置胜利方阵营
							}
							ManagerPool.monsterManager.removeAllMonster(map);
							triggerReward(map,1);	//奖励面板
						}
					}else {	//中途怪物相差60个
						int[] monnums = getMonsterNum(map, 0,2);
						int sum = monnums[0] - monnums[1];
						if (Math.abs(sum) >= differnummax) {
							if (monnums[0] > monnums[1]) {
								setn(zonedata,S_RESULT,2);	//设置胜利方阵营
							}else {
								setn(zonedata,S_RESULT,1);	//设置胜利方阵营
							}
							setn(zonedata, "Stop", 1);	//停止标记
							ManagerPool.monsterManager.removeAllMonster(map);
							triggerReward(map,2);	//奖励面板
						}else {	//如果有一方数量为0，直接出怪
							if (monnums[0] ==0||monnums[1]==0 ) {
								if (getn(zonedata,S_UPREFRESH) == 0) {
									setn(zonedata,S_UPREFRESH,1);
									setn(zonedata, S_REFRESHTIME, (int)(System.currentTimeMillis()/1000) + 5);	//设置下一波刷怪时间
								}
							}
						}
					}
				}
			}
		}
	}
	
	
	
	

	/**触发奖励
	 * type ：   1=最后一波怪物谁先杀完谁赢，2=或者2方怪物存在数量超过60只，3=或者一方离开地图
	 * @param player
	 * @param mapmodelid
	 * @param zoneid
	 */
	public void triggerReward(Map map ,int type){
		if (map.getZoneId() > 0) {
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if (zone == null) {
				return;
			}
			HashMap<String, Object> zonedata = zone.getOthers();
			int zoneModelId = zone.getZonemodelid();
			if(getn(zonedata,"fajiang") == 1){//检测是否发奖励
				return;
			}	
			
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			setn(zonedata,"fajiang",1);//设置发奖励标记
			
			for (Player mPlayer : map.getPlayers().values()) {
				if (mPlayer != null) {
					if (mPlayer.isDie()) {	//死亡自动复活
						ManagerPool.playerManager.autoRevive(mPlayer);
					}
					if ( !zonedata.containsKey("rew"+mPlayer.getId())) {
						paramMap.put("groupmark", mPlayer.getGroupmark());
						if (getn(zonedata,S_RESULT) == 0) {
							MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("双方战平！！"));
						}else {
							if(getn(zonedata,S_RESULT) == mPlayer.getGroupmark()){
								switch (type) {
								case 1:
									MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("率先击杀最后一波怪物，恭喜您获得了胜利！"));
									break;
								case 2:
									MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("双方战场存在怪物数量差距超过{1}，恭喜您获得了胜利！"),differnummax+"");
									break;
								case 3:
									MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("对方全部逃离战场，恭喜您获得了胜利！"));
									break;	
								case 4:
									MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("对方累计死亡达到{1}次，恭喜您获得了胜利！"),dienummax+"");
									break;	
								default:
									break;
								}
								
								paramMap.put("result", 1);
								
								int level  = mPlayer.getLevel();
								if (level > 100) {
									level = 100;
								}
								Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(level);
								int exp=(int) ((double)level*level*model.getQ_basis_exp()*2*1.2);
								paramMap.put("exp", exp);
								ManagerPool.playerManager.addExp(mPlayer, exp,AttributeChangeReason.SHUIYANDALIANG);
								MessageUtil.notify_player(mPlayer, Notifys.CHAT_ROLE, ResManager.getInstance().getString("获得水淹大梁副本胜利额外经验{1}！"),exp+"");
								
							}else {
								paramMap.put("result", 2);
								switch (type) {
								case 1:
									MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("对方率先击杀最后一波怪物，很遗憾，您失败了！"));
									break;
								case 2:
									MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("双方战场存在怪物数量差距超过{1}，很遗憾，您失败了！"),differnummax+"");
									break;
								case 3:
									MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("我方全部逃离战场，很遗憾，您失败了！"));
									break;	
								case 4:
									MessageUtil.notify_player(mPlayer, Notifys.CHAT_BULL, ResManager.getInstance().getString("我方累计死亡达到{1}次，很遗憾，您失败了！"),dienummax+"");
									break;
								default:
									break;
								}
								int level  = mPlayer.getLevel();
								if (level > 100) {
									level = 100;
								}
								Q_characterBean model = ManagerPool.dataManager.q_characterContainer.getMap().get(level);
								int exp= level*level*model.getQ_basis_exp()*2;
								paramMap.put("exp", exp);
								ManagerPool.playerManager.addExp(mPlayer, exp,AttributeChangeReason.SHUIYANDALIANG);
								MessageUtil.notify_player(mPlayer, Notifys.CHAT_ROLE, ResManager.getInstance().getString("获得水淹大梁副本参与额外经验{1}！"),exp+"");
							}
							ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
							sendMessage.setScriptid(getId());
							sendMessage.setType(2);
							sendMessage.setMessageData(JSON.toJSONString(paramMap));
							MessageUtil.tell_player_message(mPlayer, sendMessage);
						}

						
						ZoneLog zlog = new ZoneLog();
						zlog.setPlayerid(mPlayer.getId());
						zlog.setType(3);
						zlog.setZonemodelid(zoneModelId);
						zlog.setSid(mPlayer.getCreateServerId());
						LogService.getInstance().execute(zlog);
						
						zone.getOthers().put("rew"+mPlayer.getId(),zoneModelId);
						//mPlayer.getZoneinfo().put(ZonesManager.ManualDeathnum+"_"+zoneModelId,0);
						//mPlayer.getZoneinfo().put(ZonesManager.killmonsternum+"_"+zoneModelId,0);
						int time = (Integer)zone.getOthers().get("time");
						int newtime = (int)(System.currentTimeMillis()/1000 - time);
						mPlayer.getZoneinfo().put(ZonesManager.Manualendtime+"_"+zoneModelId, newtime);
						
						ManagerPool.zonesFlopManager.addZoneReward(mPlayer, zoneModelId, false);
						ManagerPool.zonesFlopManager.stZonePassShow(mPlayer, 3, zoneModelId);
						setClearance(mPlayer,zoneModelId);
					}
				}
			}
		}
	}	
	
	
	
	/**设定参与通关
	 * 
	 * @param player
	 * @param zoneid
	 */
	public void setClearance(Player player ,int zoneModelId){
		long status = ManagerPool.countManager.getCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId);
		if (status < 2) {//（记录是否参与）（通关后设置为2）
			ManagerPool.countManager.addCount(player, CountTypes.ZONE_TEAM_ST, ""+zoneModelId, 1);
		}
	}
	
	
	
	/**死亡3次后提示
	 * 
	 * @param list
	 */
	public void deathprompts(List<Object> list){
		Player player = (Player)list.get(0);
		if (player != null) {
			HashMap<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("zonemodelid", zonemodelid);
			paramMap.put("deathprompts", 3);	//死亡3次后提示面板
			ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
			sendMessage.setScriptid(getId());
			sendMessage.setType(3);
			sendMessage.setMessageData(JSON.toJSONString(paramMap));
			MessageUtil.tell_player_message(player, sendMessage);	
		}
	}
	
	
	/**一方离开副本，检测并判定胜利方
	 * 
	 */
	public void offlinecheck(Map map){
		if (map != null && map.getZoneModelId() == zonemodelid) {
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if (zone != null) {
				HashMap<String, Object> zonedata = zone.getOthers();
				if(getn(zonedata, "Stop") == 0){
					int anum = 0;
					int bnum = 0;
					for (Player mPlayer : map.getPlayers().values()) {
						if (mPlayer.getGroupmark() == 1) {
							anum = anum + 1;
						}else if (mPlayer.getGroupmark() == 2 ) {
							bnum = bnum + 1;
						}
					}
					
					if (anum == 0 || bnum == 0) {
						if (anum == 0) {
							setn(zonedata,S_RESULT,2);	//设置胜利方阵营
							setn(zonedata, "Stop", 1);	//停止标记
							ManagerPool.monsterManager.removeAllMonster(map);
							triggerReward(map,3);	//奖励面板
						}else if (bnum == 0) {
							setn(zonedata,S_RESULT,1);	//设置胜利方阵营
							setn(zonedata, "Stop", 1);	//停止标记
							ManagerPool.monsterManager.removeAllMonster(map);
							triggerReward(map,3);	//奖励面板
						}
					}
				}
			}
		}
	}


	//--------------------怪物脚本触发-----------------
	@Override
	public boolean wasHit(Monster monster, Fighter attacker) {
		return false;
	}


	/**怪物AI，获取目标
	 * 
	 */
	@Override
	public Fighter getAttackTarget(Monster monster) {
		Map map = ManagerPool.mapManager.getMap(monster);
		if (map != null && map.getZoneModelId() == zonemodelid) {
			if (map.getParameters().containsKey("target_"+ monster.getGroupmark())) {
				long  targetid = (Long)map.getParameters().get("target_"+ monster.getGroupmark() );
				Player player = ManagerPool.playerManager.getPlayer(targetid);
				if (player != null && !player.isDie() && player.getGroupmark() != monster.getGroupmark() && map.getPlayers().containsKey(player.getId())) {
					return player;
				}
				
//				for (Player mPlayer : map.getPlayers().values()) {
//					if (!mPlayer.isDie() && mPlayer.getGroupmark() != monster.getGroupmark()) {
//						if (targetid == mPlayer.getId()) {
//							return mPlayer;
//						}
//					}
//				}
			}
		}
		return null;
	}

	

	@Override
	public Skill getSkill(Monster monster) {
		return monster.getUseSkill();
	}

	
	//--------------------------------退出地图触发--------------------------------------
	@Override
	public void onQuitMap(Player player, Map map) {
		if (map.getZoneModelId() == zonemodelid) {	//改变攻击模式为和平
			ManagerPool.playerManager.changePkState(player, 0, 0);
		}
	}

	//--------------------------------定时公告--------------------------------------
	
	@Override
	public void action(int serverId, String serverWeb) {
		//韩国版暂不开放
		if (WServer.getInstance().getServerWeb().equals("hgpupugame")) {	
			return;
		}
		long millis = System.currentTimeMillis();
		long week = TimeUtil.getDayOfWeek(millis);
		long min = TimeUtil.getDayOfMin(millis);
		long hour = TimeUtil.getDayOfHour(millis);
		if (week == 1) {
			if (hour == 20 && min == 30 ){
				ResZoneTeamOpenBullToClientMessage cmsg = new ResZoneTeamOpenBullToClientMessage();
				cmsg.setZonemodelid(zonemodelid);
				MessageUtil.tell_world_message(cmsg);
			}else if (hour == 21 && min == 0 ) {
				ResZoneApplyDataInfoToClientMessage pmsg = new ResZoneApplyDataInfoToClientMessage();
				pmsg.setZoneapplydatainfo(new ZoneApplyDataInfo());
				MessageUtil.tell_world_message(pmsg);
				MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("今日水淹大梁活动结束，报名均已停止，活动开启时间“每周一20:30——21:00”"));
			}
		}
	}
	
	
	
	/**设定攻击目标
	 * @return 
	 * 
	 */
	public void settarget(Map map , int groupmark){
		if (map != null) {
			int fightpower = Integer.MAX_VALUE ;
			int upfightpower = 0 ;

			Player targetPlayer = null;
			Player uptargetPlayer = null;
			int num = 0;
			ZoneContext zone = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if (zone == null || zone.getZonemodelid() != zonemodelid) {
				return ;
			}
			
			 @SuppressWarnings("unchecked")
			//从副本参数取出战斗力数值
			HashMap<Long, Integer> fpmap = (HashMap<Long, Integer>)zone.getOthers().get("fpmap");
			for (Entry<Long, Integer> playerEntry : fpmap.entrySet()) {
				Player mPlayer = ManagerPool.playerManager.getOnLinePlayer(playerEntry.getKey());
				if (mPlayer != null && mPlayer.getGroupmark() != groupmark && !mPlayer.isDie() && map.getPlayers().containsKey(mPlayer.getId())) {
					num = num + 1;
					int fp=playerEntry.getValue();
					if (fightpower > fp && getn(zone.getOthers(), S_PLAYERDEATH+mPlayer.getId()) == 0) {
						fightpower = fp;
						targetPlayer = mPlayer;//取活着的，最小战斗力玩家
					}
					
					if (upfightpower < fp) {
						upfightpower = fp;
						uptargetPlayer = mPlayer;//取活着的，最高战斗力玩家
					}
				}
			}
			 

			//如果每个人都死过了，那么就选活着的，最战斗力最高的人
			if (targetPlayer == null) {
				targetPlayer = uptargetPlayer;
			}
			
			boolean is = false;
			//写入追击目标（用来通知其他人）
			if(targetPlayer != null){
				if(map.getParameters().containsKey("target_"+groupmark)){
					long  targetid = (Long)map.getParameters().get("target_"+groupmark);
					if (targetid != targetPlayer.getId()) {
						map.getParameters().put("target_"+groupmark, targetPlayer.getId());
						is = true;
					}
				}else {
					is = true;
					map.getParameters().put("target_"+groupmark, targetPlayer.getId());
				}
				
				if (is ) {
					for (Player mPlayer : map.getPlayers().values()) {
						if (mPlayer.getGroupmark() != groupmark){
							MessageUtil.notify_player(mPlayer, Notifys.CHAT_ROLE, ResManager.getInstance().getString("【{1}】被锁定为怪物目标，请大家保护好他，奋力击杀较多的怪物"),targetPlayer.getName());
						}
					}
				}
			}
		}
	}
	
	
	
	
	

}

package scripts.zone.maze;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_characterBean;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.script.IEnterMapScript;
import com.game.map.script.IEnterTeleporterScript;
import com.game.map.structs.Map;
import com.game.maze.message.ResChangeFloorMessage;
import com.game.maze.message.ResRewardMessage;
import com.game.npc.bean.ServiceInfo;
import com.game.npc.script.INpcApplyServicesScript;
import com.game.npc.script.INpcServiceScript;
import com.game.npc.struts.NPC;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.server.config.MapConfig;
import com.game.server.impl.MServer;
import com.game.server.script.IServerEventTimerScript;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.util.TimerUtil;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;
import com.game.zones.message.ResZoneTeamOpenBullToClientMessage;
import com.game.zones.script.ICreateZoneScript;
import com.game.zones.script.IZoneEventTimerScript;
import com.game.zones.structs.ZoneContext;
import com.game.zones.timer.ZoneLoopEventTimer;

public class MazeScript  implements IServerEventTimerScript,ICreateZoneScript ,IEnterTeleporterScript , INpcServiceScript, INpcApplyServicesScript, IEnterMapScript, IZoneEventTimerScript{

	private static Logger log = Logger.getLogger(MazeScript.class);
	
	private static Object obj = new Object();
	
	//迷宫副本模版ID
	private int ZONEID = 7;	
	
	//是否开启，0默认关闭，1开始
	private int ISOPEN = 0;
	
	//单个迷宫最大人数
	private int PlayerMaxNum = 150;

	//迷宫1到20层地图ID
	private int[] ZONEMAPIDLIST = {
			41101
			,41102
			,41103
			,41104
			,41105
			,41106
			,41107
			,41108
			,41109
			,41110
			,41111
			,41112
			,41113
			,41114
			,41115
			,41116
			,41117
			,41118
			,41119
			,41120};
	
	//领奖NPCID
	private int npcmodelid = 12290;
	//储存当前迷宫副本列表
	List<ZoneContext> zonelist = new ArrayList<ZoneContext>() ;
	
	//随机每层正确入口
	List<Integer>  zoneentr = new ArrayList<Integer>() ;
	
	//传送到达坐标，对应4个传送点
	private int[][] entrxy = {{99, 90}, {13, 85}, {23, 37}, {97, 38}};
	
	private String pointstr = ResManager.getInstance().getString("迷宫探险活动时间为每周三，四 （20:30 - 21:00）");
	
	/**迷宫前三名数据保存
	 * 
	 */
	private Vector<Long> mazetoplist = new Vector<Long>();
	
	//刷新怪物列表
	//private Vector<Integer> monsters = new Vector<Integer>();
	
	//已生成名次
	private int nowsort;
	
	//生成刷新怪物时间
	private long refreshTime;
	
	//刷新怪物模板Id
	private int refreshMonster;

	//刷新怪物地图模板Id
	private int refreshMap;
	
	//优胜奖礼金
	private static int YOUSHENG_LIJIN = 1000;
	
	//优胜奖经验
	private static int YOUSHENG_JINGYAN = 1000;
	
	//优胜奖真气
	private static int YOUSHENG_ZHENQI = 180;
	
	//参与奖礼金
	private static int CANYU_LIJIN = 200;
		
	//参与奖经验
	private static int CANYU_JINGYAN = 500;
	
	//第一个地宫副本Id
	private long firstZone;
	
	public int getISOPEN() {
		return ISOPEN;
	}

	public void setISOPEN(int iSOPEN) {
		ISOPEN = iSOPEN;
	}
	
	public List<ZoneContext> getZonelist() {
		return zonelist;
	}

	public void setZonelist(List<ZoneContext> zonelist) {
		this.zonelist = zonelist;
	}
	
	//刷新怪物列表
	private int[] monsters = {251,291,331,371,431,491,531,571};
	private int[] monsterboss = {211,261,341,391,441,541};
	
	/**迷宫副本
	 * 
	 */
	
	@Override
	public int getId() {
		return 4007;
	}

	@Override
	public void onTeleporter(Player player, int line, int tranId,int scriptid) {
		Map map = ManagerPool.mapManager.getMap(player);
		if ((map == null) || (map.getZoneModelId() != ZONEID)) {
			return;
		}

		int pos = scriptid;
		if (pos > 4) {
			return;
		}
		
		ZoneContext zcon = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
		if (zcon != null) {
			int floornum = getFloor(player);//得到当前层数
			if (floornum == -1) {
				return;
			}

			int correct = zoneentr.get(floornum);	//得到当前层正确入口

			if (getISOPEN() == 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, pointstr);
				return;
			}

			if (floornum == 0) {	//在1层里，只能向下
				if (correct == pos) { // 正确入口
					player.setTransType(1);
					ManagerPool.mapManager.changeMap(player, zcon.getConfigs().get(floornum+1).getMapId(), zcon.getConfigs().get(floornum+1).getMapModelId(), 1, setPosition(entrxy[pos-1][0],entrxy[pos-1][1]), this.getClass().getName() + ".onTeleporter 1");	
				}else {
					player.setTransType(0);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("此路不通，请换个入口"));
					ManagerPool.mapManager.changePosition(player, setPosition(60,60));
					ResChangeFloorMessage msg = new ResChangeFloorMessage();
					msg.setType(0);
					MessageUtil.tell_player_message(player, msg);
					

				}
			}else if (floornum == 19) {	//在20层里，传送点无效
				//MessageUtil.notify_player(player, Notifys.CUTOUT, "恭喜您到达了迷宫最后一层，快去找NPC领取奖励吧。");
			}else { //中间层，可倒退
				if (correct == pos) { // 正确入口
					player.setTransType(1);
					ManagerPool.mapManager.changeMap(player, zcon.getConfigs().get(floornum+1).getMapId(), zcon.getConfigs().get(floornum+1).getMapModelId(), 1, setPosition(entrxy[pos-1][0],entrxy[pos-1][1]), this.getClass().getName() + ".onTeleporter 2");	
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "选择正确！进入第{1}层", (floornum+1+1)+"");
				}else {	//错误入口，倒退
					player.setTransType(2);
					ManagerPool.mapManager.changeMap(player, zcon.getConfigs().get(floornum-1).getMapId(), zcon.getConfigs().get(floornum-1).getMapModelId(), 1, setPosition(entrxy[pos-1][0],entrxy[pos-1][1]), this.getClass().getName() + ".onTeleporter 3");	
//					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "踩到陷阱，退回第{1}层", (floornum)+"");
				}
			}
		}
	}
	
	
	public Position setPosition(int x ,int y){
		Position position = new Position();
		position.setX((short) (x*MapUtils.GRID_BORDER));
		position.setY((short) (y*MapUtils.GRID_BORDER));
		return position;
	}

	

	
	/**系统执行创建迷宫环境
	 * 
	 */
	public void sysactMaze(List<Object> list){
		setISOPEN(1);
		mazeGenerateEntrance();
		MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("迷宫探险活动开始了！参与活动请猛击【挑战面板】进入"));
		mazetoplist.clear();
		getZonelist().clear();
		nowsort = 0;
		ResZoneTeamOpenBullToClientMessage cmsg = new ResZoneTeamOpenBullToClientMessage();
		cmsg.setZonemodelid(ZONEID);
		MessageUtil.tell_world_message(cmsg);
		


	}
	
	/**系统执行关闭迷宫
	 * 
	 */
	public void sysOffMaze(List<Object> list){
		setISOPEN(0);
		//副本停止
		for (int i = 0; i < getZonelist().size(); i++) {
			ZoneContext zcon = getZonelist().get(i);
			MServer server = ManagerPool.zonesManager.getmServers().get(zcon.getId());
			if(server!=null){
				server.setDelete(true);
			}
		}
		getZonelist().clear();
		MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("今日迷宫活动结束，迷宫活动开启时间“每周三，四20:30——21:00”"));
	}
	
//	/**
//	 * 获取刷新精英怪
//	 */
//	private void getJingyingMonsters(){
//		monsters.clear();
//		List<Q_monsterBean> monsterBeans = ManagerPool.dataManager.q_monsterContainer.getList();
//		Iterator<Q_monsterBean> iter = monsterBeans.iterator();
//		while (iter.hasNext()) {
//			Q_monsterBean q_monsterBean = (Q_monsterBean) iter.next();
//			if(q_monsterBean.getQ_monster_type()==2 && q_monsterBean.getQ_grade()>=40){
//				monsters.add(q_monsterBean.getQ_id());
//			}
//		}
//	}
//	
	/**创建迷宫副本
	 * @return 
	 * 
	 */
	public ZoneContext  createMaze(){
		HashMap<String, Object> others = new HashMap<String, Object>();
		ArrayList<Integer> maplist = new ArrayList<Integer>();
		for (int i = 0; i < 20; i++) {
			maplist.add(ZONEMAPIDLIST[i]);
		}
		ZoneContext zone =ManagerPool.zonesManager.setZone("迷宫",others,maplist,ZONEID);	//创建副本，返回副本消息
		zone.setIsautoremove(false);
		zonelist.add(zone);
		return zone;
	}
	
	
	
	/**随机迷宫入口
	 * 
	 */
	public void mazeGenerateEntrance() {
		zoneentr.clear();
		for (int i = 0; i < 20; i++) {
			zoneentr.add(RandomUtils.random(1, 4));
		}
	}
	
	
	
	/**获取当前层数
	 * -1表示没有
	 */
	public int getFloor(Player player){
		Map map = ManagerPool.mapManager.getMap(player);
		if (map != null) {
			ZoneContext zcon = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
			if (zcon != null) {
				for (int i = 0; i < zcon.getConfigs().size(); i++) {
					if (zcon.getConfigs().get(i).getMapId() == map.getId()) {
						return i;
					}
				}
			}
		}
		return -1;
	}

	
	

	/**检查迷宫人数
	 * @return 
	 * 
	 */
	public int checkZonePlayerNum(ZoneContext zcon){
		int num = 0;
		List<MapConfig> mapConfiglist = zcon.getConfigs();
		for (MapConfig mapconfig : mapConfiglist) {
			Map map = ManagerPool.mapManager.getMap(mapconfig.getServerId(), mapconfig.getLineId(), mapconfig.getMapId());
			if (map != null ) {
				num = num + map.getPlayers().size();
			}
		}
		return num;
	}

	
	

	/**迷宫活动NPC入口
	 * 
	 * @param player
	 */
	public ZoneContext ZoneNpcEntra(Player player ){
//		for (int i = 0; i < zoneentr.size(); i++) {
//			MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "第"+(i+1)+"层>"+zoneentr.get(i));
//		}
//		String jsonString = JSON.toJSONString(zoneentr);
//		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, "正确入口："+jsonString);
//		//+++++++++++++++++++++++++++++++++++++++++
		Map map = ManagerPool.mapManager.getMap(player);
		if(isZoneMap(map.getMapModelid())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经在迷宫内。"));
			return null;
		}
		synchronized (obj) {
			if (zoneentr.size() == 0) {
				mazeGenerateEntrance();
			}
	
			if (getISOPEN() == 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, pointstr);
				return null;
			}
			if (player.getLevel() < 40) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("迷宫探险活动需要等级达到40级才能参加"));
			}
			
			int days = TimeUtil.getBetweenDays(player.getEnterTime(), System.currentTimeMillis());
			if(days!=0){
				player.setSort(0);
				player.setEnterTime(System.currentTimeMillis());
			}
			player.setTransType(-1);
			if (getZonelist().size() > 0) {
				for (int i = 0; i < getZonelist().size(); i++) {
					ZoneContext zcon = getZonelist().get(i);
					int num = checkZonePlayerNum(zcon);
					if (num < PlayerMaxNum || getZonelist().size() == 5) { //最多只会创建5个
						player.getActivitiesReward().put("PARTIMAZE", TimeUtil.GetCurTimeInMin(4)+"");
						ManagerPool.mapManager.changeMap(player, zcon.getConfigs().get(0).getMapId(), zcon.getConfigs().get(0).getMapModelId(), 1, setPosition(50,50), this.getClass().getName() + ".ZoneNpcEntra 1");	
						return null;
					}
				}
			}

			if (getZonelist().size() < 5) {
				ZoneContext zcon = createMaze();
				if(getZonelist().size()==1){
					firstZone = zcon.getId();
				}
				player.getActivitiesReward().put("PARTIMAZE", TimeUtil.GetCurTimeInMin(4)+"");
				ManagerPool.mapManager.changeMap(player, zcon.getConfigs().get(0).getMapId(), zcon.getConfigs().get(0).getMapModelId(), 1, setPosition(60,60), this.getClass().getName() + ".ZoneNpcEntra 2");
				
				return zcon;
			}
			
			return null;
		}
	}

	
	/**进入迷宫
	 * 
	 */
	@Override
	public ZoneContext onCreate(Player player, int zoneId) {
		//副本中
		Map ckmap = ManagerPool.mapManager.getMap(player);
		if (ckmap != null && ckmap.getZoneId() > 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经在副本中。"));
			return null;
		}
			
		if (zoneId == ZONEID) {
			return ZoneNpcEntra(player);
		}
		return null;
	}
	
	
	
	//点击NPC按钮
	@Override
	public void reqService(Player player, NPC npc, String parameters) {
		if (ZONEMAPIDLIST[ZONEMAPIDLIST.length-1] != player.getMapModelId()) {
			return;
		}
		if (npc.getModelId() != npcmodelid) {
			return;
		}
//		int rewardnum = ManagerPool.countManager.getCount(player, CountTypes.MAZE_NUM, null);
//		
//		HashMap<String, Object> map=NpcParamUtil.resolve(player, parameters);
//		if (map != null ){
//			if(map.get("id") != null &&  map.get("id").equals("1")) {
//				if (rewardnum > 0) {
//					MessageUtil.notify_player(player, Notifys.ERROR, "您已经领取过奖励，下次再来吧！");
//					return;
//				}
//				
//				ManagerPool.countManager.addCount(player, CountTypes.MAZE_NUM,null ,1, 1, 0);
//				if (mazetoplist.size() >= 3) {
//					ManagerPool.zonesFlopManager.giveZoneFixedReward(player, ZONEID,1);
//					MessageUtil.notify_player(player, Notifys.CUTOUT, "恭喜您获得了迷宫通关参与奖！");
//					mazetoplist.add(player.getId());
//				}else {
//					ManagerPool.zonesFlopManager.giveZoneFixedReward(player, ZONEID,0);
//					MessageUtil.notify_player(player, Notifys.CUTOUT, "恭喜您获得了迷宫通关第3名奖励！");
//					mazetoplist.add(player.getId());
//				}
//
//				
//			}else if(map.get("id") != null &&  map.get("id").equals("2"))  {
//				if (rewardnum > 0) {
//					ManagerPool.zonesManager.outZone(player);
//				}else {
//					MessageUtil.notify_player(player, Notifys.ERROR, "难得来一趟，请先领取奖励再走吧！");
//				}
//			}
//		}
		long rewardnum = ManagerPool.countManager.getCount(player, CountTypes.MAZE_NUM, null);
		
		if (rewardnum == 0) {
			
			ManagerPool.countManager.addCount(player, CountTypes.MAZE_NUM,null ,1, 1, 0);

			if(player.getSort() < 4){
				//前三名
				ManagerPool.zonesFlopManager.giveZoneFixedReward( player, ZONEID, 0);
				try {
					log.error(player.getName()+"("+player.getId()+")"+" 领取迷宫第"+player.getSort()+"奖励 ZONEID "+ZONEID);
				} catch (Exception e) {
					log.error(e, e);
				}
			}else{
				ManagerPool.zonesFlopManager.giveZoneFixedReward( player, ZONEID, 1);
			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经领取过奖励，下次活动再来吧！"));
		}
		
		ManagerPool.zonesManager.outZone(player);
	}	


	//点击NPC弹出对话
	@Override
	public void applyServices(Player player, NPC npc, List<ServiceInfo> infos) {
		if (ZONEMAPIDLIST[ZONEMAPIDLIST.length-1] != player.getMapModelId()) {
			return;
		}
		if (npc.getModelId() != npcmodelid) {
			return;
		}
//		int rewardnum = ManagerPool.countManager.getCount(player, CountTypes.MAZE_NUM, null);
//		
//		if (rewardnum > 0) {
//			String [][]  str= {{"name=离开;id=2" ,NpcParamUtil.BUTTON}};
//			NpcParamUtil.say(player, infos, str);
//		}else {
//			String [][] str= {{"name=领取奖励;id=1" ,NpcParamUtil.BUTTON}};
//			NpcParamUtil.say(player, infos, str);
//		}
		
		Q_characterBean bean = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
		int bindgold = 0;
		int exp = 0;
		int zhenqi = 0;
		if(bean!=null){
			if(player.getSort() < 4){
				//前三名//-1铜币，-2元宝，-3真气，-4经验  -5礼金
				HashMap<Integer, Integer> map2 = ManagerPool.zonesFlopManager.getZoneCountReward(player , 7 , 0);
				if (map2.containsKey(-5)) {
					bindgold = map2.get(-5) ;
				}else {
					bindgold = YOUSHENG_LIJIN;
				}
				if (map2.containsKey(-4)) {
					exp = map2.get(-4) ;
				}else {
					exp = bean.getQ_basis_exp() * YOUSHENG_JINGYAN;
				}
				if (map2.containsKey(-3)) {
					zhenqi = map2.get(-3) ;
				}else {
					zhenqi = bean.getQ_basis_zhenqi() * YOUSHENG_ZHENQI;
				}
			}else{//参与奖
				HashMap<Integer, Integer> map1 = ManagerPool.zonesFlopManager.getZoneCountReward(player , 7 , 1);
				if (map1.containsKey(-4)) {
					exp = map1.get(-4) ;
				}else {
					exp = bean.getQ_basis_exp() * CANYU_JINGYAN;
				}
				if (map1.containsKey(-5)) {
					bindgold = map1.get(-5) ;
				}else {
					bindgold = CANYU_LIJIN;
				}
			}
		}

		ResRewardMessage msg = new ResRewardMessage();
		msg.setNpc(npc.getId());
		msg.setSort(player.getSort());
		msg.setTime(player.getPassTime());
		msg.setExp(exp);
		msg.setBindgold(bindgold);
		msg.setZhenqi(zhenqi);
		MessageUtil.tell_player_message(player, msg);
	}
	
	
	//定时执行
	@Override
	public void action(int serverId, String serverWeb){
		int hour=TimeUtil.getDayOfHour(System.currentTimeMillis());
		int min=TimeUtil.getDayOfMin(System.currentTimeMillis());
		int week=TimeUtil.getDayOfWeek(System.currentTimeMillis());
		if ( week == 3  || week== 4 ) {
			if (hour == 20 && (min == 30 || min == 31)) {
				if (getISOPEN() == 0) {
					sysactMaze(null);
				}
			}

			if (hour == 21 && (min == 0|| min == 1)) {
				if (getISOPEN() == 1) {
					sysOffMaze(null);
				}
			}
			
			if (hour == 20 && (min >= 31 && min <= 59 ) &&  (min%5 == 0)) {
				refreshboss();
			}
		}
	}

	@Override
	public void onEnterMap(Player player, Map map) {
		if(!isZoneMap(map.getMapModelid())) return;
		
		ZoneContext zcon = ManagerPool.zonesManager.getContexts().get(map.getZoneId());
		if (zcon != null) {
			if(!zcon.getOthers().containsKey("ADDTIMER")){
				zcon.getOthers().put("ADDTIMER", System.currentTimeMillis());
				List<Object> parameters = new ArrayList<Object>();
				parameters.add(System.currentTimeMillis());
				ZoneLoopEventTimer timer = new ZoneLoopEventTimer(this.getId(), map.getZoneId(), map.getZoneModelId(), parameters, 1000);
				TimerUtil.addTimerEvent(timer);
			}
			
			int floornum = getFloor(player);//得到当前层数
			if (floornum == -1) {
				return;
			}

			if (getISOPEN() == 0) {
				MessageUtil.notify_player(player, Notifys.ERROR, pointstr);
				return;
			}
			
			if (floornum == 0) {
				if (mazetoplist.size() >= 3) {	
					reqmapenter(player);	//正确入口
				}else {
					ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
					sendMessage.setScriptid(4007);
					sendMessage.setType(1);
					sendMessage.setMessageData("");	//清理上次迷宫方向记录
					MessageUtil.tell_player_message(player, sendMessage);
				}	
			}
			
			
			if (floornum == 19) {
				//最后一层
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您迷宫过关，请点击地图中央NPC领奖吧！"), (floornum+1+1)+"");
				ResChangeFloorMessage msg = new ResChangeFloorMessage();
				msg.setType(3);
				MessageUtil.tell_player_message(player, msg);
			}else if(player.getTransType() == 1){
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您！成功进入第{1}层，还剩{2}层。"), (floornum+1)+"", (ZONEMAPIDLIST.length - 1 - floornum) + "");
				//正确到达
				ResChangeFloorMessage msg = new ResChangeFloorMessage();
				msg.setType(1);
				msg.setFloor(floornum + 1);
				msg.setRemain(ZONEMAPIDLIST.length - 1 - floornum);
				MessageUtil.tell_player_message(player, msg);
			}else if(player.getTransType() == 2){
				MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("您不小心走错了路，退回到了第{1}层！"), (floornum+1)+"");
				//错误退回
				ResChangeFloorMessage msg = new ResChangeFloorMessage();
				msg.setType(2);
				msg.setFloor(floornum + 1);
				msg.setRemain(ZONEMAPIDLIST.length - 1 - floornum);
				MessageUtil.tell_player_message(player, msg);
				
				if (mazetoplist.size() >= 3) {	//刷出正确路径
					reqmapenter(player);
				}
			}
			
			if (floornum == 19) {	//在20层里，计算玩家完成时间，是否为前3名
				if(player.getSort() > 0){
					return;
				}
				//名次
				int sort = 0;
				synchronized (mazetoplist) {
					if(mazetoplist.size() < 3){
						sort = mazetoplist.size() + 1;
						mazetoplist.add(player.getId());
					}
					nowsort++;
				}
				player.setSort(nowsort);
				try{ //记录每次迷宫前三名获得者
					if(nowsort<=3){ log.error(new Date(System.currentTimeMillis()).toLocaleString()+" 迷宫第"+nowsort+"名到达者"+player.getName()+" "+player.getId());  }
				}catch(Exception e){
					log.error(e, e);
				}
				player.setPassTime((int)((System.currentTimeMillis() - player.getEnterTime())/1000));
				if(sort!=0){
					//-1铜币，-2元宝，-3真气，-4经验  -5礼金
					Q_characterBean bean = ManagerPool.dataManager.q_characterContainer.getMap().get(player.getLevel());
					if(bean!=null){
						int bindgold = 0;
						int exp=0;
						int zhenqi = 0;
						HashMap<Integer, Integer> map2 = ManagerPool.zonesFlopManager.getZoneCountReward(player , 7 , 0);
						if (map2.containsKey(-5)) {
							bindgold = map2.get(-5) ;
						}else {
							bindgold = YOUSHENG_LIJIN;
						}
						if (map2.containsKey(-4)) {
							exp = map2.get(-4) ;
						}else {
							exp = bean.getQ_basis_exp() * YOUSHENG_JINGYAN;
						}
						if (map2.containsKey(-3)) {
							zhenqi = map2.get(-3) ;
						}else {
							zhenqi = bean.getQ_basis_zhenqi() * YOUSHENG_ZHENQI;
						}

						if(sort==1 || sort==2){
							MessageUtil.notify_All_player(Notifys.SROLL, java.text.MessageFormat.format(ResManager.getInstance().getString("恭喜{0}获得了迷宫活动的第{1}个优胜奖，可获得{2}礼金，{3}经验，{4}真气大奖"), new Object[] {player.getName(), sort, bindgold, exp, zhenqi}));
						}else if(sort==3){
							MessageUtil.notify_All_player(Notifys.SROLL, java.text.MessageFormat.format(ResManager.getInstance().getString("恭喜{0}获得了迷宫活动的最后1个优胜奖，可获得{1}礼金，{2}经验，{3}真气大奖"), new Object[] {player.getName(), bindgold, exp, zhenqi}));
						}
					}
					
				}
			}
		}
	}
	
	
	/**发送正确入口
	 * 
	 * @param player
	 */
	public void reqmapenter(Player player){
		HashMap<String, Object> paramMap = new HashMap<String, Object>();
		for (int i = 0; i < ZONEMAPIDLIST.length; i++) {
			if (41120 != ZONEMAPIDLIST[i]) {
				paramMap.put(""+ZONEMAPIDLIST[i], zoneentr.get(i));
			}
		}
		
		ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
		sendMessage.setScriptid(4007);
		sendMessage.setType(1);
		sendMessage.setMessageData(JSON.toJSONString(paramMap));
		MessageUtil.tell_player_message(player, sendMessage);
	}
	
	
	
	private boolean isZoneMap(int mapModelId){
		for (int i = 0; i < ZONEMAPIDLIST.length; i++) {
			if(ZONEMAPIDLIST[i]==mapModelId) return true;
		}
		return false;
	}

	@Override
	public void action(long zoneId, int zoneModelId, List<Object> parameters) {
		if(getISOPEN()==0){
			return;
		}
		
		int min=TimeUtil.getDayOfMin(System.currentTimeMillis());
		if (min >= 29) {
			return;
		}
		
		//上次刷新时间
		long reTime = (Long)parameters.get(0);
		boolean first = (zoneId == firstZone);
		//未更新
		if(!first && reTime==refreshTime) return;
		
		if(first && monsters.length>0){
			//第一个迷宫线程，负责刷新精英信息
			if(System.currentTimeMillis() - refreshTime > 60 * 1000){
				//随机生成怪
				refreshTime = System.currentTimeMillis();
				int floor = RandomUtils.random(ZONEMAPIDLIST.length);
				refreshMap = ZONEMAPIDLIST[floor];
				refreshMonster = monsters[ RandomUtils.random(monsters.length)];
				String nameString = ManagerPool.monsterManager.getName(refreshMonster);
				MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("携带大量宝物的【{1}】在迷宫{2}层刷新，大家快去啊。"), nameString, (floor + 1) +"");
			}
		}
		
		if(reTime!=refreshTime && refreshMonster!=0){
			parameters.remove(0);
			parameters.add(refreshTime);
			Map map = null;
			map = findMap(zoneId, refreshMap);
			if(map!=null){
				List<Grid> noblockGrids = new ArrayList<Grid>();
				Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
				for (int i = 0; i < grids.length; i++) {
					for (int j = 0; j < grids[i].length; j++) {
						if(grids[i][j].getBlock()!=0) noblockGrids.add(grids[i][j]);
					}
				}
				if(noblockGrids.size() > 0){
					Grid grid = noblockGrids.get(RandomUtils.random(noblockGrids.size()));
					ManagerPool.monsterManager.createMonsterAndEnterMap(refreshMonster, map.getServerId(), map.getLineId(), (int)map.getId(), grid.getCenter());
				}
			}
		}
	}
	
	private Map findMap(long zoneId, int mapModelId){
		ZoneContext zcon = ManagerPool.zonesManager.getContexts().get(zoneId);
		if(zcon!=null){
			Iterator<MapConfig> iter = zcon.getConfigs().iterator();
			while (iter.hasNext()) {
				MapConfig mapConfig = (MapConfig) iter.next();
				if(mapConfig.getMapModelId()==mapModelId){
					return ManagerPool.mapManager.getMap(mapConfig.getServerId(), mapConfig.getLineId(), mapConfig.getMapId());
				}
			}
		}
		return null;
	}
	
	
	
	/**刷BOSS
	 * 
	 */
	public void refreshboss(){
		if(getISOPEN()==0){
			return;
		}
		if (zonelist.size() == 0) {
			return;
		}
		int floor = RandomUtils.random(ZONEMAPIDLIST.length);
		Map map = findMap(zonelist.get(0).getId(), ZONEMAPIDLIST[floor]);
		if (map != null) {
			Position position = MapUtils.getMapRandPoint(map.getMapModelid());
			int bossid = monsterboss[ RandomUtils.random(monsterboss.length)];
			String nameString = ManagerPool.monsterManager.getName(bossid);
			MessageUtil.notify_All_player(Notifys.CHAT_BULL, ResManager.getInstance().getString("BOSS【{1}】正在迷宫{2}层散步中...."), nameString, (floor + 1) +"");
			ManagerPool.monsterManager.createMonsterAndEnterMap(bossid, map.getServerId(), map.getLineId(), (int)map.getId(), position);
		}
	}
	
	
	
	

	
	
	
	
}

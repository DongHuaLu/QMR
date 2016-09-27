package com.game.npc.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.config.Config;
import com.game.data.bean.Q_npcBean;
import com.game.data.manager.DataManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.manager.MapManager;
import com.game.map.message.ResRoundNpcDisappearMessage;
import com.game.map.message.ResRoundNpcMessage;
import com.game.map.message.ResRoundObjectsMessage;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.npc.bean.ServiceInfo;
import com.game.npc.message.ResNpcServicesMessage;
import com.game.npc.message.ResStartGatherMessage;
import com.game.npc.message.ResStopGatherMessage;
import com.game.npc.script.INpcApplyServicesScript;
import com.game.npc.script.INpcDefaultActionScript;
import com.game.npc.script.INpcGatherActionScript;
import com.game.npc.script.INpcServiceScript;
import com.game.npc.script.INpcWelcomeActionScript;
import com.game.npc.struts.NPC;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.structs.Grid;
import com.game.structs.Position;
import com.game.structs.Reasons;
import com.game.utils.Global;
import com.game.utils.MapUtils;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;

public class NpcManager {
	private Logger log = Logger.getLogger(NpcManager.class);
	
	private static NpcManager instance=new NpcManager();
	
	public static NpcManager getInstance(){
		return instance;
	}
	
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(NpcManager.class);

	public static final byte FUNCTION_TASK=1;//任务
	public static final byte FUNCTION_SHOP=2;//商店
	public static final byte FUNCTION_TRANS=3;//传送
	public static final byte FUNCTION_FORG=4;//煅造
	public static final byte FUNCTION_STORE=5;//仓库
	
	public static final byte FUNCTION_SCRIPT=20;//脚本

	/**
	 * 检查NPC是否有指定的功能
	 * @param npcid
	 * @param function
	 * @return
	 */
	public static boolean checkFunction(int npcid,byte function){
		Q_npcBean npc = ManagerPool.dataManager.q_npcContainer.getMap().get(npcid);
		if(npc==null) return false;
		String q_function = npc.getQ_function();
		if(q_function!=null&&!"".equals(q_function)){
			String[] split = q_function.split(Symbol.FENHAO_REG);
			if(split!=null&&split.length>0){
				for (String string : split) {
					String[] split2 = string.split(Symbol.SHUXIAN_REG);
					if(split2!=null&&split2.length>0){
						int func=Integer.parseInt(split2[0]);
						if(func==function){
							return true;
						}	
					}
				}	
			}
		}
		return false;
	}
	
	/**
	 * 检查NPC是否出售该模型的物品 
	 * @param npcid
	 * @param shopModelId
	 * @return
	 */
	public static boolean checkShop(int npcid,int shopModelId){
		Q_npcBean npc = ManagerPool.dataManager.q_npcContainer.getMap().get(npcid);
		if(npc==null) return false;
		if(npc.getQ_shop()!=shopModelId) return false;
		return true;
	}
	
	/**
	 * 检查与NPC的距离
	 * @param npcid
	 * @param pos
	 * @return
	 */
	public static boolean checkDistance(int npcid,Position pos){
		Q_npcBean npc = ManagerPool.dataManager.q_npcContainer.getMap().get(npcid);
		if(npc==null) return false;
		double countDistance = MapUtils.countDistance(MapUtils.buildPosition((short)(npc.getQ_x()*MapUtils.GRID_BORDER),(short)(npc.getQ_y()*MapUtils.GRID_BORDER)),pos);
		if(countDistance>Global.NPC_TRADE_DISTANCE){
			return false;
		}
		return true;
	}
	
	/**
	 * 检查与NPC的距离
	 * @param npcid
	 * @param pos
	 * @return
	 */
	public static boolean checkDistance(NPC npc, Position pos){
		double countDistance = MapUtils.countDistance(npc.getPosition(), pos);
		if(countDistance>Global.NPC_TRADE_DISTANCE){
			return false;
		}
		return true;
	}
	
	/**
	 * NPC传送
	 * @param player
	 * @param npcid
	 * @param mapId
	 * @param pos
	 */
	public static void npcTrans(Player player,int npcid,int mapId,Position pos){
		if(!ManagerPool.mapManager.ischangMap(player)){
			return;
		}
		
		Q_npcBean npcModel = DataManager.getInstance().q_npcContainer.getMap().get(npcid);
		if(npcModel==null){
			return;
		}
		Position npcpos=new Position((short)(npcModel.getQ_x()*MapUtils.GRID_BORDER),(short)(npcModel.getQ_y()*MapUtils.GRID_BORDER));
		double countDistance = MapUtils.countDistance(npcpos,player.getPosition());
		if(countDistance>Global.NPC_TRADE_DISTANCE){
			MessageUtil.notify_player(player, Notifys.ERROR,ResManager.getInstance().getString("操作失败，与NPC的距离过远"));
			return;
		}
				
		String transfer = npcModel.getQ_transfer();
		if(transfer==null||transfer.equals("")){
			return;
		}
		long action =Config.getId();
		String[] split = transfer.split(Symbol.FENHAO_REG);
//		Gold gold = BackpackManager.getInstance().getGold(player);
		for (String string : split) {
			if(StringUtils.isBlank(string)){
				continue;
			}
			String[] item = string.split(Symbol.SHUXIAN_REG);
			String target=item[1];
			String[] targetsplit = target.split(Symbol.DOUHAO_REG);
			int map=Integer.parseInt(targetsplit[0]);
			int x=Integer.parseInt(targetsplit[1]);
			int y=Integer.parseInt(targetsplit[2]);
			int radius=Integer.parseInt(targetsplit[3]);
//			NPC传送序列（文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格;文字描述|MAPid,X,Y,半径|等级|元宝=Y金币=G|价格）
			Position targetpos=new Position((short)(x*MapUtils.GRID_BORDER),(short)(y*MapUtils.GRID_BORDER));
			if(map==mapId&&pos.getX()==targetpos.getX()&&pos.getY()==targetpos.getY()){
				int needgrade=Integer.parseInt(item[2]);
				int needgold=0;
				int needcopper=0;
				if(needgrade>player.getLevel()){
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("进入该地图的等级不足"));
					return;
				}
				//金币
				if(item[3].equalsIgnoreCase("G")){
					needcopper=Integer.parseInt(item[4]);
					if(player.getMoney()<needcopper){
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("铜币不足"));	
						return;
					}
					BackpackManager.getInstance().changeMoney(player, -needcopper,Reasons.NPCTRANS,action);
				}
				//元宝
				else if(item[3].equalsIgnoreCase("Y")){
					needgold=Integer.parseInt(item[4]);
					if(!BackpackManager.getInstance().checkGold(player, needgold)){
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("元宝不足"));
						return;
					}
					BackpackManager.getInstance().changeGold(player,-needgold,Reasons.YBNPCTRANS,action);
				}
				List<Grid> roundNoBlockGrid = MapUtils.getRoundNoBlockGrid(targetpos, radius, mapId);
				if(roundNoBlockGrid==null||roundNoBlockGrid.size()<=0){
					logger.error("策划数据有问题，落点周围找不到可落脚的点");
					return;
				}
				Grid grid = roundNoBlockGrid.get(RandomUtils.random(roundNoBlockGrid.size()));
				MapManager.getInstance().changeMap(player, mapId, mapId, 0, grid.getCenter(), NpcManager.getInstance().getClass().getName() + ".npcTrans");
			}
		}
	}

	public void initSceneNpc(int serverId, int lineId, int mapId, int mapModelId) {
		// 获得怪物所在地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		if (map == null) {
			return;
		}
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		List<Q_npcBean> list = ManagerPool.dataManager.q_npcContainer.getList();
		if(list!=null){
			for (Q_npcBean npcmodel : list) {
				if(mapModelId!=npcmodel.getQ_map()){
					//不该刷在本地图
					continue;
				}
				NPC npc = new NPC();
				npc.setId(Config.getId());
				npc.setMap(mapId);
				npc.setMapModelId(mapModelId);
				npc.setModelId(npcmodel.getQ_id());
				npc.setServerId(serverId);
				npc.setLine(lineId);
				if(npcmodel.getQ_px()>0 && npcmodel.getQ_py()>0){
					npc.setPosition(new Position((short)npcmodel.getQ_px(), (short)npcmodel.getQ_py()));
				}else{
					Grid grid = MapUtils.getGrid(npcmodel.getQ_x(), npcmodel.getQ_y(), grids);
					if(grid==null){
						continue;
					}
					npc.setPosition(grid.getCenter());
				}
				if(npcmodel.getQ_isvisible()==0){
					npc.setShow(true);
				}else{
					npc.setShow(false);
				}
				Area area = MapManager.getInstance().getArea(npc.getPosition(), map);
				
				area.getNpcs().put(npc.getId(),npc);
//				if(mapModelId==20001){
//					logger.info(MapManager.getInstance().getAreaId(npc.getPosition())+" "+npc.getModelId());
//				}
			}
		}		
	}

	
	//获取npc服务列表
	public void getNpcServices(Player player, long npcid){
		Map map = ManagerPool.mapManager.getMap(player);
		if(map==null){
			//地图不存在
			return;
		}
		NPC npc = findNpc(map, npcid);
		if(npc==null){
			//npc不存在
			return;
		}
		List<ServiceInfo> infos = new ArrayList<ServiceInfo>();
		
		Q_npcBean npcBean = ManagerPool.dataManager.q_npcContainer.getMap().get(npc.getModelId());
		if(npcBean!=null){
			int welcome = npcBean.getQ_clickplay();
			if(welcome > 0){
				INpcWelcomeActionScript script = (INpcWelcomeActionScript) ManagerPool.scriptManager.getScript(welcome);
				if (script != null) {
					try {
						script.welcome(player, npc);
					} catch (Exception e) {
						log.error(e, e);
					}
				}
			}
			int behavior = npcBean.getQ_behavior();
			if(behavior > 0){
				INpcDefaultActionScript script = (INpcDefaultActionScript) ManagerPool.scriptManager.getScript(behavior);
				if (script != null) {
					try {
						script.defaultAction(player, npc);
					} catch (Exception e) {
						log.error(e, e);
					}
				}
				return;
			}
			String q_function = npcBean.getQ_function();
			if(q_function!=null&&!"".equals(q_function)){
				String[] split = q_function.split(Symbol.FENHAO_REG);
				if(split!=null&&split.length>0){
					for (String string : split) {
						ServiceInfo info = new ServiceInfo();
						String[] split2 = string.split(Symbol.SHUXIAN_REG);
						int func=Integer.parseInt(split2[0]);
						info.setServiceId(func);
						info.setServiceName(split2[1]);
						infos.add(info);
					}	
				}
			}
		}
		
		INpcApplyServicesScript script = (INpcApplyServicesScript) ManagerPool.scriptManager.getScript(ScriptEnum.NPC_SERVICES);
		if (script != null) {
			try {
				script.applyServices(player, npc, infos);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("npc服务列表脚本不存在！");
		}
		
		ResNpcServicesMessage msg = new ResNpcServicesMessage();
		msg.setNpcId(npcid);
		msg.setServices(infos);
		MessageUtil.tell_player_message(player, msg);
	}
	
	/**
	 * 获取npc
	 * @param player
	 * @param npcModelId
	 * @return
	 */
	public List<NPC> findNpc(Map map, int npcModelId){
		List<NPC> npcs = new ArrayList<NPC>();
		if(map!=null){
			Iterator<Area> iter = map.getAreas().values().iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				Iterator<NPC> niter = area.getNpcs().values().iterator();
				while (niter.hasNext()) {
					NPC npc = (NPC) niter.next();
					if(npc.getModelId()==npcModelId){
						npcs.add(npc);
					}
				}
			}
		}
		return npcs;
	}
	
	/**
	 * 获取npc
	 * @param player
	 * @param npcModelId
	 * @return
	 */
	public List<NPC> findNpc(Player player, int npcModelId){
		List<NPC> npcs = new ArrayList<NPC>();
		Map map = ManagerPool.mapManager.getMap(player);
		if(map!=null){
			Iterator<Area> iter = map.getAreas().values().iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				Iterator<NPC> niter = area.getNpcs().values().iterator();
				while (niter.hasNext()) {
					NPC npc = (NPC) niter.next();
					if(npc.getModelId()==npcModelId){
						npcs.add(npc);
					}
				}
			}
		}
		return npcs;
	}
	
	/**
	 * 获取npc
	 * @param map
	 * @param npcid
	 * @return
	 */
	public NPC findNpc(Map map, long npcid){
		Iterator<Area> iter = map.getAreas().values().iterator();
		while (iter.hasNext()) {
			Area area = (Area) iter.next();
			if(area.getNpcs().containsKey(npcid)){
				return area.getNpcs().get(npcid);
			}
		}
		return null;
	}
	
	/**
	 * 获取npc
	 * @param player
	 * @param npcModelId
	 * @return
	 */
	public NPC findNpc(Player player, long npcId){
		Map map = ManagerPool.mapManager.getMap(player);
		if(map!=null){
			Iterator<Area> iter = map.getAreas().values().iterator();
			while (iter.hasNext()) {
				Area area = (Area) iter.next();
				Iterator<NPC> niter = area.getNpcs().values().iterator();
				while (niter.hasNext()) {
					NPC npc = (NPC) niter.next();
					if(npc.getId()==npcId) return npc;
				}
			}
		}
		return null;
	}
	
	/**
	 * 请求npc脚本服务
	 * @param player 玩家
	 * @param npcid npc id
	 * @param parameters 参数
	 */
	public void reqNpcService(Player player, long npcid, String parameters){
		Map map = ManagerPool.mapManager.getMap(player);
		if(map==null){
			//地图不存在
			return;
		}
		NPC npc = findNpc(map, npcid);
		if(npc==null){
			//npc不存在
			return;
		}
		
		INpcServiceScript script = (INpcServiceScript) ManagerPool.scriptManager.getScript(ScriptEnum.NPC_ACTION);
		if (script != null) {
			try {
				script.reqService(player, npc, parameters);
			} catch (Exception e) {
				log.error(e, e);
			}
		} else {
			log.error("npc服务脚本不存在！");
		}
	}
	
	/**
	 * 获取周围npc对玩家显示隐藏
	 * @param player
	 * @return
	 */
	public HashMap<Long, Boolean> getNpcHide(Player player){
		HashMap<Long, Boolean> npcs = new HashMap<Long, Boolean>();
		
		Map map = ManagerPool.mapManager.getMap(player);
		Iterator<Area> iter = ManagerPool.mapManager.getRound(map, player.getPosition()).iterator();
		while (iter.hasNext()) {
			Area area = (Area) iter.next();
			Iterator<NPC> niter = area.getNpcs().values().iterator();
			while (niter.hasNext()) {
				NPC npc = (NPC) niter.next();
				npcs.put(npc.getId(), npc.canSee(player));
			}
		}
		
		return npcs;
	}
	
	/**
	 * 刷新周围npc显示情况
	 * @param player
	 * @param oldviews
	 */
	public void refreshNpc(Player player, HashMap<Long, Boolean> oldviews){
		Map map = ManagerPool.mapManager.getMap(player);
		
		ResRoundObjectsMessage msg = new ResRoundObjectsMessage();
		
		Iterator<Area> iter = ManagerPool.mapManager.getRound(map, player.getPosition()).iterator();
		while (iter.hasNext()) {
			Area area = (Area) iter.next();
			Iterator<NPC> niter = area.getNpcs().values().iterator();
			while (niter.hasNext()) {
				NPC npc = (NPC) niter.next();
				Boolean oldview = oldviews.get(npc.getId());
				if(oldview!=null){
					boolean newview = npc.canSee(player);
					if(oldview.booleanValue() == false && newview == true){
						//npc出现
						msg.getNpcs().add(ManagerPool.mapManager.getNpcInfo(npc));
					}else if(oldview.booleanValue() == true && newview == false){
						//npc消失
						msg.getNpcid().add(npc.getId());
					}
				}
			}
		}
		
		if(msg.getNpcs().size()>0 || msg.getNpcid().size()>0) MessageUtil.tell_player_message(player, msg);
	}
	
	/**
	 * 创建npc
	 * @param modelId
	 * @param map
	 * @param show
	 * @return
	 */
	public NPC createNpc(int modelId, Map map, boolean show){
		Q_npcBean npcmodel = ManagerPool.dataManager.q_npcContainer.getMap().get(modelId);
		if(npcmodel==null){
			return null;
		}
		NPC npc = new NPC();
		npc.setId(Config.getId());
		npc.setMap((int)map.getId());
		npc.setMapModelId(map.getMapModelid());
		npc.setModelId(npcmodel.getQ_id());
		npc.setServerId(map.getServerId());
		npc.setLine(map.getLineId());
		if(npcmodel.getQ_px()>0 && npcmodel.getQ_py()>0){
			npc.setPosition(new Position((short)npcmodel.getQ_px(), (short)npcmodel.getQ_py()));
		}else if(npcmodel.getQ_x()>0 && npcmodel.getQ_y()>0){
			Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
			Grid grid = MapUtils.getGrid(npcmodel.getQ_x(), npcmodel.getQ_y(), grids);
			if(grid!=null){
				npc.setPosition(grid.getCenter());
			}
		}
		npc.setShow(show);
		
		return npc;
	}
	
	/**
	 * npc出现（对全体玩家）
	 * @param npc
	 */
	public void showNpc(NPC npc){
		npc.setShow(true);
		
		ResRoundNpcMessage msg = new ResRoundNpcMessage();
		msg.setNpc(ManagerPool.mapManager.getNpcInfo(npc));
		MessageUtil.tell_round_message(npc, msg);
	}
	
	/**
	 * npc消失(对全体玩家)
	 * @param npc
	 */
	public void  hideNpc(NPC npc){
		ResRoundNpcDisappearMessage msg = new ResRoundNpcDisappearMessage();
		msg.getNpcids().add(npc.getId());
		MessageUtil.tell_round_message(npc, msg);
		
		npc.setShow(false);
	}
	

	/**
	 * npc出现（对单个玩家）
	 * @param npc
	 */
	public void showNpc(Player player, NPC npc){
		if(npc.isShow()){
			player.getHideSet().remove(String.valueOf(npc.getModelId()));
		}else{
			player.getShowSet().add(String.valueOf(npc.getModelId()));
		}
		
		ResRoundNpcMessage msg = new ResRoundNpcMessage();
		msg.setNpc(ManagerPool.mapManager.getNpcInfo(npc));
		MessageUtil.tell_player_message(player, msg);
	}
	
	/**
	 * npc消失(对单个玩家)
	 * @param npc
	 */
	public void  hideNpc(Player player, NPC npc){
		
		if(npc.isShow()){
			player.getHideSet().add(String.valueOf(npc.getModelId()));
		}else{
			player.getShowSet().remove(String.valueOf(npc.getModelId()));
		}
		
		ResRoundNpcDisappearMessage msg = new ResRoundNpcDisappearMessage();
		msg.getNpcids().add(npc.getId());
		MessageUtil.tell_player_message(player, msg);
	}
	
	/**
	 * 玩家采集
	 *
	 * @param roleId 玩家Id
	 */
	public void playerGather(Player player, NPC npc) {
		Q_npcBean npcBean = ManagerPool.dataManager.q_npcContainer.getMap().get(npc.getModelId());
		if(npcBean==null){
			//npc模板不存在
			return;
		}
		
		if(!npc.canSee(player)){
			//npc对玩家不可见
			return;
		}
		//跳跃中不可采集
		if (PlayerState.JUMP.compare(player.getState())
			|| PlayerState.DOUBLEJUMP.compare(player.getState())) {
			return;
		}
		
		//死亡不可以采集
		if(player.isDie()){
			return;
		}
		
		//已经采集中
		if(PlayerState.GATHER.compare(player.getState())){
			if(player.getGatherId() == npc.getId()){
				return;
			}else{
				playerStopGather(player);
			}
		}	
		//停止走路
		ManagerPool.mapManager.playerStopRun(player);
		//停止打坐
		ManagerPool.dazuoManager.breakDaZuo(player);
		//停止使用内力盾
		ManagerPool.mapManager.playerStopBlock(player);
		
		player.setState(PlayerState.GATHER);
		player.setGatherId(npc.getId());
		player.setGatherStarttime(System.currentTimeMillis());
		player.setGatherCosttime(npcBean.getQ_collecttime());
		
		ResStartGatherMessage msg = new ResStartGatherMessage();
		msg.setPersonId(player.getId());
		msg.setTatget(npc.getId());
		msg.setCosttime((int)player.getGatherCosttime());
		
		MessageUtil.tell_player_message(player, msg);
	}

	/**
	 * 玩家停止采集
	 *
	 * @param roleId 玩家Id
	 */
	public void playerStopGather(Player player) {
		playerStopGather(player, false);
	}
	
	/**
	 * 玩家完成采集
	 *
	 * @param roleId 玩家Id
	 */
	public void playerFinishGather(Player player) {
		Map map = ManagerPool.mapManager.getMap(player);
		if(map==null){
			//地图不存在
			return;
		}
		NPC npc = findNpc(map, player.getGatherId());
		if(npc==null){
			//npc不存在
			return;
		}
		
		Q_npcBean npcBean = ManagerPool.dataManager.q_npcContainer.getMap().get(npc.getModelId());
		if(npcBean!=null){
			int gather = npcBean.getQ_collectscript();
			if(gather > 0){
				INpcGatherActionScript script = (INpcGatherActionScript) ManagerPool.scriptManager.getScript(gather);
				if (script != null) {
					try {
						script.gather(player, npc);
					} catch (Exception e) {
						log.error(e, e);
					}
				}
				return;
			}
		}
	}
	
	/**
	 * 玩家停止采集
	 *
	 * @param roleId 玩家Id
	 */
	public void playerStopGather(Player player, boolean stopOther) {
		if(PlayerState.GATHER.compare(player.getState())){
			player.setState(PlayerState.NOGATHER);
			
			if(stopOther){
				Map map = ManagerPool.mapManager.getMap(player);
				Iterator<Player> iter = map.getPlayers().values().iterator();
				while (iter.hasNext()) {
					Player player2 = (Player) iter.next();
					if(PlayerState.GATHER.compare(player2.getState()) && player2.getGatherId() == player.getGatherId()){
						playerStopGather(player2);
					}
				}
			}
			
			player.setGatherId(0);
			
			ResStopGatherMessage msg = new ResStopGatherMessage();
			msg.setPersonId(player.getId());
			MessageUtil.tell_player_message(player, msg);
		}
	}
	
	
	
	/**改变NPC名字
	 * 
	 */
	public void changeNpcName(NPC npc ,String newname){
		npc.setName(newname);
		hideNpc(npc);
		showNpc(npc);
	}
	


}

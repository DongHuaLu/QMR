package com.game.horse.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffConst;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_globalBean;
import com.game.data.bean.Q_horse_basicBean;
import com.game.data.bean.Q_horse_skill_expBean;
import com.game.data.bean.Q_horse_skillsBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_mapBean;
import com.game.data.bean.Q_shopBean;
import com.game.dazuo.manager.PlayerDaZuoManager;
import com.game.dblog.LogService;
import com.game.fight.structs.FighterState;
import com.game.horse.bean.HorseInfo;
import com.game.horse.bean.HorseSkillInfo;
import com.game.horse.bean.OthersHorseInfo;
import com.game.horse.log.HorseLog;
import com.game.horse.log.HorseSkillLog;
import com.game.horse.message.ReqChangeHorseMessage;
import com.game.horse.message.ReqGethorseInfoMessage;
import com.game.horse.message.ReqSkillInfoMessage;
import com.game.horse.message.ReqhorseClearCDMessage;
import com.game.horse.message.ReqhorseLuckyPenteMessage;
import com.game.horse.message.ReqhorseLuckyRodMessage;
import com.game.horse.message.ReqhorseReceiveMessage;
import com.game.horse.message.ReqhorseStageUpPanelMessage;
import com.game.horse.message.ReqhorseStageUpStartMessage;
import com.game.horse.message.ResHorseInfoMessage;
import com.game.horse.message.ResOpenSkillUpPanelMessage;
import com.game.horse.message.ResOthersHorseInfoMessage;
import com.game.horse.message.ResRidingCountdownMessage;
import com.game.horse.message.ResRidingStateMessage;
import com.game.horse.message.ResSkillInfoMessage;
import com.game.horse.message.ReshorseCDTimeMessage;
import com.game.horse.message.ReshorseErrInfoMessage;
import com.game.horse.message.ReshorseLuckyPenteMessage;
import com.game.horse.message.ReshorseLuckyRodMessage;
import com.game.horse.message.ReshorseReceiveTimerMessage;
import com.game.horse.message.ReshorseStageUpPanelMessage;
import com.game.horse.message.ReshorseStageUpResultMessage;
import com.game.horse.script.IHorseUpScript;
import com.game.horse.struts.Horse;
import com.game.horse.struts.HorseSkill;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.player.manager.PlayerManager;
import com.game.player.message.ReqSyncPlayerHorseMessage;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
import com.game.skill.structs.Skill;
import com.game.structs.Reasons;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

public class HorseManager {
	private static Logger logx = Logger.getLogger(HorseManager.class);
	private static final byte RIDE=1;	//骑乘
	private static final byte UNRIDE=0;	//未骑乘
	
	
	private static final byte RIDE_TIME=5;	//骑乘需要的时间(秒)

	private static  short LAYER_MAX=10;	//目前开放的最高阶数

	
	
	private static Object obj = new Object();

	//玩家管理类实例
	private static HorseManager manager;
	public static HorseManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new HorseManager();
			}
		}
		return manager;
	}
	
	private HorseManager(){}
	
	
	/**目前开放的最高技能上限
	 * 
	 * @return
	 */
	public int getskillmax(){
		Q_horse_basicBean horsedata = getDBHorseBasic(LAYER_MAX);
		if (horsedata != null) {
			return horsedata.getQ_skill_level_max();
		}
		return 0;
	}
	
	
	
	
	
	/**得到数据库里的本阶坐骑基本数据
	 * 
	 * @param idx
	 * @return
	 */
	public Q_horse_basicBean getDBHorseBasic(int idx){
		Q_horse_basicBean horsedata = ManagerPool.dataManager.q_horse_basicContainer.getMap().get(idx);
		return horsedata;
	}

	/**得到数据库内的坐骑技能信息
	 * 
	 * @param skillid
	 * @return
	 */
	public Q_horse_skillsBean  getDBHorseDataSkill(int skillid){
		Q_horse_skillsBean skillModel = ManagerPool.dataManager.q_horse_skillsContainer.getMap().get(skillid);
		return skillModel;
	}

	/**坐骑技能升级操作开放所需坐骑阶数
	 * 
	 * @param 
	 * @return
	 */
	public int getHorseOpenSkillLv(){
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(74);
		return global.getQ_int_value();
	}
	
	/**
	 * 每轮允许摇动坐骑技能宝盒次数
	 * @return
	 */
	
	public int getHorseSkillUpNumMax(){
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(75);
		return global.getQ_int_value();
	}
	
	
	/**
	 * 坐骑技能经验消耗金币衰减系数
	 * @return
	 */
	
	public int getGoldModulus(){
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(76);
		return global.getQ_int_value();
	}
	
	
	
	/**
	 * 坐骑技能经验消耗元宝衰减系数
	 * @return
	 */
	
	public int getYuanBaoModulus(){
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(77);
		return global.getQ_int_value();
	}
	
	/**
	 * 每日清除坐骑升级经验冷却次数所需消耗元宝数
	 */
	public int getClearCDYuanBaoNum(){
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(78);
		return global.getQ_int_value();
	}
	
	/**清除坐骑升级经验冷却次数所需消耗元宝数倍率
	 * 
	 */
	public int getClearCDYuanBaoModulus(){
		Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(79);
		return global.getQ_int_value();
	}
	
	
	/**得到第一个坐骑
	 * 
	 * @param player
	 */
	public Horse getHorse(Player player ){
		List<Horse> horselist = player.getHorselist();
		if (horselist.size() == 0) {
			horselist.add(new Horse());
		}
		return horselist.get(0);
	}
	
	/**改变骑乘状态
	 * 
	 * @param player
	 * @param status	1骑乘，0下马
	 * @param currency
	 */
	public void  stChangeRidingState( Player player ,  byte status ,  short currency  ){
		Horse horse = getHorse(player);
		if (horse != null) {
			if (status == 2) {	//跨地图自动下马
				unride(player);
				return;
			}
			if ( horse.getStatus() == UNRIDE) {	//如果未骑乘，则上马
				if(FighterState.JINZHIQICHENG.compare(player.getFightState())) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前为禁止骑乘状态。"));
					return;
				}
				
				//停止采集
				ManagerPool.npcManager.playerStopGather(player);
				
				if (horse.getLayer() == 0) {
					return;
				}
				if (player.isDie()) return;

				Q_mapBean mapmodel = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
				if (mapmodel.getQ_map_ride() == 1) {//检测地图是否可骑乘
					if (currency <= horse.getLayer()) {
						horse.setCurlayer(currency);
					}
					
					ResRidingCountdownMessage smsg = new ResRidingCountdownMessage();
					smsg.setTime(RIDE_TIME);
					MessageUtil.tell_player_message(player, smsg);	
					player.setRidingtime(player.getAccunonlinetime());	//记录当前累计时间，，5秒后发送骑乘
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，本地图禁止骑乘。"));
				}
			}else {			
				unride(player);
			}
		}
	}
	

	/**倒计时结束，骑上马
	 * 
	 * @param player
	 */
	public void rideHorse(Player player) {
		if (player != null) {		
			if (player.isDie()) return;
			if(FighterState.JINZHIQICHENG.compare(player.getFightState())) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前为禁止骑乘状态。"));
				return;
			}
			Horse horse = getHorse(player);
			if (horse.getStatus() == RIDE ) {
				return;// 以经骑上了
			}
			autorideHorse(player,horse.getCurlayer());	//上马
			
		}
	}
	
	/**
	 * 是否正在骑马中
	 * @param player
	 * @return
	 */
	public boolean isRidding(Player player){
		Horse horse = getHorse(player);
		if (horse != null && horse.getLayer() > 0) {
			if(horse.getStatus() == UNRIDE){
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
	
	
//	/**自动骑上马
//	 * 
//	 * @param player
//	 * @param cur  0=自动选择最高阶马
//	 */
//	
//	private void autorideHorse(Player player,int cur,int mapid,int line) {
//		// 游泳中
//		if (PlayerState.SWIM.compare(player.getState())) {
//			MessageUtil.notify_player(player, Notifys.ERROR, "游泳区内无法骑马");
//			return;
//		}
//		Horse horse = getHorse(player);
//		Q_mapBean mapmodel = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
//		if (mapmodel != null && mapmodel.getQ_map_ride() == 1) {//检测地图是否可骑乘
//			ResRidingStateMessage smsg = new ResRidingStateMessage();
//			if (horse.getLayer() >= cur ) {
//				if (cur == 0) {
//					horse.setCurlayer(horse.getLayer());	//骑最高阶马
//					smsg.setCurlayer(horse.getLayer());
//				}else {
//					horse.setCurlayer((short) cur);	//骑马
//					smsg.setCurlayer((short) cur);
//				}
//				horse.setStatus(RIDE);
//				smsg.setStatus(RIDE);
//				smsg.setPlayerid(player.getId());
//		
//				PlayerDaZuoManager.getInstacne().breakDaZuo(player);
//				MessageUtil.tell_round_message(player, smsg);
//				//重新计算属性
//				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE);
//				ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_EQUIP);
//				ManagerPool.playerManager.stSyncExterior(player);
//			}
//		}else {
//			MessageUtil.notify_player(player, Notifys.ERROR, "很抱歉，本地图禁止骑乘。");
//		}
//	}
//
//	
	
	/**自动骑上马
	 * 
	 * @param player
	 * @param cur  0=自动选择最高阶马
	 */
	
	private void autorideHorse(Player player,int cur) {
		// 游泳中
		if (PlayerState.SWIM.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("游泳区内无法骑马"));
			return;
		}
		
		if(FighterState.JINZHIQICHENG.compare(player.getFightState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("当前为禁止骑乘状态。"));
			return;
		}
		
		Horse horse = getHorse(player);
		Q_mapBean mapmodel = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
		if (mapmodel != null && mapmodel.getQ_map_ride() == 1) {//检测地图是否可骑乘
			ResRidingStateMessage smsg = new ResRidingStateMessage();
			if (horse.getLayer() >= cur ) {
				if (cur == 0) {
					horse.setCurlayer(horse.getLayer());	//骑最高阶马
					smsg.setCurlayer(horse.getLayer());
				}else {
					horse.setCurlayer((short) cur);	//骑马
					smsg.setCurlayer((short) cur);
				}
				horse.setStatus(RIDE);
				smsg.setStatus(RIDE);
				smsg.setPlayerid(player.getId());
				Map map = ManagerPool.mapManager.getMap(player);
				if (map != null) {
					PlayerDaZuoManager.getInstacne().breakDaZuo(player);
					MessageUtil.tell_round_message(player, smsg);
					//重新计算属性
					ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE);
					ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_EQUIP);
					ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_WEAPON);
					ManagerPool.playerManager.stSyncExterior(player);
				}

			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，本地图禁止骑乘。"));
		}
	}
	
	/**下马
	 * 
	 * @param player
	 */
	public void unride(Player player){
		Horse horse = getHorse(player);
		if (horse != null && horse.getLayer() > 0) {
			horse.setStatus(UNRIDE);		//下马
			
			ResRidingStateMessage smsg = new ResRidingStateMessage();
			smsg.setStatus(UNRIDE);
			smsg.setPlayerid(player.getId());
			MessageUtil.tell_round_message(player, smsg);
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE);
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_EQUIP);
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_WEAPON);
			ManagerPool.playerManager.stSyncExterior(player);
			Q_mapBean mapmodel = ManagerPool.dataManager.q_mapContainer.getMap().get(player.getMapModelId());
			if (mapmodel.getQ_map_ride() == 0) {//检测地图是否可骑乘
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("本地图禁止骑乘。"));
			}
		}
	}
	
	
	
	/**获取玩家当前骑乘状态 0未骑乘， 1已骑马
	 * 
	 * @param player
	 * @return 
	 */
	public byte getHorseStatus(Player player){
		Horse horse = getHorse(player);
		if (horse != null) {
			return horse.getStatus();
		}
		return 0;
	}
	
	

	/**发送坐骑信息(打开面板或者其他)
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqGethorseInfoMessage(Player player ,ReqGethorseInfoMessage msg) {
		stResHorseInfo(player);
	}
	
	
	/**发送坐骑信息
	 * 
	 * @param player
	 */
	public void stResHorseInfo(Player player) {
		ResHorseInfoMessage smsg = new ResHorseInfoMessage();
		Horse horse = getHorse(player);
		if (horse != null) {
			horse.ClearDay();	//打开的时候清理每日数据
			smsg.setHorseinfo(horse.CreateHorseInfo(player));
		}
		MessageUtil.tell_player_message(player, smsg);
	}

	

	
	/**测试用GM指令
	 * 
	 * @param player
	 * @param msg
	 */
	public void testHorse(Player player ,int id) {
		if (id > LAYER_MAX) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("ID超出范围（1-10）"));	
			return;
		}
		Horse horse = getHorse(player);
		if (horse != null) {
			horse.putskill();
			horse.setLayer((short) id);
			horse.setCurlayer((short)id);
			horse.setNewhorse((byte) 2);
//			ReshorseReceiveTimerMessage smsg = new ReshorseReceiveTimerMessage();
//			smsg.setType((byte) 2);	//发送已领取
//			MessageUtil.tell_player_message(player, smsg);
			unride(player);
			stResHorseInfo(player);
			autorideHorse(player,0);
		}
	}
	
	/**测试用GM指令
	 * 
	 * @param player
	 * @param skillid
	 * @param lv
	 */
	public void testHorseskill(Player player ,int skillid,int lv){
		Horse horse = getHorse(player);
		List<HorseSkill> skills=horse.getSkills();
		for (HorseSkill horseSkill : skills) {
			if (skillid == horseSkill.getSkillModelId()) {
				horseSkill.setSkillLevel(lv);
				stResHorseInfo(player);
				Q_horse_skillsBean skillsBean = getDBHorseDataSkill(horseSkill.getSkillModelId());
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您，坐骑技能『{1}』升级了。"),skillsBean.getQ_skill_name());
				break;
			}
		}
	}
	
	
	
	/**登录加载坐骑信息
	 * 
	 * @param player
	 */
	public void  LoginLoadHorse(Player player){
		Horse horse = getHorse(player);
		if (horse.getLayer() > 0) {
			stResHorseInfo(player);
		}else {
			isGiveNewHorse(player);
		}
	}

	/**是否可领取坐骑（发送通知前端）
	 * 
	 * @param player
	 */
	public void  isGiveNewHorse(Player player) {
		Horse horse = getHorse(player);
		if (horse.getLayer() == 0) {	
			Q_globalBean global = ManagerPool.dataManager.q_globalContainer.getMap().get(73);
			int ms = global.getQ_int_value();
			ReshorseReceiveTimerMessage smsg = new ReshorseReceiveTimerMessage();
			if (ms >= player.getAccunonlinetime()) {
				smsg.setSecond(ms-player.getAccunonlinetime());
				smsg.setType((byte) 0);
				smsg.setSecondsum(ms);
				MessageUtil.tell_player_message(player, smsg);		//继续倒计时
			}else {
				smsg.setType((byte) 1);
				MessageUtil.tell_player_message(player, smsg);		//可领取
			}
		}
	}
	

	/**
	 * 新手领取坐骑
	 * @param player
	 * @param msg
	 */
	public void stReqhorseReceiveMessage(Player player,ReqhorseReceiveMessage msg) {
		Horse horse = getHorse(player);
		if (horse != null) {
			if (horse.getLayer() == 0) {
				horse.setLayer((short) 1);
				horse.setNewhorse((byte) 2);
				horse.putskill();
				PlayerManager.getInstance().savePlayer(player);
				ReshorseReceiveTimerMessage smsg = new ReshorseReceiveTimerMessage();
				smsg.setType((byte) 2);	//发送已领取
				MessageUtil.tell_player_message(player, smsg);
				stResHorseInfo(player);
			}
		}
	}

	
	
	/**
	 * 新手任务完成后给坐骑
	 * @param player
	 * @param msg
	 */
	public void giveNewHorse(Player player) {
		Horse horse = getHorse(player);
		if (horse != null) {
			if (horse.getLayer() == 0) {
				horse.setLayer((short) 1);
				horse.setNewhorse((byte) 2);
				horse.putskill();
				PlayerManager.getInstance().savePlayer(player);
				stResHorseInfo(player);
				try {
					HorseLog log = new HorseLog();
					log.setLayer(1);
					log.setPlayerId(player.getId());
					log.setIsupsuccess(1);
					log.setSid(player.getCreateServerId());
					LogService.getInstance().execute(log);
				} catch (Exception e) {

				}

			}
		}
	}
	
	
	
	
	/**打开坐骑升阶面板
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqhorseStageUpPanelMessage(Player player,ReqhorseStageUpPanelMessage msg) {
		Horse horse =  getHorse(player);
		if (horse != null) {
			if (horse.getLayer() >= LAYER_MAX ) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("已是最高阶坐骑，暂时无法继续进阶."));
				return;
			}
			int nextLayer = horse.getLayer() + 1;
			Q_horse_basicBean horseBasic = getDBHorseBasic(nextLayer);
			if (horseBasic != null) {
				horse.CreateHorseInfo(player);
				ReshorseStageUpPanelMessage smsg = new ReshorseStageUpPanelMessage();
				smsg.setGold(horseBasic.getQ_up_gold_num());
				MessageUtil.tell_player_message(player, smsg);
			}
		}
	}
	
	
	
	public int  getItemYuanbao(int id) {
		HashMap<Integer, Q_shopBean> shopBean = ManagerPool.dataManager.q_shopContainer.getMap();
		Iterator<Entry<Integer, Q_shopBean>> shop = shopBean.entrySet().iterator();
		while (shop.hasNext()) {
			Entry<Integer, Q_shopBean> entry = shop.next();
			if (entry.getValue().getQ_sell() == id) {
				if (entry.getValue().getQ_gold() > 0) {
					return entry.getValue().getQ_gold();
				}
			}
		}
		return 0;
	}
	
	
	

	/**开始坐骑升阶
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqhorseStageUpStartMessage(Player player,ReqhorseStageUpStartMessage msg) {
		if(PlayerState.DIE.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("处于死亡状态，无法进行坐骑升阶。"));
			return;
		}
		boolean ck = false;	//是否存符合完美进阶丹使用规则
		
		Horse horse =  getHorse(player);
		if (horse != null) {
			long action=Config.getId();
			if (horse.getLayer() >= LAYER_MAX ) {
				return;
			}
			int nextLayer = horse.getLayer() + 1;
			Q_horse_basicBean horseBasic = getDBHorseBasic(nextLayer);
			Q_horse_basicBean oldBasic = getDBHorseBasic(horse.getLayer());
			if (horseBasic != null || oldBasic != null) {
				if(oldBasic.getQ_up_figure_level() <=  player.getLevel()){
					Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(oldBasic.getQ_up_item_id());
					int itemnum  = ManagerPool.backpackManager.getItemNum(player,oldBasic.getQ_up_item_id());	//得到包裹道具
					//判断升阶铜币是否足够
					boolean moneyenough = true; // 铜币是否够升阶
					int goldtong = player.getMoney();	//身上铜币
//					int danjia = 0;
					List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, BuffConst.HORSE_MONEY_BUFF);
					List<Buff> buffsfor = ManagerPool.buffManager.getBuffByModelId(player, BuffConst.HORSE_FORMONEY_BUFF);					
					int money=oldBasic.getQ_up_gold_num();
					if (buffs.size() > 0 || buffsfor.size() > 0) {
						money = 0;
					}
					if ( goldtong < money) { //铜币不足
						moneyenough = false; //铜币不足标记 并不立刻返回 如果满足完美坐骑丹的条件 也可继续升阶
					}
					HorseLog log = new HorseLog();
					log.setYblessvalue(horse.getDayblessvalue());
					log.setItemnum(oldBasic.getQ_up_item_num());
					log.setItemid(oldBasic.getQ_up_item_id());
					ReshorseErrInfoMessage errmsg =new ReshorseErrInfoMessage();
//					Gold gold = player.getGold();
					if(msg.getType() == 1){	//自动购买检查
//						danjia = getItemYuanbao(oldBasic.getQ_up_item_id());	//道具元宝单价
//						if (danjia < 1) {
//							MessageUtil.notify_player(player, Notifys.ERROR, "{1}价格异常，无法自动购买.",itemBean.getQ_name());	
//							return;
//						}
//						if (itemnum < oldBasic.getQ_up_item_num() ) {
//							int shengyu = oldBasic.getQ_up_item_num() - itemnum;
//							int ybsum = danjia*shengyu;//需要的元宝数量
//							if( gold == null || gold.getGold() < ybsum){
//								errmsg.setType((byte) 1);
//								errmsg.setErrint(ybsum);
//								MessageUtil.tell_player_message(player, errmsg);
//								MessageUtil.notify_player(player, Notifys.ERROR, "升阶停止，需要：{1}元宝才能自动购买到升阶所需的材料.",String.valueOf(ybsum));	
//								return;
//							}
//						}
						if (horse.getLayer() >= 7) {
							int wmitemnum  = ManagerPool.backpackManager.getItemNum(player,9124);	//完美坐骑进阶丹
							if (wmitemnum >= 1) {
								ck =true;
							}
						}
						
					}else{	//手动升阶，检查
						
						if (horse.getLayer() >= 7) {
							int wmitemnum  = ManagerPool.backpackManager.getItemNum(player,9124);	//完美坐骑进阶丹
							if (wmitemnum >= 1) {
								ck =true;
							}
						}
						
						if ( ck == false) {
							if (itemnum < oldBasic.getQ_up_item_num()) {
								errmsg.setType((byte) 2);
								MessageUtil.tell_player_message(player, errmsg);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要{1}[{2}颗]，才可继续进阶."),itemBean.getQ_name(),String.valueOf(oldBasic.getQ_up_item_num()));	
								return;
							}
						}
					}

					// 铜币是否足够 -检查
					if(!ck && !moneyenough){ //不满足用完美坐骑丹条件 并且 铜币不足 给予提示并且返回
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要铜币：{1}，才可继续进阶."),String.valueOf(money));	
						return;
					}
					
					if(msg.getType() == 1){	//自动购买-执行
						if (horse.getLayer() >= 7) {
							if(ManagerPool.backpackManager.removeItem(player,9124,1,Reasons.HORSE_UP,action)==false){
								if(ManagerPool.shopManager.autoRemove(player, oldBasic.getQ_up_item_id(), oldBasic.getQ_up_item_num(), Reasons.HORSE_UP, action)==false){
									errmsg.setType((byte) 2);
									MessageUtil.tell_player_message(player, errmsg);
									MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要{1}[{2}颗]，才可继续进阶."),itemBean.getQ_name(),String.valueOf(oldBasic.getQ_up_item_num()));
									return;
								}
							}else {
								money = 0;
							}
						}else{
							if(ManagerPool.shopManager.autoRemove(player, oldBasic.getQ_up_item_id(), oldBasic.getQ_up_item_num(), Reasons.HORSE_UP, action)==false){
								errmsg.setType((byte) 2);
								MessageUtil.tell_player_message(player, errmsg);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要{1}[{2}颗]，才可继续进阶."),itemBean.getQ_name(),String.valueOf(oldBasic.getQ_up_item_num()));
								return;
							}
						}
					}else {//手动
						if (horse.getLayer() >= 7) {
							if(ManagerPool.backpackManager.removeItem(player,9124,1,Reasons.HORSE_UP,action)==false){
								if(ManagerPool.backpackManager.removeItem(player,oldBasic.getQ_up_item_id(),oldBasic.getQ_up_item_num(),Reasons.HORSE_UP,action)==false){
									return;
								}
							}else{
								money = 0;
							}	
						}else{
							if(ManagerPool.backpackManager.removeItem(player,oldBasic.getQ_up_item_id(),oldBasic.getQ_up_item_num(),Reasons.HORSE_UP,action)==false){
								return;
							}
						}
					}
					
					//扣钱
					if ( ManagerPool.backpackManager.changeMoney(player,-money,Reasons.HORSE_UP,action)) {
						log.setMoney(money);
						byte is = 0;		//0失败，1成功
						int upnum = horse.getDayupnum() + 1 ;
						if (horse.getDayblessvalue() >= oldBasic.getQ_blessnum_limit()) {
							is = 1;
						}else {
							if (upnum <= oldBasic.getQ_up_num_min()) {
								is = 0;
							}else if(upnum >= oldBasic.getQ_up_num_max()){
								is = 1;
							}else {
								if(RandomUtils.isGenerate2(10000, oldBasic.getQ_up_probability())){	//	进入随机
									is = 1;	//成功
								}else {
									is = 0;
								}
							}
						}
						ReshorseStageUpResultMessage smsg = new ReshorseStageUpResultMessage();
						smsg.setType(is);
						if (ck) {//根据使用的道具来生成提示内容
							smsg.setItemmodelid(9124);
							smsg.setItemnum(1);
						}else {
							smsg.setItemmodelid(oldBasic.getQ_up_item_id());
							smsg.setItemnum(oldBasic.getQ_up_item_num());
							smsg.setMoney(money);
						}
						
						if(is == 1){	//成功处理
							horse.setDayblessvalue(0);
							horse.setDayupnum(0);
							horse.setHisblessvalue(0);
							horse.setHisupnum(0);
							horse.setLayer((short) nextLayer);
							horse.setCurlayer((short) nextLayer);
							//MessageUtil.notify_player(player, Notifys.SUCCESS, "进阶成功，进阶使用了：[{1}]{2}个，[铜币]{3}.",itemBean.getQ_name(),String.valueOf(oldBasic.getQ_up_item_num()),String.valueOf(oldBasic.getQ_up_gold_num()));	
							//MessageUtil.notify_player(player, Notifys.SUCCESS, "恭喜您，坐骑进阶成功至【{1}】.",horseBasic.getQ_name());	
							if(horseBasic.getQ_notice() == 1){
								ParseUtil parseUtil = new ParseUtil();
								parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】将【%s】升阶至：【%s】战斗力获得大量提升！{@}"), player.getName(),oldBasic.getQ_name(),horseBasic.getQ_name()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.HORSE.getValue()));
								MessageUtil.notify_All_player(Notifys.CHAT_BULL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.HORSE.getValue());
								//MessageUtil.notify_All_player(Notifys.CHAT_BULL,"恭喜玩家【{1}】将【{2}】进阶至：【{3}】！",player.getName(),oldBasic.getQ_name(),horseBasic.getQ_name());
							}
							autorideHorse(player,0);//自动换最新的坐骑
							stResHorseInfo(player);
//							ManagerPool.activitiesManager.sendActivitiesInfo(player,false);
							horse.setHorseUpTime(System.currentTimeMillis());
							sethorselevel(player, false);
							
							syncPlayerHorse(player);
	
						}else {	//失败处理
							horse.setDayupnum(upnum);
							int blessnum = RandomUtils.randomValue(oldBasic.getQ_blessnum_max(), oldBasic.getQ_blessnum_min());
							String[] a = oldBasic.getQ_normal_rnd().split(Symbol.SHUXIAN_REG);
							String[] b = oldBasic.getQ_small_crit_rnd().split(Symbol.SHUXIAN_REG);
							String[] c = oldBasic.getQ_large_crit_rnd().split(Symbol.SHUXIAN_REG);
							List< Integer> tab = new ArrayList<Integer>();
							tab.add(Integer.parseInt (a[0]));
							tab.add(Integer.parseInt (b[0]));
							tab.add(Integer.parseInt (c[0]));
							int idx = RandomUtils.randomIndexByProb(tab);
							int exp=0;
							if (idx == 0) {
								exp = Integer.parseInt (a[1]);
							}else if (idx == 1) {
								exp = Integer.parseInt (b[1]);
							}else if (idx == 2) {
								exp = Integer.parseInt (c[1]);
							}
							horse.setDayblessvalue(blessnum + horse.getDayblessvalue());
							smsg.setDayblessvalue(blessnum);
							if(horse.getDayexp() < oldBasic.getQ_up_fail_addexp()){
								smsg.setExp(exp);
								horse.setDayexp(exp+horse.getDayexp());
								ManagerPool.playerManager.addExp(player, exp,AttributeChangeReason.HORSE);
							}
							smsg.setCrit((byte) idx);
							//MessageUtil.notify_player(player, Notifys.ERROR, "很遗憾，坐骑进阶失败了.");	
							horse.ClearDay();	//清理每日数据
						}
						
						MessageUtil.tell_player_message(player, smsg);
						stHorseLog(player,smsg,log);
						
						IHorseUpScript script = (IHorseUpScript)ManagerPool.scriptManager.getScript(ScriptEnum.HORSE_UP);
						if(script!=null){
							try{
								script.onHorseUp(player, is,nextLayer);
							}catch (Exception e) {
								logx.error(e, e);
							}
						}else{
							logx.error("坐骑进阶脚本不存在！");
						}
						
					}	
				}else{
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("人物等级达到[{1}]级后，才可继续进阶."),String.valueOf(oldBasic.getQ_up_figure_level()));	
					ReshorseErrInfoMessage errmsg =new ReshorseErrInfoMessage();
					errmsg.setType((byte) 3);
					MessageUtil.tell_player_message(player, errmsg);
				}
			}
		}
	}
	
	
	

	
	
	
	
	
	/**坐骑升阶日志
	 * 
	 */
	public void stHorseLog(Player player ,ReshorseStageUpResultMessage smsg ,HorseLog log) {
		Horse horse = getHorse(player);
		log.setBlessvalue(smsg.getDayblessvalue());
		log.setNewblessvalue(horse.getDayblessvalue());
		log.setDayupnum(horse.getDayupnum());
		log.setFailexp(smsg.getExp());
		log.setPlayerId(player.getId());
		log.setIsupsuccess(smsg.getType());
		log.setLayer(horse.getLayer());
		LogService.getInstance().execute(log);
	}
	
	//-------------------------------------------技能升级------------------------------------------------
	
	/**得到最高激活技能ID
	 * 
	 */
	public HorseSkill getMaxActSkill(Player player){
		int lv = 0;
		HorseSkill horseSkill =new HorseSkill();
		Horse horse = getHorse(player);
		List<HorseSkill> sHorseSkill = horse.getSkills();
		for (HorseSkill skill : sHorseSkill) {
			int skid = skill.getSkillModelId();
			Q_horse_skillsBean skillModel = getDBHorseDataSkill(skid);
			if ( horse.getLayer() >=  skillModel.getQ_activate_need_layer() ) {
				if (skillModel.getQ_activate_need_layer() >= lv) {
					lv= skillModel.getQ_activate_need_layer();
					horseSkill.setSkillModelId(skid);
					horseSkill.setSkillLevel(skill.getSkillLevel());
				}
			}
		}
		return horseSkill;
	}
	
	
	
	/**筛选可显示的技能
	 * 
	 * @param player
	 * @return
	 */
	public List<HorseSkill> selectSkill(Player player){
		List<HorseSkill> skilltab = new ArrayList<HorseSkill>();
		Horse horse = getHorse(player);
		List<HorseSkill> skills=horse.getSkills();
		int SKILL_MAX = getskillmax();
		for (HorseSkill horseSkill : skills) {
			Q_horse_skillsBean skillModel = getDBHorseDataSkill(horseSkill.getSkillModelId());
			if (skillModel.getQ_show_need_layer() <= horse.getLayer()) {	//达到显示技能所需等级
				if(horseSkill.getSkillLevel() < SKILL_MAX){
					skilltab.add(horseSkill);
				}
			}
		}
		if (skilltab.size() > 0) {
			if (skilltab.size() == 1) {
				skilltab.add(skilltab.get(0));
			}
			if (skilltab.size() == 2) {
				skilltab.add(skilltab.get(0));
			}
			return skilltab;
		}
		return null;
	}
	
	
	/**筛选激活的技能,使用技能的时候调用这里
	 * 
	 * @param player
	 * @return
	 */
	public List<HorseSkill> selectActSkill(Player player){
		Horse horse = getHorse(player);
		
		if (horse.getCurlayer() > 0 && horse.getStatus() == 1) {
			List<HorseSkill> skilltab = new ArrayList<HorseSkill>();
			List<HorseSkill> skills=horse.getSkills();
			
			// 特殊技能:烈火焚城,仅仅在锻骨草达到100%的时候才能使用
			if (horse.getMixingbone() >= 50) {
				HorseSkill skill = new HorseSkill();
				skill.setId(Config.getId());
				skill.setSkillModelId(25027);
				skill.setSkillLevel(1);
				skilltab.add(skill);
			}

			for (HorseSkill horseSkill : skills) {
				Q_horse_skillsBean skillModel = getDBHorseDataSkill(horseSkill.getSkillModelId());
				if (skillModel.getQ_activate_need_layer() <= horse.getLayer()) {	//达到激活技能所需阶数
					skilltab.add(horseSkill);
				}
			}
			return skilltab;
		}
		return null ;
	}
	
	
	
	
	/**开始随机技能，选出技能，并从待选表内删除选中技能
	 * 
	 * @param skilllist
	 * @return
	 */
	public HorseSkill randomSkill(List<HorseSkill> skilllist ,int type){
		List<Integer> intlist = new ArrayList<Integer>();
		for (int i = 0; i < skilllist.size(); i++) {
			Q_horse_skillsBean skillModel = getDBHorseDataSkill(skilllist.get(i).getSkillModelId());
			if (type == 0) {
				intlist.add(skillModel.getQ_a_grid_rnd());
			}else if (type== 1) {
				intlist.add(skillModel.getQ_b_grid_rnd());
			}else if (type ==2) {
				intlist.add(skillModel.getQ_c_grid_rnd());
			}
		}
		int idx = RandomUtils.randomIndexByProb(intlist);
		HorseSkill skill = skilllist.remove(idx);
		return skill;
	}
	
	
	
	/**拉杆随机
	 * 
	 */
	public List<HorseSkill> laganRandomSkill(Player player ){
		List<HorseSkill> chosenList = new ArrayList<HorseSkill>();
		List<HorseSkill> rndskills = selectSkill(player);
		HorseSkill maxskill = getMaxActSkill(player);
		Q_horse_skillsBean maxskillModel = getDBHorseDataSkill(maxskill.getSkillModelId());
		//从待选表挑一个新技能------------------------------------
		HorseSkill askill = randomSkill(rndskills,0);
		chosenList.add(askill);

		//开始第2个技能选择---------------------------------------
		if(RandomUtils.isGenerate2(10000, maxskillModel.getQ_b_grid_rnd())){	//	进入随机(小暴击)
			//进入复选（复制技能1）
			chosenList.add(askill);
		}else {
			//没有复选，从待选表挑一个新技能
			HorseSkill bskill = randomSkill(rndskills,1);
			chosenList.add(bskill);
		}

		//开始第三个技能选择---------------------------------------
		if(RandomUtils.isGenerate2(10000, maxskillModel.getQ_c_grid_rnd())){	//	进入随机(大暴击)//检测是否复选
			//进入复选（1和2相加后随机其中一个）
			Q_horse_skillsBean aModel = getDBHorseDataSkill(chosenList.get(0).getSkillModelId());
			Q_horse_skillsBean bModel = getDBHorseDataSkill(chosenList.get(1).getSkillModelId());
			
			if(RandomUtils.isGenerate2(aModel.getQ_a_grid_rnd()+bModel.getQ_a_grid_rnd(), aModel.getQ_a_grid_rnd())){	//1和2之间选一个
				chosenList.add(askill);
			}else {
				chosenList.add(chosenList.get(1));
			}
		}else {
			//没有复选，从待选表挑一个新技能
			HorseSkill cskill = randomSkill(rndskills,2);
			chosenList.add(cskill);
		}
		rndskills = null;
		return chosenList;
	}
	
	
	/**幸运连珠触发
	 * 
	 * @param player
	 * @return
	 */
	public HorseSkill lianzhuRandomSkill(Player player ){
		List<HorseSkill> rndskills = selectSkill(player);
		HorseSkill askill = randomSkill(rndskills,0);
		rndskills = null;
		return askill;
	}
	
	
	
	
	
	/**幸运拉杆（使用金币）
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqhorseLuckyRodMessage(Player player,ReqhorseLuckyRodMessage msg) {
		if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.HORSE_SKILLUPCD,null)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，请等待冷却时间结束。"));	
			ReshorseErrInfoMessage errmsg =new ReshorseErrInfoMessage();
			errmsg.setType((byte) 5);		//如果错误消息，前端按钮亮起
			MessageUtil.tell_player_message(player, errmsg);
			return;
		}
		int activationlv = getHorseOpenSkillLv();//坐骑技能升级操作开放所需坐骑阶数
		int upmax = getHorseSkillUpNumMax();//每轮允许摇动坐骑技能宝盒次数
		Horse horse = getHorse(player);
		horse.ClearDay();
		long action=Config.getId();
		if(horse.getLayer() >= activationlv){
			if (horse.getBoxnum() < upmax) {
				int money = getUpMoney(player);
				if (money<= 0) {
					return;
				}
				if (ManagerPool.backpackManager.changeMoney(player, -money,Reasons.HORSE_LG,action)) {
					List<HorseSkill> skills = laganRandomSkill(player);
					List<HorseSkillInfo> skillinfolist = new ArrayList<HorseSkillInfo>();
					int aid	 = skills.get(0).getSkillModelId();
					int bid  = skills.get(1).getSkillModelId();
					int cid  = skills.get(2).getSkillModelId();
					skillinfolist.add(skills.get(0).createSkillInfo());
					skillinfolist.add(skills.get(1).createSkillInfo());
					skillinfolist.add(skills.get(2).createSkillInfo());
					skills = null;
					Q_horse_skillsBean askillsBean = getDBHorseDataSkill(aid);
					Q_horse_skillsBean bskillsBean = getDBHorseDataSkill(bid);
					Q_horse_skillsBean cskillsBean = getDBHorseDataSkill(cid);
					ReshorseLuckyRodMessage smsg = new ReshorseLuckyRodMessage();

					//大暴击
					if(aid == bid && bid == cid){
						skillAddExp(player,aid,askillsBean.getQ_addexp_bigcrit());
						skillinfolist.get(0).setSkillexp(askillsBean.getQ_addexp_bigcrit());
						skillinfolist.get(1).setSkillexp(askillsBean.getQ_addexp_bigcrit());
						skillinfolist.get(2).setSkillexp(askillsBean.getQ_addexp_bigcrit());
					//小暴击
					}else if (aid == bid || aid==cid || bid==cid) {
						if (aid ==bid) {
							skillAddExp(player,aid,askillsBean.getQ_addexp_crit());	//小暴击
							skillAddExp(player,cid,cskillsBean.getQ_addexp_single());	//单球经验
							skillinfolist.get(0).setSkillexp(askillsBean.getQ_addexp_crit());
							skillinfolist.get(1).setSkillexp(askillsBean.getQ_addexp_crit());
							skillinfolist.get(2).setSkillexp(cskillsBean.getQ_addexp_single());
						}else if (aid == cid) {
							skillAddExp(player,aid,askillsBean.getQ_addexp_crit());//小暴击
							skillAddExp(player,bid,bskillsBean.getQ_addexp_single());//单球经验
							skillinfolist.get(0).setSkillexp(askillsBean.getQ_addexp_crit());
							skillinfolist.get(1).setSkillexp(bskillsBean.getQ_addexp_single());
							skillinfolist.get(2).setSkillexp(askillsBean.getQ_addexp_crit());
						}else if (bid == cid) {
							skillAddExp(player,bid,bskillsBean.getQ_addexp_crit());//小暴击
							skillAddExp(player,aid,askillsBean.getQ_addexp_single());//单球经验
							skillinfolist.get(0).setSkillexp(askillsBean.getQ_addexp_single());
							skillinfolist.get(1).setSkillexp(bskillsBean.getQ_addexp_crit());
							skillinfolist.get(2).setSkillexp(bskillsBean.getQ_addexp_crit());
						}
					}else {//普通
						skillAddExp(player,aid,askillsBean.getQ_addexp_single());//单球经验
						skillAddExp(player,bid,bskillsBean.getQ_addexp_single());//单球经验
						skillAddExp(player,cid,cskillsBean.getQ_addexp_single());	//单球经验
						skillinfolist.get(0).setSkillexp(askillsBean.getQ_addexp_single());
						skillinfolist.get(1).setSkillexp(bskillsBean.getQ_addexp_single());
						skillinfolist.get(2).setSkillexp(cskillsBean.getQ_addexp_single());
					}
					horse.setBoxnum(horse.getBoxnum()+1);
					smsg.setNum((byte) horse.getBoxnum());
					smsg.setSkillinfolist(skillinfolist);
					MessageUtil.tell_player_message(player, smsg);
					setHorseSkillCD(player);
					HorseSkillLog log = new HorseSkillLog();
					log.setMoney(money);
					log.setSkills(smsg.toString());
					stHorseSkillLog(player,log);
					stReqOpenSkillUpPanelMessage(player);//发送下一次消耗的金币和元宝
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("拉杆消耗{1}铜币。"),String.valueOf(money));
					return;
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，拉杆所需消耗的铜币不足,需要{1}铜币"),String.valueOf(money));	
				}
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，请等待冷却时间结束."));	
			}
		}else {
			Q_horse_basicBean horseBasic = getDBHorseBasic(activationlv);
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，坐骑技能升级功能在升阶至[{1}]之后开放."),horseBasic.getQ_name());	
		}
		ReshorseErrInfoMessage errmsg =new ReshorseErrInfoMessage();
		errmsg.setType((byte) 5);		//如果错误消息，前端按钮亮起
		MessageUtil.tell_player_message(player, errmsg);
	}
	
	
	
	
	/** 幸运连珠（使用元宝）
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqhorseLuckyPenteMessage(Player player,ReqhorseLuckyPenteMessage msg) {
		if (ManagerPool.cooldownManager.isCooldowning(player,CooldownTypes.HORSE_SKILLUPCD,null)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，请等待冷却时间结束。"));	
			ReshorseErrInfoMessage errmsg =new ReshorseErrInfoMessage();
			errmsg.setType((byte) 5);		//如果错误消息，前端按钮亮起
			MessageUtil.tell_player_message(player, errmsg);
			return;
		}
		long action=Config.getId();
		int activationlv = getHorseOpenSkillLv();//坐骑技能升级操作开放所需坐骑阶数
		int upmax = getHorseSkillUpNumMax();//每轮允许摇动坐骑技能宝盒次数
		Horse horse = getHorse(player);
		horse.ClearDay();
		if(horse.getLayer() >= activationlv){
			if (horse.getBoxnum() < upmax) {
				int xyb = getUpYuanbao(player);
				if (xyb <= 0) {
					return;
				}
//				Gold gold = ManagerPool.backpackManager.getGold(player);
				if(ManagerPool.backpackManager.checkGold(player, xyb)) {
					ManagerPool.backpackManager.changeGold(player,-xyb,Reasons.HORSE_LZ,action);
					HorseSkill endskill = lianzhuRandomSkill(player);
					Q_horse_skillsBean skillsBean = getDBHorseDataSkill(endskill.getSkillModelId());
					skillAddExp(player,endskill.getSkillModelId(),skillsBean.getQ_addexp_bigcrit());//大暴击
					HorseSkillInfo horseSkillInfo = endskill.createSkillInfo();
					ReshorseLuckyPenteMessage smsg = new ReshorseLuckyPenteMessage();
					horse.setBoxnum(horse.getBoxnum()+1);
					horseSkillInfo.setSkillexp(skillsBean.getQ_addexp_bigcrit());
					smsg.setNum((byte) horse.getBoxnum());
					smsg.setSkillinfo(horseSkillInfo);
					MessageUtil.tell_player_message(player, smsg);
					setHorseSkillCD(player);
					HorseSkillLog log = new HorseSkillLog();
					log.setYuanbao(xyb);
					log.setSkills(smsg.toString());
					stHorseSkillLog(player,log);
					stReqOpenSkillUpPanelMessage(player);//发送下一次消耗的金币和元宝
					MessageUtil.notify_player(player,Notifys.SUCCESS,ResManager.getInstance().getString("幸运连珠消耗{1}元宝。"),String.valueOf(xyb));
					return;
				}else {
					MessageUtil.notify_player(player,Notifys.ERROR,ResManager.getInstance().getString("很抱歉，幸运连珠所需消耗的元宝不足,需要{1}元宝。"),String.valueOf(xyb));
				}
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，请等待冷却时间结束."));	
			}
		}else {
			Q_horse_basicBean horseBasic = getDBHorseBasic(activationlv);
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，坐骑技能升级功能在升阶至[{1}]之后开放."),horseBasic.getQ_name());	
		}	
		ReshorseErrInfoMessage errmsg =new ReshorseErrInfoMessage();
		errmsg.setType((byte) 5);		//如果错误消息，前端按钮亮起
		MessageUtil.tell_player_message(player, errmsg);
		
	}

	
	
	/**技能升级日志
	 * 
	 */
	public void stHorseSkillLog(Player player,HorseSkillLog log) {
		log.setPlayerId(player.getId());
		LogService.getInstance().execute(log);
	}
	
	
	/**得到拉杆金币数量
	 * 
	 * @return
	 */
	public int getUpMoney(Player player){
		Horse horse = getHorse(player);
		HorseSkill maxskill = getMaxActSkill(player);
		Q_horse_skillsBean skillModel = ManagerPool.dataManager.q_horse_skillsContainer.getMap().get(maxskill.getSkillModelId());
		String[] goldlist = skillModel.getQ_expend_gold().split(";");
		int idx= horse.getBoxnum();
		if (idx >= 10 ) {
			idx = 0;
		}
		//“历次拉动消耗金币（1|XXXX;2|xxx;依次类推）”字段中查出来的值  * （1 - 服务器开放天数 * 坐骑技能经验消耗金币衰减系数）
		int money = Integer.parseInt(goldlist[idx]);
		int day = TimeUtil.getOpenAreaDay(player);
		double xishu = (double)getYuanBaoModulus()/100;
		double x = 1-day*xishu;
		if(x < 0.5){
			x = 0.5;	//最终是5折
		}
		int xmoney = (int) Math.ceil(money*x);
		return xmoney;
	}
	
	
	/**得到连珠元宝数量
	 * 
	 * @param player
	 * @return
	 */
	public int getUpYuanbao(Player player){
		Horse horse = getHorse(player);
		// 根据玩家当前最大所激活的技能ID，进入坐骑技能数据库中“历次拉动消耗元宝（XXXX;xxxx;依次类推）”字段中查出来的值  * （1 - 服务器开放天数 * 坐骑技能经验消耗元宝衰减系数）
		HorseSkill maxskill = getMaxActSkill(player);
		Q_horse_skillsBean skillModel = ManagerPool.dataManager.q_horse_skillsContainer.getMap().get(maxskill.getSkillModelId());
		String[] yuanbaolist = skillModel.getQ_expend_yuanbao().split(";");
		int idx= horse.getBoxnum();
		if (idx >= 10 ) {
			idx = 0;
		}
		int yb = Integer.parseInt(yuanbaolist[idx]);
		int day = TimeUtil.getOpenAreaDay(player);
		double xishu = (double)getYuanBaoModulus()/100;
		double x = 1-day*xishu;
		if(x < 0.5){
			x = 0.5;		//最终是5折
		}
		int xyb = (int) Math.ceil(yb*x);
		return xyb;
	}
	
	
	/**设置技能升级CD时间
	 * 
	 */
	public void setHorseSkillCD(Player player){
		Horse horse = getHorse(player);
		if (horse.getBoxnum() >= 10) {		//10次后开始加冷却时间
			ManagerPool.cooldownManager.addCooldown(player,CooldownTypes.HORSE_SKILLUPCD,null,24*60*60*1000);
			horse.setBoxnum(0);
			//ReshorseCDTimeMessage smsg=new ReshorseCDTimeMessage();//暂时无用了
			//smsg.setNum(24*60*60);
			//MessageUtil.tell_player_message(player,smsg );
			//stResHorseInfo(player);
		}
	}
	
	
	
	
	/**增加技能经验
	 * 
	 * @param player
	 * @param skillid
	 * @param exp
	 */
	public void skillAddExp(Player player ,int skillid , int exp){
		Horse horse = getHorse(player);
		List<HorseSkill> skilldata = horse.getSkills();
		for (HorseSkill horseSkill : skilldata) {
			if (horseSkill.getSkillModelId() == skillid) {
				if ( horseSkill.getSkillLevel() < horse.skillMaxLevel()) {
					horseSkill.setSkillexp(exp+horseSkill.getSkillexp());
					horseSkillUp(player,horseSkill);
				}
				break;
			}
		}
	}
	

	/**坐骑技能等级提升
	 * 
	 * @param player
	 * @param HorseSkill
	 */
	public void horseSkillUp(Player player , HorseSkill horseSkill){
		Q_horse_skill_expBean expBean = ManagerPool.dataManager.q_horse_skill_expContainer.getMap().get(horseSkill.getSkillModelId()+"_"+horseSkill.getSkillLevel());
		if (expBean != null) {
			Horse horse = getHorse(player);
			int maxexp = expBean.getQ_need_exp();
			if(horseSkill.getSkillexp() >= maxexp && horseSkill.getSkillLevel() < horse.skillMaxLevel()){
				horseSkill.setSkillexp(horseSkill.getSkillexp() - maxexp);
				horseSkill.setSkillLevel(horseSkill.getSkillLevel() + 1);
				if (horseSkill.getSkillLevel() >= horse.skillMaxLevel()) {
					horseSkill.setSkillexp(0);
				}
				//TODO 同步坐骑信息
				
				
			//	Q_horse_skillsBean skillsBean = getDBHorseDataSkill(horseSkill.getSkillModelId());
				//MessageUtil.notify_player(player, Notifys.SUCCESS, "恭喜您，坐骑技能『{1}』升级了。",skillsBean.getQ_skill_name());	
				int num = 0;
				for (HorseSkill skill : horse.getSkills()) {
					num = num + skill.getSkillLevel();
				}
				horse.setSkilllevelsum(num);	//得到技能等级之和
				syncPlayerHorse(player);
			}
		}
	}
	
	/**设置坐骑等级（排行用
	 * ）
	 * @param player
	 */
	public void sethorselevel(Player player, boolean sync){
		Horse horse = getHorse(player);
		int  Layer = horse.getLayer();
		Q_horse_basicBean db = getDBHorseBasic(Layer);

		if(db==null){
			return;
		}
		int level = horse.getHorselevel();
		if (db.getQ_level_max() > player.getLevel() ) {
			horse.setHorselevel(player.getLevel());
		}else {
			horse.setHorselevel(db.getQ_level_max());

		}

		if(level!=horse.getHorselevel() && sync){
			syncPlayerHorse(player);
		}
	}
	
	
	private void syncPlayerHorse(Player player){
		Horse horse = getHorse(player);
		if(horse.getLayer() < Global.SYNC_HORSE_STAGE || player.getLevel() < Global.SYNC_PLAYER_LEVEL){
			return;
		}
		ReqSyncPlayerHorseMessage msg = new ReqSyncPlayerHorseMessage();
		msg.setPlayerId(player.getId());
		msg.setHorseStage(horse.getLayer());
		msg.setHorseLevel(horse.getHorselevel());
		msg.setHorseSkillLevel(horse.getSkilllevelsum());
		msg.setHorseTime(horse.getHorseUpTime());
		
		MessageUtil.send_to_world(msg);
	}
	
	
	/**
	 * 清除拉杆CD
	 * @param parameter
	 */
	public void stReqhorseClearCDMessage(Player player, ReqhorseClearCDMessage msg){
		long cooldownTime = ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.HORSE_SKILLUPCD, null);
		if (cooldownTime == 0) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("已经清除冷却时间了。"));	
			return;
		}
		long action = Config.getId();
		Horse horse = getHorse(player);
		int yb = getClearCDYuanBao( player);
//		Gold gold = ManagerPool.backpackManager.getGold(player);
		if(ManagerPool.backpackManager.checkGold(player, yb)) {
			ManagerPool.backpackManager.changeGold(player,-yb,Reasons.HORSE_CD,action);
			ManagerPool.cooldownManager.removeCooldown(player, CooldownTypes.HORSE_SKILLUPCD, null);	
			horse.setClearcdnum(horse.getClearcdnum()+1);
			horse.setBoxnum(0);
			stResHorseInfo(player);
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您使用{1}元宝清除冷却时间."),String.valueOf(yb));	
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，需要{1}元宝才能清除冷却时间."),String.valueOf(yb));
			ReshorseErrInfoMessage errmsg =new ReshorseErrInfoMessage();
			errmsg.setType((byte) 4);
			errmsg.setErrint(yb);
			MessageUtil.tell_player_message(player, errmsg);
		}
	}

	/**清除CD需要的元宝数量计算
	 * 
	 * @param player
	 * @return
	 */
	public int getClearCDYuanBao(Player player){
		//（1 + 玩家今日已清除技能经验获取的升级冷却时间次数） * “清除坐骑升级经验冷却次数所需消耗元宝数倍率” * “每日清除坐骑升级经验冷却次数所需消耗元宝数”
		Horse horse = getHorse(player);
		int num = horse.getClearcdnum();
		double xishu = (double)getClearCDYuanBaoModulus()/100;
		double yb = (1+num)*xishu*getClearCDYuanBaoNum();
		return (int)Math.ceil(yb);
	}
	
	
	
	
	
	
	
	/**他人坐骑信息
	 * 
	 * @param player
	 * @return
	 */
	public ResOthersHorseInfoMessage getOthersHorseInfo(Player player){
		ResOthersHorseInfoMessage smsg = new ResOthersHorseInfoMessage();
		Horse horse = getHorse(player);
		OthersHorseInfo info = new OthersHorseInfo();
		if (horse != null && horse.getLayer() > 0) {
			HorseInfo oinfo = horse.CreateHorseInfo(player);
			info.setCurlayer(oinfo.getCurlayer());
			info.setHorseequipinfo(oinfo.getHorseequipinfo());
			info.setSkillinfolist(oinfo.getSkillinfolist());
			info.setLayer(oinfo.getLayer());
			info.setStatus(horse.getStatus());
			info.setLevel((short) player.getLevel());
			info.setPotential(horse.getPotential());
			info.setMixingbone(horse.getMixingbone());
		}
		smsg.setPlayerid(player.getId());
		smsg.setInfo(info);
		return smsg;
	}

	
	/**打开技能升级面板
	 * 
	 * @param parameter
	 * @param msg
	 */
	public void stReqOpenSkillUpPanelMessage(Player player) {
		ResOpenSkillUpPanelMessage smsg = new ResOpenSkillUpPanelMessage();
		smsg.setYuanbao(getUpYuanbao(player));
		smsg.setMoney(getUpMoney(player));
		smsg.setBoxnum((byte) getHorse(player).getBoxnum());
		MessageUtil.tell_player_message(player,smsg );
	}

	/**改变坐骑
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqChangeHorseMessage(Player player,ReqChangeHorseMessage msg) {
		autorideHorse(player,msg.getCurlayer());
	}

	
	/**发送给前端技能列表
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqSkillInfoMessage(Player player, ReqSkillInfoMessage msg) {
		Horse horse = getHorse(player);
		ResSkillInfoMessage smsg = new ResSkillInfoMessage();
		List<HorseSkill> skills = horse.getSkills();
		for (HorseSkill skill : skills) {
			smsg.getSkillinfolist().add(skill.createSkillInfo());
		}
		smsg.setMoney(getUpMoney( player));
		smsg.setYuanbao(getUpYuanbao(player));
		MessageUtil.tell_player_message(player,smsg );
		if (horse.getBoxnum() == 0) {
			ReshorseCDTimeMessage tmsg = new ReshorseCDTimeMessage();
			long cooldownTime = ManagerPool.cooldownManager.getCooldownTime(player, CooldownTypes.HORSE_SKILLUPCD, null);
			int time = (int)cooldownTime/1000;
			if (time < 0) {
				time = 0;
			}
			
			tmsg.setCdtimeyuanbao(getClearCDYuanBao(player));
			tmsg.setNum(time);
			
			MessageUtil.tell_player_message(player,tmsg );
		}
	}
	
	

	/**加祝福值
	 * 
	 * @param player
	 * @param num
	 */
	public void addDaybless(Player player ,int num){
		Horse horse = getHorse(player);
		horse.ClearDay();	//打开的时候清理每日数据
		horse.setDayblessvalue(num + horse.getDayblessvalue());
		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("祝福值增加了{1}"), num+"");
		stResHorseInfo(player);
	}
	
	
	
	/**设置为指定坐骑阶数
	 * 
	 * @param player
	 * @param layer
	 * @return 
	 */
	public boolean setHorseLayer(Player player, int layer) {
		Horse horse = getHorse(player);
		if (horse.getLayer() >= layer) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的坐骑已经是{1}阶"),layer+"");
			return false;
		}
		horse.setDayblessvalue(0);
		horse.setDayupnum(0);
		horse.setHisblessvalue(0);
		horse.setHisupnum(0);
		horse.setLayer((short) layer);
		horse.setCurlayer((short) layer);
		autorideHorse(player,0);//自动换最新的坐骑
		stResHorseInfo(player);
//		ManagerPool.activitiesManager.sendActivitiesInfo(player,false);
		horse.setHorseUpTime(System.currentTimeMillis());
		sethorselevel(player, false);
		syncPlayerHorse(player);
		return true;
	}
	
	
	
}

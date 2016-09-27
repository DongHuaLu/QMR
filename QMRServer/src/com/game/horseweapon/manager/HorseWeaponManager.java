package com.game.horseweapon.manager;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.Item;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_horseweaponBean;
import com.game.data.bean.Q_horseweapon_attrBean;
import com.game.data.bean.Q_horseweapon_skillBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.fight.structs.FighterState;
import com.game.fightpower.manager.FightPowerManager;
import com.game.horseweapon.bean.HorseWeaponInfo;
import com.game.horseweapon.bean.HorseWeaponSkillInfo;
import com.game.horseweapon.bean.OthersHorseWeaponInfo;
import com.game.horseweapon.log.HorseWeaponLog;
import com.game.horseweapon.message.ResHorseWeaponErrorMessage;
import com.game.horseweapon.message.ResHorseWeaponInfoMessage;
import com.game.horseweapon.message.ResHorseWeaponStageUpPanelMessage;
import com.game.horseweapon.message.ResHorseWeaponStageUpResultMessage;
import com.game.horseweapon.message.ResWearHorseWeaponStateMessage;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.server.impl.WServer;
import com.game.skill.structs.Skill;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

public class HorseWeaponManager {
	
	protected static Logger logger = Logger.getLogger(HorseWeaponManager.class);

	private static Object obj = new Object();
	
	private static final byte WEAR=1;	//装备
	
	private static final byte UNWEAR=0;	//未装备
	
	private static short LAYER_MAX=7;	//目前开放的最高阶数
	
	private static int CLEAR_HOUR=0;	//祝福值清理时间

	//玩家管理类实例
	private static HorseWeaponManager manager;
	public static HorseWeaponManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new HorseWeaponManager();
			}
		}
		return manager;
	}
	
	private HorseWeaponManager(){}
	
	/**
	 * 得到坐骑武器
	 * @param player
	 */
	public HorseWeapon getHorseWeapon(Player player){
		List<HorseWeapon> horseweaponlist = player.getHorseweaponlist();
		if (horseweaponlist==null || horseweaponlist.size() == 0) {
			return null;
		}
		return horseweaponlist.get(0);
	}
	
	/**
	 * 激活骑战兵器
	 * @param player
	 * @param itemid
	 */
	public boolean activateHorseWeapon(Player player, Item item, long actionId){
		HorseWeapon weapon = getHorseWeapon(player);
		//判断骑战兵器是否存在
		if(weapon!=null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("骑战兵器已经存在。"));
			return false;
		}
		
		//判断包裹内是否有骑战兵器
		if(item==null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您包裹内没有骑战兵器。"));
			return false;
		}

		//激活1级骑战兵器
		weapon = new HorseWeapon();
		weapon.setLayer(1);
		weapon.setCurLayer(1);
		weapon.setLevel(player.getLevel());
		weapon.setId(Config.getId());
		weapon.setOvertime(System.currentTimeMillis() + (long)7 * 24 * 60 * 60 * 1000);
		weapon.setState(WEAR);
		
		player.getHorseweaponlist().add(weapon);
		
		try{
			//日志记录 激活兵器
			HorseWeaponLog log = new HorseWeaponLog();
			log.setUserid(player.getUserId());
			log.setRoleid(String.valueOf(player.getId()));
			log.setUsername(player.getUserName());
			log.setBeforerank(0);
			log.setAfterrank(1);
			log.setBeforeexp(0);
			log.setAfterexp(0);
			log.setItemmodel(item.getItemModelId());
			log.setItemnum(1);
			log.setCosttype(3);
			log.setActionid(actionId);
			log.setSid(WServer.getInstance().getServerId());
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			logger.error(e, e);
		}
		
		//计算骑兵武器属性
		countHorseWeaponAttribute(player);
		//发送骑战兵器信息
		sendHorseWeaponInfo(player);
		//如果骑马中，而且武器更新外貌
		broadcastHorseWeaponInfo(player);
		
		ManagerPool.playerManager.stSyncExterior(player);
		
		try{
			int afterFightPower = getHorseWeaponAttrFightPower(player, weapon); //激活后战斗力- 用于引导公告
			
			//取得一阶骑战兵器名称
			String weaponname = "";
			Q_horseweaponBean bean = DataManager.getInstance().q_horseweaponContainer.getMap().get(1);
			if(bean!=null){
				weaponname = bean.getQ_name();
			}
			//发送骑兵激活成功的引导
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜【%s】成功装备第二把武器【%s】战斗力提升%d！{@}"), player.getName(), weaponname, afterFightPower), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.HORSEWEAPON.getValue()));
			MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(), new ArrayList<GoodsInfoRes>(), GuideType.HORSEWEAPON.getValue());
		}catch (Exception e) {
			logger.error(e, e);
		}
		return true;
	}
	
	/**
	 * 升级骑战兵器
	 * @param player
	 */
	public void levelupHorseWeapon(Player player, byte auto){
		HorseWeapon weapon = getHorseWeapon(player);
		//判断骑战兵器是否存在
		if(weapon==null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您还没有骑战兵器。"));
			sendFailedError(player);
			return;
		}
		//达到最高阶
		if(weapon.getLayer() >= LAYER_MAX){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的骑战兵器已经达到最高阶。"));
			sendFailedError(player);
			return;
		}
		
		Q_horseweaponBean weaponBean = ManagerPool.dataManager.q_horseweaponContainer.getMap().get(weapon.getLayer());
		if(weaponBean==null){
			logger.error("骑战兵器" + weapon.getLayer() + "等级不存在");
			sendFailedError(player);
			return;
		}
		
		boolean overtime = false;
		if(weapon.getOvertime()!=-1 && System.currentTimeMillis()>weapon.getOvertime()){
			overtime = true;
		}
		
		//等级不足
		if(player.getLevel() < weaponBean.getQ_need_level() && !overtime){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您等级不足。"));
			sendFailedError(player);
			return;
		}
		
		//钱不足
		if(player.getMoney() < weaponBean.getQ_need_money()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您铜币不足。"));
			sendFailedError(player);
			return;
		}
		
		int beforerank = weapon.getLayer(); //升级前阶数- 用于日志
		int beforeexp = weapon.getDayblessvalue(); //升级前经验- 用于日志
		Q_horseweaponBean frombean = weaponBean;
		int preFightPower = getHorseWeaponAttrFightPower(player, weapon); //升级前战斗力
		
		//根据骑战兵器，判断所需道具
		int costItemModelId = weaponBean.getQ_need_modelid();
		int costNum = weaponBean.getQ_item_num();
		int wmitemnum  = ManagerPool.backpackManager.getItemNum(player,9165);	//完美海心铁晶
		//是否需要花费元宝
		boolean costGold = false;
		
		long actionId = Config.getId();
		
		if (weapon.getLayer() >= 4 &&  wmitemnum > 0) {//4阶或以上，优先收取完美海心铁晶
			if(!ManagerPool.backpackManager.removeItem(player, 9165, 1, Reasons.HORSEWEAPON_ITEM, actionId)){
				return;
			}
		}else {
			if(costItemModelId!=0 && costNum!=0){
				Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(costItemModelId);
				if(itemBean==null){
					sendFailedError(player);
					return;
				}
				//判断材料是否足够
				int num = BackpackManager.getInstance().getItemNum(player, costItemModelId);
				
				if(num < costNum){
					if(auto==0){
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要{1}[{2}颗]，才可继续进阶."),itemBean.getQ_name(),String.valueOf(costNum));
						sendFailedError(player);
						return;
					}else{
						costGold = true;
					}
				}
				
				//扣除材料
				if(costGold){
					if(!ManagerPool.shopManager.autoRemove(player, costItemModelId, costNum, Reasons.HORSEWEAPON_GOLD, actionId)){
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要{1}[{2}颗]，才可继续进阶."),itemBean.getQ_name(),String.valueOf(costNum));
						sendFailedError(player);
						return;
					}
				}else{
					if(!ManagerPool.backpackManager.removeItem(player, costItemModelId, costNum, Reasons.HORSEWEAPON_ITEM, actionId)){
						MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要{1}[{2}颗]，才可继续进阶."),itemBean.getQ_name(),String.valueOf(costNum));
						sendFailedError(player);
						return;
					}
				}
			}else {
				return;
			}
		}

		ManagerPool.backpackManager.changeMoney(player, -weaponBean.getQ_need_money(), Reasons.HORSEWEAPON_MONEY, actionId);
		
		clearDay(weapon);
		
		byte success = 0;		//0失败，1成功
		int upnum = weapon.getDayupnum() + 1 ;
		if (weapon.getDayblessvalue() >= weaponBean.getQ_blessnum_limit()) {
			success = 1;
		}else {
			if (upnum <= weaponBean.getQ_up_num_min()) {
				success = 0;
			}else if(upnum >= weaponBean.getQ_up_num_max()){
				success = 1;
			}else {
				if(RandomUtils.isGenerate2(10000, weaponBean.getQ_up_probability())){	//	进入随机
					success = 1;	//成功
				}else {
					success = 0;
				}
			}
		}
//		ReshorseStageUpResultMessage smsg = new ReshorseStageUpResultMessage();
//		smsg.setType(is);
//		if (ck) {//根据使用的道具来生成提示内容
//			smsg.setItemmodelid(9124);
//			smsg.setItemnum(1);
//		}else {
//			smsg.setItemmodelid(oldBasic.getQ_up_item_id());
//			smsg.setItemnum(oldBasic.getQ_up_item_num());
//			smsg.setMoney(money);
//		}
		
		int blessnum = 0;
		int exp = 0;
		byte crit = 0;
		boolean levelUp = false;
		if(success == 1){	//成功处理
			weapon.setDayblessvalue(0);
			weapon.setDayupnum(0);
			weapon.setHisblessvalue(0);
			weapon.setHisupnum(0);

			weapon.setLayer(weapon.getLayer() + 1);
			weapon.setCurLayer(weapon.getLayer());
			weapon.setOvertime(-1);
			
			weaponBean = ManagerPool.dataManager.q_horseweaponContainer.getMap().get(weapon.getLayer());
			Q_horseweapon_skillBean skillBean = ManagerPool.dataManager.q_horseweapon_skillContainer.getMap().get(weaponBean.getQ_open_grid());
			if(skillBean!=null && skillBean.getQ_skilltype()==2){
				try{
					weapon.getSkills()[weaponBean.getQ_open_grid()-1] = Integer.parseInt(skillBean.getQ_skillid());
				}catch (Exception e) {
					logger.error(e, e);
				}
			}
			levelUp = true;
		}else {	//失败处理
			weapon.setDayupnum(upnum);
			blessnum = RandomUtils.randomValue(weaponBean.getQ_blessnum_max(), weaponBean.getQ_blessnum_min());
			String[] a = weaponBean.getQ_normal_rnd().split(Symbol.SHUXIAN_REG);
			String[] b = weaponBean.getQ_small_crit_rnd().split(Symbol.SHUXIAN_REG);
			String[] c = weaponBean.getQ_large_crit_rnd().split(Symbol.SHUXIAN_REG);
			List< Integer> tab = new ArrayList<Integer>();
			tab.add(Integer.parseInt (a[0]));
			tab.add(Integer.parseInt (b[0]));
			tab.add(Integer.parseInt (c[0]));
			crit = (byte)RandomUtils.randomIndexByProb(tab);
			if (crit == 0) {
				exp = Integer.parseInt (a[1]);
			}else if (crit == 1) {
				exp = Integer.parseInt (b[1]);
			}else if (crit == 2) {
				exp = Integer.parseInt (c[1]);
			}
			weapon.setDayblessvalue(blessnum + weapon.getDayblessvalue());
			if (weapon.getDayblessvalue() >= weaponBean.getQ_blessnum_limit()) {
				weapon.setDayblessvalue(weaponBean.getQ_blessnum_limit());
			}
			if(weapon.getDayexp() < weaponBean.getQ_up_fail_addexp()){
				weapon.setDayexp(exp + weapon.getDayexp());
				ManagerPool.playerManager.addExp(player, exp,AttributeChangeReason.HORSE_WEAPON);
			}else{
				exp = 0;
			}
		}
//		
//		int needExp = weaponBean.getQ_need_exp();
//		boolean levelUp = false;
//		int exp = 0;
//		List<Integer> tab = new ArrayList<Integer>();
//		tab.add(weaponBean.getQ_normal_rate());
//		tab.add(weaponBean.getQ_critical_rate());
//		tab.add(weaponBean.getQ_big_critical_rate());
//		byte crit = (byte)RandomUtils.randomIndexByProb(tab);
//		if (crit == 0) {
//			exp = weaponBean.getQ_normal_exp();
//		}else if (crit == 1) {
//			exp = weaponBean.getQ_critical_exp();
//		}else if (crit == 2) {
//			exp = weaponBean.getQ_big_critical_exp();
//		}
//		//升级骑战兵器
//		weapon.setExp(weapon.getExp() + exp);
//		if(weapon.getExp() >= needExp){
//			weapon.setLayer(weapon.getLayer() + 1);
//			weapon.setCurLayer(weapon.getLayer());
//			if(weapon.getLayer() < LAYER_MAX) weapon.setExp(weapon.getExp() - needExp);
//			else weapon.setExp(0);
//			weapon.setOvertime(-1);
//			
//			weaponBean = ManagerPool.dataManager.q_horseweaponContainer.getMap().get(weapon.getLayer());
//			
//			Q_horseweapon_skillBean skillBean = ManagerPool.dataManager.q_horseweapon_skillContainer.getMap().get(weaponBean.getQ_open_grid());
//			if(skillBean!=null && skillBean.getQ_skilltype()==2){
//				try{
//					weapon.getSkills()[weaponBean.getQ_open_grid()-1] = Integer.parseInt(skillBean.getQ_skillid());
//				}catch (Exception e) {
//					logger.error(e, e);
//				}
//			}
//			levelUp = true;
//		}
		ResHorseWeaponStageUpResultMessage msg = new ResHorseWeaponStageUpResultMessage();
		msg.setType(levelUp?(byte)1:(byte)0);
		msg.setBless(weapon.getDayblessvalue());
		msg.setGotbless(blessnum);
		msg.setGotexp(exp);
		msg.setCrit(crit);
		MessageUtil.tell_player_message(player, msg);
		
		int afterrank = weapon.getLayer(); //升级后阶数- 用于日志 
		int afterexp = weapon.getDayblessvalue(); //升级后经验- 用于日志
		int afterFightPower = 0;
		
		if(levelUp){
			//发送骑战兵器信息
			sendHorseWeaponInfo(player);
			//重新计算属性
			countHorseWeaponAttribute(player);
			//广播外貌消息
			broadcastHorseWeaponInfo(player);
			
			ManagerPool.playerManager.stSyncExterior(player);
			
			try{
				afterFightPower = getHorseWeaponAttrFightPower(player, weapon); //升级后战斗力
				int upvalue = afterFightPower-preFightPower;
				Q_horseweaponBean tobean = DataManager.getInstance().q_horseweaponContainer.getMap().get(afterrank);
				if(tobean!=null){
					if( tobean.getQ_is_announce()==1 ){ //是否需要发公告
						//取得骑战兵器名称
						String fromweapon = "", toweapon = "";
						fromweapon = frombean.getQ_name(); 
						toweapon = tobean.getQ_name(); 
						//发送骑兵升级公告
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜【%s】将【%s】升阶至【%s】战斗力提升%d！{@}"), player.getName(), fromweapon, toweapon, upvalue), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.HORSEWEAPON_UP.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(), new ArrayList<GoodsInfoRes>(), GuideType.HORSEWEAPON_UP.getValue());
					}
				}
			}catch (Exception e) {
				logger.error(e, e);
			}
		}
		
		try{
			//日志记录 升级兵器
			HorseWeaponLog log = new HorseWeaponLog();
			log.setUserid(player.getUserId());
			log.setRoleid(String.valueOf(player.getId()));
			log.setUsername(player.getUserName());
			log.setBeforerank(beforerank);
			log.setAfterrank(afterrank);
			log.setBeforeexp(beforeexp);
			log.setAfterexp(afterexp);
			log.setItemmodel(costItemModelId);
			log.setItemnum(costNum);
			log.setCosttype(costGold? 1:2);
			log.setActionid(actionId);
			log.setSid(WServer.getInstance().getServerId());
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			logger.error(e, e);
		}
	}
	
	private void sendFailedError(Player player){
		ResHorseWeaponErrorMessage error = new ResHorseWeaponErrorMessage();
		error.setType((byte)0);
		MessageUtil.tell_player_message(player, error);
	}
	/**
	 * 设置骑战兵器等级(人物升级时)
	 * @param player
	 */
	public void setHorseWeaponLevel(Player player){
		HorseWeapon weapon = getHorseWeapon(player);
		//判断骑战兵器是否存在
		if(weapon==null){
			return;
		}

		//骑战兵器等级升级
		weapon.setLevel(player.getLevel());
		
		//重新计算属性
		countHorseWeaponAttribute(player);
	}
	
	/**
	 * 装备骑战兵器
	 * @param player
	 */
	public void wearHorseWeapon(Player player, int layer){
		HorseWeapon weapon = getHorseWeapon(player);
		//判断骑战兵器是否存在
		if(weapon==null){
			return;
		}
		
		//是否超过骑战兵器最大等级
		if(layer<=0 || layer>weapon.getLayer()){
			return;
		}
		
		//武器已经过期
		if(weapon.getOvertime()!=-1 && System.currentTimeMillis()>weapon.getOvertime()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的骑战兵器已经过期。"));
			return;
		}
		
		//已经装备此武器了
		if(weapon.getCurLayer()==layer&&weapon.getState()==WEAR){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经装备此骑战兵器了。"));
			return;
		}
		
		weapon.setCurLayer(layer);
		weapon.setState(WEAR);
		
		//计算骑兵武器属性
		countHorseWeaponAttribute(player);
		
		broadcastHorseWeaponInfo(player);
		
		ManagerPool.playerManager.stSyncExterior(player);
	}
	
	/**
	 * 卸下骑战兵器
	 * @param player
	 */
	public void unwearHorseWeapon(Player player){
		HorseWeapon weapon = getHorseWeapon(player);
		//判断骑战兵器是否存在
		if(weapon==null){
			return;
		}
		
		//已经装备此武器了
		if(weapon.getState()==UNWEAR){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有装备骑战兵器。"));
			return;
		}
		
		weapon.setState(UNWEAR);
		
		//计算骑兵武器属性
		countHorseWeaponAttribute(player);
		

		broadcastHorseWeaponInfo(player);
		
		ManagerPool.playerManager.stSyncExterior(player);
	}
	
	/**
	 * 返回骑战兵器可触发技能
	 * @param player
	 * @return
	 */
	public List<Skill> getHorseWeaponSkillTriggerByAttack(Player player){
		List<Skill> skills = new ArrayList<Skill>();
		HorseWeapon weapon = getHorseWeapon(player);
		if(isWearHorseWeapon(player) && ManagerPool.horseManager.isRidding(player)){
			Q_horseweaponBean weaponBean = ManagerPool.dataManager.q_horseweaponContainer.getMap().get(weapon.getCurLayer());
			if(weaponBean!=null){
				for (int i = 0; i < weaponBean.getQ_open_grid(); i++) {
					int skillid = weapon.getSkills()[i];
					if(skillid!=0){
						Q_horseweapon_skillBean skillBean = ManagerPool.dataManager.q_horseweapon_skillContainer.getMap().get(i+1);
						if(skillBean.getQ_skilltype()==1){
							Skill skill = ManagerPool.skillManager.getSkillByModelId(player, skillid);
							if(skill!=null){
								skills.add(0, skill);
							}
						}else if(skillBean.getQ_skilltype()==2){
							Skill skill = new Skill();
							skill.setId(Config.getId());
							skill.setSkillModelId(skillid);
							skill.setSkillLevel(1);
							skills.add(0, skill);
						}
					}
				}
			}
		}
		
		return skills;
	}
	
	/**
	 * 设置骑战兵器技能
	 * @param player
	 * @param skillId
	 * @param grid
	 */
	public void setHorseWeaponSkill(Player player, int skillId, int grid){
		HorseWeapon weapon = getHorseWeapon(player);
		if(weapon!=null){
			Q_horseweaponBean weaponBean = ManagerPool.dataManager.q_horseweaponContainer.getMap().get(weapon.getLayer());
			if(weaponBean!=null){
				Q_horseweapon_skillBean skillBean = ManagerPool.dataManager.q_horseweapon_skillContainer.getMap().get(grid+1);
				if(skillBean==null||skillBean.getQ_skilltype()!=1){
					logger.error("只能设置龙元武学技能");
					return;
				}
				
				if(skillBean.getQ_skillid().indexOf(String.valueOf(skillId))==-1){
					logger.error("这个格子不能设置此技能");
					return;
				}
				
				Skill skill = ManagerPool.skillManager.getSkillByModelId(player, skillId);
				if(skill==null){
					logger.error("没有学会设置技能");
					return;
				}
				
				if(grid >= weaponBean.getQ_open_grid()){
					logger.error("武器技能格子超过最大！");
					return;
				}
				
				for (int i = 0; i < weaponBean.getQ_open_grid(); i++) {
					int skillgrid = weapon.getSkills()[i];
					if(skillgrid==skillId){
						weapon.getSkills()[i] = 0;
					}
				}
				
				weapon.getSkills()[grid] = skillId;
				
				//发送骑战兵器信息
				sendHorseWeaponInfo(player);
			}
		}
	}
	
	/**
	 * 重新计算骑战兵器属性
	 * @param player
	 */
	public void countHorseWeaponAttribute(Player player){
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HORSE_WEAPON);
	}
	
	/**
	 * 打开骑战兵器升阶面板
	 * @param player
	 */
	public void openHorseWeaponStageUp(Player player){
		HorseWeapon weapon = getHorseWeapon(player);
		if(weapon!=null){
			clearDay(weapon);
			
			ResHorseWeaponStageUpPanelMessage msg = new ResHorseWeaponStageUpPanelMessage();
			msg.setExp(weapon.getDayblessvalue());
			MessageUtil.tell_player_message(player, msg);
		}
	}
	
	/**
	 * 获取骑战兵器等级
	 * @param weapon
	 * @return
	 */
	public int getHorseWeaponLevel(HorseWeapon weapon){
		if(weapon!=null){
			Q_horseweaponBean weaponBean = ManagerPool.dataManager.q_horseweaponContainer.getMap().get(weapon.getLayer());
			if(weaponBean!=null){
				if(weapon.getLevel() > weaponBean.getQ_max_level()){
					return weaponBean.getQ_max_level();
				}else{
					return weapon.getLevel();
				}
			}
		}
		return 0;
	}
	
	/**
	 * 获取骑战兵器等级
	 * @param weapon
	 * @return
	 */
	public int getHorseWeaponLayer(Player player){
		HorseWeapon weapon = getHorseWeapon(player);
		if(weapon!=null){
			return weapon.getLayer();
		}
		return 0;
	}

	
	/**
	 * 是否装备骑战兵器
	 * @return
	 */
	public boolean isWearHorseWeapon(Player player){
		HorseWeapon weapon = getHorseWeapon(player);
		if(weapon!=null && weapon.getState()==WEAR && !FighterState.HORSEWEAPONUNWEAR.compare(player.getFightState())){
			return true;
		}
		return false;
	}
	
	/**
	 * 发送人物骑战兵器信息
	 * @param player
	 */
	public void sendHorseWeaponInfo(Player player){
		HorseWeapon weapon = getHorseWeapon(player);
		if(weapon==null){
			return;
		}
		clearDay(weapon);
		ResHorseWeaponInfoMessage msg = new ResHorseWeaponInfoMessage();
		msg.setPlayerid(player.getId());
		HorseWeaponInfo info = new HorseWeaponInfo();
		info.setLayer((short)weapon.getLayer());
		info.setCurlayer((short)weapon.getCurLayer());
		info.setStatus(isWearHorseWeapon(player)?(byte)1:(byte)0);
		info.setBless(weapon.getDayblessvalue());
		for (int i = 0; i < weapon.getSkills().length; i++) {
			HorseWeaponSkillInfo skillinfo = new HorseWeaponSkillInfo();
			skillinfo.setSkill(weapon.getSkills()[i]);
			if(skillinfo.getSkill()!=0){
				Skill skill = ManagerPool.skillManager.getSkillByModelId(player, skillinfo.getSkill());
				if(skill!=null){
					skillinfo.setLevel(skill.getRealLevel(player));
				}else{
					skillinfo.setLevel(1);
				}
			}
			info.getSkills().add(skillinfo);
		}
		if(weapon.getOvertime()==-1){
			info.setTime(-1);
		}else{
			long time = weapon.getOvertime() - System.currentTimeMillis();
			if(time < 0) time = 0;
			info.setTime((int)(time/1000));
		}
		msg.setInfo(info);
		MessageUtil.tell_player_message(player, msg);
	}
	
	/**
	 * 发送他人骑战兵器信息
	 * @param player
	 */
	public OthersHorseWeaponInfo getOtherHorseWeaponInfo(Player player){
		HorseWeapon weapon = getHorseWeapon(player);
		OthersHorseWeaponInfo info = new OthersHorseWeaponInfo();
		if(weapon==null){
			return info;
		}
		info.setLayer((short)weapon.getLayer());
		info.setCurlayer((short)weapon.getCurLayer());
		for (int i = 0; i < weapon.getSkills().length; i++) {
//			info.getSkills().add(weapon.getSkills()[i]);
			HorseWeaponSkillInfo skillinfo = new HorseWeaponSkillInfo();
			skillinfo.setSkill(weapon.getSkills()[i]);
			if(skillinfo.getSkill()!=0){
				Skill skill = ManagerPool.skillManager.getSkillByModelId(player, skillinfo.getSkill());
				if(skill!=null){
					skillinfo.setLevel(skill.getRealLevel(player));
				}else{
					skillinfo.setLevel(1);
				}
			}
			info.getSkills().add(skillinfo);
		}
		
		return info;
	}
	
	/**
	 * 广播骑乘兵器变化消息
	 */
	public void broadcastHorseWeaponInfo(Player player){
		HorseWeapon weapon = getHorseWeapon(player);
		if(weapon==null){
			return;
		}
		ResWearHorseWeaponStateMessage msg = new ResWearHorseWeaponStateMessage();
		msg.setPlayerid(player.getId());
		msg.setCurlayer((short)weapon.getCurLayer());
		msg.setStatus(isWearHorseWeapon(player)?(byte)1:(byte)0);
		MessageUtil.tell_round_message(player, msg);
	}
	
	/**
	 * 计算骑兵对角色加成的属性战斗力
	 */
	public int getHorseWeaponAttrFightPower(Player player, HorseWeapon horseweapon){
		int fightpower = 0;
		int layer = horseweapon.getLayer();
		int level = getHorseWeaponLevel(horseweapon);
		Q_horseweapon_attrBean bean = DataManager.getInstance().q_horseweapon_attrContainer.getMap().get(layer+"_"+level);
		PlayerAttribute att = new PlayerAttribute();
		att.setMaxHp(0);
		att.setMaxMp(0);
		att.setMaxSp(0);
		att.setCrit(0);
		att.setDefense(0);
		att.setAttack(0);
		att.setDodge(0);
		att.setLuck(0);
		att.setAttackSpeed(0);
		att.setSpeed(0);
		if(bean!=null){
			att.setAttack(bean.getQ_attack());
			att.setDefense(bean.getQ_defence());
			att.setCrit(bean.getQ_critical());
			att.setDodge(bean.getQ_doge());
			att.setMaxHp(bean.getQ_hp());
			att.setMaxMp(bean.getQ_mp());
			att.setMaxSp(bean.getQ_sp());
			att.setAttackSpeed(bean.getQ_attack_speed());
			att.setSpeed(bean.getQ_move_speed());
		}
		fightpower = FightPowerManager.getInstance().calAttrFightPower(att);
		return fightpower;
	}

	/**每天清理清除祝福值和冷却次数
	 * 
	 */
	public void clearDay(HorseWeapon weapon){
		int xday = TimeUtil.GetCurDay(CLEAR_HOUR);
		if(weapon.getTimeday() != xday ){
			weapon.setTimeday(xday);
			weapon.setDayexp(0);
			if( weapon.getDayupnum() > weapon.getHisupnum()){
				weapon.setHisupnum(weapon.getDayupnum());
			}
			weapon.setDayupnum(0);
			if( weapon.getDayblessvalue() > weapon.getHisblessvalue()){
				weapon.setHisblessvalue(weapon.getDayblessvalue());
			}
			weapon.setDayblessvalue(0);
		}
	}
	
	

	/**加祝福值
	 * 
	 * @param player
	 * @param num
	 */
	public void addDaybless(Player player ,int num){
		HorseWeapon weapon = getHorseWeapon(player);
		clearDay(weapon);
		weapon.setDayblessvalue(num + weapon.getDayblessvalue());
		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("祝福值增加了{1}"), num+"");
		sendHorseWeaponInfo(player);
	}
	
	
	
	/**测试骑兵
	 * 
	 * @param player
	 * @param lv
	 */
	public void testHorseWeapon(Player player,int lv ){
		HorseWeapon horseWeapon = getHorseWeapon( player);
		if (horseWeapon != null) {
			if (lv > LAYER_MAX) {
				lv = LAYER_MAX;
			}
			horseWeapon.setLayer(lv);
			sendHorseWeaponInfo(player);
		}
		
	}
}

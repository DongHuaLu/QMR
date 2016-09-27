package com.game.hiddenweapon.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_hiddenweaponBean;
import com.game.data.bean.Q_hiddenweapon_attrBean;
import com.game.data.bean.Q_hiddenweapon_skillBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_skill_modelBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.fightpower.manager.FightPowerManager;
import com.game.hiddenweapon.bean.HiddenWeaponInfo;
import com.game.hiddenweapon.bean.HiddenWeaponSkillIco;
import com.game.hiddenweapon.bean.HiddenWeaponSkillInfo;
import com.game.hiddenweapon.bean.OthersHiddenWeaponInfo;
import com.game.hiddenweapon.log.HiddenWeaponLog;
import com.game.hiddenweapon.message.ResHiddenWeaponErrorMessage;
import com.game.hiddenweapon.message.ResHiddenWeaponInfoMessage;
import com.game.hiddenweapon.message.ResHiddenWeaponLevelUpSkillMessage;
import com.game.hiddenweapon.message.ResHiddenWeaponSkillIcoMessage;
import com.game.hiddenweapon.message.ResHiddenWeaponStageUpPanelMessage;
import com.game.hiddenweapon.message.ResHiddenWeaponStageUpResultMessage;
import com.game.hiddenweapon.message.ResWearHiddenWeaponStateMessage;
import com.game.hiddenweapon.script.IHiddenWeaponSkillScript;
import com.game.hiddenweapon.structs.HiddenWeapon;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttribute;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.script.structs.ScriptEnum;
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

public class HiddenWeaponManager {
	
	protected static Logger logger = Logger.getLogger(HiddenWeaponManager.class);

	private static Object obj = new Object();
	private static final byte WEAR=1;	  //装备
	private static final byte UNWEAR=0;	  //未装备
	private static short LAYER_MAX=7;	  //目前开放的最高阶数
	private static int CLEAR_HOUR=0;	  //祝福值清理时间
	private static Skill DEFAULT_SKILL = null;

	//玩家管理类实例
	private static HiddenWeaponManager manager;
	public static HiddenWeaponManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new HiddenWeaponManager();
			}
		}
		return manager;
	}
	
	private HiddenWeaponManager(){}
	
	/**
	 * 得到暗器
	 * @param player
	 */
	public HiddenWeapon getHiddenWeapon(Player player){
		List<HiddenWeapon> hiddenweaponlist = player.getHiddenweaponlist();
		if (hiddenweaponlist==null || hiddenweaponlist.size() == 0) {
			return null;
		}
		return hiddenweaponlist.get(0);
	}
	
	/**
	 * 激活暗器
	 * @param player
	 * @param itemid
	 */
	public boolean activateHiddenWeapon(Player player){
		if (player.getLevel() < 70) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您未达到70级，无法激活暗器系统"));
			return false;
		}
		HiddenWeapon weapon = getHiddenWeapon(player);
		//判断暗器是否存在
		if(weapon!=null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("暗器已经存在。"));
			return false;
		}
		
		long action = Config.getId();
		if (!BackpackManager.getInstance().checkGold(player, 300)
				|| !BackpackManager.getInstance().changeGold(player, -300, Reasons.HIDDENWEAPON_GOLD, action)) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("激活暗器失败,元宝不足"));
			return false;
		}

		//激活1级暗器
		weapon = new HiddenWeapon();
		weapon.setLayer(1);
		weapon.setId(Config.getId());
		weapon.setOvertime(System.currentTimeMillis() + (long)7 * 24 * 60 * 60 * 1000);
		weapon.setState(WEAR);
		
		player.getHiddenweaponlist().add(weapon);
		
		try{
			//日志记录 激活兵器
			HiddenWeaponLog log = new HiddenWeaponLog();
			log.setUserid(player.getUserId());
			log.setRoleid(String.valueOf(player.getId()));
			log.setUsername(player.getUserName());
			log.setBeforerank(0);
			log.setAfterrank(1);
			log.setBeforeexp(0);
			log.setAfterexp(0);
			log.setItemnum(1);
			log.setCosttype(3);
			log.setActionid(action);
			log.setSid(WServer.getInstance().getServerId());
			LogService.getInstance().execute(log);
		}catch (Exception e) {
			logger.error(e, e);
		}
		
		countHiddenWeaponAttribute(player);
		sendHiddenWeaponInfo(player);
		this.broadcastHiddenWeaponInfo(player);
		
		ManagerPool.playerManager.stSyncExterior(player);
		
		try{
			int afterFightPower = getHiddenWeaponAttrFightPower(player, weapon); //激活后战斗力- 用于引导公告
			
			//取得一阶暗器名称
			String weaponname = "";
			Q_hiddenweaponBean bean = DataManager.getInstance().q_hiddenweaponContainer.getMap().get(1);
			if(bean!=null){
				weaponname = bean.getQ_name();
			}
			//发送暗器激活成功的引导
			ParseUtil parseUtil = new ParseUtil();
			parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜【%s】成功激活暗器【%s】战斗力提升%d！{@}"), player.getName(), weaponname, afterFightPower), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.HIDDENWEAPON.getValue()));
			MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(), new ArrayList<GoodsInfoRes>(), GuideType.HIDDENWEAPON.getValue());
		}catch (Exception e) {
			logger.error(e, e);
		}
		return true;
	}
	
	/**
	 * 升级暗器
	 * @param player
	 */
	public void levelupHiddenWeapon(Player player, byte auto){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if (weapon==null) { // 没有暗器则激活暗器
			activateHiddenWeapon(player);
			return ;
		}
		if(weapon.getLayer() >= LAYER_MAX){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的暗器已经达到最高阶。"));
			sendFailedError(player);
			return;
		}
		
		Q_hiddenweaponBean weaponBean = ManagerPool.dataManager.q_hiddenweaponContainer.getMap().get(weapon.getLayer());
		if(weaponBean==null){
			logger.error("暗器" + weapon.getLayer() + "等级不存在");
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
		Q_hiddenweaponBean frombean = weaponBean;
		int preFightPower = getHiddenWeaponAttrFightPower(player, weapon); //升级前战斗力
		
		//根据暗器，判断所需道具
		int costItemModelId = weaponBean.getQ_need_modelid();
		int costNum = weaponBean.getQ_item_num();
		//是否需要花费元宝
		boolean costGold = false;
		
		long actionId = Config.getId();
		
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
				if(!ManagerPool.shopManager.autoRemove(player, costItemModelId, costNum, Reasons.HIDDENWEAPON_GOLD, actionId)){
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要{1}[{2}颗]，才可继续进阶."),itemBean.getQ_name(),String.valueOf(costNum));
					sendFailedError(player);
					return;
				}
			}else{
				if(!ManagerPool.backpackManager.removeItem(player, costItemModelId, costNum, Reasons.HIDDENWEAPON_ITEM, actionId)){
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要{1}[{2}颗]，才可继续进阶."),itemBean.getQ_name(),String.valueOf(costNum));
					sendFailedError(player);
					return;
				}
			}
		}else {
			return;
		}

		ManagerPool.backpackManager.changeMoney(player, -weaponBean.getQ_need_money(), Reasons.HIDDENWEAPON_MONEY, actionId);
		
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
			weapon.setOvertime(-1);
			this.wearHiddenWeapon(player, weapon.getLayer());
			
			weaponBean = ManagerPool.dataManager.q_hiddenweaponContainer.getMap().get(weapon.getLayer());
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
				ManagerPool.playerManager.addExp(player, exp,AttributeChangeReason.HIDDEN_WEAPON);
			}else{
				exp = 0;
			}
		}
		ResHiddenWeaponStageUpResultMessage msg = new ResHiddenWeaponStageUpResultMessage();
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
			sendHiddenWeaponInfo(player);
			countHiddenWeaponAttribute(player);
			this.broadcastHiddenWeaponInfo(player);
			ManagerPool.playerManager.stSyncExterior(player);
			
			try{
				afterFightPower = getHiddenWeaponAttrFightPower(player, weapon); //升级后战斗力
				int upvalue = afterFightPower-preFightPower;
				Q_hiddenweaponBean tobean = DataManager.getInstance().q_hiddenweaponContainer.getMap().get(afterrank);
				if(tobean!=null){
					if( tobean.getQ_is_announce()==1 ){ //是否需要发公告
						//取得暗器名称
						String fromweapon = "", toweapon = "";
						fromweapon = frombean.getQ_name(); 
						toweapon = tobean.getQ_name(); 
						//发送升级公告
						ParseUtil parseUtil = new ParseUtil();
						parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜【%s】将【%s】升阶至【%s】战斗力提升%d！{@}"), player.getName(), fromweapon, toweapon, upvalue), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.HIDDENWEAPON_UP.getValue()));
						MessageUtil.notify_All_player(Notifys.CHAT_BULL, parseUtil.toString(), new ArrayList<GoodsInfoRes>(), GuideType.HIDDENWEAPON_UP.getValue());
					}
				}
			}catch (Exception e) {
				logger.error(e, e);
			}
		}
		
		try{
			//日志记录 升级兵器
			HiddenWeaponLog log = new HiddenWeaponLog();
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
		ResHiddenWeaponErrorMessage error = new ResHiddenWeaponErrorMessage();
		error.setType((byte)0);
		MessageUtil.tell_player_message(player, error);
	}
	/**
	 * 设置暗器等级(人物升级时)
	 * @param player
	 */
	public void setHiddenWeaponLevel(Player player){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if(weapon==null){
			return;
		}

		countHiddenWeaponAttribute(player);
	}
	
	/**
	 * 装备暗器
	 * @param player
	 */
	public void wearHiddenWeapon(Player player, int layer){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if(weapon==null){
			return;
		}
		
		if(layer<=0 || layer>weapon.getLayer()){
			return;
		}
		
		if(weapon.getOvertime()!=-1 && System.currentTimeMillis()>weapon.getOvertime()){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的暗器已经过期。"));
			return;
		}
		
		if(weapon.getLayer()==layer&&weapon.getState()==WEAR){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经装备此暗器了。"));
			return;
		}
		
		weapon.setLayer(layer);
		weapon.setState(WEAR);
		
		countHiddenWeaponAttribute(player);
		this.broadcastHiddenWeaponInfo(player);
		ManagerPool.playerManager.stSyncExterior(player);
	}
	
	/**
	 * 卸下暗器
	 * @param player
	 */
	public void unwearHiddenWeapon(Player player){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if(weapon==null){
			return;
		}
		
		if(weapon.getState()==UNWEAR){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有装备暗器。"));
			return;
		}
		
		weapon.setState(UNWEAR);
		countHiddenWeaponAttribute(player);
		this.broadcastHiddenWeaponInfo(player);
		ManagerPool.playerManager.stSyncExterior(player);
	}
	
	/**
	 * 返回暗器可触发技能
	 * @param player
	 * @return
	 */
	public Skill getHiddenWeaponSkillTriggerByAttack(Player player){
		if (!isWearHiddenWeapon(player)) {
			return null;
		}
		
		HiddenWeapon hideWeapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
		if (hideWeapon == null) {
			return null;
		}
		
		Skill skill = getIcoSkill(hideWeapon);
		if (skill == null) {
			return null;
		}
		
		// 根据次数来判定能否触发
		hideWeapon.setIconCount(hideWeapon.getIconCount() + 1);
		ResHiddenWeaponSkillIcoMessage msg = new ResHiddenWeaponSkillIcoMessage();
		msg.setIco(getIco(hideWeapon));
		MessageUtil.tell_player_message(player, msg);
		
		Q_hiddenweaponBean hiddeanwaponBean = ManagerPool.dataManager.q_hiddenweaponContainer.getMap().get(hideWeapon.getLayer());
		if (hiddeanwaponBean == null) {
			return null;
		}
		
		int times = hiddeanwaponBean.getQ_throw_times();
		Iterator<Skill> it = hideWeapon.getSkills().values().iterator();
		while (it.hasNext()) {
			Skill tmpSkill = it.next();
			Q_hiddenweapon_skillBean hiddenweaponSkillBean = ManagerPool.dataManager.q_hiddenweapon_skillContainer.getMap().get(tmpSkill.getSkillModelId() + "_" + tmpSkill.getSkillLevel());
			if (hiddenweaponSkillBean != null) {
				times -= hiddenweaponSkillBean.getQ_reduce_times(); // 这里负数可以不管
			}
		}
		
		if (times <= hideWeapon.getIconCount()) {
			return skill;
		}
		
		return null;
	}

	private Skill getIcoSkill(HiddenWeapon hideWeapon) {
		Skill skill = hideWeapon.getSkillByIndex(hideWeapon.getIcoIndex());
		boolean flag = false;
		if (skill == null) {
			flag = true;
		} else {
			Q_hiddenweapon_skillBean tmpBean = ManagerPool.dataManager.q_hiddenweapon_skillContainer.getMap().get(skill.getSkillModelId() + "_" + skill.getSkillLevel());
			if (tmpBean == null || tmpBean.getQ_type() == 0) {
				flag = true;
			}
		}
		
		if (flag) {
			if (DEFAULT_SKILL == null) {
				DEFAULT_SKILL = new Skill();
				DEFAULT_SKILL.setId(Config.getId());
				DEFAULT_SKILL.setSkillModelId(25010);
				DEFAULT_SKILL.setSkillLevel(1);
			}
			skill = DEFAULT_SKILL;
		}
		return skill;
	}

	/**
	 * 重新计算暗器属性
	 * @param player
	 */
	public void countHiddenWeaponAttribute(Player player){
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.HIDDEN_WEAPON);
	}
	
	/**
	 * 打开暗器升阶面板
	 * @param player
	 */
	public void openHiddenWeaponStageUp(Player player){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if(weapon!=null){
			clearDay(weapon);
			
			ResHiddenWeaponStageUpPanelMessage msg = new ResHiddenWeaponStageUpPanelMessage();
			msg.setExp(weapon.getDayblessvalue());
			MessageUtil.tell_player_message(player, msg);
		}
	}
	
	/**
	 * 获取暗器等级
	 * @param weapon
	 * @return
	 */
	public int getHiddenWeaponLevel(HiddenWeapon weapon){
		return 0;
	}
	
	/**
	 * 获取暗器等级
	 * @param weapon
	 * @return
	 */
	public int getHiddenWeaponLayer(Player player){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if(weapon!=null){
			return weapon.getLayer();
		}
		return 0;
	}

	
	/**
	 * 是否装备暗器
	 * @return
	 */
	public boolean isWearHiddenWeapon(Player player){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if(weapon!=null && weapon.getState()==WEAR){
			return true;
		}
		return false;
	}
	
	/**
	 * 发送人物暗器信息
	 * @param player
	 */
	public void sendHiddenWeaponInfo(Player player){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if(weapon==null){
			// 没有就发0级
			ResHiddenWeaponInfoMessage msg = new ResHiddenWeaponInfoMessage();
			msg.setPlayerid(player.getId());
			HiddenWeaponInfo info = new HiddenWeaponInfo();
			info.setLayer((short)0);
			HiddenWeaponSkillIco ico = new HiddenWeaponSkillIco();
			info.setIco(ico);
			msg.setInfo(info);
			MessageUtil.tell_player_message(player, msg);
			return;
		}
		clearDay(weapon);
		
		ResHiddenWeaponInfoMessage msg = new ResHiddenWeaponInfoMessage();
		msg.setPlayerid(player.getId());
		HiddenWeaponInfo info = new HiddenWeaponInfo();
		info.setLayer((short)weapon.getLayer());
		info.setCurlayer((short)weapon.getLayer());
		info.setStatus(isWearHiddenWeapon(player)?(byte)1:(byte)0);
		info.setBless(weapon.getDayblessvalue());
		info.setSkills(this.getSkillInfo(weapon, player));
		if(weapon.getOvertime()==-1){
			info.setTime(-1);
		}else{
			long time = weapon.getOvertime() - System.currentTimeMillis();
			if(time < 0) time = 0;
			info.setTime((int)(time/1000));
		}
		info.setIco(getIco(weapon));
		msg.setInfo(info);
		MessageUtil.tell_player_message(player, msg);
	}
	
	private HiddenWeaponSkillIco getIco(HiddenWeapon weapon) {
		HiddenWeaponSkillIco ico = new HiddenWeaponSkillIco();
		ico.setSkill(weapon.getIcoIndex() + 1); // 从1开始的索引
		ico.setTimes(weapon.getIconCount());
		return ico;
	}

	/**
	 * 发送他人暗器信息
	 * @param player
	 */
	public OthersHiddenWeaponInfo getOtherHiddenWeaponInfo(Player player){
		HiddenWeapon weapon = getHiddenWeapon(player);
		OthersHiddenWeaponInfo info = new OthersHiddenWeaponInfo();
		if(weapon==null){
			return info;
		}
		info.setLayer((short)weapon.getLayer());
		info.setCurlayer((short)weapon.getLayer());
		info.setSkills(getSkillInfo(weapon, player));
		return info;
	}
	
	public List<HiddenWeaponSkillInfo> getSkillInfo(HiddenWeapon weapon, Player player) {
		List<HiddenWeaponSkillInfo> skillInfo = new ArrayList<HiddenWeaponSkillInfo>();
		Iterator<String> it = weapon.getSkills().keySet().iterator();
		while(it.hasNext()) {
			String index = it.next();
			Skill tmpSkill = weapon.getSkills().get(index);
			HiddenWeaponSkillInfo skillinfo = new HiddenWeaponSkillInfo();
			skillinfo.setLevel(tmpSkill.getSkillLevel());
			skillinfo.setSkill(tmpSkill.getSkillModelId());
			skillinfo.setIndex((byte) (Byte.valueOf(index) + 1));
			skillInfo.add(skillinfo);
		}
		return skillInfo;
	}

	/**
	 * 计算暗器对角色加成的属性战斗力
	 */
	public int getHiddenWeaponAttrFightPower(Player player, HiddenWeapon hiddenweapon){
		int fightpower = 0;
		Q_hiddenweapon_attrBean bean = DataManager.getInstance().q_hiddenweapon_attrContainer.getMap().get(hiddenweapon.getLayer());
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
		}
		fightpower = FightPowerManager.getInstance().calAttrFightPower(att);
		return fightpower;
	}

	/**每天清理清除祝福值和冷却次数
	 * 
	 */
	public void clearDay(HiddenWeapon weapon){
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
		HiddenWeapon weapon = getHiddenWeapon(player);
		clearDay(weapon);
		weapon.setDayblessvalue(num + weapon.getDayblessvalue());
		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("祝福值增加了{1}"), num+"");
		sendHiddenWeaponInfo(player);
	}
	
	/**
	 * 广播暗器变化消息
	 */
	public void broadcastHiddenWeaponInfo(Player player){
		HiddenWeapon weapon = getHiddenWeapon(player);
		if(weapon==null){
			return;
		}
		ResWearHiddenWeaponStateMessage msg = new ResWearHiddenWeaponStateMessage();
		msg.setPlayerid(player.getId());
		msg.setCurlayer((short)weapon.getLayer());
		msg.setStatus(isWearHiddenWeapon(player)?(byte)1:(byte)0);
		MessageUtil.tell_round_message(player, msg);
	}

	public boolean studySkill(Player player, int skillId, int skillLevel) {
		Q_hiddenweapon_skillBean skillBean = ManagerPool.dataManager.q_hiddenweapon_skillContainer.getMap().get(skillId + "_" + skillLevel);
		if (skillBean == null) {
			return false;
		}
		
		HiddenWeapon weapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
		if (weapon == null) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("需要激活暗器才能使用"));
			return false;
		}
		
		if (skillBean.getQ_need_level() > weapon.getLayer()) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉,您需要先将暗器进阶到{1}阶"), skillBean.getQ_need_level() + "");
			return false;
		}
		
		
		byte index = -1;
		IHiddenWeaponSkillScript script = (IHiddenWeaponSkillScript)ManagerPool.scriptManager.getScript(ScriptEnum.HIDDENWEAPON_SKILL);
		if(script!=null) {
			try{
				index = (byte) script.onHiddenWeaponSkillStudy(player, skillId, skillLevel);
			}catch (Exception e) {
				logger.error(e, e);
				return false;
			}
		}else{
			logger.error("学习暗器技能失败,暗器技能脚本不存在");
			return false;
		}
		
		if (index == -1) {
			logger.error("学习暗器技能失败,选取不到可用的格子");
			return false;
		}
		
		weapon.removeSkillByIndex(index);
		Skill skill = new Skill();
		skill.setId(Config.getId());
		skill.setSkillLevel(skillLevel);
		skill.setSkillModelId(skillId);
		weapon.addSkill(index, skill);
		Q_skill_modelBean skillModel = ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skillBean.getQ_skill());
		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您成功习得暗器技能{1}"), skillModel.getQ_skillName());
		
		// 这里如果说移除的技能刚好是ico显示的技能,那么就重新计算下一个ico
		if (index == weapon.getIcoIndex()) {
			countNextIco(player);
		}
		
		// 同步到前端 
		ResHiddenWeaponLevelUpSkillMessage msg = new ResHiddenWeaponLevelUpSkillMessage();
		msg.setIndex((byte) (index + 1));
		msg.setSkills(getSkillInfo(weapon, player));
		MessageUtil.tell_player_message(player, msg);
		
		return true;
	}

	public void countNextIco(Player player) {
		HiddenWeapon hideWeapon = ManagerPool.hiddenWeaponManager.getHiddenWeapon(player);
		if (hideWeapon == null) {
			return ;
		}
		
		Q_hiddenweaponBean hiddenweaponBean = ManagerPool.dataManager.q_hiddenweaponContainer.getMap().get(hideWeapon.getLayer());
		if (hiddenweaponBean == null) {
			return ;
		}
		if (hiddenweaponBean.getQ_open_grid() != 0) {
			hideWeapon.setIcoIndex((byte) ((hideWeapon.getIcoIndex() + 1) % hiddenweaponBean.getQ_open_grid()));
		}
		hideWeapon.setIconCount(0);
		
		ResHiddenWeaponSkillIcoMessage msg = new ResHiddenWeaponSkillIcoMessage();
		msg.setIco(getIco(hideWeapon));
		MessageUtil.tell_player_message(player, msg);
	}
}
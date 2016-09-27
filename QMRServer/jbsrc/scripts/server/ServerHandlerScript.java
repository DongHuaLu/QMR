package scripts.server;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.chat.bean.GoodsInfoRes;
import com.game.command.Handler;
import com.game.config.Config;
import com.game.data.bean.Q_horseweaponBean;
import com.game.data.bean.Q_horseweapon_skillBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_realm_basicBean;
import com.game.data.bean.Q_realm_breakBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.fightpower.manager.FightPowerManager;
import com.game.horseweapon.log.HorseWeaponLog;
import com.game.horseweapon.message.ResHorseWeaponErrorMessage;
import com.game.horseweapon.message.ResHorseWeaponStageUpResultMessage;
import com.game.horseweapon.structs.HorseWeapon;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.message.Message;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.realm.log.RealmLog;
import com.game.realm.message.ReqBreakthroughToGameMessage;
import com.game.realm.message.ResBreakthroughToClientMessage;
import com.game.realm.message.ResRealmErrToClientMessage;
import com.game.realm.structs.Realm;
import com.game.script.structs.ScriptEnum;
import com.game.server.impl.WServer;
import com.game.server.script.IServerHandlerScript;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;

public class ServerHandlerScript implements IServerHandlerScript {
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(ServerHandlerScript.class);
	
//	private HashMap<Long,HashMap<String, HashMap<String, String>>> chatCount=new HashMap<Long, HashMap<String,HashMap<String, String>>>();
	
	
	@Override
	public int getId() {
		return ScriptEnum.HANDLER_ACTION;
	}
	

	/**
	 * 韩国版需要截断的消息ID
	 */
	private HashSet<Integer> hgpupugameset = new  HashSet<Integer>();
	
	
	public ServerHandlerScript(){
		hgpupugameset.add(10000000);
	
	}
	
	@Override //false 为截断消息
	public boolean check(Handler handler) {
		Message msg = handler.getMessage();
		if (msg != null) {
			if(WServer.getInstance().getServerWeb().equals("hgpupugame")) {	
				if(hgpupugameset.contains(msg.getId())){
					return false;
				}
			}
		}
	
		
		
		
//		if (handler instanceof ReqHorseWeaponStageUpStartHandler) {
//			ReqHorseWeaponStageUpStartMessage gmsg = (ReqHorseWeaponStageUpStartMessage)handler.getMessage();
//			levelupHorseWeapon((Player) handler.getParameter(), gmsg.getType());
//			return false;
//		}
//		
		
//		if (handler instanceof ReqOtherPlayerInfoHandler ) {
//			ReqOtherPlayerInfoMessage msg = (ReqOtherPlayerInfoMessage)handler.getMessage();
//			sendPlayerDetails((Player)handler.getParameter(), msg.getPersonId(),msg.getType());
//			return false;//false为截断
//		}
		
//		//完美境界丹
//		if (handler instanceof ReqBreakthroughToGameHandler) {
//			ReqBreakthroughToGameMessage gmsg = (ReqBreakthroughToGameMessage)handler.getMessage();
//			realmBreak((Player) handler.getParameter(), gmsg);
//			return false;//false为截断
//		}
		
		
		
		return true;
	}
	

	
	


	/**
	 * 升级骑战兵器
	 * @param player
	 */
	public void levelupHorseWeapon(Player player, byte auto){
		HorseWeapon weapon = ManagerPool.horseWeaponManager.getHorseWeapon(player);
		//判断骑战兵器是否存在
		if(weapon==null){
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您还没有骑战兵器。"));
			sendFailedError(player);
			return;
		}
		//达到最高阶
		if(weapon.getLayer() >= 7){
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
		int preFightPower = ManagerPool.horseWeaponManager.getHorseWeaponAttrFightPower(player, weapon); //升级前战斗力
		
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
		
		ManagerPool.horseWeaponManager.clearDay(weapon);
		
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
			ManagerPool.horseWeaponManager.sendHorseWeaponInfo(player);
			//重新计算属性
			ManagerPool.horseWeaponManager.countHorseWeaponAttribute(player);
			//广播外貌消息
			ManagerPool.horseWeaponManager.broadcastHorseWeaponInfo(player);
			
			ManagerPool.playerManager.stSyncExterior(player);
			
			try{
				afterFightPower = ManagerPool.horseWeaponManager.getHorseWeaponAttrFightPower(player, weapon); //升级后战斗力
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
	
	
	//------------------------------突破境界-------------------------------------
	
	

	
	/**玩家突破境界消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void realmBreak(Player player,ReqBreakthroughToGameMessage msg) {
		try {
			int wmitemid = 9198;//完美突破丹
			HashMap<Integer, Q_realm_breakBean> breakmap = ManagerPool.realmManager.getRealm_break();
			long action=Config.getId();
			Realm realm = player.getRealm();
			if (realm.getRealmlevel() == 0) {
//				//台湾版暂不开放
//				if (WServer.getInstance().getServerWeb().equals("twgmei") && !isopen) {	
//					return;
//				}
				Q_realm_breakBean breakdata = breakmap.get(0);
				if (player.getLevel() >= breakdata.getQ_break_level() ) {
					if (ManagerPool.realmManager.checkTakeMaterial(player,breakdata.getQ_break_item(),msg.getType())) {
						RealmLog realmLog = ManagerPool.realmManager.makeRealmLog(player , 2);//日志
						realmLog.setResult(1);
						int oldfightpower=FightPowerManager.getInstance().calAllFightPower(player);
						realm.setRealmlevel(1);
						ResBreakthroughToClientMessage cmsg = new ResBreakthroughToClientMessage();
						cmsg.setBealmInfo(realm.getRealmInfo());
						cmsg.setResult(1);
						cmsg.setPrompt(ResManager.getInstance().getString("境界激活成功。"));
						MessageUtil.tell_player_message(player, cmsg);
						ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.REALM);
						int newfightpower = FightPowerManager.getInstance().calAllFightPower(player);
						newfightpower = newfightpower - oldfightpower;
						
						ParseUtil parseUtil = new ParseUtil();
						if (newfightpower > 0) {
							parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】成功激活境界，提升%d战斗力{@}"), player.getName(),newfightpower), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.REALM.getValue()));
						}else {
							parseUtil.setValue(String.format(ResManager.getInstance().getString("【%s】成功激活境界！{@}"), player.getName(),newfightpower), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.REALM.getValue()));
						}
						
						MessageUtil.notify_All_player(Notifys.CHAT_BULL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.REALM.getValue());
						ManagerPool.realmManager.writeRealmLog(player, realmLog);
						ManagerPool.mapManager.refreshPlayerRound(player);
					}
				}else {
					return;  //未达到激活等级
				}
			}else {
				Q_realm_basicBean basic = ManagerPool.realmManager.getRealm_basic().get(realm.getRealmlevel());	//当前境界
				Q_realm_basicBean basicup = ManagerPool.realmManager.getRealm_basic().get(realm.getRealmlevel()+1);//下一个境界基本数据
				Q_realm_breakBean breakdata = breakmap.get(realm.getRealmlevel());	//下一个突破境界-数据 (几率啥的，从当前阶读)
				
				if (basicup == null) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("境界已达最高。"));
					return;	//没有下一个境界
				}
				if (breakdata.getQ_break_level() > player.getLevel()) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("人物等级达到[{1}]级后，才可继续突破境界。"),breakdata.getQ_break_level()+"");
					return; //等级不足
				}
				if (player.getMoney() < breakdata.getQ_break_money()) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("需要{1}铜币,才可继续突破境界"),breakdata.getQ_break_money()+"");
					ResRealmErrToClientMessage cmsg=new ResRealmErrToClientMessage();
					cmsg.setType(2);
					MessageUtil.tell_player_message(player, cmsg);
					return; //铜币不足
				}
				int  result = 0;
				if (ManagerPool.realmManager.checkallowBreak(player,basic)) {
					int wmitemnum  = ManagerPool.backpackManager.getItemNum(player,wmitemid);	//完美武学参悟丹
					if (ManagerPool.backpackManager.removeItem(player, wmitemid, 1, Reasons.REALM_ITEM, action) || ManagerPool.realmManager.checkTakeMaterial(player,breakdata.getQ_break_item(),msg.getType())) {
						RealmLog realmLog = ManagerPool.realmManager.makeRealmLog(player , 2);//日志
						ManagerPool.backpackManager.changeMoney(player, -breakdata.getQ_break_money(), Reasons.REALM_GOLD, action);
						int bknum = realm.getBreaknum() + 1;
	
						if (realm.getBlessingnum() >= breakdata.getQ_fail_blessing_limit()) {//必然成功
							result = 1;
						}else {
							if (bknum < breakdata.getQ_success_min()) {	//必然失败
								result = 0;
							}else if(bknum >= breakdata.getQ_success_max()){	//必然成功
								result = 1;
							}else {
								if(RandomUtils.isGenerate2(10000, breakdata.getQ_cipher_random())){	//	进入随机
									result = 1;	//成功
								}else {
									result = 0;
								}
							}
						}
						ResBreakthroughToClientMessage cmsg = new ResBreakthroughToClientMessage();
						if (result == 1) {
							realm.setRealmlevel(realm.getRealmlevel() + 1);
							realm.setBreaknum(0);	//失败次数清0
							realm.setBlessingnum(0);	//祝福值清0
							realm.setIntensifylevel(0);	//当前阶强化次数清0
							int oldfightpower=FightPowerManager.getInstance().calAllFightPower(player);
							ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.REALM);
							if (breakdata.getQ_isbulletin() == 1) {
								int newfightpower = FightPowerManager.getInstance().calAllFightPower(player);
								newfightpower = newfightpower - oldfightpower;
								ParseUtil parseUtil = new ParseUtil();
								if (newfightpower > 0) {
									MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("恭喜玩家【{1}】成功突破境界至：【{2}】,战斗力提升{3}。"),player.getName(),basicup.getQ_name(),newfightpower+"");
									parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】成功突破境界至：【%s】,战斗力提升%d!{@}"), player.getName(),basicup.getQ_name(),newfightpower), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.REALM_TUPO.getValue()));
								}else {
									MessageUtil.notify_All_player(Notifys.SROLL, ResManager.getInstance().getString("恭喜玩家【{1}】成功突破境界至：【{2}】。"),player.getName(),basicup.getQ_name());
									parseUtil.setValue(String.format(ResManager.getInstance().getString("恭喜玩家【%s】成功突破境界至：【%s】{@}"), player.getName(),basicup.getQ_name()), new ParseUtil.VipParm(VipManager.getInstance().getPlayerVipId(player),GuideType.REALM_TUPO.getValue()));
								}
								MessageUtil.notify_All_player(Notifys.CHAT_BULL,parseUtil.toString(),new ArrayList<GoodsInfoRes>(),GuideType.REALM_TUPO.getValue());
							
							}else {
								cmsg.setPrompt(String.format(ResManager.getInstance().getString("恭喜您，突破境界成功至【%s】"),basicup.getQ_name()));
								//MessageUtil.notify_player(player, Notifys.CHAT_ROLE, ResManager.getInstance().getString("恭喜您，突破境界成功至【{1}】"), basicup.getQ_name());
							}
	
							String[] items = breakdata.getQ_break_item().split(Symbol.XIAHUAXIAN_REG);
							
							if (wmitemnum > 0) {
								String name = ManagerPool.backpackManager.getName(wmitemid);
								MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("突破境界成功，使用：[{1}]{2}个，[铜币]{3}。"),name,"1",breakdata.getQ_break_money()+"");
							}else {
								String name = ManagerPool.backpackManager.getName(Integer.parseInt(items[0]));
								MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("突破境界成功，使用：[{1}]{2}个，[铜币]{3}。"),name,items[1],breakdata.getQ_break_money()+"");
							}
							
							ManagerPool.mapManager.refreshPlayerRound(player);
						}else {
							realm.setBreaknum(realm.getBreaknum() + 1);//加失败次数
							//加祝福值
							if (realm.getBlessingnum() < breakdata.getQ_fail_blessing_limit()) {
								int biess = RandomUtils.random(breakdata.getQ_fail_blessing_min(), breakdata.getQ_fail_blessing_max());
								if (biess + realm.getBlessingnum() > breakdata.getQ_fail_blessing_limit()) {
									realm.setBlessingnum(breakdata.getQ_fail_blessing_limit());
								}else {
									realm.setBlessingnum(biess + realm.getBlessingnum());
								}
							}
							
							//加失败经验
							if (realm.getFaillimitexp() <  breakdata.getQ_fail_limit_exp()) {
								List<Integer> rndList = new ArrayList<Integer>();
								List<Integer> expList = new ArrayList<Integer>();
								int[] general = ManagerPool.realmManager.resolve(breakdata.getQ_fail_general_exp());
								int[] crit = ManagerPool.realmManager.resolve(breakdata.getQ_fail_crit_exp());
								int[] maxcrit = ManagerPool.realmManager.resolve(breakdata.getQ_fail_maxcrit_exp());
								rndList.add(general[0]);
								rndList.add(crit[0]);
								rndList.add(maxcrit[0]);
								expList.add(general[1]);//正常经验
								expList.add(crit[1]);	//小暴击经验
								expList.add(maxcrit[1]);//大暴击经验
								int idx = RandomUtils.randomIndexByProb(rndList);
								int exp = expList.get(idx);
								realm.setFaillimitexp(exp + realm.getFaillimitexp());
								ManagerPool.playerManager.addExp(player, exp, AttributeChangeReason.REALM);
								cmsg.setExp(exp);
								cmsg.setType(idx);
								realmLog.setBreakexp(exp);
							}
							

							//MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("突破境界失败了，使用：[{1}]{2}个，[铜币]{3}。"),name,items[1],breakdata.getQ_break_money()+"");
							if (wmitemnum > 0) {
								String name = ManagerPool.backpackManager.getName(wmitemid);
								cmsg.setPrompt(String.format(ResManager.getInstance().getString("突破境界失败了，使用：[%s]%s个，[铜币]%d。"),name,"1",breakdata.getQ_break_money()));
							}else {
								String[] items = breakdata.getQ_break_item().split(Symbol.XIAHUAXIAN_REG);
								String name = ManagerPool.backpackManager.getName(Integer.parseInt(items[0]));
								cmsg.setPrompt(String.format(ResManager.getInstance().getString("突破境界失败了，使用：[%s]%s个，[铜币]%d。"),name,items[1],breakdata.getQ_break_money()));
							}
						}
		
						cmsg.setBealmInfo(realm.getRealmInfo());
						cmsg.setResult(result);
						MessageUtil.tell_player_message(player, cmsg);
						realmLog.setResult(result);
						ManagerPool.realmManager.writeRealmLog(player, realmLog);
					}
				}
			}
		} catch (Exception e) {
			logger.error("突破或者激活境界出错"+e,e);
		}
	}

	
	
	
	
	
	
	
	
}

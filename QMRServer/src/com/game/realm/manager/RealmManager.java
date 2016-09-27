package com.game.realm.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.game.backpack.manager.BackpackManager;
import com.game.chat.bean.GoodsInfoRes;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_realm_basicBean;
import com.game.data.bean.Q_realm_breakBean;
import com.game.dblog.LogService;
import com.game.fightpower.manager.FightPowerManager;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.message.ResScriptCommonPlayerToClientMessage;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.realm.log.RealmLog;
import com.game.realm.message.ReqBreakthroughToGameMessage;
import com.game.realm.message.ReqIntensifyToGameMessage;
import com.game.realm.message.ResBreakthroughToClientMessage;
import com.game.realm.message.ResIntensifyToClientMessage;
import com.game.realm.message.ResRealmErrToClientMessage;
import com.game.realm.message.ResRealmInfoToClientMessage;
import com.game.realm.structs.Realm;
import com.game.structs.Reasons;
import com.game.utils.MessageUtil;
import com.game.utils.ParseUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.vip.manager.VipManager;
import com.game.vip.struts.GuideType;


/**境界
 * 
 * @author zhangrong
 *
 */
public class RealmManager {
	private Logger log = Logger.getLogger(RealmManager.class);
	
	private static Object obj = new Object();
	//玩家管理类实例
	private static RealmManager manager;

	private RealmManager(){}
	
	public static RealmManager getInstance(){
		synchronized (obj) {
			if(manager == null){
				manager = new RealmManager();
			}
		}
		return manager;
	}
	

	
	
	/**境界突破几率表
	 * 
	 * @return
	 */
	public HashMap<Integer, Q_realm_breakBean> getRealm_break(){
		return ManagerPool.dataManager.q_realm_breakContainer.getMap();
	}
	/**是否开放
	 * true = 开放
	 * 
	 */
	private  boolean  isopen = true;
	
	
	/**境界基础表
	 * 
	 * @return
	 */
	public HashMap<Integer, Q_realm_basicBean> getRealm_basic(){
		return ManagerPool.dataManager.q_realm_basicContainer.getMap();
	}
	

	
	/**玩家突破境界消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqBreakthroughToGameMessage(Player player,ReqBreakthroughToGameMessage msg) {
		try {
			int wmitemid = 9198;//完美突破丹
			HashMap<Integer, Q_realm_breakBean> breakmap = getRealm_break();
			long action=Config.getId();
			Realm realm = player.getRealm();
			if (realm.getRealmlevel() == 0) {

				if (!isopen) {	
					return;
				}
				Q_realm_breakBean breakdata = breakmap.get(0);
				if (player.getLevel() >= breakdata.getQ_break_level() ) {
					if (checkTakeMaterial(player,breakdata.getQ_break_item(),msg.getType())) {
						RealmLog realmLog = makeRealmLog(player , 2);//日志
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
						writeRealmLog(player, realmLog);
						ManagerPool.mapManager.refreshPlayerRound(player);
					}
				}else {
					return;  //未达到激活等级
				}
			}else {
				Q_realm_basicBean basic = getRealm_basic().get(realm.getRealmlevel());	//当前境界
				Q_realm_basicBean basicup = getRealm_basic().get(realm.getRealmlevel()+1);//下一个境界基本数据
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
				if (checkallowBreak(player,basic)) {
					int wmitemnum  = ManagerPool.backpackManager.getItemNum(player,wmitemid);	//完美武学参悟丹
					if (ManagerPool.backpackManager.removeItem(player, wmitemid, 1, Reasons.REALM_ITEM, action) || checkTakeMaterial(player,breakdata.getQ_break_item(),msg.getType())) {
						RealmLog realmLog = makeRealmLog(player , 2);//日志
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
								int[] general = resolve(breakdata.getQ_fail_general_exp());
								int[] crit = resolve(breakdata.getQ_fail_crit_exp());
								int[] maxcrit = resolve(breakdata.getQ_fail_maxcrit_exp());
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
						writeRealmLog(player, realmLog);
					}
				}
			}
		} catch (Exception e) {
			log.error("突破或者激活境界出错"+e,e);
		}
	}

	
	
	/**产生日志
	 * @return type  = 1强化，2突破
	 * 
	 */
	public RealmLog makeRealmLog(Player player,int type){
		RealmLog realmLog = new RealmLog();
		Realm realm = player.getRealm();
		realmLog.setOldBlessingnum(realm.getBlessingnum());
		realmLog.setOldbreaknum(realm.getBreaknum());
		realmLog.setOldIntensifylevel(realm.getIntensifylevel());
		realmLog.setOldRealmlevel(realm.getRealmlevel());
		realmLog.setPlayerid(player.getId());
		realmLog.setType(type);
		return realmLog;
	}
	
	
	/**写日志
	 * 
	 */
	public void writeRealmLog(Player player , RealmLog realmLog){
		Realm realm = player.getRealm();
		realmLog.setNewBlessingnum(realm.getBlessingnum());
		realmLog.setNewbreaknum(realm.getBreaknum());
		realmLog.setNewIntensifylevel(realm.getIntensifylevel());
		realmLog.setNewRealmlevel(realm.getRealmlevel());
		LogService.getInstance().execute(realmLog);
	}
	
	

	
	/**检测属性是否满
	 * @return  false= 没有满
	 * 
	 */
	public boolean checkallowBreak(Player player ,Q_realm_basicBean bean){
		Realm realm = player.getRealm();
		if (realm.getRealmlevel() == 0) {
			return false;
		}
		
		int infy = realm.getIntensifylevel();
		int[] atts = resolve(bean.getQ_attack_limit());
		if (atts[0] + infy * bean.getQ_attack() < atts[1]) return false;
		
		atts = resolve(bean.getQ_defence_limit());
		if (atts[0] + infy * bean.getQ_defence() < atts[1]) return false;
		
		atts = resolve(bean.getQ_crit_limit());
		if (atts[0] + infy * bean.getQ_crit() < atts[1]) return false;
		
		atts = resolve(bean.getQ_dodge_limit());
		if (atts[0] + infy * bean.getQ_dodge() < atts[1]) return false;
		
		atts = resolve(bean.getQ_maxhp_limit());
		if (atts[0] + infy * bean.getQ_maxhp() < atts[1]) return false;
		
		atts = resolve(bean.getQ_maxsp_limit());
		if (atts[0] + infy * bean.getQ_maxsp() < atts[1]) return false;
		
		atts = resolve(bean.getQ_maxmp_limit());
		if (atts[0] + infy * bean.getQ_maxmp() < atts[1]) return false;
		
		atts = resolve(bean.getQ_attackspeed_limit());
		if (atts[0] + infy * bean.getQ_attackspeed() < atts[1]) return false;
		
		atts = resolve(bean.getQ_speed_limit());
		if (atts[0] + infy * bean.getQ_speed() < atts[1]) return false;
	
		atts = resolve(bean.getQ_neg_defence_limit());
		if (atts[0] + infy * bean.getQ_neg_defence() < atts[1]) return false;
		
		atts = resolve(bean.getQ_arrow_probability_limit());
		if (atts[0] + infy * bean.getQ_arrow_probability() < atts[1]) return false;
		
		return true;
	}
	
	
	/**解析属性 
	 *   默认属性|属性上限   
	 *   几率|经验
	 * @param str
	 * @return
	 */
	public int[] resolve(String str){
		String[] atts = str.split(Symbol.SHUXIAN_REG);
		if(atts.length >= 2){
			int[] attint = {Integer.parseInt(atts[0]),Integer.parseInt(atts[1])};
			return attint;
		}
		return null;
	}
		
	

	
	/**发送境界消息
	 * 
	 * @param player
	 */
	public void sendRealmInfo(Player player){
		Realm realm = player.getRealm();
		ResRealmInfoToClientMessage cmsg = new ResRealmInfoToClientMessage();
		cmsg.setBealmInfo(realm.getRealmInfo());
		MessageUtil.tell_player_message(player, cmsg);
		if (!isopen) {	
			return;
		}
		
		if (player.getLevel() >= 65 ){
			if(realm.getActivation() == 0) {	//65级第一次上线发送境界激活提示消息
				realm.setActivation(1);
				HashMap<String, Object> paramMap = new HashMap<String, Object>();
				paramMap.put("REALM", "1");
				ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
				sendMessage.setScriptid(1018137);//对应激活道具
				sendMessage.setType(1);
				sendMessage.setMessageData(JSON.toJSONString(paramMap));
				MessageUtil.tell_player_message(player, sendMessage);
			}
			if (realm.getRealmlevel()> 0 && realm.getRealmlevel() <= 7) {
				Q_realm_basicBean data = getRealm_basic().get(realm.getRealmlevel());
				if (data != null) {
					if ( checkallowBreak(player ,data)) {//属性已经加满后，提示突破
						if (realm.getBreakprompt() == 0) {
							realm.setBreakprompt((byte) 1);
							HashMap<String, Object> paramMap = new HashMap<String, Object>();
							paramMap.put("REALM", "2");
							ResScriptCommonPlayerToClientMessage sendMessage = new ResScriptCommonPlayerToClientMessage();
							sendMessage.setScriptid(1018137);
							sendMessage.setType(1);
							sendMessage.setMessageData(JSON.toJSONString(paramMap));
							MessageUtil.tell_player_message(player, sendMessage);
						}
					}
				}
			}
		}
		
	}
	
	
	
	
	/**
	 * 检查并收取材料
	 * @return 
	 */
	public boolean checkTakeMaterial(Player player,String string,int auto){
		ArrayList<Integer[]> itemlist =ManagerPool.equipstrengManager.getAnalyzeString(string);
		boolean is = true;
		String txt = "";
		int itemid = 0;
		long action=Config.getId();
		if (itemlist.size() > 0) {
			for (Integer[] integers : itemlist) {
				int num = ManagerPool.backpackManager.getItemNum(player,integers[0]);
				if (num < integers[1]) {
					Q_itemBean itemBean = ManagerPool.dataManager.q_itemContainer.getMap().get(integers[0]);
					is=false;
					txt=txt+" "+(integers[1]-num)+ResManager.getInstance().getString("个")+BackpackManager.getInstance().getName(itemBean.getQ_id());
					itemid = integers[0];
				}
			}

//			if (is) {
				for (Integer[] integers : itemlist) {
					boolean costGold =false;
					//判断材料是否足够
					int num = BackpackManager.getInstance().getItemNum(player, integers[0]);
					if(num < integers[1]){
						if (auto == 0) {
							return false;
						}else {
							costGold = true;
						}
					}
					
					//扣除材料
					if(costGold){
						if(!ManagerPool.shopManager.autoRemove(player, integers[0], integers[1], Reasons.REALM_ITEM, action)){
							MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("很抱歉，所需材料不足,缺少{1}。"),txt);
							ResRealmErrToClientMessage cmsg=new ResRealmErrToClientMessage();
							cmsg.setType(5);//元宝不足
							MessageUtil.tell_player_message(player, cmsg);
							return false;
						}
					}else{	
						if (ManagerPool.backpackManager.removeItem(player, integers[0], integers[1],Reasons.REALM_ITEM,action) == false) {
							MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("很抱歉，所需材料不足,缺少{1}。"),txt);
							ResRealmErrToClientMessage cmsg=new ResRealmErrToClientMessage();
							cmsg.setType(1);//道具不足
							MessageUtil.tell_player_message(player, cmsg);
							return false;
						}
					}
				}
				return true;
//			}else {
//				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("很抱歉，所需材料不足,缺少{1}。"),txt);
//			}
		}else {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("境界没有设定所需道具，停止。"));
		}
		return false;
	}
	

	
	
	/**玩家境界强化消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqIntensifyToGameMessage(Player player,ReqIntensifyToGameMessage msg) {
		Realm realm = player.getRealm();
		if (realm.getRealmlevel() == 0) {
			return;
		}
		
		Q_realm_basicBean data = getRealm_basic().get(realm.getRealmlevel());
		if(player.getZhenqi() < data.getQ_use_zhenqi() ){
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("当前境界强化需要{1}真气。"),data.getQ_use_zhenqi()+"");
			ResRealmErrToClientMessage cmsg=new ResRealmErrToClientMessage();
			cmsg.setType(3);
			MessageUtil.tell_player_message(player, cmsg);
			return ;
		}
		
		try {
			if ( !checkallowBreak(player ,data)) {
				RealmLog realmLog = makeRealmLog(player , 1);	//日志
				ManagerPool.playerManager.addZhenqi(player, -data.getQ_use_zhenqi() , AttributeChangeReason.REALM);
				ResIntensifyToClientMessage cmsg = new ResIntensifyToClientMessage();
				if(RandomUtils.isGenerate2(10000,data.getQ_true_random())){//	进入随机
					realm.setIntensifylevel(realm.getIntensifylevel()+ 1);
					cmsg.setBealmInfo(realm.getRealmInfo());
					cmsg.setResult(1);
					cmsg.setPrompt(String.format(ResManager.getInstance().getString("强化境界成功，使用真气 %d。"),data.getQ_use_zhenqi()));
					//MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("强化境界成功，使用真气 {1}。"),data.getQ_use_zhenqi()+"");
					ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.REALM);
					realmLog.setResult(1);
				}else {
					cmsg.setBealmInfo(realm.getRealmInfo());
					cmsg.setResult(0);
					cmsg.setPrompt(String.format(ResManager.getInstance().getString("强化境界失败了，使用真气 %d。"),data.getQ_use_zhenqi()));
					//MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("强化境界失败了，使用真气 {1}。"),data.getQ_use_zhenqi()+"");
				}
				MessageUtil.tell_player_message(player, cmsg);
				writeRealmLog(player, realmLog);
			}else {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("当前境界已经强化到上限。"));
				ResRealmErrToClientMessage cmsg=new ResRealmErrToClientMessage();
				cmsg.setType(4);
				MessageUtil.tell_player_message(player, cmsg);
			}
		} catch (Exception e) {
			log.error("境界强化出错："+e,e);
		}
	}

	public boolean isIsopen() {
		return isopen;
	}

	public void setIsopen(boolean isopen) {
		this.isopen = isopen;
	}
	


	
}

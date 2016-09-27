package com.game.longyuan.manager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.game.buff.structs.Buff;
import com.game.buff.structs.BuffConst;
import com.game.config.Config;
import com.game.data.bean.Q_longyuanBean;
import com.game.data.bean.Q_longyuan_expBean;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.longyuan.bean.LongYuanInfo;
import com.game.longyuan.bean.LongYuanPosTipsInfo;
import com.game.longyuan.bean.LongYuanStarMapTipsInfo;
import com.game.longyuan.bean.ShowEffectInfo;
import com.game.longyuan.log.LongYuanLog;
import com.game.longyuan.message.ReqLongYuanActivationMessage;
import com.game.longyuan.message.ReqLongYuanOpenMessage;
import com.game.longyuan.message.ReqLongYuanStarMapTipsMessage;
import com.game.longyuan.message.ReqLongYuanTipsMessage;
import com.game.longyuan.message.ResLongYuanActivationMessage;
import com.game.longyuan.message.ResLongYuanOpenMessage;
import com.game.longyuan.message.ResLongYuanPosTipsMessage;
import com.game.longyuan.message.ResLongYuanStarMapTipsMessage;
import com.game.longyuan.message.ResShowEffectToClientMessage;
import com.game.longyuan.structs.LongYuanData;
import com.game.manager.ManagerPool;
import com.game.player.message.ReqSyncPlayerLongyuanMessage;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.player.structs.PlayerState;
import com.game.player.structs.AttributeChangeReason;
import com.game.prompt.structs.Notifys;
import com.game.skill.structs.Skill;
import com.game.structs.Reasons;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.Symbol;
import com.game.utils.TimeUtil;

public class LongYuanManager {
	private Logger log = Logger.getLogger(LongYuanManager.class);
	private static Object obj = new Object();
	// 管理类实例
	private static LongYuanManager manager;

	//用来索引加技能等级
	private static HashMap<String, Integer> longyuanupmap = new HashMap<String, Integer>();
	
	private LongYuanManager(){
		createLongYuanSkilllvUpList();
	}
	private HashMap<String, Q_longyuanBean> getly(){
		return ManagerPool.dataManager.q_longyuanContainer.getMap();
	}
	
	public static LongYuanManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new LongYuanManager();
			}
		}
		return manager;
	}


	
	
	/**
	 * 得到1970至今天数
	 * @return
	 */
	public int getday(){
		long second = System.currentTimeMillis()/1000 + 60*60*8;		//+8小时，正好和服务器时间一致   0点天数+1
		int day = (int) (second/ (60*60*24));	
		return day;
	}
	
	
	
	
	
	/**
	 * 打开龙元心法面板
	 * @param player
	 * @param msg
	 */
	public void stReqLongYuanOpenMessage(Player player ,ReqLongYuanOpenMessage msg) {
		LongYuanData longyuan = player.getLongyuan();
		byte lysection = longyuan.getLysection();	//星图
		byte lylv = longyuan.getLylevel();			//星位
		HashMap<String, Integer> renmap = longyuan.getLyexpshare();
		int ren = 0;
		if (renmap.containsKey(lysection+"_"+lylv)) {
			ren = renmap.get(lysection+"_"+lylv); //分享人数
		}
		LongYuanInfo lyinfo = new LongYuanInfo();
		lyinfo.setLongyuanexpshare((short) ren);
		lyinfo.setLongyuanlv(lysection);
		lyinfo.setLongyuannum(lylv);
		ResLongYuanOpenMessage cmsg = new ResLongYuanOpenMessage();
		cmsg.setZhenqi(player.getZhenqi());
		cmsg.setLongyuaninfo(lyinfo);
		MessageUtil.tell_player_message(player, cmsg);
		
	}
	
	
	
	/**
	 * 获得心法ID
	 * @param xingtu 星图
	 * @param xinwei  星位
	 * @return
	 */
	public String getlongyuanid(byte xingtu,byte xinwei){
		String id = "";
		id = xingtu+"_"+xinwei;
		return id;
	}
	
	
	
	public String ckLongYuanNextId(Player player,byte longyuanactlv, byte longyuanactnum) {
		LongYuanData longyuan = player.getLongyuan();
		byte lysection = longyuan.getLysection();	//星图
		byte lylv = longyuan.getLylevel();			//星位
		String yid = lysection+"_"+lylv;
		Q_longyuanBean ydb = getly().get(yid);
		
		String xid="";
		if (lysection == 0) {
			if (longyuanactlv == 1 && longyuanactnum == 1 ) {
				xid = getlongyuanid(longyuanactlv,longyuanactnum);
			}
		}else if (lysection == longyuanactlv) {
			if (lylv+1 == longyuanactnum) {		//比较星位
				xid = getlongyuanid(longyuanactlv,longyuanactnum);
			}
		}else if (lysection+1 ==  longyuanactlv  && (ydb != null && ydb.getQ_islastpoint() == 1)) { 
			if (longyuanactnum == 1) {
				xid = getlongyuanid(longyuanactlv,longyuanactnum);
			}
		}
		return xid ;
	}
	
	
	
	
	
	/**检测升级条件
	 * 
	 * @param player
	 * @param xdb
	 * @return
	 */
	public byte checkUpCondition(Player player,Q_longyuanBean xdb,boolean point) {
		if (player.getLevel() < xdb.getQ_requireslv()) {
			if (point) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，等级未达到要求，无法激活该星位，激活{1}需要等级达到{2}级。"),xdb.getQ_pointname(),String.valueOf(xdb.getQ_requireslv()));
			}
			return -1;
		}
		
		List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, BuffConst.LONGYUAN_ZQ_BUFF);
		int zhenqi = xdb.getQ_ordzhenqi();
		if (buffs.size() > 0) {
			zhenqi = (int) (zhenqi*0.8);
		}
		
		if (player.getZhenqi() < zhenqi) {
			if (point) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，所需真气不足，无法激活该星位，激活{1}需要真气{2}。"),xdb.getQ_pointname(),String.valueOf(zhenqi));
			}
			return -2;
		}
		
		if (player.getMoney() < xdb.getQ_ordgold()) {
			if (point) {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，激活{1}需要铜币{2}。"),xdb.getQ_pointname(),String.valueOf(xdb.getQ_ordgold()));
			}
			return -3;
		}
		return 1; 
	}
	
	
	
	
	/**
	 * 点击星位开始激活
	 * @param player
	 * @param msg
	 */
	
	public void stReqLongYuanActivation( Player player, ReqLongYuanActivationMessage msg){
		if(PlayerState.LONGYUANSTART.compare(player.getState())) return;
		if(PlayerState.DIE.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("处于死亡状态，龙元心法无法激活。"));
			return;
		}
//		if (player.getLongyuantime() > 0) {
//			return;
//		}
		String xid = ckLongYuanNextId(player,msg.getLongyuanactlv(),msg.getLongyuanactnum());
		if (xid.length() > 0) {
			Q_longyuanBean xdb = getly().get(xid);
			if (xdb != null) {
				byte type = checkUpCondition(player,xdb,true);
				if (type == 1) {
					ShowEffectInfo effcet = new ShowEffectInfo();
					effcet.setId(player.getId());
					effcet.setType((byte) 2);
					effcet.setEffectid(1);

					ResShowEffectToClientMessage cmsg = new ResShowEffectToClientMessage();
					cmsg.setShoweffectinfo(effcet);
					MessageUtil.tell_round_message(player, cmsg);
					player.setState(PlayerState.LONGYUANSTART);
//					CommonDelayTimerUtil.addDelayTimer(10, new Runnable() {		//延迟10秒
//						@Override
//						public void run() {
//							stRunResLongYuanActivation(player,msg);
//						}
//					});	
					player.setLongyuanactlv(msg.getLongyuanactlv());
					player.setLongyuanactnum(msg.getLongyuanactnum());
					player.setLongyuantime(System.currentTimeMillis()/1000);
					return ;
				}
			}
		}else {
			log.error("不能激活");
		}
		ResLongYuanActivationMessage cmsg = new ResLongYuanActivationMessage();
		cmsg.setStatus((byte) 1);
		cmsg.setLongyuaninfo(new LongYuanInfo());
		MessageUtil.tell_player_message(player, cmsg);	//通知前端 激活失败消息
	}
	
	

	
	
	
	
	
	/**
	 * 倒计时结束后  ，激活星位
	 * @param player
	 * @param msg
	 */
	public void stRunResLongYuanActivation(Player player ){
		player.setState(PlayerState.LONGYUANEND);
		if (ManagerPool.playerManager.getOnLinePlayer(player.getId())== null) {//中途下线，停止操作
			return;
		}
		
		if(PlayerState.DIE.compare(player.getState())) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于您中途死亡，龙元心法激活停止了。"));
			return;
		}
		long action = Config.getId();
		LongYuanData longyuan = player.getLongyuan();
		String xid=ckLongYuanNextId(player,player.getLongyuanactlv(),player.getLongyuanactnum());
		ResLongYuanActivationMessage cmsg = new ResLongYuanActivationMessage();
		
		if (xid.length() > 0) {
			Q_longyuanBean xdb = getly().get(xid);
			if (xdb != null) {
				byte cktype = checkUpCondition(player,xdb,true);
				if (cktype == 1) {
					if(ManagerPool.backpackManager.changeMoney(player, -xdb.getQ_ordgold(),Reasons.LY_SUB_GOLD,action)){//扣金币
						List<Buff> buffs = ManagerPool.buffManager.getBuffByModelId(player, BuffConst.LONGYUAN_ZQ_BUFF);
						int zhenqi = xdb.getQ_ordzhenqi();
						if (buffs.size() > 0) {
							zhenqi = (int) (zhenqi*0.8);
						}
						ManagerPool.playerManager.addZhenqi(player,-zhenqi,AttributeChangeReason.LONGYUAN);//扣真气
						int day = TimeUtil.getOpenAreaDay(player);
						//修正最小所需次数 = 所需次数下限 - 开服天数/开服天数减免系数
						int limit = 0;
						if (xdb.getQ_days_delay() > 0) {
							limit =  (int) (xdb.getQ_limit() - day/xdb.getQ_days_delay());
						}

						if (limit < 0) {
							limit = 0;
						}
						short fnum = longyuan.getLyfailnum();
						
						byte type = 1 ;  //0成功，1失败
						//----------------------------------------------
						if(fnum < limit ){//如果人物激活次数 < 修正最小所需次数，则激活操作必定失败
							type = 1;
						}else if(fnum >= xdb.getQ_max() && xdb.getQ_max() > 0){ //如果 人物激活次数 >= 所需次数上限 必定成功
							type = 0;
						}else {
							if(RandomUtils.isGenerate2(10000, xdb.getQ_successrate())){	//	进入随机
								type = 0;
							}else {
								type = 1;
							}
						}

						//--------------------处理------------------------
						
						if(type == 0){	//成功
							longyuan.setLyfailnum((short) 0);
							longyuan.setLylevel(player.getLongyuanactnum());
							longyuan.setLysection(player.getLongyuanactlv());
							
							ShowEffectInfo effcet = new ShowEffectInfo();
							effcet.setId(player.getId());
							effcet.setType((byte) 2);
							effcet.setEffectid(2);	//成功后面板显示特效
							ResShowEffectToClientMessage emsg = new ResShowEffectToClientMessage();
							emsg.setShoweffectinfo(effcet);
							MessageUtil.tell_round_message(player, emsg);
							//重新计算龙元心法增加的属性
							ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.LONGYUAN);
							
							ShowEffectInfo effcet2 = new ShowEffectInfo();
							effcet2.setId(player.getId());
							effcet2.setType((byte) 1);
							effcet2.setEffectid(5);//人物为中心，地面冒火
							ResShowEffectToClientMessage cmsg2 = new ResShowEffectToClientMessage();
							cmsg2.setShoweffectinfo(effcet2);
							MessageUtil.tell_round_message(player, cmsg2);
							
						}else {			//失败
							longyuan.setLyfailnum((short) (fnum+1));
						}
						
						//---------------------------------------------
						LongYuanAddExp(player,type,xdb);	//心法加经验 	
						LongYuanInfo lyinfo = new LongYuanInfo();
						HashMap<String, Integer> renmap = longyuan.getLyexpshare();
						int ren = 0;
						if (renmap.containsKey(xid)) {
							ren = renmap.get(xid); //分享人数
						}
						lyinfo.setLongyuanexpshare((short) ren);
						lyinfo.setLongyuanlv(longyuan.getLysection());
						lyinfo.setLongyuannum(longyuan.getLylevel());
						cmsg.setLongyuaninfo(lyinfo);
						cmsg.setStatus(type);
						MessageUtil.tell_player_message(player, cmsg);
						return;
					}
				}
			}
		}
		cmsg.setStatus((byte) 1);
		cmsg.setLongyuaninfo(new LongYuanInfo());
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	
	
	
	
	/**
	 * 心法加经验    0成功，1失败
	 * @param type
	 */
	public void LongYuanAddExp(Player player,byte type,Q_longyuanBean xdb ){
		LongYuanLog log =new LongYuanLog();
		log.setLongyuanid( xdb.getQ_id());
		log.setMoney(xdb.getQ_ordgold());
		log.setType(type);
		Q_longyuan_expBean longyuanexp = ManagerPool.dataManager.q_longyuan_expContainer.getMap().get(player.getLevel());
		if (type == 0) {	//成功
			ManagerPool.playerManager.addExp(player, longyuanexp.getQ_success_exp(),AttributeChangeReason.LONGYUAN);
			log.setExp(longyuanexp.getQ_success_exp());
			//龙源心法特殊技能学习&升级
			if (xdb.getQ_skillid() != null && !xdb.getQ_skillid().equals("") ) {//自动学习被动技能
				String[] tab = xdb.getQ_skillid().split(Symbol.XIAHUAXIAN_REG);
				int id = Integer.parseInt(tab[0]);
				int lv = Integer.parseInt(tab[1]);
				if (lv > 1) {		//升级
					Skill skill = ManagerPool.skillManager.getSkillByModelId(player,id);
					if (skill != null ) {
						ManagerPool.skillManager.setLevel(player,skill,lv,true);
					}
				}else if (lv == 1) {	//新学习
					ManagerPool.skillManager.addSkill(player, id);
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您打开龙元心法穴位:{1}，获得了新武学【{2}】。"),xdb.getQ_pointname(),xdb.getQ_skillname());
				}
			}else if(xdb.getQ_addskillid() > 0){//普通技能加等级(这里额外的技能等级是按照龙元心法配置表里的数据在get技能的时候自动加上)
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("绝技星位激活成功，【{1}】层数+{2}，并且获得了人物经验：{3}"),xdb.getQ_addskillname(),String.valueOf(xdb.getQ_addskilllevel()),String.valueOf(longyuanexp.getQ_success_exp()));
			}else if (xdb.getQ_islastpoint() == 0) {
				String addname=sayaddname(player,xdb);
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您成功激活{1}！{2}并且获得了人物经验：{3}"),xdb.getQ_pointname(),addname,String.valueOf(longyuanexp.getQ_success_exp()));
			} 
			
			
			if (xdb.getQ_islastpoint() >= 1){
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您【{1}】星图激活成功，获得超高属性加成!"),xdb.getQ_name());
				if (xdb.getQ_islastpoint() == 2){ 
					MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("恭喜您激活全部星图练就龙元心法，获得超高属性加成的同时习得全部被动技能，称霸江湖指日可待！"));
					MessageUtil.notify_All_player(Notifys.CUTOUT,ResManager.getInstance().getString("恭喜玩家『{1}』激活全部星图练就龙元心法，获得超高属性加成的同时习得全部被动技能，人中之龙誉满江湖！"),player.getName());
				}else{
					//String skname=getlongyunskillname(player);
					LongYuanData longyuan = player.getLongyuan();
					if(longyuan.getLysection() >= 6 ){
						MessageUtil.notify_All_player(Notifys.CUTOUT,ResManager.getInstance().getString("恭喜玩家『{1}』激活『{2}』星图成功，获得超高属性加成."),player.getName(),xdb.getQ_name());
					}
				}
			}
			//周围人加经验
			addAroundExp(player,xdb);	
			//重新计算属性
			ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.LONGYUAN);
			player.setLongyuanUpTime(System.currentTimeMillis());
			
			//同步龙元心法
			if(player.getLevel() >= Global.SYNC_PLAYER_LEVEL && player.getLongyuan().getLysection() >= Global.SYNC_LONGYUAN){
				ReqSyncPlayerLongyuanMessage msg = new ReqSyncPlayerLongyuanMessage();
				msg.setPlayerId(player.getId());
				msg.setLongyuanSection(player.getLongyuan().getLysection());
				msg.setLongyuanLevel(player.getLongyuan().getLylevel());
				msg.setLongyuanTime(player.getLongyuanUpTime());
				MessageUtil.tell_world_message(msg);
			}
		}else {	//激活失败
			ManagerPool.playerManager.addExp(player, longyuanexp.getQ_fail_exp(),AttributeChangeReason.LONGYUAN);
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("很抱歉，本次星位激活失败了，所幸获得了人物经验：{1}"),String.valueOf(longyuanexp.getQ_fail_exp()));
			log.setExp(longyuanexp.getQ_fail_exp());
		}
		stLongYuanLog(player , log );
	}
	
	
	
//	public String getlongyunskillname(Player player  ){
//		LongYuanData data = player.getLongyuan();
//		byte sec = data.getLysection();
//		String skname = "";
//		for (int i = 0; i < 30; i++) {
//			Q_longyuanBean ydb = getly().get(sec+"_"+i);
//			if (ydb != null) {
//				if (ydb.getQ_addskillname() != null && !ydb.getQ_addskillname().equals("")) {
//					skname = skname + ","+ydb.getQ_addskillname();
//				}
//			}
//		}
//		return skname;
//	}
//	
	
	
	
	
	
	public String sayaddname(Player player ,Q_longyuanBean xdb){
		StringBuffer bff = new StringBuffer(ResManager.getInstance().getString("增加属性:"));
		if (xdb.getQ_attack() > 0) {
			bff.append(ResManager.getInstance().getString("[攻击]+")+xdb.getQ_attack()+",");
		}
		if (xdb.getQ_attackspeed() > 0) {
			bff.append(ResManager.getInstance().getString("[攻速]+")+xdb.getQ_attackspeed()+",");
		}
		if (xdb.getQ_crit() > 0) {
			bff.append(ResManager.getInstance().getString("[暴击]+")+xdb.getQ_crit()+",");
		}
		if (xdb.getQ_defence() > 0) {
			bff.append(ResManager.getInstance().getString("[防御]+")+xdb.getQ_defence()+",");
		}
		if (xdb.getQ_dodge() > 0) {
			bff.append(ResManager.getInstance().getString("[闪避]+")+xdb.getQ_dodge()+",");
		}
		if (xdb.getQ_luck() > 0) {
			bff.append(ResManager.getInstance().getString("[幸运]+")+xdb.getQ_luck()+",");
		}
		if (xdb.getQ_maxhp() > 0) {
			bff.append(ResManager.getInstance().getString("[血量上限]+")+xdb.getQ_maxhp()+",");
		}
		if (xdb.getQ_maxmp() > 0) {
			bff.append(ResManager.getInstance().getString("[内力上限]+")+xdb.getQ_maxmp()+",");
		}
		if (xdb.getQ_maxsp() > 0) {
			bff.append(ResManager.getInstance().getString("[体力上限]+")+xdb.getQ_maxsp()+",");
		}
		if (xdb.getQ_speed() > 0) {
			bff.append(ResManager.getInstance().getString("[移速]+")+xdb.getQ_speed()+",");
		}
		return bff.toString();
	}
	
	
	
	
	
	/**
	 * 给周围人加经验
	 */
	public void addAroundExp(Player player, Q_longyuanBean xdb){
		if (xdb.getQ_radius() > 0) {
			int sumexp = 0;	//总经验
			List<Player> playertab = ManagerPool.mapManager.getAroundPlayer(player,xdb.getQ_radius());
			if (playertab == null)return;
			int sum = 0;	//获得经验的人数
			for (Player splayer : playertab) {
				if(PlayerState.DIE.compare(splayer.getState())) continue;	//玩家死亡跳过
				longyuanChangeDate(splayer,2);	//更新可累加经验
				LongYuanData slongyuan = splayer.getLongyuan();
				Q_longyuan_expBean longyuanexp = ManagerPool.dataManager.q_longyuan_expContainer.getMap().get(player.getLevel());
				int exp = 0;
				if (slongyuan.getLyobtainexp() + longyuanexp.getQ_success_others_exp() <= slongyuan.getLymaxexp()) {
					exp = longyuanexp.getQ_success_others_exp();
				}else {
					exp = slongyuan.getLymaxexp() - slongyuan.getLyobtainexp();
				}
				if (exp > 0) {
					sumexp = sumexp+exp;
					slongyuan.setLyobtainexp(slongyuan.getLyobtainexp()+exp);
					ManagerPool.playerManager.addExp(splayer,exp,AttributeChangeReason.LONGYUAN);
					MessageUtil.notify_player(splayer, Notifys.SUCCESS, ResManager.getInstance().getString("【{1}】星图激活成功，您因足够靠近而获得了经验加成：{2}。"),player.getName(),String.valueOf(exp));
					sum = sum+1;
					
					ShowEffectInfo effcet = new ShowEffectInfo();
					effcet.setId(splayer.getId());
					effcet.setType((byte) 2);
					effcet.setEffectid(4);//龙元心法周围人获得经验，冒出闪电
					ResShowEffectToClientMessage cmsg = new ResShowEffectToClientMessage();
					cmsg.setShoweffectinfo(effcet);
					MessageUtil.tell_round_message(splayer, cmsg);
					
				}
			}
			
			LongYuanData longyuan = player.getLongyuan();
			if (sum > 0) {
				longyuan.getLyshareexpnum().put(xdb.getQ_id(), sumexp);	//存分享经验总量
				longyuan.getLyexpshare().put(xdb.getQ_id(), sum);		//存分享经验人数
				MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("周围另有【{1}】位玩家因为您的星图激活而获得了经验加成。"),String.valueOf(sum));
			}
		}
	}
	
	
	
	



	
	/**
	 * 上下线时间改变（主要是计算离线天数 累计给经验）
	 * type=  0上线，1下线 ,2 在线更新
	 */
	public void longyuanChangeDate(Player player , int type){
		LongYuanData longyuan = player.getLongyuan();
		int day = getday();		
		if (type == 0 && longyuan.getOnlineday() != day ) {	//上线时间和保存的天数不是同一天，不一致，则改成当前天
			longyuan.setOnlineday(day);	//上线
			if (longyuan.getOfflineday() == 0) {
				longyuan.setOfflineday(day);
			}
			longyuan.setLyobtainexp(0);
		}

		if (type == 1 && longyuan.getOfflineday() != day ) {//下线   如果最后离线时间不是今天
			if (longyuan.getOnlineday() < day) {	//离线检查最后上线时间是不是当前天，如果不是，那就更改离线时间为当前天
				longyuan.setOfflineday(day); //下线
				longyuan.setLyobtainexp(0);
			}
		}
		
		if (type == 2 && longyuan.getOnlineday() != day ) { //一直在线，跨天后，只算一天，给经验的时候设置离线时间为当天
			longyuan.setOnlineday(day);	//上线
			longyuan.setOfflineday(day);//下线
			longyuan.setLyobtainexp(0);
		}
		
		Q_longyuan_expBean longyuanexp = ManagerPool.dataManager.q_longyuan_expContainer.getMap().get(player.getLevel());
		int tday  = longyuan.getOnlineday() - longyuan.getOfflineday();
		if (tday <= 0) {
			tday = 1;
		}else if (tday > 10) {
			tday = 10;
		}
		if (longyuanexp != null ) {
			int exp = longyuanexp.getQ_max_exp()* (tday);
			longyuan.setLymaxexp(exp);
		}

	}		


	

	
	/**鼠标悬停提示信息（星位）消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqLongYuanTipsMessage(Player player , ReqLongYuanTipsMessage msg ){
		String xid = getlongyuanid(msg.getLongyuanactlv(),msg.getLongyuanactnum());
		Q_longyuanBean xdb = getly().get(xid);
		if (xdb != null) {
			ResLongYuanPosTipsMessage cmsg =  new ResLongYuanPosTipsMessage();
			LongYuanPosTipsInfo tipsinfo = new LongYuanPosTipsInfo();
			byte isachieve = checklongyuanlv( player ,msg.getLongyuanactlv(), msg.getLongyuanactnum());
			if (isachieve >= 1) {
				HashMap<String, Integer> map = player.getLongyuan().getLyexpshare();
				if (map.containsKey(xid)) {
					int num = map.get(xid);
					tipsinfo.setLongyuanexpshare((short) num);
				}
				HashMap<String, Integer> emap = player.getLongyuan().getLyshareexpnum();
				if (emap.containsKey(xid)) {
					int sum = emap.get(xid);
					tipsinfo.setLongyuanshareexpsum(sum);
				}
			}
			tipsinfo.setSuccessrate(xdb.getQ_successrate());
			tipsinfo.setLongyuanactlv(msg.getLongyuanactlv());
			tipsinfo.setLongyuanactnum(msg.getLongyuanactnum());
			tipsinfo.setIsachieve(isachieve);
			cmsg.setLongyuanpostipsinfo(tipsinfo);
			MessageUtil.tell_player_message(player, cmsg);
		}else {
			log.error("鼠标悬停提示信息（星位）错误="+msg);
		}

	}
	
	
	
	
	/**
	 * 比较星位  0当前星位小于传入值（没有激活），1当前星位等于传入值，2当前星位大于传入值（已经激活）
	 * @param tipsinfo
	 * @param star
	 * @param lynum
	 * @return
	 */
	public byte checklongyuanlv(Player player ,byte star,byte lynum){
		byte st = 0;
		byte level = player.getLongyuan().getLylevel();
		byte section = player.getLongyuan().getLysection();	//星图
		if (section == star) {
			if (level == lynum ) {
				st = 1;
			}else if (level > lynum ){
				st = 2;
			}
		}else if(section > star){
			st = 2;
		}
		return st;
	}
	
	
	
	
	/**鼠标悬停提示信息（星图）消息
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReqLongYuanStarMapTipsMessage(Player player , ReqLongYuanStarMapTipsMessage msg ){
		ResLongYuanStarMapTipsMessage cmsg = new ResLongYuanStarMapTipsMessage();
		LongYuanData longyuan = player.getLongyuan();
		String xid = getlongyuanid(longyuan.getLysection(),longyuan.getLylevel());
		Q_longyuanBean xdb = getly().get(xid);
		byte type = 0;
		if (longyuan.getLysection() > msg.getLongyuanactlv() ) {
			type = 1;
		}else if(longyuan.getLysection() == msg.getLongyuanactlv() ){
			if (xdb.getQ_islastpoint() >= 1 ) {
				type = 1;
			}
		}
		LongYuanStarMapTipsInfo mapinfo = new LongYuanStarMapTipsInfo();
		mapinfo.setIsachieve(type);
		mapinfo.setLongyuanactlv(msg.getLongyuanactlv());
		cmsg.setStarmaptipsinfo(mapinfo);
		MessageUtil.tell_player_message(player, cmsg);

	}
	
	
	
	
	
	/**天元心法升级后所加人物属性
	 * 
	 * @param player
	 * @return
	 */
	public HashMap<String, Integer> getTianyuanProperty(Player player) {
		byte sec = player.getLongyuan().getLysection();
		byte slv = player.getLongyuan().getLylevel();
		//String xid = getlongyuanid(sec,slv);
		HashMap<String, Integer> att = new HashMap<String, Integer>();
		att.put(ResManager.getInstance().getString("攻击"), 0);
		att.put(ResManager.getInstance().getString("暴击"), 0);
		att.put(ResManager.getInstance().getString("血量"), 0);
		att.put(ResManager.getInstance().getString("体力"), 0);
		att.put(ResManager.getInstance().getString("内力"), 0);
		att.put(ResManager.getInstance().getString("防御"), 0);
		att.put(ResManager.getInstance().getString("敏捷"), 0);
		att.put(ResManager.getInstance().getString("幸运"), 0);
		att.put(ResManager.getInstance().getString("攻速"), 0);
		att.put(ResManager.getInstance().getString("移速"), 0);
		
		Iterator<Entry<String, Q_longyuanBean>> it = getly().entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Q_longyuanBean> db = it.next();
			if(db!=null){
				String[] keys= db.getKey().split(Symbol.XIAHUAXIAN_REG);
				if (keys!= null && keys.length == 2) {
					int lysection = Integer.parseInt(keys[0]);
					int lylv = Integer.parseInt(keys[1]);
					if (sec >= lysection) {
						if (sec > lysection ||  (sec == lysection && slv >= lylv)) {
							try {
								Q_longyuanBean data = db.getValue();
								att.put(ResManager.getInstance().getString("攻击"),att.get(ResManager.getInstance().getString("攻击"))+data.getQ_attack());
								att.put(ResManager.getInstance().getString("暴击"),att.get(ResManager.getInstance().getString("暴击"))+data.getQ_crit());
								att.put(ResManager.getInstance().getString("血量"),att.get(ResManager.getInstance().getString("血量"))+data.getQ_maxhp());
								att.put(ResManager.getInstance().getString("体力"),att.get(ResManager.getInstance().getString("体力"))+data.getQ_maxsp());
								att.put(ResManager.getInstance().getString("内力"),att.get(ResManager.getInstance().getString("内力"))+data.getQ_maxmp());
								att.put(ResManager.getInstance().getString("防御"),att.get(ResManager.getInstance().getString("防御"))+data.getQ_defence());
								att.put(ResManager.getInstance().getString("敏捷"),att.get(ResManager.getInstance().getString("敏捷"))+data.getQ_dodge());
								att.put(ResManager.getInstance().getString("幸运"),att.get(ResManager.getInstance().getString("幸运"))+data.getQ_luck());
								att.put(ResManager.getInstance().getString("攻速"),att.get(ResManager.getInstance().getString("攻速"))+data.getQ_attackspeed());
								att.put(ResManager.getInstance().getString("移速"),att.get(ResManager.getInstance().getString("移速"))+data.getQ_speed());
							} catch (Exception e) {
								log.error(e);
							}
						}
					}
				}
			}
		}
		return att;
	}

	
	
	
	//计算龙源心法属性加成的时候调用（获得可加等级的技能）
	public HashMap<Integer, Integer> getLongYuanSkillmap(Player player){
		byte sec = player.getLongyuan().getLysection();
		byte slv = player.getLongyuan().getLylevel();
		if (sec > 0 ) {
			HashMap<Integer, Integer> map=new HashMap<Integer, Integer>();
			Iterator<Entry<String, Q_longyuanBean>> it = getly().entrySet().iterator();
			while (it.hasNext()) {
				Entry<String, Q_longyuanBean> db = it.next();
				Q_longyuanBean value = db.getValue();
				if(db!=null && value.getQ_addskillid() > 0){
					String[] keys= db.getKey().split(Symbol.XIAHUAXIAN_REG);
					if (keys!= null && keys.length == 2) {
						int lysection = Integer.parseInt(keys[0]);
						int lylv = Integer.parseInt(keys[1]);
						if (sec >= lysection) {
							if (sec > lysection ||  (sec == lysection && slv >= lylv)) {
								map.put(value.getQ_addskillid(), value.getQ_addskilllevel()) ;
							}
						}
					}
				}
			}
			if (map.size() > 0) {
				return map;
			}
		}
		return null;
	}
	
	
	
	
	
	/**得到龙元心法激活后对应的技能加成
	 * 
	 * @param player
	 * @param skillid
	 * @return  应加技能等级
	 */
	public int getLongYuanSkillLevel(Player player,int skillid){
		byte sec = player.getLongyuan().getLysection();
		byte slv = player.getLongyuan().getLylevel();
		String key = skillid+"_"+sec+"_"+slv 	;//SKILLID+星图+星位 组成KEY
		if(longyuanupmap.containsKey(key)){
			return longyuanupmap.get(key);
		}
		return 0;
	}
	
	
	

	
	
	/**得到龙元心法激活后对应的技能加成(启动调用,生成MAP)
	 * 
	 * @param skillid
	 * @return
	 */
	
	public void createLongYuanSkilllvUpList() {
		Iterator<Entry<String, Q_longyuanBean>> it = getly().entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Q_longyuanBean> db = it.next();
			Q_longyuanBean value = db.getValue();
			if(db!=null && value.getQ_addskillid() > 0){
				String[] tab = value.getQ_id().split(Symbol.XIAHUAXIAN_REG);
				int lysection = Integer.valueOf(tab[0]);
				int lylv = Integer.valueOf(tab[1]);
				createlyskillmap(value.getQ_addskillid(),lysection,lylv, value.getQ_addskilllevel());
			}
		}
	}
	
	/**循环检测当前星图是否可以加到MAP
	 * 
	 * @param skillid
	 * @param sec
	 * @param slv
	 * @param sklv
	 * @return
	 */
	public int createlyskillmap(int skillid,int sec,int slv,int sklv){
		Iterator<Entry<String, Q_longyuanBean>> it = getly().entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Q_longyuanBean> db = it.next();
			Q_longyuanBean value = db.getValue();
			if(db!=null){
				String[] keys= db.getKey().split(Symbol.XIAHUAXIAN_REG);
				if (keys!= null && keys.length == 2) {
					int lysection = Integer.parseInt(keys[0]);
					int lylv = Integer.parseInt(keys[1]);
					if (lysection >= sec) {
						if (lysection >  sec||  (lysection  == sec && lylv >= slv)) {
							longyuanupmap.put(skillid+"_"+value.getQ_id(), sklv);		//SKILLID+星图+星位 组成KEY
						}
					}
				}
			}
		}
		return 0;
	}
	
	
	/**日志记录
	 * 
	 * @param player
	 * @param log
	 */
	public void stLongYuanLog(Player player,LongYuanLog log){
		log.setPlayerId(player.getId());
		LogService.getInstance().execute(log);
	}
	
	
	
	
	
	
	
}





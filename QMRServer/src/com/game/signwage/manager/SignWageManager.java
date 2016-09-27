package com.game.signwage.manager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.game.arrow.structs.ArrowReasonsType;
import com.game.backpack.bean.ItemReasonsInfo;
import com.game.backpack.manager.BackpackManager;
import com.game.backpack.message.ResGetItemReasonsMessage;
import com.game.backpack.structs.Attribute;
import com.game.backpack.structs.Equip;
import com.game.backpack.structs.Item;
import com.game.backpack.structs.ItemTypeConst;
import com.game.config.Config;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_petinfoBean;
import com.game.data.bean.Q_sign_wageBean;
import com.game.data.bean.Q_spirittree_pack_conBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.json.JSONserializable;
import com.game.languageres.manager.ResManager;
import com.game.mail.manager.MailServerManager;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.AttributeChangeReason;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.structs.RankType;
import com.game.server.impl.WServer;
import com.game.signwage.bean.SignInfo;
import com.game.signwage.bean.WageInfo;
import com.game.signwage.log.SignWageLog;
import com.game.signwage.message.ReqReceiveAwardsToClientMessage;
import com.game.signwage.message.ReqReceiveBeautyMessage;
import com.game.signwage.message.ResSignWageInfoMessage;
import com.game.signwage.message.ResSignWagetoWageInfoMessage;
import com.game.signwage.message.ResWageERNIEinofMessage;
import com.game.signwage.message.RessignnumToClientMessage;
import com.game.signwage.structs.Sign;
import com.game.signwage.structs.Wage;
import com.game.spirittree.structs.FruitReward;
import com.game.spirittree.structs.FruitRewardAttr;
import com.game.structs.Reasons;
import com.game.utils.CommonConfig;
import com.game.utils.MessageUtil;
import com.game.utils.RandomUtils;
import com.game.utils.TimeUtil;

public class SignWageManager {
	private Logger log = Logger.getLogger(SignWageManager.class);
	
	private static Object obj = new Object();
	// 管理类实例 
	private static SignWageManager manager;

	private SignWageManager() {}

	public static SignWageManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new SignWageManager();
			}
		}
		return manager;
	}
	
	
	/**计算本次在线时间
	 * 
	 */
	public void computesignwageitem(Player player,int time){
		try {
			List<Wage> wagelist = player.getWagelist();
			int day = TimeUtil.getOpenAreaDay();
			int openareamonth = day/30;
			if (day%30 == 0) {
				openareamonth = openareamonth-1;
			}
			boolean isnew = false;
			
			if (wagelist.size() >= 2) {	//如果是2个数据
				Wage sdata = wagelist.get(1);	//当前月
				if (sdata.getMonthnum() == openareamonth) {
					String record = "累积在线日志 "+player.getId()+" "+player.getName()+" 本月在线"+sdata.getCumulativetime()+"秒 +"+"累加在线"+time+"秒=当前本月在线"; //记录文本日志
					sdata.setCumulativetime(sdata.getCumulativetime() + time);
					record += sdata.getCumulativetime()+"秒,角色在线时间Accunonlinetime="+player.getAccunonlinetime()+" 角色登录时间loginlinetime="+player.getLoginlinetime();
					log.error(record);
				}else {
					wagelist.remove(0);
					isnew = true;
				}
			}else if (wagelist.size() == 1) {	//如果只有1个数据
				Wage sdata = wagelist.get(0);
				if (sdata.getMonthnum() == openareamonth) {
					String record = "累积在线日志 "+player.getId()+" "+player.getName()+" 本月在线"+sdata.getCumulativetime()+"秒 +"+"累加在线"+time+"秒=当前本月在线"; //记录文本日志
					sdata.setCumulativetime(sdata.getCumulativetime() + time);
					record += sdata.getCumulativetime()+"秒,角色在线时间Accunonlinetime="+player.getAccunonlinetime()+" 角色登录时间loginlinetime="+player.getLoginlinetime();
					log.error(record);
				}else {
					isnew = true;
				}
			}else {
				isnew = true;
			}
			if (isnew) {
				Wage signWage = new Wage();
				signWage.setMonthnum(openareamonth);
				String record = "累积在线日志 "+player.getId()+" "+player.getName()+" 新的月份 累加在线"+time+"秒"; //记录文本日志
				signWage.setCumulativetime(time);
				record += "角色在线时间Accunonlinetime="+player.getAccunonlinetime()+" 角色登录时间loginlinetime="+player.getLoginlinetime();
				log.error(record);
				wagelist.add(signWage);	//新的月份
			}
		} catch (Exception e) {
			log.error("离线计算本月在线时间错误"+time+" , "+e);
		}
	}
	
	
	/**上线计算 上次在线时间
	 * 
	 * @param player
	 */
	
	public void loginsignwageitem(Player player ){
		int time =player.getAccunonlinetime() - player.getLoginlinetime();
		if (time > 0) {
			computesignwageitem(player,time);
		}
		player.setLoginlinetime(player.getAccunonlinetime());
	}
	
	
	
	
	
	
	
	/**得到当前月的签到数据
	 * 
	 * @param player
	 * @return 
	 */
	public Sign getCurrentMonthSign(Player player ){
		int month = TimeUtil.getMonth(System.currentTimeMillis())+1;
		String key = ""+month;
		HashMap<String, Sign> signmap = player.getSignmap();
		if (signmap.containsKey(key)) {
			return signmap.get(key);
		}else{
			if (month == 1) {	//如果当前是1月，而没有数据，则清理所有月的数据
				signmap.clear();
			}
			Sign sign = new Sign();
			signmap.put(key, sign);
			return sign;
		}
	}
	
	
	
	/**打开签到领奖励面板
	 * 
	 * @param player
	 */
	public void openSign(Player player){
		Sign sign = getCurrentMonthSign(player);
		SignInfo signInfo = new SignInfo();
		signInfo.setYear(TimeUtil.getYear(System.currentTimeMillis()));
		signInfo.setDaylist(sign.getDaylist());
		signInfo.setAward(sign.getRewardstatelist());
		signInfo.setDay(TimeUtil.getDayOfMonth(System.currentTimeMillis()));
		signInfo.setMonth(TimeUtil.getMonth(System.currentTimeMillis())+1);
		ResSignWageInfoMessage cmsg = new ResSignWageInfoMessage();
		cmsg.setSignInfo(signInfo);
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	
	
	/**点击今日签到
	 * 
	 * @param player
	 */
	public void setSign(Player player){
		Sign sign = getCurrentMonthSign(player);
		if(sign.addSign()){
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("今日已经签到成功。"));
			player.setSignsum(player.getSignsum() +1);
			openSign(player);
			RessignnumToClientMessage cnms= new RessignnumToClientMessage();
			cnms.setSignnum(player.getSignsum());
			MessageUtil.tell_player_message(player, cnms);
			Q_sign_wageBean wagebean = ManagerPool.dataManager.q_sign_wageContainer.getMap().get(2);	
			if (wagebean != null) { //这个次数的奖励是否存在
//				if (ManagerPool.vipManager.getPlayerVipId(player) > 0) {//VIP额外奖励
//					toreward(player , wagebean.getQ_vip_reward(),1,"VIP");
//				}
			}
		}else {
			MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("您今日已经签到了。"));
		}
	}
	
	
	/**领取美人
	 * 
	 * @param player
	 * @param msg
	 */
	public void stReceiveBeauty(Player player,ReqReceiveBeautyMessage msg  ){
		int receivemodel = 0;
		String name="";
		if (msg.getType() == 2 || msg.getType() == 3) {
			if (msg.getType() == 2 && player.getSignsum() >= 7 ) {
				int modelid= 7;//鹿丹儿
				if(ManagerPool.petInfoManager.getPetByModelId(player, modelid) == null){
					ManagerPool.petOptManager.addPet(player, modelid, "sign", Config.getId());
					Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(modelid);
					if (model != null) {
						name = model.getQ_name();
					}
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("恭喜您获得美人：{1}"),name);
					receivemodel = 7;
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经有获得这个美人，不能重复领取 "));
				}
			}else if (msg.getType() == 3 && player.getSignsum() >= 25) {
				int modelid= 10;//赵倩
				if(ManagerPool.petInfoManager.getPetByModelId(player, modelid) == null){
					ManagerPool.petOptManager.addPet(player, modelid, "sign", Config.getId());
					Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(modelid);
					if (model != null) {
						name = model.getQ_name();
					}
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("恭喜您获得美人：{1}"),name);
					receivemodel = 25;
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经有获得这个美人，不能重复领取 "));
				}
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有达到累计签到次数，不能领取美人"));
			}
		}else if (msg.getType() == 4) {
			int modelid= 12;//赵致
			Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(modelid);
			if (model != null) {
				name = model.getQ_name();
			}
			long logtime = Config.getId();
			if(ManagerPool.petInfoManager.getPetByModelId(player, modelid) == null){
				if (ManagerPool.backpackManager.changeGold(player, -180, Reasons.MEIREN, logtime)) {
					ManagerPool.petOptManager.addPet(player, modelid, "gold", logtime);
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("恭喜您获得美人：{1}"),name);
					receivemodel = 12;
				}else {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您没有180元宝，无法获得美人：{1}"),name);
				}
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经有获得获得美人：{1}，不能重复领取"),name);
			}
		}else if (msg.getType() == 5 && player.getSignsum() >= 14) { //韩国领取赵致
			int modelid= 12;//赵致
			if (!WServer.getInstance().getServerWeb().equals("hgpupugame")) {	
				return;
			}
			
			Q_petinfoBean model = DataManager.getInstance().q_petinfoContainer.getMap().get(modelid);
			if (model != null) {
				name = model.getQ_name();
			}
			long logtime = Config.getId();
			if(ManagerPool.petInfoManager.getPetByModelId(player, modelid) == null){
				ManagerPool.petOptManager.addPet(player, modelid, "hgpupugame", logtime);
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("恭喜您获得美人：{1}"),name);
				receivemodel = 12;
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经有获得获得美人：{1}，不能重复领取"),name);
			}
		}
		//日志记录
		try {
			if(receivemodel>0){
				SignWageLog log = new SignWageLog(player);
				log.setType(3);
				Map<String, String> parammap = new HashMap<String, String>();
				parammap.put("modelid", String.valueOf(receivemodel));
				parammap.put("msgtype", String.valueOf(msg.getType()));
				parammap.put("signnum", String.valueOf(player.getSignsum()));
				log.setContent(JSONserializable.toString(parammap));
				LogService.getInstance().execute(log);
			}
		} catch (Exception e) {
			log.error(e, e);
		}
	}
	
	
	/**领取签到奖励
	 * 
	 * @param player
	 */
	public void receiveSignReward(Player player ,ReqReceiveAwardsToClientMessage msg ){
		Sign sign = getCurrentMonthSign(player);
		int num = sign.getDaylist().size();
		Q_sign_wageBean wagebean = ManagerPool.dataManager.q_sign_wageContainer.getMap().get((int)msg.getType());	
		if (wagebean != null) { //这个次数的奖励是否存在
			if (num >= msg.getType() ) {	//签到次数是否达到
				List<Integer> rewardstatelist = sign.getRewardstatelist();
				if (rewardstatelist.contains((int)msg.getType())) {
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您已经领取过这个签到奖励。"));
				}else {
					rewardstatelist.add((int)msg.getType());		
					toreward(player , wagebean.getQ_reward(),1,null);
					String rewardcontent = wagebean.getQ_reward(); //奖励内容 -用于日志记录
					if (wagebean != null) { //这个次数的奖励是否存在
						if (ManagerPool.vipManager.getPlayerVipId(player) > 0) {//VIP额外奖励
							toreward(player , wagebean.getQ_vip_reward(),1,"VIP");
							rewardcontent+=";"+wagebean.getQ_vip_reward();
						}
					}
					//日志记录
					try {
						SignWageLog log = new SignWageLog(player);
						log.setType(1);
						log.setContent(rewardcontent);
						LogService.getInstance().execute(log);
					} catch (Exception e) {
						log.error(e, e);
					}
				}
				openSign(player);
			}else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("签到次数没有达到{1}次，不能领取奖励。"),""+msg.getType());
			}
		}else {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("没有这个类型的奖励内容。"));
		}
	}
	
	
	
	
	
	
	
	/**打工资面板
	 * 
	 * @param player
	 */
	public void openWage(Player player){
		long time = (System.currentTimeMillis() - player.getLoginTime())/1000;
		List<Wage> wagelist = player.getWagelist();
		int day = TimeUtil.getOpenAreaDay();
		int curmonth = day/30;
		if (day%30 == 0) {
			curmonth = curmonth -1;
		}
		int oldmonth = 0;
		int oldms = 0;
		int curms = 0;
		int ernienum = 0;
		WageInfo wageInfo = new WageInfo();
		wageInfo.setCurmonth(day);
		List<FruitReward> fruitRewards = new ArrayList<FruitReward>();
		boolean isnew= false;
		if (wagelist.size() >= 2) {
			Wage wage = wagelist.get(1);
			if (wage.getMonthnum() == curmonth) {
				wage.clearernie();	//清理今日领奖次数
				curms = wage.getCumulativetime() + (int)time;	//本月累计时间
				wageInfo.setCurstatus((byte) wage.getStatus());	//本月领奖状态
				fruitRewards.addAll(wage.getErnierewardlist()) ;
				
				oldms = wagelist.get(0).getCumulativetime();	//上月累计时间
				oldmonth = wagelist.get(0).getMonthnum();	//上个月份
				wageInfo.setOldstatus((byte) wagelist.get(0).getStatus());//本月领奖状态
				
				ernienum= wage.getErnienum();//摇奖次数
			}else {
				wageInfo.setOldstatus((byte) wagelist.get(1).getStatus());	//上月领奖状态
				oldms = wagelist.get(1).getCumulativetime();	//上月累计时间
				oldmonth = wagelist.get(1).getMonthnum();	//上个月份
				wagelist.remove(0);	//删除更古老月份
				isnew = true;

			}
		}else if (wagelist.size() == 1) {
			Wage wage = wagelist.get(0);
			if (wage.getMonthnum() == curmonth) {
				wage.clearernie();	//清理今日领奖次数
				ernienum= wage.getErnienum();//摇奖次数
				fruitRewards.addAll(wage.getErnierewardlist()) ;	//今日已领取奖励
				curms = wage.getCumulativetime() + (int)time;	//本月累计在线时间
				wageInfo.setCurstatus((byte) wage.getStatus());	//本月领奖状态
			}else {
				oldms = wagelist.get(0).getCumulativetime();	//上月累计时间
				oldmonth = wagelist.get(0).getMonthnum();	//上个月份
				wageInfo.setOldstatus((byte) wagelist.get(0).getStatus());	//上个月领奖状态
				isnew = true;
			}
		}else {
			isnew = true;
		}
		
		if (isnew) {
			Wage signWage = new Wage();	//新的月份
			signWage.setMonthnum(curmonth);
			wagelist.add(signWage);	
			curms = (int)time;
		}

		Date sdate = WServer.getGameConfig().getServerTimeByServer(WServer.getInstance().getServerId());
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		wageInfo.setSvrstarttime(format.format(sdate));
		int curhour=curms/3600 ;
		int oldhour=oldms/3600;
		if (curhour > 300) {
			curhour = 300;
		}
		if (oldhour > 300) {
			oldhour = 300;
		}
		
		int interval = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ERNIE_INTERVAL.getValue()).getQ_int_value();
		int minute = player.getDayonlinetime()/60 ;
		int num  = minute/interval;
		if (num > 4) {
			num = 4;
		}
		
		for (int i = 1; i <= 4; i++) {
			if (i <= ernienum) {//已经领取的格子
				wageInfo.getErnie().add(1);//1表示已经领取
			}else if (i <= num) {	//可领取格子
				wageInfo.getErnie().add(0);
			}else {
				wageInfo.getErnie().add(2);//条件未达到
			}
		}
		if (wageInfo.getOldstatus() == 0) {
			wageInfo.setOldwage(oldhour * getBasisWage(oldmonth));
		}
		if (wageInfo.getCurstatus() == 0) {
			wageInfo.setCurwage(curhour * getBasisWage(curmonth) );
		}

		wageInfo.setDaytime(player.getDayonlinetime());//今日累计时间
		wageInfo.setMonthtime(curms);	//本月累计时间
		if (fruitRewards.size() > 0) {
			for (FruitReward fruitReward : fruitRewards) {
				wageInfo.getFruitRewardinfo().add(fruitReward.makeinfo());
			}
		}
		
		ResSignWagetoWageInfoMessage cmsg = new ResSignWagetoWageInfoMessage();
		cmsg.setWageInfo(wageInfo);
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	
	/**领取工资
	 * 
	 * @param player
	 */
	public void receiveWage(Player player) {
		long actionid = Config.getId();
		int time = (int)((System.currentTimeMillis() - player.getLoginTime())/1000);
		Wage oldwage = getOldWage(player);
		if (oldwage != null) {
			if (oldwage.getStatus() == 0) {
				int oldhour= oldwage.getCumulativetime()/3600;
				if (oldhour > 300) {//每月上限300小时
					oldhour = 300;
				}
				int basis = getBasisWage(oldwage.getMonthnum());
				int oldyb = oldhour * basis;
				if (oldyb > 0) {
					oldwage.setStatus(1);
					ManagerPool.backpackManager.changeBindGold(player, oldyb, Reasons.WAGE_OLD_LIJING, actionid);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您领取到上个月工资{1}礼金"), oldyb+"");
					//领取工资日志记录
					try{
						SignWageLog log = new SignWageLog(player);
						log.setType(2); //领取工资
						Map<String, String> parammap = new HashMap<String, String>();
						parammap.put("hour", String.valueOf(oldhour));
						parammap.put("monthnum", String.valueOf(oldwage.getMonthnum()));
						parammap.put("num", String.valueOf(oldyb));
						parammap.put("cumulativetime", String.valueOf(oldwage.getCumulativetime()));
						log.setContent(JSONserializable.toString(parammap));
						LogService.getInstance().execute(log);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
			}
		}
		
		//领取本月工资
		Wage curwage = getCurWage(player);
		if (curwage != null) {
			if (curwage.getStatus() == 0) {
				int day = TimeUtil.getOpenAreaDay();
				int num = day%30;
				if (num != 0) {
					MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("很抱歉，本月工资需要再等{1}天后才能领取"), (30-num)+"");
					return;
				}
				int curhour= (curwage.getCumulativetime()+time)/3600 ;
				if (curhour > 300) {
					curhour = 300;
				}
				int basis = getBasisWage(curwage.getMonthnum());
				int curyb = curhour * basis;
				if (curyb > 0) {
					curwage.setStatus(1);
					ManagerPool.backpackManager.changeBindGold(player, curyb, Reasons.WAGE_CUR_LIJING, actionid);
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜您领取到本月工资{1}礼金"), curyb+"");
					//领取工资日志记录
					try{
						SignWageLog log = new SignWageLog(player);
						log.setType(2); //领取工资
						Map<String, String> parammap = new HashMap<String, String>();
						parammap.put("hour", String.valueOf(curhour));
						parammap.put("monthnum", String.valueOf(oldwage.getMonthnum()));
						parammap.put("num", String.valueOf(curyb));
						parammap.put("cumulativetime", String.valueOf(oldwage.getCumulativetime()));
						log.setContent(JSONserializable.toString(parammap));
						LogService.getInstance().execute(log);
					}catch (Exception e) {
						log.error(e, e);
					}
				}
			}else {
				MessageUtil.notify_player(player, Notifys.MOUSEPOS, ResManager.getInstance().getString("很抱歉，您已经领取本月工资，请下个月再来吧。"));
			}
		}
		openWage(player);
	}

		
	
	
	/**工资基础值
	 * @param num
	 * @return
	 */
	public int getBasisWage(int num ){
		if (num == 0 ) {
			return 20;
		}else if (num == 1) {
			return 40;
		}else {
			return 80;
		}
		
	}
	
	
	
	
	/**获取本月工资数据
	 * 
	 * @param player
	 * @return 
	 */
	public Wage getCurWage(Player player){
		List<Wage> wagelist = player.getWagelist();
		int day = TimeUtil.getOpenAreaDay();
		int curmonth = day/30;
		if (day%30==0) {
			curmonth = curmonth - 1;
		}
		if (wagelist.size() >= 2) {
			if (wagelist.get(1).getMonthnum() == curmonth) {
				wagelist.get(1).clearernie();
				return wagelist.get(1);
			}else {
				log.error("本月工资数据记录位置不对");
				return null;
			}
			
		}else if (wagelist.size() == 1) {
			Wage wage = wagelist.get(0);
			wage.clearernie();	//清理今日领奖次数
			return wagelist.get(0);
		}
		return null;
	}
	
	
	
	
	/**获取上个月工资数据
	 * 
	 * @param player
	 * @return 
	 */
	public Wage getOldWage(Player player){
		List<Wage> wagelist = player.getWagelist();
		int day = TimeUtil.getOpenAreaDay();
		int curmonth = day/30;
		if (day%30==0) {
			curmonth = curmonth - 1;
		}
		if (wagelist.size() >= 2) {
			if (wagelist.get(0).getMonthnum() != curmonth) {
				return wagelist.get(0);
			}
		}
		return null;
	}
	


	
	
	/**签到给奖励
	 * 
	 * @param player
	 * @param rewardstr
	 */
	public void toreward(Player player , String rewardstr,int type ,String explain  ){
		String rewardname ="";
		if (type == 1) {
			rewardname =  ResManager.getInstance().getString("签到奖励");
		}
		if (explain !=null) {
			rewardname= explain + rewardname;
		}
		List<Item> itemlist = new ArrayList<Item>();
		int itemNum = 0;
		//奖励道具（道具ID,数量,性别（0通，1男，2女）,绑定（0,1绑定）,消失时间,强化等级,附加属性(类型|值);道具ID,数量）
		String[] activities_dataStrings = rewardstr.split(";");
		for (int i = 0; i < activities_dataStrings.length; i++) {
			String activities_item = activities_dataStrings[i];
			if (activities_item != null && !activities_item.isEmpty()) {
				String[] itemdataStrings = activities_item.split(",");
				int itemid = 0;
				int itemnum = 0;
				int sex = 0;//1 男 2 女
				boolean bind = true;
				long losttime = 0;
				int gradenum = 0;
				int append = 0;
				for (int j = 0; j < itemdataStrings.length; j++) {
					String item_data = itemdataStrings[j];
					if (item_data != null && !item_data.isEmpty()) {
						switch (j) {
							case 0: {
								itemid = Integer.valueOf(item_data);
							}
							break;
							case 1: {
								itemnum = Integer.valueOf(item_data);
							}
							break;
							case 2: {
								sex = Integer.valueOf(item_data);
							}
							break;
							case 3: {
								int bindidx = Integer.valueOf(item_data);
								bind = (bindidx == 1) ? true : false;
							}
							break;
							case 4: {
								losttime = Long.valueOf(item_data);
							}
							break;
							case 5: {
								gradenum = Integer.valueOf(item_data);
							}
							break;
							case 6: {
								append = Integer.valueOf(item_data);
							}
							break;
						}
					}
				}
				if (itemdataStrings.length >= 2) {
					if (itemid != 0 && itemnum != 0) {
						if (sex == 0 || sex == player.getSex()) {
							switch (itemid) {
								case -1: {
									Item moneyItem = Item.createMoney(itemnum);
									if (moneyItem != null) {
										itemlist.add(moneyItem);
									}
								}
								break;
								case -2: {
									Item goldItem = Item.createGold(itemnum, true);
									if (goldItem != null) {
										itemlist.add(goldItem);
									}
								}
								break;
								case -3: {
									Item zhenqiItem = Item.createZhenQi(itemnum);
									if (zhenqiItem != null) {
										itemlist.add(zhenqiItem);
									}
								}
								break;
								case -4: {
									Item expItem = Item.createExp(itemnum);
									if (expItem != null) {
										itemlist.add(expItem);
									}
								}
								break;
								case -5: {
									Item bindgoldItem = Item.createBindGold(itemnum);
									if (bindgoldItem != null) {
										itemlist.add(bindgoldItem);
									}
								}
								break;
								default: {
									List<Item> items = Item.createItems(itemid, itemnum, bind, losttime == 0 ? losttime : System.currentTimeMillis() + losttime * 1000, gradenum, append);
									if (!items.isEmpty()) {
										itemlist.addAll(items);
										itemNum += items.size();
									}
								}
								break;
							}
						}
					}
				}
			}
		}
		if (!itemlist.isEmpty()) {
			if (BackpackManager.getInstance().getEmptyGridNum(player) >= itemNum) {
				ResGetItemReasonsMessage sendMessage = new ResGetItemReasonsMessage();
				for (int i = 0; i < itemlist.size(); i++) {
					Item item = itemlist.get(i);
					if (item != null) {
						boolean is =true;
						int num = item.getNum();
						String itemname = item.acqItemModel().getQ_name();
						if (item.getItemModelId() == -1) {//Money
							if (!BackpackManager.getInstance().changeMoney(player, item.getNum(), Reasons.ACTIVITY_GIFT, Config.getId())) {
								List<Item> items = new ArrayList<Item>();
								items.add(Item.createMoney(item.getNum()));
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("活动系统邮件"), ResManager.getInstance().getString("由于未知原因，您的活动中铜币未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，签到奖励铜币未领取成功，请点击邮件领取。"));
								is=false;
							}
//						} else if (item.getItemModelId() == -2) {//Gold
//							if (!BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.ACTIVITY_GIFT, Config.getId())) {
//								List<Item> items = new ArrayList<Item>();
//								items.add(Item.createBindGold(item.getNum()));
//								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), "活动系统邮件", "由于未知原因，您的活动中礼金未领取成功，请点击附件领取。", (byte) 1, 0, items);
//								MessageUtil.notify_player(player, Notifys.ERROR, "由于未知原因，您的活动中礼金未领取成功，请点击邮件领取。");
//							}
						} else if (item.getItemModelId() == -3) {//zhenqi
							if (PlayerManager.getInstance().addZhenqi(player, item.getNum(),AttributeChangeReason.SIGNWAGE) == 0 && item.getNum() != 0) {
								List<Item> items = new ArrayList<Item>();
								items.add(Item.createZhenQi(item.getNum()));
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("签到奖励"), ResManager.getInstance().getString("由于未知原因，真气未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，签到奖励真气未领取成功，请点击邮件领取。"));
								is=false;
							}
						} else if (item.getItemModelId() == -4) {//exp
							PlayerManager.getInstance().addExp(player, item.getNum(),AttributeChangeReason.SIGNWAGE);
						} else if (item.getItemModelId() == -5) {//bindgold
							if (!BackpackManager.getInstance().changeBindGold(player, item.getNum(), Reasons.ACTIVITY_GIFT, Config.getId())) {
								List<Item> items = new ArrayList<Item>();
								items.add(Item.createBindGold(item.getNum()));
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("签到奖励"), ResManager.getInstance().getString("由于未知原因，礼金未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，签到奖励礼金未领取成功，请点击邮件领取。"));
								is=false;
							}
						}else if (item.getItemModelId() == -6) {	//战魂
							itemname = ResManager.getInstance().getString("七曜战魂");
							if(player != null){
								ManagerPool.arrowManager.addFightSpiritNum(player, 1, item.getNum(), true, ArrowReasonsType.SIGN);
								MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}({2})"),itemname,item.getNum()+"");
							}
						}else if (item.getItemModelId() == -7) {	//军功
							itemname = ResManager.getInstance().getString("军功值");
							if(player != null){
								ManagerPool.rankManager.addranknum(player, item.getNum(), RankType.OTHER);
								MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}({2})"),itemname,item.getNum()+"");
							}
						} else {//普通物品
							List<Item> items = new ArrayList<Item>();
							if (item instanceof Equip) {
								Equip equip = (Equip) item;
								items = Item.createItems(item.getItemModelId(), item.getNum(), item.isBind(), (long) equip.getLosttime() * 1000, equip.getGradeNum(), equip.getAttributes().size());
							} else {
								items = Item.createItems(item.getItemModelId(), item.getNum(), item.isBind(), (long) item.getLosttime() * 1000, 0, 0);
							}
							if (!BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, Config.getId())) {
								MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("签到奖励"), ResManager.getInstance().getString("由于未知原因，该物品未领取成功，请点击附件领取。"), (byte) 1, 0, items);
								MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("由于未知原因，您签到奖励中有物品未领取成功，请点击邮件领取。"));
								is=false;
							} else {
								ItemReasonsInfo itemReasonsInfo = new ItemReasonsInfo();
								if (!items.isEmpty()) {
									itemReasonsInfo.setItemId(items.get(0).getId());
								}
								itemReasonsInfo.setItemModelId(item.getItemModelId());
								itemReasonsInfo.setItemNum(item.getNum());
								itemReasonsInfo.setItemReasons(0);
								sendMessage.getItemReasonsInfoList().add(itemReasonsInfo);
							}
						}
						if (is) {
							MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardname,itemname,num+"");
						}
					}
				}
				sendMessage.setItemReasons(Reasons.ACTIVITY_GIFT.getValue());
				MessageUtil.tell_player_message(player, sendMessage);
			} else {
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("您的包裹格子不足，不能获得签到奖励物品，已经转发邮件。"));
				MailServerManager.getInstance().sendSystemMail(player.getId(), player.getName(), ResManager.getInstance().getString("签到奖励"), ResManager.getInstance().getString("由于您的包裹格子不足，签到奖励物品未领取成功，请点击附件领取。"), (byte) 1, 0, itemlist);
			}
		}
	}
	
	
	
	/**在线摇奖
	 * 所有奖励均为绑定
	 */
	public void onlineERNIE(Player player ){
		Wage wage = getCurWage(player);
		int interval = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.ERNIE_INTERVAL.getValue()).getQ_int_value();
		if (wage.getErnienum() >= 4) {
			MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("今日的摇奖次数已经全部使用，明天再来吧。"));
			return;
		}
		
		int minute = player.getDayonlinetime()/60 ;
		int num  = minute/interval;
		if (num > 4) {
			num = 4;
		}
		
		
		int shake = num -wage.getErnienum();
		ResWageERNIEinofMessage cmsg = new ResWageERNIEinofMessage();
		for (int i = 0; i < shake; i++) {
			List<FruitReward> fruitRewards= ManagerPool.zonesFlopManager.createFruitRewardList(player,1,0);//筛选并设置奖励列表 
			if (fruitRewards.size() > 0) {
				int rnd= 0;
				List<Integer> rndlist = new ArrayList<Integer>();
				for (FruitReward fruitReward : fruitRewards) {
					 Q_spirittree_pack_conBean fruitdata = ManagerPool.zonesFlopManager.getpackcondata().get(fruitReward.getIdx());
					 if (fruitdata == null) {
						 rndlist.add(100);	//数据库改变后，默认值
					}else{
						rndlist.add(fruitdata.getQ_selected_rnd());
					}
				}
				
				rnd = RandomUtils.randomIndexByProb(rndlist);
				FruitReward fruitReward = fruitRewards.get(rnd);
				fruitReward.setBind(true);	//设置为绑定
				giveRewarded(player, fruitReward,0);	//给奖励
				cmsg.getFruitRewardinfo().add(fruitReward.makeinfo());
				wage.getErnierewardlist().add(fruitReward);
				wage.setErnienum(wage.getErnienum()+1);
				cmsg.getPos().add(wage.getErnienum());
			}
		}
		MessageUtil.tell_player_message(player, cmsg);
	}
	
	
	
	/**在线摇奖
	 * type = 0翻牌奖励，1通关固定奖励
	 * 
	 * @param msg
	 */
	public void giveRewarded(Player player , FruitReward fruitReward,int type) {
		String rewardedname = ""; //默认0
		if (type == 0) {
			rewardedname = ResManager.getInstance().getString("在线时间摇奖");
		}
		
		int id = fruitReward.getItemModelid();
		long action = Config.getId();
		//-1铜币，-2元宝，-3真气，-4经验  -5礼金
		if (fruitReward.getNum() == 0) {
			return ;
		}
		boolean issuccess = true;
		List<Item> createItems = new ArrayList<Item>();
		String itemname="";
		if (id == -1) {
			itemname = ResManager.getInstance().getString("铜币");
			if(player != null && ManagerPool.backpackManager.changeMoney(player, fruitReward.getNum(), Reasons.RAID_MONEY, action)){
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");	
			}else {
				issuccess =false;
			}
			
//		}else if (id== -2) {
//			itemname = "元宝";
//			if(player != null && ManagerPool.backpackManager.changeGold(player, fruitReward.getNum(), Reasons.RAID_YUANBAO, action)){
//				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, "恭喜！获得了{1}{2}({3})",rewardedname,itemname,fruitReward.getNum()+"");
//			}else {
//				issuccess =false;
//			}
		}else if ( id == -3) {
			itemname = ResManager.getInstance().getString("真气");
			if(player != null){
				ManagerPool.playerManager.addZhenqi(player, fruitReward.getNum(),AttributeChangeReason.SIGNWAGE);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -4) {
			itemname = ResManager.getInstance().getString("经验");
			if(player != null){
				ManagerPool.playerManager.addExp(player, fruitReward.getNum(),AttributeChangeReason.SIGNWAGE);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -5) {
			itemname = ResManager.getInstance().getString("礼金");
			if(player != null && ManagerPool.backpackManager.changeBindGold(player, fruitReward.getNum(), Reasons.RAID_BIND_YUANBAO, action)){
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
			
		}else if (id == -6) {	//战魂
			itemname = ResManager.getInstance().getString("七曜战魂");
			if(player != null){
				ManagerPool.arrowManager.addFightSpiritNum(player, 1, fruitReward.getNum(), true, ArrowReasonsType.SIGN);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}
		}else if (id == -7) {	//军功
			itemname = ResManager.getInstance().getString("军功值");
			if(player != null){
				ManagerPool.rankManager.addranknum(player, fruitReward.getNum(), RankType.OTHER);
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})"),rewardedname,itemname,fruitReward.getNum()+"");
			}	
		}else if (id > 0) {
			Q_itemBean itemMode = ManagerPool.dataManager.q_itemContainer.getMap().get(fruitReward.getItemModelid());
			if (itemMode != null) {
				itemname = itemMode.getQ_name();
				createItems = Item.createItems(fruitReward.getItemModelid(), fruitReward.getNum(), fruitReward.isBind(),((fruitReward.getLosttime() == 0) ? fruitReward.getLosttime() : (System.currentTimeMillis() + fruitReward.getLosttime() * 1000)) , fruitReward.getStrenglevel(), null);
				List<FruitRewardAttr> attrs = fruitReward.getFruitRewardAttrslist();
				//写入属性
				if (itemMode.getQ_type()== ItemTypeConst.EQUIP) {
					if (attrs.size() > 0) {
						for (Item item : createItems) {
							Equip equip = (Equip)item;
							for (FruitRewardAttr attr : attrs) {
								Attribute attribute = new Attribute();
								attribute.setType(attr.getType());
								attribute.setValue(attr.getValue());
								equip.getAttributes().add(attribute);
							}
						}
					}
				}

				if(player != null && ManagerPool.backpackManager.getEmptyGridNum(player) >= createItems.size()) {
					BackpackManager.getInstance().addItems(player, createItems,Reasons.RAID_ITEM,action);
					MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM, ResManager.getInstance().getString("恭喜！获得了{1}{2}({3})。"),rewardedname,itemMode.getQ_name(),fruitReward.getNum()+"");
				}else {
					issuccess =false;
				}
			}else {
				log.error(rewardedname+"道具item不存在：["+id +"]");
			}
		}else{
			log.error(rewardedname+"ID类型未知：["+id+"]");
		}
		
		if(issuccess == false){
			itemname = itemname+"("+fruitReward.getNum()+")";
			if (id > 0) {
				ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems);
			}else {
				if (id == -1 ) {	//铜币
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createMoney(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0, createItems2);
				//}else if (id == -2 ) {	//元宝
				//	ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,"系统邮件",rewardedname+":"+itemname,(byte) 2,fruitReward.getNum(),new ArrayList<Item>());
				}else if (id == -3) {	//真气
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createZhenQi(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}else if ( id == -4 ) {	//经验
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createExp(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}else if ( id == -5) {	//礼金
					List<Item> createItems2 = new ArrayList<Item>();
					createItems2.add(Item.createBindGold(fruitReward.getNum()));
					ManagerPool.mailServerManager.sendSystemMail(player.getId(),null,ResManager.getInstance().getString("系统邮件"),rewardedname+":"+itemname,(byte) 1,0,createItems2);
				}
			}
			if (player != null) {
				MessageUtil.notify_player(player,Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("由于您的包裹已满，")+rewardedname+"："+itemname+ResManager.getInstance().getString(" 已经通过邮件发送给您。"));
			}
		}
	}
	
	
	/**登录发送累计签到次数
	 * 
	 * @param player
	 */
	public void loginRessignnum(Player player ){
		RessignnumToClientMessage cnms= new RessignnumToClientMessage();
		cnms.setSignnum(player.getSignsum());
		MessageUtil.tell_player_message(player, cnms);
	}
	

	public void testgm(Player player,int type){
		Sign sign = getCurrentMonthSign(player);
		if (type == 1) {
			sign.getDaylist().clear();
			sign.getRewardstatelist().clear();
			player.setSignsum(31);
			for (int i = 1; i <= 31; i++) {
				sign.getDaylist().add(i);
			}
			openSign(player);
			loginRessignnum(player);
		}else if (type ==2) {
			Wage wage = getCurWage(player);
			wage.setErnieday(0);
			wage.setErnienum(0);
			openWage(player);
		}
	}
	
	
}

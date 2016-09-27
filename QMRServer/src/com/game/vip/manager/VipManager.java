package com.game.vip.manager;

import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.game.backpack.manager.BackpackManager;
import com.game.backpack.structs.BuffGoods;
import com.game.backpack.structs.Item;
import com.game.buff.manager.BuffManager;
import com.game.buff.structs.Buff;
import com.game.config.Config;
import com.game.data.bean.Q_buffBean;
import com.game.data.bean.Q_itemBean;
import com.game.data.bean.Q_vipBean;
import com.game.data.manager.DataManager;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.rank.manager.RankManager;
import com.game.structs.Reasons;
import com.game.utils.Global;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;
import com.game.vip.bean.VipInfo;
import com.game.vip.log.RoleVipLog;
import com.game.vip.message.ResPlayerVIPInfoMessage;
import com.game.vip.message.ResVIPAnnounceMessage;
import com.game.vip.message.ResVIPExpiredMessage;

public class VipManager {

	private static Logger log = Logger.getLogger(VipManager.class);
	private static Object obj = new Object();
	// 管理类实例
	private static VipManager manager;

	private VipManager() {
	}

	public static VipManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new VipManager();
			}
		}
		return manager;
	}
	/**
	 * 得到玩家VIP等级   0-无VIP  1-白金VIP 2-钻石VIP 3-至尊VIP
	 */
	public int getPlayerVipId(Player player){
		int vipid = 0;
		List<Q_vipBean> viplist = DataManager.getInstance().q_vipContainer.getList();
		for(Q_vipBean bean: viplist){
			List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, bean.getQ_buffid());
			if(bufflist.size()>0){
				vipid = bean.getQ_id();
				break;
			}
		}
		
		if(vipid == 101) {//临时VIP白金卡
			vipid=1;
		}else if (vipid == 103) {//临时VIP至尊卡
			vipid=3;
		}
		return vipid;
	}
	
	/**
	 * 得到玩家VIP卡ID  0-无VIP  1-白金VIP 2-钻石VIP 3-至尊VIP  101-临时VIP
	 * 需要知道是 真正的白金VIP 还是 临时VIP 时调用此方法
	 */
	public int getPlayerVipIdReal(Player player){
		int vipid = 0;
		List<Q_vipBean> viplist = DataManager.getInstance().q_vipContainer.getList();
		for(Q_vipBean bean: viplist){
			List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, bean.getQ_buffid());
			if(bufflist.size()>0){
				vipid = bean.getQ_id();
				break;
			}
		}
		return vipid;
	}
	
	//发送VIP信息给客户端
	public void sendVipInfoToClient(Player player){
		ResPlayerVIPInfoMessage resmsg = new ResPlayerVIPInfoMessage();
		VipInfo playerVipInfo = getPlayerVipInfo(player);
		
		resmsg.setVipinfo(playerVipInfo); 
		MessageUtil.tell_player_message(player, resmsg);
	}
	
	//得到玩家的 VIP 信息
	public VipInfo getPlayerVipInfo(Player player){
		VipInfo info = new VipInfo();
		int vipid = getPlayerVipId(player);
		if(vipid>0){
			info.setVipId(vipid);
			info.setStatus(canReceiveReward(player)? 1:0);
			info.setRemain(getVipRemainTime(player));
		}else{ //vipid<=0
			info.setVipId(0);
			info.setStatus(0);
			info.setRemain(0);
		}
		if(player.getVipright().getReceivedTopReward()>0){
			info.setShowad((byte)0);  //领过 不显示
		}else{
			info.setShowad((byte)1);
		}
		return info;
	}	
	
	//领取至尊VIP奖励
	public void receiveVipTopReward(Player player){
		if(player.getVipright().getReceivedTopReward()>0){ //领过
			return ;
		}else{
			int vipid = getPlayerVipId(player);
			if(vipid==3){
				//过期时间=当前时间+72小时
				if(BackpackManager.getInstance().getEmptyGridNum(player)>=5){ //是否能装的下
					long losttime =  System.currentTimeMillis()+72*60*60*1000;
					long actionid = Config.getId();
					List<Item> items = Item.createItems(1023 , 1, true, losttime);  //坐骑祝福丹
					items.addAll(Item.createItems(8403, 1, true, losttime));	//龙元心法秘籍
					items.addAll(Item.createItems(1018, 2, true, 0));    //连斩延时丹 *2  1018
					items.addAll(Item.createItems(1100, 3, true, 0));    //红玫瑰*3 1100
					items.addAll(Item.createItems(1015, 2, true, 0));    //双倍收益丹*2  1015
					BackpackManager.getInstance().addItems(player, items, Reasons.ACTIVITY_GIFT, actionid);
					//设置已领取标记
					player.getVipright().setReceivedTopReward(1);
					//通知客户端
					sendVipInfoToClient(player);
					LogService.getInstance().execute(new RoleVipLog(player, 6, vipid, vipid, actionid));
				}else{
					MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("包裹空间不足,至尊VIP奖励领取失败"));
				}
			}
		}
	}
	
	//得到VIP剩余时间
	public int getVipRemainTime(Player player){ 
		long remain = 0;
		List<Q_vipBean> viplist = DataManager.getInstance().q_vipContainer.getList();
		for(Q_vipBean bean: viplist){
			List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, bean.getQ_buffid());
			if(bufflist.size()>0){
				Buff vipbuff = bufflist.get(0);
				remain = vipbuff.getStart()+vipbuff.getTotalTime()-System.currentTimeMillis();
				break;
			}
		}
		return (int)(remain/1000);
	}
	
	//领取VIP礼包 
	public boolean receiveVIPReward(Player player){
		int vipid = getPlayerVipIdReal(player);
		HashMap<Integer,Q_vipBean> map = DataManager.getInstance().q_vipContainer.getMap();
		Q_vipBean vipbean = map.get(vipid);
		if(vipbean!=null){
			int giftid = vipbean.getQ_dayreceivegiftid();		//礼包
			int money = vipbean.getQ_dayreceive_money();		//铜币			
			int bindgold = vipbean.getQ_dayreceive_lijin();		//礼金
			if(Global.BAG_MAX_COPPER-player.getMoney()<money){  //铜币满了
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，背包铜币数己达上限"));
				return false;
			}
			if(Global.BAG_MAX_BINDGOLD-player.getBindGold()<bindgold){ //礼金满了
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，礼金数己达上限"));
				return false;
			}
			if(BackpackManager.getInstance().getAbleAddNum(player, giftid, true, 0)<=0){
				MessageUtil.notify_player(player, Notifys.ERROR, ResManager.getInstance().getString("操作失败，背包空间不足"));
				return false;
			}
			long actionid = Config.getId();
			List<Item> vipgift = Item.createItems(giftid, 1, true, 0);
			if(BackpackManager.getInstance().addItems(player, vipgift, Reasons.VIP_DAILYGIFT, actionid)){ //领取成功
				BackpackManager.getInstance().changeMoney(player, money, Reasons.VIP_DAILYMONEY, actionid);
				BackpackManager.getInstance().changeBindGold(player, bindgold, Reasons.VIP_DAILYBINDGOLD, actionid);
				RankManager.getInstance().vipGiveRank(player);
				player.getVipright().setLastReceiveVipRewardTime(System.currentTimeMillis());
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("领取成功。"));
				
				RoleVipLog viplog = new RoleVipLog(player, 5, vipid, vipid, actionid);
				LogService.getInstance().execute(viplog); //执行保存日志
				return true;
			}
		}
		return false;
	}
	
	public Q_vipBean getVipModelByBuffid(int buffid){
		Q_vipBean bean = null;
		List<Q_vipBean> viplist = DataManager.getInstance().q_vipContainer.getList();
		for(Q_vipBean b: viplist){
			if(b.getQ_buffid()==buffid){
				bean = b;  break;
			}
		}
		return bean;
	}
	
	//是否可以领取VIP礼包  判断条件 上次领取时间与当前时间是否在同一天  前置条件是判断是否是VIP 是VIP才调用此方法
	public boolean canReceiveReward(Player player){
		long lastreceivetime = player.getVipright().getLastReceiveVipRewardTime();
		long now = System.currentTimeMillis();
		boolean issameday = TimeUtil.isSameDay(lastreceivetime, now);
		
		return !issameday;
	}
	
	//vip状态移除事件
	public void removeVipEvent(Player player){
		//通知前端 
		ResVIPExpiredMessage resmsg = new ResVIPExpiredMessage();
		MessageUtil.tell_player_message(player, resmsg);
		//通知世界服
		PlayerManager.getInstance().stSyncExterior(player);
		//更新副本次数
		ManagerPool.zonesManager.stResZoneSurplusSum(player);
		//
		RoleVipLog viplog = new RoleVipLog(player, 4, VipManager.getInstance().getPlayerVipId(player), 0, -1);
		LogService.getInstance().execute(viplog); //执行保存日志
	}
	
	//使用VIP卡
	public void userVipCard(Player player, BuffGoods item, String buff){
		if(player.getWebName().equals("qq3366") && player.getVipright().getWebVipLevel()<=0 ){ return; }//3366平台使用VIP的条件必须是平台VIP
		int vipid = VipManager.getInstance().getPlayerVipIdReal(player); //玩家当前vipid
		int curbuffid = 0;
		Q_vipBean curvipBean = null;
		if(vipid>0){
			curvipBean = DataManager.getInstance().q_vipContainer.getMap().get(vipid);
			curbuffid = curvipBean.getQ_buffid(); //当前buffid
		}
		if(!StringUtils.isNumeric(buff)){ log.error("错误！检查VIP配置表,BUFF字段不是数字");  return; }
		int buffid = Integer.parseInt(buff);  //要加的buffid
		String tip = ResManager.getInstance().getString("成功使用{1},获得{2}特权时间{3}天");
		Q_vipBean targetvipBean = VipManager.getInstance().getVipModelByBuffid(buffid);
		Q_buffBean targetbuffBean = DataManager.getInstance().q_buffContainer.getMap().get(buffid);
		Q_itemBean useitemBean = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
		if(curbuffid==1411){ //当前是临时VIP 全部执行替换操作 并且重置VIP每日礼包领奖时间 应该排除临时 但是临时卡的使用是走useVipTmpCard方法不会进这里
			List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, curbuffid);
			if(bufflist.size()>0){
				long actionid = Config.getId();
				Buff curbuff = bufflist.get(0);
				long remainbufftime = curbuff.getTotalRemainTime();
				ManagerPool.buffManager.removeBuff(player, curbuff);
				ManagerPool.buffManager.addBuff(player, player, buffid, remainbufftime, 0, 0);//添加新VIPbuff加上剩余时间
				BackpackManager.getInstance().removeItem(player, item, 1,Reasons.GOODUSE,actionid);
				VipManager.getInstance().resetVipReward(player); //重置VIP每日礼包领奖时间
				player.getVipright().resetVipRight(player); //重置vip权限 免费元宝传送次数
				MessageUtil.notify_player(player, Notifys.SUCCESS, tip, useitemBean.getQ_name(), targetvipBean.getQ_name(), ""+(targetbuffBean.getQ_effect_time()/3600/24) );
				RoleVipLog viplog = new RoleVipLog(player, 3, 101, VipManager.getInstance().getPlayerVipId(player), actionid);
				LogService.getInstance().execute(viplog); //执行保存日志
				PlayerManager.getInstance().stSyncExterior(player);//vip状态同步到世界服
			}
		}else if (curbuffid==1412) {	//移除临时至尊VIP，并加正式VIP
			
			if (item.getItemModelId() != 1022) {
				MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您已经使用至尊VIP体验卡，等到期后才能使用{1}"),item.acqItemModel().getQ_name() );
				return ;
			}
			
			List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, curbuffid);
			if(bufflist.size()>0){
				long actionid = Config.getId();
				Buff curbuff = bufflist.get(0);
				long remainbufftime = curbuff.getTotalRemainTime();
				ManagerPool.buffManager.removeBuff(player, curbuff);
				BackpackManager.getInstance().removeItem(player, item, 1,Reasons.GOODUSE,actionid);
				ManagerPool.buffManager.addBuff(player, player, buffid, remainbufftime, 0, 0);//添加新VIPbuff加上剩余时间
				VipManager.getInstance().resetVipReward(player); //重置VIP每日礼包领奖时间
				player.getVipright().resetVipRight(player); //重置vip权限 免费元宝传送次数
				MessageUtil.notify_player(player, Notifys.SUCCESS, tip, useitemBean.getQ_name(), targetvipBean.getQ_name(), ""+(targetbuffBean.getQ_effect_time()/3600/24) );
				RoleVipLog viplog = new RoleVipLog(player, 3, 103, VipManager.getInstance().getPlayerVipId(player), actionid);
				LogService.getInstance().execute(viplog); //执行保存日志
				PlayerManager.getInstance().stSyncExterior(player);//vip状态同步到世界服
			}
		}else{
			if(vipid<=0){ //成为VIP 
				int result = ManagerPool.buffManager.addBuff(player, player, buffid, 1, 0, 0);//加buff
				if (result > 0) { //使用vip卡成功
					long actionid = Config.getId();
					BackpackManager.getInstance().removeItem(player, item, 1,Reasons.GOODUSE, actionid);
					ResVIPAnnounceMessage vipannmsg = new ResVIPAnnounceMessage(); //使用成功后 播放公告
					vipannmsg.setPlayername(player.getName());
					vipannmsg.setVipid(targetvipBean.getQ_id());
					MessageUtil.tell_world_message(vipannmsg); //通知前端发送公告
					VipManager.getInstance().resetVipReward(player); //重置VIP每日礼包领奖时间
					player.getVipright().resetVipRight(player); //重置vip权限 免费元宝传送次数
					PlayerManager.getInstance().stSyncExterior(player);//vip状态同步到世界服
					//屏幕提示
					MessageUtil.notify_player(player, Notifys.SUCCESS, tip, useitemBean.getQ_name(), targetvipBean.getQ_name(), ""+(targetbuffBean.getQ_effect_time()/3600/24) );
					RoleVipLog viplog = new RoleVipLog(player, 1, vipid, VipManager.getInstance().getPlayerVipId(player), actionid);
					LogService.getInstance().execute(viplog); //执行保存日志
				}
			}else if(buffid == curbuffid){ //是同一种VIP
				List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, curbuffid);
				if(bufflist.size()>0){
					long actionid = Config.getId();
					ManagerPool.buffManager.addBuff(player, player, buffid, 0, 0, 0);//添加新VIPbuff加上剩余时间
					BackpackManager.getInstance().removeItem(player, item, 1,Reasons.GOODUSE,actionid);
					MessageUtil.notify_player(player, Notifys.SUCCESS, tip, useitemBean.getQ_name(), targetvipBean.getQ_name(), ""+(targetbuffBean.getQ_effect_time()/3600/24) );
					RoleVipLog viplog = new RoleVipLog(player, 2, vipid, VipManager.getInstance().getPlayerVipId(player), actionid);
					LogService.getInstance().execute(viplog); //执行保存日志
				}
			}else if(buffid > curbuffid) {  //要加的vip>当前vip  ->升级VIP  ->清空领奖时间
				List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, curbuffid);
				if(bufflist.size()>0){
					long actionid = Config.getId();
					Buff curbuff = bufflist.get(0);
					long remainbufftime = curbuff.getTotalRemainTime();
					ManagerPool.buffManager.removeBuff(player, curbuff);
					ManagerPool.buffManager.addBuff(player, player, buffid, remainbufftime, 0, 0);//添加新VIPbuff加上剩余时间
					BackpackManager.getInstance().removeItem(player, item, 1,Reasons.GOODUSE,actionid);
					VipManager.getInstance().resetVipReward(player); //重置VIP每日礼包领奖时间
					player.getVipright().resetVipRight(player); //重置vip权限 免费元宝传送次数
					MessageUtil.notify_player(player, Notifys.SUCCESS, tip, useitemBean.getQ_name(), targetvipBean.getQ_name(), ""+(targetbuffBean.getQ_effect_time()/3600/24) );
					RoleVipLog viplog = new RoleVipLog(player, 3, vipid, VipManager.getInstance().getPlayerVipId(player), actionid);
					LogService.getInstance().execute(viplog); //执行保存日志
				}
			}else if(buffid < curbuffid){  //要加的vip<当前vip  ->保持vip   //在当前剩余时间基础上+要加的vip时间
				List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, curbuffid);
				if(bufflist.size()>0){
					long actionid = Config.getId();
					ManagerPool.buffManager.addBuff(player, player, buffid, 0, 0, 0);//添加新VIPbuff加上剩余时间
					BackpackManager.getInstance().removeItem(player, item, 1,Reasons.GOODUSE, actionid);
					MessageUtil.notify_player(player, Notifys.SUCCESS, tip, useitemBean.getQ_name(), curvipBean.getQ_name(), ""+(targetbuffBean.getQ_effect_time()/3600/24) );
					RoleVipLog viplog = new RoleVipLog(player, 2, vipid, VipManager.getInstance().getPlayerVipId(player), actionid);
					LogService.getInstance().execute(viplog); //执行保存日志
				}
			}
		}
		//更新副本次数
		ManagerPool.zonesManager.stResZoneSurplusSum(player);
		//更新玩家VIP信息
		ResPlayerVIPInfoMessage resmsg = new ResPlayerVIPInfoMessage();
		resmsg.setVipinfo(ManagerPool.vipManager.getPlayerVipInfo(player)); 
		MessageUtil.tell_player_message(player, resmsg);
	}
	
	//使用临时VIP卡
	public void useVipTmpCard(Player player, BuffGoods item, String buff){
		if(player.getWebName().equals("qq3366") && player.getVipright().getWebVipLevel()<=0 ){ return; }//3366平台使用VIP的条件必须是平台VIP
		int vipid = VipManager.getInstance().getPlayerVipId(player); //玩家当前vipid
		if(!StringUtils.isNumeric(buff)){ log.error("错误！检查VIP配置表,BUFF字段不是数字");  return; }
		int buffid = Integer.parseInt(buff);
		String tip = ResManager.getInstance().getString("成功使用{1},获得{2}特权时间{3}小时");
		RoleVipLog viplog = new RoleVipLog();
		viplog.setSid(player.getCreateServerId());
		Q_vipBean targetvipBean = VipManager.getInstance().getVipModelByBuffid(buffid);
		Q_itemBean useitemBean = DataManager.getInstance().q_itemContainer.getMap().get(item.getItemModelId());
		List<Buff> zzbufflist = ManagerPool.buffManager.getBuffByModelId(player, 1412);
		if (zzbufflist.size() > 0) {
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您已经使用至尊VIP体验卡，不能再使用{1}"),item.acqItemModel().getQ_name() );
			return;
		}
		
		if(vipid>0){ //已是VIP了再使用临时卡 则延长时间
			Q_vipBean curvipBean = DataManager.getInstance().q_vipContainer.getMap().get(vipid);
			int curbuffid = curvipBean.getQ_buffid(); //当前buffid
			List<Buff> bufflist = ManagerPool.buffManager.getBuffByModelId(player, curbuffid);
			if(bufflist.size()>0){
				long actionid = Config.getId();
				ManagerPool.buffManager.addBuff(player, player, buffid, 0, 0, 0);//添加新VIPbuff加上剩余时间
				BackpackManager.getInstance().removeItem(player, item, 1,Reasons.GOODUSE, actionid);
				MessageUtil.notify_player(player, Notifys.SUCCESS, tip, useitemBean.getQ_name(), curvipBean.getQ_name(), "1" );
				viplog = new RoleVipLog(player, 2, 101, VipManager.getInstance().getPlayerVipId(player), actionid);
			}
		}else{ //不是VIP使用临时卡成为VIP
			int result = ManagerPool.buffManager.addBuff(player, player, buffid, 1, 0, 0);//加buff
			if (result > 0) { //使用vip卡成功
				long actionid = Config.getId();
				BackpackManager.getInstance().removeItem(player, item, 1,Reasons.GOODUSE, actionid);
				VipManager.getInstance().resetVipReward(player); //重置VIP每日礼包领奖时间
				player.getVipright().resetVipRight(player); //重置vip权限
				PlayerManager.getInstance().stSyncExterior(player);//vip状态同步到世界服
				//屏幕提示
				MessageUtil.notify_player(player, Notifys.SUCCESS, tip, useitemBean.getQ_name(), targetvipBean.getQ_name(), "1" );
				viplog = new RoleVipLog(player, 1, vipid, 101, actionid);
			}
		}
		//保存日志
		LogService.getInstance().execute(viplog);
		//更新玩家VIP信息
		ResPlayerVIPInfoMessage resmsg = new ResPlayerVIPInfoMessage();
		resmsg.setVipinfo(ManagerPool.vipManager.getPlayerVipInfo(player)); 
		MessageUtil.tell_player_message(player, resmsg);
	}
	
	/**使用临时至尊VIP
	 * 
	 * @param player
	 * @param item
	 * @param buff
	 */
	public void useVipTmpZhiZunCard(Player player, BuffGoods item, String buff){
		if(player.getWebName().equals("qq3366") && player.getVipright().getWebVipLevel()<=0 ){ return; }//3366平台使用VIP的条件必须是平台VIP
		int vipid = getPlayerVipIdReal(player); //玩家当前vipid
		String name = item.acqItemModel().getQ_name();
		if (vipid > 0 ) {
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("您已经是VIP，不能再使用{1}"),name );
			return ;
		}
		
		if(ManagerPool.backpackManager.removeItem(player, item, 1,Reasons.GOODUSE,  Config.getId())){
			ManagerPool.buffManager.addBuff(player, player, Integer.valueOf(buff), 0, 0, 0);//添加新VIPbuff
			VipManager.getInstance().resetVipReward(player); //重置VIP每日礼包领奖时间
			PlayerManager.getInstance().stSyncExterior(player);//vip状态同步到世界服
			//更新副本次数
			ManagerPool.zonesManager.stResZoneSurplusSum(player);
			//更新玩家VIP信息
			ResPlayerVIPInfoMessage resmsg = new ResPlayerVIPInfoMessage();
			resmsg.setVipinfo(ManagerPool.vipManager.getPlayerVipInfo(player)); 
			MessageUtil.tell_player_message(player, resmsg);
			MessageUtil.notify_player(player, Notifys.SUCCESS, ResManager.getInstance().getString("成功使用{1}，获得7天VIP时间") ,name);
		}
	}
	
	
	
	//GM命令用
	public void removeVip(Player player){
		int vipid = getPlayerVipId(player);
		if(vipid>0){
			if(vipid==1){
				BuffManager.getInstance().removeByBuffId(player, 1401); //移除白金VIP
				BuffManager.getInstance().removeByBuffId(player, 1411); //移除临时白金VIP
				removeVipEvent(player);
				
			}else if(vipid==2){
				BuffManager.getInstance().removeByBuffId(player, 1402);
				removeVipEvent(player);
				
			}else if(vipid==3){
				BuffManager.getInstance().removeByBuffId(player, 1403);
				removeVipEvent(player);
				
				
			}
		}
	}
	public void resetFreeFly(Player player){
		long now = System.currentTimeMillis();
		long last = player.getVipright().getLastFreeFlyTime();
		if(!TimeUtil.isSameDay(now, last)){ //上次与当前不是同一天 需要重设免费次数
			player.getVipright().resetVipRight(player);
		}
	}
	public void resetVipReward(Player player){
		player.getVipright().setLastReceiveVipRewardTime(0);
	}
//	
//	/**
//	 * 获取玩家vip等级
//	 * @param player
//	 */
//	@SuppressWarnings("unchecked")
//	public void setVipLevel(Player player){
//		if("".equals(WServer.getInstance().getServerWeb())){
//			try{
//				String result = HttpUtil.postr("http://openapi.tencentyun.com/v3/user/get_info", "openid=" + player.getUserName() + "&openkey=" + player.getAgentPlusdata() + "&appid=2&sig=VrN%2BTn5J%2Fg4IIo0egUdxq6%2B0otk%3D&pf=" + player.getAgentColdatas() + "&format=json&userip=" + player.getLoginIp() + "&charset=utf-8&flag=2");
//				HashMap<String, Object> objs = (HashMap<String, Object>)JSON.parse(result);
//				
//			}catch (Exception e) {
//				log.error(e, e);
//			}
//		}else{
//			player.getVipright().setWebVipLevel(1);
//		}
//	}
}

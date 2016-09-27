package com.game.rank.manager;

import java.util.List;

import com.game.buff.structs.Buff;
import com.game.count.structs.CountTypes;
import com.game.data.bean.Q_rankBean;
import com.game.data.bean.Q_vipBean;
import com.game.dblog.LogService;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.prompt.structs.Notifys;
import com.game.rank.bean.Rankinfo;
import com.game.rank.log.RankLog;
import com.game.rank.message.ResRankExpToClientMessage;
import com.game.rank.message.ResRankUPToClientMessage;
import com.game.rank.message.ResRankinfoToClientMessage;
import com.game.rank.structs.RankType;
import com.game.utils.MessageUtil;
import com.game.utils.TimeUtil;

public class RankManager {

	private static Object obj = new Object();
	// 管理类实例
	private static RankManager manager;

	private RankManager(){}

	public static RankManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new RankManager();
			}
		}
		return manager;
	}
	
	

	
	
	
	/**发送军衔消息
	 * 
	 * @param player
	 */
	public void sendRankinfo(Player player) {
		int ranklevel = player.getRanklevel();
		int rankexp = player.getRankexp();
		ResRankinfoToClientMessage msg = new ResRankinfoToClientMessage();
		Rankinfo rankinfo =new Rankinfo();
		rankinfo.setRankexp(rankexp);
		rankinfo.setRanklevel(ranklevel);
		long num = ManagerPool.countManager.getCount(player, CountTypes.DAY_RANK,null);
		if (num == 0) {
			ManagerPool.countManager.addCount(player, CountTypes.DAY_RANK, null,1, 0, 0);	//初始化
		}
		msg.setDayrankexp((int) num);
		msg.setRankinfo(rankinfo);
		MessageUtil.tell_player_message(player, msg);
		ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.RANK);
	}
	
	
	
	/**军衔升级
	 * 
	 * @param player
	 * @return 
	 */
	public boolean rankup(Player player) {
		int ranklevel = player.getRanklevel()+1;//下一个军衔等级
		int rankexp = player.getRankexp();	
		Q_rankBean db = ManagerPool.dataManager.q_rankContainer.getMap().get(ranklevel);
		if(db != null) {
			if(rankexp >= db.getQ_ranknum() ) {
				if ( player.getLevel() >= db.getQ_need_level()) {
					player.setRanklevel(ranklevel);
					player.setRankexp(rankexp - db.getQ_ranknum());
					//重新计算军衔增加的属性
					
					ManagerPool.playerAttributeManager.countPlayerAttribute(player, PlayerAttributeType.RANK);
					MessageUtil.notify_player(player, Notifys.NORMAL,ResManager.getInstance().getString("恭喜您获得【{1}】军衔！"),db.getQ_rankname());
					if (db.getQ_bulletin() == 1) {
						MessageUtil.notify_All_player(Notifys.CHAT_BULL,ResManager.getInstance().getString("【{1}】被授予【{2}】军衔，大家都来膜拜吧！"),player.getName(),db.getQ_rankname());
					}
					ResRankUPToClientMessage msg = new ResRankUPToClientMessage();
					Rankinfo rankinfo =new Rankinfo();
					rankinfo.setRankexp(rankexp - db.getQ_ranknum());
					rankinfo.setRanklevel(ranklevel);
					msg.setRankinfo(rankinfo);
					msg.setPlayerid(player.getId());
					MessageUtil.tell_round_message(player, msg);
					
					sendRankinfo(player);
					
					RankLog rlog = new RankLog();
					rlog.setOldrankexp(-1);
					rlog.setOldranklevel(ranklevel-1);
					rlog.setNewranklevel(ranklevel);
					rlog.setNewrankexp(-1);
					rlog.setReason(-1);
					rlog.setPlayerid(player.getId());
					rlog.setSid(player.getCreateServerId());
					LogService.getInstance().execute(rlog);
					return true;
				}else {
					MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("请提升等级至{1}级，可晋升军衔为【{2}】"),db.getQ_need_level()+"",db.getQ_rankname());
				}
			}
		}
		return false;
	}
	
	
	/**
	 * 增加军功
	 * @param player
	 * @param newrankexp
	 */
	public void  addranknum(Player player , int newrankexp,int reason){
		int rankexp = player.getRankexp();	
		//int ranklevel = player.getRanklevel()+1;//下一个军衔等级
		//Q_rankBean db = ManagerPool.dataManager.q_rankContainer.getMap().get(ranklevel);
		//今日获得军功值
		int duobei = 1;
		for (int i = 9112; i <= 9120; i++) {
			List<Buff> buff = ManagerPool.buffManager.getBuffByModelId(player, i);
			if (buff.size() > 0) {
				int dange = i - 9111;
				duobei = duobei + dange;
			}
		}
		
		newrankexp = newrankexp * duobei;
		
		long num = ManagerPool.countManager.getCount(player, CountTypes.DAY_RANK,null);
		if (num == 0) {
			ManagerPool.countManager.addCount(player, CountTypes.DAY_RANK, null,1, 0, 0);	//初始化
		}
		ManagerPool.countManager.addCount(player, CountTypes.DAY_RANK, null, newrankexp);	
		
		MessageUtil.notify_player(player, Notifys.CHAT_SYSTEM,ResManager.getInstance().getString("获得军功{1}点"),String.valueOf(newrankexp));
		ResRankExpToClientMessage msg = new ResRankExpToClientMessage();
		msg.setRankexp(rankexp);
		msg.setDayrankexp((int)ManagerPool.countManager.getCount(player, CountTypes.DAY_RANK,null));

		RankLog rlog = new RankLog();
		rlog.setOldrankexp(rankexp);
		rlog.setOldranklevel(player.getRanklevel());
		rlog.setNewranklevel(-1);
		rlog.setReason(reason);
		rlog.setPlayerid(player.getId());
		rlog.setSid(player.getCreateServerId());
		
//		List<Buff> buff2 = ManagerPool.buffManager.getBuffByModelId(player, 9112);
//		List<Buff> buff3 = ManagerPool.buffManager.getBuffByModelId(player, 9113);
//		List<Buff> buff4 = ManagerPool.buffManager.getBuffByModelId(player, 9114);
//		List<Buff> buff5 = ManagerPool.buffManager.getBuffByModelId(player, 9115);
//		List<Buff> buff6 = ManagerPool.buffManager.getBuffByModelId(player, 9116);
//		List<Buff> buff7 = ManagerPool.buffManager.getBuffByModelId(player, 9117);
//		List<Buff> buff8 = ManagerPool.buffManager.getBuffByModelId(player, 9118);
//		List<Buff> buff9 = ManagerPool.buffManager.getBuffByModelId(player, 9119);
//		List<Buff> buff10 = ManagerPool.buffManager.getBuffByModelId(player, 9120);
		

		
		//累计军功值
		if (player.getRankexp() + newrankexp <= 2100000000) {
			player.setRankexp(player.getRankexp() + newrankexp);
			rlog.setNewranklevel(player.getRanklevel());
			rlog.setNewrankexp(newrankexp);
			LogService.getInstance().execute(rlog);
			while (true) {
				if(!rankup(player)){
					break;
				}
			}

			msg.setRanksum(player.getRankexp());
			MessageUtil.tell_player_message(player, msg);
		}

	}
	
	
	
	
	/**vip领取军功
	 * 
	 */
	public void vipGiveRank(Player player){
		int vip = ManagerPool.vipManager.getPlayerVipId(player);
		Q_vipBean vipdb = ManagerPool.dataManager.q_vipContainer.getMap().get(vip);
		if (vipdb != null) {
			if (vipdb.getQ_rank() > 0) {
				String day = TimeUtil.GetSeriesDay()+"";
				if(player.getActivitiesReward().containsKey("vip_rank")){
					String sday = player.getActivitiesReward().get("vip_rank");
					if (day.equals(sday)) {
						return;
					}
				}
				player.getActivitiesReward().put("vip_rank",day+"");
				addranknum(player , vipdb.getQ_rank(),RankType.VIPGET);
			}
		}
	}

	
	
	
	/**GM测试
	 * 
	 * @param player
	 * @param type
	 * @param num
	 */
	public void testgm(Player player ,int type ,int num ){
		if (type == 1) {
			addranknum(player , num , RankType.OTHER );
		}else if (type == 2) {
			player.setRankexp(0);
			player.setRanklevel(num);
			sendRankinfo(player);
		}
	}
	
	
	
//	/**
//	 * 怪物死亡分配军功
//	 * @param hatreds 
//	 * 
//	 */
//	public void MilitaryExpAllocation(List<Hatred> hatreds, Monster monster,Fighter attacter) {
//		if (hatreds.size() > 0) {
//			Q_monsterBean q_monsterBean = ManagerPool.dataManager.q_monsterContainer.getMap().get(monster.getModelId());
//			if(q_monsterBean == null || q_monsterBean.getQ_ranknum() == 0 ) return;
//			int monlv = q_monsterBean.getQ_grade();
//			boolean isboss = ManagerPool.monsterManager.isBoss(monster.getModelId());
//			Player maxenemy = null;	//最仇恨者
//			Player killer = null;  //击杀 者
//			
//			if(attacter != null && attacter instanceof Pet) {	//击杀者如果是宠物，那就获取主人
//				killer=PlayerManager.getInstance().getPlayer(((Pet)attacter).getOwnerId());
//			}
//			
//			if(attacter != null && attacter instanceof Player) {
//				killer=(Player)attacter;
//			}
//			
//			Fighter attacker = hatreds.get(0).getTarget();  //最仇恨的对象(已自动排序)
//			
//			if(attacker != null && attacker instanceof Pet){
//				maxenemy=PlayerManager.getInstance().getPlayer(((Pet)attacker).getOwnerId());
//			}
//			
//			if (attacker != null && attacker instanceof Player) {
//				maxenemy = (Player) attacker;
//			}
//			
//			if(maxenemy != null) {
//				long teamid = maxenemy.getTeamid();
//				if (teamid > 0) {
//					List<Player> team = ManagerPool.teamManager.getMapSameTeam(maxenemy);
//					if (team != null && team.size() > 0) {
//						ManagerPool.teamManager.getTeamAVG(team);
//						int averagelv = ManagerPool.teamManager.getTeamAVG(team);//取平均等级
//						//如果BOSS小于队伍平均等级5级，则击杀BOSS无军功值
//						if(isboss && averagelv - monlv > 5 )return;
//						//如果怪物等级小于队伍中等级最高玩家5级，则击杀怪物无军功值
//						if(team.get(0).getLevel() - monlv > 5)return;
//						for (Player player : team) {
//							addranknum(player, q_monsterBean.getQ_ranknum());
//							//组队并且进行最后一击的玩家额外奖励军功值
//							if(q_monsterBean.getQ_extra_ranknum() > 0 ){
//								if(killer != null && killer.getId() == player.getId()) {
//									//MessageUtil.notify_player(player, Notifys.NORMAL,"额外奖励军功值");
//									addranknum(player, q_monsterBean.getQ_extra_ranknum());
//								}
//							}
//						}
//					} else {
//						//如果怪物(包括BOSS)等级小于或大于玩家本身等级5级，则击杀怪物无军功值
//						if (Math.abs(monlv - maxenemy.getLevel())> 5) return;
//						addranknum(maxenemy, q_monsterBean.getQ_ranknum());
//						//对BOSS进行最后一击的玩家额外奖励军功值
//						if( isboss && killer != null && killer.getId() == maxenemy.getId() ) {
//							MessageUtil.notify_player(maxenemy, Notifys.NORMAL,"额外奖励军功值");
//							addranknum(maxenemy, q_monsterBean.getQ_extra_ranknum());
//						}
//					}
//				}
//			}
//		}
//	}
	
//
//
//	/**接收前端放入的技能
//	 * 
//	 * @param player
//	 * @param skillModelId
//	 * @param lattice （0-3） 如果是新放入技能,这里写要放入的位置，如果是对调，这里写原位置
//	 * @param swap （0-3	） 如果是新放入技能，这里写-1 
//	 */
//	
//	public void setDraganddropskills(Player player, int skillModelId, int lattice,int swap) {
//		Rank rank = player.getStSaveRankinfo();		//获得军衔信息
//		List<Integer> tab = rank.getCombinationskill();
//		if( tab.size() > 0 && lattice + 1 <= tab.size()) {
//			String skillliest = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.RANK_SKILL_LIST.getValue()).getQ_string_value();
//			if(skillliest  != null && !skillliest.equals("")) {
//				Q_skill_modelBean skillModel= ManagerPool.dataManager.q_skill_modelContainer.getMap().get(skillModelId+"_"+1);
//				if(skillModel != null) {
//					String[] skidStrings = skillliest.split(Symbol.SHUXIAN_REG);
//					for (String specifiedskill : skidStrings) {
//						if(skillModelId == Integer.parseInt(specifiedskill)) {
//							if(swap == -1) {	//表示是拖入新技能
//								tab.add(lattice,  skillModelId);
//								MessageUtil.notify_player(player, Notifys.NORMAL,"放入技能{1}",skillModel.getQ_skillName());
//							}else {			//技能对调位置
//								int tmpskill = tab.get(swap);
//								tab.add(swap,skillModelId);
//								tab.add(lattice,tmpskill);
//							}		
//							rank.setCombinationskill(tab);
//							ManagerPool.playerManager.savePlayer(player);	// 保存玩家
//							break;
//						}
//					}
//				}else {
//					MessageUtil.notify_player(player, Notifys.ERROR,"技能ID:{1},不存在。",String.valueOf(skillModelId));
//				}
//			}
//		}
//	}
}





package com.game.guildflag.timer;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.game.guildflag.manager.GuildFlagManager;
import com.game.guildflag.structs.GuildFiagJF;
import com.game.guildflag.structs.GuildTerritoryFlag;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.message.ResMonsterHpChangeMessage;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.timer.TimerEvent;
import com.game.utils.MessageUtil;

/**领地帮旗处理
 * 
 * @author zhangrong
 *
 */
public class GuildFlagTimer extends TimerEvent{

	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public GuildFlagTimer(int serverId, int lineId, int mapId) {
		super(-1,1000);
		this.serverId=serverId;
		this.lineId=lineId;
		this.mapId = mapId;
	}
	
	private Logger log = Logger.getLogger(GuildFlagTimer.class);
	@Override
	public void action() {
		List<Integer> flagmaplist = ManagerPool.guildFlagManager.getFlagmaplist();
		if(!flagmaplist.contains(this.mapId)){	//不在可插旗地图列表列表中
			return;
		}
		
		int ms =(int)(System.currentTimeMillis()/1000);
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		//Grid[][] mapBlocks = MapManager.getInstance().getMapBlocks(this.mapId);
		ConcurrentHashMap<Integer, GuildTerritoryFlag> territorymap = ManagerPool.guildFlagManager.getTerritorymap();
		List<Integer> flagmonidlist = GuildFlagManager.getFlagidlist();
		if (territorymap.containsKey(this.mapId)) {	//此地图已经被占领
			GuildTerritoryFlag guildTerritoryFlag = territorymap.get(this.mapId);
			boolean is = false;
			if (flagmonidlist.size() == 0) {
				if (ms%60 == 0) {	
					log.error("全局变量表，帮旗数据为空！");
				}
				return;
			}
			for (int flagid : flagmonidlist) {
				List<Monster> monsters = ManagerPool.monsterManager.getSameMonster(map,flagid);	//根据旗帜ID找怪物
				if (monsters.size() > 0) {
					if (flagid != guildTerritoryFlag.getFlagid()) {
						for (Monster monster : monsters) {
							ManagerPool.monsterManager.removeMonster(monster);	//不符合的旗帜全部删除
						}
					}else {
						is = true;	//已经存在
						if (ms%5 == 0) {	//5秒自动补血
							if(monsters.get(0).getHp() < monsters.get(0).getMaxHp()){
								monsters.get(0).setHp(monsters.get(0).getHp()+1);
								ResMonsterHpChangeMessage msg = new ResMonsterHpChangeMessage();
								msg.setMonsterId(monsters.get(0).getId());
								msg.setCurrentHp(monsters.get(0).getHp());
								MessageUtil.tell_round_message(monsters.get(0), msg);
							}
						}
						break;
					}
				}
			}	

			if (is==false && guildTerritoryFlag.getFlagid() > 0) {	//刷新帮旗
				if (!flagmonidlist.contains(guildTerritoryFlag.getFlagid())) {
					log.error(guildTerritoryFlag.getFlagid()+"旗帜不在全局变量表帮旗数据列表中");
					return;
				}
				Monster mon = ManagerPool.monsterManager.createMonster( guildTerritoryFlag.getFlagid() ,  serverId,  lineId,  mapId, guildTerritoryFlag.getPosition());
				mon.setDirection((byte) 0);
				mon.setFriend(2);//设定敌对类型
				mon.setFriendPara(guildTerritoryFlag.getGuildname());//参数
				
				mon.setName("【"+guildTerritoryFlag.getGuildname()+ResManager.getInstance().getString("】的帮旗"));
				mon.getParameters().put("guildid", guildTerritoryFlag.getGuildid());
				mon.getParameters().put("guildname", guildTerritoryFlag.getGuildname());
				ManagerPool.mapManager.enterMap(mon);
			}
		}else {
			if (ms%10 == 0) {	//5秒执行一次,不符合条件的地图进行删除操作
				for (int flagid : flagmonidlist) {
					List<Monster> monsters = ManagerPool.monsterManager.getSameMonster(map,flagid);	//根据旗帜ID找怪物
					if (monsters.size() > 0) {
						for (Monster monster : monsters) {
							ManagerPool.monsterManager.removeMonster(monster);	//不符合的旗帜全部删除
						}
					}
				}	
			}
		}

		if (this.lineId == 1) {		//1线自动清理插旗优先权
			ConcurrentHashMap<Integer, GuildFiagJF> attackjfmap = GuildFlagManager.getAttackjfmap();
			if (attackjfmap.containsKey(map.getMapModelid())) {
				GuildFiagJF guildFiagJF = attackjfmap.get(map.getMapModelid());
				if (guildFiagJF.getPriorityguildid() > 0 && guildFiagJF.getPrioritytime() > 0 ) {
					if (  (int)(System.currentTimeMillis()/1000) - guildFiagJF.getPrioritytime() >= 60* 3) {
						attackjfmap.remove(map.getMapModelid());	//3分钟后清除本地图插旗优先权的帮派记录
					}
				}
			}
		}
		
		//--------------给地图上本帮派成员加BUFF
		int wartime = ManagerPool.guildFlagManager.getFlagwarendtime();
		if (wartime > 0) {
			int type = 0;
			int time = wartime - ms;
			if (time < 1) {
				ManagerPool.guildFlagManager.setFlagwarendtime(0);	//设定时间结束
			}
			
			if ( time <= 5 && time >= 4 ) {	//结束前第3秒调用
				type = 1;
			}else if ( time  == (60*60 - 2)) {	//开始前第2秒调用
				type = 2;
			}

			
			if (type > 0) {
				Iterator<Player> iter = map.getPlayers().values().iterator();//遍历玩家列表
				while (iter.hasNext()) {
					Player player = (Player) iter.next();
					if (player == null) {
						continue;
					}
					//不是本线玩家
					if (player.getServerId() != this.serverId || player.getLine() != this.lineId || player.getMap() != this.mapId) {
						continue;
					}

					ManagerPool.guildFlagManager.checkAndAddGuildFlagBUFF(player, map);
				}
			}
		}

	}
}

package com.game.map.timer;

import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.game.buff.structs.Buff;
import com.game.buff.structs.TriggerType;
import com.game.cooldown.structs.CooldownTypes;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.languageres.manager.ResManager;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.pet.struts.Pet;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerState;
import com.game.prompt.structs.Notifys;
import com.game.structs.Grid;
import com.game.timer.TimerEvent;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.MessageUtil;

public class PlayerBlockTimer extends TimerEvent {
	
	private Logger log = Logger.getLogger(PlayerBlockTimer.class);
	
	private int serverId;
	
	private int lineId;
	
	private int mapId;
	
	public PlayerBlockTimer(int serverId, int lineId, int mapId){
		super(-1, 100);
		this.serverId = serverId;
		this.lineId = lineId;
		this.mapId = mapId;
	}

	@Override
	public void action() {
		//获取地图
		Map map = ManagerPool.mapManager.getMap(serverId, lineId, mapId);
		
		//Q_mapBean mapBean = ManagerPool.dataManager.q_mapContainer.getMap().get(map.getMapModelid());
		
		Grid[][] grids = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
		//获取玩家
		Iterator<Player> iter = map.getBlockingPlayers().values().iterator();
		while (iter.hasNext()) {
			Player player = (Player) iter.next();
			
			if(player.isDie()){
				iter.remove();
				continue;
			}
			
			//log.info(player.getId() + "状态" + PlayerState.BLOCK.compare(player.getState()));
			//非格挡状态
			if(!(PlayerState.BLOCKPREPARE.compare(player.getState()) || PlayerState.BLOCK.compare(player.getState()))){
				iter.remove();
				continue;
			}
			
			//在格挡冷却中
			if(ManagerPool.cooldownManager.isCooldowning(player, CooldownTypes.BLOCK, null, 0)){
				continue;
			}
			
			if(PlayerState.BLOCKPREPARE.compare(player.getState())){
				player.setState(PlayerState.BLOCK);
				ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.CLEAR_DEBUFF,
						null, Global.CLEAN_DEBUFF_TIME);
			}
			
			//伤害值
			int damage = 0;
			
			int cost = 0;
			int percent = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SHIELD_RESUM_PS.getValue()).getQ_int_value();
			cost = player.getMaxMp() * percent / Global.MAX_PROBABILITY;
			int damagePercent = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SHIELD_ATTACK_FACTOR.getValue()).getQ_int_value();
			damage = cost * damagePercent;

			//不消耗内力格挡
			if(FighterState.GEDANGBUJIANSHAONEILI.compare(player.getFightState())){
				cost = 0;
			}
			
			if(player.getMp() < cost){
				iter.remove();
				//解除格挡
				ManagerPool.mapManager.playerStopBlock(player);
				
				continue;
			}
			
			if(cost > 0){
				player.setMp(player.getMp() - cost);
				ManagerPool.playerManager.onMpChange(player);
			}
			
			ManagerPool.cooldownManager.addCooldown(player, CooldownTypes.BLOCK, null, 1000);
			
			//格挡触发的BUFF
			List<Buff> buffs= ManagerPool.buffManager.getBuffTriggerByType(player, TriggerType.BLOCK_COST);
			for (int i = 0; i < buffs.size(); i++) {
				Buff buff = buffs.get(i);
				buff.action(player, player);
			}
			
			//设置敌人类型 0-玩家和怪物 1-玩家
			int type = 0;
			//玩家类型选择 0-全部 1-非同队 2-非本帮会
			int playerAttackType = 0;
			
			//人物pk模式
			playerAttackType = player.getPkState();
			//人物是否站在安全区内
			if(ManagerPool.mapManager.isSafe(player.getPosition(), map.getMapModelid())){
				playerAttackType = 0;
			}
			
//			//调试用
//			ManagerPool.fightManager.showGridInCircle(player, player.getPosition(), Global.BLOCK_DAMAGE_RADIUS, grids);
			//伤害周围敌对目标
			List<Fighter> fighters = ManagerPool.fightManager.selectFighters(map, player, player.getPosition(), Global.BLOCK_DAMAGE_RADIUS, type, playerAttackType, Global.BLOCK_DAMAGE_NUM, grids);
			Iterator<Fighter> iterator = fighters.iterator();
			while (iterator.hasNext()) {
				Fighter fighter = (Fighter) iterator.next();
				if(fighter instanceof Player || fighter instanceof Pet){
					//主动Pk移除被杀保护Buff
					if(FighterState.PKBAOHU.compare(player.getFightState())){
						ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_FOR_KILLED);
						if(fighter instanceof Player){
							log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")对玩家(" + fighter.getId() + ")攻击(内力盾)导致和平保护buff消失");
						}else{
							log.error("玩家(" + player.getId() + ")PK状态为(" + player.getPkState() + ")挂机状态为(" + PlayerState.AUTOFIGHT.compare(player.getState()) + ")对宠物(" + fighter.getId() + ")攻击(内力盾)导致和平保护buff消失");
						}
						MessageUtil.notify_player(player, Notifys.NORMAL, ResManager.getInstance().getString("您主动发起了对其他玩家的PK，和平保护BUFF消失了"));
					}
					
					//主动Pk移除夜晚保护Buff
					if(FighterState.PKBAOHUFORNIGHT.compare(player.getFightState())){
						ManagerPool.buffManager.removeByBuffId(player, Global.PROTECT_IN_NIGHT);
					}
				}
				ManagerPool.fightManager.damage(player, fighter, damage, 1);
			}
		}
	}
}

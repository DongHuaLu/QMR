package com.game.buff.structs;

import java.util.Iterator;

import com.game.data.bean.Q_buffBean;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.manager.ManagerPool;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.player.structs.PlayerAttributeType;
import com.game.player.structs.PlayerState;
import com.game.structs.Grid;
import com.game.utils.MapUtils;

public class StateBuff extends Buff implements IBuff {

	private static final long serialVersionUID = -8241684368134739053L;

	@Override
	public int add(Fighter source, Fighter target) {
		switch (this.getActionType()) {
				//23	在一段时间内享受禁止被PK的保护，持续一段时间后或当玩家主动PK其他玩家时消失
			case BuffType.PKPROTECT:
				source.addFightState(FighterState.PKBAOHU);
				return 1;
				//24	有一定成功几率将目标定身（不能移动，不能跳跃，不能格挡，不能使用技能，允许吃药），持续一段时间后恢复
			case BuffType.STAKME:
				if(target instanceof Player){
					//获取地图
					Map map = ManagerPool.mapManager.getMap((Player)target);
					//获取阻挡信息
					Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
					//获取玩家所在格子
					Grid grid = MapUtils.getGrid(target.getPosition(), blocks);
					//停止走路
					ManagerPool.mapManager.playerStopRun((Player)target, grid.getCenter());
					//停止格挡
					if(PlayerState.BLOCK.compare(((Player) target).getState())){
						ManagerPool.mapManager.setPlayerPosition((Player) target, target.getPosition());
//						((Player) target).setState(PlayerState.STAND);
					}
				}else if(target instanceof Monster){
					//停止走路
					ManagerPool.mapManager.monsterStopRun((Monster)target);
				}
				target.addFightState(FighterState.DINGSHEN);
				return 1;
				//27	有一定成功几率将目标的内力值清零，且禁止目标吃内力恢复类药品（使用药品时返回消息将禁止使用的消息提示），持续一段时间后恢复
			case BuffType.MPEMPTY:
				target.setMp(0);
				target.addFightState(FighterState.JINZHINEILIHUIFU);
				if(target instanceof Player) ManagerPool.playerManager.onMpChange((Player)target);
				else if(target instanceof Monster) ManagerPool.monsterManager.onMpChange((Monster)target);
				return 1;
				//28	有一定成功几率将目标的体力值清零，且禁止目标吃体力恢复类药品（使用药品时返回消息将禁止使用的消息提示），持续一段时间后恢复
			case BuffType.SPEMPTY:
				target.setSp(0);
				target.addFightState(FighterState.JINZHITILIHUIFU);
				if(target instanceof Player) ManagerPool.playerManager.onSpChange((Player)target);
				else if(target instanceof Monster) ManagerPool.monsterManager.onSpChange((Monster)target);
				return 1;
				//31	有一定成功几率将目标的闪避值清零，持续一段时间后恢复
			case BuffType.DODGEEMPTY:
				target.addFightState(FighterState.SHANBIWEILING);
				return 1;
				//32	有一定成功几率将目标的暴击值清零，持续一段时间后恢复
			case BuffType.CRITEMPTY:
				target.addFightState(FighterState.BAOJIWEILING);
				return 1;
				//33	有一定成功几率令目标的方向控制键倒置（上变下，下变上，左变右，右变左），持续一段时间后恢复
			case BuffType.BABELISM:
				target.addFightState(FighterState.FANGXIANGDAOZHI);
				return 1;
				//34	有一定成功几率将目标从坐骑上击落，并且在一段时间内禁止骑乘，持续一段时间后恢复
			case BuffType.FORBIDRIDE:
				if(target instanceof Player){
					//坐骑击落
					ManagerPool.horseManager.unride((Player)target);
				}
				
				target.addFightState(FighterState.JINZHIQICHENG);
				return 1;
				//35	有一定成功几率将目标沉睡（不能移动，不能跳跃，不能格挡，不能使用技能，允许吃药）持续一段时间或者被攻击一次后效果消失
			case BuffType.SLEEP:
				if(target instanceof Player){
					//获取地图
					Map map = ManagerPool.mapManager.getMap((Player)target);
					//获取阻挡信息
					Grid[][] blocks = ManagerPool.mapManager.getMapBlocks(map.getMapModelid());
					//获取玩家所在格子
					Grid grid = MapUtils.getGrid(target.getPosition(), blocks);
					//停止走路
					ManagerPool.mapManager.playerStopRun((Player)target, grid.getCenter());
					//停止格挡
					if(PlayerState.BLOCK.compare(((Player) target).getState())){
						ManagerPool.mapManager.setPlayerPosition((Player) target, target.getPosition());
						//((Player) target).setState(PlayerState.STAND);
					}
				}else if(target instanceof Monster){
					//停止走路
					ManagerPool.mapManager.monsterStopRun((Monster)target);
				}
				target.addFightState(FighterState.SHUIMIAN);
				return 1;
				//41	在一定时间内主角跳跃、二次跳跃不减少体力值
			case BuffType.NOCOSTFORJUMP:
				target.addFightState(FighterState.TIAOYUEBUJIANSHAOTILI);
				return 1;
				//42	在一定时间内主角格挡不减少内力值
			case BuffType.NOCOSTFORBLOCK:
				target.addFightState(FighterState.GEDANGBUJIANSHAONEILI);
				return 1;
				//45	在一定时间内无敌，主角免疫所有伤害，持续一段时间后消失
			case BuffType.INVINCIBLE:
				target.addFightState(FighterState.WUDI);
				return 1;
				//46	在一定时间内若主角死亡则免费原地健康复活一次（不弹出复活提示面板，直接播放复活倒计时），持续一段时间或者死亡复活一次后效果消失
			case BuffType.FREERELIVE:
				target.addFightState(FighterState.MIANFEIFUHUO);
				return 1;
				//52	有一定成功几率将目标的攻击力清零，持续一段时间后恢复
			case BuffType.ATTACKEMPTY:
				target.addFightState(FighterState.GONGJIWEILING);
				return 1;
				//53	有一定成功几率当主角受到负面状态时，予以立刻解除
			case BuffType.RELIEFDEBUFF:
				target.addFightState(FighterState.FUMIANZIDONGJIECHU);
				return 1;
				//54	有一定成功几率将目标的防御力清零，持续一段时间后恢复
			case BuffType.DEFENSEEMPTY:
				target.addFightState(FighterState.FANGYUWEILING);
				return 1;
				//55	有一定成功几率令目标的主界面中央出现黑影（无法看清自己以及自己周边环境），持续一段时间后恢复
			case BuffType.BLIND:
				target.addFightState(FighterState.ZHIMANG);
				return 1;
				//60	有一定成功几率令目标无法使用任何药品，持续一段时间
			case BuffType.FROBIDDRUG:
				target.addFightState(FighterState.JINZHISHIYONGYAOPIN);
				return 1;
				//61	令内力盾新增功能，反弹墨子剑法前三式的无视防御伤害
			case BuffType.REBOUND:
				target.addFightState(FighterState.FANTAN);
				return 1;
				//66	夜晚挂机保护PK, 当玩家主动PK其他玩家时消失
			case BuffType.PROTECTFORNIGHT:
				target.addFightState(FighterState.PKBAOHUFORNIGHT);
				return 1;
				//82 勾魂BUFF
			case BuffType.GOUHUNBUFF:
				if(target instanceof Player){
					target.addFightState(FighterState.DAMAGEONMOVE);
					((Player) target).setMovestep(0);
				}
				return 1;
				//84 玲珑BUFF
			case BuffType.LINGLONGBUFF:
				target.addFightState(FighterState.LINGLONG);
				Q_buffBean thismodel = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(thismodel==null) return 0;
				Iterator<Buff> iter = target.getBuffs().iterator();
				while (iter.hasNext()) {
					Buff buff = (Buff) iter.next();
					Q_buffBean model = ManagerPool.dataManager.q_buffContainer.getMap().get(buff.getModelId());
					if(model==null) continue;
					if(ManagerPool.buffManager.check(model, BuffConst.LINGLONG_BUFF)){
						if(model.getQ_effect_type() == 1){
							buff.setTotalRemainTime(0);
							buff.setTotalTime(0);
						}else if(model.getQ_effect_type() == 2 && !buff.getBackups().containsKey(String.valueOf(BuffConst.LINGLONG_BUFF))){
							buff.setTotalRemainTime(buff.getTotalRemainTime() + (long)(buff.getTotalTime() * ((double)thismodel.getQ_effect_ratio()/10000)));
							buff.setTotalTime((long)(buff.getTotalTime() * ((double)(10000 + thismodel.getQ_effect_ratio())/10000)));
							buff.getBackups().put(String.valueOf(BuffConst.LINGLONG_BUFF), "1");
							ManagerPool.buffManager.sendChangeBuffMessage(source, target, buff);
						}
					}
				}
				return 1;
				//85 遮影BUFF
			case BuffType.ZHEYINGBUFF:
				target.addFightState(FighterState.ZHEYING);
				return 1;
				//86 强行PK BUFF
			case BuffType.FORCEPKBUFF:
				target.addFightState(FighterState.FORCEPK);
				return 1;
				//87 紫芒BUFF
			case BuffType.ZIMANGBUFF:
				source.addFightState(FighterState.FORCEDODGE);
				return 1;
				//90 boss虚弱
			case BuffType.BOSSWEAK:
				source.addFightState(FighterState.DINGSHEN);
				source.addFightState(FighterState.WEAK);
				return 1;
				//91 骑战兵器缴械
			case BuffType.HORSEWEAPONUNWEAR:
				if(target instanceof Player){
					target.addFightState(FighterState.HORSEWEAPONUNWEAR);
					ManagerPool.playerAttributeManager.countPlayerAttribute((Player)target, PlayerAttributeType.HORSE_WEAPON);
					ManagerPool.horseWeaponManager.broadcastHorseWeaponInfo((Player)target);
				}
				return 1;
				//92 防骑战兵器缴械
			case BuffType.HORSEWEAPONPROTECT:
				source.addFightState(FighterState.HORSEWEAPONPROTECT);
				return 1;
		}
		return 0;
	}

	@Override
	public int action(Fighter source, Fighter target) {
		return 0;
	}

	@Override
	public int remove(Fighter source) {
		switch (this.getActionType()) {
				//23	在一段时间内享受禁止被PK的保护，持续一段时间后或当玩家主动PK其他玩家时消失
			case BuffType.PKPROTECT:
				source.removeFightState(FighterState.PKBAOHU);
				return 1;
				//24	有一定成功几率将目标定身（不能移动，不能跳跃，不能格挡，不能使用技能，允许吃药），持续一段时间后恢复
			case BuffType.STAKME:
				source.removeFightState(FighterState.DINGSHEN);
				return 1;
				//27	有一定成功几率将目标的内力值清零，且禁止目标吃内力恢复类药品（使用药品时返回消息将禁止使用的消息提示），持续一段时间后恢复
			case BuffType.MPEMPTY:
				source.removeFightState(FighterState.JINZHINEILIHUIFU);
				return 1;
				//28	有一定成功几率将目标的体力值清零，且禁止目标吃体力恢复类药品（使用药品时返回消息将禁止使用的消息提示），持续一段时间后恢复
			case BuffType.SPEMPTY:
				source.removeFightState(FighterState.JINZHITILIHUIFU);
				return 1;
				//31	有一定成功几率将目标的闪避值清零，持续一段时间后恢复
			case BuffType.DODGEEMPTY:
				source.removeFightState(FighterState.SHANBIWEILING);
				return 1;
				//32	有一定成功几率将目标的暴击值清零，持续一段时间后恢复
			case BuffType.CRITEMPTY:
				source.removeFightState(FighterState.BAOJIWEILING);
				return 1;
				//33	有一定成功几率令目标的方向控制键倒置（上变下，下变上，左变右，右变左），持续一段时间后恢复
			case BuffType.BABELISM:
				source.removeFightState(FighterState.FANGXIANGDAOZHI);
				return 1;
				//34	有一定成功几率将目标从坐骑上击落，并且在一段时间内禁止骑乘，持续一段时间后恢复
			case BuffType.FORBIDRIDE:
				source.removeFightState(FighterState.JINZHIQICHENG);
				return 1;
				//35	有一定成功几率将目标沉睡（不能移动，不能跳跃，不能格挡，不能使用技能，允许吃药）持续一段时间或者被攻击一次后效果消失
			case BuffType.SLEEP:
				source.removeFightState(FighterState.SHUIMIAN);
				return 1;
				//41	在一定时间内主角跳跃、二次跳跃不减少体力值
			case BuffType.NOCOSTFORJUMP:
				source.removeFightState(FighterState.TIAOYUEBUJIANSHAOTILI);
				return 1;
				//42	在一定时间内主角格挡不减少内力值
			case BuffType.NOCOSTFORBLOCK:
				source.removeFightState(FighterState.GEDANGBUJIANSHAONEILI);
				return 1;
				//45	在一定时间内无敌，主角免疫所有伤害，持续一段时间后消失
			case BuffType.INVINCIBLE:
				source.removeFightState(FighterState.WUDI);
				return 1;
				//46	在一定时间内若主角死亡则免费原地健康复活一次（不弹出复活提示面板，直接播放复活倒计时），持续一段时间或者死亡复活一次后效果消失
			case BuffType.FREERELIVE:
				source.removeFightState(FighterState.MIANFEIFUHUO);
				return 1;
				//52	有一定成功几率将目标的攻击力清零，持续一段时间后恢复
			case BuffType.ATTACKEMPTY:
				source.removeFightState(FighterState.GONGJIWEILING);
				return 1;
				//53	有一定成功几率当主角受到负面状态时，予以立刻解除
			case BuffType.RELIEFDEBUFF:
				source.removeFightState(FighterState.FUMIANZIDONGJIECHU);
				return 1;
				//54	有一定成功几率将目标的防御力清零，持续一段时间后恢复
			case BuffType.DEFENSEEMPTY:
				source.removeFightState(FighterState.FANGYUWEILING);
				return 1;
				//55	有一定成功几率令目标的主界面中央出现黑影（无法看清自己以及自己周边环境），持续一段时间后恢复
			case BuffType.BLIND:
				source.removeFightState(FighterState.ZHIMANG);
				return 1;
				//60	有一定成功几率令目标无法使用任何药品，持续一段时间
			case BuffType.FROBIDDRUG:
				source.removeFightState(FighterState.JINZHISHIYONGYAOPIN);
				return 1;
				//61	令内力盾新增功能，反弹墨子剑法前三式的无视防御伤害
			case BuffType.REBOUND:
				source.removeFightState(FighterState.FANTAN);
				return 1;
				//66	夜晚挂机保护PK, 当玩家主动PK其他玩家时消失
			case BuffType.PROTECTFORNIGHT:
				source.removeFightState(FighterState.PKBAOHUFORNIGHT);
				return 1;
				//82 勾魂BUFF
			case BuffType.GOUHUNBUFF:
				if(source instanceof Player){
					source.removeFightState(FighterState.DAMAGEONMOVE);
				}
				return 1;
				//84 玲珑BUFF
			case BuffType.LINGLONGBUFF:
				source.removeFightState(FighterState.LINGLONG);
				return 1;
				//85 遮影BUFF
			case BuffType.ZHEYINGBUFF:
				source.removeFightState(FighterState.ZHEYING);
				return 1;
				//86 强行PK BUFF
			case BuffType.FORCEPKBUFF:
				source.removeFightState(FighterState.FORCEPK);
				return 1;
				//87 紫芒BUFF
			case BuffType.ZIMANGBUFF:
				source.removeFightState(FighterState.FORCEDODGE);
				return 1;
				//90 boss虚弱
			case BuffType.BOSSWEAK:
				source.removeFightState(FighterState.DINGSHEN);
				source.removeFightState(FighterState.WEAK);
				return 1;
				//91 骑战兵器缴械
			case BuffType.HORSEWEAPONUNWEAR:
				if(source instanceof Player){
					source.removeFightState(FighterState.HORSEWEAPONUNWEAR);
					ManagerPool.playerAttributeManager.countPlayerAttribute((Player)source, PlayerAttributeType.HORSE_WEAPON);
					ManagerPool.horseWeaponManager.broadcastHorseWeaponInfo((Player)source);
				}
				return 1;
				//92 防骑战兵器缴械
			case BuffType.HORSEWEAPONPROTECT:
				source.removeFightState(FighterState.HORSEWEAPONPROTECT);
				return 1;
		}
		return 0;
	}

}

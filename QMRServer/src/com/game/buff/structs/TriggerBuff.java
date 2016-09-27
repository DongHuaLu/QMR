package com.game.buff.structs;

import java.util.ArrayList;
import java.util.Iterator;

import com.game.cooldown.structs.CooldownTypes;
import com.game.data.bean.Q_buffBean;
import com.game.fight.structs.Fighter;
import com.game.fight.structs.FighterState;
import com.game.manager.ManagerPool;
import com.game.map.structs.Area;
import com.game.map.structs.Map;
import com.game.monster.structs.Monster;
import com.game.player.structs.Player;
import com.game.utils.CommonConfig;
import com.game.utils.Global;
import com.game.utils.RandomUtils;

public class TriggerBuff extends Buff implements IBuff {

	private static final long serialVersionUID = 8246583584824311187L;

	@Override
	public int add(Fighter source, Fighter target) {
		return 0;
	}

	@Override
	public int action(Fighter source, Fighter target) {
		Q_buffBean buff = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
		if(buff==null) return 0;
		
		int maxHp = 0;
		int maxMp = 0;
		int maxSp = 0;
		
		int hp = 0;
		int mp = 0;
		int sp = 0;
		int min = 0;
		
		switch(this.getActionType()){
				//43	在一定时间内使用格挡，每当主角格挡消耗真气时，均按主角生命上限的一定比例恢复主角当前生命值
			case BuffType.RECOVERFORBLOCK:
				maxHp = source.getMaxHp();
				if(source.getHp() >= maxHp) return 0;
				hp = buff.getQ_effect_value() + this.getValue();
				hp += maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				source.setHp(source.getHp() + hp);
				if(source.getHp() > maxHp) source.setHp(maxHp);
				
				min = maxHp * 2000/ Global.MAX_PROBABILITY;
				if(hp >= min){
					log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + hp + ",因为内力盾消耗恢复");
				}
				ManagerPool.playerManager.onHpChange((Player)source);
				return 1;
				//44	在一定时间内使用跳跃，每当主角跳跃时，均按主角生命上限的一定比例恢复主角当前生命值
			case BuffType.RECOVERFORJUMP:
				maxHp = source.getMaxHp();
				if(source.getHp() >= maxHp) return 0;
				hp = buff.getQ_effect_value() + this.getValue();
				hp += maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				source.setHp(source.getHp() + hp);
				if(source.getHp() > maxHp) source.setHp(maxHp);
				min = maxHp * 2000/ Global.MAX_PROBABILITY;
				if(hp >= min){
					log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + hp + ",因为跳跃");
				}
				ManagerPool.playerManager.onHpChange((Player)source);
				return 1;
				//47	在一定时间内若主角攻击则必定产生多倍暴击，持续一段时间或者使用三次后效果消失
			case BuffType.MULCRIT:
				if(this.getParameter() < buff.getQ_effect_value() + this.getValue()){
					this.setParameter(this.getParameter() + 1);
					if(this.getParameter() < buff.getQ_effect_value() + this.getValue()){
						return 1;
					}else{
						return 2;
					}
				}
				break;
				//48	在一定时间内每次主角发出攻击，若命中，均按其自身内力上限的一定比例恢复自身当前内力值，按同样比例减少目标内力值（吸蓝）
			case BuffType.RECOVERMPONATTACK:
				//恢复内力
				maxMp = source.getMaxMp();
				mp = buff.getQ_effect_value() + this.getValue();
				mp += maxMp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				//是否禁止内力恢复
				if(mp>0 && !FighterState.JINZHINEILIHUIFU.compare(source.getFightState())){
					source.setMp(source.getMp() + mp);
					if(source.getMp() > maxMp) source.setMp(maxMp);
					else if(source.getMp() < 0) source.setMp(0);
					if(source instanceof Player) ManagerPool.playerManager.onMpChange((Player)source);
					else if(source instanceof Monster) ManagerPool.monsterManager.onMpChange((Monster)source);
				}
				//目标减少内力
				maxMp = target.getMaxMp();
				mp = buff.getQ_effect_value() + this.getValue();
				mp += maxMp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				target.setMp(target.getMp() - mp);
				if(target.getMp() > maxMp) target.setMp(maxMp);
				else if(target.getMp() < 0) target.setMp(0);
				if(target instanceof Player) ManagerPool.playerManager.onMpChange((Player)target);
				else if(target instanceof Monster) ManagerPool.monsterManager.onMpChange((Monster)target);
				return 1;
				//49	在一定时间内每次主角发出攻击，若命中，均按其自身体力上限的一定比例恢复自身当前体力值，按同样比例减少目标体力值（吸体）
			case BuffType.RECOVERSPONATTACK:
				//恢复体力
				maxSp = source.getMaxSp();
				sp = buff.getQ_effect_value() + this.getValue();
				sp += maxSp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				//是否禁止内力恢复
				if(sp>0 && !FighterState.JINZHITILIHUIFU.compare(source.getFightState())){
					if(source.getSp() >= maxSp) return 0;
					source.setSp(source.getSp() + sp);
					if(source.getSp() > maxSp) source.setSp(maxSp);
					else if(source.getSp() < 0) source.setSp(0);
					if(source instanceof Player) ManagerPool.playerManager.onSpChange((Player)source);
					else if(source instanceof Monster) ManagerPool.monsterManager.onSpChange((Monster)source);
				}
				//目标减少内力
				maxSp = target.getMaxSp();
				sp = buff.getQ_effect_value() + this.getValue();
				sp += maxSp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				target.setSp(target.getSp() - sp);
				if(target.getSp() > maxSp) target.setSp(maxSp);
				else if(target.getSp() < 0) target.setSp(0);
				if(target instanceof Player) ManagerPool.playerManager.onSpChange((Player)target);
				else if(target instanceof Monster) ManagerPool.monsterManager.onSpChange((Monster)target);
				return 1;
				//50	在一定时间内每次主角发出攻击，若命中，均按其自身生命上限的一定比例恢复自身当前生命值（吸血）
			case BuffType.RECOVERHPONATTACK:
				maxHp = source.getMaxHp();
				hp = buff.getQ_effect_value() + this.getValue();
				hp += maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				source.setHp(source.getHp() + hp);
				if(source.getHp() > maxHp) source.setHp(maxHp);
				min = maxHp * 2000/ Global.MAX_PROBABILITY;
				if(hp >= min){
					log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + hp + ",因为吸血");
				}
				ManagerPool.playerManager.onHpChange((Player)source);
				return 1;
				//51	在一定时间内令目标每次在被攻击时，若被命中，均至少按照其生命上限的一定比例减少其当前生命值（魅惑）
			case BuffType.REDUCEHPONBEATTACK:
				maxHp = source.getMaxHp();
				hp = buff.getQ_effect_value() + this.getValue();
				hp += maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				source.setHp(source.getHp() - hp);
				if(source.getHp() > maxHp) source.setHp(maxHp);
				else if(source.getHp() < 1){
					source.setHp(1);
				}
				min = maxHp * 2000/ Global.MAX_PROBABILITY;
				if(hp >= min){
					log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + hp + ",因为魅惑");
				}
				if(source instanceof Player) ManagerPool.playerManager.onHpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onHpChange((Monster)source);
				return 1;
				//56	有一定成功几率令目标每次被攻击命中后（攻击可来自任何人），所受到的伤害均按比例额外加深，持续一段时间后恢复
			case BuffType.DEEPENDAMAGE:
//				this.setParameter(this.getParameter() + (buff.getQ_effect_ratio() + this.getPercent()));
				this.setParameter((buff.getQ_effect_ratio() + this.getPercent()));
				return 1;
				//62	令内力盾新增功能，每隔2秒清除一个自己所受的负面BUFF（按所受时间倒序清除）
			case BuffType.CLEARDEBUFF:
				//buff数量为0或正在清除CD中
				if(ManagerPool.cooldownManager.isCooldowning((Player)source, CooldownTypes.CLEAR_DEBUFF, null) || source.getBuffs().size() == 0) return 0;
				
				Buff debuff = ManagerPool.buffManager.getLastDebuff(source);
				if(debuff!=null){
					ManagerPool.buffManager.removeBuff(source, debuff);
				}
				//增加清除CD
				ManagerPool.cooldownManager.addCooldown((Player)source, CooldownTypes.CLEAR_DEBUFF, null, Global.CLEAN_DEBUFF_TIME);
				return 1;
				//70	令内力盾新增功能，每次消耗内力时，恢复对应的血量
			case BuffType.RECOVERSAMEFORBLOCK:
				if(source.getHp() >= source.getMaxHp()) return 0;
				int cost = 0;
				int percent = ManagerPool.dataManager.q_globalContainer.getMap().get(CommonConfig.SHIELD_RESUM_PS.getValue()).getQ_int_value();
				cost = source.getMaxMp() * percent / Global.MAX_PROBABILITY;
				//不消耗内力格挡
				if(FighterState.GEDANGBUJIANSHAONEILI.compare(source.getFightState())){
					cost = 0;
				}
				if(cost > 0){
					source.setHp(source.getHp() + cost);
					min = maxHp * 2000/ Global.MAX_PROBABILITY;
					if(cost >= min){
						log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + cost + ",因为内力盾 71 buff");
					}
					if(source.getHp() > source.getMaxHp()) source.setHp(source.getMaxHp());
					ManagerPool.playerManager.onHpChange((Player)source);
				}
				return 1;
			case BuffType.BAQUANBUFF:
				ManagerPool.buffManager.addBuff(source, target, BuffConst.DINGSHEN, 2000, 0, 0);
				return 1;
				//79	仁德BUFF
			case BuffType.RENDEBUFF:
				if(!(source instanceof Player)) return 0;
				Q_buffBean buffModel = ManagerPool.dataManager.q_buffContainer.getMap().get(this.getModelId());
				if(buffModel==null) return 0;
				if(RandomUtils.random(Global.MAX_PROBABILITY) >= buffModel.getQ_trigger_probability()){
					return 0;
				}
				Player player = (Player)source;
				Map map = ManagerPool.mapManager.getMap(player);
				if(map==null) return 0;
				
				//找到周围20格内的同帮会成员
				int[] areaIds = ManagerPool.mapManager.getRoundAreas(player.getPosition(), map, 500);
				ArrayList<Player> members = new ArrayList<Player>();
				for (int areaId : areaIds) {
					Area area = map.getAreas().get(areaId);
					if(area==null) continue;
					Iterator<Player> iter = area.getPlayers().iterator();
					while (iter.hasNext()) {
						Player other = (Player) iter.next();
						if(other.getGuildId()==0 || other.getId()==player.getId() || other.getGuildId()!=player.getGuildId() || other.isDie()){
							continue;
						}else{
							members.add(other);
						}
					}
				}
				//随机寻找3位队友加血
				int k = 3;
				while(k>0 && members.size()>0){
					k--;
					Player other = members.remove(RandomUtils.random(members.size()));
					maxHp = other.getMaxHp();
					hp = buff.getQ_effect_value() + this.getValue();
					hp += maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
					other.setHp(other.getHp() + hp);
					if(other.getHp() > maxHp) other.setHp(maxHp);
					min = maxHp * 2000/ Global.MAX_PROBABILITY;
					if(hp >= min){
						log.error("玩家：" + other.getName() + "(" + other.getId() + ")回复生命" + hp + ",因为仁德");
					}
					ManagerPool.playerManager.onHpChange(other);
				}
				return 1;
				//83 梅花BUFF
			case BuffType.MEIHUABUFF:
				//this.setParameter(this.getParameter() + (buff.getQ_effect_ratio() + this.getPercent()));
				ManagerPool.buffManager.addBuff(source, source, BuffConst.MEIHUA_BUFF, 0, 0, 0);
				return 1;
				//88 跳跃减血BUFF
			case BuffType.DAMAGEFORJUMP:
				maxHp = source.getMaxHp();
				hp = buff.getQ_effect_value() + this.getValue();
				hp += maxHp * (buff.getQ_effect_ratio() + this.getPercent()) / Global.MAX_PROBABILITY;
				source.setHp(source.getHp() + hp);
				if(source.getHp() > maxHp) source.setHp(maxHp);
				else if(source.getHp() < 1){
					source.setHp(1);
				}
				min = maxHp * 2000/ Global.MAX_PROBABILITY;
				if(hp >= min){
					log.error("玩家：" + source.getName() + "(" + source.getId() + ")回复生命" + hp + ",因为跳跃81buff");
				}
				if(source instanceof Player) ManagerPool.playerManager.onHpChange((Player)source);
				else if(source instanceof Monster) ManagerPool.monsterManager.onHpChange((Monster)source);
				return 1;
		}
		return 0;
	}

	@Override
	public int remove(Fighter source) {
		return 0;
	}

	/**
	 * 触发类型
	 * @return 1-进攻命中触发 2-防御触发 3-格挡消耗触发 4-跳跃触发 5暴击计算触发
	 */
	public int getTriggerType(){
		switch(this.getActionType()){
				//43	在一定时间内使用格挡，每当主角格挡消耗真气时，均按主角生命上限的一定比例恢复主角当前生命值
			case BuffType.RECOVERFORBLOCK:
				return TriggerType.BLOCK_COST;
				//44	在一定时间内使用跳跃，每当主角跳跃时，均按主角生命上限的一定比例恢复主角当前生命值
			case BuffType.RECOVERFORJUMP:
				return TriggerType.JUMP;
				//47	在一定时间内若主角攻击则必定产生多倍暴击，持续一段时间或者使用三次后效果消失
			case BuffType.MULCRIT:
				return TriggerType.CRIT;
				//48	在一定时间内每次主角发出攻击，若命中，均按其自身内力上限的一定比例恢复自身当前内力值，按同样比例减少目标内力值（吸蓝）
			case BuffType.RECOVERMPONATTACK:
				return TriggerType.ATTACK_SUCCESS;
				//49	在一定时间内每次主角发出攻击，若命中，均按其自身体力上限的一定比例恢复自身当前体力值，按同样比例减少目标体力值（吸体）
			case BuffType.RECOVERSPONATTACK:
				return TriggerType.ATTACK_SUCCESS;
				//50	在一定时间内每次主角发出攻击，若命中，均按其自身生命上限的一定比例恢复自身当前生命值（吸血）
			case BuffType.RECOVERHPONATTACK:
				return TriggerType.ATTACK_SUCCESS;
				//51	在一定时间内令目标每次在被攻击时，若被命中，均至少按照其生命上限的一定比例减少其当前生命值（魅惑）
			case BuffType.REDUCEHPONBEATTACK:
				return TriggerType.DEFENSE;
				//56	有一定成功几率令目标每次被攻击命中后（攻击可来自任何人），所受到的伤害均按比例额外加深，持续一段时间后恢复
			case BuffType.DEEPENDAMAGE:
				return TriggerType.DEFENSE;
				//62	令内力盾新增功能，每隔2秒清除一个自己所受的负面BUFF（按所受时间倒序清除）
			case BuffType.CLEARDEBUFF:
				return TriggerType.BLOCK_COST;
				//70	令内力盾新增功能，每次消耗内力时，恢复对应的血量
			case BuffType.RECOVERSAMEFORBLOCK:
				return TriggerType.BLOCK_COST;
				//79	仁德BUFF
			case BuffType.RENDEBUFF:
				return TriggerType.ATTACK_SUCCESS;
				//83 梅花BUFF
			case BuffType.MEIHUABUFF:
				return TriggerType.DEFENSE;
				//88 跳跃减血BUFF
			case BuffType.DAMAGEFORJUMP:
				return TriggerType.JUMP;
		}
		return 0;
	}
}
